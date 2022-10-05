import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    Node A, parentNodeA, childrenNode1, childrenNode2;
    NodeTest(){
        this.A = new Node("123405678");
        this.parentNodeA = new Node("1230456789");
        this.childrenNode1 = new Node("123475608");
        this.childrenNode2 = new Node("103425678");
        this.A.setParentNode(parentNodeA);
        this.A.addChildrenNode(childrenNode1);
        this.A.addChildrenNode(childrenNode2);
    }

    @Test
    void getStrState() {
        assertEquals("123405678", A.getStrState());
    }

    @Test
    void getParentNode() {
        assertEquals(parentNodeA, A.getParentNode());
    }

    @Test
    void setParentNode() {
        Node newParent = new Node("123450678");
        A.setParentNode(newParent);
        assertEquals(newParent, A.getParentNode());
    }

    @Test
    void addChildrenNode() {
        assertEquals(A.getStrState(), childrenNode1.getParentNode().getStrState());
    }

    @Test
    void getSucessores() {
        ArrayList<String> testSucessores = new ArrayList<>();
        testSucessores.add("103425678");
        testSucessores.add("123045678");
        testSucessores.add("123450678");
        testSucessores.add("123475608");
        assertEquals(A.getSucessores(), testSucessores);
    }

    @Test
    void swapMove() { assertEquals("123450678", A.swapMove(A.getStrState(), 4,5)); }

    @Test
    void findMovement() {
        assertEquals( "a esquerda" ,A.findMovement("123405678", "123450678"));
        assertEquals( "a direita" ,A.findMovement("123405678", "123045678"));
        assertEquals( "baixo" ,A.findMovement("123405678", "103425678"));
        assertEquals( "cima" ,A.findMovement("123405678", "123475608"));
    }
}