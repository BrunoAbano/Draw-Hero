import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import java.io.*;
import java.util.*;


public class KeyboardHandler implements org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler {
    private final Rectangle player;
    private final Player player1;
    private boolean painting;
    private int colorNumber;
    private int colorNumberCounter = 1;

    private final int height;
    private final int width;

    private int playerLocation;
    private final HashMap<Integer, Rectangle> list;
    private final HashMap<Integer, Rectangle> loadList;
    private final Rectangle colorRectangle;

    private final HashMap<Integer, String> loadMap;
    private final String darkGray = "dark_gray";
    private final String black = "black";
    private final String blue = "blue";
    private final String red = "red";
    private final String cyan = "cyan";
    private final String orange = "orange";
    private final String pink = "pink";
    private final String yellow = "yellow";
    private final String gray = "gray";
    private final String magenta = "magenta";

    private final Set<Integer> pressed = new HashSet<>();


    public KeyboardHandler(Rectangle player, int height, int width, Player player1, HashMap<Integer, Rectangle> list, HashMap<Integer, Rectangle> loadList, Rectangle colorRectangle){
        this.player = player;
        this.player1 = player1;
        this.height = height;
        this.width = width;
        this.list = list;
        this.loadList = loadList;
        this.colorRectangle = colorRectangle;
        this.loadMap = new HashMap<>();


        for (int i = 0; i < player1.getRows() * player1.getCols(); i++) {
            this.loadMap.put(i, this.darkGray);
        }

        try {
            File mapToRead = new File("resources/Map");
            FileInputStream fileInputStream = new FileInputStream(mapToRead);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            HashMap<Integer, String> mapInFile = (HashMap<Integer, String>)objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            this.loadMap.putAll(mapInFile);

            for(Integer key : this.loadMap.keySet()){
                if (this.loadMap.get(key).equals(this.darkGray)) {
                    this.loadList.get(key).setColor(Color.DARK_GRAY);
                    this.loadList.get(key).draw();
                }else if(this.loadMap.get(key).equals(this.black)){
                    this.loadList.get(key).setColor(Color.BLACK);
                    this.loadList.get(key).fill();
                }else if(this.loadMap.get(key).equals(this.blue)){
                    this.loadList.get(key).setColor(Color.BLUE);
                    this.loadList.get(key).fill();
                }else if(this.loadMap.get(key).equals(this.red)){
                    this.loadList.get(key).setColor(Color.RED);
                    this.loadList.get(key).fill();
                }else if(this.loadMap.get(key).equals(this.cyan)){
                    this.loadList.get(key).setColor(Color.CYAN);
                    this.loadList.get(key).fill();
                }else if(this.loadMap.get(key).equals(this.orange)){
                    this.loadList.get(key).setColor(Color.ORANGE);
                    this.loadList.get(key).fill();
                }else if(this.loadMap.get(key).equals(this.pink)){
                    this.loadList.get(key).setColor(Color.PINK);
                    this.loadList.get(key).fill();
                }else if(this.loadMap.get(key).equals(this.yellow)){
                    this.loadList.get(key).setColor(Color.YELLOW);
                    this.loadList.get(key).fill();
                }else if(this.loadMap.get(key).equals(this.gray)){
                    this.loadList.get(key).setColor(Color.GRAY);
                    this.loadList.get(key).fill();
                }else if(this.loadMap.get(key).equals(this.magenta)){
                    this.loadList.get(key).setColor(Color.MAGENTA);
                    this.loadList.get(key).fill();
                }
            }

        } catch(Exception error) {
            System.out.println(error.getMessage());
        }

        Keyboard keyboard = new Keyboard(this);

        //movement, coloring, clear, load and save
        KeyboardEvent up = new KeyboardEvent();
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        up.setKey(KeyboardEvent.KEY_W);

        KeyboardEvent upReleased = new KeyboardEvent();
        upReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        upReleased.setKey(KeyboardEvent.KEY_W);

        KeyboardEvent down = new KeyboardEvent();
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        down.setKey(KeyboardEvent.KEY_S);

        KeyboardEvent downReleased = new KeyboardEvent();
        downReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        downReleased.setKey(KeyboardEvent.KEY_S);

        KeyboardEvent left = new KeyboardEvent();
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        left.setKey(KeyboardEvent.KEY_A);

        KeyboardEvent leftReleased = new KeyboardEvent();
        leftReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        leftReleased.setKey(KeyboardEvent.KEY_A);

        KeyboardEvent right = new KeyboardEvent();
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKey(KeyboardEvent.KEY_D);

        KeyboardEvent rightReleased = new KeyboardEvent();
        rightReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        rightReleased.setKey(KeyboardEvent.KEY_D);

        KeyboardEvent space = new KeyboardEvent();
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        space.setKey(KeyboardEvent.KEY_SPACE);

        KeyboardEvent spaceReleased = new KeyboardEvent();
        spaceReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        spaceReleased.setKey(KeyboardEvent.KEY_SPACE);

        KeyboardEvent clear = new KeyboardEvent();
        clear.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        clear.setKey(KeyboardEvent.KEY_C);

        KeyboardEvent save = new KeyboardEvent();
        save.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        save.setKey(KeyboardEvent.KEY_K);

        KeyboardEvent load = new KeyboardEvent();
        load.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        load.setKey(KeyboardEvent.KEY_L);

        //number colors setters
        KeyboardEvent setColor = new KeyboardEvent();
        setColor.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        setColor.setKey(KeyboardEvent.KEY_R);


        keyboard.addEventListener(up);
        keyboard.addEventListener(down);
        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(space);
        keyboard.addEventListener(upReleased);
        keyboard.addEventListener(downReleased);
        keyboard.addEventListener(leftReleased);
        keyboard.addEventListener(rightReleased);
        keyboard.addEventListener(spaceReleased);

        keyboard.addEventListener(clear);
        keyboard.addEventListener(save);
        keyboard.addEventListener(load);

        keyboard.addEventListener(setColor);

    }

