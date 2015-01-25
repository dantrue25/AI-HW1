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
	
	public Node(ArrayList<Move> moves) {
		this.moves = moves;
		this.children = null;
	}
	
	public void addChild(Node child){
		children.add(child);
	}
	
	public int getLevel() {
		return moves.size();
	}
	
}
