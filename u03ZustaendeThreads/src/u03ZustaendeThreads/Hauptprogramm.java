package u03ZustaendeThreads;

public class Hauptprogramm {

	public static void main(String[] args) {
        MyThread myThread = new MyThread();
        
        MyThreadStateAnalyser analyser = new MyThreadStateAnalyser(myThread);
        MyThreadTerminator terminator = new MyThreadTerminator(myThread);

        System.out.println("Starte Analyser und Terminator");
       
        analyser.start();
        terminator.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Starte MyThread");
        myThread.start();

        try {
            myThread.join();
            terminator.join(); 
            analyser.join();   
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
