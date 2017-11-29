import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * AlphabetSort takes input from stdin and prints to stdout.
 * The first line of input is the alphabet permutation.
 * The the remaining lines are the words to be sorted.
 *
 * The output should be the sorted words, each on its own line,
 * printed to std out.
 */

public class AlphabetSort {
    // Might need to use tries for runtime. :)

    private String order = new String();
    private CharacterTrie words = new CharacterTrie();

    /**
     * Reads input from standard input and prints out the input words in
     * alphabetical order.
     *
     * @param args ignored
     **/

    public AlphabetSort() {
        this.order = order;
        this.words = words;
    }

    public void printAlphaSort() {
        words.printAlpha(order);
    }

    public static void main(String[] args) {
        /* YOUR CODE HERE. */
        // Initializes reader and fills orderToNumber.
        AlphabetSort toBeSorted = new AlphabetSort();
        String curr = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            toBeSorted.order = reader.readLine();
        } catch (IOException e) {
            System.out.println(e);
            System.err.println(e.getMessage());
        }
        if (toBeSorted.order == null || toBeSorted.order.equals("")) {
            throw new IllegalArgumentException("No Alphabet Given");
        }
        for (int i = 0; i < toBeSorted.order.length(); i++) {
            if (toBeSorted.order.indexOf(toBeSorted.order.charAt(i))
                    == toBeSorted.order.lastIndexOf(toBeSorted.order.charAt(i))) {
                continue;
            } else {
                throw new IllegalArgumentException("Duplicate characters in Alphabet.");
            }
        }
        // Adds all words after the alphabet into a trie;
        try {
            curr = reader.readLine();
        } catch (IOException e) {
            System.out.println(e);
            System.err.println(e.getMessage());
        }
        if (curr == null || curr.equals("")) {
            throw new IllegalArgumentException("No Words Given");
        }
        toBeSorted.words.insert(curr);
        try {
            curr = reader.readLine();
        } catch (IOException e) {
            System.out.println(e);
            System.err.println(e.getMessage());
        }
        while (curr != null && !curr.equals("")) {
            toBeSorted.words.insert(curr);
            try {
                curr = reader.readLine();
            } catch (IOException e) {
                System.out.println(e);
                System.err.println(e.getMessage());
            }
            // Possible null pointer?
        }

        toBeSorted.printAlphaSort();
    }
}
