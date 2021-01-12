public class ArrayDeque<T> {
    public T[] array;
    public int size;
    public int start;
    public int end;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        start = 7;
        end = 0;
    }

    private int minusOne(int start){
        if (start == 0) return array.length - 1;
        else return start - 1;
    }

    private int addOne(int start){
        if (start == array.length - 1) return 0;
        else return start + 1;
    }

    public void resize_increment(int newLength) {
        end = start + 1;
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(array, end, newArray, 0, array.length - end);
        System.arraycopy(array, 0, newArray, array.length - end, start + 1);
        array = newArray;
        start = newLength - 1;
        end = size;
    }

    public void addFirst(T item) {
        if (size == array.length) {
            resize_increment(array.length * 2);
        }
        array[start] = item;
        start = minusOne(start);
        size += 1;
    }

    public void addLast(T item) {
        if (size == array.length) {
            resize_increment(array.length * 2);
        }
        array[end] = item;
        end = addOne(end);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int p = addOne(start);
        while (p != end) {
            System.out.print(array[p]);
            System.out.print(" ");
            p = addOne(p);
        }
        if (size == array.length){  // special case
            System.out.print(array[p]);
            System.out.print(" ");
            p = addOne(p);
            while (p != end) {
                System.out.print(array[p]);
                System.out.print(" ");
                p = addOne(p);
            }
        }
    }

    public void resize_decrement(int newLength) {
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(array, start + 1, newArray, 0, array.length - start - 1);
        System.arraycopy(array, 0, newArray, array.length - start - 1, end);
        array = newArray;
        start = newLength - 1;
        end = size;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        start = addOne(start);
        T res = array[start];
        array[start] = null;
        size -= 1;
        if (array.length >= 16 && size / array.length < 0.25) {
            resize_decrement(array.length / 2);
        }
        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        end = minusOne(end);
        T res = array[end];
        array[end] = null;
        size -= 1;
        if (array.length >= 16 && size / array.length < 0.25) {
            resize_decrement(array.length / 2);
        }
        return res;
    }

    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        int p = start;
        while (index >= 0) {
            p = addOne(p);
            index -= 1;
        }
        return array[p];
    }
/** For test purposes */
    public int length() {
        return array.length;
    }
/**
    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque();
        //deque.addFirst(1);
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        for (int i = 0; i < 9; i++) {
            deque.addFirst(i);
        }
        //System.out.println(deque.removeFirst());
        //System.out.println(deque.removeLast());
        deque.printDeque();
        System.out.println();
        System.out.println(deque.length());
        System.out.println(deque.get(0));
        System.out.println(deque.get(-1));
        System.out.println(deque.get(8));
        for (int i = 0; i < 5; i++) {
            deque.removeLast();
        }
        System.out.println(deque.removeLast());
        deque.printDeque();
        System.out.println();
        System.out.println(deque.length());
        //System.out.println(deque.size);
    }
 */
}
