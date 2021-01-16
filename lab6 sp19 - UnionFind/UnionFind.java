import java.util.LinkedList;
public class UnionFind {

    public int[] array;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >= array.length) {
            throw new IllegalArgumentException("Bad argument");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        v1 = find(v1);
        return -parent(v1);
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return array[v1];
    }


    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by choosing the smaller integer as root. Unioning a
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        if (r1 == r2) return;
        if (sizeOf(r1) < sizeOf(r2)) {
            int size = sizeOf(r1);
            array[r1] = r2;
            array[r2] -= size;
        } else if (sizeOf(r1) > sizeOf(r2)){
            int size = sizeOf(r2);
            array[r2] = r1;
            array[r1] -= size;
        } else {
            if (r1 < r2) {
                int size = sizeOf(r2);
                array[r2] = r1;
                array[r1] -= size;
            } else {
                int size = sizeOf(r1);
                array[r1] = r2;
                array[r2] -= size;
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        LinkedList<Integer> ll = new LinkedList<>();
        while (parent(vertex) >= 0) {
            ll.add(vertex);
            vertex = parent(vertex);
        }
        while (!ll.isEmpty()) {
            array[ll.removeFirst()] = vertex;
        }
        return vertex;
    }

}
