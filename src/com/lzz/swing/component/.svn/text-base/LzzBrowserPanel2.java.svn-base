package com.lzz.swing.component;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;


public class LzzBrowserPanel2 extends JPanel {
	private JWebBrowser browser;

	public JWebBrowser getBrowser() {
		return browser;
	}

	public void setBrowser(JWebBrowser browser) {
		this.browser = browser;
	}
	
	public LzzBrowserPanel2(final String url) {
		Runnable updateAComponent = new Runnable() {
		    public void run() {
		    	setLayout(new BorderLayout(0, 0));
		    	browser = new JWebBrowser();
				browser.navigate(url);
				browser.setBarsVisible(false);
				browser.setButtonBarVisible(false);
				browser.setMenuBarVisible(false);
				
				add(browser, BorderLayout.CENTER);
		    }
		};
		SwingUtilities.invokeLater(updateAComponent);
	}
}
