/**
 * Classe Solvwe que representa o algoritmo utilizado para resolver o 8 puzzle.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solver {

    /**
     * Método para procurar a solução do 8 puzzle.
     * Adiciona os Nodes a uma Queue e percorre a head da Queue até chegar ao estado final.
     *
     * @param rootState String do estado inicial do puzzle.
     * @param goalState String do estado final do puzzle.
     */
    public static void bruteForce(String rootState, String goalState) {
        Queue<Node> nodeQueue = new LinkedList<>();
        HashSet<String> visitedStateSet = new HashSet<>();

        Node root = new Node(rootState);
        nodeQueue.add(root);
        Node currentNode = nodeQueue.poll();

        while (!currentNode.getStrState().equals(goalState)) {
            visitedStateSet.add(currentNode.getStrState());
            ArrayList<String> nodeSucessores = currentNode.getSucessores();

            for (String strNode : nodeSucessores) {
                if (!visitedStateSet.contains(strNode)){
                    visitedStateSet.add(strNode);
                    Node child = new Node(strNode);
                    currentNode.addChildrenNode(child);
                    nodeQueue.add(child);
                }
            }
            currentNode = nodeQueue.poll();
        }
        Node.printSolutionBoard(currentNode, root);
    }

}
