import model.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args)  {
        int port = 56000;
        Server server;
        try {
            server = new Server(port);
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
