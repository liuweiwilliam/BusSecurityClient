package com.lzz.swing.mgr;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lzz.swing.util.LzzProperties;
import com.lzz.swing.util.LzzRequest;

public class LzzUserMgr {
	public static String userSessionId = "";
	public static String userName = "管理员";
	public static String roleName = "";
	
	/**
	 * 用户登录
	 * @param uname 用户名
	 * @param pwd 密码
	 * @return true表示登录成功，false登录失败
	 */
	public static boolean login(String uname, String pwd){
		boolean rslt = false;
		String login_url = LzzProperties.getDataRequestUrl() + "/" + "login.action";
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
				userName = rslt_obj.getString("lastName");
				roleName = rslt_obj.getString("roleName");
				rslt = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			rslt = false;
		}
		return rslt;
	}
	
	/**
	 * 更新用户信息
	 * @param info
	 * @return
	 */
	public static JSONObject addOrUpdateUser(JSONObject info){
		return LzzDataRequestMgr.addOrUpdateUser(info);
	}
	
	/**
	 * 获取用户信息
	 * @param userid
	 * @return
	 */
	public static JSONObject getUserInfo(String userid){
		return LzzDataRequestMgr.getUserInfo(userid);
	}
	
	/**
	 * 获取用户所有权限名称
	 * @return
	 */
	public static JSONArray getUserAuthoritys(){
		JSONObject rslt = LzzDataCache.getUserAuthoritys();
		return rslt.getJSONArray("data");
	}
	
	/**
	 * 判单用户是否有某权限
	 * @param authority_name
	 * @return
	 */
	public static boolean userHasAuthority(String authority_name){
		return getUserAuthoritys().contains(authority_name);
	}
	
	/**
	 * 删除用户
	 * @param userid
	 * @return
	 */
	public static JSONObject delUser(String userid){
		return LzzDataRequestMgr.delUser(userid);
	}
	
	/**
	 * 获取用户管理列表
	 * @param page_info
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject getUserList(JSONObject page_info, String search_key){
		if(null==page_info){
			page_info = new JSONObject();
			page_info.put("curPage", 1);
			page_info.put("pageSize", 20);
		}
		
		return LzzDataCache.getUserList(page_info, search_key);
	}
	
	/**
	 * 获取角色列表
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONArray getRoleList(){
		return LzzDataCache.getRoleList();
	}
}
