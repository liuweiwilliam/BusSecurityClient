package com.lzz.swing.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lzz.swing.component.LzzDataMgrPanel;
import com.lzz.swing.mgr.LzzUserMgr;

public class LzzUserMgrPanel extends LzzDataMgrPanel {
	
	private static Object[] tableTitles = {"序号", "用户编号", "姓名", "登录名", "角色"};
	public LzzUserMgrPanel(JFrame parent) {
		super(parent, tableTitles);
	}

	@Override
	protected void add() {
		LzzUserAddUpdateFrm add_update = new LzzUserAddUpdateFrm(null);
		add_update.setVisible(true);
		add_update.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosed(WindowEvent e) {
		    	searchData(); 
		    }  
		});
	}
	
	@Override
	protected void edit(String id) {
		LzzUserAddUpdateFrm add_update = new LzzUserAddUpdateFrm(id);
		add_update.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosed(WindowEvent e) {  
		    	searchData(); 
		    }  
		});
		add_update.setVisible(true);
	}
	
	@Override
	protected JSONObject del(String id) {
		JSONObject rslt = LzzUserMgr.delUser(id);
		return rslt;
	}
	
	@Override
	public JSONObject search(String search_key) {
		JSONObject rslt = LzzUserMgr.getUserList(pageInfo, search_key);
		JSONArray data = rslt.getJSONArray("data");
		JSONArray data_trans = new JSONArray();
		for(int data_i=0; data_i<data.size(); data_i++){
			JSONArray trans_sin = new JSONArray();
			JSONObject data_sin = data.getJSONObject(data_i);
			trans_sin.add(data_sin.getString("orderNum"));
			trans_sin.add(data_sin.getString("id"));
			trans_sin.add(data_sin.getString("lastName"));
			trans_sin.add(data_sin.getString("userName")); 
			trans_sin.add(data_sin.getString("roleName"));
			data_trans.add(trans_sin);
		}
		rslt.put("data", data_trans);
		return rslt;
	}
}
