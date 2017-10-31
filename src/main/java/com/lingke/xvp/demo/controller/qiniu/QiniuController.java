package com.lingke.xvp.demo.controller.qiniu;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.qiniu.common.QiniuException;

/**
 * Created by yuwb on 2017-03-13. 通用类相关业务处理
 */
@RestController
@RequestMapping(value = "/qiniu")
public class QiniuController {
	
	private String qiniu_host = "http://or7x1joja.bkt.clouddn.com/";
	 /**
     * 上传图片
     * @param request
     * @param file
     * @return
	 * @throws IOException 
	 * @throws QiniuException 
     */
    @RequestMapping(value="/uploadPic",method= RequestMethod.POST)
    public XvpResponse uploadAskPic(HttpServletRequest request,
                               @RequestParam("file") MultipartFile file) throws Exception{
        String inputFileName = file.getOriginalFilename();
        String fileType = inputFileName.substring(inputFileName.lastIndexOf(".")+1);
        String fileName = "course/images/"+ UUID.randomUUID().toString()+"."+fileType;
        QiniuPicInfo info = QiniuUtils.uploadData(file.getBytes(), fileName);
        String key = info.getKey();
        String picUrl = qiniu_host + key;
        QiniuResponse response = new QiniuResponse();
        response.setUrl(picUrl);
        return response;
    }
	
}
