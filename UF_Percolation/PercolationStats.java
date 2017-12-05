
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double Vmean;//平均值
	
	private double Vstddev;//标准差
	
	
	private double VconfidenceLo;
	private double VconfidenceHi;//%95置信区间
	
	public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
	{
		if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Arguments out of bound");
		
		double mean[]=new double[trials];
		
		/*int arry[][]=new int[n*n][2];//网格遍历顺序数组初始化(1，1)(1，2)(1，3).....(n,n-1)(n,n)
		int k=0;
		for(int row=1;row<=n;row++)
			for(int col=1;col<=n;col++)
			{
				arry[k][0]=row;
				arry[k][1]=col;
				k++;
			}*/
		
		
		for(int i=0;i<trials;i++) {//进行trials次实验
			/*k=0;
			StdRandom.shuffle(arry);//打乱遍历顺序
*/			Percolation per=new Percolation(n);
			
			while(!per.percolates()) 
				
				per.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1));
				//per.open(arry[k][0], arry[k++][1]);
			
			mean[i]=(double)per.numberOfOpenSites()/(n*n);//记录概率
			//System.out.println("trials++");
		}
		
		
		//根据mean数组计算
		Vmean=StdStats.mean(mean);
		Vstddev=StdStats.stddev(mean);
		VconfidenceLo=Vmean-(1.96*Vstddev)/Math.sqrt(trials);
		VconfidenceHi=Vmean+(1.96*Vstddev)/Math.sqrt(trials);
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
		
		//获取命令行参数
		int n=Integer.parseInt(args[0]);
		int T=Integer.parseInt(args[1]);
		//System.out.println(n+T);
		
		PercolationStats PerS=new PercolationStats(n,T);
		
		System.out.println("mean                    = "+PerS.mean());
		System.out.println("stddev                  = "+PerS.stddev());
		System.out.println("95% confidence interval = ["+PerS.confidenceLo()+", "+PerS.confidenceHi()+"]");
	}
}
