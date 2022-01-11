public class Main {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game(40, 40);

        Thread.sleep(1500);

        game.init();
    }
}