/**
 * Created by rnatarajan1 on 8/2/2017.
 */
public class TST<Value> {
    //Citation: source code from http://algs4.cs.princeton.edu/52trie/TST.java.html
    private int n;
    private Node<Value> root;

    private static class Node<V> {
        private char c;
        private Node<V> left;
        private Node<V> right;
        private Node<V> mid;
        private V val;
    }

    public TST() {
        root = new Node();
    }

    public int size() {
        return n;
    }

    public void insert(String s) {
        /* YOUR CODE HERE. */
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            put(s, null);
        }
    }

    public void insert(String s, Value v) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            put(s, v);
        }
    }

    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public Value get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with null argument");
        }
        if (key.length() == 0) {
            throw new IllegalArgumentException("key must have length >= 1");
        }
        Node<Value> x = getHelper(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }

    private Node<Value> getHelper(Node<Value> x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (key.length() == 0) {
            throw new IllegalArgumentException("key must have length >= 1");
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return getHelper(x.left,  key, d);
        } else if (c > x.c) {
            return getHelper(x.right, key, d);
        } else if (d < key.length() - 1) {
            return getHelper(x.mid,   key, d+1);
        } else {
            return x;
        }
    }

    public void put(String key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with null key");
        }
        if (!contains(key)) {
            n++;
        }
        root = putHelper(root, key, val, 0);
    }

    private Node<Value> putHelper(Node<Value> x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<Value>();
            x.c = c;
        }
        if (c < x.c) {
            x.left  = putHelper(x.left,  key, val, d);
        } else if (c > x.c) {
            x.right = putHelper(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid = putHelper(x.mid, key, val, d+1);
        } else {
            x.val = val;
        }
        return x;
    }

    public String longestPrefixOf(String query) {
        if (query == null) {
            throw new IllegalArgumentException("calls longestPrefixOf() with null argument");
        }
        if (query.length() == 0) {
            return null;
        }
        int length = 0;
        Node<Value> x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if (c < x.c) {
                x = x.left;
            } else if (c > x.c) {
                x = x.right;
            } else {
                i++;
                if (x.val != null) length = i;
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }

}