package trietests;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import tries.ArrayBasedTrie;
import tries.TrieInterface;

public class ArrayBasedTrieTest {
    private final TrieInterface trie = new ArrayBasedTrie();

    @Test
    public void addWordToTrie() {
        trie.addWord("AAA");
        var result = trie.searchWord("AAA");
        Assertions.assertTrue(result);
    }

    @Test
    public void addOverlappingWordsToTrie() {
        trie.addWord("AAA");
        trie.addWord("AAAA");
        trie.addWord("AAAABBB");
        var result = trie.searchWord("AAA") && trie.searchWord("AAAA") && trie.searchWord("AAAABBB");
        Assertions.assertTrue(result);
    }

    @Test
    public void addOverlappingWordsToTrieLargestFirst() {
        trie.addWord("AAAABBB");
        trie.addWord("AAAA");
        trie.addWord("AAA");
        var result = trie.searchWord("AAA") && trie.searchWord("AAAA") && trie.searchWord("AAAABBB");
        Assertions.assertTrue(result);
    }

    @Test
    public void addNonOverlappingWordsToTrie() {
        trie.addWord("AAA");
        trie.addWord("BBB");
    }

    @Test
    public void searchWordNotPresent() {
        trie.addWord("AAA");
        var result = trie.searchWord("BBB");
        Assertions.assertFalse(result);
    }

    @Test
    public void searchInEmptyTrie() {
        var result = trie.searchWord("AAA");
        Assertions.assertFalse(result);
    }

    @Test
    public void searchWordWhenLettersArePresentInAnotherWord() {
        trie.addWord("AAA");
        var result = trie.searchWord("A");
        Assertions.assertFalse(result);
    }

    @Test
    public void deleteWordWhenNotPresentInTrie() {
        var result = trie.deleteWord("AAA");
        Assertions.assertFalse(result);
    }

    @Test
    public void deleteWordWhenPresentInTrie() {
        trie.addWord("AAA");
        trie.deleteWord("AAA");
        var result = trie.searchWord("AAA") && !trie.searchPrefix("A");
        Assertions.assertFalse(result);
    }

    @Test
    public void deleteWordWhenPresentInTrieAndPartOfAnotherWord() {
        trie.addWord("AAA");
        trie.addWord("A");
        trie.deleteWord("AAA");
        var result = !trie.searchWord("AAA") && trie.searchPrefix("A");
        Assertions.assertTrue(result);
    }

    @Test
    public void searchValidPrefix() {
        trie.addWord("AAA");
        var result = trie.searchPrefix("A");
        Assertions.assertTrue(result);
    }

    @Test
    public void searchInvalidPrefix() {
        var result = trie.searchPrefix("A");
        Assertions.assertFalse(result);
    }
}
