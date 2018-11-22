package com.lzz.swing.component;

import java.awt.Color;

import javax.swing.tree.DefaultTreeCellRenderer;

import com.lzz.swing.mgr.LzzColorFontMgr;

public class LzzTreeCellRenderer extends DefaultTreeCellRenderer {
	private String treeCloseIcon = "/images/closeImg.png";
	private String treeOpenIcon = "/images/treeOpen.png";
	private String treeRootIcon = "/images/closeImg.png";
	private String treeLeafIcon = "/images/treeOpen.png";
	
	public LzzTreeCellRenderer(){
		setBackgroundNonSelectionColor(LzzColorFontMgr.smUIBodyBkColor);
		setBackgroundSelectionColor(LzzColorFontMgr.smUIBodyBkColor);
		setTextNonSelectionColor(Color.BLACK);
		setTextSelectionColor(Color.WHITE);
		setFont(LzzColorFontMgr.smTreeTextFont);
		setForeground(LzzColorFontMgr.smTextColor);
		setLeafIcon(new LzzImageIcon(treeLeafIcon, 20, 20));
		setOpenIcon(new LzzImageIcon(treeOpenIcon, 20, 20));
		setClosedIcon(new LzzImageIcon(treeCloseIcon, 20, 20));
		setIcon(new LzzImageIcon(treeRootIcon, 20, 20));
	}
}
