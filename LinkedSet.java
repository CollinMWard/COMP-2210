import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Collin Ward (cmw0147@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {
// an online resource with the exact programming assighnment exist
// and was refrenced many times throughout the assignment however,
// all code was written by myself

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
   
      Node a = new Node(element);
      
      if ((this.contains(element)) || (element == null)) {
         return false;
      }
      if (isEmpty()) {
         front = a;
         rear = a;
      } else if (front.element.compareTo(element) > 0) {
         a.next = front;
         front.prev = a;
         front = a;
      } else {
         rear.next = a;
         a.prev = rear;
         rear = a;
         if (a.prev.element.compareTo(element) > 0) {
            Node itr1 = this.front;
            while (itr1 != null) {
               if (itr1.element.compareTo(element) > 0) {
                  rear = a.prev;
                  itr1.prev.next = a;
                  a.next = itr1;
                  a.prev = itr1.prev;
                  itr1.prev = a;
                  rear.next = null;
                  // may not work;
                  break;
               }
               itr1 = itr1.next;
            }
         }
      }
      size++;
      return true;
   }

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
   
      if ((element == null) || (size == 0)) {
         return false;
      }
   
      if (!this.contains(element)) {
         return false;
      }
      Node itr1 = front;
      while (itr1 != null) {
         if (itr1.element.equals(element)) {
            if (itr1.equals(front)) {
               front = front.next;
               if (front == null) {
                  rear = null;
               } else if (front != null) {
                  front.prev = null;
               }
            } else if (itr1.equals(rear)) {
               rear = rear.prev;
               rear.next = null;
            } else {
               itr1.prev.next = itr1.next;
               itr1.next.prev = itr1.prev;
            }
            size--;
            return true;
         }
         itr1 = itr1.next;
      }
      return false;
   }

   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
   
      if (element == null) {
         return false;
      } 
      if (this.size == 0) {
         return false;
      }
      Node itr1 = front;
      while (itr1 != null) {
         if (itr1.element.equals(element)) {
            return true;
         }
         itr1 = itr1.next;
      }
      return false;
    
   }
   


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
   // can use s size and complement size (list of elements in this but not s)
      if (size == s.size() && complement(s).size() == 0) {
         return true;
      } else {
         return false;
      }
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
      if (size != s.size()) {
         return false;
      }
     
      Node thisItr = front;
      Node sItr = s.front;
      
      while (sItr != null && thisItr != null) {
         if (thisItr.element.compareTo(sItr.element) != 0) {
            return false;
         }
         sItr = sItr.next;
         thisItr = thisItr.next;
      }
      return true;

       
   }
  
  
     
   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s){// rewrite to be faster?
   // failing for some reason? problem with equals?
      Set<T> a = new LinkedSet<T>();
      Iterator<T> itr = s.iterator();
      while (itr.hasNext()) {
         a.add(itr.next());
      }
      
      Node itr1 = front;
      while (itr1 != null) {
         a.add(itr1.element);
         itr1 = itr1.next;
      }
      return a;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s){
   // copy of this set
      LinkedSet<T> a = new LinkedSet<T>();
   // copy of parameter set and will be returned as union set
      LinkedSet<T> b = new LinkedSet<T>();
   // set to be returned, need a new one inorder to maintain order of elemtns
      LinkedSet<T> c = new LinkedSet<T>();
      a.front = front;
      a.rear = rear;
      a.size = size;
      b.front = s.front;
      b.rear = s.rear;
      b.size = s.size; 
   // itr nodes placed at the start of each linked list (could redo with iterator?)
      Node itr1 = this.front;
      Node itr2 = s.front;
      Node itr3 = c.front;
   // if a or b is null, the other should be returned
      if (itr1 == null) {
         while (itr2 != null) { 
            c.add(itr2.element);
            itr2 = itr2.next;
         }
         return c;
      }
      if (itr2 == null) {
         while (itr1 != null) { 
            c.add(itr1.element);
            itr1 = itr1.next;
         }
         return c;
      }
  
      if (itr1 != null && itr2 != null) {
         if (itr1.element.compareTo(itr2.element) < 0) {
            c.front = new Node(itr1.element);
            c.rear = new Node(itr1.element);
            itr1 = itr1.next;
         }
         else if (itr1.element.compareTo(itr2.element) > 0) {
            c.front = new Node(itr2.element);
            c.rear = new Node(itr2.element);
            itr2 = itr2.next;
         }
         else if (itr1.element.compareTo(itr2.element) == 0) {
            c.front = new Node(itr1.element);
            c.rear = new Node(itr1.element);
            itr1 = itr1.next;
            itr2 = itr2.next;
         }
         itr3 = c.front;
         c.size++;
      }
         
      while (itr1 != null || itr2 != null) {
         if (itr1 == null) {
            itr3.next = new Node(itr2.element);
            itr2 = itr2.next;
         }
         else if (itr2 == null) {
            itr3.next = new Node(itr1.element);
            itr1 = itr1.next;
         }
         else if (itr1.element.compareTo(itr2.element) < 0) {
            itr3.next = new Node(itr1.element);
            itr1 = itr1.next;
         }
         else if ((itr1.element.compareTo(itr2.element)) > 0) {
            itr3.next = new Node(itr2.element);
            itr2 = itr2.next;
         }
         else if ((itr1.element.compareTo(itr2.element)) == 0) {
            itr3.next = new Node(itr1.element);
            itr1 = itr1.next;
            itr2 = itr2.next;
         }
         
         itr3.next.prev = itr3;
         c.size++;
         itr3 = itr3.next;
         c.rear = itr3;
      }
      return c;
   }
      
   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
   // should be easy, just check what is contained in this and s
      LinkedSet<T> a = new LinkedSet<T>();
      Iterator<T> itr1 = this.iterator();
      while (itr1.hasNext()) {
         T temp = itr1.next();
         if (this.contains(temp) && s.contains(temp)) {
            a.add(temp);
         }
      }
      return a;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
     
      Set<T> a = this.union(s);
      Set<T> b = this.complement(s);
      Set<T> c = s.complement(this);
      Set<T> d = b.union(c);
      Set<T> e = a.complement(d);
      return e;
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
 
      Set<T> a = new LinkedSet<T>();
      Iterator<T> itr2 = s.iterator();
      Iterator<T> itr1 = this.iterator();
   
      while (itr1.hasNext()) {
         a.add(itr1.next());
      }
      while (itr2.hasNext()) {
         T temp = itr2.next();
         if (a.contains(temp)) {
            a.remove(temp);
         } 
      }
      return a;
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
            LinkedSet<T> a = new LinkedSet<T>();
      LinkedSet<T> b = new LinkedSet<T>();
      LinkedSet<T> c = new LinkedSet<T>();
      a.front = front;
      a.rear = rear;
      a.size = size;
      b.front = s.front;
      b.rear = s.rear;
      b.size = s.size;
      if (front == null) {
         return a;
      }
     
      c.front = new Node(front.element);
      c.rear = c.front;
      c.size++;
      Node itr3 = c.front;
      Node itr1 = front.next;
      Node itr2 = s.front;
   
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
   
      

      
       
   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() { 
   // how in the hell???
      return new AscendingIterator();
   }

   private class AscendingIterator implements Iterator<T> {
      private Node current = front;
   
      public boolean hasNext() {
         return (current != null);
      }
   
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         T p = current.element;
         current = current.next;
         return p;
      }
   
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      return new DescendingIterator(rear);
   
   }
   private class DescendingIterator implements Iterator<T> {
      private Node current;
   
      public DescendingIterator(Node n) {
         current = n;
      }
   
      public boolean hasNext() {
         return ((current != null) && (current.element != null));
      }
   
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         if (current != null) {
            T result = current.element;
            current = current.prev;
            return result;
         }
         return null;
      }
   
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }


   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      return new PowerSetIterator(rear,size);
   }

   private class PowerSetIterator implements Iterator<Set<T>> {
      private Node current;
      private int siz;
      private int count;
   
      public PowerSetIterator(Node rear,int size) {
         current = rear;
         siz = size;
         count = 0;
      }
   
      public boolean hasNext() {
         if (count == 0) {
            return true;
         }
         return ((count < (int) Math.pow(2,siz)) && (current != null));
      }
   
      public Set<T> next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         Set<T> result = new LinkedSet<T>();
         if (count == 0) {
            count++;
            return result;
         }
         String binary = Integer.toBinaryString(count);// what tf
         for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
               result.add(current.element);
            }
            current = current.prev;
         }
         count++;
         current = rear;
         return result;
      }
   
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }



   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////

   // Feel free to add as many private methods as you need.

   ////////////////////
   // Nested classes //
   ////////////////////

   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

}
