package client.graphics.panels.sing;

import client.AppException;
import client.graphics.panels.AbstractPanel;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Login extends AbstractPanel {
    private JTextField usernameField = new JTextField(10);
    private JTextField passwordField = new JPasswordField(10);
    private GridBagConstraints constraints = new GridBagConstraints();

    public Login(){
        this.setBackground(Color.GRAY);
        this.setBorder( new LineBorder(Color.ORANGE  , 5  , true));

        this.setLayout(new GridBagLayout());
        constraints.weighty = 0;
        constraints.weightx = 1;

        ///// label usernameField
        constraints.gridx = 0 ;
        constraints.gridy = 0 ;
        constraints.anchor = GridBagConstraints.LAST_LINE_END;
        constraints.insets = new Insets(0 , 0 , 5 , 5);
        this.add(new JLabel("usernameField") , constraints);

        //// text usernameField
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(0 , 5 , 5 , 0);
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(usernameField, constraints);

        //// label password
        constraints.gridx = 0 ;
        constraints.gridy = 1 ;
        constraints.insets = new Insets(0 , 0 , 5 , 5);
        constraints.anchor = GridBagConstraints.LAST_LINE_END;
        this.add(new JLabel("password") , constraints);

        /// text password
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(0 , 5 , 5 , 0);
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(passwordField, constraints);

        /// bnt
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
    }

    public String getUsernameField() throws AppException {
        if (usernameField.getText().equals("")) throw new AppException("usernameField is required");
        return usernameField.getText();
    }

    public String getPasswordField() throws AppException {
        if (passwordField.getText().equals("")) throw new AppException("password is required");
        return passwordField.getText();
    }
}
