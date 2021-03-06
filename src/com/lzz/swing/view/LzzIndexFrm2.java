package com.lzz.swing.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import com.lzz.swing.component.LzzBrowserPanel;
import com.lzz.swing.component.LzzImageIcon;
import com.lzz.swing.component.LzzImgLabel;
import com.lzz.swing.mgr.LzzBusMgr;
import com.lzz.swing.mgr.LzzDataCache;
import com.lzz.swing.mgr.LzzDataRequestMgr;
import com.lzz.swing.mgr.LzzLogMgr;
import com.lzz.swing.mgr.LzzUserMgr;
import com.lzz.swing.model.LzzSensorData;
import com.lzz.swing.util.LzzDateUtil;
import com.lzz.swing.util.LzzFontUtil;
import com.lzz.swing.util.LzzFrameUtil;
import com.lzz.swing.util.LzzImageUtil;
import com.lzz.swing.util.LzzProperties;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.examples.win32.W32API.HWND;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.NativeLongByReference;

public class LzzIndexFrm2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2445298015243567503L;

	private JPanel contentPane;
	
	/**
	 * 顶部控件对象
	 */
	private JPanel northPanel;
	
	/**
	 * 左侧控件对象
	 */
	private JPanel westPanel;
	private JPanel westPanelManu;
	private JPanel westPanelTree;
	
	/**
	 * 右侧控件对象
	 */
	private JPanel eastPanel;
	
	/**
	 * 底部控件对象
	 */
	private JPanel southPanel;
	
	/**
	 * 中间控件对象
	 */
	private JPanel centerPanel;
	
	/**
	 * 浏览器控件对象
	 */
	private JPanel webBrowserPanel;
	
	/**
	 * 底部时间显示控件
	 */
	private JLabel timeLabel;
	/**
	 * 底部版权显示控件
	 */
	private JLabel copyRightLabel;
	
	/**
	 * 最小化按钮控件
	 */
	private LzzImgLabel labelMin;
	/**
	 * 最大化按钮控件
	 */
	private LzzImgLabel labelMax;
	/**
	 * 向下还原控件
	 */
	private LzzImgLabel labelResize;
	/**
	 * 关闭按钮控件
	 */
	private LzzImgLabel labelClose;
	
	/**
	 * 背景图片控件
	 */
	private JLabel labelBk;
	
	/**
	 * 当前选中的公交ID
	 */
	private String curBusId = null;
	private Font prevFont = null;
	
	/**
	 * 当前选中的菜单名称
	 */
	private String curMenuName = null;
	
	/**
	 * 是否正在处理警告
	 */
	private boolean isAlermHandling = false;

	private int initWidth = 1366; //窗口初始化宽度
	private int initHeight = 768; //窗口初始化高度
	private Color bkColor = new Color(138, 184, 233);//背景色
	private Color bkColorBlack = new Color(36, 40, 44);//背景色
	private List<JPanel> menuPanels = new ArrayList<JPanel>();//菜单对象列表
	private String sysName = "公交车安全预警联网平台";
	private String img_url_min = "/images/min.png";
	private String img_url_normalStatus = "/images/normal.png";
	private String img_url_alarmStatus = "/images/alarm.png";
	private String img_url_logo = "/images/syslogo.png";
	private String img_url_small = "/images/login.png";
	private String img_url_bk = "/images/bk.jpg";
	private String img_url_close = "/images/exit.png";
	private String img_url_max = "/images/max.png";
	private String img_url_resize = "/images/preSize.png";
	private String img_url_head = "/images/head.png";
	private ImageIcon img = new ImageIcon(LzzImageUtil.getResource(img_url_bk));//这是背景图片
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LzzIndexFrm2 frame = new LzzIndexFrm2();
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
	public LzzIndexFrm2() {
		this.setBounds(0, 0, initWidth, initHeight);
		LzzFontUtil.setDefaultFont();
		setIconImage(Toolkit.getDefaultToolkit().getImage(LzzImageUtil.getResource("/images/logo.png")));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//设置容器布局方式为BorderLayout
		contentPane.setLayout(new BorderLayout());
		
		//创建顶部控件，并添加到容器
		createNorthPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		//创建左侧控件，并添加到容器
		createWestPanel();
		contentPane.add(westPanel, BorderLayout.WEST);
		
		//创建中间控件，并添加到容器
		createCenterPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		//创建底部控件，并添加到容器
		createSouthPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		
		//创建右部控件，并添加到容器
		createEastPanel();
		contentPane.add(eastPanel, BorderLayout.EAST);
		
		//创建窗口最大化最小化监听器
		addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent state) {
				// TODO Auto-generated method stub
				if(state.getNewState() == 1 || state.getNewState() == 7) {
                    System.out.println("窗口最小化");
                }else if(state.getNewState() == 0) {
                    System.out.println("窗口恢复到初始状态");
                }else if(state.getNewState() == 6) {
                    System.out.println("窗口最大化");
                }
				//setBackgroundImage(img_url_bk, getWidth(), getHeight());
			}
		});
		
		//设置窗口关闭提示
		addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                int exi = JOptionPane.showConfirmDialog (null, "要退出该程序吗？", "友情提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (exi == JOptionPane.YES_OPTION){
                    dispose();
        			System.exit(0);
                }
                else{
                    //return;
                }
            }
        });
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//设置窗口居中
		LzzFrameUtil.setFrameToScreenCenter(this);
		//设置窗口背景图片
		setBackgroundImage(img_url_bk, this.getWidth(), this.getHeight());
		((JPanel)contentPane).setOpaque(false);
		
		
		//摄像头参数初始化部分
		lUserID = new NativeLong(-1);
        lPreviewHandle = new NativeLong(-1);
        g_lVoiceHandle = new NativeLong(-1);
        m_lPort = new NativeLongByReference(new NativeLong(-1));
        fRealDataCallBack= new FRealDataCallBack();
        
        //首页时间刷新定时器
        setIndexTimeRefreshTimer();
        //获取报警车辆定时器
        setGetAlarmedBusedTimer();
        //日志获取定时器
        setLogRefreshTimer();
	}
	
	/**
	 * 设置时间刷新定时器
	 */
	protected void setIndexTimeRefreshTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        public void run() {
		        	timeLabel.setText(LzzDateUtil.getNow("s"));
		        }
		}, 1000 , 1000);
	}
	
	/**
	 * 设置获取报警车辆定时器
	 */
	protected void setGetAlarmedBusedTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        public void run() {
		        	JSONArray rslt = LzzBusMgr.getAlarmedBuses();
					if(rslt.size()>0){
						showAlarm(rslt);
					}else{
						System.out.println("没有报警数据");
					}
		        }
		}, 1000, 2000);
	}
	
	protected void setLogRefreshTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        public void run() {
		        	String name = LzzDateUtil.getNow("s");
		        	final JLabel log_label = new JLabel(name);
		        	log_label.setForeground(Color.RED);
		        	log_label.setName(name);
		        	logRefreshContentPanel.add(log_label);
		        	logRefreshContentPanel.updateUI();
		        	if(!isStopScroll){
		        		logScrollPanel.getViewport().setViewPosition(new Point(0, logScrollPanel.getVerticalScrollBar().getMaximum()));
		        	}
		        	log_label.addMouseListener(new MouseAdapter(){
					      public void mouseClicked(MouseEvent e){
					    	  if (e.getButton()==MouseEvent.BUTTON1
					    			  && e.getClickCount() >= 2){
					    		  JOptionPane.showMessageDialog(null, log_label.getName()); 
					    	  }
					      }
					});
		        	
		        	/*JSONArray rslt = LzzLogMgr.getRefreshSensorData();
					if(rslt.size()>0){
						for(int i=0; i<rslt.size(); i++){
							JSONObject obj = rslt.getJSONObject(i);
							String status = (String)obj.get("status");
							if("1".equals(status)){
								//是报警数据
								addAlarmLogData(obj);
							}else{
								//是正常数据
								addNormalLogData(obj);
							}
						}
					}else{
						System.out.println("没有报警数据");
					}*/
		        }
		}, 1000, 2000);
	}
	
	/**
	 * 添加正常类型传感器数据
	 * @param obj
	 */
	protected void addNormalLogData(JSONObject obj) {
		
	}

	/**
	 * 添加报警类型传感器数据
	 * @param obj
	 */
	protected void addAlarmLogData(JSONObject obj) {
		
	}

	/**
	 * 显示alarm信息
	 * @param rslt
	 */
	protected void showAlarm(JSONArray rslt) {
		String alarm_msg = "公交发生报警：\n";
		int count = 0;//新产生报警的公交
		for(int i=0; i<rslt.size(); i++){
			JSONObject data = (JSONObject) rslt.get(i);
			String bus_id = data.getString("id");
			if(!LzzDataCache.inAlarmBuses(bus_id)){
				String bus_name = getBusTreeNodePath(bus_id);
				alarm_msg = LzzBusMgr.getBusAlarmMsg(bus_id);
				setAlramBusLabel(bus_id);
				LzzDataCache.addNewAlarmBus(bus_id);
				count++;
			}
		}
		
		if(count>0){
			JOptionPane.showConfirmDialog(null, alarm_msg, "警告", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * 设置公交文字标签显示报警
	 * @param bus_id
	 */
	private void setAlramBusLabel(String bus_id){
		JLabel label = busLabels.get(bus_id);
		label.setForeground(textAlarmC);
		label.setIcon(new LzzImageIcon(img_url_alarmStatus, 10, 10));
	}
	
	/**
	 * 设置公交文字标签显示正常
	 * @param bus_id
	 */
	private void setNormalBusLabel(String bus_id){
		JLabel label = busLabels.get(bus_id);
		label.setForeground(textNormalC);
		label.setIcon(new LzzImageIcon(img_url_normalStatus, 10, 10));
	}

	public void paint(Graphics g){
		if(null==labelBk){
			labelBk = new JLabel(img);//将背景图放在标签里
			((ImageIcon)labelBk.getIcon()).setImage(((ImageIcon)labelBk.getIcon()).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
			this.getLayeredPane().add(labelBk, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里
		}else{
			((ImageIcon)labelBk.getIcon()).setImage(((ImageIcon)labelBk.getIcon()).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		}
    	labelBk.setBounds(0, 0, this.getWidth(), this.getHeight());//设置背景标签的位置
    	
		super.paint(g);
	}
	/**
	 * 设置frame背景图片
	 * @param bk_img_url 背景图片地址
	 * @param width 需设置的宽度
	 * @param height 需设置的高度
	 * @param frame 设置的frame主体对象
	 * @param content_pane frame容器
	 */
	private void setBackgroundImage(String bk_img_url, int width, int height) {
		ImageIcon img = new ImageIcon(LzzImageUtil.getResource(bk_img_url));//这是背景图片
		if(null==labelBk){
			labelBk = new JLabel(img);//将背景图放在标签里
			((ImageIcon)labelBk.getIcon()).setImage(((ImageIcon)labelBk.getIcon()).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
			this.getLayeredPane().add(labelBk, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里
		}else{
			((ImageIcon)labelBk.getIcon()).setImage(((ImageIcon)labelBk.getIcon()).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		}
    	labelBk.setBounds(0, 0, width, height);//设置背景标签的位置
	}

	/**
	 * 创建顶部控件
	 */
	private void createNorthPanel() {
		// TODO Auto-generated method stub
		northPanel = new JPanel();
		((JPanel)northPanel).setOpaque(false);
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		
		//设置顶部LOGO图标,需要将图片进行缩放，生成新的ImageIcon
		ImageIcon imageIcon = new ImageIcon(LzzImageUtil.getResource(img_url_logo));
		Image image = imageIcon.getImage();
		Image smallImage = image.getScaledInstance(180,65,Image.SCALE_FAST);
		ImageIcon smallIcon = new ImageIcon(smallImage);
		JLabel logo_label = new LzzImgLabel(smallIcon);
		northPanel.add(logo_label);
		
		//添加水平间距
		northPanel.add(Box.createHorizontalStrut(10));
		
		//添加系统名称
		JLabel sys_name_l = new JLabel(sysName);
		sys_name_l.setFont(new Font("Dialog", 1, 30));
		sys_name_l.setForeground(Color.WHITE);
		northPanel.add(sys_name_l);
		
		//添加水平间距
		northPanel.add(Box.createHorizontalGlue());
		
		//添加最小化按钮
		labelMin = new LzzImgLabel(img_url_min);
		labelMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				minWin();
			}
		});
		labelMin.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		//添加最大化按钮
		labelMax = new LzzImgLabel(img_url_max);
		labelMax.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				maxWin();
			}
		});
		labelMax.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		//添加还原按钮
		labelResize = new LzzImgLabel(img_url_resize);
		labelResize.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				resizeWin();
			}
		});
		labelResize.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		//添加关闭按钮
		labelClose = new LzzImgLabel(img_url_close);
		labelClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				closeWin();
			}
		});
		labelClose.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		//northPanel.add(labelMin);
		//northPanel.add(labelMax);
		//northPanel.add(labelResize);
		//labelResize.setVisible(false);
		//northPanel.add(labelClose);
		
		JLabel head_label = new LzzImgLabel(img_url_head, 30, 30, LzzUserMgr.userName);
		northPanel.add(head_label);
		northPanel.add(Box.createHorizontalStrut(10));
	}

	/**
	 * 关闭窗口
	 */
	protected void closeWin() {
		// TODO Auto-generated method stub
		int rslt = JOptionPane.showConfirmDialog(null, "确认退出系统?");
		if(rslt==0){//是
			this.dispose();
			System.exit(0);
		}
		
		if(rslt==1){//否
			
		}
		
		if(rslt==2){//取消
			
		}
	}

	/**
	 * 窗口尺寸改变
	 */
	protected void resizeWin() {
		labelMax.setVisible(true);
		labelResize.setVisible(false);
		this.setBounds(0, 0, origWidth, origHeight);
		this.setLocation(xOld, yOld);
		setBackgroundImage(img_url_bk, this.getWidth(), this.getHeight());
		this.validate();
	}
	
	private static int xOld;
	private static int yOld;
	private int origWidth;
	private int origHeight;
	/**
	 * 最大化窗口
	 */
	protected void maxWin() {
		labelMax.setVisible(false);
		labelResize.setVisible(true);
		origWidth = this.getWidth();
		origHeight = this.getHeight();
		xOld = this.getX();
		yOld = this.getY();
		
		this.setBounds(0, 0, LzzFrameUtil.getWindowMaxWidth(), LzzFrameUtil.getWindowMaxHeight(this));
		setBackgroundImage(img_url_bk, this.getWidth(), this.getHeight());
		this.validate();
	}

	/**
	 * 最小化窗口
	 */
	protected void minWin() {
		// TODO Auto-generated method stub
		this.setExtendedState(JFrame.ICONIFIED);
		this.validate();
	}

	private String menuStr = "[{\"name\":\"地图\",\"imgUrl\":\"/images/map.png\"},"
			+ "{\"name\":\"日志\",\"imgUrl\":\"/images/log.png\"},"
			+ "{\"name\":\"监控\",\"imgUrl\":\"/images/camera.png\"},"
			+ "{\"name\":\"设置\",\"imgUrl\":\"/images/set.png\"},"
			+ "]";
	private JSONArray menu = JSONArray.fromObject(menuStr);
	private Color menuBgC = new Color(167, 232, 238);
	private Color menuTextC = new Color(30, 59, 166);
	private Color textAlarmC = new Color(242, 77, 92);
	private Color textNormalC = new Color(0, 0, 0);
	private Font busSelectedFont = new java.awt.Font("Dialog", 1, 15);
	private Font busNormalFont = new java.awt.Font("Dialog", 0, 12);
	/**
	 * 创建左侧控件
	 */
	private void createWestPanel() {
		// TODO Auto-generated method stub
		westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.X_AXIS));
		//给左侧菜单控件加边框
		//westPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		westPanelManu = new JPanel();
		westPanelManu.setLayout(new BoxLayout(westPanelManu, BoxLayout.Y_AXIS));
		westPanelManu.setBorder(BorderFactory.createTitledBorder("功能区"));
		westPanelManu.setBackground(bkColor);
		
		westPanelTree = new JPanel();
		westPanelTree.setLayout(new BoxLayout(westPanelTree, BoxLayout.Y_AXIS));
		westPanelTree.setBackground(bkColor);
		
		//创建菜单控件
		JPanel menuPanel1 = new JPanel();
		menuPanel1.setLayout(new BoxLayout(menuPanel1, BoxLayout.Y_AXIS));
		for(int i=0; i<menu.size(); i++){
			JPanel single = new JPanel();
			single.setLayout(new BoxLayout(single, BoxLayout.Y_AXIS));
			single.setName(menu.getJSONObject(i).getString("name"));
			//菜单图标
			LzzImgLabel label = new LzzImgLabel(menu.getJSONObject(i).getString("imgUrl"), 100, 100);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			single.add(label);
			//菜单名称
			JLabel namelabel = new JLabel(menu.getJSONObject(i).getString("name"));
			namelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			namelabel.setFont(new Font("Dialog", 1, 15));
			namelabel.setForeground(menuTextC);
			single.add(namelabel);
			
			//默认第一个选中
			if(i==0){
				single.setBackground(menuBgC);
			}
			
			//设置下沉式边框
			Border llineBorder = BorderFactory.createLoweredBevelBorder();
			single.setBorder(llineBorder);
			
			//添加鼠标点击事件，切换菜单
			single.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					changeMenu(arg0);
				}
			});
			
			menuPanels.add(single);
			menuPanel1.add(single);
			
			//添加垂直间距
			menuPanel1.add(Box.createVerticalStrut(10));
		}
		westPanelManu.add(menuPanel1);
		//westPanelManu.setOpaque(false);
		
		//添加垂直间距
		westPanelManu.add(Box.createVerticalGlue());
		
		//创建菜单栏底部小菜单
		JPanel menuPanel2 = new JPanel();
		menuPanel2.setLayout(new BoxLayout(menuPanel2, BoxLayout.X_AXIS));
		
		LzzImgLabel label1 = new LzzImgLabel(img_url_small);
		menuPanel2.add(label1);
		
		LzzImgLabel label2 = new LzzImgLabel(img_url_small);
		menuPanel2.add(label2);
		westPanelManu.add(menuPanel2);
		
		
		//创建公交树状控件
		JPanel treePanel = createBusTreePanel();
		treePanel.setBackground(bkColor);
		treePanel.add(Box.createVerticalGlue());
		westPanelTree.add(treePanel);
				
		westPanel.add(westPanelManu);
		westPanel.add(Box.createHorizontalStrut(5));
		westPanel.add(westPanelTree);
		westPanel.add(Box.createHorizontalStrut(5));
		westPanel.setOpaque(false);
	}
	
	protected void changeMenu(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JPanel panel = (JPanel) arg0.getComponent();
		
		//监控功能需要先选择公交
		String menu_name = panel.getName();
		changeMenu(menu_name);
	}
	
	private void changeMenu(String menu_name){
		if(menu_name.equals("监控")){
			if(null==curBusId){
				JOptionPane.showMessageDialog(null, "请先选择一辆公交！");
				return;
			}else{
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				prewiew();
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		JPanel panel = null;
		for(int i=0; i<menuPanels.size(); i++){
			menuPanels.get(i).setBackground(null);
			if(menuPanels.get(i).getName().equals(menu_name)){
				panel = menuPanels.get(i); 
				panel.setBackground(menuBgC);
			}
		}
		
		centerCardLayout.show(centerPanel, menu_name);
	}

	private javax.swing.JPanel jPanelRealplayArea = new javax.swing.JPanel();
	private java.awt.Panel panelRealplay = new java.awt.Panel();
	boolean bRealPlay;//是否在预览.
	NativeLong lUserID;//用户句柄
	HCNetSDK.NET_DVR_CLIENTINFO m_strClientInfo;//用户参数
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	static PlayCtrl playControl = PlayCtrl.INSTANCE;
	NativeLong lPreviewHandle;//预览句柄
	NativeLongByReference m_lPort;//回调预览时播放库端口指针
	FRealDataCallBack fRealDataCallBack;//预览回调函数实现
	HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
	public static NativeLong g_lVoiceHandle;//全局的语音对讲句柄
	
	/**
	 * 初始化监控面板
	 */
	private void initMonitorPanel() {
		// TODO Auto-generated method stub
		jPanelRealplayArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 102)));
		jPanelRealplayArea.setLayout(new BoxLayout(jPanelRealplayArea, BoxLayout.Y_AXIS));
		jPanelRealplayArea.add(panelRealplay, BorderLayout.CENTER);
		
		/*javax.swing.GroupLayout jPanelRealplayAreaLayout = new javax.swing.GroupLayout(jPanelRealplayArea);
        jPanelRealplayArea.setLayout(jPanelRealplayAreaLayout);
        jPanelRealplayAreaLayout.setHorizontalGroup(
            jPanelRealplayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRealplay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelRealplayAreaLayout.setVerticalGroup(
            jPanelRealplayAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRealplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );*/

        //jSplitPaneVertical.setTopComponent(jPanelRealplayArea);
	}
	
	/**
	 * 预览函数
	 */
	private void prewiew(){
		registCamera();
		startPreview();
	}

	/**
	 * 开始预览
	 */
	private void startPreview() {
		System.out.println(panelRealplay.getWidth());
		System.out.println(panelRealplay.getHeight());
		
        if (lUserID.intValue() == -1){
            JOptionPane.showMessageDialog(this, "摄像头初始化失败！");
            return;
        }

        //如果预览窗口没打开,不在预览
        if (bRealPlay == false){
            //获取窗口句柄
            HWND hwnd = new HWND(Native.getComponentPointer(panelRealplay));

            //获取通道号
            int iChannelNum = getChannelNumber();//通道号
            if(iChannelNum == -1){
                JOptionPane.showMessageDialog(this, "请选择要预览的通道");
                return;
            }

            m_strClientInfo = new HCNetSDK.NET_DVR_CLIENTINFO();
            m_strClientInfo.lChannel = new NativeLong(iChannelNum);

            //在此判断是否回调预览,0,不回调 1 回调
            int directPreview = 0;
            if(directPreview == 0){
                m_strClientInfo.hPlayWnd = hwnd;
                lPreviewHandle = hCNetSDK.NET_DVR_RealPlay_V30(lUserID,
                        m_strClientInfo, null, null, true);
                System.out.println(hCNetSDK.NET_DVR_GetLastError());
            }
            else if(directPreview == 1)
            {
                m_strClientInfo.hPlayWnd = null;
                lPreviewHandle = hCNetSDK.NET_DVR_RealPlay_V30(lUserID,
                        m_strClientInfo, fRealDataCallBack, null, true);
            }

            long previewSucValue = lPreviewHandle.longValue();

            //预览失败时:
            if (previewSucValue == -1){
                JOptionPane.showMessageDialog(this, "预览失败");
                return;
            }

            //预览成功的操作
            bRealPlay = true;
        }else{//如果在预览,停止预览,关闭窗口
            hCNetSDK.NET_DVR_StopRealPlay(lPreviewHandle);
            bRealPlay = false;
            if(m_lPort.getValue().intValue() != -1)
            {
                playControl.PlayM4_Stop(m_lPort.getValue());
                m_lPort.setValue(new NativeLong(-1));
            }

            panelRealplay.repaint();
        }
	}

	/**
	 * 注册链接摄像头
	 * @return 是否注册成功
	 */
	private boolean registCamera() {
		//注册之前先注销已注册的用户,预览情况下不可注销
        if (bRealPlay){
            //JOptionPane.showMessageDialog(this, "注册新用户请先停止当前预览!");
            //return false;
        	hCNetSDK.NET_DVR_StopRealPlay(lPreviewHandle);
        	bRealPlay = false;
            if(m_lPort.getValue().intValue() != -1)
            {
                playControl.PlayM4_Stop(m_lPort.getValue());
                m_lPort.setValue(new NativeLong(-1));
            }
            
            hCNetSDK.NET_DVR_Logout_V30(lUserID);
            lUserID = new NativeLong(-1);
        }

        if (lUserID.longValue() > -1){
            //先注销
            //hCNetSDK.NET_DVR_Logout_V30(lUserID);
            //lUserID = new NativeLong(-1);
        }
        //注册
        String camera_ip = "172.0.0.5";//设备ip地址
        m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
        int iPort = Integer.parseInt("38022");
        
        boolean initSuc = hCNetSDK.NET_DVR_Init();
        if (initSuc != true){
            JOptionPane.showMessageDialog(null, "初始化失败");
            return false;
        }
        
        lUserID = hCNetSDK.NET_DVR_Login_V30(camera_ip,
                (short) iPort, "admin", "XINHEDEF001", m_strDeviceInfo);

        long userID = lUserID.longValue();
        if (userID == -1){
        	camera_ip = "";//登录未成功,IP置为空
        	return false;
        }
        
        return true;
	}

	//获取摄像头通道号
	private int getChannelNumber() {
        /*int iChannelNum = -1;
        TreePath tp = jTreeDevice.getSelectionPath();//获取选中节点的路径
        if(tp != null)//判断路径是否有效,即判断是否有通道被选中{
            //获取选中的通道名,对通道名进行分析:
            String sChannelName = ((DefaultMutableTreeNode)tp.getLastPathComponent()).toString();
            if(sChannelName.charAt(0) == 'C')//Camara开头表示模拟通道{
	            //子字符串中获取通道号
	            iChannelNum = Integer.parseInt(sChannelName.substring(6));
            }else{
                if(sChannelName.charAt(0) == 'I')//IPCamara开头表示IP通道{
                     //子字符创中获取通道号,IP通道号要加32
                     iChannelNum = Integer.parseInt(sChannelName.substring(8)) + 32;
                }else{
                     return -1;
                }
            }
        }else{
             return -1;
        }
        return iChannelNum;*/
		return 1;
	}

	/**
	 * 监控功能UI对象
	 */
	private JPanel monitorPanel;
	
	/**
	 * 日志管理功能UI对象
	 */
	private JPanel logMgrPanel;
	
	
	/**
	 * 日志刷新功能UI对象
	 */
	private JPanel logRefreshPanel;
	/**
	 * 日志刷新功能滚动框
	 */
	private JScrollPane logScrollPanel;
	/**
	 * 日志刷新功能内容面板
	 */
	private JPanel logRefreshContentPanel;
	
	/**
	 * 是否停止滚动
	 */
	private boolean isStopScroll = false;
	
	/**
	 * 地图功能UI对象
	 */
	private JPanel mapPanel;
	
	/**
	 * 设置功能UI对象
	 */
	private JPanel setPanel;

	/**
	 * 中部cardlayout布局对象
	 */
	private CardLayout centerCardLayout;
	
	/**
	 * 创建中间控件对象
	 */
	private void createCenterPanel() {
		centerCardLayout = new CardLayout();
		centerPanel = new JPanel(centerCardLayout);
		
		//创建地图功能UI
		createCenterPanelMap();
		centerPanel.add(mapPanel, "地图");
		
		//创建日志管理功能UI
		createCenterPanelLogMgr();
		centerPanel.add(logMgrPanel, "日志");
		
		//创建监控功能UI
		createCenterPanelMonitor();
		centerPanel.add(monitorPanel, "监控");
		
		//创建设置功能UI
		createCenterPanelSet();
		centerPanel.add(setPanel, "设置");
		
		centerPanel.setOpaque(false);
	}

	/**
	 * 创建地图功能UI
	 */
	private void createCenterPanelMap() {
		// TODO Auto-generated method stub
		mapPanel = new JPanel();
		mapPanel.setLayout(new BorderLayout());
		
		//地图组件
		webBrowserPanel = new LzzBrowserPanel(LzzProperties.getServerUrl() + "?sessionId=" + LzzUserMgr.userSessionId);
		//webBrowserPanel.setLayout(new BoxLayout(webBrowserPanel, BoxLayout.Y_AXIS));
		mapPanel.add(webBrowserPanel, BorderLayout.CENTER);
		
		//创建传感器数据刷新面板
		createLogRefreshPanel();
		mapPanel.add(logRefreshPanel, BorderLayout.SOUTH);
		
		mapPanel.setOpaque(false);
	}
	
	/**
	 * 创建传感器数据刷新UI
	 */
	private void createLogRefreshPanel() {
		logRefreshPanel = new JPanel();
		logRefreshPanel.setLayout(new BoxLayout(logRefreshPanel, BoxLayout.Y_AXIS));
		logRefreshPanel.setPreferredSize(new Dimension(100, 200));
		logRefreshPanel.setBackground(bkColor);
		
		logRefreshContentPanel = new JPanel();
		logRefreshContentPanel.setLayout(new BoxLayout(logRefreshContentPanel, BoxLayout.Y_AXIS));
		logRefreshContentPanel.add(Box.createVerticalStrut(5));
		logRefreshContentPanel.setBackground(bkColorBlack);
	
		logScrollPanel = new JScrollPane(logRefreshContentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		logScrollPanel.setPreferredSize(new Dimension(100, 200));
		logScrollPanel.setBorder(BorderFactory.createTitledBorder("传感器数据实时刷新："));
		logScrollPanel.setOpaque(false);
		logRefreshPanel.add(logScrollPanel);
		
		JPanel check_panel = new JPanel();
		check_panel.setBackground(bkColor);
		check_panel.setLayout(new BoxLayout(check_panel, BoxLayout.X_AXIS));
		final JCheckBox stop_scroll  = new JCheckBox("暂停滚动");
		stop_scroll.setOpaque(false);
		stop_scroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isStopScroll = stop_scroll.isSelected();
			}
		});
		check_panel.add(stop_scroll);
		check_panel.add(Box.createHorizontalGlue());
		logRefreshPanel.add(check_panel);
	}

	/**
	 * 公交树状控件
	 */
	private JTree busTree;
	/**
	 * 创建树形控件对象
	 * @return
	 */
	private JPanel createBusTreePanel() {
		NativeInterface.open();
		
		final LzzIndexFrm2 that = this;
		
		//创建树形控件
		JPanel treePanel = new JPanel();
		treePanel.setOpaque(false);
		//创建树形控件节点
		DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer();
        cellRenderer.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
        cellRenderer.setBackgroundSelectionColor(new Color(0, 0, 0, 0));
		
		JSONObject bus_datas = LzzDataRequestMgr.getUserMgrBusList();
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode();
		LzzDataCache.clearBuses();
		if(!bus_datas.isEmpty()){
			top = getCompanyTree(bus_datas.getJSONObject("data"));
		}
		
		
		/*DefaultMutableTreeNode node1 = new DefaultMutableTreeNode(new LzzTreeBusNode("1路", ""));
        node1.add(new DefaultMutableTreeNode(new LzzTreeBusNode("皖1111", "001")));
        node1.add(new DefaultMutableTreeNode(new LzzTreeBusNode("皖2222", "002")));
        node1.add(new DefaultMutableTreeNode(new LzzTreeBusNode("皖3333", "003")));
 
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(new LzzTreeBusNode("2路", ""));
        node2.add(new DefaultMutableTreeNode(new LzzTreeBusNode("皖4444", "004")));
        node2.add(new DefaultMutableTreeNode(new LzzTreeBusNode("皖5555", "005")));
        node2.add(new DefaultMutableTreeNode(new LzzTreeBusNode("皖6666", "006")));

        DefaultMutableTreeNode top = new DefaultMutableTreeNode(new LzzTreeBusNode("公交公司1", ""));
 
        top.add(node1);
        top.add(node2);*/
        
        final JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
        tmpPanel.setOpaque(false);
        busTree = new JTree(top);
        busTree.setOpaque(false);
        busTree.setCellRenderer(cellRenderer);
        //添加节点展开、收拢事件监听器
        busTree.addTreeExpansionListener(new TreeExpansionListener() {
			@Override
			public void treeExpanded(TreeExpansionEvent arg0) {
				// TODO Auto-generated method stub
				that.validate();
			}
			@Override
			public void treeCollapsed(TreeExpansionEvent arg0) {
				// TODO Auto-generated method stub
				that.validate();
			}
		});
        tmpPanel.add(busTree, BorderLayout.CENTER);
        treePanel.add(tmpPanel);
        
        //添加树形控件节点点击监听器
        busTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) busTree
                        .getLastSelectedPathComponent();
                if (node == null){
                	return;
                }
                
                LzzTreeBusNode object = (LzzTreeBusNode) node.getUserObject();
                if (node.isLeaf() && null!=object) {
            		System.out.println("你选择了：" + object.getNodeId());
            		selectBus(object.getNodeId(), null);
                }else{
                	System.out.println("你取消了公交选择");
                	cancelSelectBus();
                }
            }
        });
        
        return treePanel;
	}

	Hashtable <String, DefaultMutableTreeNode> busNodes = new Hashtable<String, DefaultMutableTreeNode>();
	/**
	 * 获取公交公司树控件数据结构
	 * @param bus_datas
	 * @return
	 */
	private DefaultMutableTreeNode getCompanyTree(JSONObject bus_datas) {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode top_node = new DefaultMutableTreeNode();
		if(!bus_datas.isEmpty()){
			String company_name = bus_datas.getString("name");
			top_node = new DefaultMutableTreeNode(new LzzTreeBusNode(company_name, "", ""));
			
			JSONArray lines = bus_datas.getJSONArray("lines");
			for(int i=0; i<lines.size(); i++){
				JSONObject line = lines.getJSONObject(i);
				String line_num = line.getString("lineNum");
				DefaultMutableTreeNode line_node = new DefaultMutableTreeNode(new LzzTreeBusNode(line_num, "", ""));
				
				JSONArray buses = line.getJSONArray("buses");
				
				if(buses.size()==0){
					line_node.add(new DefaultMutableTreeNode());
				}
				for(int j=0; j<buses.size(); j++){
					JSONObject bus = buses.getJSONObject(j);
					String id = bus.getString("id");
					String car_num = bus.getString("carNum");
					String status = bus.getString("status");
					LzzTreeBusNode tree_bus_node = new LzzTreeBusNode(car_num, id, status);
					DefaultMutableTreeNode bus_node = new DefaultMutableTreeNode(tree_bus_node);
					
					busNodes.put(id, bus_node);
					
					line_node.add(bus_node);
					LzzDataCache.addNewBuses(tree_bus_node);
				}
				
				top_node.add(line_node);
			}
			
			JSONArray companys = bus_datas.getJSONArray("companys");
			for(int k=0; k<companys.size(); k++){
				top_node.add(getCompanyTree(companys.getJSONObject(k)));
			}
		}
		return top_node;
	}

	/**
	 * 创建日志功能UI
	 */
	private void createCenterPanelLogMgr() {
		// TODO Auto-generated method stub
		logMgrPanel = new JPanel();
		logMgrPanel.setLayout(new BoxLayout(logMgrPanel, BoxLayout.X_AXIS));
		logMgrPanel.add(Box.createHorizontalStrut(5));
		
		final Object[] columnNames = {"姓名", "性别", "家庭地址",//列名最好用final修饰
				"电话号码", "生日", "工作", "收入", "婚姻状况","恋爱状况"};
		Object[][] rowData = {
				{"ddd", "男", "江苏南京", "1378313210", "03/24/1985", "学生", "寄生中", "未婚", "没"},
				{"eee", "女", "江苏南京", "13645181705", "xx/xx/1985", "家教", "未知", "未婚", "好象没"},
				{"fff", "男", "江苏南京", "13585331486", "12/08/1985", "汽车推销员", "不确定", "未婚", "有"},
				{"ggg", "女", "江苏南京", "81513779", "xx/xx/1986", "宾馆服务员", "确定但未知", "未婚", "有"},
				{"ggg", "女", "江苏南京", "81513779", "xx/xx/1986", "宾馆服务员", "确定但未知", "未婚", "有"},
				{"ggg", "女", "江苏南京", "81513779", "xx/xx/1986", "宾馆服务员", "确定但未知", "未婚", "有"},
				{"ggg", "女", "江苏南京", "81513779", "xx/xx/1986", "宾馆服务员", "确定但未知", "未婚", "有"},
				{"ggg", "女", "江苏南京", "81513779", "xx/xx/1986", "宾馆服务员", "确定但未知", "未婚", "有"},
				{"ggg", "女", "江苏南京", "81513779", "xx/xx/1986", "宾馆服务员", "确定但未知", "未婚", "有"},
				{"ggg", "女", "江苏南京", "81513779", "xx/xx/1986", "宾馆服务员", "确定但未知", "未婚", "有"},
				{"ggg", "女", "江苏南京", "81513779", "xx/xx/1986", "宾馆服务员", "确定但未知", "未婚", "有"},
				{"ggg", "女", "江苏南京", "81513779", "xx/xx/1986", "宾馆服务员", "确定但未知", "未婚", "有"},
				{"hhh", "男", "江苏南京", "13651545936", "xx/xx/1985", "学生", "流放中", "未婚", "无数次分手后没有"}
		};
		
		JTable friends = new JTable (rowData, columnNames){
			public boolean isCellEditable(int row, int column){
			       return false;
			   }
		};
		friends.setPreferredScrollableViewportSize(new Dimension(600, 100));//设置表格的大小
		friends.setRowHeight (30);//设置每行的高度为20
		friends.setRowHeight (0, 20);//设置第1行的高度为15
		friends.setRowMargin (5);//设置相邻两行单元格的距离
		friends.setRowSelectionAllowed (true);//设置可否被选择.默认为false
		friends.setSelectionBackground (Color.white);//设置所选择行的背景色
		friends.setSelectionForeground (Color.red);//设置所选择行的前景色
		friends.setGridColor (Color.black);//设置网格线的颜色
		//friends.selectAll ();//选择所有行
		friends.setRowSelectionInterval (0,2);//设置初始的选择行,这里是1到3行都处于选择状态
		friends.clearSelection ();//取消选择
		friends.setDragEnabled (false);//不懂这个
		friends.setShowGrid (false);//是否显示网格线
		friends.setShowHorizontalLines (false);//是否显示水平的网格线
		friends.setShowVerticalLines (true);//是否显示垂直的网格线
		friends.setValueAt ("tt", 0, 0);//设置某个单元格的值,这个值是一个对象
		friends.doLayout ();
		friends.setBackground (Color.lightGray);
		//friends.setCellSelectionEnabled(false);//设置行是否可被选择
		//friends.setEnabled(false);
		
		JScrollPane pane1 = new JScrollPane (friends);//JTable最好加在JScrollPane上
		JPanel panel = new JPanel (new GridLayout(0, 1));
		panel.setPreferredSize (new Dimension (600,400));
		panel.setBackground (Color.black);
		panel.add (pane1);
		
		//logPanel.add(panel);
	}

	/**
	 * 创建监控功能UI
	 */
	private void createCenterPanelMonitor() {
		// TODO Auto-generated method stub
		monitorPanel = new JPanel();
		monitorPanel.setLayout(new BoxLayout(monitorPanel, BoxLayout.X_AXIS));
		monitorPanel.add(Box.createHorizontalStrut(5));
		
		initMonitorPanel();
		monitorPanel.add(jPanelRealplayArea);
		
		monitorPanel.setOpaque(false);
	}

	/**
	 * 设置页面标签页控件
	 */
	JTabbedPane tp;
	
	/**
	 * 公司设置面板
	 */
	JPanel companySetPanel;
	
	private String tabImg = "/images/tabImg.jpg";
	
	/**
	 * 创建设置功能UI
	 */
	private void createCenterPanelSet() {
		// TODO Auto-generated method stub
		setPanel = new JPanel();
		setPanel.setLayout(new BoxLayout(setPanel, BoxLayout.X_AXIS));
		setPanel.add(Box.createHorizontalStrut(5));
		
		tp = new JTabbedPane();
		LzzImageIcon image_icon = new LzzImageIcon(tabImg, 30, 30);
		createPanelCompanySet();
		tp.addTab("车辆及线路管理", image_icon, companySetPanel, "管理车辆线路及车辆信息");
		
		setPanel.add(tp);
		setPanel.setOpaque(false);
	}

	/**
	 * 创建公司设置面板
	 */
	private void createPanelCompanySet() {
		// TODO Auto-generated method stub
		companySetPanel = new JPanel();
		JLabel label = new JLabel("compnay set");
		companySetPanel.add(label);
	}

	/**
	 * 创建底部控件
	 */
	private void createSouthPanel() {
		// TODO Auto-generated method stub
		southPanel = new JPanel();
		((JPanel)southPanel).setOpaque(false);
		timeLabel = new JLabel("");
		timeLabel.setFont(new Font("Dialog", 1, 15));
		timeLabel.setForeground(Color.WHITE);
		copyRightLabel = new JLabel("芯核防务 版权所有@CopyRight");
		copyRightLabel.setFont(new Font("Dialog", 1, 15));
		copyRightLabel.setForeground(Color.WHITE);
		southPanel.add(copyRightLabel);
		southPanel.add(timeLabel);
	}
	
	/**
	 * 忽略公交警告单选框
	 */
	private JCheckBox ignoreCheck;
	Hashtable<String, LzzImgLabel> busLabels = new Hashtable<String, LzzImgLabel>();
	/**
	 * 创建右部控件
	 */
	/*private void createEastPanel() {
		// TODO Auto-generated method stub
		eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.X_AXIS));
		eastPanel.add(Box.createHorizontalStrut(5));
		
		JPanel content_panel = new JPanel();
		content_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		content_panel.setLayout(new BoxLayout(content_panel, BoxLayout.Y_AXIS));
		
		List<LzzTreeBusNode> buses = LzzDataCache.getBuses();
		for(int i=0; i<buses.size(); i++){
			final LzzImgLabel label = new LzzImgLabel(img_url_normalStatus, 10, 10, buses.get(i).getNodeName());
			final String bus_id = buses.get(i).getNodeId();
			
			
			label.setToolTipText(getBusTreeNodePath(bus_id));
			label.addMouseListener(new MouseAdapter(){
			      public void mouseClicked(MouseEvent e){
			    	  //label.setForeground(Color.red);
			    	  selectBus(bus_id, label);
			    	  if (e.getButton()==MouseEvent.BUTTON3) {  
			              //弹出右键菜单  
			    		  final JPopupMenu menu = new JPopupMenu("设置警告已处理");  
			    		  JMenuItem menu_item = new JMenuItem("设置警告已处理");
			    		  menu.add(menu_item);
			    		  menu.show(label, e.getPoint().x, e.getPoint().y);
			    		  menu_item.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								boolean rslt = setAlarmHandled(bus_id);
			    				  if(rslt){
			    					  JOptionPane.showMessageDialog(null, "处理成功");
			    				  }else{
			    					  JOptionPane.showMessageDialog(null, "设置失败");
			    				  }
							}
						});
			    	  }  
			      }
			});
			
			busLabels.put(bus_id, label);
			content_panel.add(label);
		}
		
		content_panel.add(Box.createVerticalGlue());
		
		//创建底部忽略勾选项
		JPanel check_panel = new JPanel();
		check_panel.setLayout(new BoxLayout(check_panel, BoxLayout.X_AXIS));
		
		ignoreCheck  = new JCheckBox("忽略报警");
		ignoreCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setBusAlermIgnore();
			}
		});
		content_panel.add(ignoreCheck);
		eastPanel.add(content_panel);
		eastPanel.setOpaque(false);
	}*/
	
	private static String menuBkImgAllCar = "/images/menu-allcar.png";
	private static String menuBkImgAllCar_ml = "/images/menu-allcar-ml.png";
	private static String menuBkImgAllCar_hl = "/images/menu-allcar-hl.png";
	private static String menuBkImgAlarmCar = "/images/menu-alarmcar.png";
	private static String menuBkImgAlarmCar_ml = "/images/menu-alarmcar-ml.png";
	private static String menuBkImgAlarmCar_hl = "/images/menu-alarmcar-hl.png";
	private static boolean menuAllcarSelected = true;
	
	private void createEastPanel() {
		// TODO Auto-generated method stub
		eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.X_AXIS));
		eastPanel.add(Box.createHorizontalStrut(5));
		eastPanel.setBackground(bkColor);
		
		final JPanel content_panel = new JPanel();
		content_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		content_panel.setLayout(new BoxLayout(content_panel, BoxLayout.Y_AXIS));
		content_panel.setOpaque(false);
		
		//所有车辆列表面板
		final JPanel allBusContentPanel = new JPanel();
		allBusContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		allBusContentPanel.setLayout(new BoxLayout(allBusContentPanel, BoxLayout.Y_AXIS));
		allBusContentPanel.setOpaque(false);
		allBusContentPanel.add(new JLabel("所有车辆"));
		
		//报警车辆面板
		final JPanel alarmBusContentPanel = new JPanel();
		alarmBusContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		alarmBusContentPanel.setLayout(new BoxLayout(alarmBusContentPanel, BoxLayout.Y_AXIS));
		alarmBusContentPanel.setOpaque(false);
		alarmBusContentPanel.add(new JLabel("报警车辆"));
		
		//菜单面板
		JPanel menu_panel = new JPanel();
		menu_panel.setLayout(new BoxLayout(menu_panel, BoxLayout.X_AXIS));
		//所有车辆菜单按钮
		final LzzImgLabel menu_label1 = new LzzImgLabel(menuBkImgAllCar_hl, 100, 30);
		//报警车辆菜单按钮
		final LzzImgLabel menu_label2 = new LzzImgLabel(menuBkImgAlarmCar, 100, 30);
		//添加鼠标点击，经过，滑出样式切换事件
		menu_label1.addMouseListener(new MouseAdapter(){
			/**
			 * 鼠标点击切换面板
			 */
			public void mouseClicked(MouseEvent e){
				if (e.getButton()==MouseEvent.BUTTON1){
					menuAllcarSelected = true;
					allBusContentPanel.setVisible(true);
					alarmBusContentPanel.setVisible(false);
					menu_label1.changeIcon(menuBkImgAllCar_hl);
					menu_label2.changeIcon(menuBkImgAlarmCar);
				}
			}
			/**
			 * 鼠标进入，切换中等高亮
			 */
			public void mouseEntered(MouseEvent e) {
				menu_label1.changeIcon(menuBkImgAllCar_ml);
			}
			/**
			 * 鼠标离开，切换灰色
			 */
			public void mouseExited(MouseEvent e) {
				if(menuAllcarSelected){
					menu_label1.changeIcon(menuBkImgAllCar_hl);
				}else{
					menu_label1.changeIcon(menuBkImgAllCar);
				}
			}
		});
		
		menu_label2.addMouseListener(new MouseAdapter(){
			/**
			 * 鼠标点击切换面板
			 */
			public void mouseClicked(MouseEvent e){
				if (e.getButton()==MouseEvent.BUTTON1){
					menuAllcarSelected = false;
					allBusContentPanel.setVisible(false);
					alarmBusContentPanel.setVisible(true);
					menu_label1.changeIcon(menuBkImgAllCar);
					menu_label2.changeIcon(menuBkImgAlarmCar_hl);
				}
			}
			/**
			 * 鼠标进入，切换中等高亮
			 */
			public void mouseEntered(MouseEvent e) {
				menu_label2.changeIcon(menuBkImgAlarmCar_ml);
			}
			/**
			 * 鼠标离开，切换灰色
			 */
			public void mouseExited(MouseEvent e) {
				if(!menuAllcarSelected){
					menu_label2.changeIcon(menuBkImgAlarmCar_hl);
				}else{
					menu_label2.changeIcon(menuBkImgAlarmCar);
				}
			}
		});
		
		menu_panel.add(menu_label1);
		menu_panel.add(menu_label2);
		
		content_panel.add(menu_panel);
		
		List<LzzTreeBusNode> buses = LzzDataCache.getBuses();
		for(int i=0; i<buses.size(); i++){
			final LzzImgLabel label = new LzzImgLabel(img_url_normalStatus, 10, 10, buses.get(i).getNodeName());
			final String bus_id = buses.get(i).getNodeId();
			
			label.setToolTipText(getBusTreeNodePath(bus_id));
			label.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					//label.setForeground(Color.red);
					selectBus(bus_id, label);
					if (e.getButton()==MouseEvent.BUTTON3) {  
						//弹出右键菜单  
						final JPopupMenu menu = new JPopupMenu("设置警告已处理");  
						JMenuItem menu_item = new JMenuItem("设置警告已处理");
						menu.add(menu_item);
						menu.show(label, e.getPoint().x, e.getPoint().y);
						menu_item.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								boolean rslt = setAlarmHandled(bus_id);
								if(rslt){
									JOptionPane.showMessageDialog(null, "处理成功");
								}else{
									JOptionPane.showMessageDialog(null, "设置失败");
								}
							}
						});
					}  
				}
			});
			
			busLabels.put(bus_id, label);
			allBusContentPanel.add(label);
		}
		content_panel.add(allBusContentPanel);
		content_panel.add(alarmBusContentPanel);
		alarmBusContentPanel.setVisible(false);
		
		content_panel.add(Box.createVerticalGlue());
		
		//创建底部忽略勾选项
		JPanel check_panel = new JPanel();
		check_panel.setLayout(new BoxLayout(check_panel, BoxLayout.X_AXIS));
		check_panel.setOpaque(false);
		ignoreCheck  = new JCheckBox("忽略报警");
		ignoreCheck.setOpaque(false);
		ignoreCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setBusAlermIgnore();
			}
		});
		check_panel.add(ignoreCheck);
		check_panel.add(Box.createHorizontalGlue());
		
		content_panel.add(check_panel);
		eastPanel.add(content_panel);
	}
	
	/**
	 * 设置历史报警已处理
	 * @param bus_id
	 */
	private boolean setAlarmHandled(String bus_id){
		boolean rslt = LzzDataRequestMgr.setPrevAlarmHandled(bus_id);
		if(!rslt) return rslt;
		
		setLocalAlarmHandled(bus_id);
		
		return rslt;
	}
	/**
	 * 设置本地的警告数据已处理
	 * @param bus_id
	 */
	private void setLocalAlarmHandled(String bus_id){
		LzzDataCache.removeAlarmBus(bus_id);
		setNormalBusLabel(bus_id);
	}
	
	/**
	 * 获取公交节点路径字符串
	 * @param bus_id
	 * @return
	 */
	private String getBusTreeNodePath(String bus_id) {
		TreeNode[] nodes = busNodes.get(bus_id).getPath();
		String rslt = "";
		for(int i=0; i<nodes.length; i++){
			rslt += nodes[i].toString();
			
			if(i!=nodes.length-1){
				 rslt += "-";
			}
		}
		
		return rslt;
	}

	/**
	 * 选中公交
	 * @param bus_id
	 * @param label
	 */
	protected void selectBus(String bus_id, LzzImgLabel label) {
		//恢复上次选择的公交控件样式
		recovreSelectedBusUI(curBusId);
		if(null==label){
			label = busLabels.get(bus_id);
		}
		prevFont = label.getFont();
		
		label.setFont(busSelectedFont);
		curBusId = bus_id;
		
		//获取是否忽略警告标志
		boolean is_ignore_a = LzzDataRequestMgr.getBusAlarmIgnore(bus_id);
		ignoreCheck.setSelected(is_ignore_a);
	}
	
	/**
	 * 取消选择公交
	 */
	protected void cancelSelectBus() {
		// TODO Auto-generated method stub
		recovreSelectedBusUI(curBusId);
		curBusId = null;
	}

	/**
	 * 恢复选中公交的样式
	 * @param bus_id
	 */
	private void recovreSelectedBusUI(String bus_id) {
		if(null==bus_id) return;
		JLabel label = getBusLabelById(bus_id);
		label.setFont(prevFont);
	}
	
	/**
	 * 获取公交右侧标签控件
	 * @param id 公交ID
	 * @return
	 */
	private JLabel getBusLabelById(String id){
		return busLabels.get(id);
	}

	/**
	 * 设置公交警告忽略
	 */
	protected void setBusAlermIgnore() {
		if(null==curBusId){
			JOptionPane.showMessageDialog(null, "请选择一辆公交车！");
			ignoreCheck.setSelected(!ignoreCheck.isSelected());
			return;
		}
		boolean flag = ignoreCheck.isSelected();
		boolean rslt = LzzDataRequestMgr.setBusAlarmIgnore(curBusId, flag);
		if(rslt){
			JOptionPane.showMessageDialog(null, "设置成功");
			//ignoreCheck.setSelected(!ignoreCheck.isSelected());
			
			if(flag){
				setLocalAlarmHandled(curBusId);
			}
			
		}else{
			JOptionPane.showMessageDialog(null, "设置失败");
		}
	}

	/**
	 * 实现预览回调数据
	 * @author Administrator
	 *
	 */
	class FRealDataCallBack implements HCNetSDK.FRealDataCallBack_V30{    
        //预览回调
        public void invoke(NativeLong lRealHandle, int dwDataType, ByteByReference pBuffer, int dwBufSize, Pointer pUser){
            HWND hwnd = new HWND(Native.getComponentPointer(panelRealplay));
            switch (dwDataType){
                case HCNetSDK.NET_DVR_SYSHEAD: //系统头
                    if (!playControl.PlayM4_GetPort(m_lPort)){ //获取播放库未使用的通道号
                        break;
                    }

                    if (dwBufSize > 0){
                        if (!playControl.PlayM4_SetStreamOpenMode(m_lPort.getValue(), PlayCtrl.STREAME_REALTIME)){  //设置实时流播放模式
                            break;
                        }

                        if (!playControl.PlayM4_OpenStream(m_lPort.getValue(), pBuffer, dwBufSize, 1024 * 1024)){ //打开流接口
                            break;
                        }

                        if (!playControl.PlayM4_Play(m_lPort.getValue(), hwnd)){ //播放开始
                            break;
                        }
                    }
                case HCNetSDK.NET_DVR_STREAMDATA:   //码流数据
                    if ((dwBufSize > 0) && (m_lPort.getValue().intValue() != -1)){
                        if (!playControl.PlayM4_InputData(m_lPort.getValue(), pBuffer, dwBufSize)){  //输入流数据
                            break;
                        }
                    }
            }
        }
    }
}
