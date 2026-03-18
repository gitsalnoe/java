package threadtest;

public class CounterMain {

	public static void main(String[] args) {
		Runnable t1 = new CounterThread();
		Thread t2 = new Thread(t1);
		t2.start();
	}

}
