package client.listener;

import shared.events.Event;

public interface EventListener {
    void listen(Event event);
}
