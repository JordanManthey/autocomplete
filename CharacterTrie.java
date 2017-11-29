import java.util.HashMap;

public class CharacterTrie {

    private Node root = new Node();

    private class Node {
        HashMap<Character, Node> children;
        boolean exists;


        Node() {
            exists = false;
            children = new HashMap<>();
        }
    }

    public void insert(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("No word inserted");
        } else {
            insert(0, root, word);
        }
    }

    private Node insert(int soFar, Node n, String word) {
        if (n == null) {
            n = new Node();
        }
        if (soFar == word.length()) {
            n.exists = true;
            return n;
        }
        char c = word.charAt(soFar);
        soFar++;
        n.children.put(c, insert(soFar, n.children.get(c), word));
        return n;
    }

    public void printAlpha(String order) {
        String starter = new String();
        printAlpha(order, starter, root);
    }

    public void printAlpha(String order, String curr, Node n) {
        if (n.exists) {
            System.out.println(curr);
        }
        int i = 0;
        while (i < order.length()) {
            char c = order.charAt(i);
            if (n.children.get(c) != null) {
                printAlpha(order, curr + c, n.children.get(c));
            }
            i++;
        }
    }
}
