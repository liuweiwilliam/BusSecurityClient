package com.lzz.swing.util;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class LzzFontUtil {
	/**
	 * 设置窗口默认字体
	 */
	public static void setDefaultFont() {
		// TODO Auto-generated method stub
		Font font = new Font("Dialog", Font.PLAIN, 12);
		Enumeration keys = UIManager.getDefaults().keys();
		while(keys.hasMoreElements()){
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof FontUIResource){
				UIManager.put(key, font);
			}
		}
	}
}
