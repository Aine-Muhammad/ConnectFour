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
import java.awt.event.*;

public class Display extends JPanel implements ActionListener {
    private JButton[] inputBtn;
    private Point[] coordinates;
    private Game game;
    private JLabel lblTurn;
    private JTextField tfTurn;

    public Display() {
        game = new Game();
        setLayout(new FlowLayout());

        inputBtn = new JButton[7];
        for(int i = 0; i < inputBtn.length; i++) {
            inputBtn[i] = new JButton(i + "");
            inputBtn[i].addActionListener(this);
            add(inputBtn[i]);
        }

        coordinates = new Point[42];
        for(int i = 0; i < 42; i++) {
            coordinates[i] = new Point();
        }

        lblTurn = new JLabel("P.Turn");
        add(lblTurn);

        tfTurn = new JTextField( "1",1);
        tfTurn.setEditable(false);
        tfTurn.setBackground(Color.GRAY);
        tfTurn.setFont(new Font("Arial", Font.BOLD, 14));
        add(tfTurn);

        JOptionPane.showMessageDialog(new JFrame(), "Player 1: Green\nPlayer 2: Yellow");
        JOptionPane.showMessageDialog(new JFrame(), "Player 1 start");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.GRAY);
        drawBoard(g);
    }

    public void initCoordinates() {
        int j = 0;
        int k = 35;
        int l = 5;
        for(int i = 0; i < 42; i++) {
            coordinates[i].setLocation(inputBtn[j].getX() + l, inputBtn[j].getY() + k);
            j++;
            if(j > 6) { j = 0; k += 45; }
        }
    }

    public void drawBoard(Graphics g) {
        initCoordinates(); //[WARNING] Don't put it anywhere else especially in constructor
        for (int i = 0; i < 42; i++) {
            if(game.board[i] == 0) { g.setColor(Color.LIGHT_GRAY); }
            else if(game.board[i] == 1) { g.setColor(Color.GREEN); }
            else if(game.board[i] == 2) { g.setColor(Color.YELLOW); }
            g.fillOval(coordinates[i].x, coordinates[i].y, 35, 35);
        }
        game.playerTurn();
        if (game.turn == 1) {
            tfTurn.setForeground(Color.GREEN);
        }else {
            tfTurn.setForeground(Color.YELLOW);
        }
        tfTurn.setText(game.turn + "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < inputBtn.length; i++) {
            if((JButton)e.getSource() == inputBtn[i]) {
                //System.out.println(i);
                game.updateBoard(i);
                repaint();
                endGame(); //[WARNING] Don't put it anywhere else
            }
        }
    }

    public void endGame() {
        game.checkEndGame();
        if(game.turn == -1 && game.endGame == true) {
            //System.out.println("Draw Game");
            JOptionPane.showMessageDialog(new JFrame(), "Double KO!!!");
            JOptionPane.showMessageDialog(new JFrame(), "Press OK to start new game");
            game.initBoard();
            game.turn = 0;
            game.endGame = false;
            repaint();
        }
        else if(game.turn == 1 || game.turn == 2) {
            if(game.endGame == true) {
                //System.out.println("Player " + game.turn + " Victory!!!");
                JOptionPane.showMessageDialog(new JFrame(), "Player " + game.turn + " Victory!!!");
                JOptionPane.showMessageDialog(new JFrame(), "Press OK to start new game");
                game.initBoard();
                game.turn = 0;
                game.endGame = false;
                repaint();
            }
        }
    }
}

