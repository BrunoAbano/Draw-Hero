import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.HashMap;

public class Player {
    private final int PADDING = 10;
    private final int playerSize;
    private final int rows;
    private final int cols;

    public Player(int playerSize, int rows, int cols, int height, int width, HashMap<Integer, Rectangle> list, HashMap<Integer, Rectangle> loadList, Rectangle colorRectangle){
        this.playerSize = playerSize;
        this.rows = rows;
        this.cols = cols;

        Rectangle player = new Rectangle(PADDING, PADDING, playerSize, playerSize);
        player.setColor(Color.GREEN);
        player.fill();

        new KeyboardHandler(player, height, width, this, list, loadList, colorRectangle);
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return cols;
    }

    public int getPADDING() {
        return this.PADDING;
    }

    public int getPlayerSize() {
        return this.playerSize;
    }

}