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


public class Game {

    public int[] board;
    public boolean endGame;
    public boolean flipTurn;
    public int turn;

    public Game() {
        board = new int[42];
        flipTurn = true;
        turn = 0;
        endGame = false;
    }

    public void initBoard() {
        for(int i = 0; i < board.length; i++) {
            board[i] = 0;
        }
    }

    public void updateBoard(int in) {
        for (int i = board.length - 1; i >= 0; i--) {
            if(in % 7 == i % 7 && board[i] == 0) {
                board[i] = turn;
                return;
            }
        }
        flipTurn = !flipTurn;
        //System.out.println("Filled Already");
        JOptionPane.showMessageDialog(new JFrame(), "This column is already filled");
    }

    public void playerTurn() {
        if(!flipTurn) { this.turn = 1; }
        else { this.turn = 2; }
        flipTurn = !flipTurn;
    }

    public void checkRow() {
        int counter = 0;
        for(int i = 0; i < board.length; i++) {
            if(i % 7 == 0) { counter = 0; }
            if(board[i] == turn) { counter++; }
            else { counter = 0; }
            if(counter >= 4) { endGame = true; break; }
        }
    }

    public void checkColumn() {
        int j;
        int counter;

        for(int i=0 ; i<7 ; i++ ){
             j = i;
             counter =0;
            while (counter <3){
                if(board[j] == turn && board[j+7] == turn && board[j+14] == turn && board[j+21] == turn){
                    endGame=true; break;
                }
                counter++;
                j+=7;
            }
            counter=0;
        }
    }

    public void checkDiagonal() {
        frontDiagonal();
        backDiagonal();
    }

    public void frontDiagonal() {
        int counter = 21;
        int i =0;
        while (counter <39){
            if(board[counter] == turn && board[counter-6] == turn && board[counter-12] == turn && board[counter-18] == turn){
                endGame = true; break;
            }
            counter++;
            i++;
            if(i == 3){
                counter+=4;
                i=0;
            }
        }

    }

    public void backDiagonal() {
        int counter = 24;
        int i = 0;
        while (counter < 42) {
            if(board[counter] == turn && board[counter-8] == turn && board[counter-16] == turn && board[counter-24] == turn){
                endGame = true; break;
            }
            counter++;
            i++;
            if(i == 3){
                counter+=4;
                i=0;
            }
        }
    }

    public void checkDrawGame() {
        int counter = 0;
        for(int i = 0; i < board.length; i++) {
            if(board[i] == 0) { counter++; }
        }
        if(counter == 0) {
            turn = -1;
            endGame = true;
        }
    }

    public void checkEndGame() {
        checkRow();
        checkColumn();
        checkDiagonal();
        checkDrawGame();
    }

}
