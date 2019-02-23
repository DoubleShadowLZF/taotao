package com.taotao.common.client;

import com.alibaba.fastjson.JSONObject;
import com.taotao.common.config.FtpProperties;
import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 */
public class FtpClient {

	private FtpProperties ftpProperties;

	public FtpClient(FtpProperties ftpProperties){
		this.ftpProperties = ftpProperties;
	}

	/**
	 * 上传文件
	 * @param file
	 * @return
	 *  //成功时
	 * {
	 *        "error" : 0,
	 *        "url" : "http://www.example.com/path/to/file.ext"
	 *}
	 * //失败时
	 * {
	 *        "error" : 1,
	 *        "message" : "错误信息"
	 * }
	 */
	public String uploadFile(MultipartFile file) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String filePath = ftpProperties.getFilePath() + new DateTime().toString("/yyyy/MM/dd");
			String imageName = IDUtils.genImageName()+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			boolean isUpload = FtpUtil.uploadFile(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(),
					ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, imageName, file.getInputStream());
			if (!isUpload) {
				result.put("error", 1);
				result.put("message", "上传图片失败");
				return JsonUtils.objectToJson(result.toString());
			}
			String url = "http://" + ftpProperties.getHost()  +  filePath + File.separator + imageName;
			result.put("error", 0);
			result.put("url", url);
			return JSONObject.toJSON(result).toString();//{"error":0,"url":"192.168.100.120/home/double/Pictures/taotao/2018/10/11/1539226835636578.jpg"}
		} catch (ConnectException e){
			result.put("error", 0);
			result.put("message", "Ftp服务器连接异常");
			return JsonUtils.objectToJson(result.toString());
		} catch (IOException e ) {
			result.put("error", 0);
			result.put("message", "上传图片异常");
			return JsonUtils.objectToJson(result.toString());
		}
	}


}
