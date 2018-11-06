package com.lzz.swing.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import com.lzz.swing.component.LzzImgLabel;
import com.lzz.swing.util.LzzFrameUtil;
import com.lzz.swing.util.LzzImageUtil;
import com.lzz.swing.util.LzzProperties;
import com.lzz.swing.util.LzzRButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LzzSetServerFrm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1257938773419765657L;
	private JPanel contentPane;
	private JTextField serverUrl;
	private JTextField serverUrl2;
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
					LzzSetServerFrm frame = new LzzSetServerFrm();
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
	public LzzSetServerFrm() {
		setTitle("服务地址设置");
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
		JLabel titleLabel = new JLabel("登陆设置");
		titleLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		titlePanel.add(titleLabel);
		LzzImgLabel closeBtn = new LzzImgLabel("/images/closeImg.png", 30, 30);
		closeBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		closeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				LzzSetServerFrm.this.dispose();
			}
		});
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(closeBtn);
		LzzFrameUtil.setDragable(titlePanel, this);
		contentPane.add(titlePanel);
		contentPane.add(Box.createVerticalStrut(15));
		
		JPanel serveUrlPanel = new JPanel();
		serveUrlPanel.setLayout(new BoxLayout(serveUrlPanel, BoxLayout.X_AXIS));
		serveUrlPanel.setMaximumSize(new Dimension(300, 40));
		serveUrlPanel.setOpaque(false);
		JLabel lblNewLabel_1 = new JLabel("首选服务器");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 16));
		serverUrl = new JTextField();
		serveUrlPanel.add(lblNewLabel_1);
		serveUrlPanel.add(Box.createHorizontalStrut(15));
		serveUrlPanel.add(serverUrl);
		contentPane.add(serveUrlPanel);
		contentPane.add(Box.createVerticalStrut(15));
		
		JPanel serveUrlPanel2 = new JPanel();
		serveUrlPanel2.setLayout(new BoxLayout(serveUrlPanel2, BoxLayout.X_AXIS));
		serveUrlPanel2.setMaximumSize(new Dimension(300, 40));
		serveUrlPanel2.setOpaque(false);
		JLabel lblNewLabel_2 = new JLabel("备选服务器");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 16));
		serverUrl2 = new JTextField();
		serveUrlPanel2.add(lblNewLabel_2);
		serveUrlPanel2.add(Box.createHorizontalStrut(15));
		serveUrlPanel2.add(serverUrl2);
		contentPane.add(serveUrlPanel2);
		contentPane.add(Box.createVerticalStrut(15));
		
		JPanel servePortPanel = new JPanel();
		servePortPanel.setLayout(new BoxLayout(servePortPanel, BoxLayout.X_AXIS));
		servePortPanel.setMaximumSize(new Dimension(300, 40));
		servePortPanel.setOpaque(false);
		JLabel lblNewLabel_3 = new JLabel("服务器端口");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 16));
		serverPort = new JTextField();
		servePortPanel.add(lblNewLabel_3);
		servePortPanel.add(Box.createHorizontalStrut(15));
		servePortPanel.add(serverPort);
		contentPane.add(servePortPanel);
		contentPane.add(Box.createVerticalStrut(15));
		
		JPanel btnPanel = new JPanel();
		btnPanel.setOpaque(false);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setMaximumSize(new Dimension(300, 40));
		LzzRButton btnNewButton = new LzzRButton("保存设置");
		btnNewButton.setPreferredSize(new Dimension(400, 40));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveServerProperties();
			}
		});
		btnPanel.add(btnNewButton);
		contentPane.add(btnPanel);
		
		initData();
		
		LzzFrameUtil.setFrameToScreenCenter(this);
	}

	private void initData() {
		// TODO Auto-generated method stub
		String server_url = LzzProperties.getServerUrl();
		String server_url2 = LzzProperties.getServerUrl2();
		String server_port = LzzProperties.getServerPort();
		
		serverUrl.setText(server_url);
		serverUrl2.setText(server_url2);
		serverPort.setText(server_port);
	}

	protected void saveServerProperties() {
		// TODO Auto-generated method stub
		String server_url = serverUrl.getText();
		String server_url2 = serverUrl2.getText();
		String server_port = serverPort.getText();
		
		LzzProperties.saveServerUrl(server_url);
		LzzProperties.saveServerUrl2(server_url2);
		LzzProperties.saveServerPort(server_port);
		
		this.dispose();
	}
}
