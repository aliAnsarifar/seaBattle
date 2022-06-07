package shared.model.game;


import shared.model.Side;
import shared.model.ship.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Board implements Serializable {
    private  static String ships1  = "src\\main\\resources\\boards\\ships1.txt";
    private  static String ships2  = "src\\main\\resources\\boards\\ships2.txt";
    private  static String ships3  = "src\\main\\resources\\boards\\ships3.txt";
    private  static String ships4  = "src\\main\\resources\\boards\\ships4.txt";

    private ArrayList<Cell> cells = new ArrayList<>();
    private ArrayList<Ship> ships = new ArrayList<>();
    private Side side;
    private boolean ready;

    public Side getSide() {
        return side;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public Cell get(int x , int y){
        for (Cell cell:  this.cells) {
            if (cell.getX() ==x && cell.getY() ==y){
                return cell;
            }
        }
        return null;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }



    public Board(Side side) {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                Cell cell = new Cell(i , j);
                this.cells.add(cell);
            }
        }
        for (Cell cell : cells) {
            ArrayList<Cell> adjacent = new ArrayList<>();
            Cell c = get(cell.getX()+1 , cell.getY()+1);
            if ( c != null){
                adjacent.add(c);
            }
            c = get(cell.getX()+1 , cell.getY()-1);
            if (c != null ){
                adjacent.add(c);
            }
            c = get(cell.getX()-1 , cell.getY()+1);
            if (c != null ){
                adjacent.add(c);
            }
            c = get(cell.getX()-1 , cell.getY()-1);
            if (c != null ){
                adjacent.add(c);
            }

            c = get(cell.getX() , cell.getY()+1);
            if ( c != null){
                adjacent.add(c);
            }
            c = get(cell.getX() , cell.getY()-1);
            if (c != null ){
                adjacent.add(c);
            }
            c = get(cell.getX()-1 , cell.getY());
            if (c != null ){
                adjacent.add(c);
            }
            c = get(cell.getX()+1 , cell.getY());
            if (c != null ){
                adjacent.add(c);
            }
            cell.setAdjacent(adjacent);
        }
        this.side = side;
        arrangeShip();
    }



    public void arrangeShip(){
        Random random = new Random();
        int r = random.nextInt(4);

        for (Cell c : cells) {
            c.setShip(null);
        }

//        System.out.println(r  + "random int ");

        ArrayList<Ship> ships = new ArrayList<>();
        if (r == 0){
            ArrayList<Cell> cells = new ArrayList<>();
            cells.add(this.get(6 , 3));
            cells.add(this.get(7 , 3));
            cells.add(this.get(8 , 3));
            cells.add(this.get(9 , 3));
            ships.add(new BattleShip(cells));
            cells.removeAll(cells);
            cells.add(this.get(7 , 1));
            cells.add(this.get(8 , 1));
            cells.add(this.get(9 , 1));
            ships.add(new Cruiser(cells));
            cells.removeAll(cells);
            cells.add(this.get(1 , 7));
            cells.add(this.get(2 , 7));
            cells.add(this.get(3 , 7));
            ships.add(new Cruiser(cells));

            cells.removeAll(cells);
            cells.add(this.get(5 , 6));
            cells.add(this.get(6 , 6));
            ships.add(new Destroyer(cells));
            cells.removeAll(cells);
            cells.add(this.get(8 , 7));
            cells.add(this.get(8 , 8));
            ships.add(new Destroyer(cells));
            cells.removeAll(cells);
            cells.add(this.get(5 , 8));
            cells.add(this.get(5 , 9));
            ships.add(new Destroyer(cells));

            cells.removeAll(cells);
            cells.add(this.get(1 , 9));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            cells.add(this.get(3 , 9));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            cells.add(this.get(2 , 4));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            cells.add(this.get(7 , 10));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            this.ships = ships;
//            for (int i = 1; i <11 ; i++) {
//                for (int j = 1; j <11 ; j++) {
//                    if (get(j ,i).getShip() != null){
//                        System.out.print("*");
//                    }else {
//                        System.out.print("+");
//                    }
//                }
//                System.out.println();
//            }
        }
        if (r ==1){
            ArrayList<Cell> cells = new ArrayList<>();
            cells.add(this.get(4 , 1));
            cells.add(this.get(5 , 1));
            cells.add(this.get(6 , 1));
            cells.add(this.get(7 , 1));
            ships.add(new BattleShip(cells));
            cells.removeAll(cells);
            cells.add(this.get(7 , 4));
            cells.add(this.get(7 , 5));
            cells.add(this.get(7 , 6));
            ships.add(new Cruiser(cells));
            cells.removeAll(cells);
            cells.add(this.get(4 , 9));
            cells.add(this.get(5 , 9));
            cells.add(this.get(6 , 9));
            ships.add(new Cruiser(cells));
            cells.removeAll(cells);
            cells.add(this.get(1 , 4));
            cells.add(this.get(2 , 4));
            ships.add(new Destroyer(cells));
            cells.removeAll(cells);
            cells.add(this.get(5 , 4));
            cells.add(this.get(5 , 5));
            ships.add(new Destroyer(cells));
            cells.removeAll(cells);
            cells.add(this.get(3 , 6));
            cells.add(this.get(3 , 7));
            ships.add(new Destroyer(cells));
            cells.removeAll(cells);
            cells.add(this.get(1 , 7));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            cells.add(this.get(10,1));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            cells.add(this.get(9,5));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            cells.add(this.get(8 , 10));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            this.ships = ships;
//            for (int i = 1; i <11 ; i++) {
//                for (int j = 1; j <11 ; j++) {
//                    if (get(j ,i).getShip() != null){
//                        System.out.print("*");
//                    }else {
//                        System.out.print("+");
//                    }
//                }
//                System.out.println();
//            }
        }
        if (r ==2){
            ArrayList<Cell> cells = new ArrayList<>();
            cells.add(this.get(8 , 1));
            cells.add(this.get(8 , 2));
            cells.add(this.get(8 , 3));
            cells.add(this.get(8 , 4));
            ships.add(new BattleShip(cells));
            cells.removeAll(cells);

            cells.add(this.get(9 , 7));
            cells.add(this.get(9 , 8));
            cells.add(this.get(9 , 9));
            ships.add(new Cruiser(cells));
            cells.removeAll(cells);
            cells.add(this.get(6 , 6));
            cells.add(this.get(6 , 7));
            cells.add(this.get(6 , 8));
            ships.add(new Cruiser(cells));
            cells.removeAll(cells);

            cells.add(this.get(2 , 1));
            cells.add(this.get(2 , 2));
            ships.add(new Destroyer(cells));
            cells.removeAll(cells);
            cells.add(this.get(4 , 2));
            cells.add(this.get(5 , 2));
            ships.add(new Destroyer(cells));
            cells.removeAll(cells);
            cells.add(this.get(3 , 5));
            cells.add(this.get(4 , 5));
            ships.add(new Destroyer(cells));
            cells.removeAll(cells);

            cells.add(this.get(6, 4));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            cells.add(this.get(1,6));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            cells.add(this.get(4,7));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            cells.add(this.get(3 , 10));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            this.ships = ships;
//            for (int i = 1; i <11 ; i++) {
//                for (int j = 1; j <11 ; j++) {
//                    if (get(j ,i).getShip() != null){
//                        System.out.print("*");
//                    }else {
//                        System.out.print("+");
//                    }
//                }
//                System.out.println();
//            }
        }
        if (r == 3){
            ArrayList<Cell> cells = new ArrayList<>();
            cells.add(this.get(7 , 6));
            cells.add(this.get(7 , 7));
            cells.add(this.get(7 , 8));
            cells.add(this.get(7 , 9));
            ships.add(new BattleShip(cells));
            cells.removeAll(cells);

            cells.add(this.get(3 , 1));
            cells.add(this.get(4 , 1));
            cells.add(this.get(5 , 1));
            ships.add(new Cruiser(cells));
            cells.removeAll(cells);
            cells.add(this.get(2 , 5));
            cells.add(this.get(3 , 5));
            cells.add(this.get(4 , 5));
            ships.add(new Cruiser(cells));
            cells.removeAll(cells);

            cells.add(this.get(7 , 3));
            cells.add(this.get(7 , 4));
            ships.add(new Destroyer(cells));
            cells.removeAll(cells);
            cells.add(this.get(4 , 10));
            cells.add(this.get(5 , 10));
            ships.add(new Destroyer(cells));
            cells.removeAll(cells);
            cells.add(this.get(1 , 7));
            cells.add(this.get(1 , 8));
            ships.add(new Destroyer(cells));
            cells.removeAll(cells);

            cells.add(this.get(5, 3));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            cells.add(this.get(1,1));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            cells.add(this.get(4,7));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            cells.add(this.get(9 , 9));
            ships.add(new Frigate(cells));
            cells.removeAll(cells);
            this.ships = ships;
//            for (int i = 1; i <11 ; i++) {
//                for (int j = 1; j <11 ; j++) {
//                    if (get(j ,i).getShip() != null){
//                        System.out.print("*");
//                    }else {
//                        System.out.print("+");
//                    }
//                }
//                System.out.println();
//            }
        }




    }

    public static void main(String[] args) {
       Board board =  new Board(Side.PLAYER_ONE);
       board.arrangeShip();
    }


}

