package client.graphics.panels.game;

import client.listener.EventListener;
import shared.events.ClickBoardEvent;
import shared.model.game.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicCell extends JPanel {
    private Cell cell;
    private boolean isMine;
    public static int Width ;
    public static int Height ;

    public GraphicCell(Cell cell , boolean isMine , EventListener serverListener) {

        this.cell = cell;
        this.isMine = isMine;
        if (isMine){
            if (cell.getShip() !=null){
                this.setBackground(Color.BLUE);
            }else{
                this.setBackground(Color.BLACK);
            }
            if (cell.isDestroyed()){
                this.repaint();
            }
        }else{
            this.setBackground(Color.BLACK);
            if (cell.isDestroyed()){
                this.repaint();
                if (cell.getShip() != null && cell.getShip().isDamaged() ){
                    this.setBackground(Color.BLUE);
                }
            }
        }
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!isMine && !cell.isDestroyed()) {
                    serverListener.listen(new ClickBoardEvent(cell.getX() , cell.getY()));
                }
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (this.cell.isDestroyed() && this.isMine ) {
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setStroke(new BasicStroke(8));
            graphics2D.setColor(Color.RED);
            graphics2D.drawLine(0, 0, 50, 50);
        }
        if (this.cell.isDestroyed() && !this.isMine){
            if (this.cell.getShip() == null){
                Graphics2D graphics2D = (Graphics2D) g;
                graphics2D.setStroke(new BasicStroke(8));
                graphics2D.setColor(Color.RED);
                graphics2D.drawLine(0, 0, 50, 50);
            }else{
                Graphics2D graphics2D = (Graphics2D) g;
                graphics2D.setStroke(new BasicStroke(8));
                graphics2D.setColor(Color.RED);
                graphics2D.drawLine(0, 0, 50, 50);
                graphics2D.setColor(Color.GREEN);
                graphics2D.drawLine(0 , 50 , 50 , 0);
            }
        }
    }
}
