package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

public class Percolation {
    /** uf has the following structure:
     *  1. The internal array is row-wise arranged.
     *     That is, the first N elements represent the first row, and so on.
     *  2. The last element represents the "ceiling". It is originally blocked,
     *     but will open and connect to any opening first-row element.
     */
    private WeightedQuickUnionUF uf;
    private int[] status; // 0 means BLOCKED, 1 means OPEN
    private int N;
    private int ceiling;
    private int openCount = 0;
    private boolean percolated = false;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        uf = new WeightedQuickUnionUF(N * N + 1);
        status = new int[N * N];
        this.N = N;
        this.ceiling = N*N;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        /* Do nothing if it's already open. */
        if (isOpen(row, col)) return;
        checkMawariAndConnect(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return status[reduceDimension(row, col)] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.find(reduceDimension(row, col)) == uf.find(ceiling);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        /* Right now takes linear time. */
        if (percolated) return true;
        for (int col = 0; col <= N - 1; col++) {
            if (isFull(N - 1, col)){
                percolated = true;
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        /**
        Percolation p = new Percolation(3);
        p.print();
        p.open(0,0);
        p.print();
        assertEquals(true, p.isOpen(0,0));
        assertTrue(p.uf.connected(0,9));
        p.open(0, 1);
        p.open(1, 1);
        p.open(2, 1);
        p.print();
        assertTrue(p.uf.connected(7,0));
        assertTrue(p.percolates());
        assertFalse(p.isOpen(0,2));
        assertEquals(4, p.numberOfOpenSites());
         */
    }


    // HELPER FUNCTIONS
    private void validate(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int reduceDimension(int row, int col) {
        return N * row + col;
    }

    private void checkMawariAndConnect(int row, int col) {
        int pos = reduceDimension(row, col);
        /* Label pos as open. */
        makeOpen(pos);
        /* Find in the bounds neighbors. */
        LinkedList<Integer> ll = getValidNeighbors(row, col);
        /* Find open neighbors. */
        while (!ll.isEmpty()) {
            int n = ll.remove();
            /* Connect to open neighbors. */
            if (isOpen(n)) uf.union(pos, n);
        }
        /* first row special case. */
        if (pos <= N - 1) {
            uf.union(pos, ceiling);
        }
    }

    private void makeOpen(int pos) {
        status[pos] = 1;
        openCount += 1;
    }

    private boolean isOpen(int pos) {
        return status[pos] == 1;
    }

    private LinkedList<Integer> getValidNeighbors(int row, int col) {
        LinkedList<Integer> ll = new LinkedList<>();
        int pos = reduceDimension(row, col);
        int top = pos - N;
        int bot = pos + N;
        int left = pos - 1;
        int right = pos + 1;
        ll.add(top);
        ll.add(bot);
        ll.add(left);
        ll.add(right);
        if (row == 0) ll.removeFirstOccurrence(top);
        if (row == N - 1) ll.removeFirstOccurrence(bot);
        if (col == 0) ll.removeFirstOccurrence(left);
        if (col == N - 1) ll.removeFirstOccurrence(right);

        return ll;
    }

    private void print() {
        for (int i = 0; i < status.length; i++) {
            System.out.print(status[i]);
            System.out.print(" ");
            if ((i - N + 1) % N == 0) System.out.println();
        }
        System.out.println();
    }
}
