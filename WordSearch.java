import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Scanner;
import java.io.File;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Defines the methods needed to play a word search game.
 *
 */
public class WordSearch implements WordSearchGame {

   TreeSet test = new TreeSet<String>();
   boolean loadLexiconCalled = false;
   String[][] arrayBoard;
   int size = 0;
   Depth d1;
   int middle;
   TreeSet allScorableWords = new TreeSet<String>();
  

   
   
   /**
    * Loads the lexicon into a data structure for later use. 
    * 
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
   @SuppressWarnings("unchecked")
   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      Scanner scan1;
      File file = new File(fileName);
      try {
         scan1 = new Scanner(file);
         while (scan1.hasNext()) {
            String readIn = scan1.next().toUpperCase();
            test.add(readIn);
         }
      } catch (Exception e) {
         throw new IllegalArgumentException();
      }
      scan1.close();
      loadLexiconCalled = true;
   } /*
      if(fileName == null) {
         throw new IllegalArgumentException();
      }
      File file = new File(fileName);
      Scanner s = null;
      try {
         s = new Scanner(file);
         while(s.hasNext()) {
            test.add(s.next().toLowerCase());
         }
      } catch (Exception e) {
         throw new IllegalArgumentException();
      } finally {
         if(s != null) {
            s.close();
         }
      
      }
   }*/
   
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
   public void setBoard(String[] letterArray) {
      if (letterArray == null || Math.sqrt(letterArray.length) % 1 != 0) {
         throw new IllegalArgumentException();
      }
      size = (int) Math.sqrt(letterArray.length);
      arrayBoard = new String[size][size];
     
      d1 = new Depth(letterArray, size);
      middle = size/2;
    
   }
   
   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    */
   public String getBoard() {
      String boardString = "";
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            boardString += d1.grid[i][j].getE() + " ";
         }
         boardString += "\n";
      }
      return boardString;
   }
   
   /**
    * Retrieves all scorable words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   @SuppressWarnings("unchecked")
   public SortedSet<String> getAllScorableWords(int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (!loadLexiconCalled) {
         throw new IllegalStateException();
      }
      TreeSet allScorableBySize = new TreeSet<String>();
      Iterator<String> itr = allScorableWords.iterator();
      while (itr.hasNext()) {
         String comp = itr.next();
         if (comp.length() >= minimumWordLength) {
            allScorableBySize.add(comp);
         }
      }
      return allScorableBySize;
   }
   
 /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */  
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (!loadLexiconCalled) {
         throw new IllegalStateException();
      }
      Iterator<String> itr = words.iterator();
      int score = 0;
      while(itr.hasNext()) {
         String temp = itr.next();
         if(temp.length() >= minimumWordLength && isValidWord(temp) && isOnBoard(temp).size() != 0) {
            score += 1;
            if(temp.length() > minimumWordLength) {
               score += temp.length() - minimumWordLength;
            }
         }
      }
      return score;
   }
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (!loadLexiconCalled) {
         throw new IllegalStateException();
      }
      return (test.contains(wordToCheck.toUpperCase()));
       
   }
   
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   @SuppressWarnings("unchecked")
   public boolean isValidPrefix(String prefixToCheck) {
      if (prefixToCheck.equals(null)) {
         throw new IllegalArgumentException();
      }
      if (!loadLexiconCalled) {
         throw new IllegalStateException();
      }
      boolean out = false;
      Iterator<String> itr = test.iterator();
      while (itr.hasNext() && !out) {
         //String oop = itr.next().substring(0, prefixToCheck.length() - 1);
         out = itr.next().toUpperCase().startsWith(prefixToCheck.toUpperCase());
        
         /*if (oop.toUpperCase() == prefixToCheck.toUpperCase()) {
            return true; }
            */
         
      }
      return out;
      
   }
       
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   @SuppressWarnings("unchecked")
   public List<Integer> isOnBoard(String wordToCheck) {
      ArrayList<Integer> onBoardTemp = new ArrayList<Integer>();
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (!loadLexiconCalled) {
         throw new IllegalStateException();
      }
   // fucling shit fuck damn
      if (!allScorableWords.contains(wordToCheck.toUpperCase())) {
         return onBoardTemp;
      }
      onBoardTemp = d1.depthActive(middle, middle, wordToCheck.toUpperCase());
     // new method needed?
      
      return onBoardTemp;
   }
   
   
   
   // Search class
   
   
   public class Depth {
   // 2d area to traverse
      public gameE[][] grid;
      public String stringOut;
   // visited positions in the grid
      public boolean[][] visited;
   // dimensions of the grid
      public int numRows;
      public int numCols;
      ArrayList<Integer> onBoard = new ArrayList<Integer>();
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
      
      public ArrayList depthActive (int x, int y, String wordToCheck) {
         markAllUnvisited();
         Position start = new Position(x, y);
         if (isValid(start)) {
            order = 1;
            return dfsIterativej(start, wordToCheck);
         }
         return null;
      } 
      
      public ArrayList dfsIterativej(Position start, String wordToCheck) {
         Deque<Position> stack = new ArrayDeque<>();
         visit(start);
         process2(start);
         stack.addFirst(start);
         while (!stack.isEmpty()) {
            Position position = stack.peekFirst();
            Position neighbor = getFirstUnvisited(position.neighbors());
            if (neighbor != null && isValidPrefix(stringOut)) {
            // add int to board in process?
               stack.addFirst(neighbor);
               visit(neighbor);
               process2(neighbor);
            // check if string is a word?
            }
            else {
            // remove int from onBoard
               stack.removeFirst();
               removeP(neighbor);
            }
            
         }
         return onBoard;
      }
   
   
      
      public void dfsIterativeB(Position start) {
         Deque<Position> stack = new ArrayDeque<>();
         visit(start);
         process(start);
         stack.addFirst(start);
         while (!stack.isEmpty()) {
            Position position = stack.peekFirst();
            Position neighbor = getFirstUnvisited(position.neighbors());
            if (neighbor != null && isValidPrefix(stringOut)) {
            // add int to board in process?
               stack.addFirst(neighbor);
               visit(neighbor);
               process(neighbor);
            // check if string is a word?
            }
            else {
            // remove int from onBoard
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
   
      public void removeP(Position p) {
         onBoard.remove(grid[p.x][p.y].index);
      }
      @SuppressWarnings("unchecked")
      public void process(Position p) {
         stringOut += grid[p.x][p.y].getE();
         grid[p.x][p.y].orderSearched = order++;
         if (isValidWord(stringOut.toUpperCase())) {
            allScorableWords.add(stringOut.toUpperCase());
         }
      }
      public void process2(Position p) {
         stringOut += grid[p.x][p.y].getE();
         stringOut.toUpperCase();
         grid[p.x][p.y].orderSearched = order++;
         onBoard.add(grid[p.x][p.y].index);
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

}
