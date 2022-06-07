package client.listener.network;

import client.listener.EventSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import shared.events.Event;
import shared.gson.Deserializer;
import shared.gson.Serializer;
import shared.model.ship.Ship;
import shared.response.Response;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketEventSender implements EventSender {
    private final Socket socket;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;

    public SocketEventSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Response.class, new Deserializer<>())
                .registerTypeAdapter(Event.class, new Serializer<>())
                .registerTypeAdapter(Ship.class , new Deserializer<>())
                .registerTypeAdapter(Ship.class , new Serializer<>())
                .create();

    }

    @Override
    public Response send(Event event) {

        String eventString = gson.toJson(event, Event.class);
        printStream.println(eventString);
        String responseString = scanner.nextLine();
        return gson.fromJson(responseString, Response.class);
    }

    @Override
    public void close() {
        try {
            printStream.close();
            scanner.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
