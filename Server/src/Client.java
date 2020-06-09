import java.io.*;
import java.net.Socket;


public class Client extends Thread {

    private static int CONNECTION_TIMEOUT = 300000;
    private final PrintWriter out;
    private BufferedReader in;

    private Socket socket;

    private String name;
    private boolean active;
    // private  dataProvider

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        active = true;
        in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
        out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream())), true);
    }

    public BufferedReader getIn() {
        return in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void run() {

    }
}