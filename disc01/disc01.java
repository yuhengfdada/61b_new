public class disc01 {
    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }

    public static int fib2(int n, int k, int f0, int f1) {
        /** n: target index  k: current index, initially 0  f0: fib(n-2), initially 0  f1: fib(n-1), initially 1*/
        if (n == k) { // If k increments to the target index, return.
            return f0;
        }
        return fib2(n, k + 1, f1, f0 + f1); // If k < n, compute the next fib number, which is fib(k+1).
    }
    public static void main(String[] args) {
        System.out.println(fib2(5, 0, 0, 1));
    }
}