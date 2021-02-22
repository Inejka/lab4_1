package MainPane;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.Serial;
import java.io.Serializable;

public class DrawPane extends ScrollPane {



    public Graph<Circle, Line> graph = new Graph<Circle, Line>();


    Circle selected = null;

    boolean activated = false;

    Pane innerPane = new Pane();

    private EventHandler<MouseEvent> select = e -> {
        if (e.getButton() == MouseButton.SECONDARY) {
            if (selected != null && selected!= (Circle)e.getSource() ) {
                Line toAdd = new Line(selected.getCenterX(), selected.getCenterY(), ((Circle) e.getSource()).getCenterX(), ((Circle) e.getSource()).getCenterY());
                graph.addEdge(selected,(Circle)e.getSource(),toAdd);
                toAdd.setIdentifier("test");
                toAdd.addTo(innerPane);
                selected.setStroke(Color.BLACK);
                selected.toFront();
                ((Circle)e.getSource()).toFront();
                selected=null;
            } else {
                selected = (Circle) e.getSource();
                selected.setStroke(Color.GREEN);
                activated = true;
            }
        }
    };

    private EventHandler<MouseEvent> addNode = e -> {
        if (e.getButton() == MouseButton.PRIMARY) {
            Circle toAdd = new Circle(e.getX() , e.getY() );
            toAdd.setOnMouseClicked(select);
            toAdd.setIdentifier("AFAFaaa1234a");
            toAdd.addTo(innerPane);
            graph.addNode(toAdd);
        }
        if (e.getButton() == MouseButton.SECONDARY) {
            if (selected != null && !activated) {
                selected.setStroke(Color.BLACK);
                selected = null;
            }
            activated = false;
        }
    };


    public DrawPane() {
        Circle t1 = new Circle(40, 40), t2 = new Circle(100, 100);
        t1.setOnMouseClicked(select);
        t1.addTo(innerPane);
        graph.addNode(t1);
        t2.setOnMouseClicked(select);
        t2.addTo(innerPane);
        graph.addNode(t2);
        innerPane.setOnMouseClicked(addNode);
        innerPane.setPrefWidth(2000);
        innerPane.setPrefHeight(2000);
        setContent(innerPane);
        innerPane.getChildren().removeAll(t2,t1);
        hbarPolicyProperty().setValue(ScrollBarPolicy.ALWAYS);
        vbarPolicyProperty().setValue(ScrollBarPolicy.ALWAYS);
    }
}
