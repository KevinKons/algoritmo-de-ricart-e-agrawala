package model.state;

import model.Client;
import utils.CloseConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class DontWantAcess extends State {

    public DontWantAcess(Client client) {
        super(client);
    }

    @Override
    public void nextState() {
        Socket conn = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            conn = new Socket(this.client.getIp(), this.client.getPort());
            out = new PrintWriter(conn.getOutputStream(), true);
            out.println("2");

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            List<String> otherClients = new ArrayList<>();
            while((line = in.readLine()) != null) {
                otherClients.add(line);
            }

            this.client.setState(new WantAcess(this.client, otherClients));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.getInstance().close(in, out, conn);
        }
    }

    @Override
    public void respondRequest(Socket conn) {
        try {
            PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
            out.println("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
