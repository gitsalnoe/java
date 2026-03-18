package net.gobbz.net;

import javax.swing.tree.TreeNode;

public class Konstante extends Operand {
	
	private double ergebnis = 0.0;

	public Konstante(double ergebnis) {
		this.ergebnis = ergebnis;
	}
	
	public Konstante() {
		super();
	}
	public void setErgebnis(double ergebnis) {
		this.ergebnis = ergebnis;
	}
	public double getErgebnis() {
		return this.ergebnis;
	}
	public String toString() {
		return String.valueOf(this.ergebnis);
	}
	
	@Override
	public TreeNode getChildAt(int childIndex) {
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public int getIndex(TreeNode node) {
		return 0;
	}

	@Override
	public void setUserObject(Object object) {
		this.setErgebnis(Double.parseDouble(object.toString()));
	}
}
