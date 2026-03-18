package threadtest;

public class CounterThread extends Thread{
	
	private int a = 0; 
	
	public CounterThread() {
		start();
	}
	
	public void run() {
		System.out.println(a);
		a++;
	}
	
}
