/**
 * Classe Node representa um nó/estado do puzzle
 */

import java.util.ArrayList;
import java.util.Stack;

public class Node {
    private final String strState;
    private final ArrayList<Node> arChildrenNodes;
    private Node parentNode;
    private int intCost;

    /**
     * Construtor da classe Node.
     * Recebe o estado do puzzle que o Node deve ter.
     * Inicializa uma arrayList de Nodes filhos.
     *
     * @param state String com o estado do puzzle.
     */
    public Node(String state) {
        this.strState = state;
        this.arChildrenNodes = new ArrayList<>();
    }

    /**
     * Método que retorna o custo do Node.
     *
     * @return intCost Inteiro com valor de custo de resolução.
     */
    public int getIntCost() {return intCost;}

    /**
     * Método que definir o custo do Node.
     *
     * @param intCost Inteiro com valor de custo de resolução.
     */
    public void setIntCost(int intCost) {this.intCost = intCost;}

    /**
     * Método que retorna o estado do Node.
     *
     * @return strState String com o estado do puzzle do Node.
     */
    public String getStrState() {return strState;}

    /**
     * Método que retorna o Node Pai deste Node.
     *
     * @return parentNode Node Pai deste Node.
     */
    public Node getParentNode() {return parentNode;}

    /**
     * Método que definir o Node Pai deste Node.
     *
     * @param parentNode Node Pai deste Node.
     */
    public void setParentNode(Node parentNode) {this.parentNode = parentNode;}

    /**
     * Método para adicionar um Node á arrayList de Nodes Filhos.
     * Define no Node Filho o Node Pai.
     *
     * @param childrenNode Node Filho a ser adicionado na arrayList de Node Filhos.
     */
    public void addChildrenNode(Node childrenNode) {
        this.arChildrenNodes.add(childrenNode);
        childrenNode.setParentNode(this);
    }

    /**
     * Método que retorna uma ArrayList de estados sucessores do Node.
     * Calcula todos os estados possíveis da próxima jogada.
     *
     * @return arSucessores ArrayList de Strings com os estados de uma próxima jogada.
     */
    public ArrayList<String> getSucessores() {
        String strActualState = this.getStrState();
        ArrayList<String> arSucessores = new ArrayList<>();

        switch (strActualState.indexOf("0")) {
            case 0: {
                arSucessores.add(swapMove(strActualState,0,1));
                arSucessores.add(swapMove(strActualState,0,3));
                break;
            }
            case 1: {
                arSucessores.add(swapMove(strActualState,1,0));
                arSucessores.add(swapMove(strActualState,1,2));
                arSucessores.add(swapMove(strActualState,1,4));
                break;
            }
            case 2: {
                arSucessores.add(swapMove(strActualState,2,1));
                arSucessores.add(swapMove(strActualState,2,5));
                break;
            }
            case 3: {
                arSucessores.add(swapMove(strActualState,3,0));
                arSucessores.add(swapMove(strActualState,3,4));
                arSucessores.add(swapMove(strActualState,3,6));
                break;
            }
            case 4: {
                arSucessores.add(swapMove(strActualState,4,1));
                arSucessores.add(swapMove(strActualState,4,3));
                arSucessores.add(swapMove(strActualState,4,5));
                arSucessores.add(swapMove(strActualState,4,7));
                break;
            }
            case 5: {
                arSucessores.add(swapMove(strActualState,5,2));
                arSucessores.add(swapMove(strActualState,5,4));
                arSucessores.add(swapMove(strActualState,5,8));
                break;
            }
            case 6: {
                arSucessores.add(swapMove(strActualState,6,3));
                arSucessores.add(swapMove(strActualState,6,7));
                break;

            }
            case 7: {
                arSucessores.add(swapMove(strActualState,7,4));
                arSucessores.add(swapMove(strActualState,7,6));
                arSucessores.add(swapMove(strActualState,7,8));
                break;
            }
            case 8: {
                arSucessores.add(swapMove(strActualState,8,5));
                arSucessores.add(swapMove(strActualState,8,7));
                break;
            }
        }
        return arSucessores;
    }

