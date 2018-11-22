package com.lzz.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
import com.lzz.swing.mgr.LzzColorFontMgr;
import com.lzz.swing.mgr.LzzImgMgr;
import com.lzz.swing.mgr.LzzLogMgr;
import com.lzz.swing.mgr.LzzUserMgr;
import com.lzz.swing.util.LzzFrameUtil;
import com.lzz.swing.util.LzzImageUtil;
import com.lzz.swing.util.LzzProperties;
import com.lzz.swing.util.LzzRequest;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LzzAlarmHandleFrm extends LzzBaseStyleFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1257938773419765657L;
	private JPanel contentPane;
	private LzzLabelInput lastName;
	private LzzLabelInput userName;
	private LzzLabelPassword userPwd;
	private LzzLabelPassword userPwdConfirm;
	private LzzLabelSelect sensorAlarmValid = null;
	private JTextArea sensorAlarmComment;
	private LzzLabelSelect driverAlarmValid = null;
	private JTextArea driverAlarmComment;
	private LzzLabelSelect role;

	/**
	 * 当前处理的报警ID
	 */
	private String alarmId;
	
	/**
	 * 父窗口
	 */
	private LzzIndexFrm parentFrame;
	
	private boolean hasSensorAlarm = false;
	private boolean hasDriverAlarm = false;
	
	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LzzAlarmHandleFrm frame = new LzzAlarmHandleFrm(null, null);
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
	public LzzAlarmHandleFrm(String alarmId, LzzIndexFrm parent_frame) {
		
		this.alarmId = alarmId;
		this.parentFrame = parent_frame;
		getAlarmInfo();
		
		setTitle("报警处理");
		
		setResizable(false);
		setUndecorated(true);
		
		setBounds(0, 0, 500, 400);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBackground(new Color(204, 204, 204));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		titlePanel.setMaximumSize(new Dimension(500, 100));
		titlePanel.setBackground(Color.DARK_GRAY);
		JLabel titleLabel = new JLabel("报警处理");
		titleLabel.setFont(new Font("宋体", Font.BOLD, 20));
		titleLabel.setForeground(LzzColorFontMgr.smTextColor);
		titleLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		titlePanel.add(titleLabel);
		LzzImgLabel closeBtn = new LzzImgLabel(LzzImgMgr.img_url_exit, 30, 30);
		closeBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		closeBtn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		closeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				LzzAlarmHandleFrm.this.dispose();
			}
		});
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(closeBtn);
		LzzFrameUtil.setDragable(titlePanel, this);
		contentPane.add(titlePanel);
		contentPane.add(Box.createVerticalStrut(15));
		
		String[] items = {"有效报警","无效报警"};
		if(hasSensorAlarm){
			sensorAlarmValid = new LzzLabelSelect("传感器报警", Color.WHITE, 16, items, 300, 40);
			contentPane.add(sensorAlarmValid);
			contentPane.add(Box.createVerticalStrut(5));
			
			sensorAlarmComment = new JTextArea();
			sensorAlarmComment.setLineWrap(true);
			sensorAlarmComment.setWrapStyleWord(true);// 激活断行不断字功能
			JScrollPane sensorScroll = new JScrollPane(sensorAlarmComment);
			sensorScroll.setHorizontalScrollBarPolicy( 
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			sensorScroll.setVerticalScrollBarPolicy( 
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			sensorScroll.setMaximumSize(new Dimension(300, 300));
			contentPane.add(sensorScroll);
			contentPane.add(Box.createVerticalStrut(15));
		}
		
		if(hasDriverAlarm){
			driverAlarmValid = new LzzLabelSelect("一键报警　", Color.WHITE, 16, items, 300, 40);
			contentPane.add(driverAlarmValid);
			contentPane.add(Box.createVerticalStrut(5));
			
			driverAlarmComment = new JTextArea();
			driverAlarmComment.setLineWrap(true);
			driverAlarmComment.setWrapStyleWord(true);// 激活断行不断字功能
			JScrollPane driverScroll = new JScrollPane(driverAlarmComment);
			driverScroll.setHorizontalScrollBarPolicy( 
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			driverScroll.setVerticalScrollBarPolicy( 
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			driverScroll.setMaximumSize(new Dimension(300, 300));
			contentPane.add(driverScroll);
			contentPane.add(Box.createVerticalStrut(15));
		}
		
		JPanel btnPanel = new JPanel();
		btnPanel.setOpaque(false);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setMaximumSize(new Dimension(300, 40));
		LzzRButton btnNewButton = new LzzRButton("提交处理");
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 16));
		btnNewButton.setPreferredSize(new Dimension(400, 40));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				handleAlarm();
			}
		});
		btnPanel.add(btnNewButton);
		contentPane.add(btnPanel);
		
		LzzFrameUtil.setFrameToScreenCenter(this);
	}

	private void getAlarmInfo() {
		if(null!=alarmId){
			JSONObject type = LzzLogMgr.getAlarmType(alarmId);
			hasSensorAlarm = type.getBoolean("hasSensorAlarm");
			hasDriverAlarm = type.getBoolean("hasDriverAlarm");
		}else{
			
		}
	}

	protected void handleAlarm() {
		JSONObject handle_info = new JSONObject();
		handle_info.put("alarmId", alarmId);
		
		if(null!=sensorAlarmValid){
			String sensor_valid = sensorAlarmValid.getSelectedItem();
			String sensor_comment = sensorAlarmComment.getText();
			handle_info.put("sensorAlarmValid", sensor_valid.equals("有效报警")?"1":"0");
			handle_info.put("sensorAlarmComment", sensor_comment);
		}else{
			handle_info.put("sensorAlarmValid", "");
			handle_info.put("sensorAlarmComment", "");
		}
		
		if(null!=driverAlarmValid){
			String driver_valid = driverAlarmValid.getSelectedItem();
			String driver_comment = driverAlarmComment.getText();
			handle_info.put("driverAlarmValid", driver_valid.equals("有效报警")?"1":"0");
			handle_info.put("driverAlarmComment", driver_comment);
		}else{
			handle_info.put("driverAlarmValid", "");
			handle_info.put("driverAlarmComment", "");
		}
		
		JSONObject rslt = LzzLogMgr.handleAlarm(handle_info);
		if(rslt.getBoolean("success")){
			JOptionPane.showMessageDialog(null, "处理成功");
			parentFrame.setSingleAlarmHandled(alarmId);
			this.dispose();
		}else{
			JOptionPane.showMessageDialog(null, rslt.getString("errMsg"));
		}
	}
}
