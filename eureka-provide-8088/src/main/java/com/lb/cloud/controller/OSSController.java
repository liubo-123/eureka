package com.lb.cloud.controller;

import com.lb.cloud.dto.OssCallbackResult;
import com.lb.cloud.dto.OssPolicyResult;
import com.lb.cloud.service.Impl.OssServiceImpl;
import com.lb.cloud.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping("/mall/oss")
@Api(tags = "OSSController", description = "Oss管理")
public class OSSController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OssServiceImpl.class);

    @Autowired
    private OssServiceImpl ossService;

    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    @ResponseBody
    public String  policy() {
        OssPolicyResult policy = ossService.policy();
        return JsonUtils.toJson(policy);
    }

    @ApiOperation(value = "oss上传成功回调")
    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    @ResponseBody
    public String callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return JsonUtils.toJson(ossCallbackResult);
    }
    @ApiOperation(value = "新建bucket")
    @RequestMapping(value = "/createBucket", method = RequestMethod.POST)
    @ResponseBody
    public String createBucket(String bucketName) {
        String bucketName1 = ossService.createBucketName(bucketName);
        return JsonUtils.toJson(bucketName1);
    }
    @ApiOperation(value = "删除bucket")
    @RequestMapping(value = "/deleteBucket", method = RequestMethod.POST)
    @ResponseBody
    public String deleteBucket(String bucketName) {
        ossService.deleteBucket(bucketName);
        return "success";
    }
    @ApiOperation(value = "新建上传目录")
    @RequestMapping(value = "/createFolder", method = RequestMethod.POST)
    @ResponseBody
    public String createFolder(String bucketName, String folder) {
        String folder1 = ossService.createFolder(bucketName, folder);
        return JsonUtils.toJson(folder1);
    }
    @ApiOperation(value = "删除文件")
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFile(String bucketName, String folder, String key) {
        ossService.deleteFile(bucketName,folder,key);
        return "success";
    }
    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(String names) {

        String   s= ossService.uploadFile(names);

        return JsonUtils.toJson(s);
    }
    @ApiOperation(value = "下载文件")
    @RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
    @ResponseBody
    public String downloadFile(String bucketName,String pathName,String localName) {
        String s = ossService.downloadFile(bucketName,pathName,localName);
        return JsonUtils.toJson(s);
    }
    @ApiOperation(value = "列举文件下所有的文件url信息")
    @RequestMapping(value = "/getFiles", method = RequestMethod.POST)
    @ResponseBody
    public String getFiles(String bucketName,String pathName) {
        List list = ossService.getFiles(bucketName,pathName);
        return JsonUtils.toJson(list);
    }
}
