import model.Client;

import java.io.IOException;
import java.net.InetAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.setIp(InetAddress.getLocalHost().getHostAddress());
        client.initProcess();


    }

}
