import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Controller {
    @FXML
    ImageView start;
    @FXML
    public void onStartPressed() throws IOException {

        ServerConnectionProvider serverprovider1 = new ServerConnectionProvider();

        serverprovider1.createConnection();

        serverprovider1.waiting();

        final Stage stage = (Stage)start.getScene().getWindow();
        stage.close();
    }

}
