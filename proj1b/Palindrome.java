public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            d.addLast(c);
        }
        return d;
    }

    private boolean helper(Deque<Character> d) {
        if (d.size() <= 1) return true;
        return (d.removeFirst() == d.removeLast()) && helper(d);
    }

    public boolean isPalindrome(String word) {
        /** A solution that doesn't use deque. */
        /**
        int L = word.length();
        if (L <= 1) {
            return true;
        }
        for (int i = 0; i < L / 2; i++) {
            int correspondence = L - i - 1;
            if (word.charAt(i) != word.charAt(correspondence)) {
                return false;
            }
        }
        return true;*/

        /** A solution that uses deque. */
        return helper(wordToDeque(word));
    }

    private boolean helper2(Deque<Character> d, CharacterComparator cc) {
        if (d.size() <= 1) return true;
        return cc.equalChars(d.removeFirst(),d.removeLast()) && helper2(d, cc);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return helper2(wordToDeque(word), cc);
    }
}
