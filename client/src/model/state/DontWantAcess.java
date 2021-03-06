package model.state;

import model.Client;
import utils.CloseConnection;
import utils.ServerInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DontWantAcess extends State {

    public DontWantAcess(Client client) {
        super(client);
    }

    /* 1: Estabelece uma conexão com o servidor principal;
    *  2: Envia um código 2, requisitando o endereço de todos os clientes participantes;
    *  3: Recebe o endereço de todos os clientes participando;
    *  4: Coloca o cliente no próximo estado passando os clientes participantes.
    * */
    @Override
    public void nextState() {
        Socket conn = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            //1
            ServerInfo serverInfo = ServerInfo.getInstance();
            conn = new Socket(serverInfo.getServerIp(), serverInfo.getServerPort());
            out = new PrintWriter(conn.getOutputStream(), true);
            //2
            out.println("2");
            //3
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            List<String> otherClients = new ArrayList<>();
            Stream<String> lines = in.lines();
            lines.forEach(line -> otherClients.add(line));
            //4
            this.client.setState(new WantAcess(this.client, otherClients));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.getInstance().close(in, out, conn);
        }
    }

    @Override
    public void nextState(String resource) {
        throw new UnsupportedOperationException("You do not have the permission to send the resource in this state");
    }

    /*
    * Ao receber uma requisição de outro cliente para obtenção de acesso,
    * responde ok, pois o mesmo não está fazendo e nem mesmo deseja
    * fazer uso do recurso compartilhado.
    * */
    @Override
    public void respondRequest(Socket conn) {
        try {
            PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
            out.println("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getResource() {
        throw new UnsupportedOperationException("You do not have the permission to access the resource in this state");
    }
}
