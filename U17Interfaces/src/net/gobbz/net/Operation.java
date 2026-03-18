package net.gobbz.net;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

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
	
	@Override
	public TreeNode getChildAt(int childIndex) {
		TreeNode ret = null;
		if (childIndex < 0 || childIndex > 1) {
			ret = null;
		}
		else {
			ret = operand[childIndex];
		}
		return ret;
	}
	
	@Override
	public int getChildCount() {
		int ret = 0;
		if (operand[0] != null) {
			ret++;
		}
		if (operand[1] != null) {
			ret++;
		}
		return ret;
	}

	@Override
	public int getIndex(TreeNode node) {
		int ret = 0;
		if (node.equals(operand[0])) {
			ret = 0;
		}
		else if (node.equals(operand[1])) {
			ret = 1;
		}
		return ret;
	}
	
	/**
	 * liefert kinder als enumeration
	 * @return Enumeration
	 */
	@Override
	public Enumeration<TreeNode> children() {
		List<TreeNode> arrlist = new ArrayList<TreeNode>();
		arrlist.add(getChildAt(0));
		arrlist.add(getChildAt(1));
		return Collections.enumeration(arrlist);
	}
	
	@Override
	public void insert(MutableTreeNode child, int index) {
		this.setOperand((Operand)child);
		//set Parent of child
		child.setParent(this);
	}
	
	@Override
	public void remove(int index) {
		this.loescheOperand(index);
	}
}
