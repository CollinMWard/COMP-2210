import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class Assignment2_Java_TemplateTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
 
   @Test public void testM1() {
      Assert.assertEquals(7, Assignment2_Java_Template.IsMagic(3, 20));
   }
   
   @Test public void testM12() {
      Assert.assertEquals(1, Assignment2_Java_Template.IsMagic(2, 20));
   }
   
   @Test public void testM13() {
      Assert.assertEquals(6, Assignment2_Java_Template.IsMagic(10, 20));
   }
   
   @Test public void testM21() {
      Assert.assertEquals(7, Assignment2_Java_Template.TestRange(1, 3, 20));
   }
   @Test public void testM22() {
      Assert.assertEquals(1, Assignment2_Java_Template.TestRange(1, 2, 20));
   }
   @Test public void testM23() {
      Assert.assertEquals(110, Assignment2_Java_Template.TestRange(1, 500, 2000));
   }
   @Test public void testM24() {
      Assert.assertEquals(6, Assignment2_Java_Template.TestRange(1, 10, 2000));
   }
}
