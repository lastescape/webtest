package com.qdh.mvc.qcloud;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by qdh on 2018/5/24.
 */
public class CosnTool {
    private COSClient cosClient;

    public void init() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKIDhDTTDhz6xd3KNCA7E5g06wVoreAQD9cp", "6yax4wXsxmMgkX6QUIsDJ9pMenhSyGJ8");
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
        // 3 生成cos客户端
        cosClient = new COSClient(cred, clientConfig);
    }

    public boolean saveToCosn(String bucketName, String filePath, String fileName, MultipartFile file) throws IOException {
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        // 指定要上传到 COS 上的路径
        String key = fileName;
        PutObjectRequest putObjectRequest = null;
        putObjectRequest = new PutObjectRequest(bucketName, filePath + "/" + key, multipartToFile(file));
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        String eTag = putObjectResult.getETag();
        return true;
    }

    public void shutDownClient() {
        if (cosClient != null) {
            cosClient.shutdown();
        }
    }

    /**
     * MultipartFile 转换成File
     *
     * @param multfile 原文件类型
     * @return File
     * @throws IOException
     */
    private File multipartToFile(MultipartFile multfile) throws IOException {
        CommonsMultipartFile cf = (CommonsMultipartFile)multfile;
        //这个myfile是MultipartFile的
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File file = fi.getStoreLocation();
        //手动创建临时文件
        if(file.length() < 2048){
            File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +
                    file.getName());
            multfile.transferTo(tmpFile);
            return tmpFile;
        }
        return file;
    }

}
