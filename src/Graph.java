import java.util.*;

public class Graph {

  private int n;
  private int dim;
  private float [][] weights; 
  private float [][] coords;
  
  public Graph (int n, int dim) {
    this.n = n;
    this.dim = dim; 
    weights = new float [n][n];
    Random rand = new Random();

    if (dim == 0) {
      for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
          float w = rand.nextFloat();
          weights[i][j] = w;
          weights[j][i] = w;
        }
      }
    }
    else {
      coords = new float [n][dim];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < dim; j++)
          coords[i][j] = rand.nextFloat();
      }

      for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
          float w = 0;
          for (int k = 0; k < dim; k++) {
            float d = coords[i][k] - coords[j][k];
            w += d*d;
          }
          weights[i][j] = (float) (Math.sqrt(w));
          weights[j][i] = (float) (Math.sqrt(w));
        }
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
        if (weights[current][j] < dist[j]) 
          dist[j] = weights[current][j];
      }

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
    for (int i = 0; i < n; i++) 
      s += Arrays.toString(weights[i]) + "\n";
    return s;
  }
}
