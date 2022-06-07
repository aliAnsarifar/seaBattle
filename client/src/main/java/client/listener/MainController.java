package client.listener;


import client.graphics.GraphicalAgent;
import client.graphics.panels.AllGamesPanel;
import client.graphics.panels.WatchGamePanel;
import shared.events.Event;

import shared.model.GamePlayer;
import shared.model.Side;
import shared.model.User;
import shared.model.game.Game;
import shared.response.Response;
import shared.response.ResponseVisitor;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainController implements ResponseVisitor {
    private final EventSender eventSender;
    private final List<Event> events;
    private final Timer timer;
    private final GraphicalAgent graphicalAgent ;
    public static String authToken;


    public MainController(EventSender eventSender) {
        this.eventSender = eventSender;
        this.events = new LinkedList<>();
        this.timer = new Timer(300 , this::sendEvents);
        this.graphicalAgent = new GraphicalAgent(this::addEvent);

    }



    public void start() {
        timer.start();
        graphicalAgent.initialize();
        graphicalAgent.goToLogin();
    }


    private void addEvent(Event event) {
        synchronized (events) {
            events.add(event);
        }
    }

    private void sendEvents(ActionEvent e) {
        List<Event> temp;
        synchronized (events) {
            temp = new LinkedList<>(events);
            events.clear();
        }
        for (Event event : temp) {
            Response response = eventSender.send(event);
            response.visit(this);
        }
    }

    @Override
    public void showError(String s) {
        JOptionPane.showMessageDialog(null , s , "error" , JOptionPane.ERROR_MESSAGE );
    }

    @Override
    public void sing(User user , String authToken) {
        this.graphicalAgent.setCurrentUser(user);
        this.graphicalAgent.goToMainMenu();
        MainController.authToken = authToken;
    }

    @Override
    public void opponentFound(Side side) {
//        System.out.println("found opponent");
        graphicalAgent.setSide(side);
    }

    @Override
    public void getGame(Game game) {
//        System.out.println(" get game " + graphicalAgent.getCurrentUser().getUsername());
        this.graphicalAgent.setGame(game);
    }
    @Override
    public void getGame(Game game , String s){
//        System.out.println("should change the turn");
        this.graphicalAgent.setGame(game);
    }

    @Override
    public void endGame(int i) {
        graphicalAgent.endGame(i);
//        System.out.println("going to main menu end game");
        graphicalAgent.goToMainMenu();
    }

    @Override
    public void scoreBoard(ArrayList<User> users) {
        graphicalAgent.updateScoreBoard(users);

    }

    @Override
    public void allGames(ArrayList<GamePlayer> games) {
        graphicalAgent.showGames(games);
    }

    @Override
    public void watchGame(Game game) {
        WatchGamePanel panel = new WatchGamePanel(game ,graphicalAgent.getInternalListener() );
        graphicalAgent.frame.addPanel(panel);
    }


}
