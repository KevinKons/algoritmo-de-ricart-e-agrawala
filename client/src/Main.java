import model.Client;
import model.ClientList;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String endereco = "192.168.1.3";
        int porta = 56000;

        Socket conn = null;
        PrintWriter out = null;

        try {
            conn = new Socket(endereco, porta);
            out = new PrintWriter(conn.getOutputStream(), true);
            out.println("2");
//            out.println("192.168.1.3");
//            out.println("56001");
//            BufferedReader in = new BufferedReader(new
//                    InputStreamReader(conn.getInputStream()));
            ObjectInputStream outO = new ObjectInputStream(conn.getInputStream());
            ClientList clientList =  (ClientList) outO.readObject();
            for(Client c : clientList.getClients()) {
                System.out.println(c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (out != null) {
                out.close();
            }

        }
    }
}
