package com.lzz.swing.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXDatePicker;
import org.jvnet.lafwidget.preview.PreviewPainter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

import com.lzz.swing.component.LzzLoadingGlassPane;
import com.lzz.swing.component.LzzBrowserPanel;
import com.lzz.swing.component.LzzFunctionMenus;
import com.lzz.swing.component.LzzImageIcon;
import com.lzz.swing.component.LzzImgLabel;
import com.lzz.swing.component.LzzRButton;
import com.lzz.swing.component.LzzRoundPanel;
import com.lzz.swing.component.LzzTextField;
import com.lzz.swing.component.LzzTimerDialog;
import com.lzz.swing.component.LzzTreeCellRenderer;
import com.lzz.swing.mgr.LzzBusMgr;
import com.lzz.swing.mgr.LzzColorFontMgr;
import com.lzz.swing.mgr.LzzDataCache;
import com.lzz.swing.mgr.LzzDataRequestMgr;
import com.lzz.swing.mgr.LzzFrameMgr;
import com.lzz.swing.mgr.LzzImgMgr;
import com.lzz.swing.mgr.LzzLogMgr;
import com.lzz.swing.mgr.LzzMenuMgr;
import com.lzz.swing.mgr.LzzUserMgr;
import com.lzz.swing.util.LzzDateUtil;
import com.lzz.swing.util.LzzFontUtil;
import com.lzz.swing.util.LzzFrameUtil;
import com.lzz.swing.util.LzzImageUtil;
import com.lzz.swing.util.LzzProperties;
import com.lzz.swing.util.LzzRequest;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.examples.win32.W32API.HWND;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.NativeLongByReference;

