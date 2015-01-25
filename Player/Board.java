/**
 * @Author: Mainly Taken from given referee board class
 * @Author: Nicholas Muesch
 * @Author: Dan B. True
 */

package Player;

import java.util.ArrayList;

public class Board {
	
	int width;
	int height;
	int board[][];
	int numOfDiscsInColumn[];
	int emptyCell =0;
	int N;
	int PLAYER1 = 1;
	int PLAYER2 = 2;
	int NOCONNECTION=-1;
	int TIE = 0;
	
	Board(int height, int width, int N)
	{
		this.N = N;
		this.width = width;
		this.height = height;
		board = new int[height][width];
		numOfDiscsInColumn=new int[this.width];
	}
	
	//Returns a new board with the same state as the given board
	public Board clone()
	{
		int i = 0;
		int j = 0;
		Board b = new Board(this.height, this.width, this.N);
		for(i = 0; i < b. height;i++)
		{
			for(j = 0; j < b.width; j++)
			{
				b.board[i][j] = this.board[i][j];
			}
		}
		return b;
	}
	
	
	
	//Given a player, use the current board state to create a list of possible moves
	public ArrayList<Move> getMoves(int player)
	{
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		for(int i = 0; i < this.width; i++)
		{
			Move dropMove = new Move(i, 1);
			if(this.canDropADiscFromTop(dropMove.column, player))
			{
				possibleMoves.add(dropMove);
			}
			Move popMove = new Move(i, 0);
			if(this.canRemoveADiscFromBottom(popMove.column, player))
			{
				possibleMoves.add(popMove);
			}
		}
		return possibleMoves;
	}
	
	
	//Updates the currentState board based on the given move
	//Dropping is 1
	//Popping out is 0
	//Need current player
	public void makeMove(Move move, int playerNum)
	{
		if(move.moveType == 1)
		{
			this.dropADiscFromTop(move.column, playerNum);
		}
		else if(move.moveType == 0)
		{
			this.removeADiscFromBottom(move.column);
		}
	}
	
	//Prints the board to stdout
	 public void printBoard(){
		 System.out.println("Board: ");
		 for(int i=0;i<height;i++){
				for(int j=0;j<width;j++){
					System.out.print(board[i][j]+" ");
				}
				System.out.println();
		 }
	 }
	 
	 //Checks to see if the given player can validly remove a piece from bottom
	 //Check this function, seems weird
	 public boolean canRemoveADiscFromBottom(int col, int currentPlayer){
		 if(col<0 || col>=this.width) {
			 return false;
			 }
		 else if(board[height-1][col]!=currentPlayer){
			 return false;
		 }
		 else 
			 return true;
	 }
	 
	 //Actually remove a disc from the bottom
	 //This currently just alters this board
	 public void removeADiscFromBottom(int col){
		 int i;
		 for(i=height-1;i>height-this.numOfDiscsInColumn[col];i--){
			 board[i][col]=board[i-1][col];
		 }
		 board[i][col]=this.emptyCell;
		 this.numOfDiscsInColumn[col]--;
	 }
	 
	 //Checks to see if dropping move is valid
	 public boolean canDropADiscFromTop(int col, int currentPlayer){
		 if(col<0 || col>=this.width) {
			 return false;
			 }
		 else if(this.numOfDiscsInColumn[col]==this.height){
			 return false;
		 }
		 else
			 return true;
	 }
	 
	 //Alters this board to Drop piece
	 public void dropADiscFromTop(int col, int currentplayer){
		 int firstEmptyCellRow=height-this.numOfDiscsInColumn[col]-1;
		 board[firstEmptyCellRow][col]=currentplayer;
		 this.numOfDiscsInColumn[col]++;
	 }

	 //Checks to see if board is already full
	 public boolean isFull(){
			for(int i=0;i<height;i++)
				for(int j=0;j<width;j++){
					if(board[i][j]==this.emptyCell)
						return false;
				}
			return true;
		}
	 
	 
	 public int isConnectN(){
			int tmp_winner=checkHorizontally();
			
			if(tmp_winner!=this.NOCONNECTION)
				return tmp_winner;
			
			 tmp_winner=checkVertically();
			 if(tmp_winner!=this.NOCONNECTION)
					return tmp_winner;
			 
			 tmp_winner=checkDiagonally1();
			 if(tmp_winner!=this.NOCONNECTION)
					return tmp_winner; 
			 tmp_winner=checkDiagonally2();
			 if(tmp_winner!=this.NOCONNECTION)
					return tmp_winner; 
			 
			 return this.NOCONNECTION;
			 
		 }
		 
