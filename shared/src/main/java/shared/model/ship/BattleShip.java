package shared.model.ship;

import shared.model.game.Cell;

import java.util.ArrayList;

public class BattleShip extends Ship{

    public BattleShip(ArrayList<Cell> cells) {
        super(cells  , 4);
        this.length = 4;
    }
}
