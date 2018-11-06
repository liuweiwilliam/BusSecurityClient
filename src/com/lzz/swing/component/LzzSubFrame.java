package com.lzz.swing.component;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class LzzSubFrame extends JInternalFrame {
	private static String smLoadingImgUrl = "/images/alarm.png";
	
	public LzzSubFrame createLoadingFrame(){
		LzzImgLabel loading_img = new LzzImgLabel(smLoadingImgUrl, 400, 400);
		add(loading_img);
		//设置边框隐藏
		setBorder(BorderFactory.createEmptyBorder());
		//隐藏标题栏
		((BasicInternalFrameUI)getUI()).setNorthPane(null);
		return this;
	}
}
