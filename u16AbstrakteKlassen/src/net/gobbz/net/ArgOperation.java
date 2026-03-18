package net.gobbz.net;

public abstract class ArgOperation extends Operand{
	
	public Argument argument;
	public Operand operand;
	
	public ArgOperation(Argument argument, Operand operand)	{
		this.argument = argument;
		this.operand= operand;
	}
	
	public Argument getArgument()	{
		return argument;
	}
	
	public void setArgument(Argument argument)	{
		this.argument = argument;
	}
	
	public Operand getOperand()	{
		return operand;
	}
	
	public void setOperand(Operand operand)	{
		this.operand = operand;
	}
}
