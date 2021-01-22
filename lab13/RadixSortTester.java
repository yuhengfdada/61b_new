import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RadixSortTester {
    @Test
    public void someTest() {
        String[] asciis = {"a", "b", "c", "ab"};
        String[] haha = RadixSort.sort(asciis);
        String[] exp = {"a", "ab", "b", "c"};
        assertArrayEquals(exp, haha);
    }
}
