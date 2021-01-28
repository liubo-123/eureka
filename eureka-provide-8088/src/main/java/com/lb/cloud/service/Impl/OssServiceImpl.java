package com.lb.cloud.service.Impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.*;
import com.lb.cloud.dto.OssCallbackParam;
import com.lb.cloud.dto.OssCallbackResult;
import com.lb.cloud.dto.OssPolicyResult;
import com.lb.cloud.service.OssService;
import com.lb.cloud.util.OSSUtils;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * oss上传管理Service实现类
 */
@Service
public class OssServiceImpl implements OssService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OssServiceImpl.class);
    @Value("${aliyun.oss.policy.expire}")
    private int ALIYUN_OSS_EXPIRE;
    @Value("${aliyun.oss.maxSize}")
    private int ALIYUN_OSS_MAX_SIZE;
    @Value("${aliyun.oss.callback}")
    private String ALIYUN_OSS_CALLBACK;
    @Value("${aliyun.oss.bucketName}")
    private String ALIYUN_OSS_BUCKET_NAME;
    @Value("${aliyun.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;
    @Value("${aliyun.oss.dir.prefix}")
    private String ALIYUN_OSS_DIR_PREFIX;

    @Autowired
    private OSSClient ossClient;

    /**
     * 签名生成
     */
    @Override
    public OssPolicyResult policy() {
        OssPolicyResult result = new OssPolicyResult();
        // 存储目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dir = ALIYUN_OSS_DIR_PREFIX+sdf.format(new Date());
        // 签名有效期
        long expireEndTime = System.currentTimeMillis() + ALIYUN_OSS_EXPIRE * 1000;
        Date expiration = new Date(expireEndTime);
        // 文件大小
        long maxSize = ALIYUN_OSS_MAX_SIZE * 1024 * 1024;
        // 回调
        OssCallbackParam callback = new OssCallbackParam();
        callback.setCallbackUrl(ALIYUN_OSS_CALLBACK);
        callback.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
        callback.setCallbackBodyType("application/x-www-form-urlencoded");
        // 提交节点
        String action = "http://" + ALIYUN_OSS_BUCKET_NAME + "." + ALIYUN_OSS_ENDPOINT;
        try {
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String policy = BinaryUtil.toBase64String(binaryData);
            String signature = ossClient.calculatePostSignature(postPolicy);
            String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(callback).toString().getBytes("utf-8"));
            // 返回结果
            result.setAccessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId());
            result.setPolicy(policy);
            result.setSignature(signature);
            result.setDir(dir);
            result.setCallback(callbackData);
            result.setHost(action);
        } catch (Exception e) {
            LOGGER.error("签名生成失败", e);
        }
        return result;
    }

    @Override
    public OssCallbackResult callback(HttpServletRequest request) {
        OssCallbackResult result= new OssCallbackResult();
        String filename = request.getParameter("filename");
        filename = "http://".concat(ALIYUN_OSS_BUCKET_NAME).concat(".").concat(ALIYUN_OSS_ENDPOINT).concat("/").concat(filename);
        result.setFilename(filename);
        result.setSize(request.getParameter("size"));
        result.setMimeType(request.getParameter("mimeType"));
        result.setWidth(request.getParameter("width"));
        result.setHeight(request.getParameter("height"));
        return result;
    }

    @Override
    public String createBucketName(String bucketName) {
        if (!ossClient.doesBucketExist(bucketName)) {
            // 创建存储空间
            Bucket bucket = ossClient.createBucket(bucketName);
            LOGGER.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketName;
    }

    @Override
    public void  deleteBucket(String bucketName) {
        ossClient.deleteBucket(bucketName);
        LOGGER.info("删除" + bucketName + "Bucket成功");
    }

    @Override
    public String createFolder(String bucketName, String folder) {
        // 判断文件夹是否存在，不存在则创建
        if (!ossClient.doesObjectExist(bucketName, folder)) {
            // 创建文件夹
            ossClient.putObject(bucketName, folder, new ByteArrayInputStream(new byte[0]));
            LOGGER.info("创建文件夹成功");
            // 得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, folder);
            String fileDir = object.getKey();
            return fileDir;
        }
        return folder;
    }

    @Override
    public void deleteFile(String bucketName, String folder, String key) {
                ossClient.deleteObject(bucketName, folder + key);
        LOGGER.info("删除" + bucketName + "下的文件" + folder + key + "成功");
    }

    @Override
    public String uploadFile(String name) {
        String res  ="";
        String[] split = name.split(",");
        try {
            for(String s:split) {
                String bucketName = "lbtest-oss";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                File file = new File("E:\\" + s);
                // 以输入流的形式上传文件
                String folder = "lb/images" + sdf.format(new Date()) + "/";
                InputStream is = new FileInputStream(file);
                // 文件名
                String fileName = file.getName();
                LOGGER.info("上传到路径" + folder + fileName.substring(fileName.lastIndexOf(".")));
                // 文件大小
                Long fileSize = file.length();
                // 创建上传Object的Metadata
                ObjectMetadata metadata = new ObjectMetadata();
                // 上传的文件的长度
                metadata.setContentLength(is.available());
                // 指定该Object被下载时的网页的缓存行为
                metadata.setCacheControl("no-cache");
                // 指定该Object下设置Header
                metadata.setHeader("Pragma", "no-cache");
                // 指定该Object被下载时的内容编码格式
                metadata.setContentEncoding("utf-8");
                // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
                // 如果没有扩展名则填默认值application/octet-stream
                metadata.setContentType(OSSUtils.getContentType(fileName));
                // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
                metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
                // 上传文件 (上传文件流的形式)
                PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);
                // 解析结果
                res = folder + fileName + "=====" + putResult.getETag();
            }
                ossClient.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return res;
    }

    @Override
    public String uploadImage() {
        return null;
    }

    @Override
    public String uploadVideo() {
        return null;
    }

    @Override
    public String downloadFile(String bucketName, String pathName, String localName) {
        try {
            // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
            // 设置URL过期时间为10年  3600l* 1000*24*365*10
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
            ossClient.getObject(new GetObjectRequest(bucketName, pathName), new File(localName));
            URL url = ossClient.generatePresignedUrl(bucketName, pathName,expiration);
            if (url != null) {
                return url.toString();
            }
            // 关闭OSSClient。
            ossClient.shutdown();
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }

    }

    @Override
    public List getFiles(String bucket, String filePath) {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucket);
        // 设置正斜线（/）为文件夹的分隔符。
        listObjectsRequest.setDelimiter("/");
        // 设置prefix参数来获取filePath目录下的所有文件。
        if (StringUtils.isNotBlank(filePath)) {
            listObjectsRequest.setPrefix(filePath + "/");
        }
        // 列出文件
        ObjectListing listing = ossClient.listObjects(listObjectsRequest);
        // 遍历所有commonPrefix
        List list = new ArrayList<>();
        for (OSSObjectSummary objectSummaries : listing.getObjectSummaries()) {
            String url = "https://" + bucket + "." + ALIYUN_OSS_ENDPOINT + "/" + objectSummaries.getKey();
            LOGGER.info("url:====" + url);
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
            URL url1 = ossClient.generatePresignedUrl(bucket, objectSummaries.getKey(),expiration);
            list.add(url1);
        }
        // 关闭OSSClient
        //ossClient.shutdown();
        return list;
    }
}