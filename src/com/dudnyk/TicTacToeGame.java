package com.dudnyk;

import java.util.Scanner;

public class TicTacToeGame {

  private static final Scanner sc = new Scanner(System.in);
  private TicTacToe ticTacToe;

  public TicTacToeGame() {
      ticTacToe = new TicTacToe();
  }

  public void play() {
    System.out.println("TIC TAC TOE");
    int[] position = ticTacToe.getPosition();

    while (true){
      int state = ticTacToe.getState(position[0], position[1]);

      if (state == TicTacToe.WIN) {

        System.out.println("Winner: " + ticTacToe.getCurrentPlayer());
        break;

      } else if (state == TicTacToe.DRAW) {

        System.out.println("Draw. Try again");
        break;

      } else if (state == TicTacToe.ALREADY_FILLED) {

        System.out.println("This position is already taken.");
        position = ticTacToe.getPosition();

      } else if (state == TicTacToe.AVAILABLE_TURN) {

        ticTacToe.executeTurn(position[0], position[1]);
        ticTacToe.printTable(ticTacToe.getTable());

        // check if player have not won;
        int newState = ticTacToe.getState(position[0], position[1]);
        if (newState == TicTacToe.ALREADY_FILLED) {
          ticTacToe.nextTurn();
          position = ticTacToe.getPosition();
        }
      }
    }
    if (isRestart()) new TicTacToeGame().play();
  }

  private boolean isRestart() {
    System.out.println("+ - restart");
    System.out.println("- finish");

    while (true) {
      try {
        String input = sc.next();
         if (input.equals("+")) return true;
         else if (input.equals("-")) return false;
         else throw new Exception("Wrong input");
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
