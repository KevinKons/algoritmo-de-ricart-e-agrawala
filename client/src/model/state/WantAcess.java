package model.state;

import model.Client;
import utils.CloseConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class WantAcess extends State {

    private final List<String> otherClients;

    public WantAcess(Client client, List<String> otherClients) {
        super(client);
        this.otherClients = otherClients;
    }

    @Override
    public void nextState() {
        while(!otherClients.isEmpty()) {
            BufferedReader in = null;
            PrintWriter out = null;
            Socket conn = null;
            String[] clientInfo = otherClients.get(0).split(":");

            try {
                conn = new Socket(clientInfo[0], Integer.parseInt(clientInfo[1]));
                out = new PrintWriter(conn.getOutputStream(), true);
                out.println(this.client.getCounter());
                out.println(this.client.getIp());
                out.println(this.client.getPort());

                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void respondRequest(Socket conn) {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            out = new PrintWriter(conn.getOutputStream(), true);
            int otherCounter = Integer.parseInt(in.readLine());
            if(this.client.getCounter() >= otherCounter) {
                out.println("ok");
            } else {
                String otherIp = in.readLine();
                String otherPort = in.readLine();
                this.client.addOtherClientInQueue(otherIp + ":" + otherPort);
                out.println("wait");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.getInstance().close(in, out, conn);
        }
    }
}
