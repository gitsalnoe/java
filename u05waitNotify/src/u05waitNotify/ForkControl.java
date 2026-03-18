package u05waitNotify;

public class ForkControl {
	Fork links = null;
	Fork rechts = null;
	
	public ForkControl(Fork links, Fork rechts) {
		this.links = links;
		this.rechts = rechts;
	}

	public synchronized void get(Philosopher p)	{
		if (links.available)	{
			links.get(p);
		}
		if (rechts.available)	{
			rechts.get(p);
		}
		else	{
			links.put(p);
		}
	}

	public synchronized void put(Philosopher p) {
		if (links.owner != null && rechts.owner != null) {
			if (links.owner.equals(p) && rechts.owner.equals(p))	{
				links.put(p);
				rechts.put(p);
			}
		}
	}
	
}
