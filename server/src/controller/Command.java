package controller;

import java.io.BufferedReader;
import java.net.Socket;

public interface Command {

    void execute(Socket conn, BufferedReader in);

    Command clonar();
}
