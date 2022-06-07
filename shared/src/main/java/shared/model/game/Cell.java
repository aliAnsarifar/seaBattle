package shared.model.game;

import shared.model.ship.Ship;

import java.io.Serializable;
import java.util.ArrayList;

public class Cell implements Serializable {
    private int x;
    private int y;
    transient private ArrayList<Cell> adjacent = new ArrayList<>();
    private boolean destroyed;
    private Ship ship;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.destroyed = false;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Cell> getAdjacent() {
        return adjacent;
    }

    public void setAdjacent(ArrayList<Cell> adjacent) {
        this.adjacent = adjacent;
    }
    public void addAdjacent(Cell cell){
        if (!this.adjacent.contains(cell)){
            this.adjacent.add(cell);
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        if (this.getShip() != null && !this.destroyed && destroyed){
            this.getShip().setDamagedSquare( this.getShip().getDamagedSquare() +1 );
//            System.out.println("ships one cell destroyed " + this.getShip().getDamagedSquare());
        }
        this.destroyed = destroyed;
    }
}
