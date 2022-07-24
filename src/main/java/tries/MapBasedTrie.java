package tries;

import nodes.MapBasedNode;
import java.util.Stack;

public class MapBasedTrie implements TrieInterface{
    private final MapBasedNode ROOT = new MapBasedNode('*');

    /**
     * addWord Inserts a word in the Trie.
     * @param word The word to insert.
     */
    public void addWord(String word) {
        validLetterCheck(word);
        word = word.toLowerCase();
        MapBasedNode parser = ROOT;
        for(int i = 0; i < word.length(); i++) {
            if(!parser.getChildren().containsKey(word.charAt(i))) {
                parser.addChild(word.charAt(i));
            }
            parser = parser.getChildren().get(word.charAt(i));
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
        Stack<MapBasedNode> nodeStack = new Stack<>();
        Stack<Boolean> dependencyStack = new Stack<>();
        populateNodeStack(nodeStack, word);
        populateDependencyStack(dependencyStack, word);
        nodeStack.peek().setFullWord(false);
        boolean finalNodeFlag = true;
        for(int i = 0; i < nodeStack.peek().getChildren().size(); i++) {
            if(nodeStack.peek().getChildren().containsKey(word.charAt(i))) {
                finalNodeFlag = false;
                break;
            }
        }
        if(!finalNodeFlag) {
            return true;
        } else {
            while(!nodeStack.isEmpty()) {
                MapBasedNode node = nodeStack.pop();
                boolean dependentNode = dependencyStack.pop();
                if(!dependentNode && !nodeStack.isEmpty()) {
                    nodeStack.peek().getChildren().remove(node.getLetter());
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
    private void populateNodeStack(Stack<MapBasedNode> stack, String word) {
        MapBasedNode parser = ROOT.getChildren().get(word.charAt(0));
        for(int i = 1; i < word.length(); i++) {
            stack.push(parser);
            parser = parser.getChildren().get(word.charAt(i));
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
        MapBasedNode parser = ROOT.getChildren().get(word.charAt(0));
        boolean stackFlag = false;
        for(int i = 1; i < word.length(); i++) {
            for(int j = 0; j < parser.getChildren().size(); j++) {
                if(parser.isFullWord() || (parser.getChildren().containsKey(word.charAt(j)) &&
                        parser.getChildren().get(word.charAt(i)) != parser.getChildren().get(word.charAt(j)))) {
                    stack.push(true);
                    stackFlag = true;
                    break;
                }
            }
            if(!stackFlag) {
                stack.push(false);
            }
            stackFlag = false;
            parser = parser.getChildren().get(word.charAt(i));
        }
        for(int i = 0; i < parser.getChildren().size(); i++) {
            if(parser.getChildren().containsKey(word.charAt(i))) {
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
        MapBasedNode parser = ROOT;
        for(int i = 0; i < word.length(); i++) {
            if(!parser.getChildren().containsKey(word.charAt(i))) {
                return false;
            }
            parser = parser.getChildren().get(word.charAt(i));
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
        MapBasedNode parser = ROOT;
        for(int i = 0; i < prefix.length(); i++) {
            if(!parser.getChildren().containsKey(prefix.charAt(i))) {
                return false;
            }
            parser = parser.getChildren().get(prefix.charAt(i));
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
