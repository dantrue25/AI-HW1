package Player;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Player {

	public Board currentState;
	String playerName="A";
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	boolean first_move=false;
	Random rand = new Random();
	int turnNum = 1;
	int height;
	int width;
	int connectN;
	int moveTime;
	int firstPlayer;
	
	public Player() {
		rand.setSeed(System.currentTimeMillis()%10000);
	}
	
	public void processInput() throws IOException{	
	
		int num = rand.nextInt(6);
    	String s=input.readLine();	
		List<String> ls=Arrays.asList(s.split(" "));
		if(ls.size()==2){
			try
			{
			    String filename= "MyFile.txt";
			    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
			    fw.write(playerName + "\n" + "Turn Num: " + turnNum + "\n" + "Column: " + num + "\n");//appends the string to the file
			    fw.close();
			}
			catch(IOException ioe)
			{
			    System.err.println("IOException: " + ioe.getMessage());
			}
			System.out.println(num +" "+1);
			turnNum++;

		}
		else if(ls.size()==1){
			System.out.println("GAME OVER!");
		}
		else if(ls.size()==5){          //ls contains game info
			width =  Integer.parseInt(ls.get(1));
			height = Integer.parseInt(ls.get(0));
			connectN = Integer.parseInt(ls.get(2));
			moveTime = Integer.parseInt(ls.get(4));
			firstPlayer = Integer.parseInt(ls.get(3));
			currentState = new Board(height, width, connectN);
			if(firstPlayer == 1)
			{
				//Make a move;
				Move firstMove = new Move( (int)(width/2 + 1), 1);
				currentState.makeMove(firstMove);
				first_move = true;
				System.out.println(firstMove.toString());
			}

			
			
			//System.out.println(num + " 1");  //first movetry
			turnNum++;
		}
		else if(ls.size()==4){		//player1: aa player2: bb
			//TODO combine this information with game information to decide who is the first player
		}
		else
			System.out.println("Not what I want.");
		
	}
	
	public static void main(String[] args) throws IOException {
		Player rp=new Player();
		System.out.println(rp.playerName);
		while(true)
			rp.processInput();
	}

}
