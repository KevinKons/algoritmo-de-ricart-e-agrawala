package controller;

import java.net.Socket;

public interface Command {

    void execute(Socket conn);

    Command clonar();
}
