package com.lzz.swing.component;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class BrowserPanel extends JPanel {
	private JWebBrowser browser;

	public JWebBrowser getBrowser() {
		return browser;
	}

	public void setBrowser(JWebBrowser browser) {
		this.browser = browser;
	}
	
	public BrowserPanel(String url) {
		setLayout(new BorderLayout(0, 0));
		browser=new JWebBrowser();
		browser.navigate(url);
		browser.setBarsVisible(false);
		browser.setButtonBarVisible(false);
		browser.setMenuBarVisible(false);
		add(browser, BorderLayout.CENTER);
	}
}
