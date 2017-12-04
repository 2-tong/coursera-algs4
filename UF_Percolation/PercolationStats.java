
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double Vmean;
	private double Vstddev;
	private double VconfidenceLo;
	private double VconfidenceHi;
	
	public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
	{
		double mean[]=new double[trials];
		
		int arry[][]=new int[n*n][2];
		int k=0;
		for(int row=1;row<=n;row++)
			for(int col=1;col<=n;col++)
			{
				arry[k][0]=row;
				arry[k][1]=col;
				k++;
			}
		
		for(int i=0;i<trials;i++) {
			k=0;
			StdRandom.shuffle(arry);
			Percolation per=new Percolation(n);
			while(!per.percolates()) 
				per.open(arry[k][0], arry[k++][1]);
			mean[i]=per.numberOfOpenSites()/((double)n*n);
			//System.out.println("trials++");
		}
		Vmean=StdStats.mean(mean);
		Vstddev=StdStats.stddev(mean);
		VconfidenceLo=Vmean-(1.96*Vstddev)/Math.sqrt(trials);
		VconfidenceHi=Vmean+1.96/Math.sqrt(trials);
	}   
	public double mean()                          // sample mean of percolation threshold
	{
		return Vmean;}
	public double stddev()                        // sample standard deviation of percolation threshold
	{
		return Vstddev;}
	public double confidenceLo()                  // low  endpoint of 95% confidence interval
	{
		return VconfidenceLo;}
	public double confidenceHi()                  // high endpoint of 95% confidence interval
	{
		return VconfidenceHi;}
	public static void main(String[] args)        // test client (described below)
	{
		if(args.length!=2) 
		    System.out.println("command line wrong");
		int n=Integer.parseInt(args[0]);
		int T=Integer.parseInt(args[1]);
		//System.out.println(n+T);
		
		PercolationStats PerS=new PercolationStats(n,T);
		System.out.println("mean                    = "+PerS.mean());
		System.out.println("stddev                  = "+PerS.stddev());
		System.out.println("95% confidence interval = ["+PerS.confidenceLo()+", "+PerS.confidenceHi()+"]");
	}
}
