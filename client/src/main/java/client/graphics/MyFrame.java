package client.graphics;

import client.graphics.panels.AbstractPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class MyFrame extends JFrame{

    public static MyFrame instance;
    public AbstractPanel contentPanel;

    public MyFrame() {
        this.setSize(1500 , 800);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        instance = this;
    }



    public void refreshFrame(ActionEvent event){
        SwingUtilities.updateComponentTreeUI(this);
    }



    public static void refreshFrame(){
        SwingUtilities.updateComponentTreeUI(instance);
    }

    public void addPanel(AbstractPanel panel){
        this.contentPanel = panel;
        this.getContentPane().removeAll();
        this.add(panel , BorderLayout.CENTER);
        MyFrame.refreshFrame();
    }


}
