import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   
   @Test public void kminTest() {
      int[] b = {9, 7, 5, 3, 1};
      int k = 5;
      int[] v = {-4, -4};
      
      // Selector.kmin(b, 3);
      
      //Assert.assertEquals(1, Selector.kmin(b, 1));
      //Assert.assertEquals(2, Selector.kmin(b, 2));
      Assert.assertEquals(9, Selector.kmin(b, k));
   }
   
   @Test public void kmaxTest() {
      int[] b = {5, 7};
      // Selector.kmin(b, 3);
      //Assert.assertEquals(9, Selector.kmax(b, 1));
      //Assert.assertEquals(8, Selector.kmax(b, 2));
      //Assert.assertEquals(7, Selector.kmax(b, 3));
      Assert.assertEquals(7, Selector.kmax(b, 1));
   }
   
   @Test public void rangeTest() {
      int[] b = {1,2,3,4,5,6,7,8,9};
      int[] a = {3,4,5,6};
      int[] l = {7,5};
      int[] ll = {5,7};
      assertArrayEquals(a, Selector.range(b, 3, 6));
      assertArrayEquals(ll, Selector.range(l, 3, 7));
   }
   /*@Test public void rangeTest2() {
      int[] b = {7};
      int[] a = {};
      assertArrayEquals(a, Selector.range(b, 3, 4));
   }
   */
   
   @Test public void ceilingTest() {
      int[] b = {1,3,9,4,9,6,5,8,9};
      int[] a = {1,2,3,6,7,8,9};
      int[] l = {7};
      int[] ll = {2,3,4,4,12,14};
      //Assert.assertEquals(6, Selector.ceiling(b, 6));
      //Assert.assertEquals(3, Selector.ceiling(b, 2));
      Assert.assertEquals(7, Selector.ceiling(l, 5));
   }
   @Test public void floorTest() {
      int[] b = {1,3,9,4,9,6,5,8,9};
      int[] a = {7};
      Assert.assertEquals(6, Selector.floor(b, 6));
      //Assert.assertEquals(3, Selector.floor(a, 7));
      Assert.assertEquals(7, Selector.floor(a, 11));
     
   }
   
   @Test public void 
}
