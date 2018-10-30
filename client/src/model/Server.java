package model;

import controller.ReceiverRequestCommandInvoker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private ServerSocket server;
    private ReceiverRequestCommandInvoker receiverRequestCI = ReceiverRequestCommandInvoker.getInstance();
    private int port;

    @Override
    public void run() {
        try {
            server = new ServerSocket(this.port);
            server.setReuseAddress(true);
            while(true) {
                System.out.println("Waiting connection");
                Socket conn = server.accept();
                receiverRequestCI.execute(conn);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setPort(int port) {
        this.port = port;
    }
}
