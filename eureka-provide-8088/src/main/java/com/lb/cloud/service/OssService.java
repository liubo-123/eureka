package com.lb.cloud.service;

import com.lb.cloud.dto.OssCallbackResult;
import com.lb.cloud.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.List;

/**
 * oss上传管理Service
 */
public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);

    /**
     *
     * @param bucketName
     * @return
     */
    String createBucketName(String bucketName);

    /**
     *
     * @param bucketName
     * @return
     */
    void deleteBucket(String bucketName);

    /**
     *
     * @param bucketName
     * @param folder
     * @return
     */
    String createFolder(String bucketName, String folder);

    /**
     *
     * @param bucketName
     * @param folder
     * @param key
     * @return
     */
    void deleteFile(String bucketName, String folder, String key);

    /**
     *
     * @return
     */
    String uploadFile(String names);

    /**
     *
     * @return
     */
    String uploadImage();

    /**
     *
     * @return
     */
    String uploadVideo();

    /**
     *
     * @return
     */
    String downloadFile(String bucketName,String pathName,String localName);

    /**
     *
     * @param bucket
     * @param filePath
     * @return
     */
    List getFiles(String bucket, String filePath);
}