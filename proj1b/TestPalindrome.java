import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testPalindrome() {
        String a = "anxjkalshnhdui";
        String b = "";
        String c = " ";
        String d = "$%^&189  ahdndha  981&^%$";
        String e = "$%^&189  ahddha  981&^%$";
        assertFalse(palindrome.isPalindrome(a));
        assertTrue(palindrome.isPalindrome(b));
        assertTrue(palindrome.isPalindrome(c));
        assertTrue(palindrome.isPalindrome(d));
        assertTrue(palindrome.isPalindrome(e));
    }

    @Test
    public void testAnotherPalindrome() {
        OffByOne obo = new OffByOne();

        /** old tests */
        String a = "anxjkalshnhdui";
        String b = "";
        String c = " ";
        String d = "$%^&189  ahdndha  981&^%$";
        String e = "$%^&189  ahddha  981&^%$";
        assertFalse(palindrome.isPalindrome(a,obo));
        assertTrue(palindrome.isPalindrome(b,obo));
        assertTrue(palindrome.isPalindrome(c,obo));
        assertFalse(palindrome.isPalindrome(d,obo));
        assertFalse(palindrome.isPalindrome(e,obo));

        /** new tests */
        String f = "abABab";
        String g = "araqb";
        String h = "%#$&";
        assertTrue(palindrome.isPalindrome(f,obo));
        assertTrue(palindrome.isPalindrome(g,obo));
        assertTrue(palindrome.isPalindrome(h,obo));
    }

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
}
