package com.lingke.xvp.demo.controller.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class QiniuService {
    private UploadManager uploadManager = new UploadManager();
    private BucketManager buckManager;
    private Auth auth;
    private String bucketName;

    public QiniuService(String accessKey, String secretKey, String bucketName) {
        this.auth = Auth.create(accessKey, secretKey);
        this.buckManager = new BucketManager(this.auth);
        this.bucketName = bucketName;
    }

    public QiniuPicInfo uploadData(byte[] data, String filePath) throws QiniuException {
        Response res = this.uploadManager.put(data, filePath, this.getUpToken(filePath));
        return (QiniuPicInfo)res.jsonToObject(QiniuPicInfo.class);
    }

    public void deleteData(String bucket, String filePath) throws QiniuException {
        this.buckManager.delete(bucket, filePath);
    }

    public FileInfo info(String key) {
        try {
            return this.buckManager.stat(this.bucketName, key);
        } catch (Exception var3) {
            return null;
        }
    }

    private String getUpToken(String filePath) {
        return this.auth.uploadToken(this.bucketName, (String)null, 3600L, (new StringMap()).putNotEmpty("returnBody", "{\"key\": $(key), \"hash\": $(etag), \"width\": $(imageInfo.width), \"height\": $(imageInfo.height),\"fsize\": $(imageInfo.fsize)}"));
    }

}
