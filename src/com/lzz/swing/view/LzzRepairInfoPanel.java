package com.lzz.swing.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lzz.swing.component.LzzDataTablePanel;
import com.lzz.swing.mgr.LzzLogMgr;
import com.lzz.swing.util.LzzExcelUtil;

public class LzzRepairInfoPanel extends LzzDataTablePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2674214139563026741L;
	
	private static Object[] tableTitles = {"序号", "公司", "线路", "车牌号", "故障内容", "时间"};
	
	public LzzRepairInfoPanel(JFrame parent) {
		super(parent, tableTitles);
	}
	
	@Override
	public JSONObject search() {
		String start = getStartDate();
		String end = getEndDate();
		JSONObject page_info = getCurPageInfo();
		JSONObject rslt = LzzLogMgr.getFaultInfoDur(start, end, page_info);
		JSONArray data = rslt.getJSONArray("data");
		JSONArray data_trans = new JSONArray();
		for(int data_i=0; data_i<data.size(); data_i++){
			JSONArray trans_sin = new JSONArray();
			JSONObject data_sin = data.getJSONObject(data_i);
			trans_sin.add(data_sin.getString("orderNum"));
			trans_sin.add(data_sin.getString("company"));
			trans_sin.add(data_sin.getString("line"));
			trans_sin.add(data_sin.getString("busNum"));
			trans_sin.add(data_sin.getString("comment"));
			trans_sin.add(data_sin.getString("createTime"));
			data_trans.add(trans_sin);
		}
		rslt.put("data", data_trans);
		return rslt;
	}
	
	@Override
	public void export(String file_path) {
		String start = getStartDate();
		String end = getEndDate();
		JSONArray data = LzzLogMgr.getTotalFaultInfoDur(start, end);
		
		List<String> datas = new ArrayList<String>();
		
		for(int i=0; i<tableTitles.length; i++){
			datas.add((String)tableTitles[i]);
		}
		
		for(int i=0; i<data.size(); i++){
			JSONObject data_sin = data.getJSONObject(i);
			datas.add(data_sin.getString("orderNum"));
			datas.add(data_sin.getString("company"));
			datas.add(data_sin.getString("line"));
			datas.add(data_sin.getString("busNum"));
			datas.add(data_sin.getString("comment"));
			datas.add(data_sin.getString("createTime"));
		}
		
		HSSFWorkbook file_rslt = LzzExcelUtil.createExcelFromStart(file_path, tableTitles.length, datas);
		if(null!=file_rslt){
			JOptionPane.showMessageDialog(null, "导出成功!");
		}else{
			JOptionPane.showMessageDialog(null, "导出失败!");
		}
	}
}
