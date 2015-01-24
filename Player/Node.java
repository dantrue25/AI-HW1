package Player;

import java.util.ArrayList;


/**
 * 
 * @author Nick
 * @author Dan
 */

public class Node {
	public double hval;
	public ArrayList<Move> moves;
	public ArrayList<Node> children;
	public int level;
	
	public Node(int hvals, ArrayList<Move> moves) {
		this.hval = hvals;
		this.moves = moves;
		this.children = null;
	}
	
	public void addChild(int hval, ArrayList<Move> moves){
		children.add(new Node(hval, moves));
	}
	
}
