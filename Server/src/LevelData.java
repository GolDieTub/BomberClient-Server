import java.util.ArrayList;
import java.util.List;

public class LevelData {

    private int[][] levelContent;

    private List<Player> players;

    LevelData(final int[][] levelContent) {
        this.levelContent = levelContent;
    }

    public int getWidth() {
        return levelContent.length;
    }

    public int getHeight() {
        return levelContent[0].length;
    }

    public Integer getBlockByPosition(final int posX, final int posY) {
        if (posX < 0 || posY < 0) {
            return null;
        }

        if (posX >= levelContent.length || posY >= levelContent[0].length) {
            return null;
        }
        return levelContent[posX][posY];
    }

    public Integer getBlockByCoordinates(final int cordX, final int cordY) {
        final int posX = cordX / Constants.BLOCK_SIZE;
        final int posY = cordY / Constants.BLOCK_SIZE;
        return getBlockByPosition(posX, posY);
    }

    public static int getPositionByCoordinate(final double cord) {
        return ((int) cord / Constants.BLOCK_SIZE);
    }

//    public void plantBomb(final int posX, final int posY) {
//        if (Player.canPlantBomb()) {
//            final Executable explosiveTask = new ExplosiveTask(posX, posY);
//            final Block bomb = new Bomb(explosiveTask);
//            levelContent[posX][posY] = bomb;
//            needRebuilding = true;
//        }
//    }

//    public double getPaneHeight() {
//        return getHeight() * Constants.BLOCK_SIZE;
//    }
//
//    public double getPaneWidth() {
//        return getWidth() * Constants.BLOCK_SIZE;
//    }
//
//    public double getBlockCordX(final int posX) {
//        return posX * Constants.BLOCK_SIZE;
//    }
//
//    public double getBlockCordY(final int posY) {
//        return posY * Constants.BLOCK_SIZE;
//    }

//    private void explosion(final int posX, final int posY) {
//        levelContent[posX][posY] = new ExplosionBlock(new FireTask(posX, posY));
//        //todo анимация
//        needRebuilding = true;
//    }

//    private class FireTask implements Executable {
//
//        private final int posX;
//        private final int posY;
//
//        private FireTask(int posX, int posY) {
//            this.posX = posX;
//            this.posY = posY;
//        }
//
//        @Override
//        public void execute() {
//            levelContent[posX][posY] = new BackgroundBlock();
//            needRebuilding = true;
//        }
//    }
//
//    private class ExplosiveTask implements Executable {
//
//        private final int posX;
//        private final int posY;
//
//        ExplosiveTask(final int posX, final int posY) {
//            this.posX = posX;
//            this.posY = posY;
//        }
//
//        @Override
//        public void execute() {
//            bomberman.releaseBomb();
//            explosiveTop();
//            explosiveBottom();
//            explosiveLeft();
//            explosiveRight();
//            ;
//            explosion(posX, posY);
//        }
//
//        private void explosiveTop() {
//            boolean active = true;
//            for (int i = 0; i < Constants.EXPLOSIVE_POWER && active; i++) {
//                final int posY = this.posY - i;
//                final Block block = getBlockByPosition(posX, posY);
//                if (block != null) {
//                    active = block.isDestroyable();
//                    if (active) {
//                        explosion(posX, posY);
//                    }
//
//                }
//                notifyExplosive(posX, posY);
//            }
//        }
//
//        private void explosiveBottom() {
//            boolean active = true;
//            for (int i = 0; i < Constants.EXPLOSIVE_POWER && active; i++) {
//                final int posY = this.posY + i;
//                final Block block = getBlockByPosition(posX, posY);
//                if (block != null) {
//                    active = block.isDestroyable();
//                    if (active) {
//                        explosion(posX, posY);
//                    }
//                }
//                notifyExplosive(posX, posY);
//            }
//        }
//
//        private void explosiveLeft() {
//            boolean active = true;
//            for (int i = 0; i < Constants.EXPLOSIVE_POWER && active; i++) {
//                final int posX = this.posX - i;
//                final Block block = getBlockByPosition(posX, posY);
//                if (block != null) {
//                    active = block.isDestroyable();
//                    if (active) {
//                        explosion(posX, posY);
//                    }
//                }
//                notifyExplosive(posX, posY);
//            }
//        }
//
//        private void explosiveRight() {
//            boolean active = true;
//            for (int i = 0; i < Constants.EXPLOSIVE_POWER && active; i++) {
//                final int posX = this.posX + i;
//                final Block block = getBlockByPosition(posX, posY);
//                if (block != null) {
//                    active = block.isDestroyable();
//                    if (active) {
//                        explosion(posX, posY);
//                    }
//                }
//                notifyExplosive(posX, posY);
//            }
//        }
//
//        private void notifyExplosive(final int posX, final int posY) {
//            bomberman.explosive(posX, posY);
//            for (final Enemy enemy : enemies) {
//                enemy.explosive(posX, posY);
//            }
//        }
//    }
//
//    private class OverlayThread extends Thread {
//        @Override
//        public void run() {
//            while (LevelData.this.isActive()) {
//                for (final Character enemy : enemies) {
//                    if (bomberman.isOverlayCharacter(enemy)) {
//                        needRebuilding = true;
//                    }
//                }
//            }
//        }
//    }
}
