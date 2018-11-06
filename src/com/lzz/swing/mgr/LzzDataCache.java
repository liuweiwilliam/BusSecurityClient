package com.lzz.swing.mgr;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.lzz.swing.model.LzzSensorData;
import com.lzz.swing.view.LzzTreeBusNode;

public class LzzDataCache {
	/**
	 * 所有的公交
	 */
	private static List<LzzTreeBusNode> buses = new ArrayList<LzzTreeBusNode>();
	/**
	 * 清除所有公交
	 */
	public static void clearBuses(){
		buses.clear();
	}
	/**
	 * 添加公交
	 * @param bus
	 */
	public static void addNewBuses(LzzTreeBusNode bus){
		buses.add(bus);
	}
	/**
	 * 获取所有公交
	 * @return
	 */
	public static List<LzzTreeBusNode> getBuses(){
		return buses;
	}
	
	/**
	 * 传感器所有数据
	 */
	private static Hashtable<String, List<LzzSensorData>> totalSensorDatas = new Hashtable<String, List<LzzSensorData>>();
	
	/**
	 * 将传感器数据添加进所有传感器数据数组
	 * @param data
	 */
	public static void addTotalSensorDatas(LzzSensorData data){
		if(!totalSensorDatas.containsKey(data.getBusId())){
			totalSensorDatas.put(data.getBusId(), new ArrayList<LzzSensorData>());
		}
		totalSensorDatas.get(data.getBusId()).add(data);
	}
	
	public static Hashtable<String, List<LzzSensorData>> getTotalSensorDatas(){
		return totalSensorDatas;
	}
	
	/**
	 * 当前处于报警的公交ID列表
	 */
	private static List<String> alarmBuses = new ArrayList<String>();
	/**
	 * 添加新的报警公交
	 * @param bus_id
	 */
	public static void addNewAlarmBus(String bus_id){
		alarmBuses.add(bus_id);
	}
	/**
	 * 移除报警公交
	 * @param bus_id
	 */
	public static void removeAlarmBus(String bus_id){
		if(alarmBuses.contains(bus_id)){
			alarmBuses.remove(bus_id);
		}
	}
	/**
	 * 判断是否处于报警状态
	 * @param bus_id
	 * @return
	 */
	public static boolean inAlarmBuses(String bus_id){
		return alarmBuses.contains(bus_id);
	}
}
