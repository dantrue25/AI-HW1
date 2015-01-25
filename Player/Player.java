package Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Player {

	public static Board currentState;
	String playerName = "A";
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	int height;
	int width;
	int connectN;
	int moveTime;
	
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
			ourPlayerNum = Integer.parseInt(ls.get(3)); // (3) Our turn number (1 if going first, 2 if second)
			moveTime = Integer.parseInt(ls.get(4));     // (4) Time allotted for each move
			
			// Create initial board
			currentState = new Board(height, width, connectN);
			
			// If we are going first, make a move, else, do nothing TEST
			if(ourPlayerNum == 1)
			{
				first_move = true;
				otherPlayerNum = 2;
				
				//Make a move
				Move firstMove = new Move( (int)(width/2 + 1), 1);
				currentState.makeMove(firstMove, ourPlayerNum);
				System.out.println(firstMove.toString());
			}
			else {
				otherPlayerNum = 1;
			}

			turnNum++;
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
			Move ourNextMove = minimax(root, new ArrayList<Move>()).get(0);
			
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
		 * Not sure if this exists.
		 */
		else if(ls.size()==4){
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
	public ArrayList<Move> minimax (Node currentNode, ArrayList<Move> moveList) {
		
		// Stub function. Not implemented
		Move m = new Move(1, 1);
		moveList.add(m);
		
		return moveList;
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
