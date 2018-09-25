package com.xiandaojia.controller.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xiandaojia.common.utils.FileUtil;
import com.xiandaojia.controller.BaseController;

@RestController
@RequestMapping("file")
public class FileController extends BaseController{
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(HttpServletRequest request, HttpServletResponse response) {
		BufferedInputStream bis = null;
		FileInputStream fis = null;
		BufferedOutputStream bos = null;
		try {
			String path = URLDecoder.decode(request.getParameter("path"), "utf-8");
			fis = new FileInputStream(path);
			bis = new BufferedInputStream(fis);

			int length = bis.available();
			response.setContentLength(length);

			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] data2 = new byte[1024];
			while (bis.read(data2) != -1) {
				bos.write(data2);
				bos.flush();
			}
		} catch (FileNotFoundException e) {
			logger.error("错误", e);
		} catch (IOException e) {
			logger.error("错误", e);
		} finally {
			try {
				fis.close();
				bis.close();
			} catch (IOException e) {
				logger.error("错误", e);
			}
		}
		return null;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String uploadPath = null;
		try {
			uploadPath = FileUtil.upload(file, "productImg");
		} catch (Exception e) {
			logger.error("错误", e);
			return getErrorResultMsg("文件上传失败!");
		}
		return getSuccessResultMsg(uploadPath);
	}
}
