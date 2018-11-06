package com.lzz.swing.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.lzz.swing.view.LzzLoginFrame;

public class LzzFrameUtil {
	/**
	 * 设置窗口居中显示
	 * @param frame 窗口对象
	 */
	public static void setFrameToScreenCenter(JFrame frame){
		frame.setLocationRelativeTo(null);
	}
	
	/**
	 * 设置窗口最大化
	 * @param frame 窗口对象
	 */
	public static void setFrameMax(JFrame frame){
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	/**
	 * 获取电脑屏幕大小
	 * @return
	 */
	public static Dimension getWindowMaxDimension(){
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	/**
	 * 获取窗口的最大宽度
	 * @return
	 */
	public static int getWindowMaxWidth(){
		return (int)getWindowMaxDimension().getWidth();
	}
	
	/**
	 * 获取窗口的最大高度
	 * @param frame
	 * @return
	 */
	public static int getWindowMaxHeight(JFrame frame){
		return (int)getWindowMaxDimension().getHeight()-getScreenInsets(frame);
	}
	
	/**
	 * 获取任务栏高度
	 * @param frame
	 * @return
	 */
	public static int getScreenInsets(JFrame frame){
		Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());
		return screenInsets.bottom;
	}
	
	private static int xOld = 0;
	private static int yOld = 0;
	/**
	 * 设置通过拖动组件可拖动窗口
	 * @param obj 绑定拖动事件的组件
	 * @param frame 拖动的窗口
	 */
	public static void setDragable(final Component obj, final JFrame frame){
		obj.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mousePressed(MouseEvent e) {
			    xOld = e.getX();//记录鼠标按下时的坐标
			    yOld = e.getY();
			   }
			  });
			  
		obj.addMouseMotionListener(new MouseMotionAdapter() {
			    @Override
			    public void mouseDragged(MouseEvent e) {
			    int xOnScreen = e.getXOnScreen();
			    int yOnScreen = e.getYOnScreen();
			    int xx = xOnScreen - xOld;
			    int yy = yOnScreen - yOld;
			    frame.setLocation(xx, yy);//设置拖拽后，窗口的位置
			    }
			   });
	}
	
	/**
	 * 关闭窗口
	 */
	public static void closeWin(JFrame obj) {
		// TODO Auto-generated method stub
		int rslt = JOptionPane.showConfirmDialog(null, "确认退出系统?");
		if(rslt==0){//是
			obj.dispose();
			System.exit(0);
		}
		
		if(rslt==1){//否
			
		}
		
		if(rslt==2){//取消
			
		}
	}
	
	/**
	 * 隐藏窗口的标题框
	 * @param frame
	 */
	public static void hideFrameTitleBorder(JFrame frame){
		frame.setUndecorated(true);
	}
}
