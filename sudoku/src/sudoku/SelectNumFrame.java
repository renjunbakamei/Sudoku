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

public class SelectNumFrame extends JDialog implements MouseListener {

    private SudokuCell cell;

    public void setCell(SudokuCell cell){
        this.cell=cell;
    }

    public SelectNumFrame(){
        //影藏界面上的工具栏
        this.setUndecorated(true);
        this.setSize(150,150);
        this.setBackground(new Color(255,204,153,123));
        this.setLayout(null);

    }

    //天假数字1~9

    private void addNum(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                JButton button=new JButton();
                button.setSize(50,50);
                button.setLocation(i*50,j*50);
                button.setText(""+(j*3+i+1));
                button.addMouseListener(this);
                this.add(button);
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int modes=e.getModifiers();
        {
            if((modes& InputEvent.BUTTON1_DOWN_MASK)!=0){
                JButton button=(JButton)e.getSource();
                cell.setText(button.getText());
            }
        }

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
