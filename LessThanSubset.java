import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Return the elements in a collection that are strictly less than a specified
 * value.
 *
 */
public class LessThanSubset {

   // C O M P L E T E   T H I S   M E T H O D 

   /**
    * Returns the elements in collection strictly less than value.
    */
   public static <T extends Comparable<T>> 
           Collection<T> lessThanSubset(Collection<T> collection, T value) {
      ArrayList<T> result = new ArrayList<>();
      
      Iterator<T> itr = collection.iterator();
      while (itr.hasNext()){
         T y = itr.next();
         if (y.compareTo(value) < 0) {
            result.add(y);
         }
      }
      return result;
   }

}
