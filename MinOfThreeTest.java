import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class MinOfThreeTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
 
   @Test public void min1Test1() {
      int a = 3;
      int b = 4;
      int c = 9;
      Assert.assertEquals(3, MinOfThree.min1(a,b,c));
   }
   @Test public void min1Test2() {
      int a = -3;
      int b = -3;
      int c = 4;
      Assert.assertEquals(-3, MinOfThree.min1(a,b,c));
   }
   @Test public void min1Test3() {
      int a = 1;
      int b = 1;
      int c = 1;
      Assert.assertEquals(1, MinOfThree.min1(a,b,c));
   }
   
   @Test public void min2Test1() {
      int a = -123;
      int b = 0;
      int c = 9;
      Assert.assertEquals(-123, MinOfThree.min1(a,b,c));
   }
   @Test public void min2Test2() {
      int a = -3;
      int b = -9;
      int c = -9;
      Assert.assertEquals(-9, MinOfThree.min1(a,b,c));
   }
   @Test public void min2Test3() {
      int a = 1;
      int b = -1;
      int c = 1;
      Assert.assertEquals(-1, MinOfThree.min1(a,b,c));
   }
}
