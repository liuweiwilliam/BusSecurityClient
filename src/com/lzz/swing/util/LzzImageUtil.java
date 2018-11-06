package com.lzz.swing.util;

import java.net.URL;

public class LzzImageUtil {
	public static URL getResource(String path){
		return LzzImageUtil.class.getResource(path);
	}
}
