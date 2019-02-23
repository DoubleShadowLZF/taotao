package com.taotao.manager.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description
 */
public abstract class FdfsClient {

	protected final Logger logger = LoggerFactory.getLogger(FdfsBetsWuClient.class);

	public abstract String uploadFile(MultipartFile file) throws IOException;

	public abstract void deleteFile(String fileUrl);

}
