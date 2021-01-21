package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        auxEdgeTo = new int[maze.V()];
        s = maze.xyTo1D(1, 1);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    private int s;
    private boolean cycleDetected = false;
    private boolean doneCycling = false;
    private Maze maze;
    private int[] auxEdgeTo;
    private int cycleStarter;

    private void dfs(int v) {
        marked[v] = true;
        announce();

        for (int w : maze.adj(v)) {
            if (marked[w] && w != auxEdgeTo[v]) {
                cycleStarter = w;
                edgeTo[w] = v;
                cycleDetected = true;
                return;
            }
            if (!marked[w]) {
                auxEdgeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                dfs(w);
                if (cycleDetected) {
                    if (w == cycleStarter){ doneCycling = true; return;}
                    if (doneCycling) return;
                    edgeTo[w] = v;
                    announce();
                    return;
                }
            }
        }
    }

    @Override
    public void solve() {
        dfs(s);
    }

    // Helper methods go here
}

