package com.lzz.swing.component;

import java.awt.GridBagConstraints;

public class LzzGridConstraints extends GridBagConstraints {
	public LzzGridConstraints(float weightx, float weighty, int gridx, int gridy){
		this.weightx = weightx;
		this.weighty = weighty;
		this.gridx = gridx;
		this.gridy = gridy;
		this.fill = GridBagConstraints.BOTH;
	}
	
	public LzzGridConstraints(float weightx, float weighty, int gridx, int gridy, int fill){
		this.weightx = weightx;
		this.weighty = weighty;
		this.gridx = gridx;
		this.gridy = gridy;
		this.fill = fill;
	}
	
	public LzzGridConstraints(float weightx, float weighty, int gridx, int gridy, int fill, int anchor){
		this.weightx = weightx;
		this.weighty = weighty;
		this.gridx = gridx;
		this.gridy = gridy;
		this.fill = fill;
		this.anchor = anchor;
	}
	
	public LzzGridConstraints(int gridx, int gridy){
		this.weightx = 1.0;
		this.weighty = 1.0;
		this.gridx = gridx;
		this.gridy = gridy;
		this.fill = GridBagConstraints.BOTH;
	}
}
