/*
 * Copyright 2017 jfpal.com All right reserved. This software is the
 * confidential and proprietary information of jfpal.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with jfpal.com.
 
 Created by jun.ren on 2017/3/20.
 
 */
package sudoku;

import sun.applet.Main;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class MainFram extends JFrame {
    public static int pass=1;//关卡
    public static JLabel lbPass;//显示关卡的label
    public static long usedTime=0;//玩家用时

    private SudokuCanvers panel;//主游戏区

    public static Timer userTimerAction;

    /**
     *构造函数
     */
    public MainFram(){
        //初始化方法
        inti();
        //添加组件
        addComponent();
        //添加游戏区
         addCanvers();
    }
    /*
    界面初始化
     */
    private void inti(){
        ImageIcon image=new ImageIcon("icon/icon.png");
        this.setIconImage(image.getImage());
        //出书话窗口大小
        this.setSize(515,600);
        //设置窗口初始位置
        this.setLocation(500,50);
        //设置窗体不允许改变大小
        this.setResizable(false);
        //设置窗口标题
        this.setTitle("Sudoku");
        //设置默认关闭操作
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     *添加主游戏区
     */
    private void addCanvers(){
        panel=new SudokuCanvers();
        panel.setBorder(new TitledBorder("Game Zone"));

        //将主游戏区添加到窗体中
        this.add(panel, BorderLayout.CENTER);
    }

    private void addComponent(){
        JPanel panelComponent=new JPanel();
        //添加住消息区
        addPanelMsg(panelComponent);
        //添加时间区
        addPanelTime(panelComponent);

        //将组件添加到窗体顶部
        this.add(panelComponent,BorderLayout.NORTH);
    }

    private void addPanelTime(JPanel panelComponent){
        JPanel time=new JPanel();
        time.setBorder(new TitledBorder("时间"));
        time.setLayout(new GridLayout(2,1));

        final JLabel lbSysTime=new JLabel();

        final JLabel lbUserTime=new JLabel();
        time.add(lbSysTime,BorderLayout.NORTH);
        time.add(lbUserTime,BorderLayout.SOUTH);

        //设置系统时间定时器
        Timer sysTimeAction=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long timeMillis=System.currentTimeMillis();
                SimpleDateFormat dataFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                lbSysTime.setText("系统时间:"+dataFormat.format(timeMillis));
            }
        });
        sysTimeAction.start();
        userTimerAction=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbUserTime.setText("您已用时:"+(++usedTime)+"秒");
            }
        });
        userTimerAction.start();

        panelComponent.add(time,BorderLayout.EAST);
    }
    private void addPanelMsg(JPanel panelComponent){
        panelComponent.setLayout(new GridLayout(1,3));
        Font font14=new Font("",4,14);
        Font font28=new Font("",2,28);
        JPanel msg=new JPanel();
        msg.setBorder(new TitledBorder("Message Zone"));
        JLabel lbPass1=new JLabel("关卡：第");
        lbPass1.setFont(font14);
        msg.add(lbPass1);
        //显示关卡数
        lbPass=new JLabel(String.valueOf(pass));
        lbPass.setForeground(Color.RED);
        lbPass.setFont(font28);
        msg.add(lbPass);

        JLabel lbPass2 = new JLabel("关/总共10关");
        lbPass2.setFont(font14);
        msg.add(lbPass2);

        panelComponent.add(msg, BorderLayout.CENTER);
    }
}
