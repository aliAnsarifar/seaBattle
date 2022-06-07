package server.controller;

import server.controller.game.GameLobby;
import server.controller.network.SocketResponseSender;
import shared.model.game.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class SocketManager extends Thread{

    public static int port = 9000;
    public static final HashMap<String  , String> authTokenToUsername = new HashMap<>();
    public static final ArrayList<Game> games = new ArrayList<Game>();


    public void run() {
        try {
            GameLobby gameLobby = new GameLobby(games);
            ServerSocket serverSocket = new ServerSocket(port   );
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(new SocketResponseSender(socket),gameLobby);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
