import model.Client;
import utils.ServerInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerInfo serverInfo = ServerInfo.getInstance();
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o ip do server");
        serverInfo.setServerIp(sc.nextLine());
        System.out.println("Informe a porta do server");
        serverInfo.setServerPort(sc.nextInt());


        Client client = new Client();
        client.setIp(InetAddress.getLocalHost().getHostAddress());
        client.initProcess();


    }

}
