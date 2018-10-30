package model;

import controller.CommandInvoker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server;
    private CommandInvoker ci = CommandInvoker.getInstance();

    public Server(int port) throws IOException {
        server = new ServerSocket(port);
        server.setReuseAddress(true);
    }

    public void startServer() throws IOException {
        while(true) {
            System.out.println("Waiting connection");
            Socket conn = server.accept();
            ci.execute(conn);
        }
    }
}
