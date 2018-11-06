package com.lzz.swing.view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lzz.swing.mgr.LzzColorFontMgr;
import com.lzz.swing.mgr.LzzMenuMgr;

public class LzzTestMenuGroup extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -556177785473182724L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LzzTestMenuGroup frame = new LzzTestMenuGroup();
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
	public LzzTestMenuGroup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		setContentPane(contentPane);
		
		JMenuBar jMenuBar = new JMenuBar();
		jMenuBar.setBackground(LzzColorFontMgr.smUIHeadBkColor);
		
        JMenu jMenu = LzzMenuMgr.createInfoQueryMenu();
        jMenuBar.add(jMenu);
        jMenuBar.setMaximumSize(new Dimension(200, 40));
        contentPane.add(jMenuBar);
	}
}
