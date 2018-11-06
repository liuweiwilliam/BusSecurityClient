package com.lzz.swing.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lzz.swing.util.LzzImageUtil;

public class LzzLabelAndInput extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5153220021719120659L;
	
	private JLabel label = null;
	private JTextField textField = null;

	public LzzLabelAndInput(
			String label_text, 
			Color label_text_color, 
			int label_font_size, 
			String label_icon_url,
			int width,
			int height){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setMaximumSize(new Dimension(width, height));
		setOpaque(false);
		label = new JLabel(label_text);
		label.setForeground(label_text_color);
		label.setFont(new Font("宋体", Font.BOLD, label_font_size));
		label.setIcon(new ImageIcon(LzzImageUtil.getResource(label_icon_url)));
		textField = new JTextField();
		add(label);
		add(Box.createHorizontalStrut(20));
		add(textField);
	}
	
	public String getText(){
		if(null==textField) return null;
		return textField.getText();
	}
	
	
}
