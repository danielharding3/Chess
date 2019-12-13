import java.util.List;
/**
 * 
 * @author Connor Magee
 *
 */
public abstract class Piece {
	/**
	 * General fields for all chess pieces
	 */
	public int rank;
	public int file;
	public boolean isWhite;
	
	public Piece(int rank, int file, boolean isWhite) {
		this.rank = rank;
		this.file = file;
		this.isWhite = isWhite;
	}
	/**
	 * 
	 * @param board: enclosing board for this piece
	 * @return a list of all possible moves this piece can make given its position and the state of the board
	 */
	public abstract List<Move> allPossibleMoves(Board board);
	/**
	 * 
	 * @param m: move to be executed
	 * @param board: enclosing board
	 * @return true if the board can be manipulated correctly to make move m, false otherwise
	 */
	public abstract boolean executeMove(Move m, Board board);
}