    private void up(){
        if(this.player.getY() != this.player1.getPADDING()){
            this.player.translate(0, -this.player1.getPlayerSize());
            this.playerLocation += -this.player1.getRows();
        }
    }

    private void down(){
        if(this.player.getY() != this.height - this.player1.getPADDING()){
            this.player.translate(0, this.player1.getPlayerSize());
            this.playerLocation += +this.player1.getRows();
        }
    }

    private void right(){
        if(this.player.getX() != this.width - this.player1.getPADDING()){
            this.player.translate(this.player1.getPlayerSize(), 0);
            this.playerLocation++;
        }
    }

    private void left(){
        if(this.player.getX() != this.player1.getPADDING()){
            this.player.translate(-this.player1.getPlayerSize(), 0);
            this.playerLocation--;
        }
    }

    private void paint(){
        switch (colorNumber){
            case 1:
                if(this.list.get(this.playerLocation).getColor() != Color.BLACK){
                    this.list.get(this.playerLocation).setColor(Color.BLACK);
                    this.list.get(this.playerLocation).fill();
                    this.loadMap.replace(this.playerLocation, this.black);
                }else{
                    this.list.get(this.playerLocation).setColor(Color.DARK_GRAY);
                    this.list.get(this.playerLocation).draw();
                    this.loadMap.replace(this.playerLocation, this.darkGray);
                }
                break;
            case 2:
                if(this.list.get(this.playerLocation).getColor() != Color.BLUE){
                    this.list.get(this.playerLocation).setColor(Color.BLUE);
                    this.list.get(this.playerLocation).fill();
                    this.loadMap.replace(this.playerLocation, this.blue);
                }else{
                    this.list.get(this.playerLocation).setColor(Color.DARK_GRAY);
                    this.list.get(this.playerLocation).draw();
                    this.loadMap.replace(this.playerLocation, this.darkGray);
                }
                break;
            case 3:
                if(this.list.get(this.playerLocation).getColor() != Color.RED){
                    this.list.get(this.playerLocation).setColor(Color.RED);
                    this.list.get(this.playerLocation).fill();
                    this.loadMap.replace(this.playerLocation, this.red);
                }else{
                    this.list.get(this.playerLocation).setColor(Color.DARK_GRAY);
                    this.list.get(this.playerLocation).draw();
                    this.loadMap.replace(this.playerLocation, this.darkGray);
                }
                break;
            case 4:
                if(this.list.get(this.playerLocation).getColor() != Color.CYAN){
                    this.list.get(this.playerLocation).setColor(Color.CYAN);
                    this.list.get(this.playerLocation).fill();
                    this.loadMap.replace(this.playerLocation, this.cyan);
                }else{
                    this.list.get(this.playerLocation).setColor(Color.DARK_GRAY);
                    this.list.get(this.playerLocation).draw();
                    this.loadMap.replace(this.playerLocation, this.darkGray);
                }
                break;
            case 5:
                if(this.list.get(this.playerLocation).getColor() != Color.ORANGE){
                    this.list.get(this.playerLocation).setColor(Color.ORANGE);
                    this.list.get(this.playerLocation).fill();
                    this.loadMap.replace(this.playerLocation, this.orange);
                }else{
                    this.list.get(this.playerLocation).setColor(Color.DARK_GRAY);
                    this.list.get(this.playerLocation).draw();
                    this.loadMap.replace(this.playerLocation, this.darkGray);
                }
                break;
            case 6:
                if(this.list.get(this.playerLocation).getColor() != Color.PINK){
                    this.list.get(this.playerLocation).setColor(Color.PINK);
                    this.list.get(this.playerLocation).fill();
                    this.loadMap.replace(this.playerLocation, this.pink);
                }else{
                    this.list.get(this.playerLocation).setColor(Color.DARK_GRAY);
                    this.list.get(this.playerLocation).draw();
                    this.loadMap.replace(this.playerLocation, this.darkGray);
                }
                break;
            case 7:
                if(this.list.get(this.playerLocation).getColor() != Color.YELLOW){
                    this.list.get(this.playerLocation).setColor(Color.YELLOW);
                    this.list.get(this.playerLocation).fill();
                    this.loadMap.replace(this.playerLocation, this.yellow);
                }else{
                    this.list.get(this.playerLocation).setColor(Color.DARK_GRAY);
                    this.list.get(this.playerLocation).draw();
                    this.loadMap.replace(this.playerLocation, this.darkGray);
                }
                break;
            case 8:
                if(this.list.get(this.playerLocation).getColor() != Color.GRAY){
                    this.list.get(this.playerLocation).setColor(Color.GRAY);
                    this.list.get(this.playerLocation).fill();
                    this.loadMap.replace(this.playerLocation, this.gray);
                }else{
                    this.list.get(this.playerLocation).setColor(Color.DARK_GRAY);
                    this.list.get(this.playerLocation).draw();
                    this.loadMap.replace(this.playerLocation, this.darkGray);
                }
                break;
            case 9:
                if(this.list.get(this.playerLocation).getColor() != Color.MAGENTA){
                    this.list.get(this.playerLocation).setColor(Color.MAGENTA);
                    this.list.get(this.playerLocation).fill();
                    this.loadMap.replace(this.playerLocation, this.magenta);
                }else{
                    this.list.get(this.playerLocation).setColor(Color.DARK_GRAY);
                    this.list.get(this.playerLocation).draw();
                    this.loadMap.replace(this.playerLocation, this.darkGray);
                }
                break;
            default:
                if(this.list.get(this.playerLocation).getColor() != Color.BLACK){
                    this.list.get(this.playerLocation).setColor(Color.BLACK);
                    this.list.get(this.playerLocation).fill();
                    this.colorNumber = 1;
                    this.loadMap.replace(this.playerLocation, this.black);
                }else{
                    this.list.get(this.playerLocation).setColor(Color.DARK_GRAY);
                    this.list.get(this.playerLocation).draw();
                    this.loadMap.replace(this.playerLocation, this.darkGray);
                }

                break;
        }
    }

