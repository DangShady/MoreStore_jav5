package com.mstore.helps;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class UploadsFile {
	
	public static void uploadImage(MultipartFile file,String uploadDir,String fileName) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try(InputStream inputStream = file.getInputStream()){
		
		Path filePath = uploadPath.resolve(fileName);
		
		
		
		Files.copy(inputStream, filePath ,StandardCopyOption.REPLACE_EXISTING);
		
		}catch (Exception e) {
			throw new IOException("Could not save upload file:" + fileName);
		}
		
	}
	
}	
