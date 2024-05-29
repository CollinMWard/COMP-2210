import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Collin Ward (cmw0147@auburn.edu)
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   /////////////////////////////////////////////////////////////////////////////
   // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
   // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
   // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
   // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
   // table with chaining).
   /////////////////////////////////////////////////////////////////////////////
   TreeSet<String> lexicon = new TreeSet<String>();
   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   // int wordCount = 0;
   public Doublets(InputStream in) {
      try {
         //////////////////////////////////////
         // INSTANTIATE lexicon OBJECT HERE  //
         //////////////////////////////////////
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            lexicon.add(str.toLowerCase());
            // why tf is word count off
            //wordCount++;
            /////////////////////////////////////////////////////////////
            // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
            /////////////////////////////////////////////////////////////
            if (s.hasNextLine()) {
               s.nextLine();
            } else {
               break;
            }
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENT(C2-G2-E2-S2-U2)*0.15ATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////
   
 /**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   public int getWordCount() {
      return lexicon.size();
      //return this.wordCount;
   }
   
    /**
    * Checks to see if the given string is a word.
    *
    * @param  str the string to check
    * @return     true if str is a word, false otherwise
    */
   public boolean isWord(String str) {
      return lexicon.contains(str.toLowerCase());
   }
   
   
     /**
    * Returns the Hamming distance between two strings, str1 and str2. The
    * Hamming distance between two strings of equal length is defined as the
    * number of positions at which the corresponding symbols are different. The
    * Hamming distance is undefined if the strings have different length, and
    * this method returns -1 in that case. See the following link for
    * reference: https://en.wikipedia.org/wiki/Hamming_distance
    *
    * @param  str1 the first string
    * @param  str2 the second string
    * @return      the Hamming distance between str1 and str2 if they are the
    *                  same length, -1 otherwise
    */
   public int getHammingDistance(String str1, String str2) {
      String lstr1 = str1.toLowerCase();
      String lstr2 = str2.toLowerCase();
      if (lstr1.length() != lstr2.length()) {
         return -1;
      }
      int ham = 0;
      for (int i = 0; i < str1.length(); i++) {
         if (lstr1.charAt(i) != lstr2.charAt(i)) {
            ham++;
         }
      }
      return ham;
   }
   
   /*
   stops comparing char if 2 are different
   */
   public boolean hamIsOne(String str1, String str2) {
      String lstr1 = str1.toLowerCase();
      String lstr2 = str2.toLowerCase();
      if (lstr1.length() != lstr2.length()) {
         return false;
      }
      int ham = 0;
      if (str1 == str2) {
         return false;   
      }
      for (int i = 0; i < str1.length(); i++) {
         if (lstr1.charAt(i) != lstr2.charAt(i)) {
            ham++;
         }
         if (ham == 2) {
            return false;
         }
      }
      
      return true;
   }      
   /**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param  word the given word
    * @return      the neighbors of the given word
    */
   public List<String> getNeighbors(String word) {
   // thread keeps getting suspended indefinitly ??
      String wack = word.toLowerCase();
      ArrayList<String> neighbors = new ArrayList<String>();
      if (!isWord(word.toLowerCase()) || lexicon.isEmpty()) {
         return neighbors;
      }
      Iterator<String> itr = lexicon.iterator();
      while (itr.hasNext()) {
         String temp = itr.next();
         
         // why is it faster with this?? makes no sense
         if (temp.length() == word.length()) {
            if (hamIsOne(word, temp)) {
               neighbors.add(temp);
            }
         }
      }
      neighbors.remove(word);
      return neighbors;
   }
   
   
    /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param  sequence the given sequence of strings
    * @return          true if the given sequence is a valid word ladder,
    *                       false otherwise
    */
   public boolean isWordLadder(List<String> sequence) {
      Iterator<String> itr = sequence.iterator();
      Iterator<String> itr2 = sequence.iterator();
      if (sequence.isEmpty()) {
         return false;
      }
      itr2.next();
      while (itr2.hasNext()) {
         String temp = itr.next();
         String tempTwo = itr2.next();
         if (!isWord(temp) || !isWord(tempTwo)) {
            return false;
         }
         if (!hamIsOne(temp, tempTwo)) {
            return false;
         }
      }
      return true;
   }
   
    /**
   * Returns a minimum-length word ladder from start to end. If multiple
   * minimum-length word ladders exist, no guarantee is made regarding which
   * one is returned. If no word ladder exists, this method returns an empty
   * list.
   *
   * Breadth-first search must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a minimum length word ladder from start to end
   */
   public List<String> getMinLadder(String startIn, String endIn) {
      String start = startIn.toLowerCase();
      String end = endIn.toLowerCase();
   // em is is just empty so i have something to return
      List<String> em = new ArrayList<String>();
      // this is what will be returned, order is fucked atm
      List<String> ladder = new ArrayList<String>();
      // back holds all my movements so i can somehow backtrack 
      ArrayList<String> back = new ArrayList<String>();
      if (!isWord(start) || !isWord(end)) {
         return em;
      }
      if (start == end) {
      // not sure if start should be included or not
         ladder.add(start);
         return ladder;
      }
    // at this point start and end must be valid and not equal, just code the bit for searching
      // make a search method and use here
      back = search(start, end);
      
     
      // reverse the order so that its in correct order and not backwords
     // for (int i = 0; i < back.size(); i++) {
     //  for (int i = back.size(); i > -1; i--) {
     // somthings wrong here
      for (int i = back.size() - 1; i > -1; i--) {
         ladder.add(back.get(i));
      }
      return ladder;
   
   }
   
   
   private ArrayList<String> search(String start, String end) {
      Deque<Node> queue = new ArrayDeque<Node>();
    
      ArrayList<String> backPath = new ArrayList<String>();
      HashSet<String> visited = new HashSet<String>();
      if (start.length() != end.length()) {
         return backPath;
      }
      // someone said they used a hashSet??
     //  ArrayList<String> visited = new ArrayList<String>();
      visited.add(start);
      Node tempLast = new Node(start, null);
      queue.addLast(tempLast);
      Node last = new Node(end, null);
      boolean found = false;
   // https://www.baeldung.com/java-breadth-first-search but with nodes?
      while (!queue.isEmpty() && !found) {
      
         Node n = queue.remove();
         // String temp = queue.remove();
         String temp = n.element;
         // this call seems to be messing up
         List<String> neighbors = getNeighbors(temp);
         Iterator<String> itr = neighbors.iterator();
         while (itr.hasNext()) {
            String neighbor = itr.next();
            // if one of the neighbors has already been checked
            if (!visited.contains(neighbor)) {
               visited.add(neighbor);
               Node q = new Node(neighbor, n);
               
               queue.addLast(q);
              // if (end.compareTo(neighbor)) {
               if (neighbor.equals(end)) {
                  last.prev = n;
                  found = true;
                  break;
               }
            }
         }
      }
      if (last.prev != null) {
         Node p = last;
         while (p != null) {
            backPath.add(p.element);
            p = p.prev;
         }
         return backPath;
      } else {
         return backPath;
      }
   }
   
   /**
* Node class
* get from last assighnment n mod.
*/
// should it only be one way?
   private class Node {
      String element;
      Node prev;
   
      public Node(String elementIn, Node prevIn) {
         element = elementIn;
         prev = prevIn;
      }
   }

}
