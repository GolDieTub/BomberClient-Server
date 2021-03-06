import javafx.application.Platform;
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
    private PrintWriter out; // поток записи в сокет
    private Socket socket;
    private Stage st;

    connectingMenuController con = new connectingMenuController();

    public PrintWriter getOut(){
        return out;
    }

    public ServerConnectionProvider() {
        try {
            Socket socket = new Socket("localhost", 8285);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waiting() throws IOException {
        Parent root = FXMLLoader.load(ServerConnectionProvider.class.getResource("connectingMenu.fxml"));
        Stage stage = new Stage();
        st=stage;
        stage.setTitle("BOMBERMEN");
        stage.setScene(new Scene(root, 620, 480));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        ServerMessageThreat serverThreat = new ServerMessageThreat();
        serverThreat.start();
    }
    public void startgame() throws IOException {
        //final Stage stage1 = (Stage)con.getConnecting().getScene().getWindow();
        st.close();
        Parent root = FXMLLoader.load(ServerConnectionProvider.class.getResource("gamescene.fxml"));
        Stage stage = new Stage();
        stage.setTitle("BOMBERMEN");
        stage.setScene(new Scene(root, 620, 480));
        stage.initStyle(StageStyle.DECORATED);
        stage.show();

    }
    public void createConnection() {
        out.println("connected");
        //ServerMessageThreat serverThreat = new ServerMessageThreat();
        //serverThreat.run();
    }

    class ServerMessageThreat extends Thread {

        public void run() {
            try {
                String message = in.readLine();
                //System.out.println(message);
                if (message.equals("start")) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                startgame();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
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
