package entities;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
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

	private JButton openImageBtn = new JButton("Open Image");
	private JButton convertClickBtn = new JButton("Convert");

	private JTextField imagePathTxt = new JTextField();
	private JTextField originalImageFormatTxt = new JTextField(5);

	private JLabel imageLbl = new JLabel();
	private JLabel originalFormatLbl = new JLabel("Original Format:");
	private JLabel convertToLbl = new JLabel("Convert to:");

	private JScrollPane scrollPane = new JScrollPane();

	private JFileChooser chooseFile = new JFileChooser();

	private JPanel panel = new JPanel();

	private JComboBox imageFormats = new JComboBox();

	public ImageConverterGUI() {
		run();
	}

	public void run() {
		layout = new GridBagLayout();
		setLayout(layout);
		panel.setLayout(layout);

		setPreferredSize(new Dimension(400, 400));

		convertClickBtn.setEnabled(false);

		openImageBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseFile.showSaveDialog(null);
				if (chooseFile.getSelectedFile() != null) {
					String absolutePath = chooseFile.getSelectedFile().getAbsolutePath();
					imagePathTxt.setText(absolutePath);
					imageLbl.setIcon(new ImageIcon(absolutePath));
					panel.add(imageLbl);
					scrollPane.setViewportView(panel);
					originalImageFormatTxt.setText(getImageFormat(absolutePath));
					convertClickBtn.setEnabled(true);
				}
			}
		});
		;

		GridBagConstraints scrollConst = new GridBagConstraints();
		scrollConst.gridx = 0;
		scrollConst.gridy = 0;
		scrollConst.gridwidth = 4;
		scrollConst.gridheight = 1;
		scrollConst.weightx = 1.0;
		scrollConst.fill = GridBagConstraints.BOTH;
		scrollPane.setPreferredSize(new Dimension(400, 470));
		scrollConst.insets = new Insets(10, 10, 10, 10);
		add(scrollPane, scrollConst);

		GridBagConstraints gbc4 = new GridBagConstraints();
		gbc4.gridx = 0;
		gbc4.gridy = 1;
		gbc4.gridwidth = 1;
		gbc4.anchor = GridBagConstraints.WEST;
		gbc4.insets = new Insets(10, 10, 10, 10);
		add(originalFormatLbl, gbc4);

		GridBagConstraints originalImageFormatConstraints = new GridBagConstraints();
		originalImageFormatConstraints.gridx = 1;
		originalImageFormatConstraints.gridy = 1;
		originalImageFormatConstraints.anchor = GridBagConstraints.WEST;
		originalImageFormatConstraints.gridwidth = 1;
		originalImageFormatConstraints.insets = new Insets(10, 10, 10, 10);
		originalImageFormatTxt.setEditable(false);
		add(originalImageFormatTxt, originalImageFormatConstraints);

		GridBagConstraints convertToConstraints = new GridBagConstraints();
		convertToConstraints.gridx = 2;
		convertToConstraints.gridy = 1;
		convertToConstraints.anchor = GridBagConstraints.WEST;
		convertToConstraints.gridwidth = 1;
		convertToConstraints.insets = new Insets(10, 10, 10, 10);
		add(convertToLbl, convertToConstraints);

		GridBagConstraints imageFormatsConstraints = new GridBagConstraints();
		imageFormatsConstraints.gridx = 3;
		imageFormatsConstraints.gridy = 1;
		imageFormatsConstraints.anchor = GridBagConstraints.WEST;
		imageFormatsConstraints.gridwidth = 1;
		imageFormatsConstraints.insets = new Insets(10, 10, 10, 10);
		add(imageFormats, imageFormatsConstraints);

		GridBagConstraints openImageBtnConstraints = new GridBagConstraints();
		openImageBtnConstraints.gridx = 0;
		openImageBtnConstraints.gridy = 2;
		openImageBtnConstraints.anchor = GridBagConstraints.WEST;
		openImageBtnConstraints.gridwidth = 1;
		openImageBtnConstraints.insets = new Insets(10, 10, 10, 10);
		add(openImageBtn, openImageBtnConstraints);

		GridBagConstraints imagePathTxtConstraints = new GridBagConstraints();
		imagePathTxtConstraints.gridx = 1;
		imagePathTxtConstraints.gridy = 2;
		imagePathTxtConstraints.gridwidth = GridBagConstraints.REMAINDER;
		imagePathTxtConstraints.fill = GridBagConstraints.BOTH;
		imagePathTxtConstraints.insets = new Insets(10, 10, 10, 10);
		imagePathTxt.setEditable(false);
		add(imagePathTxt, imagePathTxtConstraints);

		// Insets(int top, int left, int bottom, int right)

		GridBagConstraints convertClickBtnConstraints = new GridBagConstraints();
		convertClickBtnConstraints.gridx = 0;
		convertClickBtnConstraints.gridy = 3;
		convertClickBtnConstraints.anchor = GridBagConstraints.WEST;
		convertClickBtnConstraints.insets = new Insets(10, 10, 10, 10);
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
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
