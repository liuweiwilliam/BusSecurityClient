package com.lzz.swing.view;

import java.awt.Toolkit;

import javax.swing.JFrame;

import com.lzz.swing.mgr.LzzImgMgr;
import com.lzz.swing.util.LzzImageUtil;

public class LzzBaseStyleFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2499293108685401843L;
	
	public LzzBaseStyleFrame(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(LzzImageUtil.getResource(LzzImgMgr.img_url_logiIcon)));
	}
}
