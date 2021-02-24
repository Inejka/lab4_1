package MainPane.DrawPane.MyShapes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Line extends javafx.scene.shape.Line implements Renameable,GraphConnected {

    Text identifier = new Text();

    public Line(double X1, double Y1, double X2, double Y2) {
        setStrokeWidth(5);
        identifier.setFill(Color.BLACK);
        identifier.setFont(Font.font("Arial", 12));
        setSmooth(true);
        setStartX(X1);
        setStartY(Y1);
        setEndX(X2);
        setEndY(Y2);
    }

    public void setIdentifier(String idntf) {
        identifier.setText(idntf);
        identifier.setX((getStartX() + getEndX() - identifier.getLayoutBounds().getWidth()) / 2);
        identifier.setY((getStartY() + getEndY() - identifier.getLayoutBounds().getHeight()) / 2);
        double from_y, from_x, to_y, to_x;
        if (getStartY() < getEndY()) {
            from_y = getStartY();
            from_x = getStartX();
            to_x = getEndX();
            to_y = getEndY();
        } else {
            to_y = getStartY();
            to_x = getStartX();
            from_x = getEndX();
            from_y = getEndY();
        }
        double angle = Math.atan((to_y - from_y) / (to_x - from_x)) * 180 / 3.1415926;
        if (Math.abs(90 - angle) < 20) {
            identifier.setX(identifier.getX() + 10);
            identifier.setY(identifier.getY() + 10);
        }
        //double agnle = Math.acos(((1)*to_x)/Math.sqrt(1)/Math.sqrt((to_x*to_x+to_y*to_y)));
        //double agnle = Math.acos(((from_x+1)*to_x+from_y*to_y)/Math.sqrt((from_x+1)*(from_x+1)+(from_y*from_y))/Math.sqrt((to_x*to_x+to_y*to_y)));
        identifier.setRotate(angle);
        //double x_distance = getEndX() - getStartX(), y_distance = getEndY() - getStartY();
        //double x_x = 1, y_y = Math.sqrt(x_distance * x_distance + y_distance * y_distance);
        //double angle = Math.acos((x_distance * x_x + y_distance * y_y) / Math.sqrt(x_distance * x_distance + y_distance * y_distance) / Math.sqrt(x_x * x_x + y_y * y_y));
        //if (getStartX() < getEndX()) identifier.setRotate(angle * 180 / 3.1415926);
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
