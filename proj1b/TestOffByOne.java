import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testAnotherPalindrome() {
        Palindrome palindrome = new Palindrome();
        /** old tests */
        String a = "anxjkalshnhdui";
        String b = "";
        String c = " ";
        String d = "$%^&189  ahdndha  981&^%$";
        String e = "$%^&189  ahddha  981&^%$";
        assertFalse(palindrome.isPalindrome(a,offByOne));
        assertTrue(palindrome.isPalindrome(b,offByOne));
        assertTrue(palindrome.isPalindrome(c,offByOne));
        assertFalse(palindrome.isPalindrome(d,offByOne));
        assertFalse(palindrome.isPalindrome(e,offByOne));

        /** new tests */
        String f = "abABab";
        String g = "araqb";
        String h = "%#$&";
        assertTrue(palindrome.isPalindrome(f,offByOne));
        assertTrue(palindrome.isPalindrome(g,offByOne));
        assertTrue(palindrome.isPalindrome(h,offByOne));
    }
}
