public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Return the size of the list using... recursion! */
    public int size() {
        return 0;
    }

    /** Return the size of the list using no recursion! */
    public int iterativeSize() {
        return 0;
    }

    /** Returns the ith value in this list.*/
    public int get(int i) {
        return 0;
    }

    public void print(){
        if (this!=null) {
            System.out.println(first);
            if (rest != null)
                rest.print();
        }
    }

}