package properties;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class PieChartGenerator extends Application {
    private SimpleIntegerProperty segment1 = new SimpleIntegerProperty(1);
    private SimpleIntegerProperty segment2 = new SimpleIntegerProperty(1);
    private SimpleIntegerProperty segment3 = new SimpleIntegerProperty(1);
    private SimpleIntegerProperty segment4 = new SimpleIntegerProperty(1);

    private SimpleIntegerProperty sum = new SimpleIntegerProperty();
    private SimpleDoubleProperty sumDouble = new SimpleDoubleProperty();

    private SimpleDoubleProperty ratio1 = new SimpleDoubleProperty();
    private SimpleDoubleProperty ratio2 = new SimpleDoubleProperty();
    private SimpleDoubleProperty ratio3 = new SimpleDoubleProperty();
    private SimpleDoubleProperty ratio4 = new SimpleDoubleProperty();

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(10);

        TextField tfSegment1 = new TextField("1");
        tfSegment1.setPrefWidth(50);
        tfSegment1.textProperty().addListener(new ChangeListener <String>() {
            @Override
            public void changed(ObservableValue <? extends String > observable ,String oldValue , String newValue ) {
                if (! newValue.matches ("[1 -9]\\d{0 ,3}")) {
                    tfSegment1.setText ( oldValue );
                } 
            } 
        });

        TextField tfSegment2 = new TextField("1");
        tfSegment2.setPrefWidth(50);
        tfSegment2.textProperty().addListener(new ChangeListener <String>() {
            @Override
            public void changed(ObservableValue <? extends String > observable ,String oldValue , String newValue ) {
                if (! newValue.matches ("[1 -9]\\d{0 ,3}")) {
                    tfSegment2.setText ( oldValue );
                } 
            } 
        });

        TextField tfSegment3 = new TextField("1");
        tfSegment3.setPrefWidth(50);
        tfSegment3.textProperty().addListener(new ChangeListener <String>() {
            @Override
            public void changed(ObservableValue <? extends String > observable ,String oldValue , String newValue ) {
                if (! newValue.matches ("[1 -9]\\d{0 ,3}")) {
                    tfSegment3.setText ( oldValue );
                } 
            } 
        });

        TextField tfSegment4 = new TextField("1");
        tfSegment4.setPrefWidth(50);
        tfSegment4.textProperty().addListener(new ChangeListener <String>() {
            @Override
            public void changed(ObservableValue <? extends String > observable ,String oldValue , String newValue ) {
                if (! newValue.matches ("[1 -9]\\d{0 ,3}")) {
                    tfSegment4.setText ( oldValue );
                } 
            } 
        });

        segment1.bind(Bindings.createIntegerBinding(() -> parseTextField(tfSegment1), tfSegment1.textProperty()));
        segment2.bind(Bindings.createIntegerBinding(() -> parseTextField(tfSegment2), tfSegment2.textProperty()));
        segment3.bind(Bindings.createIntegerBinding(() -> parseTextField(tfSegment3), tfSegment3.textProperty()));
        segment4.bind(Bindings.createIntegerBinding(() -> parseTextField(tfSegment4), tfSegment4.textProperty()));

        sum.bind(segment1.add(segment2).add(segment3).add(segment4));
        sumDouble.bind(sum);

        ratio1.bind(segment1.divide(sumDouble));
        ratio2.bind(segment2.divide(sumDouble));
        ratio3.bind(segment3.divide(sumDouble));
        ratio4.bind(segment4.divide(sumDouble));

        Label lblRatio1 = new Label();
        Label lblRatio2 = new Label();
        Label lblRatio3 = new Label();
        Label lblRatio4 = new Label();

        lblRatio1.textProperty().bind(Bindings.format("%.4f", ratio1));
        lblRatio2.textProperty().bind(Bindings.format("%.4f", ratio2));
        lblRatio3.textProperty().bind(Bindings.format("%.4f", ratio3));
        lblRatio4.textProperty().bind(Bindings.format("%.4f", ratio4));
    
        root.add(tfSegment1, 0, 0);
        root.add(tfSegment2, 0, 1);
        root.add(tfSegment3, 0, 2);
        root.add(tfSegment4, 0, 3);
        root.add(lblRatio1, 1, 0);
        root.add(lblRatio2, 1, 1);
        root.add(lblRatio3, 1, 2);
        root.add(lblRatio4, 1, 3);

        root.setPadding(new Insets(20));
    
        primaryStage.setTitle("Pie Chart Generator");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
    
    private int parseTextField(TextField tf) {
        try {
            return Integer.parseInt(tf.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}    