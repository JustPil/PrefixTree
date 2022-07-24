package tries;

import nodes.ArrayBasedNode;
import java.util.Stack;

public class ArrayBasedTrie implements TrieInterface {
    private final ArrayBasedNode ROOT = new ArrayBasedNode('*');

    /**
     * addWord Inserts a word in the Trie.
     * @param word The word to insert.
     */
    public void addWord(String word) {
        validLetterCheck(word);
        word = word.toLowerCase();
        ArrayBasedNode parser = ROOT;
        for(int i = 0; i < word.length(); i++) {
            if(parser.getChildNodes()[word.charAt(i) - 'a'] == null) {
                parser.getChildNodes()[word.charAt(i) - 'a'] = new ArrayBasedNode(word.charAt(i));
            }
            parser = parser.getChildNodes()[word.charAt(i) - 'a'];
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
        Stack<ArrayBasedNode> nodeStack = new Stack<>();
        Stack<Boolean> dependencyStack = new Stack<>();
        populateNodeStack(nodeStack, word);
        populateDependencyStack(dependencyStack, word);
        nodeStack.peek().setFullWord(false);
        boolean finalNodeFlag = true;
        for(int i = 0; i < nodeStack.peek().getChildNodes().length; i++) {
            if(nodeStack.peek().getChildNodes()[i] != null) {
                finalNodeFlag = false;
                break;
            }
        }
        if(!finalNodeFlag) {
            return true;
        } else {
            while (!nodeStack.isEmpty()) {
                ArrayBasedNode node = nodeStack.pop();
                boolean dependentNode = dependencyStack.pop();
                if(!dependentNode && !nodeStack.isEmpty()) {
                    nodeStack.peek().getChildNodes()[node.getLetter() - 'a'] = null;
                } else {
                    break;
                }
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
    private void populateNodeStack(Stack<ArrayBasedNode> stack, String word) {
        ArrayBasedNode parser = ROOT.getChildNodes()[word.charAt(0) - 'a'];
        for(int i = 1; i < word.length(); i++) {
            stack.push(parser);
            parser = parser.getChildNodes()[word.charAt(i) - 'a'];
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
        ArrayBasedNode parser = ROOT.getChildNodes()[word.charAt(0) - 'a'];
        boolean stackFlag = false;
        for(int i = 1; i < word.length(); i++) {
            for(int j = 0; j < parser.getChildNodes().length; j++) {
                if(parser.isFullWord() || (parser.getChildNodes()[j] != null &&
                        parser.getChildNodes()[word.charAt(i) - 'a'] != parser.getChildNodes()[j])) {
                    stack.push(true);
                    stackFlag = true;
                    break;
                }
            }
            if(!stackFlag) {
                stack.push(false);
            }
            stackFlag = false;
            parser = parser.getChildNodes()[word.charAt(i) - 'a'];
        }
        for(int i = 0; i < parser.getChildNodes().length; i++) {
            if(parser.getChildNodes()[i] != null) {
                stackFlag = true;
            }
        }
        stack.push(stackFlag);
    }

    /**
     * searchWord Performs a word search in the Trie.
     * @param word The word to search.
     * @return True if the word is found, false otherwise.
     */
    public boolean searchWord(String word) {
        validLetterCheck(word);
        word = word.toLowerCase();
        ArrayBasedNode parser = ROOT;
        for(int i = 0; i < word.length(); i++) {
            if(parser.getChildNodes()[word.charAt(i) - 'a'] == null) {
                return false;
            }
            parser = parser.getChildNodes()[word.charAt(i) - 'a'];
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
        ArrayBasedNode parser = ROOT;
        for(int i = 0; i < prefix.length(); i++) {
            if(parser.getChildNodes()[prefix.charAt(i) - 'a'] == null) {
                return false;
            }
            parser = parser.getChildNodes()[prefix.charAt(i) - 'a'];
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
