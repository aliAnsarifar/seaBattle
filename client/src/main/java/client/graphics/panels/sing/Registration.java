package client.graphics.panels.sing;



import client.AppException;
import client.graphics.panels.AbstractPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Registration extends AbstractPanel  {

    private JTextField usernameField = new JTextField( 10);
    private JTextField password1Field = new JPasswordField( 10);
    private JTextField password2Field = new JPasswordField(10);
    private GridBagConstraints gc = new GridBagConstraints();



    public Registration(){
        this.setBackground(Color.GREEN);
        Border innerBorder = BorderFactory.createTitledBorder("Registration form");
        Border outerBoarder = BorderFactory.createEmptyBorder(10, 5, 5, 5);
        this.setBorder(BorderFactory.createCompoundBorder(outerBoarder, innerBorder));

        this.setLayout(new GridBagLayout());

        gc.weightx = 1;
        gc.weighty = 0;

        /////////////// 1
        gc.gridx = 0;
        gc.gridy = 0;

        gc.insets = new Insets(10, 5, 10, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("username: "), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(usernameField, gc);

        /////////////// 4
        gc.gridx = 0;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("password: "), gc);

        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(password1Field, gc);

        /////////////// 5
        gc.gridx = 0;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("password: "), gc);

        gc.gridx = 1;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(password2Field, gc);




    }

    public String getUsernameField() throws AppException {
        if (usernameField.getText().equals("")) throw new AppException("username is required");
        return usernameField.getText();
    }


    public String getPassword1Field() throws AppException {
        if (password1Field.getText().equals("")) throw new AppException("password is required");
        if (!password2Field.getText().equals(password1Field.getText())) throw new AppException("passwords are different");
        return password1Field.getText();
    }

    public String getPassword2Field() throws AppException {
        if (password2Field.getText().equals("")) throw new AppException("password is required");
        return password2Field.getText();
    }



}
