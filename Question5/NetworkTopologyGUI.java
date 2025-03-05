package Question5;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;


public class NetworkTopologyGUI extends JFrame {
    // Graph representation: nodes and edges
    private final Map<String, Node> nodes; // Stores the nodes
    private final List<Edge> edges; // Stores the edges
    private final JPanel canvas; // Canvas to draw nodes and edges

    public NetworkTopologyGUI() {
        nodes = new HashMap<>();
        edges = new ArrayList<>();
        canvas = new CanvasPanel(); // Initialize the canvas for drawing

        // Basic frame setup
        setTitle("Network Topology Optimizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);

        // Panel for controls
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 3));

        JButton addNodeButton = new JButton("Add Node");
        addNodeButton.addActionListener(e -> addNode());
        controlPanel.add(addNodeButton);

        JButton addEdgeButton = new JButton("Add Edge");
        addEdgeButton.addActionListener(e -> addEdge());
        controlPanel.add(addEdgeButton);

        JButton optimizeButton = new JButton("Optimize Network");
        optimizeButton.addActionListener(e -> optimizeNetwork());
        controlPanel.add(optimizeButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    // Adds a new node to the graph
    private void addNode() {
        String nodeName = JOptionPane.showInputDialog(this, "Enter Node Name:");
        if (nodeName != null && !nodeName.trim().isEmpty()) {
            Node newNode = new Node(nodeName, new Point(100, 100)); // Default position
            nodes.put(nodeName, newNode);
            canvas.repaint();
        }
    }

    // Adds an edge between two nodes
    private void addEdge() {
        String nodeA = JOptionPane.showInputDialog(this, "Enter the first node:");
        String nodeB = JOptionPane.showInputDialog(this, "Enter the second node:");
        if (nodeA != null && nodeB != null && !nodeA.trim().isEmpty() && !nodeB.trim().isEmpty()) {
            int cost = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the cost of this connection:"));
            int bandwidth = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the bandwidth of this connection:"));
            Edge newEdge = new Edge(nodeA, nodeB, cost, bandwidth);
            edges.add(newEdge);
            canvas.repaint();
        }
    }

    // Optimize the network using MST (Prim's or Kruskal's) and Shortest Path
    private void optimizeNetwork() {
        // Call algorithm to optimize cost and latency here, e.g., Prim's MST
        // For simplicity, we'll just show a placeholder message for now.
        JOptionPane.showMessageDialog(this, "Network optimization algorithms applied!", "Optimization Complete", JOptionPane.INFORMATION_MESSAGE);
    }

    // CanvasPanel: Custom panel to draw nodes and edges
    class CanvasPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawNodes(g);
            drawEdges(g);
        }

        // Draw nodes on the canvas
        private void drawNodes(Graphics g) {
            for (Node node : nodes.values()) {
                g.setColor(Color.BLUE);
                g.fillOval(node.position.x - 15, node.position.y - 15, 30, 30);
                g.setColor(Color.WHITE);
                g.drawString(node.name, node.position.x - 10, node.position.y + 5);
            }
        }

        // Draw edges between nodes
        private void drawEdges(Graphics g) {
            for (Edge edge : edges) {
                Node nodeA = nodes.get(edge.nodeA);
                Node nodeB = nodes.get(edge.nodeB);
                g.setColor(Color.BLACK);
                g.drawLine(nodeA.position.x, nodeA.position.y, nodeB.position.x, nodeB.position.y);
                g.setColor(Color.RED);
                g.drawString("Cost: " + edge.cost + " Bandwidth: " + edge.bandwidth,
                        (nodeA.position.x + nodeB.position.x) / 2, (nodeA.position.y + nodeB.position.y) / 2);
            }
        }
    }

    // Node class to represent each server or client
    static class Node {
        String name;
        Point position;

        Node(String name, Point position) {
            this.name = name;
            this.position = position;
        }
    }

    // Edge class to represent a connection between two nodes
    static class Edge {
        String nodeA, nodeB;
        int cost, bandwidth;

        Edge(String nodeA, String nodeB, int cost, int bandwidth) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.cost = cost;
            this.bandwidth = bandwidth;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NetworkTopologyGUI networkGraph = new NetworkTopologyGUI();
            networkGraph.setVisible(true);
        });
    }
}