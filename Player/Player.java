package Player;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Player {

	String playerName="A";
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	boolean first_move=false;
	Random rand = new Random();
	int turnNum = 1;
	
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
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(num +" "+1);
			turnNum++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(ls.size()==1){
			System.out.println("GAME OVER!");
		}
		else if(ls.size()==5){          //ls contains game info
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(num + " 1");  //first movetry
			turnNum++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
