package com.lzz.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.sf.json.JSONObject;

import com.lzz.swing.component.LzzImageIcon;
import com.lzz.swing.component.LzzImgLabel;
import com.lzz.swing.component.LzzTimerDialog;
import com.lzz.swing.mgr.LzzBusMgr;
import com.lzz.swing.mgr.LzzColorFontMgr;
import com.lzz.swing.mgr.LzzImgMgr;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.examples.win32.W32API.HWND;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.NativeLongByReference;

public class LzzCameraPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4174756918052874400L;
	
	private LzzIndexFrm parent;
	private String busId = null;
	private String ip = "";
	private String port = "38002";
	private String uname = "";
	private String pwd = "";
	
	private List<JPanel> jPanelRealplayAreas = new ArrayList<JPanel>();
	private JPanel jPanelRealplayArea = new JPanel();
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
	
	private JPanel centerPanelIndexRight_camera = new JPanel();
	
	Map<Panel, LzzImgLabel> panelCameraImgs = new Hashtable<Panel, LzzImgLabel>();
	
	private int cameraCount = 3;
	
	private JScrollPane cameraScroll = new JScrollPane();
	
	public LzzCameraPanel(String bus_id, LzzIndexFrm parent){
		setBusId(busId);
		this.parent = parent;
		
		lUserID = new NativeLong(-1);
        lPreviewHandle = new NativeLong(-1);
        g_lVoiceHandle = new NativeLong(-1);
        m_lPort = new NativeLongByReference(new NativeLong(-1));
        fRealDataCallBack= new FRealDataCallBack();
        
        setLayout(new BorderLayout());
        
        JPanel centerPanelIndexRight_func = new JPanel();
		centerPanelIndexRight_func.setLayout(new BoxLayout(centerPanelIndexRight_func, BoxLayout.Y_AXIS));
		JPanel centerPanelIndexRight_func_inner = new JPanel();
		centerPanelIndexRight_func_inner.setLayout(new BoxLayout(centerPanelIndexRight_func_inner, BoxLayout.X_AXIS));
		centerPanelIndexRight_func.add(centerPanelIndexRight_func_inner);
		
		JLabel camera_title = new JLabel("车载视频监控:");
		camera_title.setFont(LzzColorFontMgr.smTextFont);
		centerPanelIndexRight_func_inner.add(camera_title);
		
		LzzImgLabel reset = new LzzImgLabel(LzzImgMgr.img_url_btnBk, 50, 18, "复位");
		reset.setHorizontalTextPosition(JLabel.CENTER);
		//centerPanelIndexRight_func_inner.add(reset);
		
		final JCheckBox clearCamera  = new JCheckBox("清晰");
		final JCheckBox smoothCamera  = new JCheckBox("流畅");
		clearCamera.setFont(LzzColorFontMgr.smTextFont);
		clearCamera.setSelected(true);
		clearCamera.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(clearCamera.isSelected()){
					smoothCamera.setSelected(false);
				}
			}
		});
		
		smoothCamera.setFont(LzzColorFontMgr.smTextFont);
		centerPanelIndexRight_func_inner.add(clearCamera);
		centerPanelIndexRight_func_inner.add(smoothCamera);
		centerPanelIndexRight_func_inner.add(Box.createHorizontalGlue());
		smoothCamera.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(smoothCamera.isSelected()){
					clearCamera.setSelected(false);
				}
			}
		});
		
		add(centerPanelIndexRight_func, BorderLayout.NORTH);
		
		GridLayout fl = new GridLayout(0, 2);
		fl.setHgap(5);
		fl.setVgap(5);
		centerPanelIndexRight_camera.setLayout(fl);
		centerPanelIndexRight_camera.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		initMonitorPanel();
		
		centerPanelIndexRight_camera.add(jPanelRealplayArea);
		
		for(int i=0; i<jPanelRealplayAreas.size(); i++){
			centerPanelIndexRight_camera.add(jPanelRealplayAreas.get(i));
		}

		cameraScroll = new JScrollPane (centerPanelIndexRight_camera);
		add(cameraScroll, BorderLayout.CENTER);
	}
	
	/**
	 * 初始化监控面板
	 */
	public void initMonitorPanel() {
		jPanelRealplayArea.setLayout(new BoxLayout(jPanelRealplayArea, BoxLayout.X_AXIS));
		jPanelRealplayArea.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		LzzImgLabel label = new LzzImgLabel(LzzImgMgr.img_url_camera);
		panelRealplay.add(label);
		panelCameraImgs.put(panelRealplay, label);
		
		//panelRealplay.setLayout(new BoxLayout(panelRealplay, BoxLayout.X_AXIS));
		//jPanelRealplayArea.add(Box.createHorizontalGlue());
		jPanelRealplayArea.add(panelRealplay, BorderLayout.CENTER);
		//jPanelRealplayArea.add(Box.createHorizontalGlue());
		
		//多余的摄像头
		for(int i=0; i<cameraCount; i++){
			JPanel area = new JPanel();
			area.setLayout(new BoxLayout(area, BoxLayout.X_AXIS));
			area.setBorder(BorderFactory.createLineBorder(Color.GREEN));
			
			jPanelRealplayAreas.add(area);
			
			LzzImgLabel label2 = new LzzImgLabel(LzzImgMgr.img_url_camera);
			java.awt.Panel panel = new java.awt.Panel();
			//panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			panel.add(label2);
			panelCameraImgs.put(panel, label2);
			
			//area.add(Box.createHorizontalGlue());
			area.add(panel, BorderLayout.CENTER);
			//area.add(Box.createHorizontalGlue());
		}
	}
	
	private Timer cameraClosetimer = new Timer();
	/**
	 * 预览函数
	 */
	public void preview(){
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				parent.showHint("摄像头加载中...");
				removeCameraImgs();
				boolean rslt = registCamera();
				if(rslt){
					rslt = startPreview();
				}else{
					JOptionPane.showMessageDialog(parent, "摄像头注册失败");
					addCameraImgs();
					parent.hideHint();
					return;
				}
				
				if(rslt){
					//开始60s倒计时，到时间弹出关闭确认框
					cameraClosetimer.cancel();
					cameraClosetimer = new Timer();
					cameraClosetimer.schedule(new TimerTask() {
				        public void run() {
				        	showContinueCameraAsk();
				        }
					}, 20*1000);
				}else{
					System.out.println("摄像头预览失败");
					addCameraImgs();
					parent.hideHint();
					return;
				}
				parent.hideHint();
			}
		});
		t.start();
	}
	
	/**
	 * 注册链接摄像头
	 * @return 是否注册成功
	 */
	public boolean registCamera() {
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
        m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
        int iPort = Integer.parseInt(port);
        
        boolean initSuc = hCNetSDK.NET_DVR_Init();
        if (initSuc != true){
            JOptionPane.showMessageDialog(null, "初始化失败");
            return false;
        }
        
        //admin XINHEDEF001
        lUserID = hCNetSDK.NET_DVR_Login_V30(ip,
                (short) iPort, uname, pwd, m_strDeviceInfo);

        long userID = lUserID.longValue();
        if (userID == -1){
        	ip = "";//登录未成功,IP置为空
        	return false;
        }
        
        return true;
	}
	
	/**
	 * 开始预览
	 */
	public boolean startPreview() {
        if (lUserID.intValue() == -1){
            JOptionPane.showMessageDialog(this, "摄像头初始化失败！");
            return false;
        }

        //如果预览窗口没打开,不在预览
        if (bRealPlay == false){
            //获取窗口句柄
            HWND hwnd = new HWND(Native.getComponentPointer(panelRealplay));

            //获取通道号
            int iChannelNum = getChannelNumber();//通道号
            if(iChannelNum == -1){
                JOptionPane.showMessageDialog(this, "请选择要预览的通道");
                return false;
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
                return false;
            }

            //预览成功的操作
            bRealPlay = true;
            return true;
        }else{//如果在预览,停止预览,关闭窗口
           stopPreview();
           return false;
        }
	}
	
	/**
	 * 停止预览
	 */
	public void stopPreview(){
		 hCNetSDK.NET_DVR_StopRealPlay(lPreviewHandle);
         bRealPlay = false;
         if(m_lPort.getValue().intValue() != -1)
         {
             playControl.PlayM4_Stop(m_lPort.getValue());
             m_lPort.setValue(new NativeLong(-1));
         }
         
         cameraClosetimer.cancel();
         addCameraImgs();
         panelRealplay.repaint();
         System.out.println("stoped");
	}
	
	//获取摄像头通道号
	public int getChannelNumber() {
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
	 * 添加摄像头上的遮罩图片
	 */
	private void addCameraImgs() {
		panelRealplay.add(panelCameraImgs.get(panelRealplay));
		cameraScroll.validate();
	}
	
	/**
	 * 删除摄像头上的遮罩图片
	 */
	protected void removeCameraImgs() {
		panelRealplay.remove(panelCameraImgs.get(panelRealplay));
		cameraScroll.repaint();
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
	
	/**
	 * 显示是否继续预览的询问
	 */
	private void showContinueCameraAsk(){
         LzzTimerDialog ask = new LzzTimerDialog();
         int rslt = ask.showDialog(null, "是否继续预览", 10);
         if(rslt==1){
        	 stopPreview();
         }
	}
	
	public boolean setBusId(String bus_id){
		if(null!=bus_id && !bus_id.equals(busId)){
			JSONObject info = LzzBusMgr.getBusCamera(bus_id);
			if(!info.getBoolean("success")){
				JOptionPane.showMessageDialog(null, info.getString("errMsg"));
				return false;
			}
			JSONObject camera = info.getJSONObject("data");
			ip = camera.getString("ip");
			port = camera.getString("port");
			uname = camera.getString("uname");
			pwd = camera.getString("pwd");
		}
		busId = bus_id;
		return true;
	}
}
