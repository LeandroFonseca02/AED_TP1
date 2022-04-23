/**
 * Classe NodeComparator é um comparador de Objetos Node e implementa a interface Comparator
 */

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
    /**
     * Compara dois Nodes e devolve um inteiro que positivo se o primeiro Node tiver maior custo
     * @param objNode1 Primeiro Node para ser comparado
     * @param objNode2 Segundo Node para ser comparado
     * @return inteiro com o valor se o Primeiro Node tem ou não maior custo
     */
    public int compare(Node objNode1, Node objNode2) {
        return Integer.compare(objNode1.getIntCost(), objNode2.getIntCost());
    }
}