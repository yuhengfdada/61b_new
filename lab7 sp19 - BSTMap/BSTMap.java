import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private static class BSTNode<K, V> {
        /** Some implementations put size inside BSTNode class, but it's unnecessary. */
        BSTNode<K, V> left;
        BSTNode<K, V> right;
        K key;
        V value;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(BSTNode<K, V> node) {
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }

        public void copyLeftRight(BSTNode<K, V> node) {
            this.left = node.left;
            this.right = node.right;
        }

        public void copyAll(BSTNode<K, V> node) {
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }
    }

    BSTNode<K, V> root;
    int size;
    boolean notFound;
    V removeVar;

    public BSTMap() {
        size = 0;
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    private boolean containsKeyHelper(K key, BSTNode<K, V> node) {
        if (node == null) {
            return false;
        } else if (key.compareTo(node.key) < 0) {
            return containsKeyHelper(key, node.left);
        } else if (key.compareTo(node.key) > 0) {
            return containsKeyHelper(key, node.right);
        } else {
            return true;
        }
    }
    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(key, root);
    }

    private V getHelper(K key, BSTNode<K, V> node) {
        if (node == null) {
            return null;
        } else if (key.compareTo(node.key) < 0) {
            return getHelper(key, node.left);
        } else if (key.compareTo(node.key) > 0) {
            return getHelper(key, node.right);
        } else {
            return node.value;
        }
    }
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        // if (!containsKey(key)) return null;  // This line is actually slow.
        return getHelper(key, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Unnecessary. */
    /**
    private BSTNode<K, V> findNode(K key, BSTNode<K, V> node) {
        if (key.compareTo(node.key) < 0) {
            return findNode(key, node.left);
        } else if (key.compareTo(node.key) > 0) {
            return findNode(key, node.right);
        } else {
            return node;
        }
    }
     */

    private BSTNode<K, V> insert(K key, BSTNode<K, V> node, V value) {
        if (node == null) {
            return new BSTNode<K, V>(key, value);
        } else if (key.compareTo(node.key) < 0) {
            node.left = insert(key, node.left, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = insert(key, node.right, value);
        } else {
            node.value = value; // Handles the already-in-BST case.
        }
        return node;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        /* Unnecessarily goes through the BST twice. */
        /**
        if (containsKey(key)) {
            findNode(key, root).value = value;
        } else {
            root = insert(key, root, value);
        }
         */

        /* Halves insertion time. */
        root = insert(key, root, value);
        size += 1;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    private BSTNode<K, V> removeHelper(K key, BSTNode<K, V> node) {
        notFound = false;
        if (node == null) { // Not found. Need some global variable to record the result.
            notFound = true;
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeHelper(key, node.left);
        } else if (cmp > 0) {
            node.right = removeHelper(key, node.right);
        } else { // node is toBeRemoved
            V res = node.value;
            removeVar = res;
            // 0 to 1 child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // 2 children
            /* Find the min value in the right branch */
            BSTNode<K, V> minRight = findMin(node.right);
            node.right = removeHelper(minRight.key, node.right); // minRight = null
            minRight.copyLeftRight(node);
            node = minRight;
            removeVar = res;
        }
        return node;
    }

    /* Finds the node with minimum value in the BST rooted at ROOT. */
    private BSTNode<K, V> findMin(BSTNode<K, V> root) {
        if (root.left == null) {
            return root;
        }
        return findMin(root.left);
    }

    private void printInOrder(BSTNode n) {
        if (n == null) {
            return;
        }
        printInOrder(n.left);
        System.out.println("key: " + n.key + " value: " + n.value);
        printInOrder(n.right);
    }
    public void printInOrder() {
        printInOrder(root);
    }

    /* Return the parent of node. If node is root, return null. */
    /**
    private BSTNode<K, V> parent(K key, BSTNode<K, V> node, BSTNode<K, V> prev) {
    }
     */

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        /* Idea 1: Find the node to be removed, then perform removal.
        *  But it's in efficient since we need to modify the parent as well. */
        /**
        BSTNode<K, V> toBeRemoved = findNode(key, root);
        if (toBeRemoved == null) {
            return null;
        }
        V res = toBeRemoved.value;
        BSTNode<K, V> left = toBeRemoved.left;
        BSTNode<K, V> right = toBeRemoved.right;
        boolean leftNull = left == null;
        boolean rightNull = right == null;
        if (leftNull && rightNull) { // 0 child
            // toBeRemoved = null; // Has bug here. Can't manually destroy an Object in Java.
            // A working (but not efficient) approach is finding its parent and set the pointer to null.
            // BSTNode<K, V> parent = parent(toBeRemoved.key, root);

        } else if (!leftNull && !rightNull) { // 2 children
            // Find the min value in the right branch
            BSTNode<K, V> minRight = findMin(right);
            toBeRemoved.copyKeyValues(minRight);

            if (minRight.right != null) {
                minRight.copyAll(minRight.right);
            } else {
                minRight = null; // Has bug here. Can't manually destroy an Object in Java.
            }

        } else { // 1 child
            if (rightNull) {
                toBeRemoved.copyAll(left);
            } else {
                toBeRemoved.copyAll(right);
            }
        }
        */
        root = removeHelper(key, root);
        if (notFound) return null;
        size -= 1;
        return removeVar;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
