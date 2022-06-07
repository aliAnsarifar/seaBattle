package shared.model.ship;

import shared.model.game.Cell;

import java.io.Serializable;
import java.util.ArrayList;

public class Ship implements Serializable {
    protected int length;
    protected boolean damaged;
    protected int damagedSquare;
    transient protected ArrayList<Cell> cells = new ArrayList<>();

    public Ship(ArrayList<Cell> cells , int length) {
        this.length = length;
        this.cells.addAll(cells);
        for (Cell cell:cells) {
            cell.setShip(this);
        }
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
        if (this.damaged){
            for (Cell cell: this.cells   ) {
                for (Cell c : cell.getAdjacent()) {
                    if (!this.cells.contains(c)){
                        c.setDestroyed(true);
                    }
                }
            }
        }
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamagedSquare(int damagedSquare) {
        this.damagedSquare = damagedSquare;
//        System.out.println("length and damaged " + this.length + " " + this.damagedSquare);
        if (this.length == this.damagedSquare){

            this.setDamaged(true);
        }
    }

    public int getDamagedSquare() {
        return damagedSquare;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }
}
