package client.graphics.panels.game;

import client.listener.EventListener;
import client.listener.internal.StringListener;
import shared.model.game.Board;
import shared.model.game.Cell;

import javax.swing.*;

import java.util.ArrayList;



public class GraphicBoard extends JPanel {

    public static int Width ;
    public static int Height ;

    private Board board;
    private ArrayList<StringListener> internalListeners = new ArrayList<>();
    public GraphicBoard(Board board , EventListener serverListener , boolean mine ) {
        this.board = board;
        this.setLayout(null);

        for (int i = 1; i <11 ; i++) {
            for (int j = 1; j <11 ; j++) {
                Cell cell  = board.get(i , j);
                GraphicCell gCell = new GraphicCell(cell , mine , serverListener);
                gCell.setBounds((GraphicCell.Width + 5) * (i-1) +30 , (GraphicCell.Height +5) * (j-1) + 30
                        , GraphicCell.Width , GraphicCell.Height );

                this.add( gCell );

            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<StringListener> getInternalListeners() {
        return internalListeners;
    }
    public void addInternalListener (StringListener stringListener){
        this.internalListeners.add(stringListener);
    }
}
