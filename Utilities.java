
import wheelsunh.users.Frame;

public class Utilities {
	
	public Utilities()
	{
		
	}
	
	// a represents the amount of spaces on the board to choose from,
	// b represents the number of squares associated with one group
	// pairA represents the number of groups
	
	public double combs(int a, int b, int pairA)
	{
		double product=1;
		for (int i = 0; i < pairA; i++)
		{
		    product = product*numer(a-i*2,a-b-i*2)/factorialA(b);
		    System.out.println(numer(a-i*2,a-b-i*2)/factorialA(b));
		}
		return product;
	}
	
	public double numer(int n, int k)
	{
		if (n == k)
			return 1;
		else
		{
			//System.out.println(n+" "+k);
			return n*numer(n-1,k);
		}
		
	}
	public double FPI(double x, int counter)
	{
		System.out.println(Math.pow(x, 3));
		if (counter == 5)
			return Math.pow(x, 3);
		else
			return FPI(Math.pow(x, 3),++counter);
		
	}
	
	public double g1X (double x, int counter)
	{
		System.out.println((4.0/5.0)*x + (1/x));
		if (counter >= 10 )
			return (4.0/5.0)*x + (1/x);
		else
			return g1X((4.0/5.0)*x + (1/x),++counter);
		
	}
	
	public double g2X (double x, int counter)
	{
		System.out.println((x/2.0) + (5.0/2)*(1/x));
		if (counter >= 10 )
			return (x/2.0) + (5.0/2.0)*(1/x);
		else
			return g2X((x/2.0) + (5.0/2.0)*(1/x),++counter);
		
	}
	
	public double g3X (double x, int counter)
	{
		System.out.println(x);
		if (counter >= 10 )
			return (x+5)/(x+1);
		else
			return g3X((x+5)/(x+1),++counter);
		
	}
	
	public int factorialA(int n)
	{
		int product = 1;
		for(int i = 1; i <= n; i++)
		{
			product = product*i;
		}
		return product;
	}
	
	public static void main (String args[]) throws InterruptedException
	{
		Utilities cal = new Utilities();
//		System.out.println("The product is "+cal.combs(25, 2, 5 ));
//		System.out.println("The product is "+cal.combs(400, 12, 1 ));
//		Sys
		System.out.println("The first thingy is : "+cal.g1X(1,0));
		System.out.println("The second thingy is : "+cal.g2X(1,0));
		System.out.println("The third thingy is : "+cal.g3X(1,0));
		
	}

}
