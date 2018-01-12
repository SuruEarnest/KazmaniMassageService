/**
 * 
 *@author Udofa N Michael    
 */
package com.kazmani.upload;

import java.io.BufferedOutputStream;   
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class ImageUploadService {
	/* private byte[] imagePhoto;
	 ImageUploadData newImgData = new ImageUploadData();
	 static boolean evaluated = true; // To prevent repeated evaluation
	 public  ImageUploadData insertImage(int image_id, InputStream image_File){
		 try{
			 if(image_File != null){
	             byte[] imgByte = readSourceContent(image_File).toByteArray();
	             byte[] realImgByte = regularizeData(imgByte);
	             imagePhoto = realImgByte;
	         }	
			String dbOwner = MbranchConnect.getDbname();	
		    Connection con = MbranchConnect.makeConnection();
		    PreparedStatement cstmt = con.prepareStatement("UPDATE "+dbOwner+"..rm_image SET photo = ?  WHERE image_id = ?");	    
		    cstmt.setBytes(1, imagePhoto);
			cstmt.setInt(2, image_id);
			int Success=cstmt.executeUpdate();
			if(Success>0){
				String tomCatUrl = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\MobileBranchAPI" ;
			 	String photoPrefix = tomCatUrl+"/img/P";
	            String imageName = null,imageNam = null;
	            File photoFile;
	            BufferedOutputStream photoBos;
				Statement stmt = con.createStatement();
			    ResultSet rs = stmt.executeQuery("select rim_no, image_id, photo from "+dbOwner+"..rm_image WHERE image_id = "+image_id+" order by image_id");
			    if (rs.next())
	            {
	                int rim = rs.getInt(1);
	                int imageId = rs.getInt(2);
	                byte[] photo = regularizeImageData(rs.getBytes(3));//note
	                if (photo != null)
	                {
	                    imageName = photoPrefix + "_"+ rim +"_"+ imageId + ".jpg";
	                    imageNam = "P" + "_"+ rim +"_"+ imageId + ".jpg";
	                    //imageFile
	                    photoFile = new File(imageName);
	                    photoBos = new BufferedOutputStream(new FileOutputStream(photoFile));
	                    photoBos.write(photo);
	                    photoBos.flush();
	                    photoBos.close();
	                    //set in dataClass
	                    newImgData.setRimNo(rim);
	                    newImgData.setImageId(imageId);
		                newImgData.setUserImg(imageNam);
		                newImgData.setSuccessMsg("Upload Successful");
	                }else {
	                	newImgData.setErrorMSg("Image Upload Failed");
	                }
	            }
			}
			else{
				newImgData.setErrorMSg("Image Upload Failed");
			}	   
		}catch (Exception e) {
			newImgData.setErrorMSg(e.getMessage());
		}
			return newImgData;
	  }
	 
	   public static ByteArrayOutputStream readSourceContent(InputStream inputStream) throws IOException {
	       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	       int nextChar;
	       try {
	           while ((nextChar = inputStream.read()) != -1) {
	               outputStream.write(nextChar);
	           }
	           outputStream.flush();
	       } catch (IOException e) {
	           throw new IOException("Exception occurred while reading content", e);
	       }
	       return outputStream;
	   }
	   
	   private static byte[] readContentIntoByteArray(File file)
	   {
	      FileInputStream fileInputStream = null;
	      byte[] bFile = new byte[(int) file.length()];
	      try
	      {
	         //convert file into array of bytes
	         fileInputStream = new FileInputStream(file);
	         fileInputStream.read(bFile);
	         fileInputStream.close();
	         for (int i = 0; i < bFile.length; i++)
	         {
	            System.out.print((char) bFile[i]);
	         }
	      }
	      catch (Exception e)
	      {
	         e.printStackTrace();
	      }
	      return bFile;
	   }
	   
		///RegularizeImageData: Only God and Jacob Understands what this method does.
	    public static byte[] regularizeData(byte[] imageData){
	        if (imageData == null || imageData.length < 7) return null; 
	        File file2= new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\MobileBranchAPI\\imgPrefix\\centura_prefix.dbs");
			String jj = file2.getAbsolutePath();
			File file = new File(jj);
	        byte[] imagePrefix = readContentIntoByteArray(file);
	        byte[] finalImage = null;
	        try {
	        	finalImage = new byte[imagePrefix.length + imageData.length];
	        	// copy imagePrefix into start of finalImage (from pos 0, copy ciphertext.length bytes)
	        	System.arraycopy(imagePrefix, 0, finalImage, 0, imagePrefix.length);
	        	// copy imageData into end of finalImage (from pos ciphertext.length, copy mac.length bytes)
	        	System.arraycopy(imageData, 0, finalImage, imagePrefix.length, imageData.length);
	        } catch (Exception e)
	        {
	            e.printStackTrace();
	            return null;
	        }
	        return finalImage;
	    }
	    
		static boolean evaluated2 = false; // To prevent repeated evaluation
		
		///RegularizeImageData: Only God and Jacob Understands what this method does.
	    public static byte[] regularizeImageData(byte[] imageData){
	        if (imageData == null || imageData.length < 7) return null;          
	        if(!evaluated2) {
	        	try {
	                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\MobileBranchAPI\\imgPrefix\\centura_prefix.dbs"));
	                bos.write(imageData, 0, 21);
	                bos.flush();
	                bos.close();
	            }
	            catch (IOException ex)
	            {
	                ex.printStackTrace();
	            }
	        	evaluated2 = true;
	        }
	        byte[] tempImage, finalImage = null;
	        try {
	           // byte[] imagePrefix = "[MOBILE]".getBytes();
	            tempImage = new byte[imageData.length - 21];
	            new ByteArrayInputStream(imageData, 21, imageData.length).read(tempImage, 0, tempImage.length);
	             finalImage = new byte[tempImage.length];
				int j = 0;
	     		for (int i = 0; i < tempImage.length; i++) {
					finalImage[j++] = tempImage[i];
	            }
	        } catch (Exception e)
	        {
	            e.printStackTrace();
	            return null;
	        }
	        return finalImage;
	    }//
	    */
}
