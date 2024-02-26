package entities;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ImageConverterGUI extends JPanel{
	
	private GridBagLayout layout;
	
	private JButton openImage = new JButton("Open Image");
	private JButton convertClick = new JButton("Convert");
	
	private JTextField imagePath = new JTextField();
	
	private JLabel imagelbl = new JLabel();
	
	private JScrollPane scrollPane = new JScrollPane();
	
	private JFileChooser chooseFile = new JFileChooser();
	
	private JPanel panel = new JPanel();

	public ImageConverterGUI() {
		run();
	}
	
	public void run() {
		layout = new GridBagLayout();
		setLayout(layout);
		panel.setLayout(layout);
		
		setPreferredSize(new Dimension(400, 400));
		
		openImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// execute action to open the image
				chooseFile.showSaveDialog(null);
				if(chooseFile.getSelectedFile() != null) {
					String absolutePath = chooseFile.getSelectedFile().getAbsolutePath(); 
					imagePath.setText(absolutePath);
					imagelbl.setIcon(new ImageIcon(absolutePath));
					panel.add(imagelbl);
					scrollPane.setViewportView(panel);
				}
			}
		});;
		
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
		imagePath.setEditable(false);
		add(imagePath, gbc2);
		
		//Insets(int top, int left, int bottom, int right)
		
		GridBagConstraints gbc3 = new GridBagConstraints();
		gbc3.gridx = 0;
		gbc3.gridy = 2;
		gbc3.anchor = GridBagConstraints.WEST;
		gbc3.insets = new Insets(5, 10, 30, 10);
		add(convertClick, gbc3);
		
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
		
	}

}
