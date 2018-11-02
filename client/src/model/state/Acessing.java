package model.state;

import model.Client;
import utils.CloseConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Acessing extends State {

    public Acessing(Client client) {
        super(client);
    }

    @Override
    public void nextState() {

    }

    @Override
    public void respondRequest(Socket conn) {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            out = new PrintWriter(conn.getOutputStream(), true);
            in.readLine();
            String otherIp = in.readLine();
            String otherPort = in.readLine();
            this.client.addOtherClientInQueue(otherIp + ":" + otherPort);
            out.println("wait");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.getInstance().closeAll(in, out, conn);
        }
    }
}
