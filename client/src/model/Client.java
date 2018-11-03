package model;

import model.state.DontWantAcess;
import model.state.State;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client implements Serializable {

    private String ip;
    private int port;
    private State state = new DontWantAcess(this);
    private SendIsAlive sendIsAlive = new SendIsAlive();
    private Server server = new Server();
    private int counter = 0;
    private List<Object[]> otherClientQueue = new ArrayList<>();

    public void initProcess() {
        this.port = sendIsAlive.send(ip);
        server.setPort(this.port);
        server.setClient(this);
        server.start();
        runProcess();
    }

    private void runProcess() {
        Random random = new Random();
        while(counter <= 10) {
            try {
                Thread.sleep((random.nextInt(15) + 31) * 1000); //simulating operation
                this.state.nextState();
                this.state.nextState();
                String resource = this.state.getResource();
                resource += ip + ":" + port + " Esteve aqui pela " + counter + "ª vez\n\n";
                this.state.nextState(resource);
                counter++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public State getState() {
        return state;
    }
    public List<Object[]> getOtherClientsQueue() {
        return otherClientQueue;
    }
    public void addOtherClientInQueue(Object[] connAndOut) {
        this.otherClientQueue.add(connAndOut);
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
