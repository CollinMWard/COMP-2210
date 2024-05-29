/**
 * Implements shift-right behavior in an array.
 *
 */

public class ShiftRight {


    /**
     * Shifts the elements at a[index] through a[a.length - 2] one
     * position to the right. 
     */
   public static void shiftRight(Object[] array, int index) {
      /*Object[] arrayTwo = array;
      for ( int i = index; i < array.length - 1; i++) {
         array[i] = null;
         array[i++] = arrayTwo[i];
      }*/
      
      
      for (int i = array.length; i > index; i--) {
         array[i] = array[i - 1];
      }
      array[index] = null;
   }
   
   
  

}