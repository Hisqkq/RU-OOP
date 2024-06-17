package snake;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import snake.Snake.SnakeSegmentListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;

/**
 * A JavaFX Pane that displays the snake game represented by the given world
 */
public class SnakeGame extends Pane implements SnakeSegmentListener{

    public static final int SCALE = 16;
    //private static final double dbSCALE = (double) SCALE;

    public SnakeGame(World world) {
        setPrefSize(world.getSize() * SCALE, world.getSize() * SCALE);

        // Bind objects to game logic
        IntegerProperty headX = world.getSnake().getXProperty();
        IntegerProperty headY = world.getSnake().getYProperty();
        Rectangle head = new Rectangle(headX.doubleValue()*SCALE, headY.doubleValue()*SCALE, SCALE, SCALE);
        head.setFill(Color.RED);
        head.xProperty().bind(headX.multiply(SCALE));
        head.yProperty().bind(headY.multiply(SCALE));

        IntegerProperty foodX = world.getFood().getXProperty();
        IntegerProperty foodY = world.getFood().getYProperty();
        Circle foodCircle = new Circle(foodX.doubleValue()*SCALE+SCALE/2, foodY.doubleValue()*SCALE+SCALE/2, (double) SCALE/2, Color.BLUE);
        foodCircle.centerXProperty().bind(foodX.multiply(SCALE).add(SCALE/2));
        foodCircle.centerYProperty().bind(foodY.multiply(SCALE).add(SCALE/2));

        getChildren().addAll(head, foodCircle);

        world.getSnake().addListener(this);
    }

    public static Pane createUserInterface(World world) {
        VBox ui = new VBox();

        Label scoreText = new Label("Points:");
        Label runningText = new Label("Press 's' to start");
        Label debuggingText1 = new Label(world.getRunningProperty().toString());
        Label debuggingText2 = new Label(world.getFood().getXProperty().toString());

        runningText.textProperty().bind(
            Bindings.when(world.getRunningProperty())
                    .then("Press 's' to pause")
                    .otherwise("Press 's' to start")
        );
        ui.getChildren().addAll(scoreText, runningText, debuggingText1, debuggingText2);

        return ui;
    }

    @Override
    public void onNewSegment(Segment segment){
        IntegerProperty segmentX = segment.getXProperty();
        IntegerProperty segmentY = segment.getYProperty();
        Rectangle segmentRectangle = new Rectangle(segmentX.doubleValue()*SCALE, segmentY.doubleValue()*SCALE, SCALE, SCALE);
        segmentRectangle.setFill(Color.GREEN);
        // segmentRectangle.xProperty().bind(segmentX.multiply(SCALE));
        // segmentRectangle.yProperty().bind(segmentY.multiply(SCALE));
        segmentRectangle.xProperty().bind(segment.getXProperty().multiply(SCALE));
        segmentRectangle.yProperty().bind(segment.getYProperty().multiply(SCALE));

        getChildren().add(segmentRectangle);
    }
}
