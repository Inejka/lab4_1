package mainPane.drawPane;

import javafx.util.Pair;

import java.util.*;

public class Graph<Node, Edge> {

    public List<Edge> getEdges() {
        Set<Edge> temp = new LinkedHashSet<>();
        for (InnerEdge i : edges)
            temp.add(i.info);
        return new LinkedList<>(temp);
    }

    public List<Node> getNodes() {
        return new LinkedList<>(nodes);
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


    public List<Node> getAdjacentNodes(Node toSearch) {
        List<Node> toReturn = new ArrayList<>();
        for (InnerEdge i : edges)
            if (i.from == toSearch) toReturn.add(i.to);
        return toReturn;
    }

    public Pair<Node, Node> getNodes(Edge toSearch) {
        for (InnerEdge i : edges)
            if (i.info == toSearch) {
                return new Pair<>(i.from, i.to);
            }
        return null;
    }

    public List<Edge> getIncidentEdges(Node toSearch) {
        List<Edge> toReturn = new ArrayList<>();
        for (InnerEdge i : edges)
            if (i.from == toSearch) toReturn.add(i.info);
        return toReturn;
    }

    public void removeEdge(Edge toDelete) {
        edges.removeIf(i -> i.info == toDelete);
    }

    public void removeNode(Node toDelete) {
        nodes.removeIf(i -> i == toDelete);
        edges.removeIf(i -> i.from == toDelete || i.to == toDelete);
    }

    public List<Node> getNodesFromEdges(List<Edge> edgesSearch) {
        ArrayList<Node> toReturn = new ArrayList<>();
        for (Edge i : edgesSearch)
            for (InnerEdge j : edges) {
                if (i == j.info) toReturn.add(j.to);
            }
        return toReturn;
    }

    public Edge getEdge(Node from, Node to) {
        for (InnerEdge i : edges)
            if (i.to == to && i.from == from) return i.info;
        return null;
    }

    public int getNodesSize() {
        return nodes.size();
    }

    public Graph<Node, Edge> copy() {
        Graph<Node, Edge> toReturn = new Graph<>();
        toReturn.nodes = this.getNodes();
        toReturn.edges = new LinkedList<>(this.edges);
        return toReturn;
    }

    private List<Node> nodes = new LinkedList<>();
    private List<InnerEdge> edges = new LinkedList<>();

    private class InnerEdge {
        final Node from;
        final Node to;
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
}
