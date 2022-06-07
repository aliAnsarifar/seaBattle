package shared.model.ship;

import shared.model.game.Cell;

import java.util.ArrayList;

public class Destroyer extends Ship{
    public Destroyer(ArrayList<Cell> cells) {
        super(cells  , 2);
        this.length = 2;
    }
}
