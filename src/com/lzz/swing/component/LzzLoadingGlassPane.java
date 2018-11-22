package com.lzz.swing.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lzz.swing.util.LzzImageUtil;

public class LzzLoadingGlassPane extends JPanel {
    private static final ImageIcon ICON_PATH = new ImageIcon(LzzImageUtil.getResource("/images/loading.gif"));
    private static final long serialVersionUID = 1L;

    public LzzLoadingGlassPane() {
        setLayout(new BorderLayout());
        setOpaque(false);
        add(new JLabel(ICON_PATH));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
    }
}