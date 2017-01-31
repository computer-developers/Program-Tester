package lib.dt.inputGenerator.regEx;

import java.util.ArrayList;
import java.util.List;

/**
 * Node class is used here to present a position in the Automata state . Each
 * Node has a nbrChar that present the number of possible characters that could
 * be used to go to the next possible position, and a list of Node that present
 * the next positions.
 * 
 * @author Neel Patel
 *
 */
public class Node {
	private int nbrChar = 1;
	private long nbrMatchedString = 0;
	private List<Node> nextNodes = new ArrayList<Node>();
	private boolean isNbrMatchedStringUpdated;
	private char minChar;
	private char maxChar;

	/**
	 * Calculate the number of string that will be generated until the
	 * transaction presented by this node, and set the result in
	 * <code>nbrMatchedString</code>.
	 */
	public void updateNbrMatchedString() {
		if (isNbrMatchedStringUpdated)
			return;
		if (nextNodes.size() == 0)
			nbrMatchedString = nbrChar;
		else
			for (Node childNode : nextNodes) {
				childNode.updateNbrMatchedString();
				long childNbrChar = childNode.getNbrMatchedString();
				nbrMatchedString += nbrChar * childNbrChar;
			}
		isNbrMatchedStringUpdated = true;
	}

     /**
      *
      * @return
      */
     public List<Node> getNextNodes() {
		return nextNodes;
	}

     /**
      *
      * @param nextNodes
      */
     public void setNextNodes(List<Node> nextNodes) {
		this.nextNodes = nextNodes;
	}

     /**
      *
      * @return
      */
     public int getNbrChar() {
		return nbrChar;
	}

     /**
      *
      * @param nbrChar
      */
     public void setNbrChar(int nbrChar) {
		this.nbrChar = nbrChar;
	}

     /**
      *
      * @return
      */
     public long getNbrMatchedString() {
		return nbrMatchedString;
	}

     /**
      *
      * @param nbrMatchedString
      */
     public void setNbrMatchedString(long nbrMatchedString) {
		this.nbrMatchedString = nbrMatchedString;
	}

     /**
      *
      * @return
      */
     public char getMaxChar() {
		return maxChar;
	}

     /**
      *
      * @param maxChar
      */
     public void setMaxChar(char maxChar) {
		this.maxChar = maxChar;
	}

     /**
      *
      * @return
      */
     public char getMinChar() {
		return minChar;
	}

     /**
      *
      * @param minChar
      */
     public void setMinChar(char minChar) {
		this.minChar = minChar;
	}

}