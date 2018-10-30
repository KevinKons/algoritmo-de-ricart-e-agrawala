package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendIsAlive {

    private String serverIp;

    public SendIsAlive(String serverIp) {
        this.serverIp = serverIp;
    }

    public int send(String myIp) {
        try {
            Socket conn = new Socket(serverIp, 56000);
            PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
            out.println("1");
            out.println(myIp);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(null);
    }

}
