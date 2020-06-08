import java.net.Socket;

public class Client extends Thread {

    private static int CONNECTION_TIMEOUT = 300000;

    private Socket socket;

    private String name;
    private boolean active;

    public Client(Socket socket) {
        this.socket = socket;
        active = true;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void run() {

    }
}