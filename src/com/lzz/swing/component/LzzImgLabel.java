package com.lzz.swing.component;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.lzz.swing.util.LzzImageUtil;

public class LzzImgLabel extends JLabel {
	public LzzImgLabel(String img_url){
		ImageIcon icon = new ImageIcon(LzzImageUtil.getResource(img_url));
		setIcon(icon);
	}
	
	public LzzImgLabel(String img_url, String text){
		ImageIcon icon = new ImageIcon(LzzImageUtil.getResource(img_url));
		setIcon(icon);
		setText(text);
	}
	
	public LzzImgLabel(String img_url, int wid, int hei){
		//ImageIcon icon = new ImageIcon(img_url);
		ImageIcon icon = new ImageIcon(LzzImageUtil.getResource(img_url));
		icon.setImage(icon.getImage().getScaledInstance(wid, hei,  
                Image.SCALE_DEFAULT));
		setIcon(icon);
	}
	
	public LzzImgLabel(ImageIcon icon, int wid, int hei){
		//ImageIcon icon = new ImageIcon(img_url);
		icon.setImage(icon.getImage().getScaledInstance(wid, hei,  
				Image.SCALE_DEFAULT));
		setIcon(icon);
	}
	
	public LzzImgLabel(String img_url, int wid, int hei, String text){
		//ImageIcon icon = new ImageIcon(img_url);
		ImageIcon icon = new ImageIcon(LzzImageUtil.getResource(img_url));
		icon.setImage(icon.getImage().getScaledInstance(wid, hei,  
				Image.SCALE_DEFAULT));
		setIcon(icon);
		setText(text);
	}
	
	public LzzImgLabel(String text, int wid, int hei, Color color){
		setSize(wid, hei);
		setText(text);
		setOpaque(true);
		setBackground(color);
	}
	
	public LzzImgLabel(ImageIcon icon){
		setIcon(icon);
	}
	
	public void changeIcon(String url){
		ImageIcon icon = new ImageIcon(LzzImageUtil.getResource(url));
		icon.setImage(icon.getImage().getScaledInstance(
				getIcon().getIconWidth(), 
				getIcon().getIconHeight(),  
                Image.SCALE_DEFAULT));  
		setIcon(icon);
	}
}
