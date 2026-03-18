package net.tfobz.concurrency.prime;

public class ThreadPrimeFactorTool extends PrimeFactorTool{
	
	@Override
	public void printPrimeFactors(int num)	{
		Thread t = new Thread(){
			@Override
			public void run() {
				ThreadPrimeFactorTool.super.printPrimeFactors(num);
			}
		};
		t.start();
	}
}

