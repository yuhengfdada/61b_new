package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Integer> queue = new LinkedList<>();


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    private void bfs() {
        while (!queue.isEmpty()) {
            int v = queue.poll();
            marked[v] = true;
            announce();
            if (v == t) return;
            for (int w : maze.adj(v)) {
                if (marked[w]) continue;
                distTo[w] = distTo[v] + 1;
                edgeTo[w] = v;
                announce();
                queue.add(w);
            }
        }
    }

    @Override
    public void solve() {
        queue.add(s);
        bfs();
    }
}

