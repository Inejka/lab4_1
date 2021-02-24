package MainPane.DrawPane.FileWorkers;

import MainPane.DrawPane.Graph;
import MainPane.DrawPane.MyShapes.Circle;
import MainPane.DrawPane.MyShapes.Line;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Loader {

    BufferedReader reader;
    Graph<Circle, Line> graph;
    Pane innerPane;
    EventHandler<MouseEvent> select;
    EventHandler<MouseEvent> selectEdge;

    public Loader(Graph<Circle, Line> graph, Stage stage, Pane innerPane, EventHandler<MouseEvent> select, EventHandler<MouseEvent> selectEdge) {
        this.graph = graph;
        this.innerPane = innerPane;
        this.select = select;
        this.selectEdge = selectEdge;
        try {
            FileChooser directoryChooser = new FileChooser();
            File selectedFile = directoryChooser.showOpenDialog(stage);
            reader = new BufferedReader(new FileReader(selectedFile));
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() throws IOException {
        int nodeCount = Integer.parseInt(reader.readLine());
        ArrayList<Circle> circles = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            loadCircles(circles);
        }
        int edgeCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < edgeCount; i++) {
            loadEdge(circles);
        }
        reader.close();
        display();
    }

    private void loadEdge(ArrayList<Circle> circles) throws IOException {
        String workWith = reader.readLine(), tmp = "";
        String[] words = workWith.split(" ");
        for (int i = 2; i < words.length; i++) tmp.concat(words[i]);
        Line toAdd = new Line(circles.get(Integer.parseInt(words[0])).getCenterX(), circles.get(Integer.parseInt(words[0])).getCenterY(), circles.get(Integer.parseInt(words[1])).getCenterX(), circles.get(Integer.parseInt(words[1])).getCenterY());
        toAdd.setIdentifier(tmp);
        graph.addEdge(circles.get(Integer.parseInt(words[0])), circles.get(Integer.parseInt(words[1])), toAdd);
    }

    private void loadCircles(ArrayList<Circle> circles) throws IOException {
        String workWith = reader.readLine(), tmp = "";
        String[] words = workWith.split(" ");
        for (int i = 2; i < words.length; i++) tmp.concat(words[i]);
        Circle toAdd = new Circle(Double.parseDouble(words[0]), Double.parseDouble(words[1]));
        toAdd.setIdentifier(tmp);
        circles.add(toAdd);
        graph.addNode(toAdd);
    }

    private void display() {
        for (Line i : graph.getEdges()) {
            i.addTo(innerPane);
            i.setOnMouseClicked(selectEdge);
        }
        for (Circle i : graph.getNodes()) {
            i.addTo(innerPane);
            i.toFront();
            i.setOnMouseClicked(select);
        }
    }
}

