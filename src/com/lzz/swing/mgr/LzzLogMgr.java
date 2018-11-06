package com.lzz.swing.mgr;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.lzz.swing.model.LzzSensorData;
import com.lzz.swing.util.LzzDateUtil;

public class LzzLogMgr {
	private static String lastRefreshTime = LzzDateUtil.getNow("s");
	/**
	 * 获取新的日志信息
	 * @return
	 */
	public static JSONArray getRefreshSensorData(){
		String now = LzzDateUtil.getNow("s");
		JSONArray rslt = LzzDataRequestMgr.getLogListBetween(lastRefreshTime, now);
		lastRefreshTime = now;
		return rslt;
	}
}
