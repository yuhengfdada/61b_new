package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ArrayHeapMinPQTest {

    @Test
    public void genericSanityTest() {
        ArrayHeapMinPQ<Integer> intPQ = new ArrayHeapMinPQ<>();
        ArrayHeapMinPQ<String> strPQ = new ArrayHeapMinPQ<>();
        intPQ.add(1, 1);
        strPQ.add("123", 1);
        intPQ.changePriority(1, 2);
        assertEquals(1, intPQ.size());
        assertTrue(intPQ.contains(1));
        assertFalse(intPQ.contains(2));
        assertEquals(1, (int)(intPQ.getSmallest()));
        assertEquals(1, (int)(intPQ.removeSmallest()));
    }

    @Test
    public void testSwap() {
        ArrayHeapMinPQ<Integer> intPQ = new ArrayHeapMinPQ<>();
        intPQ.add(1, 1);
        intPQ.add(2, 2);
        assertEquals(1, (int)(intPQ.getSmallest()));
        intPQ.swap(1, 2);
        assertEquals(2, (int)(intPQ.getSmallest()));
    }

    @Test
    public void testGoToRightPlace() {
        ArrayHeapMinPQ<Integer> intPQ = new ArrayHeapMinPQ<>();
        intPQ.add(1, 1);
        intPQ.add(2, 2);
        intPQ.add(3, 3);
        intPQ.add(4, 4);
        assertEquals(1, (int)(intPQ.getSmallest()));
        intPQ.swap(1, 3);
        assertEquals(3, (int)(intPQ.getSmallest()));
        intPQ.goToRightPlace(3, 0);
        assertEquals(1, (int)(intPQ.getSmallest()));
        intPQ.goToRightPlace(1, 0);
        assertEquals(1, (int)(intPQ.getSmallest()));
        intPQ.changePriority(1, 5);
        // intPQ.print();
        assertEquals(2, (int)(intPQ.getSmallest()));
    }
    @Test
    public void testMy() {
        NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
        naivePQ.add(1, 1);
        naivePQ.add(2, 2);
        naivePQ.add(3, 3);
        naivePQ.add(4, 4);
        assertEquals(1, (int)(naivePQ.getSmallest()));
        naivePQ.changePriority(1, 5);
        // naivePQ.print();
        assertEquals(2, (int)(naivePQ.getSmallest()));
        System.out.println(naivePQ.myToString());
    }
    @Test
    public void randomTest() {
        ArrayHeapMinPQ<Integer> intPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
        ArrayList<Integer> items = new ArrayList<>();
        ArrayList<Integer> priorities = new ArrayList<>();

        /**
        int[] array1 = {-89, 55, 98, 58, -88, 45, -39, -48, -9};
        double[] array2 = {-69, -56, 2, -9, 28, 38, 11, 96, 95};
        for (int i = 0; i < 9; i++) {
            intPQ.add(array1[i], array2[i]);
        }
        intPQ.print();
        while (intPQ.size() > 0) {
            intPQ.removeSmallest();
            intPQ.print();
        }
*/



        for (int i = 0; i < 100; i++) {
            int a = StdRandom.uniform(-100, 100);
            int aPriority = StdRandom.uniform(-100, 100);
            if (items.contains(a)) continue;
            if (priorities.contains(aPriority)) continue;
            intPQ.add(a, aPriority);
            naivePQ.add(a, aPriority);
            items.add(a);
            priorities.add(aPriority);
        }


        for (int i = 0; i < 100; i++) {
            int a = StdRandom.uniform(-100, 100);
            int aPriority = StdRandom.uniform(-100, 100);
            int operationNum = StdRandom.uniform(3);
            if (operationNum == 0) {
                if (items.contains(a)) continue;
                if (priorities.contains(aPriority)) continue;
                intPQ.add(a, aPriority);
                naivePQ.add(a, aPriority);
                items.add(a);
                priorities.add(aPriority);
            } else if (operationNum == 1) {
                int s1 = intPQ.removeSmallest();
                int s2 = naivePQ.removeSmallest();
                if (s1 != s2) intPQ.print();
                assertEquals(s1, s2);
                items.remove((Integer) s1);
            } else {
                if (priorities.contains(aPriority)) continue;
                int r = StdRandom.uniform(0,items.size());
                int a1 = items.get(r);
                intPQ.changePriority(a1, aPriority);
                naivePQ.changePriority(a1, aPriority);

            }
        }


        assertEquals(intPQ.size(), naivePQ.size());
        while (intPQ.size() > 0) {
            int s1 = intPQ.removeSmallest();
            int s2 = naivePQ.removeSmallest();
            if (s1 != s2) intPQ.print();
            assertEquals(s1, s2);
        }
    }

    @Test
    public void timedTest() {
        ArrayHeapMinPQ<Integer> intPQ = new ArrayHeapMinPQ<>();
        int times = 1000000;
        Stopwatch watch = new Stopwatch();
        for (int i = 0; i < times; i++) {
            intPQ.add(i, StdRandom.uniform(-10000, 10000));
        }
        System.out.println("For "+ times + " trials, the total time is: " + watch.elapsedTime());

        Stopwatch watch2 = new Stopwatch();
        intPQ.contains(StdRandom.uniform(1000000));
        System.out.println("Time for contains: " + watch2.elapsedTime());

        Stopwatch watch3 = new Stopwatch();
        intPQ.removeSmallest();
        System.out.println("Time for remove: " + watch3.elapsedTime());
    }

    @Test
    public void timedTestNaive() {
        NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
        int times = 1000000;
        Stopwatch watch = new Stopwatch();
        for (int i = 0; i < times; i++) {
            naivePQ.add(i, StdRandom.uniform(-10000, 10000));
        }
        System.out.println("For "+ times + " trials, the total time is: " + watch.elapsedTime());

        Stopwatch watch2 = new Stopwatch();
        naivePQ.contains(StdRandom.uniform(1000000));
        System.out.println("Time for contains: " + watch2.elapsedTime());

        Stopwatch watch3 = new Stopwatch();
        naivePQ.removeSmallest();
        System.out.println("Time for remove: " + watch3.elapsedTime());
    }

    @Test
    public void randomTimedTest() {
        ArrayHeapMinPQ<Integer> intPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
        ArrayList<Integer> items = new ArrayList<>();
        ArrayList<Integer> priorities = new ArrayList<>();

        int times = 100000;
        for (int i = 0; i < 10000; i++) {
            int a = StdRandom.uniform(-times, times);
            int aPriority = StdRandom.uniform(-times, times);
            if (items.contains(a)) continue;
            if (priorities.contains(aPriority)) continue;
            intPQ.add(a, aPriority);
            //naivePQ.add(a, aPriority);
            items.add(a);
            priorities.add(aPriority);
        }


        Stopwatch watch = new Stopwatch();
        for (int i = 0; i < times; i++) {
            int a = StdRandom.uniform(-times, times);
            int aPriority = StdRandom.uniform(-times, times);
            int operationNum = StdRandom.uniform(10);
            if (operationNum <= 3) {
                if (items.contains(a)) continue;
                if (priorities.contains(aPriority)) continue;
                intPQ.add(a, aPriority);
                //naivePQ.add(a, aPriority);
                items.add(a);
                priorities.add(aPriority);
            } else if (operationNum <= 7) {
                int s1 = intPQ.removeSmallest();
                //int s2 = naivePQ.removeSmallest();
                //if (s1 != s2) intPQ.print();
                //assertEquals(s1, s2);
                items.remove((Integer) s1);
            } else {
                if (priorities.contains(aPriority)) continue;
                int r = StdRandom.uniform(0,items.size());
                int a1 = items.get(r);
                intPQ.changePriority(a1, aPriority);
                //naivePQ.changePriority(a1, aPriority);
            }
        }

        System.out.println("For "+ times + " trials, the total time is: " + watch.elapsedTime());


    }

    @Test
    public void naiveRandomTimedTest() {
        ArrayHeapMinPQ<Integer> intPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
        ArrayList<Integer> items = new ArrayList<>();
        ArrayList<Integer> priorities = new ArrayList<>();


        for (int i = 0; i < 10000; i++) {
            int a = StdRandom.uniform(-100000, 100000);
            int aPriority = StdRandom.uniform(-100000, 100000);
            if (items.contains(a)) continue;
            if (priorities.contains(aPriority)) continue;
            //intPQ.add(a, aPriority);
            naivePQ.add(a, aPriority);
            items.add(a);
            priorities.add(aPriority);
        }

        int times = 100000;
        Stopwatch watch = new Stopwatch();
        for (int i = 0; i < times; i++) {
            int a = StdRandom.uniform(-100000, 100000);
            int aPriority = StdRandom.uniform(-100000, 100000);
            int operationNum = StdRandom.uniform(10);
            if (operationNum <= 3) {
                if (items.contains(a)) continue;
                if (priorities.contains(aPriority)) continue;
                //intPQ.add(a, aPriority);
                naivePQ.add(a, aPriority);
                items.add(a);
                priorities.add(aPriority);
            } else if (operationNum <= 7) {
                //int s1 = intPQ.removeSmallest();
                int s2 = naivePQ.removeSmallest();
                //if (s1 != s2) intPQ.print();
                //assertEquals(s1, s2);
                items.remove((Integer) s2);
            } else {
                if (priorities.contains(aPriority)) continue;
                int r = StdRandom.uniform(0,items.size());
                int a1 = items.get(r);
                //intPQ.changePriority(a1, aPriority);
                naivePQ.changePriority(a1, aPriority);
            }
        }

        System.out.println("For "+ times + " trials, the total time is: " + watch.elapsedTime());


    }


}
