package shared.model.game;

import shared.model.Side;
import shared.model.ship.Ship;

public class Game {
    private String player1 ;
    private String player2 ;
    private Board board1;
    private Board board2;
    private Side turn;
    private int finished;



    public Game(String player1, String player2 ,Board board1 , Board board2 ,  Side turn) {
        this.player1 = player1;
        this.player2 = player2;
        this.board1 = board1 ;
        this.board2 = board2;
        this.turn = turn;
    }

    public int getFinished() {
        return finished;
    }

    public void isFinished(){
        boolean isB1destroy = true;
//        System.out.println(board1.getShips().size());
        for (Ship s : board1.getShips()) {
            if (!s.isDamaged()){
                isB1destroy = false;
                break;
            }
//            else{
//                System.out.print("ship is damaged board1");
//                for (Cell cell : s.getCells()) {
//                    System.out.println("X  Y " + cell.getX() + " "  + cell.getY());
//                }
//            }
        }

        boolean isB2destroy = true;
//        System.out.println(board2.getShips().size());
        for (Ship s : board2.getShips()) {
            if (!s.isDamaged()){
                isB2destroy = false;
                break;
            }
//            else{
//                System.out.print("ship is damaged board2");
//                for (Cell cell : s.getCells()) {
//                    System.out.println("X  Y " + cell.getX() + " "  + cell.getY());
//                }
//            }
        }
//        System.out.println("1 destroy " + isB1destroy + "  " + "2 destroy " + isB2destroy );
        if (isB1destroy){
            finished = 2;
        }
        else if (isB2destroy){
            finished = 1;
        }else {
            this.finished = 0;
        }
    }
    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public Board getBoard1() {
        return board1;
    }

    public Board getBoard2() {
        return board2;
    }

    public Side getTurn() {
        return turn;
    }

    public void setBoard1(Board board1) {
        this.board1 = board1;
    }

    public void setBoard2(Board board2) {
        this.board2 = board2;
    }

    public void setTurn(Side turn) {
        this.turn = turn;
    }

    public static void main(String[] args) {
        Game game = new Game("a" , "b" , new Board(Side.PLAYER_ONE) , new Board(Side.PLAYER_TWO) , Side.PLAYER_ONE);
        game.isFinished();
        int a = game.getFinished();
        System.out.println(a);
    }
}
