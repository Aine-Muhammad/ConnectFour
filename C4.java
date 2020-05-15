/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c4;

/**
 *
 * @author Aine
 */

import javax.swing.*;
import java.awt.*;
public class C4 {

    private static final int WIDTH = 400;
        private static final int HEIGHT = 350;

        public static void main(String[] args) {
            JFrame frame = new JFrame("Connect 4");
            frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
            frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.add(new Display());
            frame.setVisible(true);
        }
    
}
