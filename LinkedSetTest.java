import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class LinkedSetTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
 
   @Test public void testadd() {
      LinkedSet<Integer> a = new LinkedSet<>();
      int one = 1;
      int two = 2;
      int three = 3;
      int four = 4;
      a.add(one);
      a.add(two);
      a.add(three);
      a.add(four);
    
      Assert.assertEquals(true, a.contains(one));
      Assert.assertEquals(true, a.contains(two));
      Assert.assertEquals(true, a.contains(three));
      Assert.assertEquals(true, a.contains(four));
   }
   @Test public void testremove() {
   
      LinkedSet<Integer> a = new LinkedSet<>();
      int one = 1;
      int two = 2;
      a.add(one);
      a.add(two);
      a.remove(one);
   
    
      Assert.assertEquals(false, a.contains(one));
      Assert.assertEquals(true, a.contains(two));
   }
   
   @Test public void testequals() {
   
      LinkedSet<Integer> a = new LinkedSet<>();
      int one = 1;
      int two = 2;
      a.add(one);
      a.add(two);
      LinkedSet<Integer> b = new LinkedSet<>();
      b.add(one);
      b.add(two);
      Assert.assertEquals(true, a.equals(b));
   }
   @Test public void testComplement() {
   
      LinkedSet<Integer> a = new LinkedSet<>();
      int one = 1;
      int two = 2;
      int three = 3;
      int fourth = 4;
      a.add(one);
      a.add(two);
      a.add(three);
      a.add(fourth);
      LinkedSet<Integer> b = new LinkedSet<>();
      b.add(one);
      b.add(two);
      LinkedSet<Integer> c = new LinkedSet<>();
      c.add(three);
      c.add(fourth);
      Assert.assertEquals(true, c.equals(a.complement(b)));
        
      
      /*
      
      
      
      
      while (itr1 != null) {
         itr3.next = new Node(itr1.element);
         itr3.next.prev = itr3;
         itr3 = itr3.next;
         c.rear = itr3;
         itr1 = itr1.next;
         c.size++;
      }
   
      if (itr2 == null) {
         return c;
      }
   
      itr3 = c.front;
   
      while ((itr2 != null) && (itr3 != null)) {
         if ((itr2.element.compareTo(itr3.element)) < 0) {
            itr2 = itr2.next;
         }
         else if ((itr2.element.compareTo(itr3.element)) > 0) {
            itr3 = itr3.next;
         }
         else if ((itr2.element.compareTo(itr3.element)) == 0) {
            if ((itr3.equals(c.rear)) && (itr3.equals(c.front))) {
               c.front = null;
               c.rear = null;
               c.size--;
               return c;
            }
            else if (itr3.equals(c.rear)) {
               itr3.prev.next = null;
               c.size--;
               return c;
            }
            else if (itr3.equals(c.front)) {
               c.front = c.front.next;
               if (c.front == null) {
                  c.rear = null;
               }
               else if (c.front != null) {
                  c.front.prev = null;
               }
               itr3 = itr3.next;
               itr2 = itr2.next;
               c.size--;
            }
            else {
               itr3.next.prev = itr3.prev;
               itr3.prev.next = itr3.next;
               itr3 = itr3.next;
               c.size--;
            }
         }
      }
      
      return c;
   }     
   */
   
   }
   @Test public void testComplement2() {
   
      LinkedSet<Integer> a = new LinkedSet<>();
      int one = 1;
      int two = 2;
      int three = 3;
      int four = 4;
      a.add(one);
      a.add(two);
      a.add(three);
      LinkedSet<Integer> b = new LinkedSet<>();
   
      LinkedSet<Integer> c = new LinkedSet<>();
      c.add(one);
      c.add(two);
      c.add(three);
      Assert.assertEquals(true, b.equals(a.complement(c)));
   }
   @Test public void testComplement3() {
   
      LinkedSet<Integer> a = new LinkedSet<>();
      int one = 1;
      int two = 2;
      int three = 3;
      int four = 4;
      a.add(one);
      a.add(two);
      a.add(three);
     
      LinkedSet<Integer> b = new LinkedSet<>();
      b.add(one);
      b.add(two);
      b.add(three);
      LinkedSet<Integer> c = new LinkedSet<>();
      //Set<Integer> l = new LinkedSet<>();
      //boolean l = c.equals(a.complement(b));
    
      Assert.assertEquals(c, a.complement(b));
      //Assert.assertEquals(true,l);
   }
   @Test public void testUnion() {
   
      LinkedSet<Integer> a = new LinkedSet<>();
      int one = 1;
      int two = 2;
      int three = 3;
      int four = 4;
      LinkedSet<Integer> c = new LinkedSet<>();
      a.add(one);
      a.add(two);
      c.add(three);
      c.add(four);
      
     
      Set<Integer> b = new LinkedSet<>();
      b.add(one);
      b.add(two);
      b.add(three);
      b.add(four);      
      Set<Integer> h = new LinkedSet<>();
      h = a.union(c);
      //Assert.assertEquals(b, a.union(c));
      Assert.assertEquals(true, b.equals(h));
      //Assert.assertEquals(b, c.union(a));
   }
   
}
