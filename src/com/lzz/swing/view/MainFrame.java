package com.lzz.swing.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

import com.lzz.swing.component.BrowserPanel;
import com.lzz.swing.mgr.LzzFrameMgr;
import com.lzz.swing.util.LzzFontUtil;
import com.lzz.swing.util.LzzFrameUtil;
import com.lzz.swing.util.LzzProperties;

import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainFrame extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UIUtils.setPreferredLookAndFeel();
        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new MainFrame();
                    LzzFrameMgr.showFrame(frame);
                } catch (HeadlessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        NativeInterface.runEventPump();
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		final JFrame that = this;
		UIUtils.setPreferredLookAndFeel();
        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					LzzFontUtil.setDefaultFont();
					
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					setBounds(100, 100, 450, 339);
					
					JMenuBar menuBar = new JMenuBar();
					setJMenuBar(menuBar);
					
					JMenu mnNewMenu = new JMenu("基本数据维护");
					mnNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 15));
					menuBar.add(mnNewMenu);
					
					JMenu mnNewMenu_2 = new JMenu("图书类别管理");
					mnNewMenu.add(mnNewMenu_2);
					
					JMenuItem mntmNewMenuItem_1 = new JMenuItem("添加图书类别");
					mnNewMenu_2.add(mntmNewMenuItem_1);
					
					JMenuItem mntmNewMenuItem_2 = new JMenuItem("图书类别维护");
					mnNewMenu_2.add(mntmNewMenuItem_2);
					
					JMenu mnNewMenu_3 = new JMenu("图书管理");
					mnNewMenu.add(mnNewMenu_3);
					
					JMenuItem mntmNewMenuItem_3 = new JMenuItem("添加图书");
					mnNewMenu_3.add(mntmNewMenuItem_3);
					
					JMenuItem mntmNewMenuItem_4 = new JMenuItem("图书维护");
					mnNewMenu_3.add(mntmNewMenuItem_4);
					
					JMenuItem exitMenu = new JMenuItem("退出系统");
					exitMenu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							exitSys(arg0);
						}
					});
					mnNewMenu.add(exitMenu);
					
					JMenu mnNewMenu_1 = new JMenu("关于我们");
					mnNewMenu_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
					menuBar.add(mnNewMenu_1);
					
					JMenuItem mntmNewMenuItem_5 = new JMenuItem("关于LZ");
					mnNewMenu_1.add(mntmNewMenuItem_5);
					
					JPanel webBrowserPanel = new BrowserPanel("https://www.baidu.com");
			        getContentPane().add(webBrowserPanel, BorderLayout.NORTH);
			        
			        JPanel webBrowserPanel2 = new BrowserPanel("https://www.baidu.com");
			        getContentPane().add(webBrowserPanel2, BorderLayout.SOUTH);
		
			        
			        
			        LzzFrameUtil.setFrameMax(that);
			        that.setVisible(true);
                } catch (HeadlessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        
        //NativeInterface.runEventPump();
	}

	/**
	 * 退出系统确认
	 * @param arg0
	 */
	protected void exitSys(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int rslt = JOptionPane.showConfirmDialog(null, "确认退出系统?");
		if(rslt==0){//是
			LzzFrameMgr.closeFrame(this);
		}
		
		if(rslt==1){//否
			
		}
		
		if(rslt==2){//取消
			
		}
	}
}
