/**
 * 
 * @author Connor Magee
 *
 */
public class Move {
	
/**
 * fields that track the end position of the move
 */
	int endRank;
	int endFile;
	
	public Move(int endRank, int endFile) {
		this.endRank = endRank;
		this.endFile = endFile;
	}
	
	/**
	 * Used for contains checks
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Move)) {
			return false;
		}
		
		Move other = (Move)o;
		return other.endFile == this.endFile && other.endRank == this.endRank;
	}
	/**
	 * Used for debugging purposes
	 */
	@Override
	public String toString() {
		return "(" + this.endRank + "," + this.endFile + ")";
	}
}
