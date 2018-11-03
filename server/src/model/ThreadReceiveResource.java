package model;

import util.CloseConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ThreadReceiveResource extends Thread {

    private final Socket conn;
    private final BufferedReader in;

    public ThreadReceiveResource(Socket conn, BufferedReader in) {
        this.conn = conn;
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String resource = "";
            String line;
            while((line = in.readLine()) != null) {
                resource += line + "\n";
            }
            System.out.println(resource);
            Resource.getInstance().setResource(resource);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.getInstance().closeInAndConn(in, conn);
        }
    }
}
