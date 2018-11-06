package com.lzz.swing.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LzzImgLabelMenu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1782764208375413576L;
	
	/**
	 * 非选中状态图标
	 */
	private String normalIcon = null;
	/**
	 * 鼠标经过状态图标
	 */
	private String hoverIcon = null;
	/**
	 * 选中状态图标
	 */
	private String selectedIcon = null;
	/**
	 * 非选中状态文字颜色
	 */
	private Color normalTextColor = null;
	/**
	 * 鼠标经过状态文字颜色
	 */
	private Color hoverTextColor = null;
	/**
	 * 选中状态文字颜色
	 */
	private Color selectedTextColor = null;
	/**
	 * 非选中状态文字字体
	 */
	private Font normalTextFont = null;
	/**
	 * 鼠标经过状态文字字体
	 */
	private Font hoverTextFont = null;
	/**
	 * 选中状态文字字体
	 */
	private Font selectedTextFont = null;
	/**
	 * 非选中状态背景颜色
	 */
	private Color normalBkColor = null;
	/**
	 * 鼠标经过状态背景颜色
	 */
	private Color hoverBkColor = null;
	/**
	 * 选中状态背景颜色
	 */
	private Color selectedBkColor = null;
	
	/**
	 * 菜单的图标
	 */
	private LzzImgLabel imageLabel = null;
	/**
	 * 菜单的文字
	 */
	private JLabel label = null;
	
	public LzzImgLabelMenu(boolean is_horizontal, String icon_url, int width, int height){
		if(is_horizontal){
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		}else{
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		}
		imageLabel = new LzzImgLabel(icon_url, width, height);
		add(imageLabel);
	}
	
	/**
	 * 创建一个拥有所有样式的菜单，包含鼠标经过/鼠标点击/鼠标划过  的  字体/文字颜色/图标/背景色
	 * @param normal_icon
	 * @param hover_icon
	 * @param selected_icon
	 * @param normal_bk_color
	 * @param hover_bk_color
	 * @param selected_bk_color
	 * @param normal_text_color
	 * @param hover_text_color
	 * @param selected_text_color
	 * @param normal_text_font
	 * @param hover_text_font
	 * @param selected_text_font
	 * @param icon_width
	 * @param icon_height
	 */
	public LzzImgLabelMenu(
			String normal_icon,
			String text,
			Color normal_bk_color,
			Color normal_text_color,
			Font normal_text_font,
			String hover_icon,
			Color hover_bk_color,
			Color hover_text_color,
			Font hover_text_font,
			String selected_icon,
			Color selected_bk_color,
			Color selected_text_color,
			Font selected_text_font,
			int icon_width,
			int icon_height,
			int menu_width,
			int menu_height,
			boolean is_horizontal){
		this.normalIcon = normal_icon;
		this.normalBkColor = normal_bk_color;
		this.normalTextFont = normal_text_font;
		this.normalTextColor = normal_text_color;
		
		this.hoverIcon = hover_icon;
		this.hoverBkColor = hover_bk_color;
		this.hoverTextFont = hover_text_font;
		this.hoverTextColor = hover_text_color;
		
		this.selectedIcon = selected_icon;
		this.selectedBkColor = selected_bk_color;
		this.selectedTextFont = selected_text_font;
		this.selectedTextColor = selected_text_color;
		
		this.label = new JLabel(text);
		
		if(null!=normal_icon){
			
		}
		
		if(null!=hover_icon){
			
		}
		
		if(null!=selected_icon){
			
		}
		
		if(null!=normal_bk_color){
			
		}
		
		if(null!=hover_bk_color){
			
		}
		
		if(null!=selected_bk_color){
			
		}
		
		if(null!=normal_text_color){
			
		}
		
		if(null!=hover_text_color){
			
		}
		
		if(null!=selected_text_color){
			
		}
		
		if(null!=normal_text_font){
			
		}
		
		if(null!=hover_text_font){
			
		}
		
		if(null!=selected_text_font){
			
		}
		
		if(is_horizontal){
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		}else{
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		}
		setMaximumSize(new Dimension(menu_width, menu_height));
		imageLabel = new LzzImgLabel(normal_icon, icon_width, icon_height, text);
		if(!is_horizontal){
			imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
			imageLabel.setHorizontalTextPosition(JLabel.CENTER);
		}
		add(imageLabel);
		
		if(null!=normal_bk_color){
			setBackground(normal_bk_color);
		}
		
		if(null!=normal_text_font){
			imageLabel.setFont(normal_text_font);
		}
		
		if(null!=normal_text_color){
			imageLabel.setForeground(normal_text_color);
		}
	}
	
	/**
	 * 创建一个包含  指定基础所有样式 + 鼠标经过文字颜色、鼠标经过背景颜色、选中文字颜色、选中背景颜色  样式的菜单
	 * @param normal_icon
	 * @param normal_text_color
	 * @param normal_bk_color
	 * @param normal_text_font
	 * @param hover_text_color
	 * @param hover_bk_color
	 * @param selected_text_color
	 * @param selected_bk_color
	 * @param width
	 * @param height
	 */
	public LzzImgLabelMenu(
			String normal_icon,
			String text,
			Color normal_text_color,
			Color normal_bk_color,
			Font normal_text_font,
			Color hover_text_color,
			Color hover_bk_color,
			Color selected_text_color,
			Color selected_bk_color,
			int icon_width,
			int icon_height,
			int menu_width,
			int menu_height,
			boolean is_horizontal){
		this(normal_icon, text, normal_bk_color, normal_text_color, normal_text_font, null, hover_bk_color, hover_text_color, null, null, selected_bk_color, selected_text_color, null, icon_width, icon_height, menu_width, menu_height, is_horizontal);
	}
	
	/**
	 * 创建一个包含  指定基础所有样式 + 选中文字颜色、选中背景颜色  样式的菜单
	 * @param normal_icon
	 * @param normal_text_color
	 * @param normal_bk_color
	 * @param normal_text_font
	 * @param selected_text_color
	 * @param selected_bk_color
	 * @param width
	 * @param height
	 */
	public LzzImgLabelMenu(
			String normal_icon,
			String text,
			Color normal_text_color,
			Color normal_bk_color,
			Font normal_text_font,
			Color selected_text_color,
			Color selected_bk_color,
			int icon_width,
			int icon_height,
			int menu_width,
			int menu_height,
			boolean is_horizontal){
		this(normal_icon, text, normal_bk_color, normal_text_color, normal_text_font, null, null, null, null, null, selected_bk_color, selected_text_color, null, icon_width, icon_height, menu_width, menu_height, is_horizontal);
	}

	public String getNormalIcon() {
		return normalIcon;
	}

	public void setNormalIcon(String normalIcon) {
		this.normalIcon = normalIcon;
	}

	public String getHoverIcon() {
		return hoverIcon;
	}

	public void setHoverIcon(String hoverIcon) {
		this.hoverIcon = hoverIcon;
	}

	public String getSelectedIcon() {
		return selectedIcon;
	}

	public void setSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
	}

	public Color getNormalTextColor() {
		return normalTextColor;
	}

	public void setNormalTextColor(Color normalTextColor) {
		this.normalTextColor = normalTextColor;
	}

	public Color getHoverTextColor() {
		return hoverTextColor;
	}

	public void setHoverTextColor(Color hoverTextColor) {
		this.hoverTextColor = hoverTextColor;
	}

	public Color getSelectedTextColor() {
		return selectedTextColor;
	}

	public void setSelectedTextColor(Color selectedTextColor) {
		this.selectedTextColor = selectedTextColor;
	}

	public Font getNormalTextFont() {
		return normalTextFont;
	}

	public void setNormalTextFont(Font normalTextFont) {
		this.normalTextFont = normalTextFont;
	}

	public Font getHoverTextFont() {
		return hoverTextFont;
	}

	public void setHoverTextFont(Font hoverTextFont) {
		this.hoverTextFont = hoverTextFont;
	}

	public Font getSelectedTextFont() {
		return selectedTextFont;
	}

	public void setSelectedTextFont(Font selectedTextFont) {
		this.selectedTextFont = selectedTextFont;
	}

	public Color getNormalBkColor() {
		return normalBkColor;
	}

	public void setNormalBkColor(Color normalBkColor) {
		this.normalBkColor = normalBkColor;
	}

	public Color getHoverBkColor() {
		return hoverBkColor;
	}

	public void setHoverBkColor(Color hoverBkColor) {
		this.hoverBkColor = hoverBkColor;
	}

	public Color getSelectedBkColor() {
		return selectedBkColor;
	}

	public void setSelectedBkColor(Color selectedBkColor) {
		this.selectedBkColor = selectedBkColor;
	}

	public LzzImgLabel getImageLabel() {
		return imageLabel;
	}

	public void setImageLabel(LzzImgLabel imageLabel) {
		this.imageLabel = imageLabel;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}
	
}
