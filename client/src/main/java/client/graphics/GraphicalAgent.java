package client.graphics;

import client.graphics.panels.*;
import client.graphics.panels.game.ArrangePanel;
import client.graphics.panels.game.GamePanel;
import client.graphics.panels.game.ProfilePanel;
import client.graphics.panels.sing.SignPanel;
import client.listener.EventListener;
import client.listener.MainController;
import client.listener.internal.PanelToAgent;
import jdk.nashorn.internal.scripts.JO;
import shared.events.*;
import shared.model.GamePlayer;
import shared.model.Side;
import shared.model.User;
import shared.model.game.Board;
import shared.model.game.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class GraphicalAgent {
    private EventListener serverListener;
    public MyFrame frame ;
    private final Map<PanelType, AbstractPanel> panels ;
    private User currentUser;
    private PanelToAgent internalListener = new PanelToAgent(this);
    private Timer timer;
    private Side side;


    public GraphicalAgent(EventListener serverListener) {
        this.serverListener = serverListener;
        this.frame = new MyFrame();
        this.panels = new EnumMap<>(PanelType.class);
    }

    public PanelToAgent getInternalListener() {
        return internalListener;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    public User getCurrentUser(){
        return this.currentUser;
    }



    public void initialize(){
        frame.setVisible(true);

    }

    public void goToLogin(){
        SignPanel panel = new SignPanel(serverListener);
        frame.addPanel(panel);
        panels.put(PanelType.SING_PANEL , panel);

    }



    public void goToMainMenu(){
        if (this.timer != null){
            timer.stop();
        }
        MainMenu panel = new MainMenu(serverListener, internalListener );
        frame.addPanel(panel);
        panels.put(PanelType.MAIN_MENU , panel);
    }

    public void scoreBoard(){
        this.timer = new Timer(2000 ,e -> { serverListener.listen(new ScoreBoardEvent());} );
        timer.start();
    }

    public void updateScoreBoard(ArrayList<User> users){
        ScoreBoardPanel panel = new ScoreBoardPanel(users , internalListener);
        frame.addPanel(panel);
    }

    public void setSide(Side side){
        if (side != null) {
            this.side = side;
            this.timer.stop();
            this.timer = new Timer(500, (e -> {
                serverListener.listen(new GetGameEvent());
            }));
            this.timer.start();
        }
        else{
            this.side = null;
        }
    }

    public void endGame(int a){
        this.timer.stop();
        int number = 0;
        if (this.side == Side.PLAYER_ONE ){
            number = 1;
        }
        if (this.side == Side.PLAYER_TWO){
            number = 2;
        }
        if (a == number){
            JOptionPane.showMessageDialog(null ,
                    "win the game" , "finished" , JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null ,
                    "lose the game" , "finished" , JOptionPane.INFORMATION_MESSAGE);
        }
        this.side = null;
        panels.remove(PanelType.GAME_PANEL);
        panels.remove(PanelType.ARRANGE_PANEL);

    }

    public void waitForOpponent(){
        AbstractPanel panel = new AbstractPanel();
        JLabel label = new JLabel("waiting for Opponent");
        panel.add(label);
        frame.addPanel(panel);
        MyFrame.refreshFrame();
        this.timer = new Timer(500 , (e -> serverListener.listen(new FindOpponentEvent(MainController.authToken))));
        timer.start();
    }

    public void profileView(){
        ProfilePanel profilePanel = new ProfilePanel(this.currentUser , internalListener);
        frame.addPanel(profilePanel);
    }

    public void gamesRequest(){
        if (timer !=null){
            this.timer.stop();
        }
        this.timer = new Timer(2000 ,e -> serverListener.listen(new AllGamesEvent()) );
        this.timer.start();
    }

    public void showGames(ArrayList<GamePlayer> games){
        AllGamesPanel panel = new AllGamesPanel(games , internalListener);
        this.frame.addPanel(panel);
    }

    public void gameRequest(String username1 , String username2){
        this.timer.stop();
        this.timer = new Timer(1000 , e ->
                serverListener.listen(new WatchingGameEvent(username1 , username2)));

        this.timer.start();
    }

    public void arrange(Board board){
        if (panels.containsKey(PanelType.ARRANGE_PANEL)){
            ((ArrangePanel)frame.contentPanel).setBoard(board);
            MyFrame.refreshFrame();
        }
        else{

            ArrangePanel panel = new ArrangePanel(serverListener , internalListener , board);
            frame.addPanel(panel);
            panels.put(PanelType.ARRANGE_PANEL , panel);
        }
    }
    public void play(Game game ){
        if (panels.containsKey(PanelType.GAME_PANEL)){
            if (this.side == Side.PLAYER_ONE){
                ((GamePanel)frame.contentPanel).setBoard1(game.getBoard1());
                ((GamePanel)frame.contentPanel).setBoard2(game.getBoard2());
                ((GamePanel)frame.contentPanel).setTurn(game.getTurn());

            }
            if (this.side == Side.PLAYER_TWO){
                ((GamePanel)frame.contentPanel).setBoard1(game.getBoard2());
                ((GamePanel)frame.contentPanel).setBoard2(game.getBoard1());
                ((GamePanel)frame.contentPanel).setTurn(game.getTurn());
            }

        }
        else{
            GamePanel gamePanel = null;
            if (this.side == Side.PLAYER_ONE){
                 gamePanel = new GamePanel(game.getBoard1(), game.getBoard2(), game.getTurn() , serverListener);
            }
            if (this.side == Side.PLAYER_TWO){
                 gamePanel = new GamePanel(game.getBoard2(), game.getBoard1(), game.getTurn() , serverListener);
            }
            panels.remove(PanelType.ARRANGE_PANEL) ;
            frame.addPanel(gamePanel);
            panels.put(PanelType.GAME_PANEL , gamePanel);
        }
    }

    public void setGame(Game game){
        if (this.side == Side.PLAYER_ONE){
            if (!game.getBoard1().isReady()){
                this.arrange(game.getBoard1());
            }
            if (game.getBoard1().isReady() && !game.getBoard2().isReady()){
                AbstractPanel panel = new AbstractPanel();
                JLabel label = new JLabel("waiting for Opponent to arrange");
                panel.add(label);
                frame.addPanel(panel);
                MyFrame.refreshFrame();
            }
            if (game.getBoard1().isReady() && game.getBoard2().isReady()){
                this.play(game);
            }
        }
        else if (this.side == Side.PLAYER_TWO){
            if (!game.getBoard2().isReady()){
                this.arrange(game.getBoard2());
            }
            if (game.getBoard2().isReady() && !game.getBoard1().isReady()){
                AbstractPanel panel = new AbstractPanel();
                JLabel label = new JLabel("waiting for Opponent to arrange");
                panel.add(label);
                frame.addPanel(panel);
                MyFrame.refreshFrame();
            }
            if (game.getBoard1().isReady() && game.getBoard2().isReady()){
                this.play(game);
            }
        }
        else {
            goToMainMenu();
            System.out.println("going to main menu set game");
        }
        MyFrame.refreshFrame();
    }

}
