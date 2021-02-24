package MainPane.DrawPane.FileWorkers;

import MainPane.DrawPane.Graph;
import MainPane.DrawPane.MyShapes.Circle;
import MainPane.DrawPane.MyShapes.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Saver {

    PrintWriter writer;
    Graph<Circle, Line> graph;

    public Saver(Graph<Circle, Line> graph, Stage stage) {
        this.graph = graph;
        try {
            FileChooser directoryChooser = new FileChooser();
            File selectedFile = directoryChooser.showSaveDialog(stage);
            if (!selectedFile.exists())
                selectedFile.createNewFile();
            writer = new PrintWriter(selectedFile);
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        Map<Circle, Integer> indexes = new HashMap<>();
        writer.println(graph.getNodes().size());
        int counter = 0;
        for (Circle i : graph.getNodes()) {
            indexes.put(i, counter);
            counter++;
            writer.println(((Double) i.getCenterX()).toString() + " " + ((Double) i.getCenterY()).toString() + " " + i.getIdentifier());
        }
        writer.println(graph.getEdges().size());
        for (Line i : graph.getEdges()) {
            Pair<Circle, Circle> pair = graph.getNodes(i);
            writer.println(indexes.get(pair.getKey()).toString() + " " + indexes.get(pair.getValue()).toString() + " " + i.getIdentifier());
        }
        writer.close();
    }
}
