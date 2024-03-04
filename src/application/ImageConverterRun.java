package application;

import java.awt.EventQueue;

import javax.swing.JFrame;

import model.entities.ImageConverterGUI;

public class ImageConverterRun extends JFrame {

	public ImageConverterRun() {
		super("Image Converter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new ImageConverterGUI());
		pack();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ImageConverterRun().setVisible(true);
			}
		});
	}
}
