import static org.junit.Assert.*;
import org.junit.Test;

public class FilkTest {
    @Test
    public void filk() {
        int a = 1;
        int b = 2;
        int c = 2;
        boolean exp1 = false;
        boolean exp2 = true;
        assertEquals(exp1, Flik.isSameNumber(a, b));
        assertEquals(exp2, Flik.isSameNumber(b, c));
    }
}
