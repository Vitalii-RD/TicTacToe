package com.dudnyk;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TicTacToeTest {
  private TicTacToe game;
  private IRenderable renderer =  new ConsoleRenderer();

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
    game.executeTurn(1, 1); // X
    game.executeTurn(2, 1); // Y
    game.executeTurn(1, 2); // X
    game.executeTurn(2, 2); // Y
    int state = game.getState(1, 3); // X

    Assert.assertEquals(TicTacToe.WIN, state);

    game.executeTurn(1, 3); // X
    renderer.printTable(game.getCloneTable());
  }

  @Test
  public void WhenVerticalLine__getState__returnsWin() {
    // X Y -
    // X Y -
    // X - -
    game.executeTurn(1, 1); // X
    game.executeTurn(1, 2); // Y
    game.executeTurn(2, 1); // X
    game.executeTurn(2, 2); // Y
    int state = game.getState(3, 1); // X

    Assert.assertEquals(TicTacToe.WIN, state);

    game.executeTurn(3, 1); // X
    renderer.printTable(game.getCloneTable());
  }

  @Test
  public void WhenDiagonalLine__getState__returnsWin() {
    // X - Y
    // - X Y
    // - - X
    game.executeTurn(1, 1); // X
    game.executeTurn(1, 3); // Y
    game.executeTurn(2, 2); // X
    game.executeTurn(2, 3); // Y
    int state = game.getState(3, 3); // X
    Assert.assertEquals(TicTacToe.WIN, state);

    game.executeTurn(3, 3); // X
    renderer.printTable(game.getCloneTable());
  }

  @Test
  public void WhenDraw__getState__returnsDraw() {
    // X Y X
    // Y Y X
    // X X Y

    // X Y X
    game.executeTurn(1, 1); // X
    game.executeTurn(1, 2); // Y
    game.executeTurn(1, 3); // X

    // Y Y X
    game.executeTurn(2, 1); // Y
    game.executeTurn(2, 3); // X
    game.executeTurn(2, 2); // Y

    // X X Y
    game.executeTurn(3, 1); // X
    game.executeTurn(3, 3); // Y
    int state = game.getState(3, 2); // X

    Assert.assertEquals(TicTacToe.DRAW, state);

    game.executeTurn(3, 2); // X
    renderer.printTable(game.getCloneTable());
  }

  @Test
  public void WhenFilled_getState__returns_ALREADY_FILLED() {
    game.executeTurn(1, 1);
    game.executeTurn(1, 1);
    int state = game.getState(1, 1);

    Assert.assertEquals(TicTacToe.ALREADY_FILLED, state);
  }

  @Test
  public void WhenInvoked__nextTurn__changes_CurrentPlayer() {
    game.executeTurn(1, 1);

    Assert.assertEquals(2, game.getCurrentPlayer());
  }

  @Test
  public void WhenFirstInvoked__getCurrentPlayer__returns_X() {
    Assert.assertEquals(1, game.getCurrentPlayer());
  }

  @After
  public void TearDown() {
    game = null;
  }
}