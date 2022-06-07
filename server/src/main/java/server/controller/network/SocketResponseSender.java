package server.controller.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import server.controller.ResponseSender;
import shared.events.Event;
import shared.gson.Deserializer;
import shared.gson.Serializer;
import shared.model.ship.Ship;
import shared.response.Response;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SocketResponseSender implements ResponseSender {
    private final Socket socket;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;

    public SocketResponseSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Event.class, new Deserializer<>())
                .registerTypeAdapter(Response.class, new Serializer<>())
                .registerTypeAdapter(Ship.class , new Deserializer<>())
                .registerTypeAdapter(Ship.class , new Serializer<>())
                .create();
    }

    @Override
    public Event getEvent() throws NoSuchElementException {
        String eventString = scanner.nextLine();
        return gson.fromJson(eventString, Event.class);
    }

    @Override
    public void sendResponse(Response response) {
        printStream.println(gson.toJson(response, Response.class));
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
