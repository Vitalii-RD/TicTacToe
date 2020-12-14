package com.dudnyk;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TicTacToeTest {
  private TicTacToe game;
  private IRender renderer =  new ConsoleRenderer();

  @Before
  public void setUp() {
    game = new TicTacToe(3);
  }

  @Test
  public void WhenCreated__getState__returnsAVAILABLE_TURN() {
    int state = game.getState(1, 1);

    Assert.assertEquals(TicTacToe.AVAILABLE_TURN, state);
  }

  @Test
  public void WhenHorizontalLine__getState__returnsWin() {
    // X X X
    // Y Y -
    // - - -
    game.executeTurn(0, 0); // X
    game.executeTurn(1, 0); // Y
    game.executeTurn(0, 1); // X
    game.executeTurn(1, 1); // Y
    int state = game.getState(0, 2); // X

    Assert.assertEquals(TicTacToe.WIN, state);

    game.executeTurn(0, 2); // X
    renderer.printTable(game.getCloneTable());
  }

  @Test
  public void WhenVerticalLine__getState__returnsWin() {
    // X Y -
    // X Y -
    // X - -
    game.executeTurn(0, 0); // X
    game.executeTurn(0, 2); // Y
    game.executeTurn(1, 0); // X
    game.executeTurn(1, 1); // Y
    int state = game.getState(2, 0); // X

    Assert.assertEquals(TicTacToe.WIN, state);

    game.executeTurn(2, 0); // X
    renderer.printTable(game.getCloneTable());
  }

  @Test
  public void WhenDiagonalLine__getState__returnsWin() {
    // X - Y
    // - X Y
    // - - X
    game.executeTurn(0, 0); // X
    game.executeTurn(0, 2); // Y
    game.executeTurn(1, 1); // X
    game.executeTurn(1, 2); // Y
    int state = game.getState(2, 2); // X
    Assert.assertEquals(TicTacToe.WIN, state);

    game.executeTurn(2, 2); // X
    renderer.printTable(game.getCloneTable());
  }

  @Test
  public void WhenDraw__getState__returnsDraw() {
    // X Y X
    // Y Y X
    // X X Y

    // X Y X
    game.executeTurn(0, 0); // X
    game.executeTurn(0, 1); // Y
    game.executeTurn(0, 2); // X

    // Y Y X
    game.executeTurn(1, 0); // Y
    game.executeTurn(1, 2); // X
    game.executeTurn(1, 1); // Y

    // X X Y
    game.executeTurn(2, 0); // X
    game.executeTurn(2, 2); // Y
    int state = game.getState(2, 1); // X

    Assert.assertEquals(TicTacToe.DRAW, state);

    game.executeTurn(2, 1); // X
    renderer.printTable(game.getCloneTable());
  }

  @Test
  public void WhenFilled_getState__returns_ALREADY_FILLED() {
    game.executeTurn(0, 0);
    int state = game.getState(0, 0);

    Assert.assertEquals(TicTacToe.ALREADY_FILLED, state);
  }

  @Test
  public void WhenInvoked__nextTurn__changes_CurrentPlayer() {
    game.executeTurn(0, 0);

    Assert.assertEquals(Marks.player2, game.getCurrentPlayer());
  }

  @Test
  public void WhenFirstInvoked__getCurrentPlayer__returns_X() {
    Assert.assertEquals(Marks.player1, game.getCurrentPlayer());
  }

  @After
  public void TearDown() {
    game = null;
  }
}