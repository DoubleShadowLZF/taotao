package com.taotao.manager.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.taotao.common.client.FtpClient;
import com.taotao.manager.client.FdfsClient;
import com.taotao.manager.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 图片文件服务实现
 */
@Service
public class PictureServiceImpl implements PictureService {

	@Autowired
	private FtpClient ftpClient;

	@Autowired
	private FdfsClient fdfsClient;

	@Override
	public String uploadPictureByFtp(MultipartFile file) {
		return ftpClient.uploadFile(file);
	}

	@Override
	public String uploadPictureByFdfs(MultipartFile file) throws IOException {
		Map result =new HashMap(3);
		String path = null;
		path = fdfsClient.uploadFile(file);
		if(path == null ){
			result.put("error",1);
			result.put("message","文件上传失败");
		}
		result.put("error",0);
		result.put("url",path);
		String json = JSONObject.toJSONString(result);
		return json;
	}
}
