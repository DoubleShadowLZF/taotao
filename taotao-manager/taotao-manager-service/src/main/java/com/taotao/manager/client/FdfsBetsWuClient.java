package com.taotao.manager.client;

import com.github.tobato.fastdfs.conn.TrackerConnectionManager;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.TrackerLocator;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.taotao.manager.properties.FdfsProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

@Component
@EnableConfigurationProperties(FdfsProperties.class)
@Slf4j
public class FdfsBetsWuClient extends FdfsClient {


	@Autowired
	private FdfsProperties fastDfsConfig;

	@Autowired
	private FastFileStorageClient fastFileStorageClient;
	@Autowired
	private TrackerConnectionManager trackerConnectionManager;

	public FdfsBetsWuClient(){}

	public FdfsBetsWuClient(FastFileStorageClient fastFileStorageClient, TrackerConnectionManager trackerConnectionManager){
		this.fastFileStorageClient = fastFileStorageClient;
		this.trackerConnectionManager = trackerConnectionManager;
	}

	/**
	 * 类初始化函数
	 */
	@PostConstruct
	public void inint(){
	}

	/**
	 * 上传文件
	 *
	 * @param file 文件对象
	 * @return 文件访问地址
	 * @throws IOException
	 */
	@Override
	public String uploadFile(MultipartFile file) throws IOException {
		StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
		return getResAccessUrl(storePath);
	}

	/**
	 * 将一段字符串生成一个文件上传
	 *
	 * @param content       文件内容
	 * @param fileExtension
	 * @return
	 */
	public String uploadFile(String content, String fileExtension) {
		byte[] buff = content.getBytes(Charset.forName("UTF-8"));
		ByteArrayInputStream stream = new ByteArrayInputStream(buff);
		StorePath storePath = fastFileStorageClient.uploadFile(stream, buff.length, fileExtension, null);
		return getResAccessUrl(storePath);
	}

	// 封装图片完整URL地址
	private String getResAccessUrl(StorePath storePath) {
		TrackerLocator trackerLocator = new TrackerLocator(trackerConnectionManager.getTrackerList());
		InetSocketAddress address = trackerLocator.getTrackerAddress();
		String hostName = address.getHostString();
		String fileUrl = "http://" + hostName + fastDfsConfig.getNginxFdfsPort() + "/" + storePath.getFullPath();
		return fileUrl;
	}

	/**
	 * 删除文件
	 *
	 * @param fileUrl 文件访问地址
	 * @return
	 */
	@Override
	public void deleteFile(String fileUrl) {@
		if (StringUtils.isEmpty(fileUrl)) {
			return;
		}
		try {
			StorePath storePath = StorePath.praseFromUrl(fileUrl);
			fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());
		} catch (FdfsUnsupportStorePathException e) {
			log.warn(e.getMessage());
		}
	}

}

