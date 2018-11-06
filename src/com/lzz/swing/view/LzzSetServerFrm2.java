package com.lzz.swing.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import com.lzz.swing.util.LzzFontUtil;
import com.lzz.swing.util.LzzFrameUtil;
import com.lzz.swing.util.LzzProperties;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LzzSetServerFrm2 extends JFrame {

	private JPanel contentPane;
	private JTextField serverUrl;
	private JTextField vpnUrl;
	private JTextField serverPort;
	private JTextField vpnPort;
	private JLabel label_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LzzSetServerFrm2 frame = new LzzSetServerFrm2();
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
	public LzzSetServerFrm2() {
		setTitle("服务地址设置");
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("服务器地址及端口：");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(39, 44, 192, 15);
		contentPane.add(label);
		
		JLabel lblVpn = new JLabel("VPN 地址及端口：");
		lblVpn.setFont(new Font("宋体", Font.PLAIN, 18));
		lblVpn.setBounds(39, 147, 225, 15);
		contentPane.add(lblVpn);
		
		serverUrl = new JTextField();
		serverUrl.setBounds(39, 69, 259, 40);
		contentPane.add(serverUrl);
		serverUrl.setColumns(10);
		
		vpnUrl = new JTextField();
		vpnUrl.setColumns(10);
		vpnUrl.setBounds(39, 172, 259, 40);
		contentPane.add(vpnUrl);
		
		serverPort = new JTextField();
		serverPort.setColumns(10);
		serverPort.setBounds(331, 69, 103, 40);
		contentPane.add(serverPort);
		
		vpnPort = new JTextField();
		vpnPort.setColumns(10);
		vpnPort.setBounds(331, 172, 103, 40);
		contentPane.add(vpnPort);
		
		JLabel label_1 = new JLabel("：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(308, 69, 22, 40);
		contentPane.add(label_1);
		
		label_2 = new JLabel("：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		label_2.setBounds(308, 172, 22, 40);
		contentPane.add(label_2);
		
		JButton btnNewButton = new JButton("保存设置");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveServerProperties();
			}
		});
		btnNewButton.setBounds(39, 265, 395, 40);
		contentPane.add(btnNewButton);
		
		initData();
		
		LzzFrameUtil.setFrameToScreenCenter(this);
	}

	private void initData() {
		// TODO Auto-generated method stub
		String server_url = LzzProperties.getServerUrl();
		String server_port = LzzProperties.getServerPort();
		String vpn_url = LzzProperties.getVpnUrl();
		String vpn_port = LzzProperties.getVpnPort();
		
		serverUrl.setText(server_url);
		serverPort.setText(server_port);
		vpnUrl.setText(vpn_url);
		vpnPort.setText(vpn_port);
	}

	protected void saveServerProperties() {
		// TODO Auto-generated method stub
		String server_url = serverUrl.getText();
		String server_port = serverPort.getText();
		String vpn_url = vpnUrl.getText();
		String vpn_port = vpnPort.getText();
		
		LzzProperties.saveServerUrl(server_url);
		LzzProperties.saveServerPort(server_port);
		LzzProperties.saveVpnUrl(vpn_url);
		LzzProperties.saveVpnPort(vpn_port);
		
		this.dispose();
	}
}
