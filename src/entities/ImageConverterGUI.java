package entities;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ImageConverterGUI extends JPanel{
	
	private GridBagLayout layout;
	
	private JButton openImage = new JButton("Open Image");
	private JButton convertClick = new JButton("Convert");
	
	private JTextField imagePath = new JTextField();
	
	private ImageIcon icon;
	private JLabel imagelbl;
	
	private JScrollPane scrollPane;

	public ImageConverterGUI() {
		run();
	}
	
	public void run() {
		layout = new GridBagLayout();
		setLayout(layout);
		
		setPreferredSize(new Dimension(400, 400));
		
		icon = new ImageIcon("");
		imagelbl = new JLabel();
		scrollPane = new JScrollPane(imagelbl);
		
		GridBagConstraints scrollConst = new GridBagConstraints();
		scrollConst.gridx = GridBagConstraints.RELATIVE;
		scrollConst.gridy = GridBagConstraints.RELATIVE;
		scrollConst.fill = GridBagConstraints.VERTICAL;
		scrollConst.fill = GridBagConstraints.HORIZONTAL;
		scrollConst.weighty = 2.0;
		scrollConst.gridwidth = 3;
		scrollConst.insets = new Insets(10, 10, 10, 10);
		scrollPane.setPreferredSize(new Dimension(400, 470));
		add(scrollPane, scrollConst);
		
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.gridx = 0;
		gbc1.gridy = 1;
		gbc1.insets = new Insets(0, 10, 5, 10);
		gbc1.anchor = GridBagConstraints.WEST;
		add(openImage, gbc1);
		
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.gridx = 1;
		gbc2.gridy = 1;
		gbc2.weightx = 1.0;
		gbc2.weighty = 1.0;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbc2.insets = new Insets(0, 5, 5, 10);
		add(imagePath, gbc2);
		
		//Insets(int top, int left, int bottom, int right)
		
		GridBagConstraints gbc3 = new GridBagConstraints();
		gbc3.gridx = 0;
		gbc3.gridy = 2;
		gbc3.anchor = GridBagConstraints.WEST;
		gbc3.insets = new Insets(5, 10, 30, 10);
		add(convertClick, gbc3);
		
	}

}
