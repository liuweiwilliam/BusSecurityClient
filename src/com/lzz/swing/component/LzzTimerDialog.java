package com.lzz.swing.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LzzTimerDialog {  
    private String message = null;  
    private int secends = 0;  
    private JLabel label = new JLabel();  
    private LzzRButton confirm,cancel;   
    private JDialog dialog = null;  
    int result = 1;  
    public int showDialog(JFrame father, String message, int sec) {  
        this.message = message;  
        secends = sec;  
        label.setText(message);  
        //label.setBounds(100,6,200,20);
        confirm = new LzzRButton("继续");  
        //confirm.setBounds(100,40,60,20);
        confirm.addActionListener(new ActionListener() {              
            @Override  
            public void actionPerformed(ActionEvent e) {  
                result = 0;  
                LzzTimerDialog.this.dialog.dispose();  
            }  
        }); 
        cancel = new LzzRButton("停止"); 
        //cancel.setBounds(190,40,60,20);  
        cancel.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                result = 1;  
                LzzTimerDialog.this.dialog.dispose();  
            }  
        });  
        dialog = new JDialog(father, true);  
        dialog.setTitle("提示: 本窗口将在"+secends+"秒后自动关闭");  
        dialog.setLayout(new BorderLayout());  
        dialog.add(label, BorderLayout.NORTH); 
        
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.add(Box.createHorizontalGlue());
        btnPanel.add(confirm);  
        btnPanel.add(Box.createHorizontalStrut(30));
        btnPanel.add(cancel);
        btnPanel.add(Box.createHorizontalGlue());
        dialog.add(btnPanel);
        
        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();  
        s.scheduleAtFixedRate(new Runnable() {  
            @Override  
            public void run() {  
                LzzTimerDialog.this.secends--;  
                if(LzzTimerDialog.this.secends == 0) {  
                    LzzTimerDialog.this.dialog.dispose();  
                }else {  
                    dialog.setTitle("提示: 本窗口将在"+secends+"秒后自动关闭");  
                }  
            }  
        }, 1, 1, TimeUnit.SECONDS);  
        
        dialog.setSize(new Dimension(350,100));  
        dialog.setLocationRelativeTo(father);  
        dialog.setVisible(true);  
        return result;  
    }  
}
