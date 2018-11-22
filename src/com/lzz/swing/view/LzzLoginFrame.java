package com.lzz.swing.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import com.lzz.swing.component.LzzImgLabel;
import com.lzz.swing.component.LzzLoadingGlassPane;
import com.lzz.swing.component.LzzRButton;
import com.lzz.swing.mgr.LzzColorFontMgr;
import com.lzz.swing.mgr.LzzDataRequestMgr;
import com.lzz.swing.mgr.LzzFrameMgr;
import com.lzz.swing.mgr.LzzUserMgr;
import com.lzz.swing.util.LzzEncode;
import com.lzz.swing.util.LzzFontUtil;
import com.lzz.swing.util.LzzFrameUtil;
import com.lzz.swing.util.LzzImageUtil;
import com.lzz.swing.util.LzzProperties;

import java.awt.Color;

import javax.swing.JCheckBox;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class LzzLoginFrame extends LzzBaseStyleFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4486591068600432270L;
	
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField pwd;
	private JCheckBox rememberPwd;
	private JLabel setLabel;
	private Thread indexThread;
	private LzzIndexFrm indexFrm;
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
					
					/*UIManager.setLookAndFeel(new SubstanceLookAndFeel());
				    UIManager.put("swing.boldMetal", false);
				    if (System.getProperty("substancelaf.useDecorations") == null) {
				    	JFrame.setDefaultLookAndFeelDecorated(true);
				    	JDialog.setDefaultLookAndFeelDecorated(true);
				    }
				    System.setProperty("sun.awt.noerasebackground", "true");
				    SubstanceLookAndFeel.setCurrentTheme(new SubstanceLightAquaTheme());*/
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

	private int xOld = 0;
	private int yOld = 0;
	private String sysNameCN = "公交车安全预警联网平台";
	private String sysNameEN = "The Bus Safety Early Waring Network Platform";
	
	LzzRButton resetBtn = null;
	LzzRButton loginBtn = null;
	JPanel btnPanel = new JPanel();
	JPanel msgPanel = new JPanel();//信息提示区域
	JLabel msgLabel = new JLabel("");//信息
	LzzImgLabel ingLabel = new LzzImgLabel("/images/ing.gif", 100, 40);
	
	public LzzLoginFrame() {
		setTitle("系统登录");
		LzzFontUtil.setDefaultFont();
		setUndecorated(true);
		
		glasspane = new LzzLoadingGlassPane();
        setGlassPane(glasspane);
		
		setResizable(false);
		setTitle("系统登录");
		setBounds(0, 0, 800, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		/*JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
		menuPanel.setMaximumSize(new Dimension(800, 30));
		menuPanel.setBackground(Color.DARK_GRAY);*/
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		titlePanel.setMaximumSize(new Dimension(800, 110));
		titlePanel.setBackground(LzzColorFontMgr.smUIHeadBkColor);
		
		LzzImgLabel logoLabel = new LzzImgLabel("/images/syslogo.png", 269, 100);
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
		
		JPanel btn_panel = new JPanel();
		btn_panel.setBackground(LzzColorFontMgr.smUIHeadBkColor);
		btn_panel.setMaximumSize(new Dimension(20, 110));
		btn_panel.setPreferredSize(new Dimension(20, 110));
		LzzImgLabel closeBtn = new LzzImgLabel("/images/exit.png", 20, 20);
		closeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				LzzFrameUtil.closeWin(LzzLoginFrame.this);
			}
		});
		btn_panel.add(closeBtn);
		
		titlePanel.add(logoLabel);
		titlePanel.add(sysNamePanel);
		titlePanel.add(Box.createHorizontalGlue());
		LzzFrameUtil.setDragable(titlePanel, this);
		
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(btn_panel);
		titlePanel.add(Box.createHorizontalStrut(5));
		
		//contentPane.add(menuPanel);
		contentPane.add(titlePanel);
		
		contentPane.add(Box.createVerticalStrut(30));
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
		usernamePanel.setMaximumSize(new Dimension(300, 30));
		usernamePanel.setOpaque(false);
		JLabel lblNewLabel_1 = new JLabel("用户名");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 16));
		lblNewLabel_1.setIcon(new ImageIcon(LzzImageUtil.getResource("/images/userName.png")));
		username = new JTextField();
		username.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                int k = e.getKeyCode();
                if(k == e.VK_ENTER){
                	new Thread(new Runnable() {
    					@Override
    					public void run() {
    						login();
    					}
    				}).start();
                }
            }
		});
		usernamePanel.add(lblNewLabel_1);
		usernamePanel.add(Box.createHorizontalStrut(20));
		usernamePanel.add(username);
		contentPane.add(usernamePanel);
		contentPane.add(Box.createVerticalStrut(30));
		
		JPanel pwdPanel = new JPanel();
		pwdPanel.setLayout(new BoxLayout(pwdPanel, BoxLayout.X_AXIS));
		pwdPanel.setMaximumSize(new Dimension(300, 30));
		pwdPanel.setOpaque(false);
		JLabel lblNewLabel_2 = new JLabel("密  码");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 16));
		lblNewLabel_2.setIcon(new ImageIcon(LzzImageUtil.getResource("/images/password.png")));
		pwd = new JPasswordField();
		pwd.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                int k = e.getKeyCode();
                if(k == e.VK_ENTER){
                	new Thread(new Runnable() {
    					@Override
    					public void run() {
    						login();
    					}
    				}).start();
                }
            }
		});
		pwdPanel.add(lblNewLabel_2);
		pwdPanel.add(Box.createHorizontalStrut(20));
		pwdPanel.add(pwd);
		contentPane.add(pwdPanel);
		contentPane.add(Box.createVerticalStrut(30));
		
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
		contentPane.add(Box.createVerticalStrut(30));
		
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setMaximumSize(new Dimension(300, 30));
		btnPanel.setOpaque(false);
		resetBtn = new LzzRButton("重置");
		resetBtn.setFont(new Font("宋体", Font.BOLD, 16));
		resetBtn.setPreferredSize(new Dimension(120, 40));
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnPanel.add(resetBtn);
		
		btnPanel.add(Box.createHorizontalGlue());
		loginBtn = new LzzRButton("登录");
		loginBtn.setFont(new Font("宋体", Font.BOLD, 16));
		loginBtn.setPreferredSize(new Dimension(120, 40));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				indexThread = new Thread(new Runnable() {
					@Override
					public void run() {
						login();
					}
				});
				indexThread.start();
			}
		});
		btnPanel.add(loginBtn);
		
		contentPane.add(btnPanel);
		
		contentPane.add(ingLabel);
		ingLabel.setVisible(false);
		
		msgPanel.setLayout(new BoxLayout(msgPanel, BoxLayout.X_AXIS));
		msgPanel.setOpaque(false);
		msgPanel.add(Box.createHorizontalGlue());
		msgPanel.add(msgLabel);
		msgPanel.add(Box.createHorizontalStrut(5));
		contentPane.add(msgPanel);
		
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
	
	private boolean loging = false;
	/**
	 * 登陆
	 * @param arg0
	 */
	protected void login(){
		setMsg("服务器连接中...");
		boolean server_valid = LzzDataRequestMgr.urlValidCheck();
		if(!server_valid){
			setMsg("服务器连接失败!");
			startServerConnectTimer();
			return;
		}
		
		if(loging){
			return;
		}
		loging = true;
		setMsg("服务器连接成功！登录中...");
		
		String uname = username.getText();
		String pwd_str = new String(pwd.getPassword());
		if(null==uname || "".equals(uname)
				|| null==pwd_str || "".equals(pwd_str)){
			JOptionPane.showMessageDialog(null, "请输入用户名和密码！");
			loging = false;
			return;
		}
		
		loginBtn.setText("登录中...");
		
		boolean ser_par_com = checkServerProperties();
		if(!ser_par_com){
			showPropertiesSetFrame();
			loging = false;
			loginBtn.setText("登录");
			return;
		}
		
		boolean rslt = LzzUserMgr.login(uname, pwd_str);
		if(rslt){
			recordUsernameAndPwd(uname, pwd_str);
			
			indexFrm = new LzzIndexFrm();
			indexFrm.setVisible(true);
			this.dispose();
		}else{
			JOptionPane.showMessageDialog(null, "用户名或密码错误,请重新输入！");
			loging = false;
			loginBtn.setText("登录");
		}
		hideLoading();
	}
	
	private void setMsg(String string) {
		msgLabel.setText(string);
	}

	private int linkFailedTimes = 0;
	private int linkSeconds = 0;
	/**
	 * 链接服务器的定时器
	 */
	private void startServerConnectTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				linkSeconds++;
				setMsg("重新接连服务器(" + (10-linkSeconds) + "s)");
				
				if(linkSeconds%10==0){
					linkSeconds = 0;
					setMsg("服务器连接中...");
					boolean rslt = LzzDataRequestMgr.urlValidCheck();
					if(rslt){
						setMsg("服务器连接成功!登录中...");
						linkFailedTimes = 0;
						login();
					}else{
						setMsg("服务器连接中失败！");
						linkFailedTimes ++;
					}
					
					if(linkFailedTimes>=4){
						setMsg("登录超时,请检查你的网络和服务器设置!");
						this.cancel();
					}
				}
			}
		}, 1000, 1000);
	}

	//遮罩层
	private LzzLoadingGlassPane glasspane;
	private boolean isLoading = false;
	public void showLoading(){
		System.out.println("start thread");
		SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
            	btnPanel.setVisible(false);
        		ingLabel.setVisible(true);
        		contentPane.updateUI();
            }
        });
		
		isLoading = true;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
	        public void run() {
	        	if(!isLoading){
	        		btnPanel.setVisible(true);
	        		ingLabel.setVisible(false);
	        		this.cancel();
	        	}
	        }
		}, 0, 100);
	}
	
	/**
	 * 隐藏加载中遮罩
	 */
	public void hideLoading(){
		isLoading = false;
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
		
		return ser_url!=null && ser_port!=null;
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
