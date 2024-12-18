package com.web.tech.model;

import java.io.FileOutputStream;
import java.io.InputStream;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component("imageHelper") //@Bean(name="imageHelper")
public class ImageHelper {
   public static final String rootPath="C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\EmployeeApp\\src\\main\\resources\\static\\imgs";
   
   public boolean isSaveImage(MultipartFile image) {
	   try {
		  InputStream input=image.getInputStream();
		  byte b[]=new byte[input.available()];
		  input.read(b);
		  FileOutputStream fout=new FileOutputStream(rootPath+"\\"+image.getOriginalFilename());
		  fout.write(b);
		  fout.close();
		  return true;  
	   }
	   catch(Exception ex) {
		   return false;
	   }
   }
}
