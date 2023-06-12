package gamePackage;

import java.util.Deque;
import java.util.LinkedList;

//Game
class Game{
  Board board;
  Dice dice;
  Deque<Player> players = new LinkedList<>();
  Player winner;

  public Game(){
    initializeGame();
  }

  void initializeGame(){
    board = new Board(10, 5, 4);
    dice = new Dice(1);
    winner = null;
    addPlayers();
  }

  void addPlayers(){
    players.add(new Player("p1", 0));
    players.add(new Player("p2", 0));
  }

  void startGame(){
    while(winner == null){
      Player playerTurn = findPlayerTurn();
      System.out.println("the player turn is: " + playerTurn.id + 
                         " his current position is: " +       playerTurn.position);

      int diceNumber = dice.rollDice();

      int newPos = playerTurn.position + diceNumber;
      newPos = jumpCheck(newPos);
      playerTurn.position = newPos;
      
      System.out.println("the player turn is: " + playerTurn.id + 
                         " his new position is: " +       newPos);

      if(newPos >= board.cells.length * board.cells.length - 1){
        winner = playerTurn;
      }
    }
    System.out.println("The Winner is: " + winner.id);
  }

  Player findPlayerTurn(){
    Player player = players.removeFirst();
    players.add(player);
    return player;
  }

  
  int jumpCheck(int pos){
    if(pos > board.cells.length * board.cells.length - 1){
        return pos;
    }

    Cell cell = board.getCell(pos);
    if(cell.jump != null && cell.jump.start == pos){
      String jumpBy = cell.jump.start < cell.jump.end ? "ladder" : "snake";
      System.out.println("Jump by: " + jumpBy);
      return cell.jump.end;
    }

    return pos;
  }
}
