package Player;

import java.util.ArrayList;

public class Board {
	
	int width;
	int height;
	int board[][];
	int numOfDiscsIColumn;
	int emptyCell =0;
	int N;
	int PLAYER1 = 1;
	int PLAYER2 = 2;
	int NOCONNECTION=-1;
	int TIE = 0;
	
	Board(int height, int width, int N)
	{
		this.width = width;
		this.height = height;
		board = new int[height][width];
	}
	
	//Returns a new board with the same state
	public Board clone()
	{
		Board b = new Board(0, 0, 0);
		return b;
	}
	
	
	
	//Given a player, use the current board state to create a list of possible moves
	public ArrayList<Move> getMoves(int player)
	{
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		return possibleMoves;
	}
	
	
	//Updates the currentState board based on the given move
	public void makeMove(Move move)
	{
		
	}
	
}
