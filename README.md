# PrefixTree
Summary

A Prefix Tree, also known as a Trie, is a Tree data structure for storing individual characters of a word. Supported methods include addition and removal of words, searching if a word is contained, and searching if a prefix exists.

Design

Each Trie Node carries a boolean value to mark if it is the end of a valid word. Each Node also carries an array of size 26 for the letters of the aplhabet as possible child Nodes.

The array maps an index to a letter by uniformly subtracting an 'a' character from each letter. As characters are represented by ASCII values, in the case of 'a' which is 97, a current letter 'a' subtracted from the uniform 'a' provides 'a' - 'a' or 97 - 97, which gives us index 0 mapping to the letter 'a'. A 'b' character, then, would give us 'b' - 'a' or 98 - 97, which is index 1 mapped to 'b'. Then index 2 would map to 'c', and so on.

The delete method is performed using a rigorous iterative algorithm rather than recursion, which dereferences Nodes if no other words depend on them. If there exists a dependent word, the boolean value that states the validity of a word is set to false. The algorithm needs to check the dependencies from the specified leaf node up to the root to determine is deletion of Nodes is possible. Although we only have reference at the root Node, a Stack is used to grant access to Nodes in reverse order, and another Stack to store boolean values for dependencies of those Nodes. If the current Node popped from the Stack has no dependecies, we peek at its parent Node in the Stack and dereference the child Node to be removed. If a dependency is found, we break out of the algorithm as no more deletions may occur without losing valid words.
