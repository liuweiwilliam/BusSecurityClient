package com.lzz.swing.mgr;

import java.util.List;

import net.sf.json.JSONArray;

public class LzzBusMgr {
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
}
