import java.util.*;

public class Graph {

  private int n;
  private int dim;
  private float [][] coords;
  Random rand = new Random();
  
  public Graph (int n, int dim) {
    this.n = n;
    this.dim = dim; 

    if (dim > 0) {
      coords = new float [n][dim];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < dim; j++)
          coords[i][j] = rand.nextFloat();
      }
    }
  }

  public float primWeight () {
    int current = 0;
    float totalWeight = 0;

    float [] dist = new float [n];
    for (int i = 1; i < n; i++) {
      dist[i] = 10;
    }

    boolean [] visited = new boolean [n];
    for (int i = 0; i < n - 1; i++) {
      visited[current] = true;
      
      for (int j = 1; j < n; j++) {
        if (visited[j])
          continue;
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
        if (w < dist[j]) 
          dist[j] = w;
      }
      //System.out.println(Arrays.toString(dist));

      float minDist = 10;
      int next = -1;
      for (int j = 0; j < n; j++) {
        if (!visited[j] && dist[j] < minDist) {
          minDist = dist[j];
          next = j;
        }
      }

      totalWeight += minDist;
      current = next; 
    }
    return totalWeight;
  }

  public String toString () {
    String s = "";
    if (dim > 0) {
      for (int i = 0; i < n; i++) 
        s += Arrays.toString(coords[i]) + "\n";
      s += "\n";
    }
    // for (int i = 0; i < n; i++) 
    //   s += Arrays.toString(weights[i]) + "\n";
    return s;
  }
}
