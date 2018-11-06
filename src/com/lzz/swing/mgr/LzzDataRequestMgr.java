package com.lzz.swing.mgr;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import com.lzz.swing.model.LzzSensorData;
import com.lzz.swing.util.LzzProperties;
import com.lzz.swing.util.LzzRequest;
import com.lzz.swing.view.LzzTreeBusNode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LzzDataRequestMgr {
	/**
	 * 数据请求URL
	 */
	private static String dataRequestUrl = LzzProperties.getDataRequestUrl();
	
	/**
	 * 验证url有效性请求名称
	 */
	private static String urlCheck = "urlCheck.action";
	
	/**
	 * 获取用户所属公司
	 */
	private static String urlGetUserCompany = "getUserCompany.action";
	
	/**
	 * 获取用户管理公交请求名称
	 */
	private static String urlGetUserMgrBusList = "getUserMgrBusList.action";
	
	/**
	 * 获取日志列表请求名称
	 */
	private static String urlGetSensorDataList = "getLogList.action";
	
	/**
	 * 获取报警车辆请求名称
	 */
	private static String urlGetAlarmedBuses = "getAlarmedBuses.action";
	
	/**
	 * 获取车辆报警信息请求名称
	 */
	private static String urlGetBusAlarmMsg = "getBusAlarmMsg.action";
	
	/**
	 * 获取报警日志列表请求名称
	 */
	private static String urlGetAlertLogList = "getAlertLogList.action";
	
	/**
	 * 设置警报忽略
	 */
	private static String urlSetBusAlarmIgnore = "setBusAlarmIgnore.action";
	/**
	 * 获取警报忽略标志
	 */
	private static String urlGetBusAlarmIgnore = "getBusAlarmIgnore.action";
	
	/**
	 * 设置警报已处理
	 */
	private static String urlSetPrevAlarmHandled = "setPrevAlarmHandled.action";
	
	/**
	 * 检测服务地址是否是能访问
	 * @return
	 */
	public static boolean urlValidCheck(){
		boolean rslt = true;
		
		String url = dataRequestUrl + "/" + urlCheck;
		String response = LzzRequest.sendByGet(url, null, null);
		
		if(isErrorResponse(response)){
			return false;
		}
		
		rslt = JSONObject.fromObject(response).getBoolean("success");
		return rslt;
	}
	
	/**
	 * 获取用户能管理的公交列表
	 * @return
	 */
	public static JSONObject getUserMgrBusList(){
		JSONObject rslt = new JSONObject();
		
		String url = dataRequestUrl + "/" + urlGetUserMgrBusList;
		String response = LzzRequest.sendByGet(url, null, null);
		
		if(isErrorResponse(response)){
			return rslt;
		}
		
		rslt = JSONObject.fromObject(response);
		return rslt;
	}
	
	/**
	 * 获取用户所属公司
	 * @return
	 */
	public static String getUserCompany(){
		JSONObject rslt = new JSONObject();
		
		String url = dataRequestUrl + "/" + urlGetUserCompany;
		String response = LzzRequest.sendByGet(url, null, null);
		
		if(isErrorResponse(response)){
			return null;
		}
		
		rslt = JSONObject.fromObject(response);
		return rslt.getString("companyName");
	}

	/**
	 * 获取公交日志信息
	 */
	public static JSONArray getLogListBetween(String start_time, String end_time){
		JSONArray rslt = new JSONArray();
		
		List<String> parName = new ArrayList<String>();
		List<String> parValue = new ArrayList<String>();
		parName.add("startTime");
		parValue.add(start_time);
		parName.add("endTime");
		parValue.add(end_time);
		
		String url = dataRequestUrl + "/" + urlGetSensorDataList;
		String response = LzzRequest.sendByGet(url, parName, parValue);
		
		if(isErrorResponse(response)){
			return rslt;
		}
		
		JSONObject rslt_obj = JSONObject.fromObject(response);
		
		return rslt_obj.getJSONArray("data");
	}
	
	/**
	 * 获取报警的车辆信息
	 * @return
	 */
	public static JSONArray getAlarmedBuses() {
		JSONArray rslt = new JSONArray();
		
		String url = dataRequestUrl + "/" + urlGetAlarmedBuses;
		String response = LzzRequest.sendByGet(url);
		
		if(isErrorResponse(response)){
			return rslt;
		}
		
		JSONObject rslt_obj = JSONObject.fromObject(response);
		
		return rslt_obj.getJSONArray("data");
	}
	
	/**
	 * 获取公交报警信息
	 * @param busId
	 * @return
	 */
	public static String getBusAlarmMsg(String busId) {
		String rslt = null;
		List<String> parName = new ArrayList<String>();
		List<String> parValue = new ArrayList<String>();
		parName.add("busId");
		parValue.add(busId);
		
		String url = dataRequestUrl + "/" + urlGetBusAlarmMsg;
		String response = LzzRequest.sendByGet(url, parName, parValue);
		
		if(isErrorResponse(response)){
			return rslt;
		}
		
		JSONObject rslt_obj = JSONObject.fromObject(response);
		
		return rslt_obj.getString("data");
	}

	
	/**
	 * 设置公交报警已处理
	 * @param bus_id
	 * @return
	 */
	public static boolean setPrevAlarmHandled(String bus_id){
		List<String> ids = new ArrayList<String>();
		ids.add(bus_id);
		return setPrevAlarmHandled(ids);
	}
	
	/**
	 * 设置公交报警已处理
	 * @param bus_ids
	 * @return
	 */
	public static boolean setPrevAlarmHandled(List<String> bus_ids){
		List<String> parName = new ArrayList<String>();
		List<String> parValue = new ArrayList<String>();
		parName.add("busIds");
		try {
			parValue.add(URLEncoder.encode(bus_ids.toString(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		String url = dataRequestUrl + "/" + urlSetPrevAlarmHandled;
		String response = LzzRequest.sendByGet(url, parName, parValue);
		
		if(isErrorResponse(response)){
			return false;
		}
		
		JSONObject rslt_obj = JSONObject.fromObject(response);
		
		return rslt_obj.getBoolean("success");
	}
	
	/**
	 * 获取报警的日志列表
	 * @param car_id 公交ID
	 */
	public static JSONArray getAlertLogList(String bus_id){
		JSONArray rslt = new JSONArray();
		
		List<String> parName = new ArrayList<String>();
		List<String> parValue = new ArrayList<String>();
		if(null!=bus_id){
			parName.add("busId");
			parValue.add(bus_id);
		}
		
		String url = dataRequestUrl + "/" + urlGetAlertLogList;
		String response = LzzRequest.sendByGet(url, parName, parValue);
		
		if(isErrorResponse(response)){
			return rslt;
		}
		
		JSONObject rslt_obj = JSONObject.fromObject(response);
		return rslt_obj.getJSONArray("data");
	}
	
	/**
	 * 设置公交忽略警报
	 * @param car_id 公交ID
	 * @param flag
	 */
	public static boolean setBusAlarmIgnore(String car_id, boolean flag){
		List<String> parName = new ArrayList<String>();
		List<String> parValue = new ArrayList<String>();
		parName.add("busId");
		parValue.add(car_id);
		parName.add("flag");
		parValue.add(flag?"1":"0");
		
		String url = dataRequestUrl + "/" + urlSetBusAlarmIgnore;
		String response = LzzRequest.sendByGet(url, parName, parValue);
		
		if(isErrorResponse(response)){
			return false;
		}
		
		JSONObject rslt_obj = JSONObject.fromObject(response);
		return rslt_obj.getBoolean("success");
	}
	
	/**
	 * 获取公交忽略警报标志
	 * @param car_id 公交ID
	 */
	public static boolean getBusAlarmIgnore(String bus_id){
		List<String> parName = new ArrayList<String>();
		List<String> parValue = new ArrayList<String>();
		parName.add("busId");
		parValue.add(bus_id);
		
		String url = dataRequestUrl + "/" + urlGetBusAlarmIgnore;
		String response = LzzRequest.sendByGet(url, parName, parValue);
		
		if(isErrorResponse(response)){
			return false;
		}
		
		JSONObject rslt_obj = JSONObject.fromObject(response);
		return rslt_obj.getBoolean("data");
	}
	
	private static List<LzzSensorData> JSONArrayToObjList(JSONArray arr_datas) {
		List<LzzSensorData> rslt = new ArrayList<LzzSensorData>();
		
		for(int i=0; i<arr_datas.size(); i++){
			LzzSensorData data = (LzzSensorData)JSONObject.toBean(arr_datas.getJSONObject(i), LzzSensorData.class);
			rslt.add(data);
		}
		
		return rslt;
	}

	/**
	 * 判断是否在历史数据中
	 * @param data
	 * @return
	 */
	private static boolean inTotalLogDatas(LzzSensorData data){
		if(data==null) return true;
		
		List<LzzSensorData> datas = LzzDataCache.getTotalSensorDatas().get(data.getBusId());
		if(null==datas) return false;
		
		for(int i=0; i<datas.size(); i++){
			if(datas.get(i).getId().equals(data.getId())){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 获取历史数据总数量
	 * @return
	 */
	public static int getTotalLogSize(){
		int rslt = 0;
		Enumeration<List<LzzSensorData>> e = LzzDataCache.getTotalSensorDatas().elements();
		while(e.hasMoreElements()){
			rslt += e.nextElement().size();
		}
		
		return rslt;
	}
	
	/**
	 * 判断请求返回数据是否有效
	 * @param response
	 * @return
	 */
	private static boolean isErrorResponse(String response) {
		return "ERROR".equals(response) || response.contains("notlogin");
	}

	
}
