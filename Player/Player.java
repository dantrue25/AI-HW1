package Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Player {

	public static Board currentState;
	String playerName = "B";
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	int height;
	int width;
	int connectN;
	int moveTime;
	int firstPlayer;
	int ourPlayerNum;
	int otherPlayerNum;
	int turnNum = 1;
	boolean first_move=false;
	
	public boolean processInput() throws IOException{	
	
    	String s=input.readLine();	
		List<String> ls=Arrays.asList(s.split(" "));
		
		/*
		 * ls.size() == 5
		 * First move.
		 */
		if(ls.size()==5){
			
			height = Integer.parseInt(ls.get(0));       // (0) Board height
			width =  Integer.parseInt(ls.get(1));       // (1) Board width
			connectN = Integer.parseInt(ls.get(2));     // (2) How many to connect to win
			firstPlayer = Integer.parseInt(ls.get(3));  // (3) Our turn number (1 if going first, 2 if second)
			moveTime = Integer.parseInt(ls.get(4));     // (4) Time allotted for each move
			
			// Create initial board
			currentState = new Board(height, width, connectN);
			
			// If we are going first, make a move, else, do nothing TEST
			if(ourPlayerNum == firstPlayer)
			{
				first_move = true;
				//otherPlayerNum = 2;
				
				//Make a move
				Move firstMove = new Move( (int)(width/2 + 1), 1);
				currentState.makeMove(firstMove, ourPlayerNum);
				System.out.println(firstMove.toString());
			}
			else {
				//otherPlayerNum = 1;
				
				//writer.println("This is not our move!!!");
			}

			turnNum++;
			return true;
			//writer.println("This is the end of our turn!");
		}
		
		/* 
		 * ls.size() == 2
		 * Get move from opponent and add opponents move to currentState.
		 * Then find best next move, add it to currentState, and send next move to referee.
		 */
		else if(ls.size()==2){
			Move oppMove = new Move(Integer.parseInt(ls.get(0)), Integer.parseInt(ls.get(1)));
			currentState.makeMove(oppMove, otherPlayerNum);
			
			Node root = new Node(new ArrayList<Move>());
			
			// Get next move from minimax algorithm
			Move ourNextMove = minimax(root, 4).moves.get(0);
			
			currentState.makeMove(ourNextMove, ourPlayerNum);
			System.out.println(ourNextMove.toString());
			
			turnNum++;
		}
		
		/* 
		 * ls.size() == 1
		 * Game has ended. Stop looping.
		 */
		else if(ls.size()==1){
			System.out.println("GAME OVER!");
			return false;
		}
		
		/*
		 * ls.size() == 4
		 * Receive information on who goes first and second.
		 */
		else if(ls.size()==4){
			if(ls.get(1).equals(playerName)) {
				ourPlayerNum = 1;
				otherPlayerNum = 2;
			}
			else {
				otherPlayerNum = 1;
				ourPlayerNum  = 2;
			}
			// Take out if unnecessary
		}
		
		/*
		 * ls.size() is not one of the options above
		 */
		else
			System.out.println("Not what I want.");
		
		
		return true; // Always return true unless the game is over
	}
	
	/*
	 * Our minimax implementation.
	 * Takes in a node and move list, and returns a move list with the highest
	 * heuristic value. 
	 */
	public Node minimax (Node currentNode, int terminalDepth) {
		Board currentBoardCopy = currentState.clone();
		ArrayList<Move> moveList = currentNode.moves;
		int player = ourPlayerNum;
		
		for(Move m: moveList) {
			currentBoardCopy.makeMove(m, player);
			
			if(player == ourPlayerNum)
				player = otherPlayerNum;
			else
				player = ourPlayerNum;
		}
		
		// If it is a leaf node, calculate heuristic and return it
		if(moveList.size() >= terminalDepth) {
			currentNode.heuristic = currentBoardCopy.getHeuristic();
			return currentNode;
		}
		
		ArrayList<Move> newMoves = currentBoardCopy.getMoves(player);
		
		for(Move newM: newMoves) {
			Node n = new Node(moveList);
			n.moves.add(newM);
			currentNode.addChild(n);
			minimax(n, terminalDepth);
		}
		
		return max(currentNode.children);
	}
	
	/*
	 * Returns the node with the highest heuristic
	 */
	public Node max (ArrayList<Node> children) {
		if (children.isEmpty())
			return null;
		
		double bestSoFar = children.get(0).heuristic;
		Node best = children.get(0);
		
		for(Node n: children) {
			if(n.heuristic > bestSoFar)
				best = n;
		}
		
		return best;
	}
	
	/*
	 * Entry point of player.
	 * Keep processing input until player receives information
	 * that game is over.
	 */
	public static void main(String[] args) throws IOException {
		Player rp=new Player();
		System.out.println(rp.playerName);
		
		boolean continueGame = rp.processInput();
		
		while(continueGame) {
			continueGame = rp.processInput();
		}
	}
}
