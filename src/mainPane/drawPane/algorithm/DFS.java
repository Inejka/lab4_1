package mainPane.drawPane.algorithm;

import mainPane.drawPane.Graph;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DFS implements Runnable {

    private final Thread thread;

    private final Drawer drawer = new Drawer();

    private final Map<Object, Integer> tin = new HashMap<>();
    private final Map<Object, Integer> fup = new HashMap<>();
    private final Map<Object, Boolean> used = new HashMap<>();
    private int timer = 0;

    private final List<Shape> bridges = new ArrayList<>();

    private final Graph<Shape, Shape> graph;

    public DFS(Graph<? extends Shape, ? extends Shape> graph) {
        thread = new Thread(this, "Demo");
        for (Object i : graph.getNodes())
            used.put(i, false);
        this.graph = (Graph<Shape, Shape>) graph;
    }

    public void start() {
        thread.start();
    }

    public void run() {
        for (Shape i : graph.getNodes())
            if (!used.get(i))
                dfs(i, null);
        drawer.makeColorBridges(bridges);
    }

    private void dfs(Shape v, Shape p) {
        drawer.makeColorNode(v);
        used.put(v, true);
        tin.put(v, timer);
        fup.put(v, timer);
        timer++;
        List<Shape> edges = graph.getIncidentEdges(v);
        drawer.makeColorEdges(edges);
        List<Shape> nodes = graph.getNodesFromEdges(edges);
        for (Shape i : nodes) {
            if (i == p) continue;
            if (used.get(i))
                fup.put(v, Math.min(fup.get(v), tin.get(i)));
            else {
                dfs(i, v);
                fup.put(v, Math.min(fup.get(v), fup.get(i)));
                if (fup.get(i) > tin.get(v))
                    bridges.add(graph.getEdge(v, i));
            }
        }
    }
}
