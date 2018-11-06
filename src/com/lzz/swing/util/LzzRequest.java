package com.lzz.swing.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lzz.swing.mgr.LzzUserMgr;

import net.sf.json.JSONObject;

public class LzzRequest {
	public static String sendByPost(String url, List<String> par_name, List<String> par_value, String contents){
		String response = "";
		
		BufferedReader in = null;
		
		String complete_url = url;
		if(null==par_name) par_name = new ArrayList<String>();
		if(null==par_value) par_value = new ArrayList<String>();
		
		//par_name.add("sessionId");
		//par_value.add(LzzUserMgr.userSessionId);
		
		for(int i=0; i<par_name.size(); i++){
			if(i==0){
				complete_url += "?";
			}
			else{
				complete_url += "&";
			}
			complete_url += par_name.get(i);
			complete_url += "=";
			complete_url += par_value.get(i);
		}
		
		try{
			JSONObject json_obj = JSONObject.fromObject(contents);
            System.out.println(json_obj.toString());
            byte[] requestStringBytes = json_obj.toString().getBytes("utf-8");
            
			URL realUrl = new URL(complete_url);

			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			
			connection.setDoOutput(true); 
            connection.setDoInput(true);
			connection.setRequestMethod("POST"); 
			connection.setUseCaches(false); 
			connection.setRequestProperty("Content-length", "" + requestStringBytes.length);
	        connection.setRequestProperty("Content-Type", 
	                   "application/json;encoding=utf-8"); 
	        connection.setRequestProperty("Charset", "utf-8");
	        
	        connection.setRequestProperty("sessionId", LzzUserMgr.userSessionId);
	        
	        connection.connect();
	        
	        DataOutputStream out = new DataOutputStream(connection.getOutputStream()); 

            out.write(requestStringBytes);
            out.flush(); 
            out.close(); 
            
	        Map<String, List<String>> map = connection.getHeaderFields();
/*	        for (String key : map.keySet()){
	            System.out.println(key + "--->" + map.get(key));
	        }
*/	        
	        in = new BufferedReader(new InputStreamReader(
	                connection.getInputStream()));
	
	        String line;
	        while ((line = in.readLine()) != null) 
	        {
	            response += line;
	        }
	        System.out.println("post finish, response is : " + response);
		}catch(Exception e){
			System.out.println("post请求失败" + e);
            e.printStackTrace();
		}finally{
			try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
		}
		
		return response;
	}
	
	public static String sendByGet(String url, List<String> par_name, List<String> par_value){
		String response = "";
		
		BufferedReader in = null;
		
		String complete_url = url;
		if(null==par_name) par_name = new ArrayList<String>();
		if(null==par_value) par_value = new ArrayList<String>();
		
		//par_name.add("sessionId");
		//par_value.add(LzzUserMgr.userSessionId);
		
		for(int i=0; i<par_name.size(); i++){
			if(i==0){
				complete_url += "?";
			}
			else{
				complete_url += "&";
			}
			complete_url += par_name.get(i);
			complete_url += "=";
			complete_url += par_value.get(i);
		}
		
		System.out.println("complete_url:" + complete_url);
		try{
			URL realUrl = new URL(complete_url);
			URLConnection connection = realUrl.openConnection();
			
			connection.setRequestProperty("accept", "*/*");
	        connection.setRequestProperty("connection", "Keep-Alive");
	        connection.setRequestProperty("user-agent",
	                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	        connection.setRequestProperty("sessionId", LzzUserMgr.userSessionId);
	        // 建立实际的链接
	        connection.connect();
	        
	        // 获取响应头字段
	        Map<String, List<String>> map = connection.getHeaderFields();
	        for (String key : map.keySet()) {
	            System.out.println(key + "--->" + map.get(key));
	        }
	        
	        // 定义 BufferedReader输入流来读取URL的响应?
	        in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	
	        String line;
	        while ((line = in.readLine()) != null) 
	        {
	            response += line;
	        }
		}catch(Exception e){
			System.out.println("发送get请求异常" + e);
			response = "ERROR";
            e.printStackTrace();
		}finally{
			try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
		}
		
		return response;
	}
	
	public static String sendByGet(String url){
		return sendByGet(url, null, null);
	}
}
