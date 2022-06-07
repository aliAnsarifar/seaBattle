package client.graphics.panels;

import client.listener.internal.StringListener;
import shared.model.GamePlayer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AllGamesPanel extends AbstractPanel{
    ArrayList<GamePlayer> gamePlayers ;

    public AllGamesPanel(ArrayList<GamePlayer> gamePlayers , StringListener internalListener) {

        this.gamePlayers = gamePlayers;
        JPanel panel = new JPanel();
        panel .setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10 , 10 , 10 , 10);
        constraints.gridx = 0;
        constraints.gridy = -1;
        for (GamePlayer gamePlayer :gamePlayers) {
            constraints.gridy++;
            JButton button = new JButton(gamePlayer.getPlayer1()  + " vs " + gamePlayer.getPlayer2());
            button.addActionListener(e -> internalListener.gameListen(gamePlayer.getPlayer1() , gamePlayer.getPlayer2()));
            panel.add(button , constraints);
        }
        JScrollPane scrollPane = new JScrollPane(panel);

        JButton backBtn = new JButton("backBtn");
        backBtn.addActionListener(e -> internalListener.listen("goToMainMenu"));

        this.setLayout(new BorderLayout());
        this.add(scrollPane , BorderLayout.CENTER);
        this.add(backBtn, BorderLayout.SOUTH);
    }
}
