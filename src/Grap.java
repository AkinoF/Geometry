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
        Line xAxis = new Line(50, 250, 470, 250);
        xAxis.setStroke(Color.BLACK);

        // Создаем ось Y
        Line yAxis = new Line(250, 30, 250, 470);
        yAxis.setStroke(Color.BLACK);

        // Создаем стрелочки для осей
        createArrow(root, 470, 250, 460, 240);
        createArrow(root, 470, 250, 460, 260);
        createArrow(root, 250, 30, 240, 45);
        createArrow(root, 250, 30, 260, 45);

        // Добавляем оси и стрелочки в корневую группу
        root.getChildren().addAll(xAxis, yAxis);

        // Добавляем текстовые метки для осей
        Text xLabel = new Text(460, 235, "X");
        Text yLabel = new Text(255, 20, "Y");
        xLabel.setFill(Color.BLACK);
        yLabel.setFill(Color.BLACK);
        xLabel.setStyle("-fx-font-size: 14px;");
        yLabel.setStyle("-fx-font-size: 14px;");

        root.getChildren().addAll(xLabel, yLabel);

        // Добавляем обозначения на оси Y (пропускаем ноль)
        for (int i = -20; i <= 20; i += 5) {
            if (i != 0) { // Пропускаем ноль
                Text label = new Text(230, 250 - (i * 10), String.valueOf(i));
                label.setFill(Color.BLACK);
                root.getChildren().add(label);
            }
        }

        // Добавляем значение 0 на оси X
        Text zeroLabelX = new Text(255, 265, "0");
        zeroLabelX.setFill(Color.BLACK);
        root.getChildren().add(zeroLabelX);

        // Добавляем обозначения на оси X (пропускаем ноль)
        for (int i = -20; i <= 20; i += 5) {
            if (i != 0) { // Пропускаем ноль
                Text label = new Text(250 + (i * 10), 265, String.valueOf(i));
                label.setFill(Color.BLACK);
                root.getChildren().add(label);
            }
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
        primaryStage.setTitle("ДА ЧТО ТАКОЕ ВАША ГЕОМЕТРИЯ");
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
        double scaleX = 10;   // Масштаб по оси X
        double scaleY = 10;   // Масштаб по оси Y

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
            Line line = new Line(pixelX1,pixelY1,pixelX2,pixelY2);
            line.setStroke(Color.BLUE);
            line.setStrokeWidth(2);
            root.getChildren().add(line);

            prevX=x;
            prevY=y;
        }

        Circle point=new Circle(250 ,250 ,5 ,Color.RED );
        root.getChildren().add(point );

        Rectangle trackingArea=new Rectangle(50 ,50 ,400 ,400 );
        trackingArea.setFill(Color.TRANSPARENT );
        root.getChildren().add(trackingArea );

        Tooltip tooltip=new Tooltip();
        Tooltip.install(trackingArea ,tooltip );

        trackingArea.setOnMouseMoved(event -> {
            double mouseX=event.getX();
            double mouseY=event.getY();

            double graphX=(mouseX-250)/scaleX;
            double graphY=(250-mouseY)/scaleY;

            String coordinates=String.format("X: %.1f,Y: %.1f",graphX ,graphY );
            tooltip.setText(coordinates );
            tooltip.show(trackingArea,event.getScreenX(),event.getScreenY()+10 );
        });

        trackingArea.setOnMouseExited(event -> tooltip.hide());
    }

    private double calculateY(double x) {
        return (x*x)/2-2;
    }

    public static void main(String[] args) {
        launch(args );
    }
}