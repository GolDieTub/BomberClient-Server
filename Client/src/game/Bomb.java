package game;

import game.blocks.Block;
import javafx.scene.media.MediaPlayer;
import utilities.Executable;

import java.io.File;
import java.net.URI;

public class Bomb extends Block {

    private final Executable explosion;


    Bomb(Executable explosion) {
        this.explosion = explosion;
        new ExplosiveTimer().start();
    }

    @Override
    public boolean isDestroyable() {
        return true;
    }

    @Override
    public boolean isPermeable() {
        return true;
    }

    @Override
    protected String getImagePath() {
        return Constants.BOMB_IMAGE;
    }

    private class ExplosiveTimer extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(Constants.EXPLOSION_TIME);
                explosion.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
