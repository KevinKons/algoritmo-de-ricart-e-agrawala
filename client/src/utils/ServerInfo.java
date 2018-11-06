package utils;

public class ServerInfo {

    private ServerInfo() {}
    private static ServerInfo instance;
    public static ServerInfo getInstance() {
        if(instance == null)
            instance = new ServerInfo();

        return instance;
    }

    private String serverIp;
    private int serverPort;

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
}
