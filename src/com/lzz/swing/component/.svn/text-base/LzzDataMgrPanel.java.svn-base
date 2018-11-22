package com.lzz.swing.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lzz.swing.mgr.LzzColorFontMgr;
import com.lzz.swing.mgr.LzzUserMgr;
import com.lzz.swing.view.LzzIndexFrm;
import com.lzz.swing.view.LzzUserAddUpdateFrm;

public class LzzDataMgrPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1872566985529113071L;
	
	private JPanel dataMgrNorthPanel = new JPanel();
	private JPanel dataMgrCenterPanel = new JPanel();
	private JPanel dataMgrSouthPanel= new JPanel();
	private JTable table = null;
	private DefaultTableModel tablemodel = null;
	protected JSONObject pageInfo = null;
	private JLabel pagePos = new JLabel("1/1");
	private Object[] tableTitles; 
	
	protected JFrame parent = null;
	
	public LzzDataMgrPanel(JFrame parent, Object[] tableTitles) {
		this.parent = parent;
		this.tableTitles = tableTitles;
		
		this.setLayout(new BorderLayout());
		this.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		//创建系统设置新增按钮功能区
		createDataMgrNorthPanel();
		
		//创建系统设置数据列表区
		createDataMgrCenterPanel();
		
		//创建系统设置翻页功能区
		createDataMgrSouthPanel();
		
		add(dataMgrNorthPanel, BorderLayout.NORTH);
		add(dataMgrCenterPanel, BorderLayout.CENTER);
		add(dataMgrSouthPanel, BorderLayout.SOUTH);
	}
	
	LzzTextField searchField = new LzzTextField();
	/**
	 * 创建数据管理-按钮功能区
	 */
	private void createDataMgrNorthPanel() {
		dataMgrNorthPanel.setLayout(new BoxLayout(dataMgrNorthPanel, BoxLayout.X_AXIS));
		dataMgrNorthPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		searchField.setPreferredSize(new Dimension(150, 30));
		searchField.setMaximumSize(new Dimension(150, 30));
		searchField.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                int k = e.getKeyCode();
                if(k == e.VK_ENTER){
                	String text = searchField.getText();
                	searchData();
                }
            }
		});
		
		LzzRButton searchBtn = new LzzRButton("查询");
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				searchData();
			}
		});
		
		final LzzRButton addBtn = new LzzRButton("新增");
		addBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				add();
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
				int row = table.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(null, "请选择一条记录！");
					return;
				}
				String userid = table.getValueAt(row, 1).toString();
				
				edit(userid);
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
				int row = table.getSelectedRow();
				if(row==-1){
					JOptionPane.showMessageDialog(null, "请选择一条记录！");
					return;
				}
				String userid = table.getValueAt(row, 1).toString();
				
				int exi = JOptionPane.showConfirmDialog (null, "确定要删除该记录吗？", "友情提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (exi == JOptionPane.YES_OPTION){
                	JSONObject rslt = del(userid);
                	if(rslt.getBoolean("success")){
                		JOptionPane.showMessageDialog(null, "删除成功");
                	}else{
                		JOptionPane.showMessageDialog(null, "删除失败");
                	}
                	searchData();
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
		
		dataMgrNorthPanel.add(Box.createHorizontalStrut(10));
		dataMgrNorthPanel.add(searchField);
		dataMgrNorthPanel.add(Box.createHorizontalStrut(10));
		dataMgrNorthPanel.add(searchBtn);
		dataMgrNorthPanel.add(Box.createHorizontalGlue());
		dataMgrNorthPanel.add(addBtn);
		dataMgrNorthPanel.add(Box.createHorizontalStrut(30));
		dataMgrNorthPanel.add(editBtn);
		dataMgrNorthPanel.add(Box.createHorizontalStrut(30));
		dataMgrNorthPanel.add(removeBtn);
		dataMgrNorthPanel.add(Box.createHorizontalStrut(30));
	}
	
	protected void edit(String id) {
		System.out.println("未继承编辑函数");
		/*LzzUserAddUpdateFrm add_update = new LzzUserAddUpdateFrm(id);
		add_update.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosed(WindowEvent e) {  
		    	searchData(); 
		    }  
		});
		add_update.setVisible(true);*/
	}

	protected void add() {
		System.out.println("未继承添加函数");
		/*LzzUserAddUpdateFrm add_update = new LzzUserAddUpdateFrm(null);
		add_update.setVisible(true);
		add_update.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosed(WindowEvent e) {
		    	searchData(); 
		    }  
		});*/
	}

	protected JSONObject del(String id) {
		JSONObject rslt = new JSONObject();
		rslt.put("success", false);
		return rslt;
	}

	/**
	 * 创建系统设置数据列表区
	 */
	private void createDataMgrCenterPanel() {
		dataMgrCenterPanel.setLayout(new BorderLayout());
		
		/*final Object[] historyDataColumnNames = {
				"序号", "公司", "线路", "车牌号", "报警类型", "报警等级", "时间", "报警处置情况"};*/
		final Object[] historyDataColumnNames = tableTitles;
		Object[][] rowData = {};
		tablemodel = new DefaultTableModel(rowData, historyDataColumnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tablemodel);
		table.getTableHeader().setFont(LzzColorFontMgr.smTextFontMiddle);
		table.setFont(LzzColorFontMgr.smTextFontMiddle);
		table.setRowHeight(30);
		//设置内容居中
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class, r);
		
		JScrollPane pane1 = new JScrollPane (table);
		JPanel panel = new JPanel (new GridLayout(0, 1));
		panel.setBackground (Color.black);
		panel.add (pane1);
		
		dataMgrCenterPanel.add(panel, BorderLayout.CENTER);
	}
	
	/**
	 * 创建系统设置翻页区
	 */
	private void createDataMgrSouthPanel() {
		dataMgrSouthPanel.setLayout(new BoxLayout(dataMgrSouthPanel, BoxLayout.X_AXIS));
		dataMgrSouthPanel.setPreferredSize(new Dimension(0, 40));
		
		JLabel prevPageLabel = new JLabel("<HTML><U>上一页</U></HTML>");
		prevPageLabel.setPreferredSize(new Dimension(60, 40));
		prevPageLabel.setMaximumSize(new Dimension(60, 30));
		prevPageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		prevPageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int cur_page = pageInfo.getInt("curPage");
				if(cur_page==1){
					JOptionPane.showMessageDialog(null, "当前已经是第一页");
					return;
				}
				pageInfo.put("curPage", cur_page-1);
				searchData();
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
				int cur_page = pageInfo.getInt("curPage");
				int total_page = pageInfo.getInt("totalPage");
				if(cur_page==total_page){
					JOptionPane.showMessageDialog(null, "当前已经是最后一页");
					return;
				}
				pageInfo.put("curPage", cur_page+1);
				searchData();
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
		
		dataMgrSouthPanel.add(Box.createHorizontalGlue());
		dataMgrSouthPanel.add(prevPageLabel);
		dataMgrSouthPanel.add(Box.createHorizontalStrut(10));
		dataMgrSouthPanel.add(pagePos);
		dataMgrSouthPanel.add(Box.createHorizontalStrut(10));
		dataMgrSouthPanel.add(nextPageLabel);
		dataMgrSouthPanel.add(Box.createHorizontalGlue());
	}
	
	public void searchData(){
		((LzzIndexFrm)parent).showLoading();
		
		DefaultTableModel repairTableModel = (DefaultTableModel) table.getModel();
		if(repairTableModel.getRowCount()>0){
			repairTableModel.setRowCount(0);
		}
		
		String text = searchField.getText();
		JSONObject data_page = search(text);
		JSONArray repairs = data_page.getJSONArray("data");
		pageInfo = data_page.getJSONObject("pageInfo");
		if(null!=repairs){
			for(int i=0; i<repairs.size(); i++){
				JSONArray sin = repairs.getJSONArray(i);
				Object[] data = new Object[sin.size()];
				for(int data_i=0; data_i<sin.size(); data_i++){
					data[data_i] = sin.get(data_i);
				}
				
				repairTableModel.addRow(data);
			}
		}
		
		//设置页码
		pagePos.setText(pageInfo.getInt("curPage") + "/" + pageInfo.getInt("totalPage"));
		
		((LzzIndexFrm)parent).hideLoading();
	}
	/**
	 * 搜索数据
	 * @return
	 */
	public JSONObject search(String search_key){
		System.out.println("子类未重载查询函数,查询失败！");
		JSONObject rslt = new JSONObject();
		rslt.put("data", new JSONArray());
		rslt.put("pageInfo", pageInfo);
		return rslt;
	}
	/**
	 * 改变鼠标形状
	 * @param handCursor 
	 */
	protected void changeCursor(int cursor_type) {
		// TODO Auto-generated method stub
		setCursor(new Cursor(cursor_type));
	}
}
