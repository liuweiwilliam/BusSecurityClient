package com.lzz.swing.view;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import org.apache.commons.collections.functors.NonePredicate;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.NullBorderPainter;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.skin.RavenSkin;
import org.jvnet.substance.skin.SubstanceAutumnLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessLookAndFeel;
import org.jvnet.substance.skin.SubstanceMangoLookAndFeel;
import org.jvnet.substance.theme.SubstanceAquaTheme;

import com.lzz.swing.component.LzzImgLabel;
import com.lzz.swing.mgr.LzzFrameMgr;
import com.lzz.swing.mgr.LzzUserMgr;
import com.lzz.swing.util.LzzEncode;
import com.lzz.swing.util.LzzFontUtil;
import com.lzz.swing.util.LzzFrameUtil;
import com.lzz.swing.util.LzzImageUtil;
import com.lzz.swing.util.LzzOprDataFromFile;
import com.lzz.swing.util.LzzProperties;
import com.lzz.swing.util.LzzRButton;

import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JCheckBox;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class LzzLoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4486591068600432270L;
	
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField pwd;
	private JCheckBox rememberPwd;
	private JLabel setLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LzzLoginFrame frame = new LzzLoginFrame();
					LzzFrameMgr.showFrame(frame);
					
					String server_url = LzzProperties.getServerUrl();
					if(null==server_url){
						LzzSetServerFrm set_frm = new LzzSetServerFrm();
						set_frm.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 设置初始的用户名和密码
	 */
	private void setInitUNamePwd() {
		String uname = LzzProperties.getUname();
		if(null!=uname){
			username.setText(uname);
		}
		
		String remember_pwd = LzzProperties.getRememberPwd();
		
		rememberPwd.setSelected("1".equals(remember_pwd));
		
		if("1".equals(remember_pwd)){
			String pwd_str = LzzProperties.getPwd();
			if(null!=pwd_str){
				pwd.setText(LzzEncode.decipher(pwd_str));
			}
		}
	}

	/**
	 * Create the frame.
	 */
	/*public LzzLoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LzzImageUtil.getResource("/images/logo.png")));
		LzzFontUtil.setDefaultFont();
		
		ImageIcon img = new ImageIcon(LzzImageUtil.getResource("/images/bk.jpg"));//这是背景图片
		img.setImage(img.getImage().getScaledInstance(600, 450, Image.SCALE_DEFAULT ));
    	JLabel imgLabel = new JLabel(img);//将背景图放在标签里。
    	this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
    	imgLabel.setBounds(0, 0, 600, 450);//设置背景标签的位置
    	
		setResizable(false);
		setTitle("系统登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("用户名");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 16));
		lblNewLabel_1.setBounds(269, 148, 77, 15);
		lblNewLabel_1.setIcon(new ImageIcon(LzzImageUtil.getResource("/images/userName.png")));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("密  码");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 16));
		lblNewLabel_2.setIcon(new ImageIcon(LzzImageUtil.getResource("/images/password.png")));
		lblNewLabel_2.setBounds(269, 208, 77, 15);
		contentPane.add(lblNewLabel_2);
		
		username = new JTextField();
		username.setBounds(375, 145, 157, 21);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton btnNewButton = new JButton("重置");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnNewButton.setBounds(269, 295, 104, 31);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("登录");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login(arg0);
			}
		});
		btnNewButton_1.setBounds(428, 295, 104, 31);
		contentPane.add(btnNewButton_1);
		
		pwd = new JPasswordField();
		pwd.setBounds(375, 205, 157, 21);
		contentPane.add(pwd);
		
		//设置frame居中显示
		LzzFrameUtil.setFrameToScreenCenter(this);
		
		((JPanel)contentPane).setOpaque(false);
		ImageIcon logo_icon = new ImageIcon(LzzImageUtil.getResource("/images/syslogo.png"));
		logo_icon.setImage(logo_icon.getImage().getScaledInstance(263, 96, Image.SCALE_DEFAULT ));
		
		rememberPwd = new JCheckBox("记住密码");
		rememberPwd.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				saveRememberPwdFlag();
			}
		});
		rememberPwd.setBounds(270, 253, 103, 23);
		rememberPwd.setOpaque(false);
		rememberPwd.setForeground(Color.WHITE);
		contentPane.add(rememberPwd);
		
		setLabel = new JLabel("<HTML><U>设置</U></HTML>");
		setLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				showPropertiesSetFrame();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				changeCursor(Cursor.HAND_CURSOR);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				changeCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		setLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		setLabel.setForeground(Color.WHITE);
		setLabel.setBounds(502, 257, 30, 15);
		contentPane.add(setLabel);
		
		setInitUNamePwd();
	}*/

	private int xOld = 0;
	private int yOld = 0;
	private String sysNameCN = "公交车安全预警联网平台";
	private String sysNameEN = "The Bus Safety Early Waring Network Platform";
	
	public LzzLoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LzzImageUtil.getResource("/images/logo.png")));
		LzzFontUtil.setDefaultFont();
		setUndecorated(true);
		
		setResizable(false);
		setTitle("系统登录");
		setBounds(0, 0, 800, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		titlePanel.setMaximumSize(new Dimension(800, 100));
		titlePanel.setBackground(Color.DARK_GRAY);
		
		LzzImgLabel logoLabel = new LzzImgLabel("/images/syslogo.png", 200, 100);
		logoLabel.setBorder(new EmptyBorder(10, 0, 0, 10));
		JPanel sysNamePanel = new JPanel();
		sysNamePanel.setLayout(new BoxLayout(sysNamePanel, BoxLayout.Y_AXIS));
		sysNamePanel.setOpaque(false);
		JLabel sysNameLabel1 = new JLabel(sysNameCN);
		sysNameLabel1.setForeground(Color.WHITE);
		sysNameLabel1.setFont(new Font("宋体", Font.PLAIN, 30));
		JLabel sysNameLabel2 = new JLabel(sysNameEN);
		sysNameLabel2.setForeground(Color.WHITE);
		sysNameLabel2.setFont(new Font("宋体", Font.PLAIN, 20));
		sysNamePanel.add(sysNameLabel1);
		sysNamePanel.add(sysNameLabel2);
		
		LzzImgLabel closeBtn = new LzzImgLabel("/images/closeImg.png", 40, 40);
		closeBtn.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		closeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				LzzFrameUtil.closeWin(LzzLoginFrame.this);
			}
		});
		
		titlePanel.add(logoLabel);
		titlePanel.add(sysNamePanel);
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(closeBtn);
		LzzFrameUtil.setDragable(titlePanel, this);
		contentPane.add(titlePanel);
		
		contentPane.add(Box.createVerticalStrut(20));
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
		usernamePanel.setMaximumSize(new Dimension(300, 30));
		usernamePanel.setOpaque(false);
		JLabel lblNewLabel_1 = new JLabel("用户名");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 16));
		lblNewLabel_1.setIcon(new ImageIcon(LzzImageUtil.getResource("/images/userName.png")));
		username = new JTextField();
		usernamePanel.add(lblNewLabel_1);
		usernamePanel.add(Box.createHorizontalStrut(20));
		usernamePanel.add(username);
		contentPane.add(usernamePanel);
		contentPane.add(Box.createVerticalStrut(15));
		
		JPanel pwdPanel = new JPanel();
		pwdPanel.setLayout(new BoxLayout(pwdPanel, BoxLayout.X_AXIS));
		pwdPanel.setMaximumSize(new Dimension(300, 30));
		pwdPanel.setOpaque(false);
		JLabel lblNewLabel_2 = new JLabel("密  码");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 16));
		lblNewLabel_2.setIcon(new ImageIcon(LzzImageUtil.getResource("/images/password.png")));
		pwd = new JPasswordField();
		pwdPanel.add(lblNewLabel_2);
		pwdPanel.add(Box.createHorizontalStrut(20));
		pwdPanel.add(pwd);
		contentPane.add(pwdPanel);
		contentPane.add(Box.createVerticalStrut(15));
		
		JPanel oprPanel = new JPanel();
		oprPanel.setLayout(new BoxLayout(oprPanel, BoxLayout.X_AXIS));
		oprPanel.setMaximumSize(new Dimension(300, 30));
		oprPanel.setOpaque(false);
		rememberPwd = new JCheckBox("记住密码");
		rememberPwd.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				saveRememberPwdFlag();
			}
		});
		rememberPwd.setOpaque(false);
		rememberPwd.setForeground(Color.WHITE);
		oprPanel.add(rememberPwd);
		
		setLabel = new JLabel("<HTML><U>设置</U></HTML>");
		setLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				showPropertiesSetFrame();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				changeCursor(Cursor.HAND_CURSOR);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				changeCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		setLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		setLabel.setForeground(Color.WHITE);
		oprPanel.add(setLabel);
		contentPane.add(oprPanel);
		contentPane.add(Box.createVerticalStrut(15));
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setMaximumSize(new Dimension(300, 30));
		btnPanel.setOpaque(false);
		LzzRButton btnNewButton = new LzzRButton("重置");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnPanel.add(btnNewButton);
		btnPanel.add(Box.createHorizontalGlue());
		LzzRButton btnNewButton_1 = new LzzRButton("登录");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login(arg0);
			}
		});
		btnPanel.add(btnNewButton_1);
		contentPane.add(btnPanel);
		
		//设置frame居中显示
		LzzFrameUtil.setFrameToScreenCenter(this);
		ImageIcon logo_icon = new ImageIcon(LzzImageUtil.getResource("/images/syslogo.png"));
		logo_icon.setImage(logo_icon.getImage().getScaledInstance(263, 96, Image.SCALE_DEFAULT));
		
		setInitUNamePwd();
	}
	
	/**
	 * 改变鼠标形状
	 * @param handCursor 
	 */
	protected void changeCursor(int cursor_type) {
		// TODO Auto-generated method stub
		setCursor(new Cursor(cursor_type));
	}

	protected void saveRememberPwdFlag() {
		// TODO Auto-generated method stub
		boolean is_remember = rememberPwd.isSelected();
		LzzProperties.saveRememberPwd(is_remember?"1":"0");
	}

	protected void reset() {
		// TODO Auto-generated method stub
		username.setText("");
		pwd.setText("");
	}
	
	/**
	 * 登陆
	 * @param arg0
	 */
	protected void login(ActionEvent arg0){
		String uname = username.getText();
		String pwd_str = new String(pwd.getPassword());
		
		boolean ser_par_com = checkServerProperties();
		if(!ser_par_com){
			showPropertiesSetFrame();
		}
		
		boolean rslt = LzzUserMgr.login(uname, pwd_str);
		if(rslt){
			recordUsernameAndPwd(uname, pwd_str);
			this.dispose();
			new LzzIndexFrm().setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null, "用户名或密码错误！");
		}
	}

	/**
	 * 显示参数设置窗口
	 */
	private void showPropertiesSetFrame() {
		// TODO Auto-generated method stub
		LzzSetServerFrm set_frm = new LzzSetServerFrm();
		set_frm.setVisible(true);
		return;
	}

	/**
	 * 检查服务参数是否完整
	 * @return
	 */
	private boolean checkServerProperties() {
		String ser_url = LzzProperties.getServerUrl();
		String ser_port = LzzProperties.getServerPort();
		String vpn_url = LzzProperties.getVpnUrl();
		String vpn_port = LzzProperties.getVpnUrl();
		
		return ser_url!=null && ser_port!=null && vpn_url!=null && vpn_port!=null;
	}

	/**
	 * 将用户名密码存储到配置文件
	 * @param uname
	 * @param pwd_str
	 */
	private void recordUsernameAndPwd(String uname, String pwd_str) {
		LzzProperties.saveUname(uname);
		LzzProperties.savePwd(LzzEncode.encryption(pwd_str));
	}
	
	
}
