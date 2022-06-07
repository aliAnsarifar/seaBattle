package server.controller;


import server.DB.UserDB;
import server.controller.game.GameLobby;
import shared.events.Event;
import shared.events.EventVisitor;
import shared.model.GamePlayer;
import shared.model.Side;
import shared.model.User;
import shared.model.game.Game;
import shared.response.*;

import java.security.SecureRandom;
import java.util.*;


public class ClientHandler extends Thread  implements EventVisitor {

    private static String USERNAME_ERROR = "username is used before";
    private static String WRONG_LOGIN = "username or password is wrong";

    private final ResponseSender sender;
    private GameLobby gameLobby ;
    private Game game;
    private User user ;
    private Side side;

    public ClientHandler(ResponseSender sender, GameLobby gameLobby) {
        this.sender = sender;
        this.gameLobby = gameLobby;
    }



    public User getUser() {
        return user;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public void run() {
        try {
            while (true) {

                Event event = sender.getEvent();
                Response response = event.visit(this);
                sender.sendResponse(response);

            }
        }catch (NoSuchElementException exception){
            for (Map.Entry<String, String> entry :
                    SocketManager.authTokenToUsername.entrySet()) {
                if (entry.getValue().equals( user.getUsername())){
                    SocketManager.authTokenToUsername.remove(entry.getKey() , entry.getValue());
                    break;
                }
            }
            exception.printStackTrace();
        }
    }


    private void saveUser(int a){
        int num = 0;
        if (this.side == Side.PLAYER_TWO){
            num = 2;
        }
        if (this.side == Side.PLAYER_ONE){
            num = 1;
        }
        if (num == a){
            user.setVictory(user.getVictory() +1 );
        }else{
            user.setLosing(user.getLosing() +1 );
        }
        UserDB userDB = new UserDB();
        userDB.update(user);
        SocketManager.games.remove(this.game);
        this.game = null;
        this.side = null;
    }
    @Override
    public Response opponentFound() {
        if (game == null){
            return new NothingResponse();
        }
        else{
            return new OpponentFoundResponse(this.side);
        }
    }

    @Override
    public  Response arrangeBoard() {
        synchronized (this.game) {
            if (this.side == Side.PLAYER_ONE) {
                this.game.getBoard1().arrangeShip();
            }
            if (this.side == Side.PLAYER_TWO) {
                this.game.getBoard2().arrangeShip();
            }
            return new GameResponse(this.game);
        }
    }

    @Override
    public Response readyBoard() {
        synchronized (this.game) {
            if (this.side == Side.PLAYER_ONE) {
                this.game.getBoard1().setReady(true);
            }
            if (this.side == Side.PLAYER_TWO) {
                this.game.getBoard2().setReady(true);
            }
            return new GameResponse(this.game);
        }
    }
    @Override
    public  Response getGame() {
        if (this.game == null){
            return new NothingResponse();
        }
        synchronized (this.game) {
            if (this.game.getFinished() != 0){
                int a = this.game.getFinished();
                saveUser(a);
                return new EndGameResponse(a);
            }
            return new GameResponse(this.game);
        }
    }

    @Override
    public Response clickBoard(int x, int y) {
        if (this.game == null){
            return new NothingResponse();
        }
        synchronized (this.game) {
            if (this.game.getTurn() == this.side) {
                if (this.side == Side.PLAYER_ONE) {
                    this.game.getBoard2().get(x, y).setDestroyed(true);
//                    System.out.println("destroyed one cell");
                    if (this.game.getBoard2().get(x, y).getShip() == null) {
//                        System.out.println("change the turn");
                        this.game.setTurn(Side.PLAYER_TWO);
                    }
                }
                if (this.side == Side.PLAYER_TWO) {
                    this.game.getBoard1().get(x, y).setDestroyed(true);
//                    System.out.println("destroyed one cell");
                    if (this.game.getBoard1().get(x, y).getShip() == null) {
//                        System.out.println("change the turn");
                        this.game.setTurn(Side.PLAYER_ONE);
                    }
                }
            }
            this.game.isFinished();
            if (this.game.getFinished() != 0){
                int a = this.game.getFinished();
                saveUser(a);
                return new EndGameResponse(a);
            }
            return new GameResponse(this.game);
        }
    }

    @Override
    public Response changeTurn() {
        if (this.game == null){
            return new NothingResponse();
        }
        synchronized (this.game) {
            if (this.game.getTurn() == this.side) {
                if (game.getTurn() == Side.PLAYER_ONE) {
                    game.setTurn(Side.PLAYER_TWO);
//                    System.out.println("side change to player two");
                }
                else if (game.getTurn() == Side.PLAYER_TWO) {
                    game.setTurn(Side.PLAYER_ONE);
//                    System.out.println("side change to player one");
                }
//                System.out.println("change the turn");
            }
            if (this.game.getFinished() != 0) {
                int a = this.game.getFinished();
                saveUser(a);
                return new EndGameResponse(a);
            }
            return new GameResponse(this.game);

        }
    }

    @Override
    public Response scoreBoard() {
        UserDB userDB = new UserDB();
        ArrayList<User> users =  userDB.get();
//        System.out.println(SocketManager.authTokenToUsername.values().toString());
        for (User user : users) {
            user.setOnline(false);
            for (String s: SocketManager.authTokenToUsername.values()) {
//                System.out.println(user.getUsername() +" " + s);
                if (s.equals(user.getUsername())){
                    user.setOnline(true);
                    break;
                }
            }
        }

        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return ( (o2.getVictory() - o2.getLosing() )- (o1.getVictory() - o1.getLosing()) );
            }
        });

        return new ScoreBoardResponse(users);
    }

    @Override
    public Response allGames() {
        ArrayList<GamePlayer> games = new ArrayList<>();
        for (Game game :
        SocketManager.games) {
            games.add(new GamePlayer(game.getPlayer1() , game.getPlayer2()));
        }
        return new AllGamesResponse(games);
    }

    @Override
    public Response getGame(String s, String s1) {
        for (Game game:SocketManager.games) {
            if (game.getPlayer1().equals(s) && game.getPlayer2().equals(s1)){
                return new WatchingGameResponse(game);
            }
        }
        return new NothingResponse();
    }


    @Override
    public Response findOpponentRequest(String s) {
        gameLobby.startGameRequest(this);
        return new NothingResponse();
    }

    @Override
    public Response register(String username, String password) {
        UserDB userDB = new UserDB();
        if (userDB.get(username) == null){
            userDB.add(username , password);
            this.user = userDB.get(username);
            return new SingResponse(userDB.get(username) , generateAuthToken(username) );
        }else{
            return new ShowError(USERNAME_ERROR);
        }

    }

    @Override
    public Response login(String username, String password) {
        UserDB userDB = new UserDB();
        User user = userDB.get(username);
        if (user == null || !user.getPassword().equals(password)){
            return new ShowError(WRONG_LOGIN);
        }else{
            this.user = user;

            return new SingResponse(user , generateAuthToken(username));
        }
    }

    public String generateAuthToken(String username){
        while(true){
            SecureRandom secureRandom = new SecureRandom();
            String auth = secureRandom.nextDouble() + "" + secureRandom.nextLong();
            if (!SocketManager.authTokenToUsername.containsKey(auth)){
                SocketManager.authTokenToUsername.put(auth , username);
                return auth;
            }
        }
    }
}
