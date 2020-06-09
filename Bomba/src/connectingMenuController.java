import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class connectingMenuController {

    public static final String WIFI_FULL = "images/wifi/wifi.png";
    public static final String WIFI_ONE = "images/wifi/wifi_one.png";
    public static final String WIFI_TWO = "images/wifi/wifi_two.png";
    public static final String WIFI_THREE = "images/wifi/wifi_three.png";

    Image connectingImage;

    @FXML
    ImageView connecting;

    public ImageView getConnecting(){
        return connecting;
    }

    private int time = 500;
    private Runnable timer;
    List<String> connectingsList = new ArrayList();
    int count = 0;

    public void initialize() {
        connectingsList.add(WIFI_ONE);
        connectingsList.add(WIFI_TWO);
        connectingsList.add(WIFI_THREE);
        connectingsList.add(WIFI_FULL);
        connectingImage = new Image(WIFI_ONE);
        Timeline timeline = new Timeline (
                new KeyFrame(
                        Duration.millis(1000 ),
                        ae -> {
                            if(count == 4){count = 0;}
                            connectingImage = new Image(connectingsList.get(count));
                            connecting.setImage(connectingImage);
                            count++;


                        }
                )
        );

        timeline.setCycleCount(9999);
        timeline.play();
    }

    public void onPresse(MouseEvent mouseEvent) {
        //out.write();
    }
}
