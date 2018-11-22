package com.lzz.swing.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lzz.swing.component.LzzImgLabel;
import com.lzz.swing.component.LzzLabelInput;
import com.lzz.swing.component.LzzLabelPassword;
import com.lzz.swing.component.LzzLabelSelect;
import com.lzz.swing.component.LzzRButton;
import com.lzz.swing.mgr.LzzBusMgr;
import com.lzz.swing.mgr.LzzColorFontMgr;
import com.lzz.swing.mgr.LzzImgMgr;
import com.lzz.swing.mgr.LzzUserMgr;
import com.lzz.swing.util.LzzFrameUtil;
import com.lzz.swing.util.LzzImageUtil;
import com.lzz.swing.util.LzzProperties;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LzzBusAddUpdateFrm extends LzzBaseStyleFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1257938773419765657L;
	private JPanel contentPane;
	private LzzLabelSelect lineNum;
	private LzzLabelInput busNum;
	private String title = "公交新增/编辑";
	private JSONArray lines = new JSONArray();

	/**
	 * 当前编辑的公交车牌号
	 */
	private String busId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LzzBusAddUpdateFrm frame = new LzzBusAddUpdateFrm(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LzzBusAddUpdateFrm(String busId) {
		this.busId = busId;
		
		setTitle(title);
		
		setResizable(false);
		setUndecorated(true);
		
		setBounds(0, 0, 500, 500);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBackground(new Color(204, 204, 204));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		titlePanel.setMaximumSize(new Dimension(500, 100));
		titlePanel.setBackground(Color.DARK_GRAY);
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("宋体", Font.BOLD, 16));
		titleLabel.setForeground(LzzColorFontMgr.smTextColor);
		titleLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		titlePanel.add(titleLabel);
		LzzImgLabel closeBtn = new LzzImgLabel(LzzImgMgr.img_url_exit, 30, 30);
		closeBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		closeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				LzzBusAddUpdateFrm.this.dispose();
			}
		});
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(closeBtn);
		LzzFrameUtil.setDragable(titlePanel, this);
		contentPane.add(titlePanel);
		contentPane.add(Box.createVerticalStrut(15));
		
		
		lines = LzzBusMgr.getBusLineList();
		String[] items = new String[lines.size()];
		for(int i=0; i<items.length; i++){
			items[i] = lines.getJSONObject(i).getString("lineNum");
		}
		
		lineNum = new LzzLabelSelect("所属线路", Color.WHITE, 16, items, 300, 40);
		contentPane.add(lineNum);
		contentPane.add(Box.createVerticalStrut(15));
		
		busNum = new LzzLabelInput("车牌号码", Color.WHITE, 16, 300, 40);
		contentPane.add(busNum);
		contentPane.add(Box.createVerticalStrut(15));
		
		JPanel btnPanel = new JPanel();
		btnPanel.setOpaque(false);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setMaximumSize(new Dimension(300, 40));
		LzzRButton btnNewButton = new LzzRButton("保　　存");
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 16));
		btnNewButton.setPreferredSize(new Dimension(400, 40));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addOrUpdateBus();
			}
		});
		btnPanel.add(btnNewButton);
		contentPane.add(btnPanel);
		
		getBusInfo();
		
		LzzFrameUtil.setFrameToScreenCenter(this);
	}

	private void getBusInfo() {
		if(null!=busId){
			JSONObject info = LzzBusMgr.getBusBaseInfo(busId);
			busNum.setText(info.getString("busNum"));
			lineNum.setSelectedItem(info.getString("line"));
		}
	}

	protected void addOrUpdateBus() {
		JSONObject info = new JSONObject();
		info.put("id", null==busId?"":busId);
		info.put("busNum", null==busNum.getText()?"":busNum.getText());
		
		String selected_num = lineNum.getSelectedItem();
		String line_id = "";
		for(int i=0; i<lines.size(); i++){
			if(lines.getJSONObject(i).getString("lineNum").equals(selected_num)){
				line_id = lines.getJSONObject(i).getString("id");
			}
		}
		info.put("lineId", line_id);
		
		JSONObject rslt = LzzBusMgr.addOrUpdateBus(info);
		if(rslt.getBoolean("success")){
			JOptionPane.showMessageDialog(null, "提交成功！");
			this.dispose();
		}else{
			JOptionPane.showMessageDialog(null, rslt.getString("msg"));
		}
	}
}
