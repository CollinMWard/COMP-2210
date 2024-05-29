import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class AutocompleteTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
 
   @Test public void test1() {
      String t1 = "wack";
      String t2 = "wacky";
      String t3 = "oof";
      Term[] test = new Term[4];
      test[0] = new Term(t1, 4);
      test[1] = new Term(t3, 4);
      test[2] = new Term(t3, 6);
      test[3] = new Term(t2, 2);
      Autocomplete a1 = new Autocomplete(test);
      Term[] o = a1.allMatches("wa");
      Term[] test1 = new Term[2];
      test1[0] = new Term(t1, 4);
      test1[1] = new Term(t2, 2);
      Assert.assertEquals(test1, o);
   }
   
   
   @Test public void test2() {
      String t1 = "a";
      String t2 = "ab";
      String t3 = "abc";
      Term[] test = new Term[4];
      test[0] = new Term(t1, 4);
      test[1] = new Term(t2, 4);
      test[2] = new Term(t3, 6);
      test[3] = new Term(t1, 2);
      Autocomplete a1 = new Autocomplete(test);
      Term[] o = a1.allMatches("a");
      Term[] test1 = new Term[2];
      test1[0] = new Term(t1, 6);
      test1[1] = new Term(t1, 2);
      Assert.assertEquals(test1, o);
   }
   @Test public void test3() {
      Term t = new Term("wack", 20);
      System.out.println(t.toString());
   }
   
}
