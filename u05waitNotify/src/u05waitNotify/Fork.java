package u05waitNotify;

public class Fork {
	protected String name = null;;
	protected boolean available = true;
	public Philosopher owner = null;
	public Fork(String name) {
		this.name = name;
	}
	public synchronized void get(Philosopher p){
		while (!available)
			try {
				wait();
			} catch (InterruptedException e) { 
				; 
			}
		available = false;
		owner = p;
		System.out.println(p.getName() + " gets " + name);
	}
	public synchronized void put(Philosopher p){
		available = true;
		owner = null;
		System.out.println(p.getName() + " puts " + name);
		notifyAll();
	}
}
