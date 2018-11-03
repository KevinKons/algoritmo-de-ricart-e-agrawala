package model;

import utils.CloseConnection;
import utils.ServerInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendIsAlive {

    public int send(String myIp) {
        Socket conn = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            conn = new Socket(ServerInfo.serverIp, ServerInfo.serverPort);
            out = new PrintWriter(conn.getOutputStream(), true);
            out.println("1");
            out.println(myIp);

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.getInstance().close(in, out, conn);
        }
        return Integer.parseInt(null);
    }

}
