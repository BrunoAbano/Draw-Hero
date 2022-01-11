import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.util.HashMap;

public class Game {
    private final int cellSize = 20;
    private final int height;
    private final int width;
    private final int cols;
    private final int rows;

    private int counter;
    private int loadCounter;
    private final HashMap<Integer, Rectangle> list = new HashMap<>();
    private final HashMap<Integer, Rectangle> loadList = new HashMap<>();
    private final Rectangle colorRectangle;
    private final Picture controls;


    public Game(int cols, int rows){

        this.cols = cols;
        this.rows = rows;

        this.width = cols * cellSize;
        this.height = rows * cellSize;

        int PADDING = 10;

        //window rectangle
        Rectangle rectangle = new Rectangle(PADDING, PADDING, this.width, this.height);
        rectangle.draw();

        //map
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Rectangle rectangleCell = new Rectangle(this.cellSize * j + PADDING, this.cellSize * i + PADDING, this.cellSize, this.cellSize);
                rectangleCell.setColor(Color.DARK_GRAY);
                rectangleCell.draw();

                this.list.put(this.counter, rectangleCell);
                this.counter++;
            }
        }

        //color rectangle
        this.colorRectangle = new Rectangle(this.width + PADDING + this.cellSize * 1.7, PADDING + this.cellSize, this.cellSize * 3, this.cellSize * 3);
        this.colorRectangle.setColor(Color.BLACK);
        this.colorRectangle.fill();

        //minimap
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Rectangle rectangleCell = new Rectangle(this.width + this.cellSize + this.cellSize/6 * j, this.cellSize/6 * i + this.height / 2.155, this.cellSize / 6 , this.cellSize / 6);
                rectangleCell.setColor(Color.DARK_GRAY);
                rectangleCell.draw();

                this.loadList.put(this.loadCounter, rectangleCell);
                this.loadCounter++;
            }
        }

        //controls image
        this.controls = new Picture(this.width + this.cellSize * 1.8, this.height - this.cellSize * 5, "resources/Controls.png");
        this.controls.draw();
    }

    public void init(){
        new Player(this.cellSize, this.rows, this.cols, this.height, this.width, this.list, this.loadList, this.colorRectangle);
    }

}