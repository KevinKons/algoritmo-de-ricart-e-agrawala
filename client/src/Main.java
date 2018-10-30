import model.Client;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Client client = Client.getInstance();
        client.setIp("172.19.192.1");
        client.initProcess();


    }
}
