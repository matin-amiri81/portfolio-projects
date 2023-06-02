
class CatsTrie:

    '''
        ---Function---
        :param sentences: a list of strings representing the sentences to be added to the prefix trie

        --Thought Notes---
        inicialising the tree adding every sentence to the prefix tree and adding the '$' node to the end of each word, also adding the frequency of each word to every node in the word, so that each node contains the highest frequency of the word using the node as a path and its parent

        1. add every sentence to the prefix tree                            O(n)
            1.1 add every letter to the prefix tree as a node               O(m)
            1.2 add a '$' node to the end of each sentence                  O(1)
            1.3 update the frequency of sentence to every node in the word  O(m)

        Total Time Complexity: O(n*m)
        Total Space Complexity: O(n*m)

        n is the number of sentences 
        m is the length of the largest sentence        
    '''

    def __init__(self, sentences):
        self.root = AlphabetNode(' ', None)
        for sentence in sentences:
            current_node = self.root
            for i in range(len(sentence)):
                letter_position = char_position(sentence[i])
                next_node = current_node.children[letter_position]
                if next_node != None:
                    current_node = next_node
                else:
                    current_node.children[letter_position] = AlphabetNode(
                        sentence[i], current_node)
                    current_node = current_node.children[letter_position]

            if current_node.children[0] == None:
                current_node.children[0] = AlphabetNode('$', current_node)

            current_node.children[0].add_to_frequency(
                current_node.children[0].max_word_frequency + 1)

    '''
        ---Function---
        :param prompt: a string representing the prefix of a word

        :return: a word from the prefix trie that has the highest frequency starting with the prompt, if there is a tie return the smallest word, if there is no word starting with the prompt return None

        --Thought Notes---
        going down the prefix trie following the path of the prompt until the next letter from the prompt does not exist in the chain and return none or get to the last letter in the prefix and find the autocomplete word.

        1. follow the path of the prompt
            1.1 follow prompt                                           O(m)
            1.2 get most frequent sentence starting with the prompt     O(m)


        Total Time Complexity: O(m)
        Total Space Complexity: O(m) 

        m is the length of the largest sentence
    '''

    def autoComplete(self, prompt):
        current_node = self.root
        for i in range(len(prompt)):
            letter_position = char_position(prompt[i])
            next_node = current_node.children[letter_position]
            if next_node != None:
                # following the path of the prompt
                current_node = next_node
            else:
                # no word starting with the prompt
                return None
        # getting the autocomplete word
        return self.get_most_common_words(current_node)

    '''
        ---Function---
        :param node: representing the last letter in the prefix of a word

        :return: a word from the prefix trie that has the highest frequency starting with the node given, if there is a tie return the smallest word

        --Thought Notes---
        since each node contains the highest frequency of the word using the node as a path, i can just follow the path of nodes with the same frequency as the given node, exiting through the first '$' node i encounter, returning None if there is no path is done in autocomplete so at this point it is guarantted that there is a word containing at least the entire prefix. since the '$' node is at index 0 and i check in ascending order, the first word i encounter with the same frequency as the current word will be the smallest.

        1. follow most frequent path
            1.1 follow path until the end of a sentence                 O(m) 

        Total Time Complexity: O(m) 
        Total Space Complexity: O(m) 

        m is the length of the largest sentence
    '''

    def get_most_common_words(self, node):
        frequency = node.max_word_frequency
        if node.letter == '$':
            # returning the smallest word with the same frequency
            return self.get_word(node.parent)
        for child in node.children:
            if child != None:
                if child.max_word_frequency == frequency:
                    # following the path of nodes with the same frequency
                    return self.get_most_common_words(child)

    '''
        ---Function---
        :param node: the node representing the last letter in the word

        :return: a word from the prefix trie ending with the node given

        --Thought Notes---
        work our way up the graph until we get to the root collecting the letters in the nodes

        1. follow the path of the word
            1.1 follow path until the root                               O(m)

        Total Time Complexity: O(m)
        Total Space Complexity: O(m) 

        m is the length of the largest sentence
    '''

    def get_word(self, node):
        word = ''
        while node.parent != None:
            word = node.letter + word
            node = node.parent
        return word


class AlphabetNode:

    def __init__(self, letter, parent):
        self.parent = parent
        self.letter = letter
        self.children = [None] * 27
        self.max_word_frequency = 0

    def add_to_frequency(self, frequency):
        self.max_word_frequency = max(self.max_word_frequency, frequency)
        if self.parent != None:
            self.parent.add_to_frequency(self.max_word_frequency)

    def add_child(self, child):
        position = char_position(child.letter)
        if self.children[position] is None:
            self.children[position] = AlphabetNode(child)


'''
    ---Function---
    :param letter: a letter from the alphabet

    :return: the position of the letter in the alphabet + 1

    --Thought Notes---
    every letter moved up one so that $ can be at index 0
    this is important for the get_most_common_words function
    as we want to return the smallest word in the case of a tie
    realising this made me feel like a genius i give mysef a star *

    Total Time Complexity: O(1)
    Total Space Complexity: O(1)
'''


def char_position(letter):
    return ord(letter) - 97 + 1


if __name__ == '__main__':
    sentences = ["a", "ab"]
    trie = CatsTrie(sentences)
    print(trie.autoComplete(""))  # == "a")
    print(trie.autoComplete("a"))  # == "a")
    print(trie.autoComplete("ab"))  # == "ab")
    print(trie.autoComplete("abc"))  # == None)
    print(trie.autoComplete("b"))  # == None)
    print(trie.autoComplete("fittwozerozerofour"))  # == None)

    sentences = ["abc", "abazacy", "dbcef", "xzz", "gdbc",
                 "abazacy", "xyz", "abazacy", "dbcef", "xyz", "xxx", "xzz"]
    mycattrie = CatsTrie(sentences)
    print(mycattrie.autoComplete("ab"))         # abazacy
    print(mycattrie.autoComplete("a"))          # abazacy
    print(mycattrie.autoComplete("dbcef"))      # dbcef
    print(mycattrie.autoComplete("dbcefz"))     # None
    print(mycattrie.autoComplete("ba"))         # None
    print(mycattrie.autoComplete("x"))          # xyz
    print(mycattrie.autoComplete(""))           # abazacy
