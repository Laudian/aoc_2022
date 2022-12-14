import java.util.*;

public class Graph {
    private Vertex start;
    private Vertex exit;
    private final List<Vertex> vertices = new ArrayList<>();
    private final Map<Integer, Vertex> coordToVertex = new HashMap<>();
    
    public void addVertex(int x, int y, int height) {
        Vertex vertex = new Vertex(x, y, height);
        vertices.add(vertex);
        List<Vertex> neighbours = findNeighbours(vertex);
        coordToVertex.put(100 * x + y, vertex);
        for (Vertex neighbour : neighbours) {
            int heightDifferenceToNeighbour = neighbour.getHeight() - height;
            if (heightDifferenceToNeighbour <= 1) {
                vertex.addEdge(neighbour, 1);
            }
            
            int heightDifferenceFromNeighbour = height - neighbour.getHeight();
            if (heightDifferenceFromNeighbour <= 1) {
                neighbour.addEdge(vertex, 1);
            }
        }
    }

    private List<Vertex> findNeighbours(Vertex center) {
        List<Vertex> neighbours = new ArrayList<>();
        Vertex neighbour;
        // left
        neighbour = getVertexByCoord(center.getX() -1, center.getY());
        if (neighbour != null) {
            neighbours.add(neighbour);
        }
        // right
        neighbour = getVertexByCoord(center.getX() +1, center.getY());
        if (neighbour != null) {
            neighbours.add(neighbour);
        }
        // up
        neighbour = getVertexByCoord(center.getX(), center.getY() -1);
        if (neighbour != null) {
            neighbours.add(neighbour);
        }
        // down
        neighbour = getVertexByCoord(center.getX(), center.getY() +1);
        if (neighbour != null) {
            neighbours.add(neighbour);
        }
        return neighbours;
    }
    
    public Vertex getVertexByCoord(int x, int y) {
        return coordToVertex.get(100*x + y);
    }
    
    public void reset() {
        for (Vertex vertex : vertices) {
            vertex.reset();
        }
    }

    public Vertex getStart() {
        return start;
    }

    public void setStart(Vertex start) {
        this.start = start;
    }

    public Vertex getExit() {
        return exit;
    }

    public void setExit(Vertex exit) {
        this.exit = exit;
    }

    public int part1() {
        Queue<Vertex> queue = new ArrayDeque<>();
        start.value = 0;
        queue.add(start);
        Vertex current;
        while ((current = queue.poll()) != null) {
            for (Edge edge : current.edges) {
                Vertex neighbour = edge.to;
                if (! neighbour.visited) {
                    neighbour.visited = true;
                    neighbour.value = current.value + edge.weight;
                    queue.add(neighbour);
                }
            }
        }
        int result = exit.value;
        reset();
        return result;
    }
    
    public int part2() {
        Vertex oldstart = start;
        int minPath = Integer.MAX_VALUE;
        for (Vertex vertex : vertices) {
            if (vertex.getHeight() == 1) {
                start = vertex;
                int path = part1();
                minPath = Integer.min(minPath, path);
                reset();
            }
        }
        start = oldstart;
        return minPath;
    }

    private static class Vertex {
        private final int x;
        private final int y;
        private final int height;
        private boolean visited = false;
        private int value = Integer.MAX_VALUE;
        private final List<Edge> edges = new ArrayList<>();
        public Vertex(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
        
        public void addEdge(Vertex toVertex, int weight) {
            edges.add(new Edge(this, toVertex, weight));
        }
        
        public List<Vertex> getNeighbours() {
            List<Vertex> neighbours = new ArrayList<>();
            for (Edge edge : edges) {
                neighbours.add(edge.to);
            }
            return neighbours;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getHeight() {
            return height;
        }
        
        private void reset() {
            visited = false;
            value = Integer.MAX_VALUE;
        }
    }
    private static class Edge {
        private final Vertex from;
        private final Vertex to;
        private final int weight;

        private Edge(Vertex from, Vertex to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public Vertex getFrom() {
            return from;
        }

        public Vertex getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }
    }
}
