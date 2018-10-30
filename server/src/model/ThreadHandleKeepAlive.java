package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadHandleKeepAlive extends Thread {

    private final Socket conn;
    private final BufferedReader in;

    public ThreadHandleKeepAlive(Socket conn, BufferedReader in) {
        this.conn = conn;
        this.in = in;
    }

    @Override
    public void run() {
        ClientList clientList = ClientList.getInstance();
        PrintWriter out = null;
        try {
            int porta;
            if(clientList.getClients().size() == 0) {
                porta = 56001;
            } else {
                porta = clientList.getClients().get(clientList.getClients().size() - 1).getPort() + 1;
            }
            Client client = new Client(in.readLine(), porta);
            clientList.addClients(client);

            out = new PrintWriter(conn.getOutputStream(), true);
            out.println(porta);

            System.out.println(client);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (conn != null) conn.close();
            } catch (IOException e) {
                System.out.println("Error on closing input stream or socket");
                e.printStackTrace();
            }
        }
    }


}
