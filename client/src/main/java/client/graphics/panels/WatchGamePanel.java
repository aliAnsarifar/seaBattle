package client.graphics.panels;

import client.graphics.panels.game.GraphicBoard;
import client.listener.internal.StringListener;
import shared.model.game.Game;

import javax.swing.*;
import java.awt.*;

public class WatchGamePanel extends AbstractPanel{
    private Game game;


    public WatchGamePanel(Game game , StringListener internalListener) {
        this.game = game;
        GraphicBoard gBoard1 = new GraphicBoard(game.getBoard1() , event -> {} , false);
        gBoard1.setBounds(50 , 140 , GraphicBoard.Width , GraphicBoard.Height);

        GraphicBoard gBoard2 = new GraphicBoard(game.getBoard2() , event -> {} , false);
        gBoard2.setBounds( 800, 140 , GraphicBoard.Width , GraphicBoard.Height);

        Font f = new Font("bold" , Font.BOLD , 20);
        JLabel label1 = new JLabel(game.getPlayer1());
        label1.setFont(f);
        label1.setBounds(900 , 40 , 300 , 80);

        JLabel label2 = new JLabel(game.getPlayer2());
        label2.setFont(f);
        label2.setBounds(100 , 40 , 300 , 80);

        JLabel turnLabel = new JLabel(game.getTurn().toString());
        turnLabel.setFont(f);
        turnLabel.setBounds(450 , 40 , 300 , 80);

        JButton backBtn = new JButton("back");
        backBtn.setBounds(80 + GraphicBoard.Width  , 500 , 100 , 100);
        backBtn.addActionListener(e -> internalListener.listen("goToMainMenu"));

        this.setLayout(null);
        this.add(gBoard1);
        this.add(gBoard2);
        this.add(label1);
        this.add(label2);
        this.add(turnLabel);
        this.add(backBtn);
    }
}
