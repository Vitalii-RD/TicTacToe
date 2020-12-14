package com.dudnyk;

import java.util.Arrays;

public class TicTacToe {
  public static final int WIN            = 0;
  public static final int DRAW           = 1;
  public static final int AVAILABLE_TURN = 2;
  public static final int ALREADY_FILLED = 3;

  private int tableSize;
  private Marks currentPlayer = Marks.player1;
  private Marks winner = Marks.empty;
  private Marks[][] table;

  public TicTacToe () {
    this(3);
  }

  public TicTacToe (int size) {
    tableSize = size;
    table = initTable(tableSize, Marks.empty);
  }

  private Marks[][] initTable(int size, Marks value) {
    Marks[][] table = new Marks[size][size];
    for(int i =0; i < size; i++) {
      Arrays.fill(table[i], value);
    }
    return table;
  }

  Marks[][] get() {
    return table;
  }
  private void nextTurn() {
    currentPlayer = currentPlayer == Marks.player1 ? Marks.player2 : Marks.player1;
  }

  public boolean isStraightLine(Marks[][] table) {
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

  public boolean isDiagonalLine(Marks[][] table) {
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

  public boolean isFree(Marks[][] table, int row, int column) {
    return table[row][column] == Marks.empty;
  }

  public boolean isFilledTable(Marks[][] table) {
    for(int i = 0; i < tableSize ; i++) {
      for (int j = 0; j < tableSize ; j++) {
        if (isFree(table, i, j)) return false;
      }
    }
    return true;
  }

  public void executeTurn(int row, int column) {
    table = setValue(row, column);
    nextTurn();
  }

  private Marks[][] setValue(int row, int column) {
    Marks[][] newTable = getCloneTable();
    newTable[row-1][column-1] = currentPlayer;
    return newTable;
  }

  private Marks[][] transposeMatrix(Marks[][] matrix) {
    Marks[][] newMatrix = new Marks[tableSize][tableSize];
    for (int i = 0; i < tableSize; i++) {
      for (int j = 0; j < tableSize; j++) {
        newMatrix[j][i] = matrix[i][j];
      }
    }
    return newMatrix;
  }

  public Marks[][] getCloneTable() {
    Marks[][] clone = new Marks[tableSize][tableSize];
    for (int i = 0; i < tableSize; i++) {
      for (int j = 0; j < tableSize; j++) {
        clone[j][i] = table[j][i];
      }
    }
    return clone;
  }


  public int getState(int row , int column) {
    if (!isFree(table, row, column)) {
      return ALREADY_FILLED;
    }
    Marks[][] newTable = setValue(row, column);

    if (isStraightLine(newTable) || isStraightLine(transposeMatrix(newTable)) || isDiagonalLine(newTable)) {
      winner = currentPlayer;
      return WIN;
    } else if (isFilledTable(newTable)) {
      return DRAW;
    } else {
      return AVAILABLE_TURN;
    }
  }

  public Marks getCurrentPlayer() {
    return currentPlayer;
  }

  public Marks getWinner() {
    return winner;
  }

  public int getTableSize() {
    return tableSize;
  }
}
