package u03ZustaendeThreads;

public class MyThread extends Thread{

	public void run()	{
		while (!Thread.currentThread().isInterrupted())	{
			try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            	System.out.println("Thread im schalf unterbrochen");
                break;
            }
			int a = 0;
			for (int i = 0; i < Integer.MAX_VALUE; i++)	{
				a++;
				if (Thread.currentThread().isInterrupted()) {
					System.out.println("Thread wurden unterbrochen");
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
	}

}
