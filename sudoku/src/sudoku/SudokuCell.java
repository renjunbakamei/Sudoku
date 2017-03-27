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

public class SudokuCell extends JButton {
    public SudokuCell(){
        this.setSize(50,50);
        Font font=new Font("",2,24);
        this.setFont(font);
        this.setBackground(new Color(255,153,102));
        this.setForeground(Color.BLUE);
    }
}
