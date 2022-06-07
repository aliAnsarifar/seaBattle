package shared.model.ship;

import shared.model.game.Cell;

import java.io.Serializable;
import java.util.ArrayList;

public class HelpShips implements Serializable {
    public ArrayList<Ship> ships;
    public ArrayList<Cell> cells;

    public HelpShips(ArrayList<Ship> ships  , ArrayList<Cell> cells) {
        this.ships = ships;
        this.cells = cells;
    }
}
