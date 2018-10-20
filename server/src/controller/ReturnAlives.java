package controller;

import model.ThreadReturnAlives;

import java.io.BufferedReader;
import java.net.Socket;

public class ReturnAlives implements Command {

    @Override
    public void execute(Socket conn, BufferedReader in) {
        ThreadReturnAlives threadReturnAlives = new ThreadReturnAlives(conn, in);
        threadReturnAlives.run();
    }

    @Override
    public Command clonar() {
        return new ReturnAlives();
    }
}
