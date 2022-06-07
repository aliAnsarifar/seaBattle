package server.controller;

import shared.events.Event;
import shared.response.Response;

public interface ResponseSender {
    Event getEvent();

    void sendResponse(Response response);

    void close();
}
