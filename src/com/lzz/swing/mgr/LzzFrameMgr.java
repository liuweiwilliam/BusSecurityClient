package com.lzz.swing.mgr;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class LzzFrameMgr {
	private static JFrame smCurFrame = null;
	private static List<JFrame> smFrameQueue = new ArrayList<JFrame>();
	private static int smCurFrameIndex = -1;
	
	/**
	 * 显示指定frame
	 * @param frame
	 */
	public static void showFrame(JFrame frame){
		if(null==frame) return;
		
		putFrameToQueue(frame);
		frame.setVisible(true);
		smCurFrame = frame;
	}
	
	/**
	 * 关关闭指定frame
	 * @param frame
	 */
	public static void closeFrame(JFrame frame){
		if(null==frame) return;
		removeFrameFromQueue(frame);
		frame.dispose();
	}
	
	/**
	 * 切换页面
	 * @param frame
	 */
	public static void changeFrame(JFrame frame){
		if(null!=smCurFrame){
			smCurFrame.dispose();
		}
		
		smCurFrame = frame;
		
		putFrameToQueue(smCurFrame);
		
		smCurFrameIndex = smFrameQueue.size()-1;
	}

	/**
	 * 将页面放入页面队列
	 * @param frame 页面对象
	 */
	private static void putFrameToQueue(JFrame frame) {
		// TODO Auto-generated method stub
		removeFrameFromQueue(frame);
		
		smFrameQueue.add(frame);
	}
	
	/**
	 * 将页面从页面队列删除
	 * @param frame 页面对象
	 */
	private static void removeFrameFromQueue(JFrame frame) {
		// TODO Auto-generated method stub
		for(int i=0; i<smFrameQueue.size(); i++){
			if(smFrameQueue.get(i).equals(frame)){
				smFrameQueue.remove(i);
				break;
			}
		}
	}
	
	/**
	 * 获取上一个显示的frame
	 * @return
	 */
	public static JFrame getPreviousFrame(){
		if(-1==smCurFrameIndex
				|| 0==smCurFrameIndex) return null;
		
		return smFrameQueue.get(smCurFrameIndex-1);
	}
	
	/**
	 * 获取下一个显示的frame
	 * @return
	 */
	public static JFrame getNextFrame(){
		if(smFrameQueue.size()==0
				|| smFrameQueue.size()==1){
			return null;
		}
		
		return smFrameQueue.get(smCurFrameIndex+1);
	}
}
