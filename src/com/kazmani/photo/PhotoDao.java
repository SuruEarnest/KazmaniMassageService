package com.kazmani.photo;

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

import com.kazmani.config.DbConnector;

public class PhotoDao {

    private byte[] imagePhoto;
    DbConnector connector = new DbConnector();

    static boolean evaluated = true; // To prevent repeated evaluation

    public Photo insertImage(int image_id, InputStream image_File) {
	Photo photoData = new Photo();

	try {

	    if (image_File != null) {
		byte[] imgByte = readSourceContent(image_File).toByteArray();
		byte[] realImgByte = regularizeData(imgByte);
		imagePhoto = realImgByte;
	    }

	    Connection con = connector.makeConnection();
	    PreparedStatement pst = con
		    .prepareStatement("UPDATE PROFILE_PHOTO SET photo = ?  WHERE image_id = ?");
	    pst.setBytes(1, imagePhoto);
	    pst.setInt(2, image_id);
	    int Success = pst.executeUpdate();
	    if (Success > 0) {
		String tomCatUrl = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\MobileMassageAPI";
		String photoPrefix = tomCatUrl + "/img/P";
		String imageName = null, imageNam = null;
		File photoFile;
		BufferedOutputStream photoBos;
		Statement stmt = con.createStatement();
		ResultSet rs = stmt
			.executeQuery("select image_id, photo from PROFILE_PHOTO WHERE image_id = "
				+ image_id + " order by image_id");
		if (rs.next()) {

		    int imageId = rs.getInt(1);
		    byte[] photo = regularizeImageData(rs.getBytes(2));// note
		    if (photo != null) {
			imageName = photoPrefix + "_" + imageId + ".jpg";
			imageNam = "P" + "_" + imageId + ".jpg";
			// imageFile
			photoFile = new File(imageName);
			photoBos = new BufferedOutputStream(
				new FileOutputStream(photoFile));
			photoBos.write(photo);
			photoBos.flush();
			photoBos.close();
			// set in dataClass

			photoData.setPhotoId(imageId);
			photoData.setUserPhoto(imageNam);
			photoData.setMessage("Upload Successful");
		    } else {
			photoData.setErrorMessage("Image Upload Failed");
		    }
		}
	    } else {
		photoData.setErrorMessage("Image Upload Failed");
	    }
	} catch (Exception e) {
	    photoData.setErrorMessage(e.getMessage());
	}
	return photoData;
    }

    public Photo getProfilePhoto(int imageId) {

	Photo photoData = new Photo();
	try {
	    String tomCatUrl = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\MobileBranchAPI";

	    String photoPrefix = tomCatUrl + "/img/P";
	    String imageName = null, imageNam = null;

	    File photoFile;
	    BufferedOutputStream photoBos;

	    Connection con = connector.makeConnection();
	    Statement cstmt = con.createStatement();
	    ResultSet rs = cstmt
		    .executeQuery("select  image_id,photo from PROFILE_PHOTO where image_id = "
			    + imageId + " order by image_id");
	    if (rs.next()) {

		System.out.println("Retrieving Images For ImageId No : "
			+ imageId + "\n");
		byte[] photo = regularizeImageData(rs.getBytes(2));// note

		if (photo != null) {
		    imageName = photoPrefix + "_" + imageId + ".jpg";
		    imageNam = "P" + "_" + imageId + ".jpg";
		    // imageFile
		    photoFile = new File(imageName);
		    photoBos = new BufferedOutputStream(new FileOutputStream(
			    photoFile));
		    photoBos.write(photo);
		    photoBos.flush();
		    photoBos.close();
		    // set in dataClass
		    photoData.setPhotoId(imageId);
		    photoData.setUserPhoto(imageNam);

		} else {
		    photoData.setErrorMessage("No Image for this customer ");

		}
	    } else {
		photoData.setErrorMessage("No Image for this customer ");

	    }
	} catch (Exception e) {

	    photoData.setErrorMessage(e.getMessage());

	}
	return photoData;
    }

    public static ByteArrayOutputStream readSourceContent(
	    InputStream inputStream) throws IOException {
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

    private static byte[] readContentIntoByteArray(File file) {
	FileInputStream fileInputStream = null;
	byte[] bFile = new byte[(int) file.length()];
	try {
	    // convert file into array of bytes
	    fileInputStream = new FileInputStream(file);
	    fileInputStream.read(bFile);
	    fileInputStream.close();
	    for (int i = 0; i < bFile.length; i++) {
		System.out.print((char) bFile[i]);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return bFile;
    }

    // /RegularizeImageData: Only God and Jacob Understands what this method
    // does.
    public static byte[] regularizeData(byte[] imageData) {
	if (imageData == null || imageData.length < 7)
	    return null;
	File file2 = new File(
		"C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\MobileMassageAPI\\imgPrefix\\centura_prefix.dbs");
	String jj = file2.getAbsolutePath();
	File file = new File(jj);
	byte[] imagePrefix = readContentIntoByteArray(file);
	byte[] finalImage = null;
	try {
	    finalImage = new byte[imagePrefix.length + imageData.length];
	    // copy imagePrefix into start of finalImage (from pos 0, copy
	    // ciphertext.length bytes)
	    System.arraycopy(imagePrefix, 0, finalImage, 0, imagePrefix.length);
	    // copy imageData into end of finalImage (from pos
	    // ciphertext.length, copy mac.length bytes)
	    System.arraycopy(imageData, 0, finalImage, imagePrefix.length,
		    imageData.length);
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
	return finalImage;
    }

    static boolean evaluated2 = false; // To prevent repeated evaluation

    // /RegularizeImageData: Only God and Jacob Understands what this method
    // does.
    public static byte[] regularizeImageData(byte[] imageData) {
	if (imageData == null || imageData.length < 7)
	    return null;
	if (!evaluated2) {
	    try {
		BufferedOutputStream bos = new BufferedOutputStream(
			new FileOutputStream(
				"C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\MobileMassageAPI\\imgPrefix\\centura_prefix.dbs"));
		bos.write(imageData, 0, 21);
		bos.flush();
		bos.close();
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	    evaluated2 = true;
	}
	byte[] tempImage, finalImage = null;
	try {
	    // byte[] imagePrefix = "[MOBILE]".getBytes();
	    tempImage = new byte[imageData.length - 21];
	    new ByteArrayInputStream(imageData, 21, imageData.length).read(
		    tempImage, 0, tempImage.length);
	    finalImage = new byte[tempImage.length];
	    int j = 0;
	    for (int i = 0; i < tempImage.length; i++) {
		finalImage[j++] = tempImage[i];
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
	return finalImage;
    }//

}
