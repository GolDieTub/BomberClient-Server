package game.blocks;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Block extends Pane {

    private Image image;
    private ImageView imageView;
    protected Block() {
        image = new Image(getClass().getResourceAsStream(getImagePath()));
        imageView = new ImageView(image);
        this.getChildren().add(imageView);
    }

    public abstract boolean isDestroyable();

    public abstract boolean isPermeable();

    protected abstract String getImagePath();

    public void setImageView(String url){
        this.getChildren().remove(this.imageView);
        image = new Image(getClass().getResourceAsStream(url));
        imageView = new ImageView(image);
        imageView = new ImageView(image);
        this.getChildren().add(imageView);
    }

}
