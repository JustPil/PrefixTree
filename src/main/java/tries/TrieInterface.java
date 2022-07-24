package tries;

public interface TrieInterface {
    void addWord(String word);
    boolean deleteWord(String word);
    boolean searchWord(String word);
    boolean searchPrefix(String prefix);
}
