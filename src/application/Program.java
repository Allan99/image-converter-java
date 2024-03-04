package application;

import java.io.IOException;

import model.entities.ImageConverter;

public class Program {

	public static void main(String[] args) {
		
		String inputImage = "C:\\Users\\allan\\Downloads\\michael-martin-photo.jpg";
		String outputImage = "C:\\Users\\allan\\Downloads\\michael-martin-photo.png";
		String imageFormat = "PNG";
		
		try {
			boolean result = ImageConverter.convertFormat(inputImage, outputImage, imageFormat);
			
			if(result) {
				System.out.println("Image converted sucessfully!");
			}else {
				System.out.println("Error: fail to convert image!");
			}
		}catch(IOException e) {
			System.out.println("Error during converting image");
			e.printStackTrace();
		}
	}

}
