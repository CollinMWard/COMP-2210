import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PA3_JavaTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void Test() {
      //PA3_Java.calc_transition_matrix("DDRRRDRDDD");
      //PA3_Java.calc_transition_matrix("RRRRDDDDDRDRD");
      PA3_Java.generate_forecast(PA3_Java.calc_transition_matrix("RRRRRDDRRRRRRRR"), 'D');
      float[][] test = new float[2][2];
      test[0][0] = 2/3f;
      test[0][1] = 1/2f;
      test[1][0] = 1/3f;
      test[1][1] = 1/2f;
     
      float[] test1 = new float[2];
      test1[0] = 1;
      test1[1] = 0;
      PA3_Java.matrix_mult(test,test1);
      
   }
   @Test public void defaultTest() {
      Assert.assertEquals("Default test added by jGRASP. Delete "
            + "this test once you have added your own.", 0, 1);
   }
}