/**creating cells of board and set the adjacent cells:
 *
 *  for (int i = 1; i < 11; i++) {
 *             for (int j = 1; j < 11; j++) {
 *                 Cell cell = new Cell(i , j);
 *                 this.cells.add(cell);
 *             }
 *         }
 *         for (Cell cell : cells) {
 *             ArrayList<Cell> adjacent = new ArrayList<>();
 *
 *             Cell c = get(cell.getX()+1 , cell.getY()+1);
 *             if ( c != null){
 *                 adjacent.add(c);
 *             }
 *
 *             c = get(cell.getX()+1 , cell.getY()-1);
 *             if (c != null ){
 *                 adjacent.add(c);
 *             }
 *
 *             c = get(cell.getX()-1 , cell.getY()+1);
 *             if (c != null ){
 *                 adjacent.add(c);
 *             }
 *
 *             c = get(cell.getX()-1 , cell.getY()-1);
 *             if (c != null ){
 *                 adjacent.add(c);
 *             }
 *
 *             cell.setAdjacent(adjacent);
 *         }
 * */
//public void arrangeShip(){
//    Random random = new Random();
//    int r = random.nextInt(3);
//    r = 3;
//    ArrayList<Ship> ships = new ArrayList<>();
//    if (r == 0){
//
//        ArrayList<Cell> cells = new ArrayList<>();
//        cells.add(this.get(6 , 3));
//        cells.add(this.get(7 , 3));
//        cells.add(this.get(8 , 3));
//        cells.add(this.get(9 , 3));
//        System.out.println(cells.size());
//        ships.add(new BattleShip(cells));
//        cells.removeAll(cells);
//
//        cells.add(this.get(7 , 1));
//        cells.add(this.get(8 , 1));
//        cells.add(this.get(9 , 1));
//        ships.add(new Cruiser(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(1 , 7));
//        cells.add(this.get(2 , 7));
//        cells.add(this.get(3 , 7));
//        ships.add(new Cruiser(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(5 , 6));
//        cells.add(this.get(6 , 6));
//        ships.add(new Destroyer(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(8 , 7));
//        cells.add(this.get(8 , 8));
//        ships.add(new Destroyer(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(5 , 8));
//        cells.add(this.get(5 , 9));
//        ships.add(new Destroyer(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(1 , 9));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//
//
//        cells.add(this.get(3 , 9));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//
//        cells.add(this.get(2 , 4));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//
//        cells.add(this.get(7 , 10));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//
//        this.ships = ships;
//
//        for (int i = 1; i <11 ; i++) {
//            for (int j = 1; j <11 ; j++) {
//                if (get(j ,i).getShip() != null){
//                    System.out.print("*");
//                }else {
//                    System.out.print("+");
//                }
//            }
//            System.out.println();
//        }
//
//
//    }
//    if (r ==1){
//
//        ArrayList<Cell> cells = new ArrayList<>();
//        cells.add(this.get(4 , 1));
//        cells.add(this.get(5 , 1));
//        cells.add(this.get(6 , 1));
//        cells.add(this.get(7 , 1));
//        System.out.println(cells.size());
//        ships.add(new BattleShip(cells));
//        cells.removeAll(cells);
//
//        cells.add(this.get(7 , 4));
//        cells.add(this.get(7 , 5));
//        cells.add(this.get(7 , 6));
//        ships.add(new Cruiser(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(4 , 9));
//        cells.add(this.get(5 , 9));
//        cells.add(this.get(6 , 9));
//        ships.add(new Cruiser(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(1 , 4));
//        cells.add(this.get(2 , 4));
//        ships.add(new Destroyer(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(5 , 4));
//        cells.add(this.get(5 , 5));
//        ships.add(new Destroyer(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(3 , 6));
//        cells.add(this.get(3 , 7));
//        ships.add(new Destroyer(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(1 , 7));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//
//
//        cells.add(this.get(10,1));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//
//        cells.add(this.get(9,5));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//
//        cells.add(this.get(8 , 10));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//
//        this.ships = ships;
//
//        for (int i = 1; i <11 ; i++) {
//            for (int j = 1; j <11 ; j++) {
//                if (get(j ,i).getShip() != null){
//                    System.out.print("*");
//                }else {
//                    System.out.print("+");
//                }
//            }
//            System.out.println();
//        }
//
//
//    }
//    if (r ==2){
//        ArrayList<Cell> cells = new ArrayList<>();
//        cells.add(this.get(8 , 1));
//        cells.add(this.get(8 , 2));
//        cells.add(this.get(8 , 3));
//        cells.add(this.get(8 , 4));
//        System.out.println(cells.size());
//        ships.add(new BattleShip(cells));
//        cells.removeAll(cells);
//
//        cells.add(this.get(9 , 7));
//        cells.add(this.get(9 , 8));
//        cells.add(this.get(9 , 9));
//        ships.add(new Cruiser(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(6 , 6));
//        cells.add(this.get(6 , 7));
//        cells.add(this.get(6 , 8));
//        ships.add(new Cruiser(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(2 , 1));
//        cells.add(this.get(2 , 2));
//        ships.add(new Destroyer(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(4 , 2));
//        cells.add(this.get(5 , 2));
//        ships.add(new Destroyer(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(3 , 5));
//        cells.add(this.get(4 , 5));
//        ships.add(new Destroyer(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//
//        cells.add(this.get(6, 4));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//
//
//        cells.add(this.get(1,6));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//
//        cells.add(this.get(4,7));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//
//        cells.add(this.get(3 , 10));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//
//        this.ships = ships;
//
//        for (int i = 1; i <11 ; i++) {
//            for (int j = 1; j <11 ; j++) {
//                if (get(j ,i).getShip() != null){
//                    System.out.print("*");
//                }else {
//                    System.out.print("+");
//                }
//            }
//            System.out.println();
//        }
//
//    }
//    if (r == 3){
//
//        ArrayList<Cell> cells = new ArrayList<>();
//        cells.add(this.get(7 , 6));
//        cells.add(this.get(7 , 7));
//        cells.add(this.get(7 , 8));
//        cells.add(this.get(7 , 9));
//        System.out.println(cells.size());
//        ships.add(new BattleShip(cells));
//        cells.removeAll(cells);
//        cells.add(this.get(3 , 1));
//        cells.add(this.get(4 , 1));
//        cells.add(this.get(5 , 1));
//        ships.add(new Cruiser(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//        cells.add(this.get(2 , 5));
//        cells.add(this.get(3 , 5));
//        cells.add(this.get(4 , 5));
//        ships.add(new Cruiser(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//        cells.add(this.get(7 , 3));
//        cells.add(this.get(7 , 4));
//        ships.add(new Destroyer(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//        cells.add(this.get(4 , 10));
//        cells.add(this.get(5 , 10));
//        ships.add(new Destroyer(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//        cells.add(this.get(1 , 7));
//        cells.add(this.get(1 , 8));
//        ships.add(new Destroyer(cells));
//        System.out.println(cells.size());
//        cells.removeAll(cells);
//        cells.add(this.get(5, 3));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//        cells.add(this.get(1,1));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//        cells.add(this.get(4,7));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//        cells.add(this.get(9 , 9));
//        System.out.println(cells.size());
//        ships.add(new Frigate(cells));
//        cells.removeAll(cells);
//        this.ships = ships;
//        for (int i = 1; i <11 ; i++) {
//            for (int j = 1; j <11 ; j++) {
//                if (get(j ,i).getShip() != null){
//                    System.out.print("*");
//                }else {
//                    System.out.print("+");
//                }
//            }
//            System.out.println();
//        }
//    }
//}
