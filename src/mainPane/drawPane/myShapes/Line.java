package mainPane.drawPane.myShapes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Line extends MyShape {

    final Text identifier = new Text();

    final MoveTo startPoint;
    final LineTo endPoint;

    public Line(double X1, double Y1, double X2, double Y2) {
        setStrokeWidth(5);
        identifier.setFill(Color.BLACK);
        identifier.setFont(Font.font("Arial", 12));
        setSmooth(true);
        startPoint = new MoveTo(X1, Y1);
        endPoint = new LineTo(X2, Y2);
        getElements().addAll(startPoint, endPoint);
    }

    public void setIdentifier(String idntf) {
        identifier.setText(idntf);
        identifier.setX((startPoint.getX() + endPoint.getX() - identifier.getLayoutBounds().getWidth()) / 2);
        identifier.setY((startPoint.getY() + endPoint.getY() - identifier.getLayoutBounds().getHeight()) / 2);
        double from_y, from_x, to_y, to_x;
        if (startPoint.getY() < endPoint.getY()) {
            from_y = startPoint.getY();
            from_x = startPoint.getX();
            to_x = endPoint.getX();
            to_y = endPoint.getY();
        } else {
            to_y = startPoint.getY();
            to_x = startPoint.getX();
            from_x = endPoint.getX();
            from_y = endPoint.getY();
        }
        double angle = Math.atan((to_y - from_y) / (to_x - from_x)) * 180 / 3.1415926;
        if (Math.abs(90 - angle) < 20) {
            identifier.setX(identifier.getX() + 10);
            identifier.setY(identifier.getY() + 10);
        }
        //double agnle = Math.acos(((1)*to_x)/Math.sqrt(1)/Math.sqrt((to_x*to_x+to_y*to_y)));
        //double agnle = Math.acos(((from_x+1)*to_x+from_y*to_y)/Math.sqrt((from_x+1)*(from_x+1)+(from_y*from_y))/Math.sqrt((to_x*to_x+to_y*to_y)));
        identifier.setRotate(angle);
        //double x_distance = endPoint.getX() - startPoint.getX(), y_distance = endPoint.getY() - startPoint.getY();
        //double x_x = 1, y_y = Math.sqrt(x_distance * x_distance + y_distance * y_distance);
        //double angle = Math.acos((x_distance * x_x + y_distance * y_y) / Math.sqrt(x_distance * x_distance + y_distance * y_distance) / Math.sqrt(x_x * x_x + y_y * y_y));
        //if (startPoint.getX() < endPoint.getX()) identifier.setRotate(angle * 180 / 3.1415926);
        //else
        //    identifier.setRotate(-1 * angle * 180 / 3.1415926);
    }

    public void removeFrom(Pane removeFrom) {
        removeFrom.getChildren().removeAll(this, identifier);
    }

    public String getIdentifier() {
        return identifier.getText();
    }

    public void addTo(Pane addTo) {
        addTo.getChildren().addAll(this, identifier);
    }
}
