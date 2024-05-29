import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PA4_JavaTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
      System.out.println(PA4_Java.run_experiment(10000, true));
      System.out.println(PA4_Java.run_experiment(10000, false));
   }
}
