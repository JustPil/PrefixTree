package tries;

import nodes.ListBasedNode;
import java.util.Stack;

public class ListBasedTrie implements TrieInterface{
    private final ListBasedNode ROOT = new ListBasedNode('*');

    /**
     * addWord Inserts a word in the Trie.
     * @param word The word to insert.
=     */
    public void addWord(String word) {
        validLetterCheck(word);
        word = word.toLowerCase();
        ListBasedNode parser = ROOT;
        for(int i = 0; i < word.length(); i++) {
            ListBasedNode child = parser.findChild(word.charAt(i));
            if(child != null) {
                parser = child;
            } else {
                parser.addChild(word.charAt(i));
                parser = parser.findChild(word.charAt(i));
            }
        }
        parser.setFullWord(true);
    }

    /**
     * deleteWord Removes a word from the Trie.
     * @param word The word to remove.
     * @return True if removal was successful, false otherwise.
     */
    public boolean deleteWord(String word) {
        if(!searchWord(word)) {
            return false;
        }
        word = word.toLowerCase();
        Stack<ListBasedNode> nodeStack = new Stack<>();
        Stack<Boolean> dependencyStack = new Stack<>();
        populateNodeStack(nodeStack, word);
        populateDependencyStack(dependencyStack, word);
        nodeStack.peek().setFullWord(false);
        while(nodeStack.size() > 1) {
            ListBasedNode node = nodeStack.pop();
            boolean dependent = dependencyStack.pop();
            if(dependent) {
                return true;
            } else {
                removeChild(nodeStack.peek(), node.getLetter());
            }
        }
        return true;
    }

    /**
     * populateNodeStack Populates a Stack of Nodes for use in the deleteWord method, providing reverse access to Nodes
     * in a word's path.
     * @param stack The Stack to populate from the deleteWord method.
     * @param word The word to traverse in the Trie.
     */
    private void populateNodeStack(Stack<ListBasedNode> stack, String word) {
        stack.push(ROOT);
        ListBasedNode parser = ROOT.findChild(word.charAt(0));
        for(int i = 1; i < word.length(); i++) {
            stack.push(parser);
            parser = parser.findChild(word.charAt(i));
        }
        stack.push(parser);
    }

    /**
     * populateDependencyStack Populates a Stack of boolean values for use in the deleteWord method, providing a true
     * value if the Node in a path must be retained for dependency of another unrelated word, or false otherwise.
     * @param stack The Stack to populate from the deleteWord method.
     * @param word The word to traverse in the Trie.
     */
    private void populateDependencyStack(Stack<Boolean> stack, String word) {
        stack.push(ROOT.getNumChildren() > 1);
        ListBasedNode parser = ROOT.findChild(word.charAt(0));
        for(int i = 1; i < word.length(); i++) {
            stack.push(parser.getNumChildren() > 1 || parser.isFullWord());
            parser = parser.findChild(word.charAt(i));
        }
        stack.push(parser.getNext() != null);
    }

    /**
     * removeChild Removes a child Node from a Trie's Node.
     * @param node The parent Node of the child to remove.
     * @param c The character of the Node to remove.
     */
    private void removeChild(ListBasedNode node, char c) {
        while(node != null) {
            if(node.getNext() != null && node.getNext().getLetter() == c) {
                node.setNext(null);
                node.decrementNumChildren();
            }
            node = node.getNext();
        }
    }

    /**
     * searchWord Performs a word search in the Trie.
     * @param word The word to search.
     * @return True if the word is found, false otherwise.
     */
    public boolean searchWord(String word) {
        validLetterCheck(word);
        word = word.toLowerCase();
        ListBasedNode parser = ROOT;
        for(int i = 0; i < word.length(); i++) {
            ListBasedNode child = parser.findChild(word.charAt(i));
            if(child != null) {
                parser = child;
            } else {
                return false;
            }
        }
        return parser.isFullWord();
    }

    /**
     * searchPrefix Performs a prefix search in the Trie.
     * @param prefix The prefix to search.
     * @return True if the prefix is found, false otherwise.
     */
    public boolean searchPrefix(String prefix) {
        validLetterCheck(prefix);
        prefix = prefix.toLowerCase();
        ListBasedNode parser = ROOT;
        for(int i = 0; i < prefix.length(); i++) {
            ListBasedNode child = parser.findChild(prefix.charAt(i));
            if(child != null) {
                parser = child;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * validLetterCheck Checks if the word contains only letters, throwing an exception if nonletter characters are
     * encountered.
     * @param word The word to check.
     */
    private void validLetterCheck(String word) {
        for(int i = 0; i < word.length(); i++) {
            if(!Character.isLetter(word.charAt(i))) {
                throw new IllegalArgumentException("Input string must contain only letters");
            }
        }
    }
}
