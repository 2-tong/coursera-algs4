import edu.princeton.cs.algs4.StdIn;

public class Permutation {
   public static void main(String[] args)
   {
	   if(args.length == 0) return;
	   int num =Integer.valueOf(args[0]);
	   RandomizedQueue<String> Rque = new RandomizedQueue<String>();
	   
	   while(!StdIn.isEmpty())Rque.enqueue(StdIn.readString());
	   
	   while(num-- != 0) System.out.println(Rque.dequeue());
   }
}