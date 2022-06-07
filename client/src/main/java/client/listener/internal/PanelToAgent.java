package client.listener.internal;

import client.graphics.GraphicalAgent;



public class PanelToAgent implements StringListener{
    private GraphicalAgent agent;

    public PanelToAgent(GraphicalAgent agent) {
        this.agent = agent;
    }

    @Override
    public void listen(String listen) {
        if (listen.equals("waitForOpponent")){
            agent.waitForOpponent();
        }
        if (listen.equals("profileView")){
            agent.profileView();
        }
        if (listen.equals("goToMainMenu")){
            agent.goToMainMenu();
        }
        if (listen.equals("scoreBoard")) {
            agent.scoreBoard();
        }
        if (listen.equals("watch games")){
            agent.gamesRequest();
        }
    }

    @Override
    public void gameListen(String player1, String player2) {
        agent.gameRequest(player1 , player2);
    }

}
