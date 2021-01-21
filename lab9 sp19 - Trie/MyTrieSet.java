import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private static class trieNode {
        char item;
        boolean isKey;
        HashMap<Character, trieNode> children;

        public trieNode(char item, boolean isKey) {
            this.item = item;
            this.isKey = isKey;
            children = new HashMap<>();
        }
    }

    trieNode root;

    public MyTrieSet() {
        root = new trieNode(' ', false);
    }

    /** Clears all items out of Trie */
    public void clear() {
        root.children.clear();
    }

    private boolean containsHelper(String key, trieNode node) {
        if (key.equals("")) {
            if (node.isKey) return true;
            else return false;
        }
        char thisOne = key.charAt(0);
        String newOne = key.substring(1);
        trieNode newNode = node.children.get(thisOne);
        return !(newNode == null) && containsHelper(newOne, newNode);
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        return containsHelper(key, root);
    }

    private void addHelper(String key, trieNode node) {
        if (key.equals("")) {
            node.isKey = true;
            return;
        }
        char thisOne = key.charAt(0);
        String newOne = key.substring(1);
        if (node.children.containsKey(thisOne)) {
            addHelper(newOne, node.children.get(thisOne));
        } else {
            trieNode newNode = new trieNode(thisOne, false);
            node.children.put(thisOne, newNode);
            addHelper(newOne, newNode);
        }
    }

    /** Inserts string KEY into Trie */
    public void add(String key) {
        addHelper(key, root);
    }

    private trieNode findPrefix(String prefix, trieNode node) {
        if (prefix.equals("")) {
            return node;
        }
        char thisOne = prefix.charAt(0);
        String newOne = prefix.substring(1);
        trieNode newNode = node.children.get(thisOne);
        if (newNode == null) return null;
        return findPrefix(newOne, newNode);
    }

    private void KWPHelper(String prefix, trieNode node, List<String> list) {
        if (node.isKey) {
            list.add(prefix);
        }
        if (node.children.isEmpty()) return;
        for (char key : node.children.keySet()) {
            KWPHelper(prefix + key, node.children.get(key), list);
        }
    }
    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        List<String> list = new ArrayList<>();
        trieNode node = findPrefix(prefix, root);
        if (node == null) return null;
        KWPHelper(prefix, node, list);
        return list;
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");

        t.add("hi");
        t.add("help");
        t.add("zebra");
    }
}
