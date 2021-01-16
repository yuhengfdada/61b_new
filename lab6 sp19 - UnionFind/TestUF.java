import static org.junit.Assert.*;
import org.junit.Test;


public class TestUF {
    @Test
    public void testUF() {
        UnionFind uf = new UnionFind(9);
        uf.union(2, 3);
        uf.union(1, 2);
        uf.union(5, 7);
        uf.union(8, 4);
        uf.union(7, 2);
        assertEquals(2, uf.find(3));
        uf.union(0, 6);
        uf.union(6, 4);
        uf.union(6, 3);
        assertEquals(2, uf.find(8));
        assertEquals(2, uf.find(6));

        assertArrayEquals(new int[]{2,2,-9,2,2,2,2,5,2}, uf.array);

    }
}
