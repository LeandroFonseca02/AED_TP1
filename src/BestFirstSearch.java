/**
 * Classe BestFirstSearch que representa o algoritmo utilizado para resolver o 8 puzzle.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch {
    /**
     * Método para procurar a solução do 8 puzzle.
     * Algoritmo baseado no artigo abaixo.
     * @see <a href="https://www.geeksforgeeks.org/best-first-search-informed-search/">https://www.geeksforgeeks.org/best-first-search-informed-search/</a>
     *
     * @param rootState String do estado inicial do puzzle.
     * @param goalState String do estado final do puzzle.
     */
    public static void search(String rootState, String goalState) {
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
                    child.setIntCost(calculateCost(child.getStrState(), goalState));
                    nodePriorityQueue.add(child);
                }
            }
            currentNode = nodePriorityQueue.poll();
        }
        Node.printSolutionBoard(currentNode, root);
    }

    /**
     * Método para calcular o custo de movimentos entre o estado atual com o objetivo.
     * Calcula o número de peças em posições diferentes do objetivo.
     *
     * @param currentState String do estado atual do puzzle.
     * @param goalSate String do estado final do puzzle.
     * @return intDifference int com o valor de custo.
     */
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
