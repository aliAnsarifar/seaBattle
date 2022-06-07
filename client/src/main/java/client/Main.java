package client;


import client.listener.MainController;
import client.listener.network.SocketEventSender;

import java.io.IOException;
import java.net.Socket;

public class Main {

    static public int port;
    public static void main(String[] args) throws IOException {
        new Config();
        Socket socket = new Socket("127.0.0.1", port);
        MainController controller = new MainController(new SocketEventSender(socket));
        System.out.println("started");
        controller.start();

    }
}
