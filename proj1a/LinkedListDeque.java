public class LinkedListDeque<T> {
    private class DequeNode {
        public DequeNode prev;
        public T item;
        public DequeNode next;

        public DequeNode(DequeNode p, T i, DequeNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    private DequeNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new DequeNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        DequeNode nextOne = sentinel.next;
        DequeNode newNode = new DequeNode(sentinel, item, nextOne);
        nextOne.prev = newNode;
        sentinel.next = newNode;

        size += 1;
    }

    public void addLast(T item) {
        DequeNode lastOne = sentinel.prev;
        DequeNode newNode = new DequeNode(lastOne, item, sentinel);
        lastOne.next = newNode;
        sentinel.prev = newNode;

        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        DequeNode p = sentinel.next;
        while (!p.equals(sentinel)) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
    }

    public T removeFirst() {
        DequeNode toBeRemoved = sentinel.next;
        if (toBeRemoved.equals(sentinel)) return null;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return toBeRemoved.item;
    }

    public T removeLast() {
        DequeNode toBeRemoved = sentinel.prev;
        if (toBeRemoved.equals(sentinel)) return null;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return toBeRemoved.item;
    }

    public T get(int index) {
        DequeNode p = sentinel.next;
        int num = 0;
        while (!p.equals(sentinel)) {
            if (num == index) {
                return p.item;
            }
            p = p.next;
            num += 1;
        }
        return null;
    }

    private T getHelper(int index, DequeNode current) {
        if (current.equals(sentinel)) return null;
        if (index == 0) return current.item;
        return getHelper(index - 1, current.next);
    }

    public T getRecursive(int index) {
        return getHelper(index, sentinel.next);
    }
}
