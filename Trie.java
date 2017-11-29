import java.util.*;

/**
 * Created by rnatarajan1 on 8/2/2017.
 */
public class Trie {
    //Citation: source code from http://algs4.cs.princeton.edu/52trie/TST.java.html
    //find and insert written by us, other methods slightly modified to fit our implementation

    private int n;
    private Node root;

    private static class Node {
        private char c;
        private Node left;
        private Node right;
        private Node mid;
        private double val;
        private boolean exists = false;
        private boolean last = false;
        private String complete;
        private double max = 0.0;
    }

    public Trie() {
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
            put(s, 0.1);
        }
    }

    public void insert(String s, double v) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            put(s, v);
        }
    }

    public boolean find(String s, boolean isFullWord) {
        if (isFullWord) {
            Node curr = getHelper(root, s, 0);
            if (curr == null) {
                return false;
            } else {
                return curr.last;
            }
        } else {
            String temp = longestPrefixOf(s);
            return s.equals(temp);
        }
    }

    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        Node curr = getHelper(root, key, 0);
        if (curr == null) {
            return false;
        } else {
            return curr.exists;
        }
    }

    public double get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with null argument");
        }
        if (key.length() == 0) {
            throw new IllegalArgumentException("key must have length >= 1");
        }
        Node x = getHelper(root, key, 0);
        if (x == null) {
            return 0.0d;
        }
        return x.val;
    }

    private Node getHelper(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (key.length() == 0) {
            return x;
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return getHelper(x.left,  key, d);
        } else if (c > x.c) {
            return getHelper(x.right, key, d);
        } else if (d < key.length() - 1) {
            return getHelper(x.mid,   key, d + 1);
        } else {
            return x;
        }
    }

    public void put(String key, double val) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with null key");
        }
        Node curr = getHelper(root, key, 0);
        if (!contains(key) || !curr.last) {
            n++;
        }
        root = putHelper(root, key, val, 0);
    }

    private Node putHelper(Node x, String key, double val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
            x.exists = true;
        }
        if (c < x.c) {
            x.left  = putHelper(x.left,  key, val, d);
        } else if (c > x.c) {
            x.right = putHelper(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            if (x.max == 0.0) {
                x.max = val;
            } else {
                if (x.max < val) {
                    x.max = val;
                }
            }
            x.mid = putHelper(x.mid, key, val, d + 1);
        } else {
            x.val = val;
            x.last = true;
            x.complete = key;
            if (x.max < val) {
                x.max = val;
            }
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
        Node x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if (c < x.c) {
                x = x.left;
            } else if (c > x.c) {
                x = x.right;
            } else {
                i++;
                if (x.val != 0.0d || x.exists) {
                    length = i;
                }
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }

    public Iterable<String> keysWithPrefix(String prefix, int k) {
        if (prefix == null) {
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
        }
        PriorityQueue<String> queue = new PriorityQueue<>((o1, o2) -> (int) (get(o1) - get(o2)));
        ArrayList<Double> weights = new ArrayList<>();
        Node x = getHelper(root, prefix, 0);
        if (x == null && x != root) {
            return queue;
        }
        if (x.val != 0.0d) {
            queue.add(prefix);
            weights.add(x.val);
        }
        if (prefix.equals("")) {
            collect(x.right, new StringBuilder(prefix), queue, weights, k);
        } else {
            collect(x.mid, new StringBuilder(prefix), queue, weights, k);
        }
        return queue;
    }

    private void collect(Node x, StringBuilder prefix,
                         PriorityQueue<String> queue, ArrayList arr, int k) {
        if (x == null) {
            return;
        }
        collect(x.left,  prefix, queue, arr, k);
        //should stop searching subtries with lower maxes than the min in arr
        if (arr.size() == k) {
            if (min(arr) > x.max) {
                collect(x.right, prefix, queue, arr, k);
                return;
            }
        }

        if (x.val != 0.0d) {
            if (arr.size() == k) {
                queue.poll();
                arr.remove(min(arr));
            }
            queue.add(prefix.toString() + x.c);
            arr.add(x.val);
        }

        collect(x.mid, prefix.append(x.c), queue, arr, k);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue, arr, k);

    }

    private double min(ArrayList<Double> arr) {
        double min = arr.get(0);
        for (double d: arr) {
            if (d < min) {
                min = d;
            }
        }
        return min;
    }

}
