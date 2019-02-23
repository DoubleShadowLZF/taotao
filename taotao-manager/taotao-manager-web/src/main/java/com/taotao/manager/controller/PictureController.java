package com.taotao.manager.controller;

import com.taotao.manager.service.PictureService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Description
 */
@RestController
@RequestMapping("/pic")
public class PictureController {

	@Resource
	private PictureService pictureService;

//	@PostMapping(value = "/upload", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	public String postPictureByFtp(@RequestParam("uploadFile") MultipartFile file) {
		String result = pictureService.uploadPictureByFtp(file);
		return result;
	}

	@PostMapping(value = "/upload", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	public String postPictureByFastDfs(@RequestParam("uploadFile") MultipartFile file) throws IOException {
		String result = pictureService.uploadPictureByFdfs(file);
		return result;
	}

	@GetMapping("download_fastdfs")
	public void downloadPicture(String storePath){

	}
}
