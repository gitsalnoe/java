package net.gobbz.net;

import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public abstract class Operand implements TreeNode, MutableTreeNode{
	
	public abstract double getErgebnis();
	
	protected MutableTreeNode parent = null;
	
	@Override
	public void insert(MutableTreeNode child, int index) {
		
	}

	@Override
	public void remove(int index) {
	}

	@Override
	public void remove(MutableTreeNode node) {
	}

	@Override
	public void setUserObject(Object object) {
	}

	@Override
	public void removeFromParent() {
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		this.parent = newParent;
	}
	
	@Override
	public TreeNode getParent() {
		return this.parent;
	}

	@Override
	public boolean isLeaf() {
		return !(this.getChildCount() > 0);
	}
	
	@Override
	public boolean getAllowsChildren() {
		return this.getChildCount() > 0;
	}

	@Override
	public Enumeration<TreeNode> children() {
		return null;
	}
	
}
