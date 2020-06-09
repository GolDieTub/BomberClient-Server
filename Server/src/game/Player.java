package game;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Player {

    private int cordX;
    private int cordY;
    private Client client;

    int moveSpeed = 3;

    public Player(Client client) {
        this.client = client;
    }

    public int getCordX() {
        return cordX;
    }

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public void setCordY(int cordY) {
        this.cordY = cordY;
    }

    private int getAvailableLeftSteps() {
        for (int i = 1; i <= moveSpeed; i++) {
            final boolean firstBlockIsPermeable = getBlockPermeableByOffset(-i, 0);
            final boolean secondBlockIsPermeable = getBlockPermeableByOffset(-i, Constants.CHARACTER_SIZE - 1);
            if (!(firstBlockIsPermeable && secondBlockIsPermeable)) {
                return i - 1;
            }
        }
        return moveSpeed;
    }

    private int getAvailableRightSteps() {
        for (int i = 1; i <= moveSpeed; i++) {
            final boolean firstBlockIsPermeable =
                    getBlockPermeableByOffset(Constants.CHARACTER_SIZE + i, 0);
            final boolean secondBlockIsPermeable =
                    getBlockPermeableByOffset(Constants.CHARACTER_SIZE + i, Constants.CHARACTER_SIZE - 1);
            if (!(firstBlockIsPermeable && secondBlockIsPermeable)) {
                return i - 1;
            }
        }
        return moveSpeed;
    }

    private int getAvailableUpSteps() {
        for (int i = 1; i <= moveSpeed; i++) {
            final boolean firstBlockIsPermeable = getBlockPermeableByOffset(0, -1);
            final boolean secondBlockIsPermeable =
                    getBlockPermeableByOffset(Constants.CHARACTER_SIZE - 1, -1);
            if (!(firstBlockIsPermeable && secondBlockIsPermeable)) {
                return i - 1;
            }
        }
        return moveSpeed;
    }

    private int getAvailableDownSteps() {
        for (int i = 1; i <= moveSpeed; i++) {
            final boolean firstBlockIsPermeable =
                    getBlockPermeableByOffset(0, Constants.CHARACTER_SIZE + i);
            final boolean secondBlockIsPermeable =
                    getBlockPermeableByOffset(Constants.CHARACTER_SIZE - 1, Constants.CHARACTER_SIZE + i);
            if (!(firstBlockIsPermeable && secondBlockIsPermeable)) {
                return i - 1;
            }
        }
        return moveSpeed;
    }

    public void moveLeft() {
        final int availableSteps = getAvailableLeftSteps();
        // setImageView(new Image(game.Constants.CHARACTER_IMAGE));
        setLayoutX(getLayoutX() - availableSteps);
    }

    public void moveRight() {
        final int availableSteps = getAvailableRightSteps();
        //    setImageView(new Image(game.Constants.CHARACTER_R));
        setLayoutX(getLayoutX() + availableSteps);
    }

    public void moveTop() {
        final int availableSteps = getAvailableUpSteps();
        //   setImageView(new Image(game.Constants.CHARACTER_U));
        setLayoutY(getLayoutY() - availableSteps);
    }

    public void moveDown() {
        final int availableSteps = getAvailableDownSteps();
        // setImageView(new Image(game.Constants.CHARACTER_IMAGE));
        setLayoutY(getLayoutY() + availableSteps);
    }

    public void setBomb() {
        final int bombPosX = LevelData.getPositionByCoordinate(getLayoutX());
        final int bombPosY = LevelData.getPositionByCoordinate(getLayoutY());
        levelData.plantBomb(bombPosX, bombPosY);
        availableBombCount--;
    }

    public void releaseBomb() {
        synchronized (LOCK) {
            availableBombCount++;
        }
    }

    private boolean hasSpaceForBomb() {
        final int posX = LevelData.getPositionByCoordinate(getLayoutX());
        final int posY = LevelData.getPositionByCoordinate(getLayoutY());
        return levelData.getBlockByPosition(posX, posY).isPermeable();
    }

    public boolean canPlantBomb() {
        return ((availableBombCount > 0) && hasSpaceForBomb() && isAlive());
    }

    private boolean getBlockPermeableByOffset(final int offsetX, final int offsetY) {
        final int posX = (int) (getLayoutX() + offsetX);
        final int posY = (int) (getLayoutY() + offsetY);
        final Block block = levelData.getBlockByCoordinates(posX, posY);
        return block.isPermeable();
    }

    private CharacterFrame getCharacterFrame() {
        final Point topLeftPoint = new Point(getLayoutX(), getLayoutY());
        final Point topRightPoint = new Point(getLayoutX() + Constants.CHARACTER_SIZE, getLayoutY());
        final Point downLeftPoint = new Point(getLayoutX(), getLayoutY() + Constants.CHARACTER_SIZE);
        final Point downRightPoint = new Point(getLayoutX() + Constants.CHARACTER_SIZE,
                getLayoutY() + Constants.CHARACTER_SIZE);

        return new CharacterFrame(topLeftPoint, topRightPoint, downLeftPoint, downRightPoint);
    }

    public boolean isOverlayCharacter(final Character other){
        final CharacterFrame otherFrame = other.getCharacterFrame();
        final CharacterFrame thisFrame = getCharacterFrame();

        if (thisFrame.isOverLay(otherFrame)){
            isAlive = false;
            return true;
        }

        return false;
    }

}
