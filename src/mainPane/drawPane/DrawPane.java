package mainPane.drawPane;

import mainPane.drawPane.algorithm.DFS;
import mainPane.drawPane.fileWorkers.Loader;
import mainPane.drawPane.fileWorkers.Saver;
import mainPane.drawPane.myShapes.*;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class DrawPane extends ScrollPane {

    public DrawPane() {
        innerPaneInit();
        hbarPolicyProperty().setValue(ScrollBarPolicy.ALWAYS);
        vbarPolicyProperty().setValue(ScrollBarPolicy.ALWAYS);
    }

    public void removeSelected() {
        if (rightClickSelected == null) return;
        if (rightClickSelected instanceof Circle) {
            List<Line> lines = graph.getIncidentEdges((Circle) rightClickSelected);
            for (Line i : lines) {
                graph.removeEdge(i);
                i.removeFrom(innerPane);
            }
            graph.removeNode((Circle) rightClickSelected);
        } else {
            graph.removeEdge((Line) rightClickSelected);
        }
        rightClickSelected.removeFrom(innerPane);
        rightClickSelected = null;
    }

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
        if (rightClickSelected != null) rightClickSelected.setStroke(Color.GREEN);
    }

    public void setIdentifier(String idntf) {
        if (rightClickSelected == null) return;
        rightClickSelected.setIdentifier(idntf);
    }

    public void save(Stage stage) {
        Saver save = new Saver(graph, stage);
    }

    public void load(Stage stage) {
        graph = new Graph<>();
        innerPaneInit();
        Loader load = new Loader(graph, stage, innerPane, select, selectEdge);
    }


    private Graph<Circle, Line> graph = new Graph<>();

    private MyShape rightClickSelected = null;

    private boolean activated = false;

    private Pane innerPane;


    final EventHandler<MouseEvent> selectEdge = e -> {
        if (e.getButton() == MouseButton.SECONDARY) {
            if (rightClickSelected != null) rightClickSelected.setStroke(Color.BLACK);
            rightClickSelected = (MyShape) (e.getSource());
            rightClickSelected.setStroke(Color.GREEN);
            activated = true;
        }
    };

    final EventHandler<MouseEvent> select = e -> {
        activated = true;
        if (e.getButton() == MouseButton.SECONDARY) {
            if (rightClickSelected != null && rightClickSelected instanceof Circle && rightClickSelected != e.getSource()) {
                createEdge(e);
            } else {
                if (rightClickSelected != null) rightClickSelected.setStroke(Color.BLACK);
                rightClickSelected = (MyShape) e.getSource();
                rightClickSelected.setStroke(Color.GREEN);
            }
        }
    };

    private void createEdge(MouseEvent e) {
        Line toAdd = new Line(((Circle) rightClickSelected).getCenterX(), ((Circle) rightClickSelected).getCenterY(), ((Circle) e.getSource()).getCenterX(), ((Circle) e.getSource()).getCenterY());
        graph.addEdge((Circle) rightClickSelected, (Circle) e.getSource(), toAdd);
        toAdd.addTo(innerPane);
        toAdd.setOnMouseClicked(selectEdge);
        rightClickSelected.setStroke(Color.BLACK);
        rightClickSelected.toFront();
        ((Circle) e.getSource()).toFront();
        rightClickSelected = null;
    }

    final EventHandler<MouseEvent> addNode = e -> {
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
            if (rightClickSelected != null) {
                rightClickSelected.setStroke(Color.BLACK);
                rightClickSelected = null;
            }
        }
    };

    private void innerPaneInit() {
        innerPane = new Pane();
        innerPane.setOnMouseClicked(addNode);
        innerPane.setPrefWidth(2000);
        innerPane.setPrefHeight(2000);
        setContent(innerPane);
    }
}
