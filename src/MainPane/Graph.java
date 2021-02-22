package MainPane;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<Node, Edge> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private LinkedList<Node> nodes = new LinkedList<Node>();
    private LinkedList<InnerEdge> edges = new LinkedList<InnerEdge>();


    private class InnerEdge {
        Node from, to;
        Edge info = null;

        public InnerEdge(Node from, Node to) {
            this.from = from;
            this.to = to;
        }

        public InnerEdge(Node from, Node to, Edge info) {
            this.from = from;
            this.to = to;
            this.info = info;
        }
    }

    public void addNode(Node toAdd) {
        nodes.add(toAdd);
    }

    public void addEdge(Node node1, Node node2) {
        edges.add(new InnerEdge(node1, node2));
        edges.add(new InnerEdge(node2, node1));
    }

    public void addEdge(Node node1, Node node2, Edge edge) {
        edges.add(new InnerEdge(node1, node2, edge));
        edges.add(new InnerEdge(node2, node1, edge));
    }

    public boolean isNodeIn(Node toCheck) {
        for (Node i : nodes)
            if (i == toCheck) return true;
        return false;
    }

    public ArrayList<Node> getAdjacentNodes(Node toSearch) {
        ArrayList<Node> toReturn = new ArrayList<Node>();
        for (InnerEdge i : edges)
            if (i.from == toSearch) toReturn.add(i.to);
        return toReturn;
    }

    public ArrayList<Edge> getIncidentEdges(Node toSearch) {
        ArrayList<Edge> toReturn = new ArrayList<Edge>();
        for (InnerEdge i : edges)
            if (i.from == toSearch) toReturn.add(i.info);
        return toReturn;
    }

}
