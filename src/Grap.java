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

        // Рисуем график функции y = (x^2)/2 - 2
        drawFunction(root);

        // Создаем сцену
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Graph with Axes and Function");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawFunction(Group root) {
        // Начальные и конечные значения x
        double xStart = -20; // Начало диапазона
        double xEnd = 20;    // Конец диапазона
        double scaleX = 10;  // Масштаб по оси X
        double scaleY = 10;  // Масштаб по оси Y

        // Переменная для хранения предыдущей точки
        double prevX = xStart;
        double prevY = calculateY(prevX);

        // Рисуем график
        for (double x = xStart; x <= xEnd; x += 0.1) {
            double y = calculateY(x);
            // Преобразуем координаты в пиксели
            double pixelX1 = 250 + (prevX * scaleX);
            double pixelY1 = 250 - (prevY * scaleY);
            double pixelX2 = 250 + (x * scaleX);
            double pixelY2 = 250 - (y * scaleY);

            // Рисуем линию между предыдущей и текущей точкой
            Line line = new Line(pixelX1, pixelY1, pixelX2, pixelY2);
            line.setStroke(Color.BLUE);
            root.getChildren().add(line);

            // Обновляем предыдущую точку
            prevX = x;
            prevY = y;
        }
    }

    private double calculateY(double x) {
        return (x * x) / 2 - 2; // Функция y = (x^2)/2 - 2
    }

    public static void main(String[] args) {
        launch(args);
    }
}