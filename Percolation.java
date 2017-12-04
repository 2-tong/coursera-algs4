
import edu.princeton.cs.algs4.WeightedQuickUnionUF;;

public class Percolation {
	private WeightedQuickUnionUF union;
	private boolean[] site;
	private int gridsize;
	private int allsize;
	private int openNum=0;
	
	private int trans(int row,int col) {
		if(row<1||row>gridsize||col<1||col>gridsize)
			throw new IllegalArgumentException("wrong row or col value with"+row+","+col);
		return (row-1)*gridsize+col;}
	
	

	public Percolation(int n)                // create n-by-n grid, with all sites blocked
	{
		gridsize=n;
		
		allsize=n*n+2;
		
		union=new WeightedQuickUnionUF(allsize);
		site=new boolean[allsize];
		site[0]=true;
		for(int i=1;i<allsize-1;i++) {
			site[i]=false;
		}
		site[allsize-1]=true;
	} 
	
	
	public    void open(int row, int col)    // open site (row, col) if it is not open already
	{
		//System.out.println("open:"+row+","+col);
		
		int arr[][]= {{0,1},{0,-1},{1,0},{-1,0}};
		int n=trans(row,col);
		
		if(site[n])return;
		
		site[n]=true;
		if(row==1)
			union.union(n, 0);
		if(row==gridsize)
			union.union(n, allsize-1);
		openNum++;
		//System.out.println(++openNum);
		for(int i=0;i<4;i++) {
			int trow=row+arr[i][0];
			int tcol=col+arr[i][1];
			if(trow>=1 && trow<=gridsize && tcol>=1 && tcol<=gridsize)
			{
				if(site[trans(trow,tcol)])	union.union(n, trans(trow,tcol));
			}	
		}
		
	}  
	
	
	public boolean isOpen(int row, int col)  // is site (row, col) open?
	{
		return site[trans(row,col)];
	}  
	
	
	public boolean isFull(int row, int col)  // is site (row, col) full?
	{
		return union.connected(trans(row,col), 0);
	}  
	
	
	public     int numberOfOpenSites()       // number of open sites
	{
		return openNum;
	}  
	
	
	public boolean percolates()              // does the system percolate?
	{
		return union.connected(0, allsize-1);
	}
	
	
	public static void main(String[] args)   // test client (optional)
	{}
}
