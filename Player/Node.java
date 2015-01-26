package Player;

import java.util.ArrayList;


/**
 * 
 * @author Nick
 * @author Dan
 */

public class Node {
	public double heuristic;
	public ArrayList<Move> moves;
	public ArrayList<Node> children;
	public boolean skipSiblings = false;
	
	public Node(ArrayList<Move> moves) {
		this.moves = moves;
		this.children = new ArrayList<Node>();
	}
	
	public void addChild(Node child){
		children.add(child);
	}
	
	public int getLevel() {
		return moves.size();
	}
	
}
