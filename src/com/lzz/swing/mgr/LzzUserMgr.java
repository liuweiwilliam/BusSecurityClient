package com.lzz.swing.mgr;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.lzz.swing.util.LzzProperties;
import com.lzz.swing.util.LzzRequest;

public class LzzUserMgr {
	public static String userSessionId = "";
	public static String userName = "管理员";
	
	/**
	 * 用户登录
	 * @param uname 用户名
	 * @param pwd 密码
	 * @return true表示登录成功，false登录失败
	 */
	public static boolean login(String uname, String pwd){
		boolean rslt = false;
		String login_url = LzzProperties.getServerUrl() + "/" + "login.action";
		List<String> par_name = new ArrayList<String>();
		List<String> par_val = new ArrayList<String>();
		par_name.add("uname");
		par_name.add("pwd");
		par_val.add(uname);
		par_val.add(pwd);
		String response = LzzRequest.sendByGet(login_url, par_name, par_val);
		try {
			JSONObject rslt_obj = JSONObject.fromObject(response);
			if(rslt_obj.containsKey("success")
					&& true==rslt_obj.getBoolean("success")){
				userSessionId = rslt_obj.getString("sessionId");
				//userName = rslt_obj.getString("userName");
				rslt = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			rslt = false;
		}
		return rslt;
	}
}
