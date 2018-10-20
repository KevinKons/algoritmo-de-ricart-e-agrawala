package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientList implements Serializable {

    public static ClientList getInstance() {
        if(instance == null) {
            instance = new ClientList();
        }

        return instance;
    }

    private static ClientList instance;

    private ClientList() {}

    private List<Client> clients = new ArrayList<>();

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void addClients(Client client) {
        this.clients.add(client);
    }


}
