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
            conn = new Socket(ServerInfo.serverIp, ServerInfo.serverPort);
            out = new PrintWriter(conn.getOutputStream(), true);
            //2
            out.println("2");
            //3
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            List<String> otherClients = new ArrayList<>();
            while((line = in.readLine()) != null) {
                otherClients.add(line);
            }
            //4
            this.client.setState(new WantAcess(this.client, otherClients));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseConnection.getInstance().closeAll(in, out, conn);
        }
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
}
