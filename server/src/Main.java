import controller.CommandInvoker;
import model.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
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
