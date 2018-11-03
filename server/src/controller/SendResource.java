package controller;

import model.ThreadSendResource;

import java.io.BufferedReader;
import java.net.Socket;

public class SendResource implements Command {

    @Override
    public void execute(Socket conn, BufferedReader in) {
        ThreadSendResource threadSendResource = new ThreadSendResource(conn, in);
        threadSendResource.start();
    }

    @Override
    public Command clonar() {
        return new SendResource();
    }
}
