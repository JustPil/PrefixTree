package trie;

public interface TrieInterface
{
    Node ROOT = new Node('*');
    boolean addWord(String word);
    boolean deleteWord(String word);
    boolean searchWord(String word);
    boolean searchPrefix(String prefix);
}
