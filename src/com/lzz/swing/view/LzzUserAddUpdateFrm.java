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

public class LzzUserAddUpdateFrm extends LzzBaseStyleFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1257938773419765657L;
	private JPanel contentPane;
	private LzzLabelInput lastName;
	private LzzLabelInput userName;
	private LzzLabelPassword userPwd;
	private LzzLabelPassword userPwdConfirm;
	private LzzLabelSelect company;
	private LzzLabelSelect role;

	/**
	 * 当前编辑的用户ID
	 */
	private String userid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LzzUserAddUpdateFrm frame = new LzzUserAddUpdateFrm(null);
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
	public LzzUserAddUpdateFrm(String userid) {
		this.userid = userid;
		
		setTitle("用户新增/编辑");
		
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
		JLabel titleLabel = new JLabel("用户新增/编辑");
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
				LzzUserAddUpdateFrm.this.dispose();
			}
		});
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(closeBtn);
		LzzFrameUtil.setDragable(titlePanel, this);
		contentPane.add(titlePanel);
		contentPane.add(Box.createVerticalStrut(15));
		
		lastName = new LzzLabelInput("用户姓名", Color.WHITE, 16, 300, 40);
		contentPane.add(lastName);
		contentPane.add(Box.createVerticalStrut(15));
		
		userName = new LzzLabelInput("用户昵称", Color.WHITE, 16, 300, 40);
		contentPane.add(userName);
		contentPane.add(Box.createVerticalStrut(15));
		
		userPwd = new LzzLabelPassword("登录密码", Color.WHITE, 16, 300, 40);
		contentPane.add(userPwd);
		contentPane.add(Box.createVerticalStrut(15));
		
		userPwdConfirm = new LzzLabelPassword("确认密码", Color.WHITE, 16, 300, 40);
		contentPane.add(userPwdConfirm);
		contentPane.add(Box.createVerticalStrut(15));
		
		JSONArray roles = LzzUserMgr.getRoleList();
		String[] items = new String[roles.size()];
		for(int i=0; i<items.length; i++){
			items[i] = roles.getJSONObject(i).getString("roleName");
		}
		
		role = new LzzLabelSelect("用户角色", Color.WHITE, 16, items, 300, 40);
		contentPane.add(role);
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
				addOrUpdateUser();
			}
		});
		btnPanel.add(btnNewButton);
		contentPane.add(btnPanel);
		
		getUserInfo();
		
		LzzFrameUtil.setFrameToScreenCenter(this);
	}

	private void getUserInfo() {
		if(null!=userid){
			JSONObject info = LzzUserMgr.getUserInfo(userid);
			lastName.setText(info.getString("lastName"));
			userName.setText(info.getString("userName"));
			userPwd.setText(info.getString("pwd"));
			userPwdConfirm.setText(info.getString("pwd"));
		}
	}

	protected void addOrUpdateUser() {
		JSONObject info = new JSONObject();
		info.put("id", null==userid?"":userid);
		info.put("lastName", lastName.getText());
		info.put("userName", userName.getText());
		info.put("pwd", userPwd.getText());
		info.put("pwd", userPwdConfirm.getText());
		info.put("role", role.getSelectedItem());
		
		JSONObject rslt = LzzUserMgr.addOrUpdateUser(info);
		if(rslt.getBoolean("success")){
			JOptionPane.showMessageDialog(null, "提交成功！");
			this.dispose();
		}else{
			JOptionPane.showMessageDialog(null, rslt.getString("msg"));
		}
	}
}
