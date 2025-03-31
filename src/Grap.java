import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Grap extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Создаем корневую группу
        Group root = new Group();

        // Создаем ось X
        Line xAxis = new Line(50, 250, 450, 250);
        xAxis.setStroke(Color.BLACK);

        // Создаем ось Y
        Line yAxis = new Line(250, 50, 250, 450);
        yAxis.setStroke(Color.BLACK);

        // Создаем стрелочку для оси X
        Line xArrow1 = new Line(450, 250, 440, 240);
        Line xArrow2 = new Line(450, 250, 440, 260);
        xArrow1.setStroke(Color.BLACK);
        xArrow2.setStroke(Color.BLACK);

        // Создаем стрелочку для оси Y
        Line yArrow1 = new Line(250, 50, 240, 65);
        Line yArrow2 = new Line(250, 50, 260, 65);
        yArrow1.setStroke(Color.BLACK);
        yArrow2.setStroke(Color.BLACK);

        // Добавляем оси и стрелочки в корневую группу
        root.getChildren().addAll(xAxis, yAxis, xArrow1, xArrow2, yArrow1, yArrow2);

        // Добавляем текстовые метки для осей
        Text xLabel = new Text(460, 255, "X");
        Text yLabel = new Text(255, 30, "Y");
        xLabel.setFill(Color.BLACK);
        yLabel.setFill(Color.BLACK);

        root.getChildren().addAll(xLabel, yLabel);

        // Создаем сцену
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Graph with Axes and Labels");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}