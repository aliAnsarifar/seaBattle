package shared.model.ship;

import shared.model.game.Cell;

import java.util.ArrayList;

public class Frigate extends Ship {

    public Frigate(ArrayList<Cell> cells) {
        super(cells  , 1);
        this.length = 1;
    }
}
