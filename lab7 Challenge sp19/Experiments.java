/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        BST<Integer> bst = new BST();
        for (int i = 0; i < 5000; i++) {
            bst.add(RandomGenerator.getRandomInt(3789138));
        }
        System.out.println(bst.getAverageDepth());
        System.out.println(ExperimentHelper.optimalAverageDepth(5000));
    }

    public static void experiment2() {
        BST<Integer> bst = new BST();
        ExperimentHelper.randomInsert(bst, 5000);
        System.out.println(bst.getAverageDepth());
        for (int i = 0; i < 1000000; i++) {
            ExperimentHelper.fixedDelete(bst);
            ExperimentHelper.randomInsert(bst);
            if (i % 1000 == 0) System.out.println(bst.getAverageDepth());
        }
    }

    public static void experiment3() {
        BST<Integer> bst = new BST();
        ExperimentHelper.randomInsert(bst, 5000);
        System.out.println(bst.getAverageDepth());
        for (int i = 0; i < 100000; i++) {
            ExperimentHelper.randomDelete(bst);
            ExperimentHelper.randomInsert(bst);
            if (i % 1000 == 0) System.out.println(bst.getAverageDepth());
        }
    }

    public static void main(String[] args) {
        experiment3();
    }
}

