package com.dudnyk;

import java.util.Scanner;

public class ConsoleRenderer implements IRender {

  private static Scanner sc = new Scanner(System.in);
  private String[] views;

  public ConsoleRenderer() {
    views =  new String[] {" ", "X", "O"};
  }

  public ConsoleRenderer(String[] views) {
    this();
    if (views.length > 2) {
      this.views = views;
    }
  }

  @Override
  public void showInit(Marks firstPlayer) {
    System.out.println("TIC TAC TOE");
    System.out.println("First goes " + views[firstPlayer.ordinal()]);
  }

  @Override
  public void showFilled() {
    System.out.println("This position is already taken.");
  }

  @Override
  public void showPlayer(Marks player) {
    System.out.println(views[player.ordinal()] + " player");
  }

  @Override
  public void showWin(Marks winner) {
    System.out.println("Winner: " + (winner != Marks.empty ? views[winner.ordinal()] : "None"));
  }

  @Override
  public void showDraw() {
    System.out.println("Draw. Try again.");
  }

  @Override
  public boolean isRestart() {
    System.out.println("+ restart");
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

  @Override
  public int[] getPosition(Marks player, int tableSize) {
    boolean isValidInput = false;
    int row = 0;
    int column = 0;

    while (!isValidInput) {
      System.out.print("Column and row for " + views[player.ordinal()] + ": ");
      try {
        row = sc.nextInt();
        column = sc.nextInt();
        if ( !(0 < row && row <= tableSize) ||
                !(0 < column && column <= tableSize) ) {
          throw new Exception();

        } else isValidInput = true;
      } catch (Exception e) {
        System.out.println("Input should contain 2 separate digits in range [1; 3]. Try again");
      }
    }
    return new int[] {row-1, column-1};
  }

  @Override
  public void printTable(Marks[][] table) {
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
        System.out.print(views[table[i][j].ordinal()]);
      }
      System.out.println();
    }

    System.out.println();
  }
}
