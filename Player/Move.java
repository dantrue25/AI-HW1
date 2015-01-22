/**
 * 
 */
package Player;

/**
 * @author Dan
 *
 */
public class Move {
	public int column;
	public int moveType;
	
	/**
	 * Constructor
	 * @param column
	 * @param moveType
	 */
	public Move (int column, int moveType) {
		this.column = column;
		this.moveType = moveType;
	}
	
	/**
	 * Used to pass referee our move
	 */
	public String toString () {
		return "" + column + " " + moveType;
	}

}
