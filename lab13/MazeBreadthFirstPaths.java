import java.util.Observable;
import java.util.Queue;
import java.util.PriorityQueue;
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
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, 
        int targetX, int targetY) {
        super(m);  
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);   
        distTo[s] = 0;
        edgeTo[s] = s; 
    }

    /** 
     * Conducts a breadth first search of the maze starting at vertex x. 
     * Credit to http://algs4.cs.princeton.edu/41undirected/BreadthFirstPaths.java.html 
     * For the reference help! */
    private void bfs(int s) {
        Queue<Integer> p = new PriorityQueue<Integer>();
        announce();


        for (int vertex = 0; vertex < maze.V(); vertex++) {
            // distTo[vertex] = Integer.MAX_VALUE;
            distTo[vertex] = 0;
            distTo[s] = 0;
            marked[s] = true;
            p.add(s);
        }

        while (p.size() != 0) {
            int v = p.poll();
            for (int vert : maze.adj(v)) {
                if (!marked[vert]) {
                    edgeTo[vert] = v;
                    announce();
                    distTo[vert] = distTo[v] + 1;
                    marked[vert] = true;
                    p.add(vert);
                    if (v == t) {
                        return;
                    }
                }
            }

        }
    }


    @Override
    public void solve() {
        bfs(s);
    }
} 

