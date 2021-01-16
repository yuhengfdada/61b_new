/**
 * Created by hug.
 */
import static java.lang.Math.*;

//import static java.lang.Math.pow;

public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        double a = 0;
        double exp = 0;
        double nodeSum = 0;
        double IPLSum = 0;
        while (nodeSum + pow(2, exp) <= N) {
            IPLSum += a * pow(2, exp);
            nodeSum += pow(2, exp);
            a += 1;
            exp += 1;
        }
        IPLSum += a * (N - nodeSum);
        return (int) IPLSum;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return optimalIPL(N) / Double.valueOf(N);
    }

    public static void randomInsert(BST bst) {
        bst.add(RandomGenerator.getRandomInt(3781378));
    }
    public static void randomInsert(BST bst, int N) {
        for (int i = 0; i < N; i++) {
            randomInsert(bst);
        }
    }
    public static void fixedDelete(BST bst) {
        if (bst.size() == 0) return;
        bst.deleteTakingSuccessor(bst.getRandomKey());
    }

    public static void randomDelete(BST bst) {
        if (bst.size() == 0) return;
        bst.deleteTakingRandom(bst.getRandomKey());
    }
}
