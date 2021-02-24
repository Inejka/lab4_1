package MainPane.DrawPane;

import MainPane.DrawPane.Algorithm.DFS;
import MainPane.DrawPane.FileWorkers.Loader;
import MainPane.DrawPane.FileWorkers.Saver;
import MainPane.DrawPane.MyShapes.Circle;
import MainPane.DrawPane.MyShapes.GraphConnected;
import MainPane.DrawPane.MyShapes.Line;
import MainPane.DrawPane.MyShapes.Renameable;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.List;

public class DrawPane extends ScrollPane {


    private Graph<Circle, Line> graph = new Graph<Circle, Line>();


    private Shape selected = null;

    private boolean activated = false;

    private Pane innerPane;


    EventHandler<MouseEvent> selectEdge = e -> {
        if (e.getButton() == MouseButton.SECONDARY) {
            if (selected != null) selected.setStroke(Color.BLACK);
            selected = (Shape) (e.getSource());
            selected.setStroke(Color.GREEN);
            activated = true;
        }
    };

    EventHandler<MouseEvent> select = e -> {
        activated = true;
        if (e.getButton() == MouseButton.SECONDARY) {
            if (selected != null && selected instanceof Circle && selected != (Circle) e.getSource()) {
                createEdge(e);
            } else {
                if (selected != null) selected.setStroke(Color.BLACK);
                selected = (Shape) e.getSource();
                selected.setStroke(Color.GREEN);
            }
        }
    };

    private void createEdge(MouseEvent e) {
        Line toAdd = new Line(((Circle) selected).getCenterX(), ((Circle) selected).getCenterY(), ((Circle) e.getSource()).getCenterX(), ((Circle) e.getSource()).getCenterY());
        graph.addEdge((Circle) selected, (Circle) e.getSource(), toAdd);
        toAdd.addTo(innerPane);
        toAdd.setOnMouseClicked(selectEdge);
        selected.setStroke(Color.BLACK);
        selected.toFront();
        ((Circle) e.getSource()).toFront();
        selected = null;
    }

    public void removeSelected() {
        if (selected == null) return;
        if (selected instanceof Circle) {
            List<Line> lines = graph.getIncidentEdges((Circle) selected);
            for (Line i : lines) {
                graph.removeEdge(i);
                i.removeFrom(innerPane);
            }
            graph.removeNode((Circle) selected);
        } else {
            graph.removeEdge((Line) selected);
        }
        ((GraphConnected)selected).removeFrom(innerPane);
        selected = null;
    }

    EventHandler<MouseEvent> addNode = e -> {
        if (activated) {
            activated = false;
            return;
        }
        if (e.getButton() == MouseButton.PRIMARY) {
            Circle toAdd = new Circle(e.getX(), e.getY());
            toAdd.setOnMouseClicked(select);
            toAdd.addTo(innerPane);
            graph.addNode(toAdd);
        }
        if (e.getButton() == MouseButton.SECONDARY) {
            if (selected != null) {
                selected.setStroke(Color.BLACK);
                selected = null;
            }
        }
    };

    public void algorithm() {
        //setDisable(true);
        //setOpacity(100);
        DFS dfs = new DFS(graph.copy());
        dfs.start();
    }

    public void clear() {
        for (Circle i : graph.getNodes())
            i.setStroke(Color.BLACK);
        for (Line i : graph.getEdges())
            i.setStroke(Color.BLACK);
        if (selected != null) selected.setStroke(Color.GREEN);
    }

    public void setIdentifier(String idntf) {
        if (selected == null) return;
        ((Renameable) selected).setIdentifier(idntf);
    }

    public void save(Stage stage) {
        Saver save = new Saver(graph, stage);
    }

    public void load(Stage stage) {
        graph = new Graph<Circle, Line>();
        innerPaneInit();
        Loader load = new Loader(graph, stage, innerPane, select, selectEdge);
    }

    public DrawPane() {
        innerPaneInit();
        hbarPolicyProperty().setValue(ScrollBarPolicy.ALWAYS);
        vbarPolicyProperty().setValue(ScrollBarPolicy.ALWAYS);
    }

    private void innerPaneInit() {
        innerPane = new Pane();
        innerPane.setOnMouseClicked(addNode);
        innerPane.setPrefWidth(2000);
        innerPane.setPrefHeight(2000);
        setContent(innerPane);
    }
}
