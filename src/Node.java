import java.util.ArrayList;
import java.util.Stack;

public class Node {
    private String strState;
    private ArrayList<Node> arChildrenNodes;
    private Node parentNode;
    private int intCost;

    public Node(String state) {
        this.strState = state;
        this.arChildrenNodes = new ArrayList<>();
    }

    public int getIntCost() {return intCost;}

    public void setIntCost(int intCost) {this.intCost = intCost;}

    public String getStrState() {return strState;}

    public Node getParentNode() {return parentNode;}

    public void setParentNode(Node parentNode) {this.parentNode = parentNode;}

    public void addChildrenNode(Node children) {this.arChildrenNodes.add(children);}

    public ArrayList<String> getSucessores() {
        String strActualState = this.getStrState();
        ArrayList<String> sucessores = new ArrayList<>();

        switch (strActualState.indexOf("0")) {
            case 0: {
                sucessores.add(swapMove(strActualState,0,1));
                sucessores.add(swapMove(strActualState,0,3));
                break;
            }
            case 1: {
                sucessores.add(swapMove(strActualState,1,0));
                sucessores.add(swapMove(strActualState,1,2));
                sucessores.add(swapMove(strActualState,1,4));
                break;
            }
            case 2: {
                sucessores.add(swapMove(strActualState,2,1));
                sucessores.add(swapMove(strActualState,2,5));
                break;
            }
            case 3: {
                sucessores.add(swapMove(strActualState,3,0));
                sucessores.add(swapMove(strActualState,3,4));
                sucessores.add(swapMove(strActualState,3,6));
                break;
            }
            case 4: {
                sucessores.add(swapMove(strActualState,4,1));
                sucessores.add(swapMove(strActualState,4,3));
                sucessores.add(swapMove(strActualState,4,5));
                sucessores.add(swapMove(strActualState,4,7));
                break;
            }
            case 5: {
                sucessores.add(swapMove(strActualState,5,2));
                sucessores.add(swapMove(strActualState,5,4));
                sucessores.add(swapMove(strActualState,5,8));
                break;
            }
            case 6: {
                sucessores.add(swapMove(strActualState,6,3));
                sucessores.add(swapMove(strActualState,6,7));
                break;

            }
            case 7: {
                sucessores.add(swapMove(strActualState,7,4));
                sucessores.add(swapMove(strActualState,7,6));
                sucessores.add(swapMove(strActualState,7,8));
                break;
            }
            case 8: {
                sucessores.add(swapMove(strActualState,8,5));
                sucessores.add(swapMove(strActualState,8,7));
                break;
            }
        }
        return sucessores;
    }

    public String swapMove(String strActualState, int intIndexMove1, int intIndexMove2){
        char[] chrState = strActualState.toCharArray();
        char chrTemp = chrState[intIndexMove1];
        chrState[intIndexMove1] = chrState[intIndexMove2];
        chrState[intIndexMove2] = chrTemp;
        return new String(chrState);
    }

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
            System.out.println("+---------------+");
            System.out.println("|\t" + solutionStack.get(i).getStrState().charAt(0)+"\t" + solutionStack.get(i).getStrState().charAt(1)+"\t" + solutionStack.get(i).getStrState().charAt(2) + "\t|");
            System.out.println("|\t" + solutionStack.get(i).getStrState().charAt(3)+"\t" + solutionStack.get(i).getStrState().charAt(4)+"\t" + solutionStack.get(i).getStrState().charAt(5) + "\t|");
            System.out.println("|\t" + solutionStack.get(i).getStrState().charAt(6)+"\t" + solutionStack.get(i).getStrState().charAt(7)+"\t" + solutionStack.get(i).getStrState().charAt(8) + "\t|");
            System.out.println("+---------------+");

        }
        System.out.println("\n\n+ Puzzle resolvido!");
        System.out.println("** Numero de movimentos utilizados:  " + (solutionStack.size() - 1));
    }

    // Calcula a diferença de index do 0 entre o estado atual e o proximo para saber em que sentido foi mexida a peça
    public static String findMovement(String strActualState, String strNextState) {
        int intZeroDifference = strNextState.indexOf('0') - strActualState.indexOf('0');
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

