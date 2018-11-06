package com.lzz.swing.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LzzOprDataFromFile {
	
	private static String parFileDirPath = System.getProperty("user.dir")+File.separator+"conf"+File.separator;
	private static String parFilePath = parFileDirPath + "record.ini";
	
	/**
	 * 从文件中读取变量值
	 * @param name 变量名
	 * @return
	 */
	public static String readParDataFromFile(String name){
	    File dir = new File(parFileDirPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		File file = new File(parFilePath);
		if(!file.exists()){
			return null;
		}
		
		String rslt = getParValueFromFile(file, name);
		
		return rslt;
	}
	
	/**
	 * 从文件中获取键值
	 * @param file
	 * @param name
	 * @return
	 */
	private static String getParValueFromFile(File file, String name) {
		// TODO Auto-generated method stub
		String contents = readFileContents(file);
		String start_str = name + "=";
		int index1 = contents.indexOf(name + "=");
		if(-1==index1) return null;
		
		int index2 = contents.indexOf("\n", index1);
		if(-1==index2) return null;
		
		return contents.substring(index1+start_str.length(), index2);
	}

	/**
	 * 从文件中写键值对
	 * @param name 变量名
	 * @param value 变量值
	 * @return
	 */
	public static synchronized boolean writeParDataToFile(String name, String value){
		boolean rslt = true;
		
		File dir = new File(parFileDirPath);
    	if(!dir.exists()){
    		dir.mkdirs();
    	}
    	
    	File file = new File(parFilePath);
    	if(!file.exists()){
    		try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
    	}
    	
    	String contents = readFileContents(file);
    	if(null==contents) return false;
    	
    	String orig_val = readParDataFromFile(name);
    	
    	if(null == orig_val){
    		//直接添加
    		appendFileContents(file, name + "=" + value + "\n");
    	}else{
    		//覆盖更新
    		contents = contents.replaceAll(name + "=" + orig_val, name + "=" + value);
    		writeFileContents(file, contents);
    	}
		
		return rslt;
	}
	
	/**
	 * 读取文件内容
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static String readFileContents(File file){
		try {
			FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
	        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
	        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
	        String s = "";
	        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
	            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
	            System.out.println(s);
	        }
			bReader.close();
			String str = sb.toString();
			return str;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 覆盖写文件内容
	 * @param file
	 * @param contents
	 * @return
	 */
	private static boolean writeFileContents(File file, String contents){
		try {
	         BufferedWriter out = new BufferedWriter(new FileWriter(file));
	         out.write(contents);
	         out.close();
	         return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	/**
	 * 追加写文件内容
	 * @param file
	 * @param contents
	 * @return
	 */
	private static boolean appendFileContents(File file, String contents){
		try {
	         BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
	         out.write(contents);
	         out.close();
	         return true;
		} catch (IOException e) {
			return false;
		}
	}
}
