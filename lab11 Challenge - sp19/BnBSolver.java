import java.util.ArrayList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    List<Bear> bears = new ArrayList<>();
    List<Bed> beds = new ArrayList<>();
    List<Bear> newBears = new ArrayList<>();
    List<Bed> pivots = new ArrayList<>();

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        this.bears = bears;
        this.beds = beds;
    }

    private void swap(int i, int j) {
        Bear temp = bears.get(j);
        bears.set(j, bears.get(i));
        bears.set(i, temp);
    }
    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        /** Solution 1: Selection sort. */
        /*
        for (int i = 0; i < beds.size(); i++) {
            for (int j = i; j < bears.size(); j++) {
                if (bears.get(j).compareTo(beds.get(i)) == 0) swap(i, j);
            }
        }
        return bears;
        */
        /** 1a. Improved Selection Sort. */
        /*
        List<Bear> res = new ArrayList<>();
        for (int i = 0; i < beds.size(); i++) {
            for (int j = 0; j < bears.size(); j++) {
                if (bears.get(j).compareTo(beds.get(i)) == 0) {
                    res.add(bears.get(j));
                    bears.remove(j);
                }
            }
        }
        return res;
        */

    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        return beds;
    }
}
