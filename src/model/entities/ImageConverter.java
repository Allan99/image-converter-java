package model.entities;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageConverter {

	public static boolean convertFormat(String inputImagePath, String outputImagePath, String formatName)
			throws IOException {
		
		FileInputStream inputStream = new FileInputStream(inputImagePath);
		FileOutputStream outputStream = new FileOutputStream(outputImagePath);
		 
		BufferedImage inputImage = ImageIO.read(inputStream);
		
		boolean result = ImageIO.write(inputImage, formatName, outputStream);
		
		outputStream.close();
		inputStream.close();
		
		return result;
	}

}
