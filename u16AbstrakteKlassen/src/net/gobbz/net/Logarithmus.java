package net.gobbz.net;

public class Logarithmus extends ArgOperation	{
	
	public Logarithmus(Argument argument, Operand operand)	{
		super(argument, operand);
	}
	
	public double getErgebnis() {
		double ret = 0.0;
		
		if (this.getOperand() != null)	{
			ret = this.getOperand().getErgebnis();	
		}
		if (this.getArgument() != null)	{
			ret = Math.log(ret)/Math.log(this.getArgument().getErgebnis());
		}
		return ret;
	}
	
	public String toString() {
		String ret = null;
		return "(log(" + this.getArgument().toString() +","+this.getOperand().toString() + ")=" + this.getErgebnis() +")";
	}
}
