package MainPane.DrawPane.MyShapes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Circle extends javafx.scene.shape.Circle implements Renameable,GraphConnected {

    Text identifier = new Text();

    public Circle(double X, double Y) {
        identifier.setFill(Color.BLACK);
        identifier.setFont(Font.font("Arial", 12));
        setCenterX(X);
        setCenterY(Y);
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
        setRadius(10);
    }

    public void setIdentifier(String idntf) {
        identifier.setText(idntf);
        ;
        identifier.setX(getCenterX() - identifier.getLayoutBounds().getWidth() / 2);
        identifier.setY(getCenterY() - getRadius() / 2 - identifier.getLayoutBounds().getHeight());
    }

    public String getIdentifier() {
        return identifier.getText();
    }

    public void removeFrom(Pane removeFrom) {
        removeFrom.getChildren().removeAll(this, identifier);
    }

    public void addTo(Pane addTo) {
        addTo.getChildren().addAll(this, identifier);
    }

}
