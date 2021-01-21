package bearmaps;
import java.util.*;

/** Keep in mind there can't be duplicate items in this PQ */
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private static class PQNode<T> {
        public T key;
        public double priority;

        public PQNode(T key, double priority) {
            this.key = key;
            this.priority = priority;
        }
    }

    /* PQ array, as in class. */
    ArrayList<PQNode<T>> array;
    /* Pairs of <item(not Node), index>. */
    HashMap<T, Integer> map;
    int size;

    public ArrayHeapMinPQ() {
        array = new ArrayList<>();
        map = new HashMap<>();
        size = 0;
        /* Dummy */
        array.add(new PQNode<T>(null, -999));
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority) {
        if(contains(item)) throw new IllegalArgumentException();
        /* Add to the last */
        array.add(size + 1, new PQNode<>(item, priority));
        size += 1;
        /* Take care of MAP */
        map.put(item, size);
        /* The new node should go to the right place */
        goToRightPlace(size, 1);
    }

    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        return map.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        if (isEmpty()) throw new NoSuchElementException();
        return array.get(1).key;
    }
    
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (isEmpty()) throw new NoSuchElementException();
        T res = getSmallest();
        /* Modify PQ */
        swap(1, size);
        array.remove(size);
        size -= 1;
        if (size != 0) goToRightPlace(1, -1);
        /* Take care of MAP */
        map.remove(res);
        return res;
    }

    /* Returns the number of items in the PQ. */
    public int size() {
        return size;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        if (!contains(item)) throw new NoSuchElementException();
        int pos = map.get(item);
        array.get(pos).priority = priority;
        goToRightPlace(pos, 0);
    }


    /** HELPER FUNCTIONS */
    private int parentIndex(int pos) {
        return pos / 2;
    }

    private int leftChildIndex(int pos) {
        return pos * 2;
    }

    private int rightChildIndex(int pos) {
        return pos * 2 + 1;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    /* Swaps nodes at pos1 and pos2 in the PQ array.
   Also updates the map.
 */
    void swap(int pos1, int pos2) {
        PQNode<T> temp = array.get(pos1);
        array.set(pos1, array.get(pos2));
        array.set(pos2, temp);
        map.replace(array.get(pos1).key, pos1);
        map.replace(array.get(pos2).key, pos2);
    }

    boolean greater(int pos1, int pos2) {
        return array.get(pos1).priority > array.get(pos2).priority;
    }
    /* The most important function (in my opinion).
       Let the node at pos go to the right place.
       directionIdentifier == 1: up
       directionIdentifier == 0: undecided
       directionIdentifier == -1: down
    */
    void goToRightPlace(int pos, int directionIdentifier) {
        PQNode<T> me = array.get(pos);
        if (directionIdentifier == 1) {
            int parentIndex = parentIndex(pos);
            if (parentIndex == 0) return;  // reached root
            PQNode<T> parent = array.get(parentIndex);
            if (parent.priority <= me.priority) return; // reached home
            swap(pos, parentIndex);
            goToRightPlace(parentIndex, 1); // recursive call
        } else if (directionIdentifier == -1) {
            int smaller = 2 * pos;
            int left = 2 * pos;
            if (left > size) return;
            if (left < size && greater(left, left+1)) smaller++;
            if (!greater(pos, smaller)) return;
            swap(pos, smaller);
            goToRightPlace(smaller, -1);
            /**
            int leftChildIndex = leftChildIndex(pos);
            if (leftChildIndex > size) return; // reached bottom
            PQNode<T> leftChild = array.get(leftChildIndex);
            int rightChildIndex = rightChildIndex(pos);
            if (rightChildIndex > size) { // reached bottom
                if (me.priority > leftChild.priority) {
                    swap(pos, leftChildIndex);
                    goToRightPlace(leftChildIndex, -1); // recursive call
                }
            }
            else {
                PQNode<T> rightChild = array.get(rightChildIndex);
                if (leftChild.priority <= rightChild.priority) {
                    if (me.priority > leftChild.priority) {
                        swap(pos, leftChildIndex);
                        goToRightPlace(leftChildIndex, -1); // recursive call
                    } else {
                        if (me.priority > rightChild.priority) {
                            swap(pos, rightChildIndex);
                            goToRightPlace(rightChildIndex, -1); // recursive call
                        }
                    }
                } else {
                    if (me.priority > rightChild.priority) {
                        swap(pos, rightChildIndex);
                        goToRightPlace(rightChildIndex, -1); // recursive call
                    } else {
                        if (me.priority > leftChild.priority) {
                            swap(pos, leftChildIndex);
                            goToRightPlace(leftChildIndex, -1); // recursive call
                        }
                    }
                }
            }
             */


        } else {
            int parentIndex = parentIndex(pos);
            if (parentIndex == 0) goToRightPlace(pos, -1);  // reached root, turn around!
            PQNode<T> parent = array.get(parentIndex);
            if (parent.priority <= me.priority) {
                goToRightPlace(pos, -1); // reached upper limit, turn around!
            } else {
                goToRightPlace(pos, 1);
            }
        }
    }
    void print() {
        int jump = 0;
        for (PQNode<T> node : array) {
            if (jump == 0) {
                jump += 1;
                continue;
            }
            System.out.println(node.key+" "+node.priority);
        }
        System.out.println();
    }
}
