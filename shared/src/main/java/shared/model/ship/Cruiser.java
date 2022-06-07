package shared.model.ship;

import shared.model.game.Cell;

import java.util.ArrayList;

public class Cruiser extends Ship{
    public Cruiser(ArrayList<Cell> cells) {
        super(cells , 3);
        this.length = 3;
    }
}
