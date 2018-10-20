package model;

import model.Client;
import model.ClientList;

import java.io.BufferedReader;
import java.io.IOException;
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
        try {
            Client client = new Client(in.readLine(), in.readLine());
            System.out.println(client);
            clientList.addClients(client);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (conn != null) conn.close();
            } catch (IOException e) {
                System.out.println("Error on closing input stream or socket");
                e.printStackTrace();
            }
        }
    }


}
