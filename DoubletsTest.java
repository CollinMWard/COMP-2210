import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class DoubletsTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void test() throws FileNotFoundException {
      //Doublets d = new Doublets(new FileInputStream(new File("words.txt")));
      Random rand = new Random();
      System.out.println(rand.nextInt(3) + 1);
      //d.getHammingDistance("yxx", "xxx");
      //d.getMinLadder("aaa","aaa");
    //  d.getHammingDistance("aaa","aaa");
      //Assert.assertEquals(true, d.hamIsOne("aaa", "aax"));
   }

   
   @Test public void test1() throws FileNotFoundException {
      //Doublets d = new Doublets(new FileInputStream(new File("words.txt")));
      //List<String> ladder = new ArrayList<String>();
      //d.getHammingDistance("yxx", "xxx");
     // d.getMinLadder("aaa","aaa");
     // cat, can, con, cog, dog
     //clash, flash, flask, flack, flock, clock, crock, crook, croon, crown, clown
      //ladder.add("cat");
      //ladder.add("can");
      //ladder.add("con");
      //ladder.add("cog");
      //ladder.add("dog");
    //  d.getHammingDistance("aaa","aaa");
      //Assert.assertEquals(ladder,  d.getMinLadder("clash", "clown"));
      //Assert.assertEquals(ladder,  d.getNeighbors("cat"));
   }
}
