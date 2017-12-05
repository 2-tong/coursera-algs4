
import edu.princeton.cs.algs4.WeightedQuickUnionUF;;

public class Percolation {
	
	
	private WeightedQuickUnionUF union,auxUnion;
	private boolean[] site;//记录网格开关状态
	private int gridsize;//矩阵边长
	private int allsize;//数组总大小（gridsize^2+2）
	private int openNum=0;//网格打开数目
	
	private int trans(int row,int col)//坐标转换
	{
		if(row<1||row>gridsize||col<1||col>gridsize)
			throw new IllegalArgumentException("wrong row or col value with"+row+","+col);
		return (row-1)*gridsize+col;
	}
	
	
	

	public Percolation(int n)                // create n-by-n grid, with all sites blocked
	{
		if(n<1)
			throw new IllegalArgumentException("Arguments out of bound");
		gridsize=n;
		
		allsize=n*n+2;
		
		union=new WeightedQuickUnionUF(allsize);
		auxUnion=new WeightedQuickUnionUF(allsize-1);
		site=new boolean[allsize];
		
		
		site[0]=true;//顶部虚拟格点
		for(int i=1;i<allsize-1;i++) {
			site[i]=false;
		}
		site[allsize-1]=true;//底部虚拟格点
	} 
	
	
	public    void open(int row, int col)    // open site (row, col) if it is not open already
	{
		//System.out.println("open:"+row+","+col);
		
		int arr[][]= {{0,1},{0,-1},{1,0},{-1,0}};//遍历右，左，上，下用到的数组
		int n=trans(row,col);
		
		if(site[n])return;
		
		site[n]=true;
		openNum++;
		
		//第一行与最后一行需要连接对应虚拟网格
		if(row==1) {
			union.union(n, 0);
			auxUnion.union(n, 0);
		}
		if(row==gridsize)
			union.union(n, allsize-1);
		
		//System.out.println(++openNum);
		for(int i=0;i<4;i++) {//遍历上下左右
			int trow=row+arr[i][0];
			int tcol=col+arr[i][1];
			
			if(trow>=1 && trow<=gridsize && tcol>=1 && tcol<=gridsize)
			{
				if(site[trans(trow,tcol)]) {
					union.union(n, trans(trow,tcol));
					auxUnion.union(n, trans(trow,tcol));
				}
			}	
		}
		
	}  
	
	
	public boolean isOpen(int row, int col)  // is site (row, col) open?
	{
		return site[trans(row,col)];
	}  
	
	
	public boolean isFull(int row, int col)  // is site (row, col) full?
	{
		return auxUnion.connected(trans(row,col), 0);
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
