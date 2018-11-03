package model.state;

import model.Client;

import java.net.Socket;

public abstract class State {

    protected final Client client;

    public State(Client client) {
        this.client = client;
    }

    public abstract void nextState();
    public abstract void nextState(String resource);
    public abstract void respondRequest(Socket conn);
    public abstract String getResource();
}
