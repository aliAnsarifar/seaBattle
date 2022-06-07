package client.graphics.panels;

import client.listener.EventListener;
import client.listener.MainController;
import client.listener.internal.StringListener;
import shared.events.FindOpponentRequestEvent;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends AbstractPanel{
    private EventListener serverListener;

    public MainMenu(EventListener serverListener , StringListener internalListener) {
        this.serverListener = serverListener;
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10 , 10 , 10 , 10);

        JButton newGameBtn = new JButton("new game");
        this.add(newGameBtn , constraints);
        newGameBtn.addActionListener((e -> {
            serverListener.listen(new FindOpponentRequestEvent(MainController.authToken));
            internalListener.listen("waitForOpponent");
        }));


        constraints.gridy = 1;
        JButton scoreBoardBtn = new JButton("score board");
        this.add(scoreBoardBtn , constraints);
        scoreBoardBtn.addActionListener(e -> {
            internalListener.listen("scoreBoard");
        });


        constraints.gridy = 2;
        JButton watchOnlineGame = new JButton("watch games");
        this.add(watchOnlineGame , constraints);
        watchOnlineGame.addActionListener(e -> internalListener.listen("watch games"));

        constraints.gridy = 3;
        JButton profileBtn = new JButton("profile");
        this.add(profileBtn , constraints);
        profileBtn.addActionListener(e -> {
            internalListener.listen("profileView");
        });

//        constraints.gridy = 4;
//        JButton exitBtn = new JButton("exit");
//        this.add(exitBtn , constraints);




    }
}
