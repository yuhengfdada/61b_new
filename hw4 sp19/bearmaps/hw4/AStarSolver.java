package bearmaps.hw4;

import bearmaps.proj2ab.DoubleMapPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;

import java.util.*;


public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    AStarGraph<Vertex> input;
    Vertex start;
    Vertex end;
    ExtrinsicMinPQ<Vertex> PQ = new DoubleMapPQ<>();
    Map<Vertex, Double> distTo = new HashMap<>();
    Map<Vertex, Vertex> edgeTo = new HashMap<>();
    List<Vertex> solution = new ArrayList<>();
    double solutionWeight = 0;
    double timeoutInMillis;
    double timeSpent;
    int numStatesExplored = 0;
    SolverOutcome outcome;


    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        long startTime = System.currentTimeMillis();
        timeoutInMillis = timeout * 1000;

        this.input = input;
        this.start = start;
        this.end = end;

        distTo.put(start, 0.0);
        PQ.add(start, 0.0);

        while (true) {
            /** Termination cases. */
            timeSpent = System.currentTimeMillis() - startTime;
            if (timeSpent > timeoutInMillis) {
                outcome = SolverOutcome.TIMEOUT;
                break;
            }
            if (PQ.size() == 0) {
                outcome = SolverOutcome.UNSOLVABLE;
                break;
            }
            Vertex v = PQ.removeSmallest();
            if (v.equals(end)) {
                outcome = SolverOutcome.SOLVED;
                solutionWeight = distTo.get(end);
                solution.add(end);
                while (!end.equals(start)) {
                    end = edgeTo.get(end);
                    solution.add(end);
                }
                Collections.reverse(solution);
                break;
            }
            numStatesExplored++;
            /** Normal cases. */
            for (WeightedEdge<Vertex> e : input.neighbors(v)) {
                Vertex w = e.to();
                double weight = e.weight();
                checkAndUpdatePQ(v, weight, w);
            }
        }
    }

    private void checkAndUpdatePQ(Vertex v, double weight, Vertex w) {
        double h = input.estimatedDistanceToGoal(w, end);
        double newDistFromSource = distTo.get(v) + weight;
        double newPriority = distTo.get(v) + weight + h;

        if (!distTo.containsKey(w)) { // First visit
            PQ.add(w, newPriority);
        } else if (newDistFromSource < distTo.get(w)) {
            if (!PQ.contains(w)) PQ.add(w, newPriority);
            else PQ.changePriority(w, newPriority);
        } else return;
        distTo.put(w, newDistFromSource);
        edgeTo.put(w, v);
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }
    @Override
    public List<Vertex> solution() {
        return solution;
    }
    @Override
    public double solutionWeight() {
        return solutionWeight;
    }
    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }
    @Override
    //(System.currentTimeMillis() - start)
    public double explorationTime() {
        return timeSpent / 1000;
    }
}