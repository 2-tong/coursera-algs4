import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private class Node
	{
		Item item;
		Node next,previous;
		Node(Item i,Node p ,Node n){item = i;next = n;previous = p;}
	}
	
	private Node first = null;//头指针
	private Node last  = null;//尾指针
	private int		num=0;//队列元素个数
	
	
	
public Deque()                           // construct an empty deque
   {

   }

   public boolean isEmpty()                 // is the deque empty?
   {
	return num==0;
   }
   
   public int size()                        // return the number of items on the deque
   {
	return num;
   }
   
   public void addFirst(Item item)          // add the item to the front
   {
	  if(item == null) throw new IllegalArgumentException("Error:The item is null!");
	  
	  Node oldFirst = first;
	  first = new Node(item,null,first);
	  if(isEmpty()) last = first;
	  else			oldFirst.previous = first;
	  
	  num++;
   }
   
   public void addLast(Item item)           // add the item to the end
   {
	   if(item == null) throw new IllegalArgumentException("Error:The item is null!!!");
	   
	   Node oldLast = last;
	   last = new Node(item,last,null);
	   if(isEmpty()) first = last;
	   else			oldLast.next = last;
	   
	   num++;
   }
   
   public Item removeFirst()                // remove and return the item from the front
   {
	   if(isEmpty()) throw new NoSuchElementException("The queue is empty!!!");
	
	   Item item = first.item;
	   first = first.next;
	   num--;
	   if(isEmpty()) 	last = null;
	   else				first.previous = null;
	   
	   return item;
   }
   
   
   public Item removeLast()                 // remove and return the item from the end
   {
	   if(isEmpty()) throw new NoSuchElementException("The queue is empty!!!");
		
	   Item item = last.item;
	   last = last.previous;
	   num--;
	   if(isEmpty()) 	first = null;
	   else				last.next = null;
	   
	   return item;
   }
   
   private class DequeIterator implements Iterator<Item>
   {
	   private Node p = first;
	   public boolean hasNext(){return p != null;}
	   public void remove() {throw new UnsupportedOperationException("UnsupportedOperation  DequeIterator::remove");}
	   public Item next() 
	   {
		   if(p == null)throw new NoSuchElementException("The pointer is null!!!");
		   
		   Item item = p.item;
		   p=p.next;
		   return item;
	   }
   }
   
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
	return new DequeIterator();
   }
   
   public static void main(String[] args)   // unit testing (optional)
   {
	   if((args.length) == 0) return;
	   Deque<String> strdeque= new Deque<String>();
	   int x = 1;
	   for(String str:args) {
		   if(x == 1) strdeque.addFirst(str);
		   if(x == -1)strdeque.addLast(str);
		   x = -x;
	   }
	   
	   for(String str:strdeque) 
	   {
		   System.out.println(str);
	   }
	   
	   System.out.println();
	   
	   while(!strdeque.isEmpty())
	   {
		   if(x == 1) System.out.println(strdeque.removeFirst());
		   if(x == -1)System.out.println(strdeque.removeLast());
		   x = -x;
	   }
   }
}