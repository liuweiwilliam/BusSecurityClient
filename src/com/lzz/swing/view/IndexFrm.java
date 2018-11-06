package com.lzz.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.OfficeSilver2007Skin;
import org.jvnet.substance.skin.SubstanceAutumnLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessLookAndFeel;
import org.jvnet.substance.skin.SubstanceGreenMagicLookAndFeel;
import org.jvnet.substance.skin.SubstanceMagmaLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel;
import org.jvnet.substance.skin.SubstanceModerateLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaLookAndFeel;
import org.jvnet.substance.watermark.SubstanceMosaicWatermark;

import com.lzz.swing.component.BrowserPanel;
import com.lzz.swing.component.LzzGridConstraints;
import com.lzz.swing.component.LzzImgLabel;
import com.lzz.swing.mgr.LzzFrameMgr;
import com.lzz.swing.util.LzzDateUtil;
import com.lzz.swing.util.LzzFrameUtil;
import com.lzz.swing.util.LzzProperties;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class IndexFrm extends JFrame {

	private JPanel contentPane;
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel bottomPanel;
	private JLabel bottom_label;
	private JPanel webBrowserPanel;
	private LzzImgLabel label_max;
	private LzzImgLabel label_resize;
	private JLabel label_bk;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//UIUtils.setPreferredLookAndFeel();
        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                	//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		            //UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
		            //UIManager.setLookAndFeel(new SubstanceGreenMagicLookAndFeel());
		            //SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());
		            //SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceHueShiftTheme");
                	IndexFrm frame = new IndexFrm();
                	//frame.setUndecorated(true);
                	LzzFrameUtil.setFrameToScreenCenter(frame);
                    frame.setVisible(true);
                } catch (HeadlessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        NativeInterface.runEventPump();
	}

	protected void setTimer() {
		// TODO Auto-generated method stub
		final JLabel label = bottom_label;
		new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				label.setText(LzzDateUtil.getNow("s"));
			}
		}).start();
	}

	/**
	 * Create the frame.
	 */
	public IndexFrm() {
		NativeInterface.open();
		this.setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon img = new ImageIcon("./images/bk.jpg");//这是背景图片
		img.setImage(img.getImage().getScaledInstance(1366, 768, Image.SCALE_DEFAULT));
    	label_bk = new JLabel(img);//将背景图放在标签里。
    	this.getLayeredPane().add(label_bk, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
    	label_bk.setBounds(0, 0, 1366, 768);//设置背景标签的位置
    	setBounds(0, 0, 1366, 768);
    	contentPane = new JPanel();
    	
		contentPane.setBorder(null);
		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		//gbl_contentPane.columnWidths = new int[]{0, 0};
		//gbl_contentPane.rowHeights = new int[]{0, 0};
		//gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		//gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		createTopPanel();
		final IndexFrm that = this;
		topPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();//记录鼠标按下时的坐标
				yOld = e.getY();
				
				//双击最大化
		        if (e.getClickCount() == 2) {
		        }
			}
		});
		topPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();
				int yOnScreen = e.getYOnScreen();
				int xx = xOnScreen - xOld;
				int yy = yOnScreen - yOld;
				that.setLocation(xx, yy);//设置拖拽后，窗口的位置
		    }
		});
		contentPane.add(topPanel, new LzzGridConstraints(1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.NORTH));
				
		createMiddlePanel();
		contentPane.add(middlePanel, new LzzGridConstraints(0, 1));
		
		createBottomPanel();
		contentPane.add(bottomPanel, new LzzGridConstraints(1, 0, 0, 2));
		((JPanel)contentPane).setOpaque(false);
		setTimer();
		LzzFrameUtil.setFrameToScreenCenter(this);
	}

	private void createBottomPanel() {
		bottomPanel = new JPanel();
		((JPanel)bottomPanel).setOpaque(false);
		bottom_label = new JLabel("版权所有@XXXX-XXXX");
		bottomPanel.add(bottom_label);
	}

	private String img_url_small = "./images/login.png";
	private String img_url_exit = "./images/exit.png";
	private String img_url_max = "./images/max.png";
	private String img_url_resize = "./images/preSize.png";
	private String img_url_min = "./images/min.png";
	private String img_url_logo = "./images/syslogo.png";
	
	private String menuStr = "[{\"name\":\"监控\",\"imgUrl\":\"./images/camera.png\"},"
			+ "{\"name\":\"日志\",\"imgUrl\":\"./images/log.png\"},"
			+ "{\"name\":\"地图\",\"imgUrl\":\"./images/map.png\"},"
			+ "{\"name\":\"设置\",\"imgUrl\":\"./images/set.png\"},"
			+ "]";
	private JSONArray menu = JSONArray.fromObject(menuStr);
	private List<JPanel> menuPanels = new ArrayList<JPanel>();
	private Color menuBgC = new Color(167, 232, 238);
	private Color menuTextC = new Color(30, 59, 166);
	
	private void createMiddlePanel() {
		middlePanel = new JPanel();
		((JPanel)middlePanel).setOpaque(false);
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		
		//主菜单
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel menuPanel1 = new JPanel();
		menuPanel1.setLayout(new BoxLayout(menuPanel1, BoxLayout.Y_AXIS));
		for(int i=0; i<menu.size(); i++){
			JPanel single = new JPanel();
			single.setLayout(new BoxLayout(single, BoxLayout.Y_AXIS));
			LzzImgLabel label = new LzzImgLabel(menu.getJSONObject(i).getString("imgUrl"), 100, 100);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			single.add(label);
			JLabel namelabel = new JLabel(menu.getJSONObject(i).getString("name"));
			namelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			namelabel.setFont(new Font("Dialog", 1, 15));
			namelabel.setForeground(menuTextC);
			single.add(namelabel);
			if(i==0){
				single.setBackground(menuBgC);
			}
			Border llineBorder = BorderFactory.createLoweredBevelBorder();
			single.setBorder(llineBorder);
			
			single.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					changeMenu(arg0);
				}
			});
			menuPanels.add(single);
			
			menuPanel1.add(single);
			menuPanel1.add(Box.createVerticalStrut(10));
		}
		menuPanel.add(menuPanel1);
		
		menuPanel.add(Box.createVerticalGlue());
		
		JPanel menuPanel2 = new JPanel();
		menuPanel2.setLayout(new BoxLayout(menuPanel2, BoxLayout.X_AXIS));
		LzzImgLabel label1 = new LzzImgLabel(img_url_small);
		menuPanel2.add(label1);
		LzzImgLabel label2 = new LzzImgLabel(img_url_small);
		menuPanel2.add(label2);
		menuPanel.add(menuPanel2);
		middlePanel.add(menuPanel);
		middlePanel.add(Box.createHorizontalStrut(10));
		
		//创建公交树状控件
		JPanel treePanel = createBusTreePanel();
		treePanel.add(Box.createVerticalGlue());
		middlePanel.add(treePanel);
		middlePanel.add(Box.createHorizontalStrut(10));
		
		webBrowserPanel = new BrowserPanel(LzzProperties.getServerUrl());
		webBrowserPanel.setLayout(new BoxLayout(webBrowserPanel, BoxLayout.Y_AXIS));
		middlePanel.add(webBrowserPanel);
	}

	protected void changeMenu(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JPanel panel = (JPanel) arg0.getComponent();
		for(int i=0; i<menuPanels.size(); i++){
			menuPanels.get(i).setBackground(null);
		}
		panel.setBackground(menuBgC);
		
	}

	private JPanel createBusTreePanel() {
		// TODO Auto-generated method stub
		final IndexFrm that = this;
		
		JPanel treePanel = new JPanel();
		//treePanel.setLayout(new BoxLayout(treePanel, BoxLayout.Y_AXIS));
		
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("蜀山区");
        node1.add(new DefaultMutableTreeNode("皖A1111"));
        node1.add(new DefaultMutableTreeNode("皖A2222"));
        node1.add(new DefaultMutableTreeNode("皖A3333"));
 
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("经开区");
        node2.add(new DefaultMutableTreeNode("皖A4444"));
        node2.add(new DefaultMutableTreeNode("皖A5555"));
        node2.add(new DefaultMutableTreeNode("皖A6666"));
 
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("合肥市");
 
        top.add(node1);
        top.add(node2);
        
        final JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
        final JTree tree = new JTree(top);
        tree.addTreeExpansionListener(new TreeExpansionListener() {
			
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
        tmpPanel.add(tree, BorderLayout.CENTER);
        treePanel.add(tmpPanel);
        
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
                        .getLastSelectedPathComponent();
                if (node == null){
                	return;
                }
 
                Object object = node.getUserObject();
                if (node.isLeaf()) {
                    System.out.println("你选择了：" + object.toString());
                }
 
            }
        });
        
        return treePanel;
	}

	private void createTopPanel() {
		topPanel = new JPanel();
		((JPanel)topPanel).setOpaque(false);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		
		//设置顶部logo图标
		ImageIcon imageIcon = new ImageIcon(img_url_logo);// Icon由图片文件形成
		Image image = imageIcon.getImage();// 但这个图片太大不适合做Icon
		//为把它缩小点，先要取出这个Icon的image ,然后缩放到合适的大小
		Image smallImage = image.getScaledInstance(180,65,Image.SCALE_FAST);
		//再由修改后的Image来生成合适的Icon
		ImageIcon smallIcon = new ImageIcon(smallImage);
		
		JLabel logo_label = new LzzImgLabel(smallIcon);
		//logo_label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		topPanel.add(logo_label);
		
		topPanel.add(Box.createHorizontalGlue());
		
		topPanel.add(new JLabel("系统名称系统名称系统名称"));
		//添加logo和最小化关闭按钮之间的间距
		topPanel.add(Box.createHorizontalGlue());
		
		//添加最小化按钮
		LzzImgLabel label_min = new LzzImgLabel(img_url_min);
		label_min.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				minWin();
			}
		});
		label_min.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		//添加最大化按钮
		label_max = new LzzImgLabel(img_url_max);
		label_max.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				maxWin();
			}
		});
		label_max.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		//添加还原按钮
		label_resize = new LzzImgLabel(img_url_resize);
		label_resize.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				reWinSize();
			}
		});
		label_resize.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		//添加关闭按钮
		LzzImgLabel label_close = new LzzImgLabel(img_url_exit);
		label_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				exitSys();
			}
		});
		label_close.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		topPanel.add(label_min);
		topPanel.add(label_max);
		topPanel.add(label_resize);
		label_resize.setVisible(false);
		topPanel.add(label_close);
	}

	protected void reWinSize() {
		// TODO Auto-generated method stub
		label_max.setVisible(true);
		label_resize.setVisible(false);
		((ImageIcon)label_bk.getIcon()).setImage(((ImageIcon)label_bk.getIcon()).getImage().getScaledInstance(origWidth, origHeight, Image.SCALE_DEFAULT));
		label_bk.setBounds(0, 0, origWidth, origHeight);
		this.setBounds(0, 0, origWidth, origHeight);
		this.setLocation(xOld, yOld);
		this.validate();
	}

	private int origWidth;
	private int origHeight;
	private static int xOld;
	private static int yOld;
	protected void maxWin() {
		// TODO Auto-generated method stub
		label_max.setVisible(false);
		label_resize.setVisible(true);
		origWidth = this.getWidth();
		origHeight = this.getHeight();
		xOld = this.getX();
		yOld = this.getY();
		
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setBounds(0, 0, LzzFrameUtil.getWindowMaxWidth(), LzzFrameUtil.getWindowMaxHeight(this));
		((ImageIcon)label_bk.getIcon()).setImage(((ImageIcon)label_bk.getIcon()).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		label_bk.setBounds(0, 0, this.getWidth(), this.getHeight());
		
		this.validate();
	}

	protected void minWin() {
		// TODO Auto-generated method stub
		this.setExtendedState(JFrame.ICONIFIED); 
		this.validate();
	}

	protected void exitSys() {
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

	
}
