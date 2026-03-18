package net.gobbz.net;

public class Potenz extends Operation{
	
	public Potenz(Operand operand0, Operand operand1)	{
		super(operand0, operand1);;
	}

	public double getErgebnis()	{
		double ret = 0.0;
		if (this.getOperand(0) != null)	{
			ret = this.getOperand(0).getErgebnis();
		}
		if (this.getOperand(1) != null)	{
			ret = Math.pow(ret, this.getOperand(1).getErgebnis());
		}
		return ret;
	}
	
	public String toString() {
		return "("+ this.getOperand(0).toString()+ "^"+this.getOperand(1).toString()+"="+this.getErgebnis()+")";
	}
}
