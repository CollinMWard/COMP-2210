import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Collin Ward (cmw0147@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  1/18/23
*
*/
public final class Selector {

    /**
     * Can't instantiate this class.
     *
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     *
     */
   private Selector() { }


    /**
     * Selects the minimum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int min = a[0];
      for (int i = 1; i < a.length; i++) {
         if (a[i] < min) {
            min = a[i];
         }
      }
      return min;
   }


    /**
     * Selects the maximum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int max = a[0];
      for (int i = 1; i < a.length; i++) {
         if (a[i] > max) {
            max = a[i];
         }
      }
   
      return max;
   }


    /**
     * Selects the kth minimum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth minimum value. Note that there is no kth
     * minimum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
   public static int kmin(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      int ucounter = 0;
      int[] b = new int[a.length];
      for (int t = 0; t < a.length; t++) {
         b[t] = a[t];
      }
      Arrays.sort(b);
      int[] c = new int[b.length];
      int m = 0;
      
      for (int i = 0; i < b.length - 1; i++) {
         if (b[i] != b[i + 1]) {
            c[m++] = b[i];
         } else {
            ucounter++;
         }
      }
      c[m++] = b[b.length - 1];
   
      int[] d = new int[b.length - ucounter];
      if (d.length < k) {
         throw new IllegalArgumentException();
      }
      for (int t = 0; t < d.length; t++) {
         d[t] = c[t];
      }
      return d[k - 1];
   }


    /**
     * Selects the kth maximum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth maximum value. Note that there is no kth
     * maximum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      int ucounter = 0;
      int[] b = new int[a.length];
      for (int t = 0; t < a.length; t++) {
         b[t] = a[t];
      }
      Arrays.sort(b);
      int[] c = new int[b.length];
      int m = 0;
      for (int i = 0; i < b.length - 1; i++) {
         if (b[i] != b[i + 1]) {
            c[m++] = b[i];
         } else {
            ucounter++;
         }
      }
      c[m++] = b[b.length - 1];
      
      int[] d = new int[b.length - ucounter];
      if (d.length < k) {
         throw new IllegalArgumentException();
      }
      for (int t = 0; t < d.length; t++) {
         d[t] = c[t];
      }
      return d[d.length - k];
   }


    /**
     * Returns an array containing all the values in a in the
     * range [low..high]; that is, all the values that are greater
     * than or equal to low and less than or equal to high,
     * including duplicate values. The length of the returned array
     * is the same as the number of values in the range [low..high].
     * If there are no qualifying values, this method returns a
     * zero-length array. Note that low and high do not have
     * to be actual values in a. This method throws an
     * IllegalArgumentException if a is null or has zero length.
     * The array a is not changed by this method.
     */
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int[] b = new int[a.length];
      int temp = 0;
      int lowIndex = -1;
      int highIndex = -1;
      for (int t = 0; t < a.length; t++) {
         b[t] = a[t];
      }
      // sorts in ascending order
      for (int j = 0; j < b.length; j++) {
         for (int i = 0; i < b.length; i++) {
            if (b[j] < b[i]) {
               temp = b[j];
               b[j] = b[i];
               b[i] = temp;
            }
         }
      }
      
      for (int j = a.length - 1; j >= 0 ; j--) {
         if (b[j] >= low) {
            lowIndex = j;
         }
      }
      for (int i = 0; i < a.length; i++) {
         if (b[i] <= high && i >= highIndex) {
            highIndex = i;
         }
      }
      
      if (lowIndex == -1 || highIndex == -1) {
         int[] c = new int[0];
         return c;
      }
      
      int cLength = highIndex - lowIndex + 1;
      int[] c = new int[cLength];
    
      
      for (int i = 0; i <= highIndex && i < c.length; i++) {
         c[i] = b[lowIndex];
         lowIndex++;
      }
      
      
      /*for (int t = 0; t <= lowIndex; t++) {
         c[t] = b[t];
      }
      int j = c.length -1;
      for (int i = b.length - 1; i >= highIndex; i--) {
         c[j] = b[i];
         j--;
      }
      */
      
      return c;
   }


    /**
     * Returns the smallest value in a that is greater than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
   public static int ceiling(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int[] b = new int[a.length];
      int temp = 0;
      int solution = -1;
      int imStupid = 0;
      for (int t = 0; t < a.length; t++) {
         b[t] = a[t];
      }
      // sorts in ascending order
      for (int j = 0; j < b.length; j++) {
         for (int i = 0; i < b.length; i++) {
            if (b[j] < b[i]) {
               temp = b[j];
               b[j] = b[i];
               b[i] = temp;
            }
         }
      }
      for (int j = b.length -1; (j > -1) && (b[j] >= key); j--) {
         solution = b[j];
         imStupid++;
      }
      if (imStupid == 0) {
         throw new IllegalArgumentException();
      }
    
      return solution;
   }


    /**
     * Returns the largest value in a that is less than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
   public static int floor(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
   
      int[] b = new int[a.length];
      int temp = 0;
      int solution = -1;
      int imStupid = 0;
      for (int t = 0; t < a.length; t++) {
         b[t] = a[t];
      }
      // sorts in ascending order
      for (int j = 0; j < b.length; j++) {
         for (int i = 0; i < b.length; i++) {
            if (b[j] < b[i]) {
               temp = b[j];
               b[j] = b[i];
               b[i] = temp;
            }
         }
      }
      for (int j = 0; (j < b.length) && (b[j] <= key); j++) {
         solution = b[j];
         imStupid++;
      }
      if (imStupid == 0) {
         throw new IllegalArgumentException();
      }
      
      return solution;
   }
}
