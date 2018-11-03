package model;

import util.CloseConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadSendResource extends Thread {

    private final BufferedReader in;
    private final Socket conn;

    public ThreadSendResource(Socket conn, BufferedReader in) {
        this.conn = conn;
        this.in = in;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        System.out.println(conn.getInetAddress() + ":" + conn.getLocalPort() +" acessou");
        try {
            out = new PrintWriter(conn.getOutputStream(), true);
            String[] resourceLines = Resource.getInstance().getResource().split("\n");
            for(String resourceLine : resourceLines) {
                out.println(resourceLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.getInstance().close(in, out, conn);
        }
    }
}
