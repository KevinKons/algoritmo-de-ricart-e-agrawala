package utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class CloseConnection {

    private CloseConnection() {}
    private static CloseConnection instance;
    public static CloseConnection getInstance() {
        if(instance == null)
            instance = new CloseConnection();

        return instance;
    }

    public void close(Reader in, Writer out, Socket conn) {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (conn != null) conn.close();
        } catch (IOException e) {
            System.out.println("Error on closing input stream, output stream or socket");
            e.printStackTrace();
        }
    }

    public void closeOutAndConn(PrintWriter out, Socket conn) {
        try {
            if (out != null) out.close();
            if (conn != null) conn.close();
        } catch (IOException e) {
            System.out.println("Error on closing output stream or socket");
            e.printStackTrace();
        }
    }
}
