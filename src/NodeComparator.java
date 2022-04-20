import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node x, Node y) {
        return Integer.compare(x.getIntCost(), y.getIntCost());
    }
}