public class LzzIndexFrm extends LzzBaseStyleFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2445298015243567503L;

	private JPanel contentPane;
	private JDesktopPane desktop = new JDesktopPane();
	//遮罩层
	private LzzLoadingGlassPane glasspane;
	
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
	private LzzBrowserPanel webBrowserPanel;
	
	/**
	 * 底部提示显示控件
	 */
	private JLabel hintLabel;
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
	
	//private int initWidth = 1366; //窗口初始化宽度
	//private int initHeight = 768; //窗口初始化高度
	private double initWidth = LzzFrameMgr.getScreenWidth();
	private double initHeight = LzzFrameMgr.getScreenHeightWithoutTaskBar();
	
	private Color bkColor = new Color(138, 184, 233);//背景色
	private Color bkColorBlack = new Color(36, 40, 44);//背景色
	private List<JPanel> menuPanels = new ArrayList<JPanel>();//菜单对象列表
	private String welcomeStr = "欢迎使用公交车安全预警联网平台";
	private String sysName = "公交车安全预警联网平台";
	private String img_url_normalStatus = "/images/normal.png";
	private String img_url_alarmStatus = "/images/alarm.png";
	private String img_url_online = "/images/online.png";
	private String img_url_offline = "/images/offline.png";
	private String img_url_logo = "/images/syslogo.png";
	private String img_url_alarm = "/images/alarm.png";
	private String img_url_alarm_hl = "/images/alarm-hl.png";
	private String img_url_small = "/images/login.png";
	private String img_url_bk = "/images/bk.jpg";
	private String img_url_btnBkGray = "/images/btnBkGray.png";
	private String img_url_min = "/images/min.png";
	private String img_url_close = "/images/exit.png";
	private String img_url_max = "/images/max.png";
	private String img_url_resize = "/images/preSize.png";
	private String img_url_head = "/images/head.png";
	private String uploadImg = "/images/upload.png";
	private ImageIcon img = new ImageIcon(LzzImageUtil.getResource(img_url_bk));//这是背景图片
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LzzIndexFrm frame = new LzzIndexFrm();
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
	public LzzIndexFrm() {
		setTitle(sysName);
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		this.setBounds(0, 0, (int)initWidth, (int)initHeight);
		LzzFontUtil.setDefaultFont();
		//setIconImage(Toolkit.getDefaultToolkit().getImage(LzzImageUtil.getResource("/images/logo.png")));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		setContentPane(contentPane);
		
		//遮罩层
		//glasspane = new LzzLoadingPane();
		//setGlassPane(glasspane);
		
		glasspane = new LzzLoadingGlassPane();
        setGlassPane(glasspane);
		
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
		//createEastPanel();
		//contentPane.add(eastPanel, BorderLayout.EAST);
		
		//创建窗口最大化最小化监听器
		addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent state) {
				// TODO Auto-generated method stub
				if(state.getNewState() == 1 || state.getNewState() == 7) {
                    //System.out.println("窗口最小化");
                }else if(state.getNewState() == 0) {
                    //System.out.println("窗口恢复到初始状态");
                	showLoading(0.1);
                }else if(state.getNewState() == 6) {
                    //System.out.println("窗口最大化");
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
		//setBackgroundImage(img_url_bk, this.getWidth(), this.getHeight());
		//((JPanel)contentPane).setOpaque(false);
		
        //首页时间刷新定时器
        setIndexTimeRefreshTimer();
        //公交树刷新定时器
        refreshBusTreeTimer();
        //日志获取定时器
        setLogRefreshTimer();
        //客户端连接状态定时器
        setServerOnLineTimer();
        
        Timer timer = new Timer();
		timer.schedule(new TimerTask() {
	        public void run() {
	        	showPanel("首页");
	        	selectBus(curBusId);
	        	this.cancel();
	        }
		}, 100);
		
		JPanel transitPanel = new JPanel();
		transitPanel.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		centerCardPanel.add(transitPanel, "过渡");
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
	
	JSONObject origBusTreeData = null;
	/**
	 * 公交树刷新定时器
	 */
	protected void refreshBusTreeTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        public void run() {
		        	JSONObject bus_datas = LzzDataRequestMgr.getUserMgrBusList();
		        	if(null==origBusTreeData){
		        		origBusTreeData = bus_datas;
		        		return;
		        	}
		        	
		        	String orig_str = origBusTreeData.toString();
		        	String cur_str = bus_datas.toString();
		        	System.out.println(!orig_str.equals(cur_str));
		        	if(!orig_str.equals(cur_str)){
		        		origBusTreeData = bus_datas;
		        		reloadBusTreePanel();
		        	}
		        }
		}, 1000, 5000);
	}
	
	protected void setLogRefreshTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        public void run() {
		        	JSONArray rslt = new JSONArray();
					try {
						rslt = LzzLogMgr.getUnhandledSensorAlarmData();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(rslt.size()>0){
						findAlarmData(rslt);
					}else{
						//System.out.println("没有报警数据");
					}
		        }
		}, 1000, 2000);
	}
	
	private int linkFailedTimes = 0;
	/**
	 * 设置客户端连接状态刷新定时器
	 */
	protected void setServerOnLineTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				boolean rslt = LzzDataRequestMgr.urlValidCheck();
				if(rslt){
					linkFailedTimes = 0;
					onlineStatusLabel.changeIcon(img_url_online);
				}else{
					linkFailedTimes ++;
				}
				
				if(linkFailedTimes>=3){
					//服务器连接失败
					serverlinkFailed();
				}
			}
		}, 1000, 2000);
	}
	
	private void serverlinkFailed(){
		onlineStatusLabel.changeIcon(img_url_offline);
		
		int exi = JOptionPane.showConfirmDialog (null, "服务器连接失败！需重新登录，是否现在退出！", "离线提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (exi == JOptionPane.YES_OPTION){
        	dispose();
			System.exit(0);
        }
	}
	
	protected void findAlarmData(JSONArray rslt) {
		startAlarmShine();
		
		List<String> bus_nums = new ArrayList<String>();
		for(int i=rslt.size()-1; i>=0; i--){
			JSONObject obj = rslt.getJSONObject(i);
			
			addAlarmLogData(obj);
			
			if(!bus_nums.contains(obj.getString("busNum"))){
				bus_nums.add(obj.getString("busNum"));
			}
		}
		
		checkAlarmCount();
		
		String first_alarm_bus = bus_nums.get(0);
		int exi = JOptionPane.showConfirmDialog (null, "车辆 " + first_alarm_bus + "发生报警，是否现在查看处理！", "报警提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		String bus_id = LzzDataCache.getBusId(first_alarm_bus);
        if (exi == JOptionPane.YES_OPTION){
        	if(null!=bus_id){
        		selectBus(bus_id);
        		showPanel("报警处理");
        		boolean set_suc = centerPanelAlarmHandleRight.setBusId(bus_id);
        		if(set_suc){
        			centerPanelAlarmHandleRight.preview();
        		}
        	}
        }else{
        	if(null!=bus_id){
        		selectBus(bus_id);
        	}
        	showPanel("报警处理");
        }
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
		synchronized (indexAlarmDataTable) {
			indexAlarmDataTablemodel.insertRow(0, new Object[]{
					obj.getString("createTime"),
					obj.getString("id"),
					obj.getString("busNum"),
					obj.getString("company"), 
					obj.getString("statusVal")});
		}
	}
	
	Map<String, List<JLabel>> alarmLogLabels = new Hashtable<String, List<JLabel>>();
	/**
	 * 添加日志区滚动日志
	 * @param log_label
	 */
	private void addLogScrolldata(final String bus_id, final JLabel log_label){
		/*List<JLabel> labels = alarmLogLabels.get(bus_id);
		if(null==labels) labels = new ArrayList<JLabel>();
		labels.add(log_label);
		alarmLogLabels.put(bus_id, labels);
		
		logRefreshContentPanel.add(log_label);
    	logRefreshContentPanel.updateUI();
    	if(!isStopScroll){
    		logScrollPanel.getViewport().setViewPosition(new Point(0, logScrollPanel.getVerticalScrollBar().getMaximum()));
    	}
    	log_label.addMouseListener(new MouseAdapter(){
		      public void mouseClicked(MouseEvent e){
		    	  if (e.getButton()==MouseEvent.BUTTON1
		    			  && e.getClickCount() >= 2){
		    		   selectBus(bus_id);
		    	  }
		    	  
		    	  if (e.getButton()==MouseEvent.BUTTON3) {
    				  //弹出右键菜单 
    				  final JPopupMenu menu = new JPopupMenu("设置该车辆报警已处理");  
    				  JMenuItem menu_item = new JMenuItem("设置该车辆报警已处理");
    				  menu.add(menu_item);
    				  menu.show(log_label, e.getPoint().x, e.getPoint().y);
    				  menu_item.addActionListener(new ActionListener() {
    					  @Override
    					  public void actionPerformed(ActionEvent e) {
    						  boolean rslt = setBusAlarmHandled(bus_id);
		    				  if(rslt){
		    					  JOptionPane.showMessageDialog(null, "处理成功");
		    				  }else{
		    					  JOptionPane.showMessageDialog(null, "设置失败");
		    				  }
    					  }
    				  });
		    	  }
		      }
    	});*/
		
		
		
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
	 * 将报警日志中关于该车辆的日志移除
	 * @param bus_id
	 */
	private void removeAlarmBusLabel(String bus_id){
		List<JLabel> labels = alarmLogLabels.get(bus_id);
		if(null==labels) return;
		for(JLabel label : labels){
			logRefreshContentPanel.remove(label);
		}
		alarmLogLabels.remove(bus_id);
		logRefreshContentPanel.updateUI();
	}

	/*public void paint(Graphics g){
		if(null==labelBk){
			labelBk = new JLabel(img);//将背景图放在标签里
			((ImageIcon)labelBk.getIcon()).setImage(((ImageIcon)labelBk.getIcon()).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
			this.getLayeredPane().add(labelBk, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里
		}else{
			((ImageIcon)labelBk.getIcon()).setImage(((ImageIcon)labelBk.getIcon()).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		}
    	labelBk.setBounds(0, 0, this.getWidth(), this.getHeight());//设置背景标签的位置
    	
		super.paint(g);
	}*/
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

	private LzzImgLabel busAlarmIcon = null;
	private JLabel busAlarmCount = null;
	/**
	 * 创建顶部控件
	 */
	private void createNorthPanel() {
		// TODO Auto-generated method stub
		northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 50));
		northPanel.setBackground(LzzColorFontMgr.smUIHeadBkColor);
		LzzFrameUtil.hideFrameTitleBorder(this);
		LzzFrameUtil.setDragable(northPanel, this);
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		
		northPanel.add(Box.createHorizontalStrut(10));
		
		//添加系统名称
		JLabel sys_name_l = new JLabel(welcomeStr);
		sys_name_l.setFont(new Font("Dialog", 1, 30));
		sys_name_l.setForeground(LzzColorFontMgr.smTextColor);
		northPanel.add(sys_name_l);
		//所有组件靠右排放，先添加左侧空白部分
		northPanel.add(Box.createHorizontalGlue());
		
		busAlarmIcon = new LzzImgLabel(img_url_normalStatus, 30, 30);
		busAlarmIcon.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.out.println("click" + isShining);
				if(isShining){
					showPanel("报警处理");
				}
			}
		});
		northPanel.add(busAlarmIcon);
		
		//添加水平间距
		northPanel.add(Box.createHorizontalStrut(10));

		busAlarmCount = new JLabel("(0)");
		busAlarmCount.setFont(LzzColorFontMgr.smTextFont);
		busAlarmCount.setForeground(Color.RED);
		northPanel.add(busAlarmCount);
		
		//添加水平间距
		northPanel.add(Box.createHorizontalStrut(15));
		
		//JLabel head_label = new LzzImgLabel(img_url_head, 30, 30, LzzUserMgr.userName);
		String role_name = LzzUserMgr.roleName
				.replaceAll("\\[\"", "")
				.replaceAll("\"]", "")
				.replaceAll(",", "")
				.replaceAll("普通用户", "");
		JLabel head_label = new JLabel(LzzUserMgr.userName + "(" + role_name + ")");
		head_label.setForeground(LzzColorFontMgr.smTextColor);
		northPanel.add(head_label);
		
		northPanel.add(Box.createHorizontalStrut(15));
		
		//添加最大化最小化关闭按钮
		LzzFunctionMenus func_menu = new LzzFunctionMenus(false);
		func_menu.bindParentFrame(this);
		northPanel.add(func_menu);
	}

	/**
	 * 是否继续报警标志
	 */
	private boolean isShining = false;
	/**
	 * 当前是否是高亮报警图标状态
	 */
	private boolean isHlIcon = false;
	private Timer shineTimer = null;
	/**
	 * 开始报警闪烁
	 */
	private void startAlarmShine(){
		if(isShining) return;
		isShining = true;
		if(null!=shineTimer) shineTimer.cancel();
		shineTimer = new Timer();
		shineTimer.schedule(new TimerTask() {
	        public void run() {
    			if(isHlIcon){
    				//设置正常报警图标
    				busAlarmIcon.changeIcon(img_url_alarm);
    				busAlarmIcon.validate();
    				isHlIcon = false;
    			}else{
    				//设置高亮报警图标
    				busAlarmIcon.changeIcon(img_url_alarm_hl);
    				busAlarmIcon.validate();
    				isHlIcon = true;
    			}
	        }
		}, 500 , 500);
	}
	
	/**
	 * 停止报警闪烁
	 */
	private void stopBusAlarmShine(){
		shineTimer.cancel();
		busAlarmIcon.changeIcon(img_url_normalStatus);
		isShining = false;
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
	 * 公交搜索框
	 */
	LzzTextField search = new LzzTextField("/images/search.png"){
		@Override
		public void iconClickFunction() {
			
		};
	};
	/**
	 * 创建左侧控件
	 */
	private void createWestPanel() {
		// TODO Auto-generated method stub
		westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(200, 0));
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		
		//系统logo
		westPanel.add(Box.createVerticalStrut(10));
		LzzImgLabel logo = new LzzImgLabel(img_url_logo, 190, 71);
		westPanel.add(logo);
		
		westPanel.add(Box.createVerticalStrut(10));
		
		//公交搜索框
		search.setPreferredSize(new Dimension(300, 40));
		search.setMaximumSize(new Dimension(400, 40));
		search.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                int k = e.getKeyCode();
                if(k == e.VK_ENTER){
                	String bus_num = search.getText();
                	System.out.println(bus_num);
                	//DefaultMutableTreeNode node = busNodes.get(bus_num);
                	
                	DefaultMutableTreeNode node = searchNode(bus_num);
                    if (node != null)  
                    {  
                        TreeNode[] nodes = busTreeModel.getPathToRoot(node);  
                        TreePath path = new TreePath(nodes);  
                        busTree.scrollPathToVisible(path);  
                        busTree.setSelectionPath(path);  
                    }  
                    else  
                    {  
                        System.out.println("Node with string "  
                                + bus_num + " not found");  
                    }
                	//busTree.setSelectionPath(new TreePath(node.getParent()));
                	//expandAllNode(busTree, new TreePath(node.getParent()), true);
                }
            }
		});
		
		westPanel.add(search);
		westPanel.add(Box.createVerticalStrut(10));
		
		//创建公交树状控件
		westPanelTree = new JPanel();
		westPanelTree.setLayout(new BoxLayout(westPanelTree, BoxLayout.Y_AXIS));
		westPanelTree.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		JPanel treePanel = createBusTreePanel();
		treePanel.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		treePanel.add(Box.createVerticalGlue());
		westPanelTree.add(treePanel);
		
		westPanel.add(westPanelTree);
		westPanel.add(Box.createHorizontalStrut(5));
		westPanel.setOpaque(false);
	}
	
	public DefaultMutableTreeNode searchNode(String nodeStr){  
        DefaultMutableTreeNode node = null;  
        Enumeration e = top.breadthFirstEnumeration();  
        while (e.hasMoreElements())
        {  
            node = (DefaultMutableTreeNode) e.nextElement();  
            if (node.getUserObject().toString().contains(nodeStr))  {  
                return node;  
            }  
        }  
        return null;  
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
				centerPanelAlarmHandleRight.preview();
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
	
	/**
	 * 监控功能UI对象
	 */
	private JPanel monitorPanel;
	
	/**
	 * 日志管理功能UI对象
	 */
	private JPanel logMgrPanel;
	
	/**
	 * 菜单UI对象
	 */
	private JPanel sysMenuPanel;
	
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
	 * 报警处理功能UI对象
	 */
	private JPanel alarmHandlePanel;
	/**
	 * 信息查询-基础信息功能UI对象
	 */
	private JPanel infoQueryPanel_BaseInfo = new JPanel();
	/**
	 * 信息查询-实时信息功能UI对象
	 */
	private JPanel infoQueryPanel_CurInfo = new JPanel();
	/**
	 * 信息查询-实时信息功能UI对象
	 */
	private JPanel infoQueryPanel_HistoryInfo = new JPanel();
	
	/**
	 * 中部cardlayout布局对象
	 */
	private CardLayout centerCardLayout = new CardLayout();
	
	/**
	 * 中部选项卡布局面板
	 */
	private JPanel centerCardPanel;
	
	/**
	 * 创建中间控件对象
	 */
	private void createCenterPanel() {
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		//设置选项卡面板布局
		centerCardPanel = new JPanel();
		centerCardPanel.setLayout(centerCardLayout);
		
		//创建菜单
		createSysMenu();
		centerPanel.add(sysMenuPanel, BorderLayout.NORTH);
		
		//创建首页panel
		//createCenterPanelIndex();
		createCenterPanelIndexMap();
		
		//创建报警处理界面
		createCenterPanelAlarmHandle();
		
		//创建信息查询相关面板
		createCenterPanelInfoQuery();
		
		//创建故障报修相关面板
		createCenterPanelRepair();
		
		//创建设置功能UI
		createCenterPanelSystemSet();
		//centerPanel.add(setPanel, "设置");
		
		//创建数据导入功能UI
		createImportPanel();
		
		//创建关于我们功能UI
		createAboutUsPanel();
		
		centerPanel.add(centerCardPanel, BorderLayout.CENTER);
		centerPanel.setOpaque(false);
	}

	/**
	 * 创建信息查询相关功能UI
	 */
	public void createCenterPanelInfoQuery() {
		createCenterPanelInfoQuery_BaseInfo();
		createCenterPanelInfoQuery_CurInfo();
		createCenterPanelInfoQuery_HistoryInfo();
	}

	private JTable base_info = null;
	private DefaultTableModel baseInfoTablemodel = null;
	/**
	 * 创建信息查询-基础信息功能UI
	 */
	public void createCenterPanelInfoQuery_BaseInfo() {
		infoQueryPanel_BaseInfo.setLayout(new BorderLayout());
		
		final Object[] columnNames = {"所属公司", "线路", "车牌号", "传感器数量", "一键应急开关"};
		Object[][] rowData = {};
		baseInfoTablemodel = new DefaultTableModel(rowData, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		base_info = new JTable(baseInfoTablemodel);
		base_info.getTableHeader().setFont(LzzColorFontMgr.smTextFontMiddle);
		base_info.setFont(LzzColorFontMgr.smTextFontMiddle);
		base_info.setRowHeight(30);
		
		//设置内容居中
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		base_info.setDefaultRenderer(Object.class, r);
		
		JScrollPane pane1 = new JScrollPane (base_info);
		JPanel panel = new JPanel (new GridLayout(0, 1));
		panel.setPreferredSize (new Dimension (600,400));
		panel.setBackground (Color.black);
		panel.add (pane1);
		infoQueryPanel_BaseInfo.add(panel, BorderLayout.CENTER);
		
		centerCardPanel.add(infoQueryPanel_BaseInfo, "信息查询-基础信息");
	}
	
	private JPanel dataImportPanel = new JPanel();
	/**
	 * 创建数据导入功能UI
	 */
	private void createImportPanel() {
		dataImportPanel.setLayout(new BoxLayout(dataImportPanel, BoxLayout.Y_AXIS));
		dataImportPanel.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		JPanel inner = new JPanel();
		inner.setLayout(new BoxLayout(inner, BoxLayout.X_AXIS));
		inner.setAlignmentX(Component.LEFT_ALIGNMENT);
		inner.setMaximumSize(new Dimension(500, 50));
		inner.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		final LzzImgLabel arrange = new LzzImgLabel(uploadImg, 50, 50, "运营排班表导入");
		arrange.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		arrange.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				// 处理鼠标点击
				JFileChooser jfc=new JFileChooser();  
		        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        String saveType[] = {"xls"}; 
		        jfc.setFileFilter(new FileNameExtensionFilter("xls", saveType));
		        jfc.showDialog(new JLabel(), "选择");  
		        File file=jfc.getSelectedFile();
		        if(null!=file){
		        	System.out.println("文件:"+file.getAbsolutePath());
		        	JSONObject rslt = LzzBusMgr.importBusInfo(file);
		        	if(rslt.getBoolean("success")){
		        		JOptionPane.showMessageDialog(null, "导入成功!");
		        		reloadBusTreePanel();
		        	}else{
		        		JOptionPane.showMessageDialog(null, "导入失败：" + rslt.getString("errMsg"));
		        	}
		        }
			}
			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
				arrange.setBorder(BorderFactory.createLineBorder(Color.white, 2));
			}
			public void mouseExited(MouseEvent e) {
				// 处理鼠标离开
				arrange.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			}
			public void mousePressed(MouseEvent e) {
				// 处理鼠标按下
			}
			public void mouseReleased(MouseEvent e) {
				// 处理鼠标释放
			}
		});
		
		final LzzImgLabel camera = new LzzImgLabel(uploadImg, 50, 50, "摄像头关联数据导入");
		camera.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		camera.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				// 处理鼠标点击
				JFileChooser jfc=new JFileChooser();  
		        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        String saveType[] = {"xls"}; 
		        jfc.setFileFilter(new FileNameExtensionFilter("xls", saveType));
		        jfc.showDialog(new JLabel(), "选择");  
		        File file=jfc.getSelectedFile();
		        if(null!=file){
		        	System.out.println("文件:"+file.getAbsolutePath());
		        	JSONObject rslt = LzzBusMgr.importBusCamera(file);
		        	if(rslt.getBoolean("success")){
		        		JOptionPane.showMessageDialog(null, "导入成功!");
		        	}else{
		        		JOptionPane.showMessageDialog(null, "导入失败：" + rslt.getString("errMsg"));
		        	}
		        }
			}
			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
				camera.setBorder(BorderFactory.createLineBorder(Color.white, 2));
			}
			public void mouseExited(MouseEvent e) {
				// 处理鼠标离开
				camera.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			}
			public void mousePressed(MouseEvent e) {
				// 处理鼠标按下
			}
			public void mouseReleased(MouseEvent e) {
				// 处理鼠标释放
			}
		});
		
		
		inner.add(Box.createHorizontalStrut(10));
		inner.add(arrange);
		inner.add(Box.createHorizontalStrut(20));
		inner.add(camera);
		
		dataImportPanel.add(Box.createVerticalStrut(10));
		dataImportPanel.add(inner);
		
		centerCardPanel.add(dataImportPanel, "数据导入");
	}

	private JPanel aboutUsPanel = new JPanel();
	private String versionName = "公交车安全预警联网平台V1.0.0";
	private String company = "安徽芯核防务装备技术股份有限公司";
	private String address = "安徽省合肥市高新区望江西路5089号中科大先进技术研究院";
	private String telephone = "0551-65531720";
	private String webSite = "www.ahxhfw.com";
	/**
	 * 创建关于我们面板
	 */
	private void createAboutUsPanel() {
		aboutUsPanel.setLayout(new BoxLayout(aboutUsPanel, BoxLayout.Y_AXIS));
		aboutUsPanel.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		JPanel tmp = new JPanel();
		tmp.setLayout(new BoxLayout(tmp, BoxLayout.X_AXIS));
		tmp.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		JPanel aboutUsPanelInner = new JPanel();
		aboutUsPanelInner.setLayout(new BoxLayout(aboutUsPanelInner, BoxLayout.Y_AXIS));
		aboutUsPanelInner.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		JLabel version = new JLabel("软件版本：" + versionName + "");
		version.setFont(LzzColorFontMgr.smTextFont);
		
		JLabel com = new JLabel("开发单位：" + company);
		com.setFont(LzzColorFontMgr.smTextFont);
		
		JLabel add = new JLabel("地　　址：" + address);
		add.setFont(LzzColorFontMgr.smTextFont);
		
		JLabel tel = new JLabel("联系电话：" + telephone);
		tel.setFont(LzzColorFontMgr.smTextFont);
		
		JLabel site = new JLabel("网　　站：" + webSite);
		site.setFont(LzzColorFontMgr.smTextFont);
		
		aboutUsPanelInner.add(version);
		aboutUsPanelInner.add(Box.createVerticalStrut(5));
		aboutUsPanelInner.add(com);
		aboutUsPanelInner.add(Box.createVerticalStrut(5));
		aboutUsPanelInner.add(add);
		aboutUsPanelInner.add(Box.createVerticalStrut(5));
		aboutUsPanelInner.add(tel);
		aboutUsPanelInner.add(Box.createVerticalStrut(5));
		aboutUsPanelInner.add(site);
		
		tmp.add(Box.createHorizontalGlue());
		tmp.add(aboutUsPanelInner);
		tmp.add(Box.createHorizontalGlue());
		
		aboutUsPanel.add(Box.createVerticalStrut(100));
		aboutUsPanel.add(tmp);
		aboutUsPanel.add(Box.createVerticalGlue());
		
		centerCardPanel.add(aboutUsPanel, "关于软件");
	}

	private String curSelectedMenu = null;
	public void showPanel(String name){
		showLoading();
		String orig_name = curSelectedMenu;
		curSelectedMenu = name;
		
		//停止摄像头预览
		centerPanelAlarmHandleRight.stopPreview();
		curInfoCameraPanel.stopPreview();
		
		boolean refresh_success = refreshPanelData();
		
		if(refresh_success){
			centerCardLayout.show(centerCardPanel, name);
			curSelectedMenu = name;
			updatePathPanel(name);
			
			selectBus(curBusId);
		}else{
			curSelectedMenu = orig_name;
		}
		hideLoading();
	}
	
	boolean isCurInfoMapFirstLoaded = true;
	/**
	 * 切换菜单或者切换车辆时，刷新页面数据
	 */
	private boolean refreshPanelData() {
		switch(curSelectedMenu){
		case "首页":
			
			break;
		case "报警处理":
			break;
		case "信息查询-基础信息":
			if(!needCurBusCheck()){
				return false;
			}
			DefaultTableModel tableModel = (DefaultTableModel) base_info.getModel();
			if(tableModel.getRowCount()>0){
				tableModel.removeRow(0);
			}
			JSONObject base_info = LzzBusMgr.getBusBaseInfo(curBusId);
			if(null!=base_info){
				tableModel.addRow(new Object[]{
						base_info.getString("company"), 
						base_info.getString("line"), 
						base_info.getString("busNum"), 
						base_info.getString("sensorCount"),
						base_info.getString("driverFlag")});
			}
			break;
		case "信息查询-实时信息-传感器":
			if(!needCurBusCheck()){
				return false;
			}
			showLoading(1d);
			JSONArray sensors = LzzDataRequestMgr.getBusSensorStatus(curBusId);
			curInfoSensorInnerPanel.removeAll();
			for(int i=0; i<sensors.size(); i++){
				JSONObject sensor = sensors.getJSONObject(i);
				String name = sensor.getString("name");
				String status = sensor.getString("status");
				String status_img = "/images/sensorStatus-" + status + ".png";
				LzzImgLabel label = new LzzImgLabel(status_img, 20, 20, name);
				label.setMaximumSize(new Dimension(100, 30));
				label.setPreferredSize(new Dimension(200, 30));
				curInfoSensorInnerPanel.add(label);
			}
			break;
		case "信息查询-实时信息-电子地图":
			if(!needCurBusCheck()){
				return false;
			}
			showLoading(1d);
			String bus_num = LzzDataCache.getBusNum(curBusId);
			curInfoMapInnerNorthPanelBusNum.setText(bus_num);
			
			if(isCurInfoMapFirstLoaded){
				isCurInfoMapFirstLoaded = false;
				selectBus(curBusId);
			}
			break;
		case "信息查询-实时信息-视频监控":
			if(!needCurBusCheck()){
				return false;
			}
			boolean set_suc = curInfoCameraPanel.setBusId(curBusId);
			if(set_suc){
				curInfoCameraPanel.preview();
       	 	}
			break;
		case "信息查询-历史信息":
			historyInfoPanel.search();
			break;
		case "故障报修":
			repairInfoPanel.search();
			break;
		case "系统设置-人员管理":
			userMgrPanel.searchData();
			break;
		case "系统设置-公交管理":
			busMgrPanel.searchData();
			break;
		case "数据导入":
			
			break;
		case "关于软件":
			
			break;
		}
		
		return true;
	}

	private boolean needCurBusCheck() {
		if(null==curBusId){
			JOptionPane.showMessageDialog(null, "请选择一辆公交车！");
			return false;
		}
		return true;
	}

	/**
	 * 创建信息查询-实时信息功能UI
	 */
	private void createCenterPanelInfoQuery_CurInfo() {
		//创建 信息查询-实时信息-传感器 功能UI
		createCenterPanelInfoQuery_CurInfoSensor();
		//创建 信息查询-实时信息-电子地图 功能UI
		createCenterPanelInfoQuery_CurInfoMap();
		//创建 信息查询-实时信息-视频监控 功能UI
		createCenterPanelInfoQuery_CurInfoCamera();
	}
	
	private JPanel curInfoSensorPanel = new JPanel();
	private JPanel curInfoSensorSignalPanel = new JPanel();
	private JPanel curInfoSensorInnerPanel = new JPanel();
	/**
	 * 创建 信息查询-实时信息-传感器 功能UI
	 */
	private void createCenterPanelInfoQuery_CurInfoSensor() {
		curInfoSensorPanel.setLayout(new BorderLayout());
		curInfoSensorPanel.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		curInfoSensorInnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		curInfoSensorInnerPanel.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		createSensorStatusSignalPanel();
		
		curInfoSensorPanel.add(curInfoSensorSignalPanel, BorderLayout.NORTH);
		curInfoSensorPanel.add(curInfoSensorInnerPanel, BorderLayout.CENTER);
		
		centerCardPanel.add(curInfoSensorPanel, "信息查询-实时信息-传感器");
	}

	/**
	 * 创建传感器状态标识说明区域
	 */
	private void createSensorStatusSignalPanel() {
		//符号标志说明区域
		curInfoSensorSignalPanel.setLayout(new BoxLayout(curInfoSensorSignalPanel, BoxLayout.X_AXIS));
		String status_img1 = "/images/sensorStatus-1.png";
		String status_img2 = "/images/sensorStatus-2.png";
		String status_img3 = "/images/sensorStatus-3.png";
		String status_img4 = "/images/sensorStatus-4.png";
		
		LzzImgLabel signal_label1 = new LzzImgLabel(status_img1, 20, 20, "正常状态");
		signal_label1.setMaximumSize(new Dimension(100, 30));
		signal_label1.setPreferredSize(new Dimension(200, 30));
		LzzImgLabel signal_label2 = new LzzImgLabel(status_img2, 20, 20, "报警状态");
		signal_label2.setMaximumSize(new Dimension(100, 30));
		signal_label2.setPreferredSize(new Dimension(200, 30));
		LzzImgLabel signal_label3 = new LzzImgLabel(status_img3, 20, 20, "故障状态");
		signal_label3.setMaximumSize(new Dimension(100, 30));
		signal_label3.setPreferredSize(new Dimension(200, 30));
		LzzImgLabel signal_label4 = new LzzImgLabel(status_img4, 20, 20, "未安装状态");
		signal_label4.setMaximumSize(new Dimension(100, 30));
		signal_label4.setPreferredSize(new Dimension(200, 30));
		
		curInfoSensorSignalPanel.add(Box.createHorizontalStrut(20));
		curInfoSensorSignalPanel.add(signal_label1);
		curInfoSensorSignalPanel.add(signal_label2);
		curInfoSensorSignalPanel.add(signal_label3);
		curInfoSensorSignalPanel.add(signal_label4);
	}

	private JPanel curInfoMapPanel = new JPanel();
	private JPanel curInfoMapInnerNorthPanel = new JPanel();
	private JLabel curInfoMapInnerNorthPanelBusNum = new JLabel();
	private LzzBrowserPanel curInfoMapInnerCenterPanel;
	/**
	 * 创建 信息查询-实时信息-电子地图 功能UI
	 */
	private void createCenterPanelInfoQuery_CurInfoMap() {
		curInfoMapPanel.setLayout(new BorderLayout());
		curInfoMapPanel.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		curInfoMapInnerNorthPanel.setLayout(new BoxLayout(curInfoMapInnerNorthPanel, BoxLayout.X_AXIS));
		curInfoMapInnerNorthPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		curInfoMapInnerNorthPanel.setBackground(LzzColorFontMgr.smUITextBkColor);
		String bus_num = LzzDataCache.getBusNum(curBusId);
		curInfoMapInnerNorthPanelBusNum.setFont(LzzColorFontMgr.smTextFont);
		curInfoMapInnerNorthPanelBusNum.setText(bus_num);
		JLabel label = new JLabel("当前选中车辆:");
		label.setFont(LzzColorFontMgr.smTextFont);
		curInfoMapInnerNorthPanel.add(label);
		curInfoMapInnerNorthPanel.add(curInfoMapInnerNorthPanelBusNum);
		
		curInfoMapInnerCenterPanel = new LzzBrowserPanel(
				LzzProperties.getDataRequestUrl() 
				+ "?sessionId=" + LzzUserMgr.userSessionId
				+ "&showAll=1");
		
		curInfoMapPanel.add(curInfoMapInnerNorthPanel, BorderLayout.NORTH);
		curInfoMapPanel.add(curInfoMapInnerCenterPanel, BorderLayout.CENTER);
		
		centerCardPanel.add(curInfoMapPanel, "信息查询-实时信息-电子地图");
	}
	
	private LzzCameraPanel curInfoCameraPanel = null;
	/**
	 * 创建 信息查询-实时信息-视频监控 面板
	 */
	private void createCenterPanelInfoQuery_CurInfoCamera(){
		curInfoCameraPanel = new LzzCameraPanel(curBusId, this);
		centerCardPanel.add(curInfoCameraPanel, "信息查询-实时信息-视频监控");
	}
	
	private LzzHistoryInfoPanel historyInfoPanel;
	
	/**
	 * 创建 信息查询-历史信息 功能UI
	 */
	private void createCenterPanelInfoQuery_HistoryInfo() {
		historyInfoPanel = new LzzHistoryInfoPanel(this);
		centerCardPanel.add(historyInfoPanel, "信息查询-历史信息");
	}
	
	private LzzRepairInfoPanel repairInfoPanel;
	/**
	 * 创建 故障报修 面板
	 */
	private void createCenterPanelRepair() {
		repairInfoPanel = new LzzRepairInfoPanel(this);
		repairInfoPanel.getTable().addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                	int row = ((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置 
                	String car_num = (String)(repairInfoPanel.getTable().getModel().getValueAt(row, 3)); //获得点击单元格数据 
                	String bus_id = LzzDataCache.getBusId(car_num);
                     if(null!=bus_id){
                    	 selectBus(bus_id);
                    	 showPanel("信息查询-实时信息-传感器");
                     }
                }
            }
		});
		centerCardPanel.add(repairInfoPanel, "故障报修");
	}
	
	private JPanel pathPanel = null;
	private JPanel centerPanelAlarmHandleContents = new JPanel();
	/**
	 * 显示首页panel
	 */
	public void createCenterPanelAlarmHandle() {
		alarmHandlePanel = new JPanel();
		alarmHandlePanel.setLayout(new BorderLayout());
		
		createCenterPanelAlarmHandleContents();
		alarmHandlePanel.add(centerPanelAlarmHandleContents, BorderLayout.CENTER);
		
		centerCardPanel.add(alarmHandlePanel, "报警处理");
	}
	
	private JPanel indexMapPanel = new JPanel();
	private LzzBrowserPanel indexMapInnerCenterPanel;
	/**
	 * 创建 首页 功能UI
	 */
	private void createCenterPanelIndexMap() {
		indexMapPanel.setLayout(new BorderLayout());
		indexMapPanel.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		indexMapInnerCenterPanel = new LzzBrowserPanel(
				LzzProperties.getDataRequestUrl() 
				+ "?sessionId=" + LzzUserMgr.userSessionId
				+ "&showAll=1");
		
		indexMapPanel.add(indexMapInnerCenterPanel, BorderLayout.CENTER);
		
		centerCardPanel.add(indexMapPanel, "首页");
	}
	
	private JPanel centerPanelAlarmHandleLeft = new JPanel();
	private LzzCameraPanel centerPanelAlarmHandleRight = null;
	/**
	 * 创建首页内容UI
	 */
	private void createCenterPanelAlarmHandleContents() {
		centerPanelAlarmHandleContents.setLayout(new GridLayout(1, 2));
		centerPanelAlarmHandleContents.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		createCenterPanelAlarmHandleLeft();
		createCenterPanelAlarmHandleRight();
		
		centerPanelAlarmHandleContents.add(centerPanelAlarmHandleLeft, BorderLayout.WEST);
		centerPanelAlarmHandleContents.add(centerPanelAlarmHandleRight, BorderLayout.CENTER);
	}

	final JCheckBox allCarCheck  = new JCheckBox("所有车辆");
	final JCheckBox alarmCarCheck  = new JCheckBox("报警车辆");
	/**
	 * 创建首页内容左侧面板
	 */
	private void createCenterPanelAlarmHandleLeft() {
		centerPanelAlarmHandleLeft.setLayout(new BorderLayout());
		centerPanelAlarmHandleLeft.setBackground(LzzColorFontMgr.smUIHeadBkColor);
		
		JPanel centerPanelIndexLeft_func = new JPanel();
		centerPanelIndexLeft_func.setLayout(new BoxLayout(centerPanelIndexLeft_func, BoxLayout.Y_AXIS));
		JPanel centerPanelIndexLeft_func_inner = new JPanel();
		centerPanelIndexLeft_func_inner.setLayout(new BoxLayout(centerPanelIndexLeft_func_inner, BoxLayout.X_AXIS));
		centerPanelIndexLeft_func.add(centerPanelIndexLeft_func_inner);
		
		JLabel map_title = new JLabel("电子地图:");
		map_title.setFont(LzzColorFontMgr.smTextFont);
		centerPanelIndexLeft_func_inner.add(map_title);
		
		LzzImgLabel reset = new LzzImgLabel(LzzImgMgr.img_url_btnBk, 50, 18, "复位");
		reset.setHorizontalTextPosition(JLabel.CENTER);
		//centerPanelIndexLeft_func_inner.add(reset);
		
		allCarCheck.setFont(LzzColorFontMgr.smTextFont);
		allCarCheck.setSelected(true);
		allCarCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean is_checked = allCarCheck.isSelected();
				if(is_checked){
					setAllBusChecked();
				}
			}
		});
		
		alarmCarCheck.setFont(LzzColorFontMgr.smTextFont);
		alarmCarCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean is_checked = alarmCarCheck.isSelected();
				if(is_checked){
					setAlarmBusChecked();
				}
			}
		});
		
		centerPanelIndexLeft_func_inner.add(allCarCheck);
		centerPanelIndexLeft_func_inner.add(alarmCarCheck);
		centerPanelIndexLeft_func_inner.add(Box.createHorizontalGlue());
		centerPanelAlarmHandleLeft.add(centerPanelIndexLeft_func, BorderLayout.NORTH);
		
		//地图组件
		webBrowserPanel = new LzzBrowserPanel(
				LzzProperties.getDataRequestUrl() 
				+ "?sessionId=" + LzzUserMgr.userSessionId
				+ "&opr=selectBus"
				+ "&value=" + curBusId
				+ "&sign=" + System.currentTimeMillis());
		//webBrowserPanel.setLayout(new BoxLayout(webBrowserPanel, BoxLayout.Y_AXIS));
		centerPanelAlarmHandleLeft.add(webBrowserPanel, BorderLayout.CENTER);
		
		//创建传感器数据刷新面板
		createLogRefreshPanel();
		centerPanelAlarmHandleLeft.add(logRefreshPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * 设置所有车辆勾选，并请求
	 */
	private void setAllBusChecked(){
		showLoading(2d);
		LzzDataRequestMgr.addUserCheckAllBusOpr();
		alarmCarCheck.setSelected(false);
	}
	
	/**
	 * 设置报警车辆勾选，并请求
	 */
	private void setAlarmBusChecked(){
		showLoading(2d);
		LzzDataRequestMgr.addUserCheckAlarmBusOpr();
		allCarCheck.setSelected(false);
	}
	
	private boolean isLoading = false;
	/**
	 * 显示加载中遮罩
	 * @param timedur 显示时长
	 */
	private void showLoading(Double timedur){
		glasspane.setVisible(true);
		isLoading = true;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
	        public void run() {
	        	glasspane.setVisible(false);
	        	this.cancel();
	        }
		}, Long.valueOf(((long)(Float.valueOf(timedur+"")*1000))+""));
	}
	
	/**
	 * 显示加载中遮罩
	 */
	public void showLoading(){
		glasspane.setVisible(true);
		isLoading = true;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
	        public void run() {
	        	if(!isLoading){
	        		glasspane.setVisible(false);
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
	
	public void showHint(String str){
		hintLabel.setText(str);
	}
	
	public void hideHint(){
		hintLabel.setText("");
	}
	
	JPanel centerPanelIndexRight_camera = new JPanel();
	/**
	 * 创建首页内容右侧面板
	 */
	private void createCenterPanelAlarmHandleRight() {
		centerPanelAlarmHandleRight = new LzzCameraPanel(curBusId, this);
	}
	
	private String locationImgUrl = "/images/location.png";
	/**
	 * 创建页面路径UI
	 * @param string
	 */
	private JPanel updatePathPanel(String path) {
		String[] paths = path.split("-");
		if(null!=pathPanel){
			sysMenuPanel.remove(pathPanel);
		}
		pathPanel = new JPanel();
		pathPanel.setLayout(new BoxLayout(pathPanel, BoxLayout.X_AXIS));
		pathPanel.add(Box.createHorizontalStrut(10));
		pathPanel.setPreferredSize(new Dimension(0, 40));
		
		LzzImgLabel local_img = new LzzImgLabel(locationImgUrl, 20, 20);
		pathPanel.add(local_img);
		pathPanel.add(Box.createHorizontalStrut(10));
		
		String cur_path = "";
		for(int i=0; i<paths.length; i++){
			if(i!=0){
				JLabel location_spr = new JLabel("->");
				pathPanel.add(location_spr);
				cur_path += "-";
			}
			
			final JLabel location_name = new JLabel(paths[i]);
			pathPanel.add(location_name);
			
			cur_path += paths[i];
		}
		sysMenuPanel.add(pathPanel, BorderLayout.SOUTH);
		return pathPanel;
	}

	private void createSysMenu() {
		sysMenuPanel = new JPanel();
		sysMenuPanel.setLayout(new BorderLayout());
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
		menuPanel.setBorder(BorderFactory.createLineBorder(LzzColorFontMgr.smTextColor));
		menuPanel.setBackground(LzzColorFontMgr.smUIHeadBkColor);
		//menuPanel.add(Box.createHorizontalStrut(10));
		
		JMenuBar jMenuBar = new JMenuBar();
		jMenuBar.setOpaque(false);
		jMenuBar.add(LzzMenuMgr.createIndexMenu());
		
		if(LzzUserMgr.userHasAuthority("报警处理")){
			//jMenuBar.add(LzzMenuMgr.createAlarmHandleMenu());
		}
		
		if(LzzUserMgr.userHasAuthority("基础信息")){
			jMenuBar.add(LzzMenuMgr.createInfoQueryMenu());
		}
		
		if(LzzUserMgr.userHasAuthority("故障报修")){
			jMenuBar.add(LzzMenuMgr.createRepairMenu());
		}
		
		if(LzzUserMgr.userHasAuthority("系统设置")){
			jMenuBar.add(LzzMenuMgr.createSysSetMenu());
		}
		
		if(LzzUserMgr.userHasAuthority("数据导入")){
			jMenuBar.add(LzzMenuMgr.createDataImportMenu());
		}
		
		if(LzzUserMgr.userHasAuthority("关于我们")){
			jMenuBar.add(LzzMenuMgr.createAboutMenu());
		}
		LzzMenuMgr.setMainFrame(this);
		menuPanel.add(jMenuBar);
		
		sysMenuPanel.add(menuPanel, BorderLayout.NORTH);
	}
	
	/**
	 * 创建地图功能UI
	 */
	private void createCenterPanelMap() {
		// TODO Auto-generated method stub
		mapPanel = new JPanel();
		mapPanel.setLayout(new BorderLayout());
		
		//地图组件
		webBrowserPanel = new LzzBrowserPanel(LzzProperties.getDataRequestUrl() + "?sessionId=" + LzzUserMgr.userSessionId);
		//webBrowserPanel.setLayout(new BoxLayout(webBrowserPanel, BoxLayout.Y_AXIS));
		mapPanel.add(webBrowserPanel, BorderLayout.CENTER);
		
		//创建传感器数据刷新面板
		createLogRefreshPanel();
		mapPanel.add(logRefreshPanel, BorderLayout.SOUTH);
		
		mapPanel.setOpaque(false);
	}
	
	private JTable indexAlarmDataTable;
	private DefaultTableModel indexAlarmDataTablemodel;
	/**
	 * 创建传感器数据刷新UI
	 */
	private void createLogRefreshPanel() {
		logRefreshPanel = new JPanel();
		logRefreshPanel.setLayout(new BoxLayout(logRefreshPanel, BoxLayout.Y_AXIS));
		logRefreshPanel.setPreferredSize(new Dimension(100, 200));
		logRefreshPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		logRefreshPanel.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		/*logRefreshContentPanel = new JPanel();
		logRefreshContentPanel.setLayout(new BoxLayout(logRefreshContentPanel, BoxLayout.Y_AXIS));
		logRefreshContentPanel.add(Box.createVerticalStrut(5));
		logRefreshContentPanel.setBackground(bkColorBlack);
	
		logScrollPanel = new JScrollPane(logRefreshContentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		logScrollPanel.setPreferredSize(new Dimension(100, 200));
		logScrollPanel.setBorder(BorderFactory.createTitledBorder("车辆报警信息："));
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
		check_panel.add(Box.createHorizontalGlue());*/
		
		//创建报警日志区
		final Object[] historyDataColumnNames = {
				"报警时间", "报警编号", "车牌号", "所属公司", "报警类型"};
		Object[][] rowData = {};
		indexAlarmDataTablemodel = new DefaultTableModel(rowData, historyDataColumnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		indexAlarmDataTable = new JTable(indexAlarmDataTablemodel);
		indexAlarmDataTable.getTableHeader().setFont(LzzColorFontMgr.smTextFontMiddle);
		indexAlarmDataTable.setFont(LzzColorFontMgr.smTextFontMiddle);
		indexAlarmDataTable.setRowHeight(30);
		//设置内容居中
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		indexAlarmDataTable.setDefaultRenderer(Object.class, r);
		indexAlarmDataTable.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
            	int row = ((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置 
            	int col = ((JTable)e.getSource()).columnAtPoint(e.getPoint()); //获得列位置 
            	final String alarm_id = (String)(indexAlarmDataTablemodel.getValueAt(row, 1)); //获得点击单元格数据 
            	String car_num = (String)(indexAlarmDataTablemodel.getValueAt(row, 2)); //获得点击单元格数据 
            	String bus_id = LzzDataCache.getBusId(car_num);
                if (e.getClickCount() == 2){
                     if(null!=bus_id){
                    	 selectBus(bus_id);
                    	 boolean set_suc = centerPanelAlarmHandleRight.setBusId(bus_id);
                    	 if(set_suc){
                    		 centerPanelAlarmHandleRight.preview();
                    	 }
                     }
                }
                
                if (e.getButton()==MouseEvent.BUTTON3) {  
		              //弹出右键菜单  
		    		  final JPopupMenu menu = new JPopupMenu("处理该报警");  
		    		  JMenuItem menu_item = new JMenuItem("处理该报警");
		    		  menu.add(menu_item);
		    		  menu.show(indexAlarmDataTable, e.getPoint().x, e.getPoint().y);
		    		  menu_item.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							LzzAlarmHandleFrm handle = new LzzAlarmHandleFrm(alarm_id, LzzIndexFrm.this);
							handle.setVisible(true);
						}
					});
		    	  }
            }
        });
		
		JScrollPane pane1 = new JScrollPane (indexAlarmDataTable);
		JPanel panel = new JPanel (new GridLayout(0, 1));
		panel.add (pane1);
		
		logRefreshPanel.add(panel);
	}
	
	/**
	 * 设置单条报警已处理
	 * @param alarm_id
	 */
	public void setSingleAlarmHandled(String alarm_id){
		synchronized (indexAlarmDataTable) {
			int row_count = indexAlarmDataTable.getRowCount();
			for(int i=0; i<row_count; i++){
				String alarm_id_check = (String) indexAlarmDataTable.getValueAt(i, 1);
				if(alarm_id_check.equals(alarm_id)){
					indexAlarmDataTablemodel.removeRow(i);
					break;
				}
			}
			
			checkAlarmCount();
		}
	}
	
	/**
	 * 检查报警数量
	 */
	private void checkAlarmCount() {
		if(indexAlarmDataTable.getRowCount()==0){
			stopBusAlarmShine();
			showPanel("首页");
		}
		
		List<String> bus_nums = new ArrayList<String>();
		int row_count = indexAlarmDataTable.getRowCount();
		for(int i=0; i<row_count; i++){
			String bus_num = (String) indexAlarmDataTable.getValueAt(i, 2);
			if(!bus_nums.contains(bus_num)){
				bus_nums.add(bus_num);
			}
		}
		setAlarmBusCount(bus_nums.size());
	}

	/**
	 * 设置报警车辆数量
	 * @param size
	 */
	private void setAlarmBusCount(int size) {
		busAlarmCount.setText("(" + size + ")");
	}

	/**
	 * 公交树状控件
	 */
	private JTree busTree;
	private DefaultTreeModel busTreeModel;
	private DefaultMutableTreeNode top;
	private JPanel treePanel;
	/**
	 * 创建树形控件对象
	 * @return
	 */
	private JPanel createBusTreePanel() {
		NativeInterface.open();
		
		final LzzIndexFrm that = this;
		
		//创建树形控件
		treePanel = new JPanel();
		treePanel.setOpaque(false);
		//创建树形控件节点
		LzzTreeCellRenderer cellRenderer = new LzzTreeCellRenderer();
		JSONObject bus_datas = LzzDataRequestMgr.getUserMgrBusList();
		top = new DefaultMutableTreeNode();
		LzzDataCache.clearBuses();
		if(!bus_datas.isEmpty()){
			top = getCompanyTree(bus_datas.getJSONObject("data"));
		}
        
        final JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
        tmpPanel.setOpaque(false);
        busTreeModel = new DefaultTreeModel(top);
        busTree = new JTree(busTreeModel);
        busTree.setRowHeight(30);
        busTree.setBackground(LzzColorFontMgr.smUIBodyBkColor);
        busTree.setCellRenderer(cellRenderer);
        busTree.setExpandsSelectedPaths(true);
        
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
            		selectBus(object.getNodeId());
                }else{
                	System.out.println("你取消了公交选择");
                	cancelSelectBus();
                }
            }
        });
        
        busTree.addMouseListener(new MouseAdapter(){
		      public void mouseClicked(MouseEvent e){
		    	  if (e.getButton()==MouseEvent.BUTTON3) {
		    		  TreePath path = busTree.getPathForLocation(e.getX(), e.getY());
		    		  if(path!=null){
		    			  busTree.setSelectionPath(path);
		    			  final DefaultMutableTreeNode node = (DefaultMutableTreeNode) busTree.getLastSelectedPathComponent();
		    			  if(node.isLeaf()){
		    				  /*//弹出右键菜单  
		    				  final JPopupMenu menu = new JPopupMenu("设置报警已处理");  
		    				  JMenuItem menu_item = new JMenuItem("设置报警已处理");
		    				  menu.add(menu_item);
		    				  menu.show(busTree, e.getPoint().x, e.getPoint().y);
		    				  menu_item.addActionListener(new ActionListener() {
		    					  @Override
		    					  public void actionPerformed(ActionEvent e) {
		    						  String bus_id = ((LzzTreeBusNode)node.getUserObject()).getNodeId();
		    						  boolean rslt = setBusAlarmHandled(bus_id);
				    				  if(rslt){
				    					  JOptionPane.showMessageDialog(null, "处理成功");
				    				  }else{
				    					  JOptionPane.showMessageDialog(null, "设置失败");
				    				  }
		    					  }
		    				  });*/
		    			  }
		    		  }
		    	  }  
		      }
		});
        
        return treePanel;
	}
	
	/**
	 * 重新加载公交结构树
	 */
	public void reloadBusTreePanel(){
		westPanelTree.remove(treePanel);
		treePanel.removeAll();
		treePanel = createBusTreePanel();
		westPanelTree.add(treePanel);
		westPanelTree.validate();
	}
	
	/**
	 * 展开或者折叠树
	 * @param tree
	 * @param parent
	 * @param expand
	 */
	private void expandAllNode(JTree tree, TreePath parent, boolean expand) {
	    // Traverse children
	    TreeNode node = (TreeNode) parent.getLastPathComponent();
	    if (node.getChildCount() >= 0) {
	        for (Enumeration<?> e = node.children(); e.hasMoreElements();) {
	            TreeNode n = (TreeNode) e.nextElement();
	            TreePath path = parent.pathByAddingChild(n);
	            expandAllNode(tree, path, expand);
	        }
	    }
	 
	    if (expand) {
	        tree.expandPath(parent);
	    } else {
	        tree.collapsePath(parent);
	    }
	}

	/**
	 * 公交树状结构所有叶子节点和公交ID的对应
	 */
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
			top_node = new DefaultMutableTreeNode(new LzzTreeBusNode(company_name + "　　　", "", ""));
			
			JSONArray lines = bus_datas.getJSONArray("lines");
			for(int i=0; i<lines.size(); i++){
				JSONObject line = lines.getJSONObject(i);
				String line_num = line.getString("lineNum");
				int on_line_count = line.getInt("onLineCount");
				JSONArray buses = line.getJSONArray("buses");
				
				line_num = line_num + "(" + on_line_count + "/" + buses.size() + ")";
				DefaultMutableTreeNode line_node = new DefaultMutableTreeNode(new LzzTreeBusNode(line_num, "", ""));
				
				if(buses.size()==0){
					line_node.add(new DefaultMutableTreeNode());
				}
				for(int j=0; j<buses.size(); j++){
					JSONObject bus = buses.getJSONObject(j);
					String id = bus.getString("id");
					String car_num = bus.getString("carNum");
					
					//默认初始选中第一辆车
					if(null==curBusId){
						curBusId = id;
					}
					
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
	 * 设置功能UI对象
	 */
	private LzzUserMgrPanel userMgrPanel = null;
	private LzzBusMgrPanel busMgrPanel = null;

	/**
	 * 创建 系统设置 功能UI
	 */
	private void createCenterPanelSystemSet() {
		// TODO Auto-generated method stub
		/*systemSetPanel.setLayout(new BorderLayout());
		systemSetPanel.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		//创建系统设置新增按钮功能区
		createSystemSetInnerNorthPanel();
		
		//创建系统设置数据列表区
		createSystemSetfoInnerCenterPanel();
		
		//创建系统设置翻页功能区
		createSystemSetInnerSouthPanel();
		
		systemSetPanel.add(systemSetInnerNorthPanel, BorderLayout.NORTH);
		systemSetPanel.add(systemSetInnerCenterPanel, BorderLayout.CENTER);
		systemSetPanel.add(systemSetInnerSouthPanel, BorderLayout.SOUTH);*/
		userMgrPanel = new LzzUserMgrPanel(this);
		centerCardPanel.add(userMgrPanel, "系统设置-人员管理");
		
		busMgrPanel = new LzzBusMgrPanel(this);
		centerCardPanel.add(busMgrPanel, "系统设置-公交管理");
	}

	private JPanel systemSetInnerNorthPanel = new JPanel();
	LzzTextField userSearch = new LzzTextField();
	/**
	 * 创建系统设置新增按钮功能区
	 */
	private void createSystemSetInnerNorthPanel() {
		systemSetInnerNorthPanel.setLayout(new BoxLayout(systemSetInnerNorthPanel, BoxLayout.X_AXIS));
		systemSetInnerNorthPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		userSearch.setPreferredSize(new Dimension(150, 30));
		userSearch.setMaximumSize(new Dimension(150, 30));
		userSearch.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                int k = e.getKeyCode();
                if(k == e.VK_ENTER){
                	String text = userSearch.getText();
                	getUserList();
                }
            }
		});
		
		LzzRButton searchBtn = new LzzRButton("查询");
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				getUserList();
			}
		});
		
		final LzzRButton addBtn = new LzzRButton("新增");
		addBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				LzzUserAddUpdateFrm add_update = new LzzUserAddUpdateFrm(null);
				add_update.setVisible(true);
				add_update.addWindowListener(new WindowAdapter() {
				    @Override
				    public void windowClosed(WindowEvent e) {  
				        getUserList(); 
				    }  
				});
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
		
		final LzzRButton editBtn = new LzzRButton("编辑");
		editBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int row = systemSetDataTable.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(null, "请选择一条记录！");
					return;
				}
				String userid = systemSetDataTable.getValueAt(row, 1).toString();
				LzzUserAddUpdateFrm add_update = new LzzUserAddUpdateFrm(userid);
				add_update.addWindowListener(new WindowAdapter() {
				    @Override
				    public void windowClosed(WindowEvent e) {  
				        getUserList(); 
				    }  
				});
				add_update.setVisible(true);
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
		
		final LzzRButton removeBtn = new LzzRButton("删除");
		removeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int row = systemSetDataTable.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(null, "请选择一条记录！");
					return;
				}
				String userid = systemSetDataTable.getValueAt(row, 1).toString();
				
				int exi = JOptionPane.showConfirmDialog (null, "确定要删除该用户吗？", "友情提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (exi == JOptionPane.YES_OPTION){
                	JSONObject rslt = LzzUserMgr.delUser(userid);
                	if(rslt.getBoolean("success")){
                		JOptionPane.showMessageDialog(null, "删除成功");
                	}else{
                		JOptionPane.showMessageDialog(null, "删除失败");
                	}
                	getUserList();
                }
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
		
		systemSetInnerNorthPanel.add(Box.createHorizontalStrut(10));
		systemSetInnerNorthPanel.add(userSearch);
		systemSetInnerNorthPanel.add(Box.createHorizontalStrut(10));
		systemSetInnerNorthPanel.add(searchBtn);
		systemSetInnerNorthPanel.add(Box.createHorizontalGlue());
		systemSetInnerNorthPanel.add(addBtn);
		systemSetInnerNorthPanel.add(Box.createHorizontalStrut(30));
		systemSetInnerNorthPanel.add(editBtn);
		systemSetInnerNorthPanel.add(Box.createHorizontalStrut(30));
		systemSetInnerNorthPanel.add(removeBtn);
		systemSetInnerNorthPanel.add(Box.createHorizontalStrut(30));
	}
	
	private JPanel systemSetInnerCenterPanel = new JPanel();
	private DefaultTableModel ststemSetTablemodel = null;
	private JTable systemSetDataTable = null;
	/**
	 * 创建系统设置数据列表区
	 */
	private void createSystemSetfoInnerCenterPanel() {
		systemSetInnerCenterPanel.setLayout(new BorderLayout());
		
		/*final Object[] historyDataColumnNames = {
				"序号", "公司", "线路", "车牌号", "报警类型", "报警等级", "时间", "报警处置情况"};*/
		final Object[] historyDataColumnNames = {
				"序号", "用户编号", "姓名", "登录名", "角色"};
		Object[][] rowData = {};
		ststemSetTablemodel = new DefaultTableModel(rowData, historyDataColumnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		systemSetDataTable = new JTable(ststemSetTablemodel);
		systemSetDataTable.getTableHeader().setFont(LzzColorFontMgr.smTextFontMiddle);
		systemSetDataTable.setFont(LzzColorFontMgr.smTextFontMiddle);
		systemSetDataTable.setRowHeight(30);
		//设置内容居中
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		systemSetDataTable.setDefaultRenderer(Object.class, r);
		
		JScrollPane pane1 = new JScrollPane (systemSetDataTable);
		JPanel panel = new JPanel (new GridLayout(0, 1));
		panel.setBackground (Color.black);
		panel.add (pane1);
		
		systemSetInnerCenterPanel.add(panel, BorderLayout.CENTER);
	}

	private JLabel systemSetPagePos = new JLabel("1/1");
	private JPanel systemSetInnerSouthPanel = new JPanel();
	private JSONObject systemSetPageInfo = null;
	/**
	 * 创建系统设置翻页区
	 */
	private void createSystemSetInnerSouthPanel() {
		systemSetInnerSouthPanel.setLayout(new BoxLayout(systemSetInnerSouthPanel, BoxLayout.X_AXIS));
		systemSetInnerSouthPanel.setPreferredSize(new Dimension(0, 40));
		
		JLabel prevPageLabel = new JLabel("<HTML><U>上一页</U></HTML>");
		prevPageLabel.setPreferredSize(new Dimension(60, 40));
		prevPageLabel.setMaximumSize(new Dimension(60, 30));
		prevPageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		prevPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int cur_page = systemSetPageInfo.getInt("curPage");
				if(cur_page==1){
					JOptionPane.showMessageDialog(null, "当前已经是第一页");
					return;
				}
				systemSetPageInfo.put("curPage", cur_page-1);
				getUserList();
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
		
		JLabel nextPageLabel = new JLabel("<HTML><U>下一页</U></HTML>");
		nextPageLabel.setPreferredSize(new Dimension(60, 40));
		nextPageLabel.setMaximumSize(new Dimension(60, 30));
		nextPageLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nextPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int cur_page = systemSetPageInfo.getInt("curPage");
				int total_page = systemSetPageInfo.getInt("totalPage");
				if(cur_page==total_page){
					JOptionPane.showMessageDialog(null, "当前已经是最后一页");
					return;
				}
				systemSetPageInfo.put("curPage", cur_page+1);
				getUserList();
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
		
		systemSetInnerSouthPanel.add(Box.createHorizontalGlue());
		systemSetInnerSouthPanel.add(prevPageLabel);
		systemSetInnerSouthPanel.add(Box.createHorizontalStrut(10));
		systemSetInnerSouthPanel.add(systemSetPagePos);
		systemSetInnerSouthPanel.add(Box.createHorizontalStrut(10));
		systemSetInnerSouthPanel.add(nextPageLabel);
		systemSetInnerSouthPanel.add(Box.createHorizontalGlue());
	}
	
	/**
	 * 获取用户列表
	 */
	protected void getUserList() {
		showLoading();
		DefaultTableModel sysSetModel = (DefaultTableModel) systemSetDataTable.getModel();
		if(sysSetModel.getRowCount()>0){
			sysSetModel.setRowCount(0);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String search_key = userSearch.getText();
		JSONObject data_page = LzzUserMgr.getUserList(systemSetPageInfo, search_key);
		JSONArray user_list = data_page.getJSONArray("data");
		systemSetPageInfo = data_page.getJSONObject("pageInfo");
		if(null!=user_list){
			for(int i=0; i<user_list.size(); i++){
				JSONObject sin = user_list.getJSONObject(i);
				sysSetModel.addRow(
					new Object[]{
							sin.getString("orderNum"), 
							sin.getString("id"), 
							sin.getString("lastName"),
							sin.getString("userName"),
							sin.getString("roleName")});
			}
		}
		
		//设置页码
		systemSetPagePos.setText(systemSetPageInfo.getInt("curPage") + "/" + systemSetPageInfo.getInt("totalPage"));
		hideLoading();
	}

	/**
	 * 创建底部控件
	 */
	private void createSouthPanel() {
		// TODO Auto-generated method stub
		southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
		southPanel.setBackground(LzzColorFontMgr.smUIHeadBkColor);
		
		createSouthHintPanel();
		createSouthCopyRightPanel();
		craeteSouthTimePanel();
		createSouthStatusPanel();

		southPanel.add(Box.createHorizontalStrut(10));
		southPanel.add(hintLabel);
		southPanel.add(Box.createHorizontalGlue());
		southPanel.add(copyRightLabel);
		southPanel.add(timeLabel);
		southPanel.add(Box.createHorizontalStrut(10));
		southPanel.add(onlineStatusLabel);
		southPanel.add(Box.createHorizontalStrut(10));
	}
	
	private void createSouthHintPanel() {
		hintLabel = new JLabel("");
		hintLabel.setFont(new Font("Dialog", 1, 15));
		hintLabel.setForeground(LzzColorFontMgr.smTextColor);
	}

	private void createSouthCopyRightPanel() {
		copyRightLabel = new JLabel("芯核防务 版权所有@CopyRight  ");
		copyRightLabel.setFont(new Font("Dialog", 1, 15));
		copyRightLabel.setForeground(LzzColorFontMgr.smTextColor);
	}

	private void craeteSouthTimePanel() {
		timeLabel = new JLabel("");
		timeLabel.setFont(new Font("Dialog", 1, 15));
		timeLabel.setForeground(LzzColorFontMgr.smTextColor);
	}

	private LzzImgLabel onlineStatusLabel  = null;
	private void createSouthStatusPanel() {
		onlineStatusLabel = new LzzImgLabel(img_url_offline, 25, 25);
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
					selectBus(bus_id);
					if (e.getButton()==MouseEvent.BUTTON3) {  
						//弹出右键菜单  
						final JPopupMenu menu = new JPopupMenu("设置警告已处理");  
						JMenuItem menu_item = new JMenuItem("设置警告已处理");
						menu.add(menu_item);
						menu.show(label, e.getPoint().x, e.getPoint().y);
						menu_item.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								boolean rslt = setBusAlarmHandled(bus_id);
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
	private boolean setBusAlarmHandled(String bus_id){
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
		removeAlarmBusLabel(bus_id);
		
		if(hasNoAlarmBus()){
			stopBusAlarmShine();
		}
	}
	
	/**
	 * 判断是否已经没有报警车辆了
	 * @return
	 */
	private boolean hasNoAlarmBus() {
		for(String key : alarmLogLabels.keySet()) { 
			return false; 
		}
		
		return true;
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
	protected void selectBus(String bus_id) {
		curBusId = bus_id;
		LzzDataRequestMgr.addUserSelectBusOpr(bus_id);
		refreshPanelData();
		System.out.println("select" + bus_id);
	}
	
	/**
	 * 取消选择公交
	 */
	protected void cancelSelectBus() {
		curBusId = null;
		return;
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
	 * 改变鼠标形状
	 * @param handCursor 
	 */
	protected void changeCursor(int cursor_type) {
		// TODO Auto-generated method stub
		setCursor(new Cursor(cursor_type));
	}

	public String selectSavePath(){  
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");  
        String name = "报警记录" + dateformat.format(new Date());  
        name = name + ".xls";
        //构造文件保存对话框  
        JFileChooser chooser = new JFileChooser();  
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);  
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        String saveType[] = {"xls"}; 
        chooser.setFileFilter(new FileNameExtensionFilter("xls", saveType));
        chooser.setSelectedFile(new File(name));
        chooser.setMultiSelectionEnabled(false);  
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogTitle("导出报警记录");
          
        //取得文件名输入框冰设置指定格式  
        //JTextField fileNameField = getTextField(chooser);  
        //fileNameField.setText(name);  
          
        //打开对话框  
        int result = chooser.showSaveDialog(this);//null  
          
        //文件确定  
        if(result==JFileChooser.APPROVE_OPTION) {  
            String outPath = chooser.getSelectedFile().getAbsolutePath();  
            if(new File(outPath).exists()){ 
            	int over_write = JOptionPane.showConfirmDialog(null, "文件已经存在,是否要覆盖该文件?");
            	System.out.println("over_write : " + over_write);
                if(2==over_write){
                    return null;
                }  
            }  
            return outPath;  
        }  
        return null;  
    }
}
