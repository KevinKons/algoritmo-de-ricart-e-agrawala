package model;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class CloseConnection {

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
}
