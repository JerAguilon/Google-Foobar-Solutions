import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class BinaryBunnies {
    public static String answer(int[] input) {
        BST bst = new BST(input[0]);

        for (int i = 1; i < input.length; i++) {
            bst.add(input[i]);
        }

        return bst.getPermutations().toString();
    }
}

class BST {
    Node root;
    Map<Integer, BigInteger> factorials;
    public BST(int startValue) {
        root = new Node(startValue);
        factorials = constructFactorials();
    }

    private Map<Integer, BigInteger> constructFactorials() {
        factorials = new HashMap<>();
        factorials.put(0, BigInteger.ONE);

        for (int i = 1; i <= 1000; i++) {
            factorials.put(i, factorials.get(i - 1).multiply(new BigInteger(Integer.toString(i))));
        }
        return factorials;
    }

    public void add(int value) {
        root = add(root, value);
    }

    public Node add (Node root, int value) {
        if (root == null) {
            return new Node(value);
        }

        if (value < root.value) {
            root.left = add(root.left, value);
        }

        if (value > root.value) {
            root.right = add(root.right, value);
        }
        return root;

    }

    public BigInteger getPermutations() {
        return getPermutations (root);
    }

    public BigInteger getPermutations(Node root) {
        if (root == null) return BigInteger.ONE; //is this right???

        //git those shawtiesssss
        int shawtiesOnRight = 1 + nodeCount(root.right);
        int shawtiesOnLeft = nodeCount(root.left);

        //Reduces to a stars and bars combo problem
        return choose(shawtiesOnRight + shawtiesOnLeft - 1, shawtiesOnLeft)
                .multiply(getPermutations(root.right))
                .multiply(getPermutations(root.left));
    }

    public int nodeCount(Node root) {
        if (root == null) return 0;

        return 1 + nodeCount(root.left) + nodeCount(root.right);
    }

    public BigInteger choose(int n, int r) {
        if (!factorials.containsKey(n) || !factorials.containsKey(r)) return BigInteger.ONE;
        return factorials.get(n).divide(factorials.get(r).multiply(factorials.get(n - r)));
    }

}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
}