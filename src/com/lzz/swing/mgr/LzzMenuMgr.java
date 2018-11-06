package com.lzz.swing.mgr;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.Border;

import com.lzz.swing.component.LzzImageIcon;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 菜单管理类
 * @author Administrator
 *
 */
public class LzzMenuMgr {
	/**
	 * 默认菜单图标宽度
	 */
	private static int smDeaultMenuIconWidth = 30;
	/**
	 * 默认菜单图标高度
	 */
	private static int smDeaultMenuIconHeight = 30;
	/**
	 * 默认菜单尺寸
	 */
	private static Dimension smDefaultMenuDimension = new Dimension(100, 40);
	
	/**
	 * 默认子菜单图标宽度
	 */
	private static int smDeaultSubMenuIconWidth = 20;
	/**
	 * 默认子菜单图标高度
	 */
	private static int smDeaultSubMenuIconHeight = 20;
	/**
	 * 默认子菜单尺寸
	 */
	private static Dimension smDefaultSubMenuDimension = new Dimension(80, 30);
	
	/**
	 * 默认菜单边框
	 */
	private static Border smDefaultMenuBorder = BorderFactory.createMatteBorder(0, 0, 0, 5, LzzColorFontMgr.smTextColor);
	/**
	 * 默认子菜单边框
	 */
	private static Border smDefaultSubMenuBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, LzzColorFontMgr.smTextColor);
	
	private static String smDefaultQueryIcon = "/images/query.png";
	private static String smDefaultRepairIcon = "/images/repair.png";
	private static String smDefaultSetIcon = "/images/set.png";
	private static String smDefaultImportIcon = "/images/import.png";
	private static String smDefaultAboutIcon = "/images/about.png";
	
	private static String smDefaultSubMenuIcon = "/images/subMenuIcon.png";
	
	public static JMenu createInfoQueryMenu(){
		String name = "信息查询";
		String icon = smDefaultQueryIcon;
		String[] sub_names = {"item1","item2","item3"};
		String[] sub_icons = {smDefaultSubMenuIcon, smDefaultSubMenuIcon, smDefaultSubMenuIcon};
		
		JSONObject menu_struct = createMenuStruct(name, icon, sub_names, sub_icons);
		return cerateMenu(menu_struct);
	}
	
	/**
	 * 通过菜单信息数组构建菜单结构数据 
	 * @param name 菜单名称
	 * @param sub_names 子菜单名称数组
	 * @param sub_icons 子菜单图标数组
	 * @return
	 */
	private static JSONObject createMenuStruct(String name, String icon, String[] sub_names, String[] sub_icons){
		JSONObject menu_struct = new JSONObject();
		menu_struct.put("name", name);
		menu_struct.put("icon", icon);
		
		JSONArray sub_menus = new JSONArray();
		for(int i=0; i<sub_names.length; i++){
			JSONObject menu = new JSONObject();
			menu.put("name", sub_names[i]);
			menu.put("icon", sub_icons[i]);
			sub_menus.add(menu);
		}
		menu_struct.put("subMenus", sub_menus);
		return menu_struct;
	}
	
	/**
	 * 根据菜单结构数据创建菜单
	 * @param menu_struct
	 * @return
	 */
	private static JMenu cerateMenu(JSONObject menu_struct){
		if(null==menu_struct) return null;
		
		JMenu jMenu = new JMenu(menu_struct.getString("name"));
		jMenu.setIcon(new LzzImageIcon(menu_struct.getString("icon"), smDeaultMenuIconWidth, smDeaultMenuIconHeight));
        jMenu.setForeground(LzzColorFontMgr.smTextColor);
        jMenu.setPreferredSize(smDefaultMenuDimension);
        jMenu.setBorder(smDefaultMenuBorder);
        
        JSONArray sub_menus = menu_struct.getJSONArray("subMenus");
        for(int i=0; i<sub_menus.size(); i++){
        	JSONObject menu_item = sub_menus.getJSONObject(i);
        	JMenuItem item = new JMenuItem(menu_item.getString("name"));
        	
        	String icon = menu_item.getString("icon");
        	if(null!=icon && !"".equals(icon)){
        		item.setIcon(new LzzImageIcon(icon, smDeaultMenuIconWidth, smDeaultMenuIconHeight));
        	}
        	item.setForeground(LzzColorFontMgr.smTextColor);
        	item.setPreferredSize(smDefaultSubMenuDimension);
        	item.setBorder(smDefaultSubMenuBorder);
        	jMenu.add(item);
        }
        return jMenu;
	}
}
