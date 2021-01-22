/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        String[] haha = new String[asciis.length];
        int maxLength = 0;
        for (int i = 0; i < asciis.length; i++) {
            haha[i] = asciis[i];
            maxLength = Math.max(asciis[i].length(), maxLength);
        }
        for (int i = maxLength - 1; i >= 0; i--) {
            haha = sortHelperLSD(haha, i);
        }
        return haha;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] arr = new int[asciis.length];
        // Extract that char at INDEX to ARR[]
        for (int i = 0; i < asciis.length; i++) {
            if (asciis[i].length() - 1 < index) arr[i] = -1;
            else arr[i] = asciis[i].charAt(index);
        }

        // The frequency of -1 corresponds to count[0], etc.
        int[] counts = new int[257];
        for (int i : arr) {
            counts[i + 1]++;
        }

        int[] starts = new int[257];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted2 = new String[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            String corres = asciis[i];
            int place = starts[item + 1];
            sorted2[place] = corres;
            starts[item + 1] += 1;
        }

        // return the sorted array
        return sorted2;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
