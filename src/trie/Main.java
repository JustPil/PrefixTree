package trie;

import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        Trie trie = new Trie();
        Scanner scn = new Scanner(System.in);
        String choice = "";
        while(!choice.equalsIgnoreCase("X"))
        {
            System.out.print("Methods for Prefix Tree (Trie)\n1 - ADD WORD - Adds a word to the Trie\n2 - DELETE" +
                    " WORD - Deletes a word from the Trie\n3 - SEARCH WORD - Searches for a word in the Trie\n4 - " +
                    "SEARCH PREFIX - Searches the Trie for a prefix\nX - Terminate\n\nEnter choice: ");
            choice = scn.nextLine();
            if(choice.equals("1"))
            {
                System.out.print("Input a word to add to the Trie: ");
                String word = scn.nextLine();
                System.out.println(word + " added: " + trie.addWord(word));
            }
            else if(choice.equals("2"))
            {
                System.out.print("Input a word to delete from the Trie: ");
                String word = scn.nextLine();
                System.out.println(word + " deleted: " + trie.deleteWord(word));
            }
            else if(choice.equals("3"))
            {
                System.out.print("Input a word to search in the Trie: ");
                String word = scn.nextLine();
                System.out.println(word + " found: " + trie.searchWord(word));
            }
            else if(choice.equals("4"))
            {
                System.out.print("Input a prefix to search in the Trie: ");
                String prefix = scn.nextLine();
                System.out.println(prefix + " found: " + trie.searchPrefix(prefix));
            }
            else if(choice.equalsIgnoreCase("X"))
            {
                break;
            }
            else
            {
                System.out.println("Invalid choice");
            }
        }
    }
}
