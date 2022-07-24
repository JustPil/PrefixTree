package nodes;

public class ListBasedNode {
    private char letter;
    private ListBasedNode next = null;
    private boolean fullWord = false;
    private int numChildren = 0;

    /**
     * Constructor sets a Node's letter.
     * @param c The letter parameter.
     */
    public ListBasedNode(char c) {
        letter = c;
    }

    /**
     * getNext Returns the next child Node.
     * @return The next child Nodes.
     */
    public ListBasedNode getNext() {
        return next;
    }

    /**
     * getNext Sets the next child Node.
     * @param n The next child Node.
     */
    public void setNext(ListBasedNode n) {
        next = n;
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
     * getNumChildren Returns the total number of children of the Node.
     * @return The total number of children.
     */
    public int getNumChildren() {
        return numChildren;
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
     * findChild Finds if a child is present.
     * @param c The letter of the child to search.
     * @return The child Node is found, null otherwise.
     */
    public ListBasedNode findChild(char c) {
        if(next == null) {
            return null;
        }
        ListBasedNode parser = this;
        boolean firstNode = true;
        while(parser != null) {
            if(parser.getLetter() == c && !firstNode) {
                return parser;
            }
            firstNode = false;
            parser = parser.getNext();
        }
        return null;
    }

    /**
     * addChild Adds a child Node.
     * @param c The character for the child Node.
     */
    public void addChild(char c) {
        ListBasedNode parser = this;
        while(parser.getNext() != null) {
            parser = parser.next;
        }
        parser.setNext(new ListBasedNode(c));
        numChildren++;
    }

    /**
     * decrementNumChildren Decrements the total number of child Nodes.
     */
    public void decrementNumChildren() {
        numChildren--;
    }
}
