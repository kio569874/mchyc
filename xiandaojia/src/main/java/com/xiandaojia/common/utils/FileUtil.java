package com.xiandaojia.common.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	private static final String basePath = "/home/xiandaojia/file";
	
	public static String upload(MultipartFile file,String path) throws IllegalStateException, IOException{
		String fileName = file.getOriginalFilename(); 
		String prefix = "." + fileName.substring(fileName.lastIndexOf(".")+1);
		String id = String.valueOf(System.currentTimeMillis());
		File targetDir = new File(basePath + "/" + path);
		if(!targetDir.exists()){
			targetDir.mkdirs();
		}
		File targetFile = new File(basePath + "/" + path + "/" + id + prefix);
		file.transferTo(targetFile);
		return targetFile.getAbsolutePath();
	};
}
