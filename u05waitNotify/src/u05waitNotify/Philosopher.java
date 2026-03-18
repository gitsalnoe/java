package u05waitNotify;

public class Philosopher extends Thread{
	public static final int MAX_THINK_TIME = 0;
	public static final int MAX_EAT_TIME = 1000;
	private Fork left, right = null;
	private ForkControl control = null;
	public Philosopher(String name, Fork left, Fork right) {
		setName(name); this.left = left; this.right = right;
		control = new ForkControl(this.left, this.right);
	}
	@Override
	public void run() {
		while (true) {
	try {
		sleep((int)(Math.random() * MAX_THINK_TIME));
	} catch (InterruptedException e) { ; }
		control.get(this);
	try {
		sleep((int)(Math.random() * MAX_EAT_TIME));
	} catch (InterruptedException e) { ; }
		control.put(this);
	}
}
	
	public static void main(String[] args) {
		Fork f1 = new Fork("F1");
		Fork f2 = new Fork("F2");
		Fork f3 = new Fork("F3");
		Philosopher p1 = new Philosopher("P1", f3, f1);
		Philosopher p2 = new Philosopher("P2", f1, f2);
		Philosopher p3 = new Philosopher("P3", f2, f3);
		p1.start(); p2.start(); p3.start();
	}

}
