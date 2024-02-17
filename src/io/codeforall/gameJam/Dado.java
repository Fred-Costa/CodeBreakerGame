package io.codeforall.gameJam;

import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dado implements MouseHandler {
    private final ExecutorService executorService;
    private Picture dado;
    private Game game = new Game();
    int col = 322;
    int row = 470;

    public Dado(String pathToImage) {
        dado = new Picture(col, row, pathToImage);
        executorService = Executors.newFixedThreadPool(1);
    }

    public void drawDado() {
        dado.draw();
        mouseSetup();
    }

    private void mouseSetup() {
        Mouse mouse = new Mouse(this);

        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
        mouse.addEventListener(MouseEventType.MOUSE_MOVED);

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Runnable runnable;
        executorService.execute(() -> {
            if (mouseEvent.getX() >= dado.getX() &&
                    mouseEvent.getX() <= dado.getX() + dado.getWidth() &&
                    mouseEvent.getY() >= dado.getY() &&
                    mouseEvent.getY() <= dado.getY() + dado.getHeight()) {
                int numRandom = (int) Math.ceil(Math.random() * 6);
                System.out.println(numRandom);

                if (numRandom == 2) {
                    dado.load("resources/Dice2TOP.png");
                } else if (numRandom == 3) {
                    dado.load("resources/Dice3TOP.png");
                } else if (numRandom == 4)  {
                    dado.load("resources/Dice4TOP.png");
                } else if (numRandom == 5) {
                    dado.load("resources/Dice5TOP.png");
                } else if (numRandom == 6) {
                    dado.load("resources/Dice6TOP.png");
                }

                game.movePlayer(numRandom);
            }
        });
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
