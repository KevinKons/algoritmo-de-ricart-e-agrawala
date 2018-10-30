package model;

import java.io.Serializable;

public class Client implements Serializable {

    private String ip;
    private int port;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "Client: " + ip + ":" + port;
    }
}
