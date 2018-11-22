package com.lzz.swing.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jdesktop.swingx.JXDatePicker;

import com.lzz.swing.component.LzzImgLabel;
import com.lzz.swing.component.LzzRButton;
import com.lzz.swing.mgr.LzzColorFontMgr;
import com.lzz.swing.mgr.LzzImgMgr;
import com.lzz.swing.mgr.LzzLogMgr;
import com.lzz.swing.util.LzzDateUtil;
import com.lzz.swing.view.LzzIndexFrm;

public class LzzDataTablePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5345365793457250450L;
	
	private JPanel dataTableInnerNorthPanel = new JPanel();
	private JPanel dataTableInnerCenterPanel = new JPanel();
	private JPanel dataTableInnerSouthPanel= new JPanel();
	private JXDatePicker dataStartDate = new JXDatePicker();
	private JXDatePicker dataEndDate = new JXDatePicker();
	private List<LzzImgLabel> dataSerchDurLabels = new ArrayList<LzzImgLabel>();
	private JTable table = null;
	private DefaultTableModel tablemodel = null;
	private JSONObject pageInfo = null;
	private JLabel pagePos = new JLabel("1/1");
	private Object[] tableTitles; 
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private JFrame parent = null;
	private String exportFileName = "导出文件未命名";
	
	public LzzDataTablePanel(JFrame parent, Object[] tableTitles){
		this.parent = parent;
		this.tableTitles = tableTitles;
		
		this.setLayout(new BorderLayout());
		this.setBackground(LzzColorFontMgr.smUIBodyBkColor);
		
		//创建故障报修查询功能区
		createDataInnerNorthPanel();
		
		//创建故障报修查询数据区
		createDataInnerCenterPanel();
		
		//创建历史信息查询翻页区
		createRepairInfoInnerSouthPanel();
		
		this.add(dataTableInnerNorthPanel, BorderLayout.NORTH);
		this.add(dataTableInnerCenterPanel, BorderLayout.CENTER);
		this.add(dataTableInnerSouthPanel, BorderLayout.SOUTH);
		
		initPageInfo();
	}
	
	/**
	 * 创建故障报修查询数据区
	 */
	private void createDataInnerCenterPanel() {
		dataTableInnerCenterPanel.setLayout(new BorderLayout());
		
		final Object[] repairDataColumnNames = tableTitles;
		Object[][] rowData = {};
		tablemodel = new DefaultTableModel(rowData, repairDataColumnNames) {
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
		
		dataTableInnerCenterPanel.add(panel, BorderLayout.CENTER);
	}

	/**
	 * 创建历史信息查询功能区
	 */
	private void createDataInnerNorthPanel() {
		dataTableInnerNorthPanel.setLayout(new BoxLayout(dataTableInnerNorthPanel, BoxLayout.X_AXIS));
		dataTableInnerNorthPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		final LzzImgLabel today = new LzzImgLabel(LzzImgMgr.img_url_btnBk, 80, 30, "当　天");
		today.setForeground(Color.WHITE);
		today.setFont(LzzColorFontMgr.smTextFontMiddle);
		today.setHorizontalTextPosition(JLabel.CENTER);
		dataSerchDurLabels.add(today);
		today.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				changeRepairInfoSearchDur(today, LzzDateUtil.getNextNDayFromNow(0));
			}
		});
		
		final LzzImgLabel seven_day = new LzzImgLabel(LzzImgMgr.img_url_btnBk, 60, 30, "七　天");
		seven_day.setFont(LzzColorFontMgr.smTextFontMiddle);
		seven_day.setHorizontalTextPosition(JLabel.CENTER);
		dataSerchDurLabels.add(seven_day);
		seven_day.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				changeRepairInfoSearchDur(seven_day, LzzDateUtil.getNextNDayFromNow(-7));
			}
		});
		
		final LzzImgLabel one_month = new LzzImgLabel(LzzImgMgr.img_url_btnBk, 60, 30, "一个月");
		one_month.setFont(LzzColorFontMgr.smTextFontMiddle);
		one_month.setHorizontalTextPosition(JLabel.CENTER);
		dataSerchDurLabels.add(one_month);
		one_month.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				changeRepairInfoSearchDur(one_month, LzzDateUtil.getNextNMonthFromNow(-1));
			}
		});
		
		final LzzImgLabel three_month = new LzzImgLabel(LzzImgMgr.img_url_btnBk, 60, 30, "三个月");
		three_month.setFont(LzzColorFontMgr.smTextFontMiddle);
		three_month.setHorizontalTextPosition(JLabel.CENTER);
		dataSerchDurLabels.add(three_month);
		three_month.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				changeRepairInfoSearchDur(three_month, LzzDateUtil.getNextNMonthFromNow(-3));
			}
		});
		
		final LzzImgLabel six_month = new LzzImgLabel(LzzImgMgr.img_url_btnBk, 60, 30, "六个月");
		six_month.setFont(LzzColorFontMgr.smTextFontMiddle);
		six_month.setHorizontalTextPosition(JLabel.CENTER);
		dataSerchDurLabels.add(six_month);
		six_month.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				changeRepairInfoSearchDur(six_month, LzzDateUtil.getNextNMonthFromNow(-6));
			}
		});
		
		LzzRButton searchBtn = new LzzRButton("搜索");
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				searchData();
			}
		});
		
		LzzRButton exportBtn = new LzzRButton("导出");
		exportBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//exportHistoryAlarmData();
				String file_path = selectSavePath();
				if(null!=file_path){
					export(file_path);
				}
			}
		});
		
		Date date = new Date();
		dataStartDate = new JXDatePicker();
		dataEndDate = new JXDatePicker();
		dataStartDate.setDate(date);
		dataEndDate.setDate(date);
		
		dataTableInnerNorthPanel.add(today);
		dataTableInnerNorthPanel.add(Box.createHorizontalStrut(5));
		dataTableInnerNorthPanel.add(seven_day);
		dataTableInnerNorthPanel.add(Box.createHorizontalStrut(5));
		dataTableInnerNorthPanel.add(one_month);
		dataTableInnerNorthPanel.add(Box.createHorizontalStrut(5));
		dataTableInnerNorthPanel.add(three_month);
		dataTableInnerNorthPanel.add(Box.createHorizontalStrut(5));
		dataTableInnerNorthPanel.add(six_month);
		dataTableInnerNorthPanel.add(Box.createHorizontalStrut(10));
		dataTableInnerNorthPanel.add(dataStartDate);
		dataTableInnerNorthPanel.add(Box.createHorizontalStrut(10));
		dataTableInnerNorthPanel.add(dataEndDate);
		dataTableInnerNorthPanel.add(Box.createHorizontalStrut(30));
		dataTableInnerNorthPanel.add(searchBtn);
		dataTableInnerNorthPanel.add(Box.createHorizontalStrut(30));
		dataTableInnerNorthPanel.add(exportBtn);
	}
	
	/**
	 * 创建故障报修查询翻页区域
	 */
	private void createRepairInfoInnerSouthPanel() {
		dataTableInnerSouthPanel.setLayout(new BoxLayout(dataTableInnerSouthPanel, BoxLayout.X_AXIS));
		dataTableInnerSouthPanel.setPreferredSize(new Dimension(0, 40));
		
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
				System.out.println(pageInfo.toString());
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
		
		dataTableInnerSouthPanel.add(Box.createHorizontalGlue());
		dataTableInnerSouthPanel.add(prevPageLabel);
		dataTableInnerSouthPanel.add(Box.createHorizontalStrut(10));
		dataTableInnerSouthPanel.add(pagePos);
		dataTableInnerSouthPanel.add(Box.createHorizontalStrut(10));
		dataTableInnerSouthPanel.add(nextPageLabel);
		dataTableInnerSouthPanel.add(Box.createHorizontalGlue());
	}
	
	/**
	 * 筛选数据
	 */
	public void searchData(){
		((LzzIndexFrm)parent).showLoading();
		
		DefaultTableModel repairTableModel = (DefaultTableModel) table.getModel();
		if(repairTableModel.getRowCount()>0){
			repairTableModel.setRowCount(0);
		}
		
		JSONObject data_page = search();
		//JSONObject data_page = LzzLogMgr.getSensorAlarmDataDur(start, end, pageInfo);
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
	 * 切换筛选时间段执行函数
	 * @param label
	 * @param day_count
	 */
	private void changeRepairInfoSearchDur(LzzImgLabel label, Date start_date){
		//改变按钮样式
		for(LzzImgLabel l : dataSerchDurLabels){
			if(l.equals(label)){
				l.setForeground(Color.WHITE);
			}else{
				l.setForeground(Color.BLACK);
			}
		}
		
		//切换时间段
		Date date = new Date();
		dataStartDate.setDate(start_date);
		dataEndDate.setDate(date);
		
		//初始化页码
		initPageInfo();
	}
	
	/**
	 * 改变鼠标形状
	 * @param handCursor 
	 */
	protected void changeCursor(int cursor_type) {
		// TODO Auto-generated method stub
		setCursor(new Cursor(cursor_type));
	}
	
	/**
	 * 选择保存路径
	 * @return
	 */
	public String selectSavePath(){  
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");  
        String name = exportFileName + dateformat.format(new Date());  
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
        chooser.setDialogTitle("导出"+exportFileName);
          
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
	
	/**
	 * 设置导出文件的名称
	 * @param exportFileName
	 */
	public void setExportFileName(String exportFileName){
		this.exportFileName = exportFileName;
	}
	/**
	 * 获取导出文件名称
	 * @return
	 */
	public String getExportFileName(){
		return this.exportFileName;
	}
	/**
	 * 获取当前页码
	 * @return
	 */
	public JSONObject getCurPageInfo(){
		return this.pageInfo;
	}
	/**
	 * 获取筛选时间段开始时间
	 * @return
	 */
	public String getStartDate(){
		String start = sdf.format(dataStartDate.getDate()) + " 00:00:00";
		return start;
	}
	/**
	 * 获取筛选时间段结束时间
	 * @return
	 */
	public String getEndDate(){
		String end = sdf.format(dataEndDate.getDate()) + " 23:59:59";
		return end;
	}
	/**
	 * 搜索数据
	 * @return
	 */
	public JSONObject search(){
		System.out.println("子类未重载查询函数,查询失败！");
		JSONObject rslt = new JSONObject();
		rslt.put("data", new JSONArray());
		rslt.put("pageInfo", pageInfo);
		return rslt;
	}
	/**
	 * 将数据导出文件
	 * @param file_path
	 */
	public void export(String file_path){
		System.out.println("子类未重载导出函数,导出失败！");
	}
	/**
	 * 初始化页码
	 */
	private void initPageInfo(){
		pageInfo = new JSONObject();
		pageInfo.put("curPage", 1);
		pageInfo.put("pageSize", 20);
	}
	
	/**
	 * 获取表格对象
	 * @return
	 */
	public JTable getTable(){
		return table;
	}
}
