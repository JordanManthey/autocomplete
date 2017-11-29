import java.util.ArrayList;

/** A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T) and an associated priority value.
 * @author CS 61BL Staff */
public class ArrayHeap<T> {


    private ArrayList<Node> contents;

    public ArrayHeap() {
        contents = new ArrayList<>();
        contents.add(null);
    }

    private Node getNode(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    private void setNode(int index, Node n) {
        // In the case that the ArrayList is not big enough
        // add null elements until it is the right size
        while (index + 1 >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, n);
    }

    private void swap(int index1, int index2) {
        Node node1 = getNode(index1);
        Node node2 = getNode(index2);
        this.contents.set(index1, node2);
        this.contents.set(index2, node1);
    }


    @Override
    public String toString() {
        return toStringHelper(1, "");
    }


    private String toStringHelper(int index, String soFar) {
        if (getNode(index) == null) {
            return "";
        } else {
            String toReturn = "";
            int rightChild = getRightOf(index);
            toReturn += toStringHelper(rightChild, "        " + soFar);
            if (getNode(rightChild) != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + getNode(index) + "\n";
            int leftChild = getLeftOf(index);
            if (getNode(leftChild) != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += toStringHelper(leftChild, "        " + soFar);
            return toReturn;
        }
    }

    public int size() {
        return contents.size() - 2;
    }


    public class Node {
        private T item;
        private double priority;

        private Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        public T item() {
            return this.item;
        }

        public double priority() {
            return this.priority;
        }

        @Override
        public String toString() {
            return this.item.toString() + ", " + this.priority;
        }
    }




    private int getLeftOf(int i) {
        //YOUR CODE HERE
        return i * 2;
    }


    private int getRightOf(int i) {
        //YOUR CODE HERE
        return (i * 2) + 1;
    }


    private int getParentOf(int i) {
        //YOUR CODE HERE
        return i / 2;
    }


    private void setLeft(int index, Node n) {
        int left = getLeftOf(index);
        contents.add(left, n);
        //YOUR CODE HERE
    }


    private void setRight(int index, Node n) {
        //YOUR CODE HERE
        int right = getRightOf(index);
        contents.add(right, n);
    }


    private int min(int index1, int index2) {
        //YOUR CODE HERE
        Node a = getNode(index1);
        Node b = getNode(index2);
        if (b == null || a.priority() < b.priority()) {
            return index1;
        } else {
            return index2;
        }
    }


    public Node peek() {
        //YOUR CODE HERE
        return getNode(1);
    }


    private void bubbleUp(int index) {
        //YOUR CODE HERE
        Node bub = getNode(index);
        while (!getNode(1).item().equals(bub.item())) {
            int parent = (getParentOf(contents.indexOf(bub)));
            if (min(index, parent) == index) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }


    private void bubbleDown(int index) {
        //YOUR CODE HERE
        Node bub = getNode(index);
        while (getNode(getLeftOf(index)) != null) {
            int loR = min(getLeftOf(index), getRightOf(index));
            if (min(loR, index) == loR) {
                swap(loR, index);
                index = loR;
            } else {
                break;
            }
        }
    }


    public void insert(T item, double priority) {
        //YOUR CODE HERE
        Node newGuy = new Node(item, priority);
        int start = 1;
        while (getNode(start) != null) {
            start++;
        }
        setNode(start, newGuy);
        bubbleUp(start);
    }


    public Node removeMin() {
        //YOUR CODE HERE
        int start = 1;
        while (getNode(start) != null) {
            start++;
        }
        Node small = getNode(1);
        setNode(1, getNode(start - 1));
        contents.remove(start - 1);
        bubbleDown(1);
        return small;
    }


    public void changePriority(T item, double priority) {
        //YOUR CODE HERE
        int start = 1;
        double oldPr = 0;
        while (getNode(start) != null) {
            Node curr = getNode(start);
            if (curr.item().equals(item)) {
                oldPr = curr.priority();
                setNode(start, new Node(item, priority));
                break;
            }
            start++;
        }
        if (oldPr > priority && oldPr != 0) {
            bubbleUp(start);
        } else if (oldPr < priority && oldPr != 0) {
            bubbleDown(start);
        }
    }
}
