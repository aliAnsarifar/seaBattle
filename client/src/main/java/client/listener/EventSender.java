package client.listener;

import shared.events.Event;
import shared.response.Response;

public interface EventSender {
    Response send(Event event);

    void close();
}