    @Override
    public void keyPressed(KeyboardEvent e) {

        pressed.add(e.getKey());

        if (pressed.contains(KeyboardEvent.KEY_W)){
            up();
            if (pressed.contains(KeyboardEvent.KEY_SPACE)) {
                this.painting = true;
                paint();
            }
        }

        if (pressed.contains(KeyboardEvent.KEY_S)){
            down();
            if (pressed.contains(KeyboardEvent.KEY_SPACE)) {
                this.painting = true;
                paint();
            }
        }

        if (pressed.contains(KeyboardEvent.KEY_D)){
            right();
            if (pressed.contains(KeyboardEvent.KEY_SPACE)) {
                this.painting = true;
                paint();
            }
        }

        if (pressed.contains(KeyboardEvent.KEY_A)){
            left();
            if (pressed.contains(KeyboardEvent.KEY_SPACE)) {
                this.painting = true;
                paint();
            }
        }

        if(e.getKey() == KeyboardEvent.KEY_SPACE && !this.painting){
            paint();
        }

        if(e.getKey() == KeyboardEvent.KEY_R){

            switch (this.colorNumberCounter){
                case 0:
                    this.colorRectangle.setColor(Color.BLACK);
                    this.colorNumber = 1;
                    this.colorNumberCounter++;
                    break;
                case 1:
                    this.colorRectangle.setColor(Color.BLUE);
                    this.colorNumber = 2;
                    this.colorNumberCounter++;
                    break;
                case 2:
                    this.colorRectangle.setColor(Color.RED);
                    this.colorNumber = 3;
                    this.colorNumberCounter++;
                    break;
                case 3:
                    this.colorRectangle.setColor(Color.CYAN);
                    this.colorNumber = 4;
                    this.colorNumberCounter++;
                    break;
                case 4:
                    this.colorRectangle.setColor(Color.ORANGE);
                    this.colorNumber = 5;
                    this.colorNumberCounter++;
                    break;
                case 5:
                    this.colorRectangle.setColor(Color.PINK);
                    this.colorNumber = 6;
                    this.colorNumberCounter++;
                    break;
                case 6:
                    this.colorRectangle.setColor(Color.YELLOW);
                    this.colorNumber = 7;
                    this.colorNumberCounter++;
                    break;
                case 7:
                    this.colorRectangle.setColor(Color.GRAY);
                    this.colorNumber = 8;
                    this.colorNumberCounter++;
                    break;
                case 8:
                    this.colorRectangle.setColor(Color.MAGENTA);
                    this.colorNumber = 9;
                    this.colorNumberCounter = 0;
                    break;
            }
        }

        if(e.getKey() == KeyboardEvent.KEY_C){
            //System.out.println("Clear");

            for(Integer key : this.list.keySet()){
                this.list.get(key).setColor(Color.DARK_GRAY);
                this.list.get(key).draw();
            }

            this.loadMap.replaceAll((k, v) -> this.darkGray);
        }

        if(e.getKey() == KeyboardEvent.KEY_K){
            File mapToRead = new File("resources/Map");

            try {
                File map = new File("resources/Map");
                FileOutputStream fileOutputStream = new FileOutputStream(map);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                objectOutputStream.writeObject(this.loadMap);
                objectOutputStream.flush();
                objectOutputStream.close();
                fileOutputStream.close();

                //System.out.println("Saved");
            } catch(Exception error) {
                System.out.println(error.getMessage());
            }

            try {
                FileInputStream fileInputStream = new FileInputStream(mapToRead);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                HashMap<Integer, String> mapInFile = (HashMap<Integer, String>)objectInputStream.readObject();

                objectInputStream.close();
                fileInputStream.close();

                for(Integer key : this.loadList.keySet()){
                    this.loadList.get(key).setColor(Color.DARK_GRAY);
                    this.loadList.get(key).draw();
                }

                this.loadMap.putAll(mapInFile);

                for(Integer key : this.loadMap.keySet()){
                    if (this.loadMap.get(key).equals(this.darkGray)) {
                        this.loadList.get(key).setColor(Color.DARK_GRAY);
                        this.loadList.get(key).draw();
                    }else if(this.loadMap.get(key).equals(this.black)){
                        this.loadList.get(key).setColor(Color.BLACK);
                        this.loadList.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.blue)){
                        this.loadList.get(key).setColor(Color.BLUE);
                        this.loadList.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.red)){
                        this.loadList.get(key).setColor(Color.RED);
                        this.loadList.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.cyan)){
                        this.loadList.get(key).setColor(Color.CYAN);
                        this.loadList.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.orange)){
                        this.loadList.get(key).setColor(Color.ORANGE);
                        this.loadList.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.pink)){
                        this.loadList.get(key).setColor(Color.PINK);
                        this.loadList.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.yellow)){
                        this.loadList.get(key).setColor(Color.YELLOW);
                        this.loadList.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.gray)){
                        this.loadList.get(key).setColor(Color.GRAY);
                        this.loadList.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.magenta)){
                        this.loadList.get(key).setColor(Color.MAGENTA);
                        this.loadList.get(key).fill();
                    }
                }

            } catch(Exception error) {
                System.out.println(error.getMessage());
            }
        }

        if(e.getKey() == KeyboardEvent.KEY_L) {
            File mapToRead = new File("resources/Map");
            //System.out.println("Load");

            for(Integer key : this.list.keySet()){
                this.list.get(key).setColor(Color.DARK_GRAY);
                this.list.get(key).draw();
            }

            try {
                FileInputStream fileInputStream = new FileInputStream(mapToRead);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                HashMap<Integer, String> mapInFile = (HashMap<Integer, String>)objectInputStream.readObject();

                objectInputStream.close();
                fileInputStream.close();

                this.loadMap.putAll(mapInFile);

                for(Integer key : this.loadMap.keySet()){
                    if (this.loadMap.get(key).equals(this.darkGray)) {
                        this.list.get(key).setColor(Color.DARK_GRAY);
                        this.list.get(key).draw();
                    }else if(this.loadMap.get(key).equals(this.black)){
                        this.list.get(key).setColor(Color.BLACK);
                        this.list.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.blue)){
                        this.list.get(key).setColor(Color.BLUE);
                        this.list.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.red)){
                        this.list.get(key).setColor(Color.RED);
                        this.list.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.cyan)){
                        this.list.get(key).setColor(Color.CYAN);
                        this.list.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.orange)){
                        this.list.get(key).setColor(Color.ORANGE);
                        this.list.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.pink)){
                        this.list.get(key).setColor(Color.PINK);
                        this.list.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.yellow)){
                        this.list.get(key).setColor(Color.YELLOW);
                        this.list.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.gray)){
                        this.list.get(key).setColor(Color.GRAY);
                        this.list.get(key).fill();
                    }else if(this.loadMap.get(key).equals(this.magenta)){
                        this.list.get(key).setColor(Color.MAGENTA);
                        this.list.get(key).fill();
                    }
                }

            } catch(Exception error) {
                System.out.println(error.getMessage());
            }
        }

    }

    @Override
    public void keyReleased(KeyboardEvent e) {
        pressed.remove(e.getKey());

        if(e.getKey() == KeyboardEvent.KEY_SPACE){
            this.painting = false;
        }
    }

}