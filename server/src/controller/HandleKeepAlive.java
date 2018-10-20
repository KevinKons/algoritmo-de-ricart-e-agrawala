package controller;

import model.ThreadHandleKeepAlive;

import java.io.BufferedReader;
import java.net.Socket;

public class HandleKeepAlive implements Command {

    @Override
    public void execute(Socket conn, BufferedReader in) {
        ThreadHandleKeepAlive threadHandleKeepAlive = new ThreadHandleKeepAlive(conn, in);
        threadHandleKeepAlive.start();
    }

    @Override
    public Command clonar() {
        return new HandleKeepAlive();
    }
}