    /**
     * Método para trocar as peças do puzzle para obter um estado de uma jogada.
     *
     * @param strActualState String estado atual do puzzle.
     * @param intIndexMove1 int index da primeira peça a trocar.
     * @param intIndexMove2 int index da segunda peça a trocar.
     * @return String com o estado da jogada efetuada.
     */
    public String swapMove(String strActualState, int intIndexMove1, int intIndexMove2){
        char[] chrState = strActualState.toCharArray();
        char chrTemp = chrState[intIndexMove1];
        chrState[intIndexMove1] = chrState[intIndexMove2];
        chrState[intIndexMove2] = chrTemp;
        return new String(chrState);
    }

    /**
     * Método para imprimir a solução do puzzle.
     * Utiliza uma Stack para guardar os estados desde a solução até ao estado inicial.
     * Imprime cada jogada do puzzle.
     *
     * @param goalNode Node do puzzle resolvido.
     * @param rootNode Node do puzzle inicial.
     */
    public static void printSolutionBoard(Node goalNode, Node rootNode) {
        Stack<Node> solutionStack = new Stack<>();
        solutionStack.push(goalNode);
        while (!goalNode.getStrState().equals(rootNode.getStrState())) {
            solutionStack.push(goalNode.getParentNode());
            goalNode = goalNode.getParentNode();
        }
        String rootState = rootNode.getStrState();
        String goalState;
        int intPassos = 0;
        for (int i = solutionStack.size() - 1; i >= 0; i--, intPassos++) {
            if(intPassos == 0){
                System.out.println("\n\n+ Puzzle inicial!");
            }else{
                System.out.println("\n\n+ Passo " + intPassos);
            }

            goalState = solutionStack.get(i).getStrState();
            if (!rootState.equals(goalState)) {
                System.out.println("Mover o " + goalState.charAt(rootState.indexOf('0')) + " para " + findMovement(rootState, goalState));
            }

            rootState = goalState;
            printBoard(solutionStack.get(i));
        }
        System.out.println("\n\n+ Puzzle resolvido!");
        System.out.println("** Numero de movimentos utilizados:  " + (solutionStack.size() - 1));
    }

    /**
     * Método para imprimir um estado do puzzle.
     *
     * @param node Node o qual vai ser impresso o estado.
     */
    public static void printBoard(Node node){
        System.out.println("+---------------+");
        System.out.println("|\t" + node.getStrState().charAt(0)+"\t" + node.getStrState().charAt(1)+"\t" + node.getStrState().charAt(2) + "\t|");
        System.out.println("|\t" + node.getStrState().charAt(3)+"\t" + node.getStrState().charAt(4)+"\t" + node.getStrState().charAt(5) + "\t|");
        System.out.println("|\t" + node.getStrState().charAt(6)+"\t" + node.getStrState().charAt(7)+"\t" + node.getStrState().charAt(8) + "\t|");
        System.out.println("+---------------+");
    }

    // Calcula a diferença de index do 0 entre o estado atual e o proximo para saber em que sentido foi mexida a peça

    /**
     * Método para calcular para que posição a peça foi movida.
     * Calcula a diferença dos index da peça 0 para calcular aonde a outra peça foi movida.
     *
     * @param strInitialState String do estado inicial do puzzle.
     * @param strNextState String do estado seguinte do puzzle.
     * @return String com o movimento da peça.
     */
    public static String findMovement(String strInitialState, String strNextState) {
        int intZeroDifference = strNextState.indexOf('0') - strInitialState.indexOf('0');
        switch (intZeroDifference) {
            case 1:
                return "a esquerda";
            case -1:
                return "a direita";
            case -3:
                return "baixo";
            case 3:
                return "cima";
        }
        return null;
    }
}

