import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SummationTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   @Test public void test1() {
      int[] a = new int[4];
      a[0] = 2;
      a[1] = 4;
      a[2] = 7;
      a[3] = 2;
   
      int i = Summation.sum(a,1,3);
      Assert.assertEquals(13,i);
   }
   
   @Test public void test2() {
      int[] a = new int[4];
      a[0] = 2;
      a[1] = -4;
      a[2] = 7;
      a[3] = 2;
   
      int i = Summation.sum(a,1,3);
      Assert.assertEquals(5,i);
   }
   
   @Test public void test3() {
      int[] a = new int[4];
      a[0] = 2;
      a[1] = 0;
      a[2] = 0;
      a[3] = 2;
   
      int i = Summation.sum(a,1,3);
      Assert.assertEquals(2,i);
   }
   
   @Test public void test4() {
      int[] a = new int[5];
      a[0] = 1;
      a[1] = 2;
      a[2] = 3;
      a[3] = 4;
      a[4] = 5;
   
      int i = Summation.sum(a,1,3);
      Assert.assertEquals(9,i);
   }

   @Test public void test5() {
      int[] a = new int[1];
      a[0] = 1;
   
      int i = Summation.sum(a,0,0);
      Assert.assertEquals(1,i);
   }
   @Test public void test6() {
      int[] a = new int[2];
      a[0] = 1;
      a[1] = 2;
   
      int i = Summation.sum(a,0,1);
      Assert.assertEquals(3,i);
   }
   @Test public void test7() {
      int[] a = new int[5];
      a[0] = 1;
      a[1] = 3;
      a[2] = 5;
      a[3] = 7;
      a[4] = 9;
   
      int i = Summation.sum(a,0,4);
      Assert.assertEquals(25,i);
   }
}
