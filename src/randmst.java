public class randmst {
  
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    int numpoints = Integer.parseInt(args[1]);
    int numtrials = Integer.parseInt(args[2]);
    int dimension = Integer.parseInt(args[3]);

    float average = 0;
    for (int i = 0; i < numtrials; i++) {
      Graph g = new Graph(numpoints, dimension);
      //System.out.print(g);
      average += g.primWeight();
    }
    average /= numtrials;

    long end = System.currentTimeMillis();
    System.out.println(average + " " + numpoints + " " + numtrials + " " + dimension);
    System.out.println("Time: " + (end - start));
  }
}
