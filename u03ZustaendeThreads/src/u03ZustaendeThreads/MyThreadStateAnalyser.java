package u03ZustaendeThreads;

public class MyThreadStateAnalyser extends Thread{

	private final Thread analyseThread;

    public MyThreadStateAnalyser(Thread analyseThread) {
        this.analyseThread = analyseThread;
    }

    @Override
    public void run() {
        while (analyseThread.getState() != Thread.State.TERMINATED) {
            System.out.println("Zustand: " + analyseThread.getState());
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
	
	public static void main(String[] args) {
		    

	}

}
