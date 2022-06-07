package client.graphics.panels.sing;

import client.AppException;
import client.graphics.MyFrame;
import client.graphics.panels.AbstractPanel;
import client.listener.EventListener;
import shared.events.LoginEvent;
import shared.events.RegistrationEvent;

import javax.swing.*;
import java.awt.*;

public class SignPanel extends AbstractPanel {

    private EventListener listener;



    public SignPanel(EventListener listener) {
        this.listener = listener;
        this.setLayout(new BorderLayout());

        JPanel middlePanel  = new JPanel();
        middlePanel.setLayout(new BorderLayout());
        Login loginPanel =  new Login();
        Registration registrationPanel = new Registration();


        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton singUp = new JButton("sing up");

        JButton singIn = new JButton("sing in ");



        JPanel topPanel = new JPanel(new FlowLayout());
        JButton login = new JButton("login");
        topPanel.add(login);
        login.addActionListener( (event) -> {
            middlePanel.removeAll() ;
            middlePanel.add(loginPanel);
            bottomPanel.removeAll();
            bottomPanel.add(singIn);
            MyFrame.refreshFrame();
        } );

        JButton register = new JButton("register");
        topPanel.add(register);
        register.addActionListener((e -> {
            middlePanel.removeAll();
            middlePanel.add(registrationPanel);
            bottomPanel.removeAll();
            bottomPanel.add(singUp);
            MyFrame.refreshFrame();
        }));


        singUp.addActionListener((e -> {
            try {
                RegistrationEvent registrationEvent = new RegistrationEvent(
                        registrationPanel.getUsernameField(),
                        registrationPanel.getPassword1Field());
                this.listener.listen(registrationEvent);
            }catch (AppException exception){
                JOptionPane.showMessageDialog(
                        null , exception.getMessage() ,
                        "something went wrong" , JOptionPane.ERROR_MESSAGE );
            }
        }));

        singIn.addActionListener(e -> {
            try {
                LoginEvent loginEvent = new LoginEvent(loginPanel.getUsernameField() , loginPanel.getPasswordField());
                this.listener.listen(loginEvent);
            }catch (AppException exception){
                JOptionPane.showMessageDialog(null , exception.getMessage() ,
                        "something went wrong" , JOptionPane.ERROR_MESSAGE);
            }
        });



        this.add(topPanel , BorderLayout.NORTH);
        this.add(middlePanel , BorderLayout.CENTER);
        this.add(bottomPanel , BorderLayout.SOUTH);



    }
}
