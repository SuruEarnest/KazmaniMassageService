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


public class SignatureUploadService {
	/* private byte[] signPhoto;
	 ImageUploadData newSignData = new ImageUploadData();
	 static boolean evaluated = true; // To prevent repeated evaluation
	 public  ImageUploadData insertSignature(int image_id, InputStream sign_File){
		 try{
			 if(sign_File != null){
	             byte[] signByte = readSourceContent(sign_File).toByteArray();
	             byte[] realSignByte = regularizeData(signByte);
	             signPhoto = realSignByte;
	         }		
		 	String dbOwner = MbranchConnect.getDbname();
		    Connection con = MbranchConnect.makeConnection();
		    PreparedStatement cstmt = con.prepareStatement("UPDATE "+dbOwner+"..rm_image SET signature = ?  WHERE image_id = ?");	    
		    cstmt.setBytes(1, signPhoto);
			cstmt.setInt(2, image_id);
			int Success=cstmt.executeUpdate();
			if(Success>0){
				String tomCatUrl = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\MobileBranchAPI" ;
			 	String signPrefix = tomCatUrl+"/img/S";
	            String signName = null,signNam = null;
	            File signFile;
	            BufferedOutputStream photoBos;
				Statement stmt = con.createStatement();
			    ResultSet rs = stmt.executeQuery("select rim_no, image_id, signature from "+dbOwner+"..rm_image WHERE image_id = "+image_id+" order by image_id");
			    if (rs.next())
	            {
	                int rim = rs.getInt(1);
	                int imageId = rs.getInt(2);
	                byte[] signature = regularizeImageData(rs.getBytes(3));//note
	                if (signature != null)
	                {
	                	signName = signPrefix + "_"+ rim +"_"+ imageId + ".jpg";
	                	signNam = "S" + "_"+ rim +"_"+ imageId + ".jpg";
	                    //imageFile
	                	signFile = new File(signName);
	                    photoBos = new BufferedOutputStream(new FileOutputStream(signFile));
	                    photoBos.write(signature);
	                    photoBos.flush();
	                    photoBos.close();
	                    //set in dataClass
	                    newSignData.setRimNo(rim);
	                    newSignData.setImageId(imageId);
	                    newSignData.setUserSign(signNam);
	                    newSignData.setSuccessMsg("Upload Successful");
	                }else {
	                	newSignData.setErrorMSg("Image Upload Failed");
	                }
	            }
			}
			else{
				newSignData.setErrorMSg("Image Upload Failed");
			}	   
		}catch (Exception e) {
			newSignData.setErrorMSg(e.getMessage());
		}
			return newSignData;
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
