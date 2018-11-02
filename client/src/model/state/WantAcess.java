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

    /* 1: Executa até que todos clientes respondam "ok";
    *  2: Estabelece conexão com cliente;
    *  3: Informa contador para se necessário, o receptor comparar tempos;
    *  4: Recebe resposta, e em caso de "ok", remove o cliente em questão
    *       da lista indicando que esse liberou o acesso ao recurso.
    *  5: Coloca o cliente no próximo estado indicando que agora ele está
    *       livre para acessar o recurso.
    * */
    @Override
    public void nextState() {
        BufferedReader in;
        PrintWriter out;
        Socket conn;
        System.out.println("verificando se posso acessar");

        //1
        while(!otherClients.isEmpty()) {
            String[] clientInfo = otherClients.get(0).split(":");

            try {
                //2
                conn = new Socket(clientInfo[0], Integer.parseInt(clientInfo[1]));
                out = new PrintWriter(conn.getOutputStream(), true);
                //3
                out.println(this.client.getCounter());

                //4
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                if(in.readLine().equalsIgnoreCase("ok")) {
                    otherClients.remove(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //5
        System.out.println("agora posso acessar");
        this.client.setState(new Acessing(this.client));

    }

    /* Compara contadores
    *  1: Caso seu próprio contador seja maior, responde "ok" liberando o outro cliente para fazer o acesso ao recurso
     *      primeiro;
    *  2: Caso seu próprio contador seja menor, guarda a conexão do outro cliente numa lista para após fazer uso do
    *       recurso responder "ok" liberando o outro cliente para fazer o acesso.
    * */
    @Override
    public void respondRequest(Socket conn) {
        BufferedReader in;
        PrintWriter out;
        try {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            out = new PrintWriter(conn.getOutputStream(), true);

            int otherCounter = Integer.parseInt(in.readLine());
            if(this.client.getCounter() >= otherCounter) {
                out.println("ok");
                CloseConnection.getInstance().closeAll(in, out, conn);
            } else {
                this.client.addOtherClientInQueue(conn);
                CloseConnection.getInstance().closeInAndOut(in, out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
