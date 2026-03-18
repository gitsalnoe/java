package u03ZustaendeThreads;

public class MyThreadTerminator extends Thread{
	
	private final Thread terminierungsThread;

    public MyThreadTerminator(Thread TerminierungsThread) {
        this.terminierungsThread = TerminierungsThread;
    }

    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        terminierungsThread.interrupt();
    }

	public static void main(String[] args) {

	}

}
