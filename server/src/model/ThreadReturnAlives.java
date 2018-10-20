package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadReturnAlives extends Thread {

    private final Socket conn;
    private final BufferedReader in;

    public ThreadReturnAlives(Socket conn, BufferedReader in) {
        this.conn = conn;
        this.in = in;
    }

    @Override
    public void run() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(conn.getOutputStream());
            out.writeObject(ClientList.getInstance());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (conn != null) conn.close();
            } catch (IOException e) {
                System.out.println("Error on closing input stream, output stream or socket");
                e.printStackTrace();
            }
        }
    }
}
