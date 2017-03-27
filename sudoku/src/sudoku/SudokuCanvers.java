/*
 * Copyright 2017 jfpal.com All right reserved. This software is the
 * confidential and proprietary information of jfpal.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with jfpal.com.
 
 Created by jun.ren on 2017/3/20.
 
 */
package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SudokuCanvers extends JPanel implements MouseListener {


    SudokuCell[][] cells;

    //得到数独数组

    int[][] maps=new int[9][9];

    private SelectNumFrame selectNumFrame;

    /**
     *构造函数
     * @param
     */
    public SudokuCanvers(){
        MainFram.usedTime=0;

        maps=SudokuHelper.getMap();

        //加载数独区
        this.setLayout(null);
        cells=new SudokuCell[9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                //this.remove(cells[i])
                //创建单元格
                cells[i][j]=new SudokuCell();
                cells[i][j].setLocation(20+i*50+(i/3)*5,20+j*50+(j/3)*5);
                //设置背景颜色
                if(passRole(MainFram.pass)){
                    cells[i][j].setText(String.valueOf(maps[i][j]));
                    //设置颜色
                    cells[i][j].setBackground(Color.WHITE);
                    cells[i][j].setEnabled(false);
                    cells[i][j].setForeground(Color.gray);
                }else{
                    cells[i][j].addMouseListener(this);
                }
                this.add(cells[i][j]);
            }
        }
        checkFinish();
    }

    private void checkFinish(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(!check(i,j)){
                    return;
                }
            }
        }
        //停止用户用时计时器
        MainFram.userTimerAction.stop();
        //清楚所有cell监听
        clearAllListener();
        //闯关书加一
        MainFram.pass+=1;
        if(MainFram.pass>10){
            int o=JOptionPane.showConfirmDialog(this,"恭喜您通关了，是否重新开始","",0);
            if(o==1){
                System.exit(0);
            }else{
                MainFram.pass=1;
            }
        }else{
            JOptionPane.showMessageDialog(this,"恭喜通关,用时:"+MainFram.usedTime
                +"秒\n即将进入下一关!");
        }
        //更新关卡提示
        MainFram.lbPass.setText(String.valueOf(MainFram.pass));
        //开始新的关卡
        reLoadCanvers();
        //打开用户计时器
        MainFram.userTimerAction.start();
    }

    private boolean check(int i,int j){
        if(cells[i][j].getText().isEmpty()){
            return false;
        }
        for(int k=0;k<9;k++){
            if(cells[i][j].getText().trim().equals(cells[i][k].getText().trim())&&j!=k){
                return false;
            }
            if(cells[i][j].getText().trim().equals(cells[k][j].getText().trim())&&i!=k){
                return false;
            }
            int ii=(i%3)*3+k/3;
            int jj=(j/3)*3+k%3;
            if(cells[i][j].getText().trim().equals(cells[ii][jj].getText().toString())&&!(i==ii&&j==jj)){
                return false;
            }
        }
        return true;
    }

    /**
     *重新加载数独区
     */
    public void reLoadCanvers(){
        MainFram.usedTime=0;
        maps=SudokuHelper.getMap();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                this.remove(cells[i][j]);
                //创建的单元格
                cells[i][j]=new SudokuCell();
                //设置位置
                cells[i][j].setLocation(20+i*50+(i*3)*5,20+j*50+(j/3)*5);
                if (passRole(MainFram.pass)){
                    cells[i][j].setText(String.valueOf(maps[i][j]));
                    cells[i][j].setBackground(Color.white);
                    cells[i][j].setEnabled(false);
                    cells[i][j].setForeground(Color.gray);
                }else{
                    cells[i][j].addMouseListener(this);
                }
                this.add(cells[i][j]);
            }
        }
        this.repaint();
        checkFinish();
    }

    /**
     *根据管卡随机禅城该位置是否显示数字
     * @param pass
     * @return
     */
    private boolean passRole(int pass){
        return Math.random()*11>pass;
    }
    /*
     * 根据数字获得颜色
     */
    private Color getColor(int i) {
        Color color = Color.pink;
        switch (i) {
            case 1:
                color = new Color(255, 255, 204);
                break;
            case 2:
                color = new Color(204, 255, 255);
                break;
            case 3:
                color = new Color(255, 204, 204);
                break;
            case 4:
                color = new Color(255, 204, 153);
                break;
            case 5:
                color = new Color(204, 255, 153);
                break;
            case 6:
                color = new Color(204, 204, 204);
                break;
            case 7:
                color = new Color(255, 204, 204);
                break;
            case 8:
                color = new Color(255, 255, 255);
                break;
            case 9:
                color = new Color(153, 255, 153);
                break;
            default:
                break;
        }
        return color;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int modes=e.getModifiers();
        if((modes& InputEvent.BUTTON3_MASK)!=0){
            //点击鼠标右键
            //清空单元格内容
            ((SudokuCell)e.getSource()).setText("");
        }else if((modes&InputEvent.BUTTON1_MASK)!=0){
            //点击鼠标左键
            //如果选择数字窗口存在则销毁
            if(selectNumFrame!=null){
                selectNumFrame.dispose();
            }
            //新建一个选择窗口
            selectNumFrame=new SelectNumFrame();
            //设置成模态窗口
            selectNumFrame.setModal(true);
            //设置选择串口正在显示器上的位置
            selectNumFrame.setLocation(e.getLocationOnScreen().x,e.getLocationOnScreen().y);
            //将电机的单元格传递给数字选择窗口
            selectNumFrame.setCell((SudokuCell)e.getSource());
            //显示数字选择床
            selectNumFrame.setVisible(true);
        }
        checkFinish();
    }

    /**
     *清空所有cell的点击监听
     */
    private void clearAllListener(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                cells[i][j].removeMouseListener(this);
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
