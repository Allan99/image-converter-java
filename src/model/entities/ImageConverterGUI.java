package model.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ImageConverterGUI extends JPanel {

	private GridBagLayout layout;

	private JButton openImageBtn = new JButton("Open Image", new ImageIcon("icons/abrir-pasta.png"));
	private JButton convertClickBtn = new JButton("Convert", new ImageIcon("icons/actualizar.png"));
	private JButton saveInBtn = new JButton("Save in", new ImageIcon("icons/salvar.png"));

	private JTextField imagePathTxt = new JTextField();
	private JTextField originalImageFormatTxt = new JTextField(7);
	private JTextField saveInTxt = new JTextField();

	private JLabel imageLbl = new JLabel();
	private JLabel originalFormatLbl = new JLabel("Original Format:");
	private JLabel convertToLbl = new JLabel("Convert to:");

	private JScrollPane scrollPane = new JScrollPane();

	private JFileChooser chooseFile = new JFileChooser();

	private JPanel panel = new JPanel();

	private JComboBox imageFormats;

	public ImageConverterGUI() {
		run();
	}

	public void run() {
		layout = new GridBagLayout();
		setLayout(layout);
		panel.setLayout(layout);

		setPreferredSize(new Dimension(400, 400));

		String[] formats = { "JPEG", "PNG", "GIF", "TIFF", "BMP", "PDF" };

		imageFormats = new JComboBox(formats);

		convertClickBtn.setEnabled(false);
		
		openImageBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int res = chooseFile.showOpenDialog(null);
				if (res == JFileChooser.APPROVE_OPTION) {
					String absolutePath = chooseFile.getSelectedFile().getAbsolutePath();
					String exhibitParent = chooseFile.getSelectedFile().getParent();
					imagePathTxt.setText(absolutePath);
					if(saveInTxt.getText().length() == 0) {
						saveInTxt.setText(exhibitParent);
					}
					imageLbl.setIcon(new ImageIcon(absolutePath));
					panel.add(imageLbl);
					scrollPane.setViewportView(panel);
					originalImageFormatTxt.setText(getImageFormat(absolutePath));
					convertClickBtn.setEnabled(true);
				}
			}
		});

		convertClickBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					
					public void run() {
						File f = new File(imagePathTxt.getText());//imagem original
						String format = imageFormats.getSelectedItem().toString().toLowerCase();//formato final
						String name = f.getName().replaceAll("\\.[a-z]{2,}", "."+format);//alterando o formato da imagem
						String saveIn = saveInTxt.getText() + "\\" + name;
						
						try {
							ImageConverter.convertFormat(f.getAbsolutePath(), saveIn, format.toUpperCase());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					};
					
				}.start();
			}

		});
		
		saveInBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int res = chooseFile.showOpenDialog(null);
				if(res == JFileChooser.APPROVE_OPTION) {
					saveInTxt.setText(chooseFile.getSelectedFile().getAbsolutePath());
				}
			}
		});

		GridBagConstraints scrollConst = new GridBagConstraints();
		scrollConst.gridx = 0;
		scrollConst.gridy = 0;
		scrollConst.gridwidth = 4;
		scrollConst.gridheight = 1;
		scrollConst.weightx = 1.0;
		scrollConst.weighty = 1.0;
		scrollConst.fill = GridBagConstraints.BOTH;
		scrollPane.setPreferredSize(new Dimension(400, 470));
		scrollConst.insets = new Insets(10, 10, 10, 10);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		add(scrollPane, scrollConst);

		GridBagConstraints gbc4 = new GridBagConstraints();
		gbc4.gridx = 0;
		gbc4.gridy = 1;
		gbc4.gridwidth = 1;
		gbc4.anchor = GridBagConstraints.WEST;
		gbc4.insets = new Insets(10, 10, 10, 0);
		originalFormatLbl.setFont(new Font("Helvetica", Font.BOLD, 15));
		add(originalFormatLbl, gbc4);

		GridBagConstraints originalImageFormatConstraints = new GridBagConstraints();
		originalImageFormatConstraints.gridx = 1;
		originalImageFormatConstraints.gridy = 1;
		originalImageFormatConstraints.anchor = GridBagConstraints.WEST;
		originalImageFormatConstraints.gridwidth = 1;
		originalImageFormatConstraints.ipadx = 57;
		originalImageFormatConstraints.fill = GridBagConstraints.HORIZONTAL;
		originalImageFormatConstraints.insets = new Insets(10, 10, 10, 10);
		originalImageFormatTxt.setFont(new Font("Helvetica", Font.BOLD, 15));
		originalImageFormatTxt.setHorizontalAlignment(JTextField.CENTER);
		originalImageFormatTxt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		originalImageFormatTxt.setEditable(false);
		add(originalImageFormatTxt, originalImageFormatConstraints);

		GridBagConstraints convertToConstraints = new GridBagConstraints();
		convertToConstraints.gridx = 2;
		convertToConstraints.gridy = 1;
		convertToConstraints.anchor = GridBagConstraints.WEST;
		convertToConstraints.gridwidth = 1;
		convertToConstraints.insets = new Insets(10, 3, 10, 3);
		convertToLbl.setFont(new Font("Helvetica", Font.BOLD, 15));
		add(convertToLbl, convertToConstraints);

		GridBagConstraints imageFormatsConstraints = new GridBagConstraints();
		imageFormatsConstraints.gridx = 3;
		imageFormatsConstraints.gridy = 1;
		imageFormatsConstraints.anchor = GridBagConstraints.WEST;
		imageFormatsConstraints.gridwidth = 1;
		imageFormatsConstraints.insets = new Insets(10, 3, 10, 3);
		imageFormats.setFont(new Font("Helvetica", Font.BOLD, 15));
		imageFormats.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		add(imageFormats, imageFormatsConstraints);

		GridBagConstraints openImageBtnConstraints = new GridBagConstraints();
		openImageBtnConstraints.gridx = 0;
		openImageBtnConstraints.gridy = 2;
		openImageBtnConstraints.anchor = GridBagConstraints.WEST;
		openImageBtnConstraints.gridwidth = 1;
		openImageBtnConstraints.insets = new Insets(10, 10, 10, 10);
		openImageBtn.setFont(new Font("Helvetica", Font.BOLD, 15));
		add(openImageBtn, openImageBtnConstraints);

		GridBagConstraints imagePathTxtConstraints = new GridBagConstraints();
		imagePathTxtConstraints.gridx = 1;
		imagePathTxtConstraints.gridy = 2;
		imagePathTxtConstraints.gridwidth = GridBagConstraints.REMAINDER;
		imagePathTxtConstraints.fill = GridBagConstraints.BOTH;
		imagePathTxtConstraints.insets = new Insets(10, 10, 10, 10);
		imagePathTxt.setFont(new Font("Helvetica", Font.BOLD, 15));
		imagePathTxt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		imagePathTxt.setEditable(false);
		add(imagePathTxt, imagePathTxtConstraints);

		GridBagConstraints saveInBtnConstraints = new GridBagConstraints();
		saveInBtnConstraints.gridx = 0;
		saveInBtnConstraints.gridy = 3;
		saveInBtnConstraints.anchor = GridBagConstraints.WEST;
		saveInBtnConstraints.insets = new Insets(10, 10, 10, 10);
		saveInBtn.setFont(new Font("Helvetica", Font.BOLD, 15));
		add(saveInBtn, saveInBtnConstraints);

		GridBagConstraints saveInTxtConstraints = new GridBagConstraints();
		saveInTxtConstraints.gridx = 1;
		saveInTxtConstraints.gridy = 3;
		saveInTxtConstraints.gridwidth = GridBagConstraints.REMAINDER;
		saveInTxtConstraints.fill = GridBagConstraints.BOTH;
		saveInTxtConstraints.insets = new Insets(10, 10, 10, 10);
		saveInTxt.setFont(new Font("Helvetica", Font.BOLD, 15));
		saveInTxt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		saveInTxt.setEditable(false);
		add(saveInTxt, saveInTxtConstraints);

		GridBagConstraints convertClickBtnConstraints = new GridBagConstraints();
		convertClickBtnConstraints.gridx = 0;
		convertClickBtnConstraints.gridy = 4;
		convertClickBtnConstraints.anchor = GridBagConstraints.WEST;
		convertClickBtnConstraints.insets = new Insets(10, 10, 10, 10);
		convertClickBtn.setFont(new Font("Helvetica", Font.BOLD, 15));
		add(convertClickBtn, convertClickBtnConstraints);
	}

	private String getImageFormat(String imagePath) {
		try {
			ImageInputStream inputStream = ImageIO.createImageInputStream(new File(imagePath));
			Iterator<ImageReader> iter = ImageIO.getImageReaders(inputStream);
			if (!iter.hasNext()) {
				throw new RuntimeException("No readers found!");
			}
			ImageReader reader = iter.next();
			inputStream.close();
			return reader.getFormatName();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
