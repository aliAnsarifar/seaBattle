package client.graphics.panels.game;

import client.graphics.panels.AbstractPanel;
import client.listener.EventListener;
import shared.events.ChangeTurnEvent;
import shared.model.Side;
import shared.model.game.Board;

import javax.swing.*;

public class GamePanel extends AbstractPanel {

    private Board board1; //my board
    private Board board2; //opponent board
    private Side turn;
    private EventListener serverListener;
    private Timer timer;
    private JProgressBar progressBar;
    private JLabel turnLabel;
    private GraphicBoard gBoard1;
    private GraphicBoard gBoard2;


    public GamePanel(Board board1 , Board board2 , Side turn , EventListener serverListener) {
        this.board1 = board1;
        this.board2 = board2;
        this.turn = turn;
        this.serverListener = serverListener;

        this.gBoard1 = new GraphicBoard(board1 , serverListener , true);
        this.gBoard1.setBounds(50 , 140 , GraphicBoard.Width , GraphicBoard.Height);

        this.gBoard2 = new GraphicBoard(board2 , serverListener , false);
        this.gBoard2.setBounds( 800, 140 ,GraphicBoard.Width , GraphicBoard.Height);
        if (board1.getSide() == turn){
            newProgressBar();
        }
        setTurnLabel();

        this.setLayout(null);
        this.add(gBoard1);
        this.add(gBoard2);

    }
    private void setGraphicBoard(int a){
        if (a ==1){
            this.remove(gBoard1);
            this.gBoard1 = new GraphicBoard(board1 , serverListener , true);
            this.gBoard1.setBounds(50 , 140 , GraphicBoard.Width , GraphicBoard.Height);
            this.add(gBoard1);
        }
        if (a == 2){
            this.remove(gBoard2);
            this.gBoard2 = new GraphicBoard(board2 , serverListener , false);
            this.gBoard2.setBounds( 800, 140 , GraphicBoard.Width , GraphicBoard.Height);
            this.add(gBoard2);
        }
    }
    public void setBoard1(Board board1) {
        this.board1 = board1;
        setGraphicBoard(1);
    }

    public void setBoard2(Board board2) {
        this.board2 = board2;
        setGraphicBoard(2);
    }

    private void setTurnLabel(){
        if (this.turnLabel !=null){
            this.remove(this.turnLabel);
        }
        String labelString = "your turn";
        if (board1.getSide() != turn){
            labelString = "enemy turn";
        }
        this.turnLabel = new JLabel(labelString);
        this.turnLabel.setBounds(600 , 40 , 300 , 80);
        this.add(this.turnLabel);
    }

    private void newProgressBar(){
        if (timer !=null){
            timer.stop();
        }
        if (this.progressBar !=null){
            this.remove(this.progressBar);
        }
        this.progressBar = new JProgressBar();
        this.progressBar.setStringPainted(true);
        this.progressBar.setBounds(150  , 40 , 300 , 80);
        this.progressBar.setValue(0);
        this.progressBar.setMaximum(30);
        this.timer = new Timer(1000 , (e -> {
            progressBar.setValue(progressBar.getValue() + 1);
            if (progressBar.getMaximum() <= progressBar.getValue()){
                serverListener.listen(new ChangeTurnEvent());
                System.out.println("change the turn");
                timer.stop();
            }
        }));
        this.timer.start();
        this.add(progressBar);

    }



    public Side getTurn() {
        return turn;
    }

    public void setTurn(Side turn) {
        if (this.turn != turn){
//            System.out.println("turn changed");
            if (turn == board1.getSide()){
                newProgressBar();
//                System.out.println("its my turn");
            }
            if (turn == board2.getSide()){
                if (this.progressBar !=null){
                    this.timer.stop();
                    this.remove(this.progressBar);
                }
//                System.out.println("its enemy turn");
            }
        }
        this.turn = turn;
        setTurnLabel();
    }
}
