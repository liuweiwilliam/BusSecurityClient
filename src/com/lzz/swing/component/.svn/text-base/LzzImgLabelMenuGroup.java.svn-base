package com.lzz.swing.component;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class LzzImgLabelMenuGroup extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7461629046118975764L;
	
	private Map<String, LzzImgLabelMenu> menus = new Hashtable<String, LzzImgLabelMenu>();
	private Map<String, JPanel> panels = new Hashtable<String, JPanel>();
	private String curSelectedMenu = null;
	private Component separate = null;
	
	/**
	 * 初始化函数，新建一个菜单组面板
	 * @param is_horizontal 菜单排列方向，true表示菜单水平排列，false表示菜单垂直排列
	 */
	public LzzImgLabelMenuGroup(boolean is_horizontal){
		if(is_horizontal){
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			separate = Box.createHorizontalStrut(5);
		}else{
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			separate = Box.createVerticalStrut(5);
		}
	}
	
	/**
	 * 新建一个菜单水平排列的菜单组面板
	 */
	public LzzImgLabelMenuGroup(){
		this(true);
	}
	
	public void addMenu(final String name, LzzImgLabelMenu menu, JPanel panel){
		if(null==menu) return;
		
		//记录菜单信息
		menus.put(name, menu);
		panels.put(name, panel);
		
		//添加菜单鼠标事件
		menu.addMouseListener(new MouseAdapter(){
			/**
			 * 鼠标点击事件,切换至选中样式,并切换面板
			 */
			public void mouseClicked(MouseEvent e){
				if (e.getButton()==MouseEvent.BUTTON1){
					selectMenu(name);
				}
			}
			/**
			 * 鼠标进入，切换鼠标经过样式
			 */
			public void mouseEntered(MouseEvent e) {
				setHoverStyleSingle(name);
			}
			/**
			 * 鼠标离开，切换正常样式(如果之前是选择状态，则切换成选择状态)
			 */
			public void mouseExited(MouseEvent e) {
				if(name.equals(curSelectedMenu)){
					//切换选中样式
					setSelectedStyleSingle(name);
				}else{
					//切换普通样式
					setNormalStyleSingle(name);
				}
			}
		});
		
		//将菜单添加进面板
		if(menus.size()>0){//如果菜单中已经有数据，说明在新加入的菜单之前已经有菜单，需要添加分隔组件
			add(separate);
		}
		
		if(null!=panel){
			panel.setVisible(false);
		}
		add(menu);
	}
	
	/**
	 * 选中某菜单
	 * @param name
	 */
	public void selectMenu(String name){
		String prev_selected = curSelectedMenu;
		
		curSelectedMenu = name;
		setSelectedStyle(name);
		
		//隐藏之前选中的面板面板
		hidePanelSingle(prev_selected);
		showPanelSingle(name);
	}
	
	/**
	 * 显示单个面板
	 * @param name
	 */
	private void showPanelSingle(String name){
		if(null==name) return;
		JPanel seleccted_panel = panels.get(name);
		if(null==seleccted_panel) return;
		seleccted_panel.setVisible(true);
	}
	
	/**
	 * 隐藏单个面板
	 * @param name
	 */
	private void hidePanelSingle(String name){
		if(null==name) return;
		JPanel seleccted_panel = panels.get(name);
		if(null==seleccted_panel) return;
		seleccted_panel.setVisible(false);
	}
	
	/**
	 * 切换菜单为选中样式,同时将其他菜单切换成非选中
	 * @param name
	 */
	private void setSelectedStyle(String name){
		if(null==name) return;
		
		setSelectedStyleSingle(name);
		setOthersNormalStyle(name);
	}
	
	/**
	 * 设置菜单选中样式
	 * @param name
	 */
	private void setSelectedStyleSingle(String name){
		LzzImgLabelMenu menu = menus.get(name);
		if(null==name) return;
		
		if(null!=menus.get(name).getSelectedIcon()){
			menu.getImageLabel().changeIcon(menus.get(name).getSelectedIcon());
		}
		if(null!=menus.get(name).getSelectedTextColor()){
			menu.getLabel().setForeground(menus.get(name).getSelectedTextColor());
		}
		if(null!=menus.get(name).getSelectedTextFont()){
			menu.getLabel().setFont(menus.get(name).getSelectedTextFont());
		}
		if(null!=menus.get(name).getSelectedBkColor()){
			menu.setBackground(menus.get(name).getSelectedBkColor());
		}
	}
	
	/**
	 * 设置菜单非选中样式
	 * @param name
	 */
	private void setNormalStyleSingle(String name){
		LzzImgLabelMenu menu = menus.get(name);
		if(null==name) return;
		
		if(null!=menus.get(name).getNormalIcon()){
			menu.getImageLabel().changeIcon(menus.get(name).getNormalIcon());
		}
		if(null!=menus.get(name).getNormalTextColor()){
			menu.getLabel().setForeground(menus.get(name).getNormalTextColor());
		}
		if(null!=menus.get(name).getNormalTextFont()){
			menu.getLabel().setFont(menus.get(name).getNormalTextFont());
		}
		if(null!=menus.get(name).getNormalBkColor()){
			menu.setBackground(menus.get(name).getNormalBkColor());
		}
	}
	
	/**
	 * 设置菜单鼠标经过样式
	 * @param name
	 */
	private void setHoverStyleSingle(String name){
		LzzImgLabelMenu menu = menus.get(name);
		if(null==name) return;
		
		if(null!=menus.get(name).getHoverIcon()){
			menu.getImageLabel().changeIcon(menus.get(name).getHoverIcon());
		}
		
		if(null!=menus.get(name).getHoverTextColor()){
			menu.getLabel().setForeground(menus.get(name).getHoverTextColor());
		}
		
		if(null!=menus.get(name).getHoverTextFont()){
			menu.getLabel().setFont(menus.get(name).getHoverTextFont());
		}
		
		if(null!=menus.get(name).getHoverBkColor()){
			menu.setBackground(menus.get(name).getHoverBkColor());
		}
	}
	
	/**
	 * 设置除name以外的菜单为非选中样式
	 * @param name
	 */
	private void setOthersNormalStyle(String name){
		for (String key : menus.keySet()){
			if(key.equals(name)){
				continue;
			}
			
			setNormalStyleSingle(key);
		}
	}

	/**
	 * 设置菜单的分隔组件
	 * @param separate
	 */
	public void setSeparate(Component separate) {
		this.separate = separate;
	}
}
