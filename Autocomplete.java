import java.util.Arrays;


/**
 * Autocomplete.
 */
public class Autocomplete {

   private Term[] terms;
   public Term[] termss;
	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
   public Autocomplete(Term[] terms) {
      if (terms == null) {
         throw new NullPointerException();
      }
    
      terms = terms;
      Arrays.sort(terms);
      termss = terms;
   
     
   }

	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
	 */
   public Term[] allMatches(String prefix) {
      
      if (prefix == null) {
         throw new NullPointerException();
      }
      Term t = new Term(prefix, 0);
      
      int start = BinarySearch.firstIndexOf(termss, t, Term.byPrefixOrder(prefix.length()));
      int end = BinarySearch.lastIndexOf(termss, t, Term.byPrefixOrder(prefix.length()));
      Term[] test = new Term[end - start + 1];
      for (int i = start; i <= end; i++) {
         test[i - start] = termss[i];
      }
      Arrays.sort(test, Term.byDescendingWeightOrder());
     
      return test;
   }

}

