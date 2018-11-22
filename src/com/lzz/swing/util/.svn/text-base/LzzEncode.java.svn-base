package com.lzz.swing.util;

import java.security.MessageDigest;

public class LzzEncode {
	public static String key = "xhfw_carsecurity";
	
	/**
	 * 对称加密，使用密钥key对content进行加密
	 * @param content 加密内容
	 * @param key 加密密钥
	 * @return 加密后的内容
	 */
	public static String encryption(String content){
        byte[] contentBytes = content.getBytes();
        byte[] keyBytes = key.getBytes();
 
        byte dkey = 0;
        for(byte b : keyBytes){
            dkey ^= b;
        }
 
        byte salt = 0;  //随机盐值
        byte[] result = new byte[contentBytes.length];
        for(int i = 0 ; i < contentBytes.length; i++){
            salt = (byte)(contentBytes[i] ^ dkey ^ salt);
            result[i] = salt;
        }
        return new String(result);
    }
	
	/**
	 * 对称加密解密
	 * @param content 需解密内容
	 * @param key 解密密钥
	 * @return 解密后结果
	 */
	public static String decipher(String content){
        byte[] contentBytes = content.getBytes();
        byte[] keyBytes = key.getBytes();
 
        byte dkey = 0;
        for(byte b : keyBytes){
            dkey ^= b;
        }
 
        byte salt = 0;  //随机盐值
        byte[] result = new byte[contentBytes.length];
        for(int i = contentBytes.length - 1 ; i >= 0 ; i--){
            if(i == 0){
                salt = 0;
            }else{
                salt = contentBytes[i - 1];
            }
            result[i] = (byte)(contentBytes[i] ^ dkey ^ salt);
        }
        return new String(result);
    }
	
	/**
	 * MD5加密
	 * @param value 需加密的值
	 * @return 密文
	 */
	public static String MD5(String value){
		char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
                'A', 'B', 'C', 'D', 'E', 'F' }; 
		try{
			if(value==null){
				return null;
			}
			byte[] inputString = value.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(inputString);
			
			byte[] result = md.digest();
			int j = result.length;
			char[] str = new char[j*2];
			
            int k = 0;  
            for (int i = 0; i < j; i++) {   //  i = 0  
                byte byte0 = result[i];  //95  
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5   
                str[k++] = md5String[byte0 & 0xf];   //   F  
            }  
			
			return new String(str);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
