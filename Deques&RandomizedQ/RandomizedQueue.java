import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>
{
	private int first,last;
	private int num=0;
	private Item arry[];
	
	public RandomizedQueue()                 // construct an empty randomized queue
	{
		arry = (Item[])new Object[1];
		first = last = 0;
	}
	
	public boolean isEmpty()                 // is the randomized queue empty?
	{
		return num == 0;
	}
	
	public int size()                        // return the number of items on the randomized queue
	{
		return num;
	}
	
	private int firstOps(int op) 
	{
		int old = first;
		if(op == 1)
			if(++first>= arry.length) first = 0;

		if(op == 0)
			if(--first< 0)first = arry.length;
		
		return old;
	}
	
	private int lastOps(int op) 
	{
		int old = last;
		if(op == 1)
			if(++last >= arry.length) last = 0;

		if(op == 0)
			if(--last < 0)last = arry.length;
		
		return old;
	}
	
	private void resize(int size) 
	{
		Item[] copy = (Item[])new Object[size];
		for(int i = 0;i < num; i++ ) 
		{
			copy[i]=arry[first];
			firstOps(1);
		}
		arry = copy;
		first = 0;
		last  = num;
			
	}
	
	public void enqueue(Item item)           // add the item
	{
		if(item == null) throw new IllegalArgumentException("Item Pointer is null!!");
		
		if(num == arry.length) resize(num * 2);
		//System.out.print("in at "+last);
		arry[lastOps(1)] = item;
		//System.out.println(" "+last);
		num++;
	}
	
	public Item dequeue()                    // remove and return a random item
	{
		if(isEmpty()) throw new NoSuchElementException("The queue is empty!!!");
		
		int random = StdRandom.uniform(num);
		if((random += first) >= arry.length) random -= arry.length;
		
		Item item = arry[random]; 
		arry[random] = arry[first];
		arry[firstOps(1)] = null;
		
		num--;
				
		if(num > 0 && num == arry.length/4)resize(arry.length/2);
		
		return item;
	}
	
	public Item sample()                     // return a random item (but do not remove it)
	{
		if(isEmpty()) throw new NoSuchElementException("The queue is empty!!!");
		
		int random = StdRandom.uniform(num);
		if((random += first) >= arry.length) random -= arry.length;
		
		return arry[random];
	}
	   
	private class RQueueIterator implements Iterator<Item>
	{
		private int pos = first;
		private int left = num;
		private int[] iteratorarry = new int [num];
		public RQueueIterator() 
		{
			for(int i=0;i<num;i++)iteratorarry[i]=i;
			StdRandom.shuffle(iteratorarry);
		}
		
		public boolean hasNext(){return left != 0;}
		public void remove() {throw new UnsupportedOperationException("UnsupportedOperation  DequeIterator::remove");}
		public Item next() {
			if(left == 0)throw new NoSuchElementException("The queue is empty!!!");
			
			//System.out.println(pos);
			//System.out.println(left);
			
			/*int random = StdRandom.uniform(left--);
			if((random += pos) >= arry.length) random -= arry.length;
			
			Item item = arry[random]; 
			arry[random] = arry[pos];
			arry[pos] = item;
			if(++pos >= arry.length) pos = 0;*/
			int random = iteratorarry[left-- - 1];
			if((random += pos) >= arry.length) random -= arry.length;			
			return arry[random];
		}
	}
	   
	public Iterator<Item> iterator()		// return an independent iterator over items in random order
	{
		return new RQueueIterator();
	}
	       
	public static void main(String[] args)   // unit testing (optional)
	{
		if(args.length == 0) return;
		RandomizedQueue<String> Rque = new RandomizedQueue<String>();
		
		for(String str:args) Rque.enqueue(str);
		
		for(String str:Rque) System.out.print(str+" ");
		System.out.println();
		
		//System.out.println(Rque.dequeue());
		
		for(String str:Rque) System.out.print(str+" ");
		System.out.println();
		
		while(!Rque.isEmpty())System.out.print(Rque.dequeue()+" ");
	}
}
