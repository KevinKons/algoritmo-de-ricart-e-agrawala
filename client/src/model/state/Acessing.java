package model.state;

import model.Client;
import utils.CloseConnection;
import utils.ServerInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Acessing extends State {

    private ServerInfo serverInfo = ServerInfo.getInstance();

    public Acessing(Client client) {
        super(client);
    }

    @Override
    public void nextState() {
        throw new UnsupportedOperationException("To go to the next state you must send the resource");
    }

    @Override
    public void nextState(String resource) {
        Socket conn = null;
        PrintWriter out = null;
        try {
            conn = new Socket(serverInfo.getServerIp(), serverInfo.getServerPort());
            out = new PrintWriter(conn.getOutputStream(), true);
            out.println("4");

            out.print(resource);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.getInstance().closeOutAndConn(out, conn);
        }
        emptyQueueList();
        this.client.setState(new DontWantAcess(this.client));
    }

    private void emptyQueueList() {
        Socket conn;
        PrintWriter out;
        for(Object[] obj : this.client.getOtherClientsQueue()) {
            conn = (Socket) obj[0];
            out = (PrintWriter) obj[1];
            out.println("ok");
            CloseConnection.getInstance().closeOutAndConn(out, conn);
        }
    }

    @Override
    public void respondRequest(Socket conn) {
        PrintWriter out;
        try {
            out = new PrintWriter(conn.getOutputStream(), true);
            Object[] connAndOut = new Object[2];
            connAndOut[0] = conn;
            connAndOut[1] = out;
            this.client.addOtherClientInQueue(connAndOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Abre conexão;
    *  Envia mensagem requisitando recurso;
    *  Recebe e retorna recurso.
    * */
    @Override
    public String getResource() {
        Socket conn = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            conn = new Socket(serverInfo.getServerIp(), serverInfo.getServerPort());
            out = new PrintWriter(conn.getOutputStream(), true);
            out.println("3");

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String resource = "";
            String line;
            while((line = in.readLine()) != null) {
                resource += line + "\n";
            }
            return resource;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.getInstance().close(in, out, conn);
        }
        return null;
    }
}
