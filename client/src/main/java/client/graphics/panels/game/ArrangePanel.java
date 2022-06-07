package client.graphics.panels.game;

import client.graphics.MyFrame;
import client.graphics.panels.AbstractPanel;
import client.graphics.panels.game.GraphicBoard;
import client.listener.EventListener;
import client.listener.internal.StringListener;
import shared.events.ArrangeEvent;
import shared.events.Event;
import shared.events.ReadyBoardEvent;
import shared.model.game.Board;

import javax.swing.*;
import java.awt.*;

public class ArrangePanel extends AbstractPanel {
    private EventListener serverListener;
    private StringListener internalListener;
    int arranging = 3 ;
    private Board board;
    private GraphicBoard gBoard;
    private Timer timer;

    public void setBoard(Board board) {
        this.board = board;
        this.remove(this.gBoard);
        setGBoard(board);
    }

    public ArrangePanel(EventListener serverListener, StringListener internalListener , Board board ) {
        this.board = board;
        this.serverListener = serverListener;
        this.internalListener = internalListener;

        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setBounds(1000  , 50 , 300 , 80);
        progressBar.setValue(0);
        progressBar.setMaximum(20);
        this.timer = new Timer(1000 , (e -> {
            MyFrame.refreshFrame();
            progressBar.setValue(progressBar.getValue() + 1);
            if (progressBar.getMaximum() <= progressBar.getValue()){
                System.out.println("ready board event!!!");
                serverListener.listen(new ReadyBoardEvent());
                timer.stop();
            }
        }));
        this.timer.start();

        JButton readyBtn = new JButton("ready");
        readyBtn.addActionListener(e -> {
            System.out.println("ready board event!!!");
            serverListener.listen(new ReadyBoardEvent());
            timer.stop();
        });
        readyBtn.setBounds(1350 , 600 , 100 , 70);

        JButton arrangeBtn = new JButton("arrange : " + this.arranging);
        arrangeBtn.addActionListener(e -> {
            this.arranging--;
            serverListener.listen(new ArrangeEvent());
            arrangeBtn.setText("arrange : " + this.arranging );
            progressBar.setMaximum( progressBar.getMaximum() + 10);
            if (this.arranging == 0){
                arrangeBtn.setEnabled(false);
            }
        });
        arrangeBtn.setBounds(1200 , 600 , 100 , 70);

        setGBoard(board);

        this.setLayout(null);
        this.add(progressBar);
        this.add(arrangeBtn);
        this.add(readyBtn);
    }
    private void setGBoard(Board board){
        this.gBoard = new GraphicBoard(board,(event -> { }), true) ;
        gBoard.setBackground(Color.ORANGE);
        gBoard.setBounds(20 , 100 , GraphicBoard.Width , GraphicBoard.Height);
        this.add(gBoard);

    }

}
