import java.io.*;
import java.net.Socket;


public class Client {

    private int id;
    private final PrintWriter out;
    private BufferedReader in;
    private boolean isActive = true;
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
        new CommandReader().start();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String command;

    public String getCommand(){
        String currentCommand = command;
        command = null;
        return currentCommand;
    }

    private class CommandReader extends Thread{
        @Override
        public void run() {
            while (isActive){
                try {
                    String command = in.readLine();
                    Client.this.command = command;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}