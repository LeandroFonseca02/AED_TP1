import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch {
    public static void bestFirstSearch(String rootState, String goalState) {
        NodeComparator nodeComparator = new NodeComparator();
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<>(nodeComparator);
        HashSet<String> visitedStateSets = new HashSet<>();

        Node root = new Node(rootState);
        Node currentNode = root;

        while (!currentNode.getStrState().equals(goalState)) {
            visitedStateSets.add(currentNode.getStrState());
            ArrayList<String> nodeSucessores = currentNode.getSucessores();
            for (String strNode : nodeSucessores) {
                if (!visitedStateSets.contains(strNode)){
                    visitedStateSets.add(strNode);
                    Node child = new Node(strNode);
                    currentNode.addChildrenNode(child);
                    child.setParentNode(currentNode);
                    child.setIntCost(calculateCost(child.getStrState(), goalState));
                    nodePriorityQueue.add(child);
                }
            }
            currentNode = nodePriorityQueue.poll();
        }
        Node.printSolutionBoard(currentNode, root);
    }

    private static int calculateCost(String currentState, String goalSate) {
        int intDifference = 0;
        for (int i = 0; i < currentState.length(); i++) {
            if (currentState.charAt(i) != goalSate.charAt(i)){
                intDifference++;
            }
        }
        return intDifference;
    }

}
