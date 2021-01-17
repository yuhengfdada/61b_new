import java.util.ArrayList;
import java.util.PriorityQueue;
/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    ArrayList<Flight> flights; // sorted by ascending startTime.
    public FlightSolver(ArrayList<Flight> flights) {
        this.flights = new ArrayList<>(flights);
        this.flights.sort((f1, f2) -> f1.startTime() - f2.startTime());
    }

    public int solve() {
        int max = 0;
        int sum = 0;
        /* MinPQ, sorted by ascending endTime. */
        PriorityQueue<Flight> PQ = new PriorityQueue<>((f1, f2) -> f1.endTime() - f2.endTime());
        for (Flight f : flights) {
            while (true) {
                if (PQ.size() == 0 || f.startTime <= PQ.peek().endTime) {
                    sum += f.passengers();
                    PQ.add(f);
                    if (sum > max) max = sum;
                    break;
                } else {
                    Flight f2 = PQ.poll();
                    sum -= f2.passengers();
                }
            }
        }
        return max;
    }

}
