package net.gobbz.net;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

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
 
    /**
     * liefert Kindknoten am index (0=Argument, 1=Operand) zurück
     */
    @Override
    public TreeNode getChildAt(int childIndex) {
        TreeNode ret = null;
        if (childIndex < 0 || childIndex > 1) {
            ret = null;
        }
        else {
            if (childIndex == 0) {
                ret = argument;
            }
            else if (childIndex == 1){
                ret = operand;
            }
        }
        return ret;
    }
    
    /**
     * liefert die anzahl der Operanden vom knoten
     * @return anzah der Kinder
     */
    @Override
    public int getChildCount() {
        int ret = 0;
        if (argument != null) { 
            ret++;
        }
        if (operand != null) {
            ret++;
        }
        return ret;
    }
        
    /**
     * liefert Index vom kindknoten
     * @param knoten
     * @return Index 0 oder 1
     */
    @Override
    public int getIndex(TreeNode node) {
        int ret = 2;
        if (node.equals(argument)) {
            ret = 0;
        }
        else if (node.equals(operand)) {
            ret = 1;
        }
        return ret;
    }
    
    /**
     * liefert kinder als enumaration
     * @return Die Kinder als Enumeration.
     */
    @Override
    public Enumeration<TreeNode> children() {
        List<TreeNode> arrlist = new ArrayList<TreeNode>();
        TreeNode child0 = getChildAt(0);
        if (child0 != null)	{
        	arrlist.add(child0);
        }
        TreeNode child1 = getChildAt(1);
        if (child1 != null)	{ 
        	arrlist.add(child1);
        }
        return Collections.enumeration(arrlist);
    }
    
}
