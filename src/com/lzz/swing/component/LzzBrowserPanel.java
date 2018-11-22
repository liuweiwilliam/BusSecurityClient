package com.lzz.swing.component;

import java.awt.BorderLayout;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;




import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.ay;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class LzzBrowserPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4844325655697775938L;
	private Browser browser;

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}
	
	public LzzBrowserPanel(final String url) {
		try {
	        Field e = ay.class.getDeclaredField("e");
	        e.setAccessible(true);
	        Field f = ay.class.getDeclaredField("f");
	        f.setAccessible(true);
	        Field modifersField = Field.class.getDeclaredField("modifiers");
	        modifersField.setAccessible(true);
	        modifersField.setInt(e, e.getModifiers() & ~Modifier.FINAL);
	        modifersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
	        e.set(null, new BigInteger("1"));
	        f.set(null, new BigInteger("1"));
	        modifersField.setAccessible(false);
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
		
		Runnable updateAComponent = new Runnable() {
		    public void run() {
		    	setLayout(new BorderLayout(0, 0));
		    	setLayout(new BorderLayout(0, 0));
				browser = new Browser();
				BrowserView view = new BrowserView(browser);
				browser.loadURL(url);
				
				add(view, BorderLayout.CENTER);
		    }
		};
		SwingUtilities.invokeLater(updateAComponent);
	}
}
