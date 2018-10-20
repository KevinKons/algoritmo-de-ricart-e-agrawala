package model;

import java.io.Serializable;

public class Client implements Serializable {

    private String ip;
    private String port;

    public Client(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "Client: " + ip + ":" + port;
    }
}
