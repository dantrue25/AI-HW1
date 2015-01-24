package Player;

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
	
	
}
