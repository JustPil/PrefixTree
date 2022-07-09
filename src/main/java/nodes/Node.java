package nodes;

public class Node {
    private char letter;
    private final int ALPHABET = 26;
    private Node[] childNodes = new Node[ALPHABET];
    private boolean fullWord = false;

    /**
     * Constructor sets a Node's letter.
     * @param c The letter parameter.
     */
    public Node(char c) {
        letter = c;
    }

    /**
     * getChildNodes Returns the array of child Nodes.
     * @return The array of child Nodes.
     */
    public Node[] getChildNodes() {
        return childNodes;
    }

    /**
     * getLetter Returns the letter of a Node.
     * @return The letter of a Node.
     */
    public char getLetter() {
        return letter;
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
     * setLetter Sets the letter of a Node.
     * @param c The character to assign a Node.
     */
    public void setLetter(char c) {
        letter = c;
    }

    /**
     * addChildNodes Adds a child Node to a current Node.
     * @param c The letter of the child Node.
     */
    public void addChildNodes(char c) {
        if(!Character.isLetter(c) || !Character.isUpperCase(c)) {
            return;
        }
        childNodes[c - 'a'] = new Node(c);
    }
}
