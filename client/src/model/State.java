package model;

public abstract class State {

    protected final Client client;

    public State(Client client) {
        this.client = client;
    }

    public abstract void nextState();
    public abstract boolean respondRequest();
}
