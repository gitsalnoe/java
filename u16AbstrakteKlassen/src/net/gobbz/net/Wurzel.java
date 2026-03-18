package net.gobbz.net;

public class Wurzel extends ArgOperation{

	public Wurzel(Argument argument, Operand operand) {
		super(argument, operand);
	}

	public double getErgebnis() {
		double ret = 0.0;
		
		if (this.getOperand() != null) {
	        ret = this.getOperand().getErgebnis();
	    }
		
	    if (this.getArgument() != null) {
	        double wurzelExponent = this.getArgument().getErgebnis();
	        ret = Math.pow(ret, 1.0 / wurzelExponent);
	    }
	    return ret;
	}
	
	public String toString() {
        return "(Wurzel(" + this.getArgument().toString() + "," + this.getOperand().toString() + ")=" + this.getErgebnis() + ")";
    }
}
