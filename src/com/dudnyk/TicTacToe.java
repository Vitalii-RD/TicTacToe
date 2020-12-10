package com.dudnyk;

import java.util.Scanner;

public class TicTacToe {
  public static final int WIN            = 0;
  public static final int DRAW           = 1;
  public static final int AVAILABLE_TURN = 2;
  public static final int ALREADY_FILLED = 3;

  private static final Scanner sc = new Scanner(System.in);

  private static String[] marks = {" ", "X", "O"};

  private int tableSize;
  private int currentPlayer = 1;
  private int winner = 0;
  private int[][] table;

  public TicTacToe () {
    this(3);
  }

  public TicTacToe (int size) {
    tableSize = size;
    table = new int[tableSize][tableSize];
  }

  public int[] getPosition() {
    boolean isValidInput = false;
    int row = 0;
    int column = 0;

    while (!isValidInput) {
      System.out.print("Column and row for " + marks[currentPlayer] + ": ");
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
    return new int[] {row, column};
  }

  public int getState(int row , int column) {
    if (!isFree(table, row, column)) {
      return ALREADY_FILLED;
    }

    int[][] newTable = setValue(row, column);

    if (isStraightLine(newTable) || isStraightLine(transposeMatrix(newTable)) || isDiagonalLine(newTable)) {
      winner = currentPlayer;
      return WIN;
    } else if (isFilledTable(newTable)) {
      return DRAW;
    } else {
      return AVAILABLE_TURN;
    }
  }

  private void nextTurn() {
    currentPlayer = currentPlayer == 1 ? 2 : 1;
  }

  public boolean isFree(int[][] table, int row, int column) {
    return table[row-1][column-1] == 0;
  }

  public boolean isStraightLine(int[][] table) {
    boolean isLine = false;

    for (int i = 0; i < tableSize; i++) {
      isLine = true;
      for (int j = 0; j < tableSize; j++) {
        if (table[i][j] != currentPlayer) {
          isLine = false;
          break;
        }
      }
      if (isLine) break;
    }
    return isLine;
  }

  public boolean isDiagonalLine(int[][] table) {
    boolean isLine = false;
    int[][] startPoints = {{0, 0}, {tableSize-1, 0}};
    int[][] coefficients = {{1,1}, {-1, 1}};

    for (int i = 0; i < startPoints.length; i++) {
      isLine = true;
      for (int j = 0; j < tableSize; j++) {

        int row = startPoints[i][0] + coefficients[i][0] * j;
        int column = startPoints[i][1] + coefficients[i][1] * j;

        if (table[row][column] != currentPlayer) {
          isLine = false;
          break;
        }
      }
      if (isLine) break;
    }
    return isLine;
  }

  public boolean isFilledTable(int[][] table) {
    for(int i = 0; i < tableSize ; i++) {
      for (int j = 0; j < tableSize ; j++) {
        if (table[i][j] == 0) return false;
      }
    }
    return true;
  }

  private int[][] setValue(int row, int column) {
    int[][] newTable = getCloneTable();
    newTable[row-1][column-1] = currentPlayer;
    return newTable;
  }

  public void executeTurn(int row, int column) {
    table = setValue(row, column);
    nextTurn();
  }

  private int[][] transposeMatrix(int[][] matrix) {
    int[][] newMatrix = new int[tableSize][tableSize];
    for (int i = 0; i < tableSize; i++) {
      for (int j = 0; j < tableSize; j++) {
        newMatrix[j][i] = matrix[i][j];
      }
    }
    return newMatrix;
  }

  public int[][] getCloneTable() {
    int[][] clone = new int[tableSize][tableSize];
    for (int i = 0; i < tableSize; i++) {
      for (int j = 0; j < tableSize; j++) {
        clone[j][i] = table[j][i];
      }
    }
    return clone;
  }

  public String getCurrentPlayer() {
    return  marks[currentPlayer];
  }

  public String getWinner() {
    return winner != 0  ? marks[winner] : "None";
  }
}
