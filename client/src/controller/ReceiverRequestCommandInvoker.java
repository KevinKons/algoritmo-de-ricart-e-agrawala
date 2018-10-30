package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ReceiverRequestCommandInvoker {

    private static ReceiverRequestCommandInvoker instance;

    private ReceiverRequestCommandInvoker() {
//        commands.put(1, new Respond)
    }

    public static ReceiverRequestCommandInvoker getInstance() {
        if (instance == null)
            instance = new ReceiverRequestCommandInvoker();

        return instance;
    }

    private Map<Integer, Command> commands = new HashMap<>();

    public void execute(Socket conn) {
        BufferedReader in = null;
        String option = null;
        try {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            option = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Command comm = commands.get(Integer.parseInt(option)).clonar();
        comm.execute(conn);
    }
}
