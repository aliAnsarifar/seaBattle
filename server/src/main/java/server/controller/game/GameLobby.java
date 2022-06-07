package server.controller.game;


import server.controller.ClientHandler;
import shared.model.Side;
import shared.model.game.Board;
import shared.model.game.Game;

import java.util.ArrayList;

public class GameLobby {
    private ClientHandler waiting;
    private final ArrayList<Game> games;

    public GameLobby(ArrayList<Game> games) {
        this.games = games;
    }

    public synchronized void startGameRequest(ClientHandler clientHandler) {
        if (this.waiting == null){
            this.waiting = clientHandler;
        }
        else{
            if (this.waiting != clientHandler){
                Game game = null;
                String user1;
                String user2;
                int a  = (int) (2 * Math.random());
                if (a == 0){
                    user1 = this.waiting.getUser().getUsername();
                    user2 = clientHandler.getUser().getUsername();
                    Board board1 = new Board(Side.PLAYER_ONE);
                    Board board2 = new Board(Side.PLAYER_TWO) ;
                    game =  new Game(user1
                            , user2
                            , board1
                            , board2
                            ,  Side.PLAYER_ONE);
                    waiting.setSide(Side.PLAYER_ONE);
                    clientHandler.setSide(Side.PLAYER_TWO);
                }
                if (a == 1){
                    user1 = clientHandler.getUser().getUsername();
                    user2 = this.waiting.getUser().getUsername();
                    Board board1 = new Board(Side.PLAYER_ONE);
                    Board board2 = new Board(Side.PLAYER_TWO);

                    game = new Game(user1
                            , user2
                            , board1
                            , board2
                            , Side.PLAYER_ONE);
                    waiting.setSide(Side.PLAYER_TWO);
                    clientHandler.setSide(Side.PLAYER_ONE);
                }

                clientHandler.setGame(game);
                waiting.setGame(game);
                
                waiting = null;
                this.games.add(game);
                
            }
        }


    }


}
