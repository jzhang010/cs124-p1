import java.util.*;

public class Graph {

  private int n;
  private int dim;
  private float [][] coords;
  Random rand = new Random();
  
  public Graph (int n, int dim) {
    this.n = n;
    this.dim = dim; 

    // Calculate coordinates for cases where dimensions >1
    if (dim > 0) {
      coords = new float [n][dim];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < dim; j++)
          coords[i][j] = rand.nextFloat();
      }
    }
  }

  // Returns weight of minimum spanning tree
  public float primWeight () {
    // Start building MST at vertex 0 
    int current = 0;
    float totalWeight = 0;

    // Stores distance of each vertex to MST 
    float [] dist = new float [n];
    for (int i = 1; i < n; i++) 
      dist[i] = 10;

    boolean [] visited = new boolean [n];
    for (int i = 0; i < n - 1; i++) {
      visited[current] = true;
      
      // Explores each edge coming out of current vertex to unexplored vertices
      for (int j = 1; j < n; j++) {
        if (visited[j])
          continue;

        // Finds weight of edge - randomly generates for dim 0, calculates dist otherwise 
        float w = 0;
        if (dim == 0) 
          w = rand.nextFloat();
        else {
          for (int k = 0; k < dim; k++) {
            float d = coords[current][k] - coords[j][k];
            w += d*d;
          }
          w = (float) (Math.sqrt(w));
        }
        //System.out.println(w + " " + current + " " + j);
        
        // Updates distance of vertex to MST 
        if (w < dist[j]) 
          dist[j] = w;
      }
      //System.out.println(Arrays.toString(dist));

      // Finds the vertex that is not visited and closest to the MST 
      float minDist = 10;
      int next = -1;
      for (int j = 0; j < n; j++) {
        if (!visited[j] && dist[j] < minDist) {
          minDist = dist[j];
          next = j;
        }
      }

      // Adds the weight of the closest unexplored vertex and explores that next
      totalWeight += minDist;
      current = next; 
    }
    return totalWeight;
  }

  // toString method for testing purposes s
  public String toString () {
    String s = "";
    if (dim > 0) {
      for (int i = 0; i < n; i++) 
        s += Arrays.toString(coords[i]) + "\n";
      s += "\n";
    }

    return s;
  }
}
