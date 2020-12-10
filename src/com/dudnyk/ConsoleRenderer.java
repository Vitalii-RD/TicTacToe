package com.dudnyk;

import java.util.Scanner;

public class ConsoleRenderer implements IRenderable {
  private static Scanner sc = new Scanner(System.in);
  private static String[] marks = {" ", "X", "O"};

  @Override
  public void showInit(String firstPlayer) {
    System.out.println("TIC TAC TOE");
    System.out.println("First goes " + firstPlayer);
  }

  @Override
  public void showFilled() {
    System.out.println("This position is already taken.");
  }

  @Override
  public void showPlayer(String player) {
    System.out.println(player + " player");
  }

  @Override
  public void showWin(String winner) {
    System.out.println("Winner: " + winner);
  }

  @Override
  public void showDraw() {
    System.out.println("Draw. Try again.");
  }

  @Override
  public boolean isRestart() {
    System.out.println("+ - restart");
    System.out.println("- finish");

    while (true) {
      try {
        System.out.print("-> ");
        String input = sc.next();
        System.out.println();
        if (input.equals("+")) return true;
        else if (input.equals("-")) return false;
        else throw new Exception("Wrong input");
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public void printTable(int[][] table) {
    int tableSize = table.length;
    System.out.println();

    for (int i = 0; i < tableSize; i++) {
      if (i > 0) {
        int numOfDashes = (tableSize - 1) * 3 + tableSize;
        System.out.println("-".repeat(numOfDashes));
      }
      for (int j = 0; j < tableSize; j++) {
        if (j > 0)
          System.out.print(" | ");
        System.out.print(marks[table[i][j]]);
      }
      System.out.println();
    }
    System.out.println();
  }
}
