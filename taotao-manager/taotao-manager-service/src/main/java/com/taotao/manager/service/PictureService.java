package com.taotao.manager.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description 图片文件服务
 */
public interface PictureService {
	/**
	 * 上传商品图片
	 * @param file 图片信息
	 * @return
	 *
	 */
	String uploadPictureByFtp(MultipartFile file);

	String uploadPictureByFdfs(MultipartFile file) throws IOException;


}
