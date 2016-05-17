package com.acv.gallery.util;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	private final static String RUTA_IMAGE = Environment.getExternalStoragePublicDirectory(
			Environment.DIRECTORY_DCIM).toString() + "/100ANDRO";

	private final static String JPG_EXTENSION = "jpg";
	private final static String PNG_EXTENSION = "png";
	private final static String JPEG_EXTENSION = "jpeg";
	private final static String GIF_EXTENSION = "gif";
	
	public FileUtil() {
	}
	
	public List<File> getImages(){
		File[] lArrFicheros = new File(RUTA_IMAGE).listFiles();
		List<File> lArrImages = new ArrayList<>();
		
		for (File file : lArrFicheros) {
			if(isImage(file.getName()))
				lArrImages.add(file);
		}
		
		return lArrImages;
	}
	
	private boolean isImage(String fileName){
		return fileName.endsWith(JPEG_EXTENSION) ||
				fileName.endsWith(JPG_EXTENSION) ||
				fileName.endsWith(JPG_EXTENSION.toUpperCase()) ||
				fileName.endsWith(PNG_EXTENSION) ||
				fileName.endsWith(GIF_EXTENSION);
	}
}
