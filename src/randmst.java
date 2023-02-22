public class randmst {
  
  public static void main(String[] args) {
    //long start = System.currentTimeMillis();

    // Initialize command line arguments 
    int numpoints = Integer.parseInt(args[1]);
    int numtrials = Integer.parseInt(args[2]);
    int dimension = Integer.parseInt(args[3]);

    float average = 0;
    for (int i = 0; i < numtrials; i++) {
      Graph g = new Graph(numpoints, dimension);
      average += g.primWeight();
      //System.out.print(g);
    }
    average /= numtrials;

    // Prints out the average weight with initial inputs 
    System.out.println(average + " " + numpoints + " " + numtrials + " " + dimension);

    // Prints out time for program to run
    //long end = System.currentTimeMillis();
    //System.out.println("Time: " + (end - start));
  }
}
