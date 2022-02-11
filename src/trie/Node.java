package trie;

public class Node
{
    private char letter;
    private final int ALPHABET = 26;
    private Node[] childNodes = new Node[ALPHABET];
    private boolean fullWord = false;

    /**
     *
     * @param c
     */
    Node(char c)
    {
        letter = c;
    }

    /**
     *
     * @return
     */
    public Node[] getChildNodes()
    {
        return childNodes;
    }

    /**
     *
     * @return
     */
    public char getLetter()
    {
        return letter;
    }

    /**
     *
     * @return
     */
    public boolean isFullWord()
    {
        return fullWord;
    }

    /**
     *
     * @param b
     */
    public void setFullWord(boolean b)
    {
        fullWord = b;
    }

    /**
     *
     * @param c
     */
    public void setLetter(char c)
    {
        letter = c;
    }

    /**
     *
     * @param c
     */
    public void addChildNodes(char c)
    {
        if(!Character.isLetter(c) || !Character.isUpperCase(c))
        {
            return;
        }
        childNodes[c - 'a'] = new Node(c);
    }
}
