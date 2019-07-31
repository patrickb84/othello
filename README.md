# Othello

The game "Othello" written in Java with a GUI. This was an assignment for _Advanced Programming_.

**:game_die: Play it at [https://repl.it/@PatrickBradshaw/Othello](https://repl.it/@PatrickBradshaw/Othello)**

![Othello cover image](https://i.ibb.co/2k5WC20/othello-cover-2.jpg)

---

Notably, the function `findLegalSquares()` contains nested loops and conditional statements. It determines and marks what squares are legal.

```java
private void findLegalSquares(Status currentDiscColor, int rowStep, int colStep) {
  int rowX;
  int colX;

  for (int i = 0; i < 8; i++) {
    for (int j = 0; j < 8; j++) {
      /* Find current players discs, move in a direction */
      if (squares[i][j].getStatus() == currentDiscColor) {
        rowX = i + rowStep;
        colX = j + colStep;

        /* Stay within board boundaries */
        if (rowX < 0 || rowX > 7 || colX < 0 || colX > 7) {
          continue;
        }
        /* If the next square is opposite, keep moving */
        while (squares[rowX][colX].getStatus() == oppositeDisc(currentDiscColor)) {
          rowX = rowX + rowStep;
          colX = colX + colStep;
          /* Stay in bounds */
          if (rowX < 0 || rowX > 7 || colX < 0 || colX > 7) {
            break;
          }
          /* Then if the next square is open, it is legal */
          if (squares[rowX][colX].getStatus() == Status.OPEN) {
            squares[rowX][colX].updateStatus(Status.LEGAL);
            repaint();
          }
        }
      }
    }
  }
}
```

Also the function `othelloFlip()` checks for flip-able discs.

```java
public void othelloFlip(int rowStart, int colStart, Status turnColor, int rowStep, int colStep) {
  int rowX = rowStart + rowStep;
  int colX = colStart + colStep;

  /* Stay in bounds */
  if (rowX < 0 || rowX > 7 || colX < 0 || colX > 7) {
    return;
  }
  /**
   *  We need status of square in the direction we are checking
   *  Keep checking until we hit empty cells
   */
  while (squares[rowX][colX].getStatus() == Status.BLACK || squares[rowX][colX].getStatus() == Status.WHITE) {
    /**
     *  Return direction to flip chips
     *  Run else statement till we hit a cell with the same color
     */
    if (squares[rowX][colX].getStatus() == turnColor) {
      while(!(rowStart == rowX && colStart == colX)) {
        squares[rowX][colX].updateStatus(turnColor);
        rowX = rowX - rowStep;
        colX = colX - colStep;
      }
      break;
    }
    /* Moving to next cell in direction to check for chip color change */
    else {
      rowX = rowX + rowStep;
      colX = colX + colStep;
    }

    /* Check to keep us on the board, break when we are off board */
    if (rowX < 0 || rowX > 7 || colX < 0 || colX > 7) {
      break;
    }
  }
}
```

![Othello cover image](https://i.ibb.co/dcH1f9h/othello-cover.jpg)
