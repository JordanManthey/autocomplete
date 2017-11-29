import ucb.junit.textui;
import org.junit.Test;
import static org.junit.Assert.*;

/** The suite of all JUnit tests for the Trie class.
 *  @author
 */
public class TestTrie {

    /** A dummy test to avoid complaint. */
    @Test
    public void placeholderTest() {

    }

    @Test
    public void testBasic() {
        Trie t = new Trie();
        t.insert("hello");
        t.insert("hey");
        t.insert("goodbye");
        assertEquals(true, (t.find("hell", false)));
        assertEquals(true, (t.find("hello", true)));
        assertEquals(true, (t.find("good", false)));
        assertEquals(false, (t.find("bye", false)));
        assertEquals(false, (t.find("heyy", false)));
        assertEquals(false, (t.find("hell", true)));
    }

    @Test
    public void testFail1() {
        Trie t = new Trie();
        try {
            t.insert("");
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println("Error thrown");
        }
    }

    @Test
    public void testFail2() {
        Trie t = new Trie();
        try {
            t.insert(null);
            fail();
        } catch (IllegalArgumentException e) {
            System.out.println("Error thrown");
        }
    }

    @Test
    public void testHard() {
        Trie t = new Trie();
        t.insert("her");
        assertTrue(t.find("he", false));
        assertTrue(t.find("her", true));
        assertTrue(t.find("her", false));
        assertFalse(t.find("he", true));
        t.insert("he");
        assertTrue(t.find("he", true));
        assertTrue(t.find("he", false));
    }

    @Test
    public void testSubstrings() {
        Trie t = new Trie();
        t.insert("manager");
        assertTrue(t.find("man", false));
        assertTrue(t.find("manag", false));
        assertTrue(t.find("manage", false));
        assertTrue(t.find("manager", false));
        assertTrue(t.find("manager", true));
        assertFalse(t.find("man", true));
        assertFalse(t.find("manag", true));
        t.insert("man");
        assertTrue(t.find("man", true));
        assertTrue(t.find("man", false));
        assertTrue(t.find("manag", false));
        assertFalse(t.find("manag", true));
        t.insert("manage");
        assertTrue(t.find("manage", true));
        assertTrue(t.find("manage", false));
        assertTrue(t.find("manag", false));
        assertFalse(t.find("manag", true));
        assertTrue(t.find("man", true));
        assertTrue(t.find("manager", true));
        t.insert("managerman");
        assertTrue(t.find("manage", true));
        assertTrue(t.find("manage", false));
        assertTrue(t.find("man", true));
        assertTrue(t.find("man", false));
        assertTrue(t.find("manager", false));
        assertTrue(t.find("manager", true));
        assertTrue(t.find("manag", false));
        assertFalse(t.find("manag", true));
    }
    @Test
    public void cTrieSmall() {
        String order = "abcdefghijklmnopqrstuvwxyz";
        CharacterTrie c = new CharacterTrie();
        c.insert("school");

        c.printAlpha(order);
    }

    @Test
    public void cTrieMed() {
        String order = "abcdefghijklmnopqrstuvwxyz";
        CharacterTrie c = new CharacterTrie();
        c.insert("school");
        c.insert("hello");
        c.insert("world");
        c.insert("senior");
        c.insert("alpha");

        c.printAlpha(order);
    }

    @Test
    public void cTrieLong() {
        String order = "abcdefghijklmnopqrstuvwxyz";
        CharacterTrie c = new CharacterTrie();
        // check long
        c.insert("senior");
        c.insert("reckless");
        c.insert("subordinate");
        c.insert("polls");
        c.insert("a");
        c.insert("quarrel");
        c.insert("vince");
        c.insert("yearly");
        c.insert("peace");
        c.insert("love");
        c.insert("guy");
        c.insert("brother");
        c.insert("alpha");

        c.printAlpha(order);
    }

    @Test
    public void cTrieDuplicates() {
        String order = "abcdefghijklmnopqrstuvwxyz";
        CharacterTrie c = new CharacterTrie();
        // check duplicates
        c.insert("school");
        c.insert("school");

        c.printAlpha(order);
    }

    @Test
    public void cTrieStability() {
        String order = "abcdefghijklmnopqrstuvwxyz";
        CharacterTrie c = new CharacterTrie();
        // check stability
        c.insert("world");
        c.insert("wordly");

        c.printAlpha(order);
    }

    @Test
    public void cTrieIgnorables() {
        // order is missing letter 'a'
        String order = "bcdefghijklmnopqrstuvwxyz";
        CharacterTrie c = new CharacterTrie();
        c.insert("bentley");
        c.insert("child");
        // check if ignored
        c.insert("arms");
        c.insert("rattle");

        c.printAlpha(order);
    }

    /** Run the JUnit tests above. */
    public static void main(String[] ignored) {
        textui.runClasses(TestTrie.class);
    }
}
