package gamePackage;

import java.util.concurrent.ThreadLocalRandom;

class Board{
  Cell[][] cells;

  public Board(int boardSize, int noOfSnakes, int noOfLadders){
    initializeCells(boardSize);
    addSnakesLadders(cells, noOfSnakes, noOfLadders);
  }

  void initializeCells(int size){
    cells = new Cell[size][size];
    for(int i=0;i<size;i++){
      for(int j=0;j<size;j++){
        Cell cell = new Cell();
        cells[i][j] = cell;
      }
    }
  }

  void addSnakesLadders(Cell[][] cells, int noOfSnakes, int noOfLadders){
    while(noOfSnakes > 0){
      int snakeHead = ThreadLocalRandom.current().nextInt(1, this.cells.length * this.cells.length - 1);
      int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);

      if(snakeTail > snakeHead) continue;

      Jump snake = new Jump();
      snake.start = snakeHead;
      snake.end = snakeTail;

      Cell cell = getCell(snakeHead);
      cell.jump = snake;

      noOfSnakes--;
    }

    while(noOfLadders > 0){
      int ladderHead = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length - 1);
      int ladderTail = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length - 1);

      if(ladderTail > ladderHead) continue;

      Jump ladder = new Jump();
      ladder.start = ladderHead;
      ladder.end = ladderTail;

      Cell cell = getCell(ladderHead);
      cell.jump = ladder;

      noOfLadders--;
    }
  }

  Cell getCell(int pos){
    int row = pos/cells.length;
    int col = pos%cells.length;
    return cells[row][col];
  }
}

