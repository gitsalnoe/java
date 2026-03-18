package net.gobbz.net;

public abstract class Operation extends Operand{
	
	private Operand[] operand = new Operand[2];
	
	public Operation(Operand operand0, Operand operand1) {
		this.setOperand(operand0);
		this.setOperand(operand1);
	}
	
	public Operation() {
		super();
	}
	
	public void setOperand(Operand operand) {
		if (this.operand[0] == null)	{
			this.operand[0] = operand;
	}	
	else
		if (this.operand[1] == null)
			this.operand[1] = operand;
	}
	
	public Operand getOperand(int position) {
	if (position >= 0 && position <= 1)
		return this.operand[position];
	else
		return null;
	}
	
	public void vertausche() {
		Operand operand = this.operand[0];
		this.operand[0] = this.operand[1];
		this.operand[1] = operand;
	}
	
	public void loescheOperand(int position) {
		if (position == 0) {
			this.operand[0] = this.operand[1];
			this.operand[1] = null;
	} else
		if (position == 1)
			this.operand[1] = null;
	}
}
