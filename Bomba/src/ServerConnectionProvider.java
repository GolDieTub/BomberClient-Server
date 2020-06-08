import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.Socket;
import java.util.logging.FileHandler;

public class ServerConnectionProvider {
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток записи в сокет
    private Socket socket;

    public BufferedWriter getOut(){
        return out;
    }

    public ServerConnectionProvider() {
        try {
            Socket socket = new Socket("localhost", 8285);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waiting() throws IOException {
        Parent root = FXMLLoader.load(ServerConnectionProvider.class.getResource("connectingMenu.fxml"));
        Stage stage = new Stage();
        stage.setTitle("BOMBERMEN");
        stage.setScene(new Scene(root, 620, 480));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        //ServerMessageThreat.start();
    }
    public void startgame() throws IOException {
        Parent root = FXMLLoader.load(ServerConnectionProvider.class.getResource("connectingMenu.fxml"));
        Stage stage = new Stage();
        stage.setTitle("BOMBERMEN");
        stage.setScene(new Scene(root, 620, 480));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        //ServerMessageThreat.start();
    }
    public void createConnection() {
        try {
            out.write("connected\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServerMessageThreat serverThreat = new ServerMessageThreat();
    }

    class ServerMessageThreat extends Thread {
        public void run() {
            try {
                String message = in.readLine();
                ServerConnectionProvider serverprovider = new ServerConnectionProvider();
                if (message.equals("start")) serverprovider.startgame();
                else {
                    in.close();
                    out.close();
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
