package model;

import java.io.Serializable;
import java.util.Random;

public class Client implements Serializable {

    private static Client instance;
    private Client() { }
    public static Client getInstance() {
        if(instance == null)
            instance = new Client();

        return instance;
    }

    private String ip;
    private int port;
    private State state = new DontWantAcess(this);
    private SendIsAlive sendIsAlive = new SendIsAlive("172.19.192.1");
    private Server server = new Server();
    private int counter = 0;

    public void initProcess() {
        this.port = sendIsAlive.send(ip);
        server.setPort(this.port);
        server.start();
        runProcess();
    }

    private void runProcess() {
        Random random = new Random();
        while(counter <= 20) {
            try {
                Thread.sleep((random.nextInt(15) + 31) * 1000); //simulating operation
                this.state.nextState();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getIp() {
        return ip;
    }
    public int getPort() {
        return port;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setPort(int port) {
        this.port = port;
        this.server.setPort(port);
    }
    public Server getServer() {
        return server;
    }

    @Override
    public String toString() {
        return "Client: " + ip + ":" + port;
    }

}
