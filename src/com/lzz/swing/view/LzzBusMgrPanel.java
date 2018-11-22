package com.lzz.swing.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lzz.swing.component.LzzDataMgrPanel;
import com.lzz.swing.mgr.LzzBusMgr;
import com.lzz.swing.mgr.LzzUserMgr;

public class LzzBusMgrPanel extends LzzDataMgrPanel {
	
	private static Object[] tableTitles = {"序号", "车辆编号", "车牌号码", "所属线路", "状态"};
	public LzzBusMgrPanel(JFrame parent) {
		super(parent, tableTitles);
	}

	@Override
	protected void add() {
		LzzBusAddUpdateFrm add_update = new LzzBusAddUpdateFrm(null);
		add_update.setVisible(true);
		add_update.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosed(WindowEvent e) {
		    	searchData();
		    	((LzzIndexFrm)parent).reloadBusTreePanel();
		    }  
		});
	}
	
	@Override
	protected void edit(String id) {
		LzzBusAddUpdateFrm add_update = new LzzBusAddUpdateFrm(id);
		add_update.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosed(WindowEvent e) {  
		    	searchData();
		    	((LzzIndexFrm)parent).reloadBusTreePanel();
		    }  
		});
		add_update.setVisible(true);
	}
	
	@Override
	protected JSONObject del(String id) {
		JSONObject rslt = LzzBusMgr.delBus(id);
		((LzzIndexFrm)parent).reloadBusTreePanel();
		return rslt;
	}
	
	@Override
	public JSONObject search(String search_key) {
		JSONObject rslt = LzzBusMgr.getBusesList(pageInfo, search_key);
		JSONArray data = rslt.getJSONArray("data");
		JSONArray data_trans = new JSONArray();
		for(int data_i=0; data_i<data.size(); data_i++){
			JSONArray trans_sin = new JSONArray();
			JSONObject data_sin = data.getJSONObject(data_i);
			trans_sin.add(data_sin.getString("orderNum"));
			trans_sin.add(data_sin.getString("id"));
			trans_sin.add(data_sin.getString("carNum"));
			trans_sin.add(data_sin.getString("lineName"));
			trans_sin.add(data_sin.getString("statusName")); 
			data_trans.add(trans_sin);
		}
		rslt.put("data", data_trans);
		return rslt;
	}
}
