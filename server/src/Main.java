import controller.CommandInvoker;
import model.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException {
        int port = 56000;
        Server server = null;
        try {
            server = new Server(port);
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
