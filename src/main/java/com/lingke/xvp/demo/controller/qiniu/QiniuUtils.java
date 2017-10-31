package com.lingke.xvp.demo.controller.qiniu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.model.FileInfo;

public class QiniuUtils {
	private static final String bucketName = "xiaovp";
	private static String businessName = "xvp";
	private static QiniuService qiniuService = new QiniuService("VOtzHS57IVTOTWtHqzKRrzYeJVFKf0wXYq9ERT4s","PqFGKKHQDsrpIDEsvDlRbYTCZctbVFsFU7OygAZ1",bucketName);
	public static String uploadFile(File file,String type) throws Exception{
		if(file == null){
			throw new FileNotFoundException("file not exists");
		}
		String suffix = file.getName().split("\\.")[1];
		byte [] content = FileUtils.readFileToByteArray(file);
		String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." +suffix;
		return qiniuService.uploadData(content,businessName + "/" + type + "/"+fileName).getKey();
	}
	
	
	public static String uploadFile(byte [] data,String type,String fileName) throws QiniuException{
		String tmpFileName = businessName + "/" + type + "/" +fileName;
		FileInfo fileInfo = qiniuService.info(tmpFileName);
		if(fileInfo != null){
			qiniuService.deleteData(bucketName,tmpFileName);
		}
		return qiniuService.uploadData(data, tmpFileName).getKey();
	}
	
	public static String uploadFile(File file,String type,String fileName) throws QiniuException,IOException{
		return uploadFile(FileUtils.readFileToByteArray(file), type, fileName);
	}
	
	public static boolean isExists(String type,String fileName){
		String tmpFileName = businessName + "/" + type + "/" +fileName;
		FileInfo fileInfo = qiniuService.info(tmpFileName);
		return fileInfo != null;
	}
	
	public static QiniuPicInfo uploadData(byte [] data,String filePath) throws QiniuException{
		return qiniuService.uploadData(data, businessName + "/" + filePath);
	}
	
	public static FileInfo info(String key) throws QiniuException{
		return qiniuService.info(key);
	}
	
//	public static void main(String[] args) {
//		try {
//			uploadFile(new File("C:\\Users\\yu\\Desktop\\文本\\图片\\1.png"), "test");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
