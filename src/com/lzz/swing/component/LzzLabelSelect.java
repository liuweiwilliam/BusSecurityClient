package com.lzz.swing.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lzz.swing.util.LzzImageUtil;

public class LzzLabelSelect extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5153220021719120659L;
	
	private JLabel label = null;
	private JComboBox comboBox = null;

	public LzzLabelSelect(
			String label_text, 
			Color label_text_color, 
			int label_font_size, 
			String label_icon_url,
			String[] items,
			int width,
			int height){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setMaximumSize(new Dimension(width, height));
		setOpaque(false);
		label = new JLabel(label_text);
		label.setForeground(label_text_color);
		label.setFont(new Font("宋体", Font.BOLD, label_font_size));
		label.setIcon(new ImageIcon(LzzImageUtil.getResource(label_icon_url)));
		comboBox = new JComboBox();
		for(int i=0; i<items.length; i++){
			comboBox.addItem(items[i]);
		}
		add(label);
		add(Box.createHorizontalStrut(20));
		add(comboBox);
	}
	public LzzLabelSelect(
			String label_text, 
			Color label_text_color, 
			int label_font_size,
			String[] items,
			int width,
			int height){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setMaximumSize(new Dimension(width, height));
		setOpaque(false);
		label = new JLabel(label_text);
		label.setForeground(label_text_color);
		label.setFont(new Font("宋体", Font.BOLD, label_font_size));
		comboBox = new JComboBox();
		for(int i=0; i<items.length; i++){
			comboBox.addItem(items[i]);
		}
		add(label);
		add(Box.createHorizontalStrut(20));
		add(comboBox);
	}
	
	public String getSelectedItem(){
		if(null==comboBox) return null;
		return (String) comboBox.getSelectedItem();
	}
	
	public void setSelectedItem(String text){
		if(null==comboBox) return;
		comboBox.setSelectedItem(text);
	}
}
