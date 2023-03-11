package nodes;

import java.util.HashMap;
import java.util.Map;

public class MapBasedNode {
    private char letter;
    private final Map<Character, MapBasedNode> children = new HashMap<>();
    private boolean fullWord = false;

    /**
     * Constructor sets a Node's letter.
     * @param c The letter parameter.
     */
    public MapBasedNode(char c) {
        letter = c;
    }

    /**
     * getChildren Returns the Map of child Nodes.
     * @return The Map of child Nodes.
     */
    public Map<Character, MapBasedNode> getChildren() {
        return children;
    }

    /**
     * getLetter Returns the letter of a Node.
     * @return The letter of a Node.
     */
    public char getLetter() {
        return letter;
    }

    /**
     * setLetter Sets the letter of a Node.
     * @param c The character to assign a Node.
     */
    public void setLetter(char c) {
        letter = c;
    }


    /**
     * isFullWord Returns if a Node is the end of a word.
     * @return True if the Node is the end of a word, false otherwise.
     */
    public boolean isFullWord() {
        return fullWord;
    }

    /**
     * setFullWord Sets a Node's fullWord variable to mark whether it is a word.
     * @param b The boolean value to assign to the Node's fullWord variable.
     */
    public void setFullWord(boolean b) {
        fullWord = b;
    }

    /**
     * addChild Adds a child Node.
     * @param c The character for the child Node.
     */
    public void addChild(char c) {
        if(!children.containsKey(c)) {
            children.put(c, new MapBasedNode(c));
        }
    }
}
