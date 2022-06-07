package client.graphics.panels;

import client.listener.internal.StringListener;
import shared.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScoreBoardPanel extends AbstractPanel{

    ArrayList<User> users ;

    public ScoreBoardPanel(ArrayList<User> users , StringListener internalListener) {
        this.users = users;
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10 , 10 , 10  ,10 );
        constraints.gridx = 0;

        constraints.gridy = -1;
        for (User user : users) {
            constraints.gridy ++;

            JLabel label = new JLabel(user.getUsername()
                    + "  score :" +(user.getVictory() - user.getLosing()) +
                    "  isOnline: "  + user.isOnline());
            label.setFont(new Font("bold" , Font.BOLD , 20));
            panel.add(label , constraints);
        }
        JScrollPane pane = new JScrollPane(panel);

        JButton button = new JButton("back");
        button.addActionListener(e -> internalListener.listen("goToMainMenu"));

        this.setLayout( new BorderLayout());
        this.add(pane , BorderLayout.CENTER);
        this.add(button , BorderLayout.SOUTH);
    }


}
