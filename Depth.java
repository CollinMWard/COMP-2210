import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList;

/**
 * Provides sample implementations of breadth-first and depth-first
 * search in the context of a two-dimensional grid. Reference descriptions
 * of these two search methods are available at:
 *
 * https://en.wikipedia.org/wiki/Depth-first_search
 * https://en.wikipedia.org/wiki/Breadth-first_search
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2019-03-06
 *
 */
public class Depth {

   ArrayList<Integer> onBoard = new ArrayList<Integer>();
   // 2d area to traverse
   public gameE[][] grid;
   public String stringOut;
   // visited positions in the grid
   public boolean[][] visited;
   public boolean validPrefix;
   // dimensions of the grid
   public int numRows;
   public int numCols;

   // number of neighbors, degrees of motion
   public final int MAX_NEIGHBORS = 8;

   // order in which positions are visited.
   // used to enumerate positions in the grid.
   public int order;

   /**
    * Construct a new Depth object initialized
    * with the given grid specification.
    */
   public Depth(String[] grd, int sizeIn) {
    // initialize grid dimensions
      numRows = sizeIn;
      numCols = numRows;
      // initialize grid with values in grd
      grid = new gameE[numRows][numCols];
      int f = 0;
      while (f < grd.length) {
         for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
               grid[i][j] = new gameE(i,j, grd[f], f);
               f++;
            }
         }
      }
      // initialize visited to false
      markAllUnvisited();
   
   }
         
   
      
   /**
    * Initializes visited to false.
    */
   public void markAllUnvisited() {
      visited = new boolean[numRows][numCols];
      for (boolean[] row : visited) {
         Arrays.fill(row, false);
      }
   
   }
   
   public void depthFirstB(int x, int y) {
      markAllUnvisited();
      Position start = new Position(x, y);
      if (isValid(start)) {
         order = 1;
         dfsIterativeB(start);
      }
   }

   
   public void dfsIterativeB(Position start) {
      Deque<Position> stack = new ArrayDeque<>();
      visit(start);
      process(start);
      stack.addFirst(start);
      while (!stack.isEmpty()) {
         Position position = stack.peekFirst();
         Position neighbor = getFirstUnvisited(position.neighbors());
         if (neighbor != null && validPrefix == true) {
            stack.addFirst(neighbor);
            visit(neighbor);
            process(neighbor);
            // check if string is a word?
         }
         else {
            stack.removeFirst();
         }
      }
   }
   // on some bullshit
        
    /** Returns the first of the given positions that has not been visited. */
   public Position getFirstUnvisited(Position[] positions) {
      for (Position position : positions) {
         if (!isVisited(position)) {
            return position;
         }
      }
      return null;
   }
   
      
       ///////////////////////////////////////////
   // Position class and associated methods //
   ///////////////////////////////////////////

   /**
    * Models an (x,y) position in the grid.
    */
   public class Position {
      int x;
      int y;
   
      /** Constructs a Position with coordinates (x,y). */
             
      public Position(int x, int y) {
         this.x = x;
         this.y = y;
      }
   
      /** Returns a string representation of this Position. */
      @Override
      public String toString() {
         return "(" + x + ", " + y + ")";
      }
   
      /** Returns all the neighbors of this Position. */
      public Position[] neighbors() {
         Position[] nbrs = new Position[MAX_NEIGHBORS];
         int count = 0;
         Position p;
         // generate all eight neighbor positions
         // add to return value if valid
         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               if (!((i == 0) && (j == 0))) {
                  p = new Position(x + i, y + j);
                  if (isValid(p)) {
                     nbrs[count++] = p;
                  }
               }
            }
         }
         return Arrays.copyOf(nbrs, count);
      }
   }

   /**
    * Is this position valid in the search area?
    */
   public boolean isValid(Position p) {
      return (p.x >= 0) && (p.x < numRows) && 
            (p.y >= 0) && (p.y < numCols);
   }

   /**
    * Has this valid position been visited?
    */
   public boolean isVisited(Position p) {
      return visited[p.x][p.y];
   }
   

   /**
    * Mark this valid position as having been visited.
    */
   public void visit(Position p) {
      visited[p.x][p.y] = true;
   }


   public void process(Position p) {
      stringOut += grid[p.x][p.y].getE();
      grid[p.x][p.y].orderSearched = order++;
   }
   
   public class gameE {
      int x;
      int y;
      String element;
      int index;
      int orderSearched;
   
      /** Constructs a Position with coordinates (x,y). */
      public gameE(int x, int y, String elementIn, int indexIn) {
         this.x = x;
         this.y = y;
         this.element = elementIn;
         this.index = indexIn;
      }
      public String getE() {
         return this.element;
      }
      
   }

}