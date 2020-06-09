package game.characters;

import game.Constants;
import game.LevelData;
import game.Point;
import game.blocks.Block;
import javafx.scene.image.Image;

public abstract class Character extends Block {

    private boolean isAlive = true;

    private int moveSpeed = 3;

    final LevelData levelData;

    Character(LevelData levelData) {
        this.levelData = levelData;
    }

    public boolean isAlive() {
        return isAlive;
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
       // setImageView(new Image(Constants.CHARACTER_IMAGE));
        setLayoutX(getLayoutX() - availableSteps);
    }

    public void moveRight() {
        final int availableSteps = getAvailableRightSteps();
    //    setImageView(new Image(Constants.CHARACTER_R));
        setLayoutX(getLayoutX() + availableSteps);
    }

    public void moveUp() {
        final int availableSteps = getAvailableUpSteps();
     //   setImageView(new Image(Constants.CHARACTER_U));
        setLayoutY(getLayoutY() - availableSteps);
    }

    public void moveDown() {
        final int availableSteps = getAvailableDownSteps();
       // setImageView(new Image(Constants.CHARACTER_IMAGE));
        setLayoutY(getLayoutY() + availableSteps);
    }

    public void explosive(final int posX, final int posY) {
        final double cordX = getLayoutX();
        final double cordY = getLayoutY();
        final int characterPosX = LevelData.getPositionByCoordinate(cordX);
        final int characterPosY = LevelData.getPositionByCoordinate(cordY);
        if (characterPosX == posX && characterPosY == posY) {
            isAlive = false;
        }
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
