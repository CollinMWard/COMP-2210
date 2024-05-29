import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class BinarySearchTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
      Assert.assertEquals("Default test added by jGRASP. Delete "
            + "this test once you have added your own.", 0, 1);
   }
   @Test public void test1() {
      BinarySearch b1 = new BinarySearch();
      Comparator comp = new Comparator<Key>();
      Key[] k = new Key[4];
      Key = t = 10002;
      k[0] = 10000;
      k[1] = 10001;
      k[2] = 10002;
      k[3] = 10003;
      b1.firstIndexOf(k,t,comp);
   
   
      Assert.assertEquals("Default test added by jGRASP. Delete "
            + "this test once you have added your own.", 0, 1);
   }
}
