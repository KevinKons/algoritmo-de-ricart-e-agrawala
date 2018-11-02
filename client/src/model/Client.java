package model;

import model.state.DontWantAcess;
import model.state.State;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client implements Serializable {

    private String ip;
    private int port;
    private State state = new DontWantAcess(this);
    private SendIsAlive sendIsAlive = new SendIsAlive("10.60.95.82");
    private Server server = new Server();
    private int counter = 0;
    private List<Socket> otherClientQueue = new ArrayList<>();

    public void initProcess() {
        this.port = sendIsAlive.send(ip);
        server.setPort(this.port);
        server.setClient(this);
        server.start();
        runProcess();
    }

    private void runProcess() {
        Random random = new Random();
        while(counter <= 20) {
            try {
                Thread.sleep((random.nextInt(15) + 31) * 1000); //simulating operation
                this.state.nextState();
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
    public State getState() {
        return state;
    }
    public List<Socket> getOtherClientsQueue() {
        return otherClientQueue;
    }
    public void addOtherClientInQueue(Socket conn) {
        this.otherClientQueue.add(conn);
    }
    public int getCounter() {
        return counter;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Client: " + ip + ":" + port;
    }

}
