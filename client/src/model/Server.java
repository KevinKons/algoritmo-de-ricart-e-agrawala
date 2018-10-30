package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private ServerSocket server;
    private int port;
    private Client client;

    @Override
    public void run() {
        try {
            server = new ServerSocket(this.port);
            server.setReuseAddress(true);
            while(true) {
                System.out.println("Waiting connection");
                Socket conn = server.accept();
                client.getState().respondRequest(conn);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setPort(int port) {
        this.port = port;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}
