package mainPane.drawPane.myShapes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Circle extends MyShape {

    final Text identifier = new Text();
    final MoveTo moveTo = new MoveTo(10, 0);
    final ArcTo topArc = new ArcTo();
    final ArcTo bottomArc = new ArcTo();

    public Circle(double X, double Y) {
        topArc.setRadiusX(10);
        topArc.setRadiusY(10);
        bottomArc.setRadiusY(10);
        bottomArc.setRadiusX(10);
        topArc.setX(-10);
        bottomArc.setX(-10);
        bottomArc.setSweepFlag(true);
        getElements().addAll(moveTo, topArc, moveTo, bottomArc);
        identifier.setFill(Color.BLACK);
        identifier.setFont(Font.font("Arial", 12));
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
        setLayoutX(X);
        setLayoutY(Y);
    }

    public double getCenterX() {
        return getLayoutX();
    }

    public double getCenterY() {
        return getLayoutY();
    }

    public void setIdentifier(String idntf) {
        identifier.setText(idntf);
        identifier.setX(getLayoutX() - identifier.getLayoutBounds().getWidth() / 2);
        identifier.setY(getLayoutY() - topArc.getRadiusX() / 2 - identifier.getLayoutBounds().getHeight());
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
