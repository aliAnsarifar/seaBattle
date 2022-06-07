package client.graphics.panels.game;

import client.graphics.panels.AbstractPanel;
import client.listener.internal.StringListener;
import shared.model.User;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends AbstractPanel {
    private User user;

    public ProfilePanel(User user , StringListener internalListener) {
        this.user = user;
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.insets = new Insets(10 , 10 , 10 , 10);



        JLabel usernameLabel = new JLabel("username : " + user.getUsername());
        constraints.gridy = 0;
        this.add(usernameLabel , constraints);

        JLabel passwordLabel = new JLabel("password : " + user.getPassword());
        constraints.gridy = 1;
        this.add(passwordLabel , constraints);

        JLabel victoryLabel = new JLabel("victory : " + user.getVictory());
        constraints.gridy = 2;
        this.add(victoryLabel , constraints);

        JLabel losingLabel = new JLabel( " losing : " + user.getLosing() );
        constraints.gridy = 3;
        this.add(losingLabel , constraints);

        JLabel scoreLabel = new JLabel("score : " +( user.getVictory() - user.getLosing() ) ) ;
        constraints.gridy = 4;
        this.add(scoreLabel , constraints);

        JButton back = new JButton("back");
        constraints.gridy = 5;
        this.add(back , constraints);
        back.addActionListener(e -> {internalListener.listen("goToMainMenu");});



    }
}
