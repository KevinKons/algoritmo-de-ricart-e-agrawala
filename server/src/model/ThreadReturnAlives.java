package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
        PrintWriter out = null;
        try {
            out = new PrintWriter(conn.getOutputStream(), true);
            for(Client c : ClientList.getInstance().getClients()) {
                out.println(c.getIp() + ";" + c.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            new CloseConnection().close(in, out, conn);
        }
    }
}
