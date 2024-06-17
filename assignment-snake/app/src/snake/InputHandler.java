package snake;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Handles controls of a snake game, where the 'a' and 'd' keys can be used to move and 's' (un)pauses the game
 */
public class InputHandler {

    private final EventHandler<KeyEvent> keyHandler;
    private final EventHandler<MouseEvent> mouseHandler;

    public InputHandler(World world) {
        Snake snake = world.getSnake();

        keyHandler = keyEvent -> {
            switch (keyEvent.getCode()) {
                case A:
                    snake.setDirection(snake.getDirection().rotateLeft());
                    break;
                case D:
                    snake.setDirection(snake.getDirection().rotateRight());
                    break;
                case S:
                    if (world.isRunning()) {
                        world.setRunning(false);
                    } else {
                        world.setRunning(true);
                    }
                    break;
                default:
                    break;
            }
            keyEvent.consume();
        };

        mouseHandler = mouseEvent -> {
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED) {
                double mouseX = mouseEvent.getX();
                double mouseY = mouseEvent.getY();
    
                // Check if the mouse click is within the bounds of the field
                if (mouseX >= 0 && mouseX < world.getSize() && mouseY >= 0 && mouseY < world.getSize()) {
                    // Teleport the food to the location of the mouse click
                    world.getFood().moveTo((int) mouseX, (int) mouseY);
                }
            }
            mouseEvent.consume();
        };
    }

    public EventHandler<KeyEvent> getKeyHandler() {
        return keyHandler;
    }

    public EventHandler<MouseEvent> getMouseHandler() {
        return mouseHandler;
    }
}
