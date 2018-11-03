package controller;

import model.ThreadReceiveResource;

import java.io.BufferedReader;
import java.net.Socket;

public class ReceiveResource implements Command {

    @Override
    public void execute(Socket conn, BufferedReader in) {
        ThreadReceiveResource threadReceiveResource = new ThreadReceiveResource(conn, in);
        threadReceiveResource.start();
    }

    @Override
    public Command clonar() {
        return new ReceiveResource();
    }
}
