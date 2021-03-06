package com.lzz.swing.mgr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lzz.swing.util.LzzExcelUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LzzBusMgr {
	/**
	 * 获取公交管理列表
	 * @param page_info
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject getBusesList(JSONObject page_info, String search_key){
		if(null==page_info){
			page_info = new JSONObject();
			page_info.put("curPage", 1);
			page_info.put("pageSize", 20);
		}
		
		return LzzDataCache.getBusesList(page_info, search_key);
	}
	
	/**
	 * 删除车辆
	 * @param busid
	 * @return
	 */
	public static JSONObject delBus(String busid){
		return LzzDataRequestMgr.delBus(busid);
	}
	
	/**
	 * 获取公交线路列表
	 * @param page_info
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONArray getBusLineList(){
		return LzzDataCache.getTotalBusLines();
	}
	
	/**
	 * 更新车辆信息
	 * @param info
	 * @return
	 */
	public static JSONObject addOrUpdateBus(JSONObject info){
		return LzzDataRequestMgr.addOrUpdateBus(info);
	}
	
	/**
	 * 获取报警车辆列表
	 * @return
	 */
	public static JSONArray getAlarmedBuses(){
		JSONArray rslt = LzzDataRequestMgr.getAlarmedBuses();
		return rslt;
	}
	
	/**
	 * 获取车辆报警信息
	 * @param busId
	 * @return
	 */
	public static String getBusAlarmMsg(String busId){
		String rslt = LzzDataRequestMgr.getBusAlarmMsg(busId);
		return rslt;
	}
	
	/**
	 * 获取公交的基础信息
	 * @param busId
	 * @return
	 */
	public static JSONObject getBusBaseInfo(String busId){
		JSONObject rslt = LzzDataRequestMgr.getBusBaseInfo(busId);
		return rslt;
	}
	/**
	 * 获取公交摄像头信息
	 * @param busId
	 * @return
	 */
	public static JSONObject getBusCamera(String busId){
		JSONObject rslt = LzzDataRequestMgr.getBusCamera(busId);
		return rslt;
	}

	/**
	 * 导入公交排班信息
	 * @param file
	 * @return
	 */
	public static JSONObject importBusInfo(File file) {
		JSONObject rslt = new JSONObject();
		
		JSONArray datas = new JSONArray();
		String[] valueFilter = {
				"{\"name\":\"线路\",\"parName\":\"lineNum\"}",
				"{\"name\":\"车牌号\",\"parName\":\"carNum\"}",
				"{\"name\":\"经度\",\"parName\":\"lng\"}",
				"{\"name\":\"纬度\",\"parName\":\"lat\"}"
		};
		try {
			FileInputStream input_document = new FileInputStream(file);
			XSSFWorkbook xlsx_workbook = new XSSFWorkbook(input_document);
			XSSFSheet worksheet = xlsx_workbook.getSheetAt(0);
			Cell cell =  worksheet.getRow(0).getCell(1);
			String company_name = LzzExcelUtil.getCellValue(cell);
			if(null==company_name){
				rslt.put("success", false);
				rslt.put("errMsg", "未找到公司名称，请检车文件格式");
			}
			JSONObject data_msg = LzzExcelUtil.parseExcelToJSON(worksheet, valueFilter, 2);
			
			if(!data_msg.getBoolean("success")){
				return data_msg;
			}
			
			JSONArray bus_data = data_msg.getJSONArray("data");
			JSONObject data = new JSONObject();
			data.put("busData", bus_data);
			data.put("company", company_name);
			rslt = LzzDataRequestMgr.importBusInfo(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rslt;
	}
	
	/**
	 * 导入摄像头信息
	 * @param file
	 * @return
	 */
	public static JSONObject importBusCamera(File file) {
		JSONObject rslt = new JSONObject();
		
		JSONArray datas = new JSONArray();
		String[] valueFilter = {
				"{\"name\":\"车牌号\",\"parName\":\"carNum\"}",
				"{\"name\":\"地址\",\"parName\":\"ip\"}",
				"{\"name\":\"端口号\",\"parName\":\"port\"}",
				"{\"name\":\"用户名\",\"parName\":\"uname\"}",
				"{\"name\":\"密码\",\"parName\":\"pwd\"}",
				"{\"name\":\"通讯模块ID\",\"parName\":\"comId\"}"
		};
		try {
			FileInputStream input_document = new FileInputStream(file);
			XSSFWorkbook xlsx_workbook = new XSSFWorkbook(input_document);
			XSSFSheet worksheet = xlsx_workbook.getSheetAt(0);
			
			JSONObject data_msg = LzzExcelUtil.parseExcelToJSON(worksheet, valueFilter, 0);
			
			if(!data_msg.getBoolean("success")){
				return data_msg;
			}
			
			JSONArray bus_data = data_msg.getJSONArray("data");
			JSONObject data = new JSONObject();
			data.put("cameraData", bus_data);
			rslt = LzzDataRequestMgr.importBusCamera(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rslt;
	}
}
