import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
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

        // Создаем стрелочки для осей
        createArrow(root, 450, 250, 440, 240);
        createArrow(root, 450, 250, 440, 260);
        createArrow(root, 250, 50, 240, 65);
        createArrow(root, 250, 50, 260, 65);

        // Добавляем оси и стрелочки в корневую группу
        root.getChildren().addAll(xAxis, yAxis);

        // Добавляем текстовые метки для осей
        Text xLabel = new Text(460, 255, "X");
        Text yLabel = new Text(255, 30, "Y");
        xLabel.setFill(Color.BLACK);
        yLabel.setFill(Color.BLACK);
        xLabel.setStyle("-fx-font-size: 14px;");
        yLabel.setStyle("-fx-font-size: 14px;");

        root.getChildren().addAll(xLabel, yLabel);

        // Добавляем обозначения на оси Y
        for (int i = -20; i <= 20; i += 5) { // Изменяем диапазон и шаг
            Text label = new Text(230, 250 - (i * 10), String.valueOf(i)); // Увеличиваем масштаб для Y
            label.setFill(Color.BLACK);
            root.getChildren().add(label);
        }

        // Добавляем значение 0 на оси X
        Text zeroLabelX = new Text(255, 265, "0");
        zeroLabelX.setFill(Color.BLACK);
        root.getChildren().add(zeroLabelX);

        // Добавляем обозначения на оси X
        for (int i = -20; i <= 20; i += 5) {
            Text label = new Text(250 + (i * 10), 265, String.valueOf(i));
            label.setFill(Color.BLACK);
            root.getChildren().add(label);
        }

        // Рисуем график функции y = (x^2)/2 - 2
        drawFunction(root);

        // Добавляем текстовое описание функции
        Text functionDescription = new Text(75, 100, "y = (x^2)/2 - 2");
        functionDescription.setFill(Color.BLUE);
        functionDescription.setStyle("-fx-font-size: 16px;");
        root.getChildren().add(functionDescription);

        // Создаем сцену
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("График функции");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createArrow(Group root, double startX, double startY, double endX, double endY) {
        Line arrow = new Line(startX, startY, endX, endY);
        arrow.setStroke(Color.BLACK);
        root.getChildren().add(arrow);
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
            line.setStrokeWidth(2); // Увеличиваем ширину линии
            root.getChildren().add(line);

            // Обновляем предыдущую точку
            prevX = x;
            prevY = y;
        }

        // Добавляем точку (0, 0)
        Circle point = new Circle(250, 250, 5, Color.RED);
        root.getChildren().add(point);

        // Добавляем прозрачный прямоугольник для отслеживания курсора
        Rectangle trackingArea = new Rectangle(50, 50, 400, 400);
        trackingArea.setFill(Color.TRANSPARENT);
        root.getChildren().add(trackingArea);

        // Создаем Tooltip для отображения координат
        Tooltip tooltip = new Tooltip();
        Tooltip.install(trackingArea, tooltip);

        // Добавляем обработчик событий для отображения координат
        trackingArea.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();

            // Преобразуем пиксели в координаты графика
            double graphX = (mouseX - 250) / scaleX;
            double graphY = (250 - mouseY) / scaleY; // Получаем y по пиксельной координате

            // Форматируем координаты
            String coordinates = String.format("X: %.1f, Y: %.1f", graphX, graphY);
            tooltip.setText(coordinates);
            tooltip.show(trackingArea, event.getScreenX(), event.getScreenY() + 10);
        });

        trackingArea.setOnMouseExited(event -> {
            tooltip.hide(); // Скрываем Tooltip при выходе мыши
        });
    }

    private double calculateY(double x) {
        return (x * x) / 2 - 2; // Функция y = (x^2)/2 - 2
    }

    public static void main(String[] args) {
        launch(args);
    }
}