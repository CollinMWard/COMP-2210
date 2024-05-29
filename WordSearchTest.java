import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class WordSearchTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/

   @Test public void defaultTest2() {
      WordSearch two = new WordSearch();
      two.loadLexicon("text");
      Assert.assertEquals(false, two.isValidWord("aus"));
      
   }
}
