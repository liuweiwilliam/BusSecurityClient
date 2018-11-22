package com.lzz.swing.component;

import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JTextField;

public class LzzTextField extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LzzImageIcon icon;  
	  
    public LzzTextField() {  
        Insets insets = new Insets(0, 5, 0, 5);  
        this.setMargin(insets);
    }
    
    public LzzTextField(String icon_url) {  
        //获取当前路径下的图片  
        icon = new LzzImageIcon(icon_url, 20, 20);
        
        Insets insets = new Insets(0, 0, 0, 5);
        //设置文本输入距右边5
        this.setMargin(insets);
    }
      
    @Override  
    public void paintComponent(Graphics g) {  
        Insets insets = getInsets();
        super.paintComponent(g);
        
        if(null!=icon){
        	int iconWidth = icon.getIconWidth();  
        	int iconHeight = icon.getIconHeight();  
        	int height = this.getHeight();
        	int width = this.getWidth();
        	//在文本框中画上之前图片 
        	icon.paintIcon(this, g, (width-iconWidth-insets.right), (height - iconHeight) / 2);  
        }
    }
    
    public void iconClickFunction(){
    	System.out.println("未重载");
    }
}
