package com.ravi.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ravi.blog.services.FileService;


@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		
		String name=file.getOriginalFilename();
		//imagename
		
		
		
		String randomID=UUID.randomUUID().toString();
		String fileName1=randomID.concat(name.substring(name.lastIndexOf(".")));
		
		
		String filepath=path + File.separator + fileName1;
		
		
	//create folder if not created
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		
		//files copy
		Files.copy(file.getInputStream(), Paths.get(filepath));
		
		
		
		
		return name;
	}

	@Override
	public InputStream getResource(String path, String filename) throws FileNotFoundException {
	
		
		String fullPath=path +File.separator +filename;
		InputStream is= new  FileInputStream(fullPath);
		
		//db logic to return inputstream;
		return is;
	}

	
	
}
