package com.lzz.swing.component;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.lzz.swing.util.LzzImageUtil;

public class LzzImageIcon extends ImageIcon {
	public LzzImageIcon(String img_url, int wid, int hei){
		super(LzzImageUtil.getResource(img_url));
		setImage(getImage().getScaledInstance(wid, hei,  
				Image.SCALE_DEFAULT));
	}
	
	public LzzImageIcon(String img_url){
		super(LzzImageUtil.getResource(img_url));
	}
}
