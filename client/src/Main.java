import model.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws InterruptedException, UnknownHostException {
        Client client = new Client();
        client.setIp(InetAddress.getLocalHost().getHostAddress());
        client.initProcess();
    }
}