	  public int checkHorizontally(){
		  int max1=0;
			 int max2=0;
			 boolean player1_win=false;
			 boolean player2_win=false;
			 //check each row, horizontally
			 for(int i=0;i<this.height;i++){
				 max1=0;
				 max2=0;
				for(int j=0;j<this.width;j++){
					if(board[i][j]==PLAYER1){
						max1++;
						max2=0;
						if(max1==N)
							 player1_win=true;
					}
					else if(board[i][j]==PLAYER2){
						max1=0;
						max2++;
						if(max2==N)
							 player2_win=true;
					}
					else{
						max1=0;
						max2=0;
					}
				}
			 } 
			 if (player1_win && player2_win)
				 return this.TIE;
			 if (player1_win)
				 return this.PLAYER1;
			 if (player2_win)
				 return this.PLAYER2;
			 
			 return this.NOCONNECTION;
	  }

	  public int checkVertically(){
		  //check each column, vertically
		  int max1=0;
		  int max2=0;
		  boolean player1_win=false;
		  boolean player2_win=false;
			 
			 for(int j=0;j<this.width;j++){
				 max1=0;
				 max2=0;
				for(int i=0;i<this.height;i++){
					if(board[i][j]==PLAYER1){
						max1++;
						max2=0;
						if(max1==N)
							 player1_win=true;
					}
					else if(board[i][j]==PLAYER2){
						max1=0;
						max2++;
						if(max2==N)
							player2_win=true;
					}
					else{
						max1=0;
						max2=0;
					}
				}
			 } 
			 if (player1_win && player2_win)
				 return this.TIE;
			 if (player1_win)
				 return this.PLAYER1;
			 if (player2_win)
				 return this.PLAYER2;
			 
			 return this.NOCONNECTION;
	  }
	  
	   public int checkDiagonally1(){
		 //check diagonally y=-x+k
		   int max1=0;
		   int max2=0;
		   boolean player1_win=false;
		   boolean player2_win=false;
		   int upper_bound=height-1+width-1-(N-1);
		   
			 for(int k=N-1;k<=upper_bound;k++){			
				 max1=0;
				 max2=0;
				 int x,y;
				 if(k<width) 
					 x=k;
				 else
					 x=width-1;
				 y=-x+k;
				 
				while(x>=0  && y<height){
					// System.out.println("k: "+k+", x: "+x+", y: "+y);
					if(board[height-1-y][x]==PLAYER1){
						max1++;
						max2=0;
						if(max1==N)
							 player1_win=true;
					}
					else if(board[height-1-y][x]==PLAYER2){
						max1=0;
						max2++;
						if(max2==N)
							player2_win=true;
					}
					else{
						max1=0;
						max2=0;
					}
					x--;
					y++;
				}	 
				 
			 }
			 if (player1_win && player2_win)
				 return this.TIE;
			 if (player1_win)
				 return this.PLAYER1;
			 if (player2_win)
				 return this.PLAYER2;
			 
			 return this.NOCONNECTION;
	   }
		 
	   public int checkDiagonally2(){
		 //check diagonally y=x-k
		   int max1=0;
		   int max2=0;
		   boolean player1_win=false;
		   boolean player2_win=false;
		   int upper_bound=width-1-(N-1);
		   int  lower_bound=-(height-1-(N-1));
		  // System.out.println("lower: "+lower_bound+", upper_bound: "+upper_bound);
			 for(int k=lower_bound;k<=upper_bound;k++){			
				 max1=0;
				 max2=0;
				 int x,y;
				 if(k>=0) 
					 x=k;
				 else
					 x=0;
				 y=x-k;
				while(x>=0 && x<width && y<height){
					// System.out.println("k: "+k+", x: "+x+", y: "+y);
					if(board[height-1-y][x]==PLAYER1){
						max1++;
						max2=0;
						if(max1==N)
							 player1_win=true;
					}
					else if(board[height-1-y][x]==PLAYER2){
						max1=0;
						max2++;
						if(max2==N)
							player2_win=true;
					}
					else{
						max1=0;
						max2=0;
					}
					x++;
					y++;
				}	 
				 
			 }	 //end for y=x-k
			 
			 if (player1_win && player2_win)
				 return this.TIE;
			 if (player1_win)
				 return this.PLAYER1;
			 if (player2_win)
				 return this.PLAYER2;
			 
			 return this.NOCONNECTION;
	   }
		 
		 
		 public void setBoard(int row, int col, int player){
			 if(row>=height || col>=width)
				 throw new IllegalArgumentException("The row or column number is out of bound!");
			 if(player!=this.PLAYER1 && player!=this.PLAYER2)
				 throw new IllegalArgumentException("Wrong player!");
			 this.board[row][col]=player;
		 }
}
