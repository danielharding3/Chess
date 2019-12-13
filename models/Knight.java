import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Connor Magee
 *
 */
public class Knight extends Piece {

	public Knight(int rank, int file, boolean isWhite) {
		super(rank, file, isWhite);
	}
	/**
	 * Inherited from Piece, returns a list of every possible move a knight can make given the current board layout
	 */
	public List<Move> allPossibleMoves(Board board) {
		List<Move> possibleMoves = new ArrayList<Move>();
		
		if(board.coordinateInside(this.rank + 2, this.file + 1)) {
			if(board.grid[this.rank + 2][this.file + 1] == null || board.grid[this.rank + 2][this.file + 1].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank + 2, this.file + 1));
			}
		}
		
		if(board.coordinateInside(this.rank + 2, this.file - 1)) {
			if(board.grid[this.rank + 2][this.file - 1] == null || board.grid[this.rank + 2][this.file - 1].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank + 2, this.file - 1));
			}
		}
		
		if(board.coordinateInside(this.rank - 2, this.file + 1)) {
			if(board.grid[this.rank - 2][this.file + 1] == null || board.grid[this.rank - 2][this.file + 1].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank - 2, this.file + 1));
			}
		}
		
		if(board.coordinateInside(this.rank - 2, this.file - 1)) {
			if(board.grid[this.rank - 2][this.file - 1] == null || board.grid[this.rank - 2][this.file - 1].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank - 2, this.file - 1));
			}
		}
		
		if(board.coordinateInside(this.rank + 1, this.file + 2)) {
			if(board.grid[this.rank + 1][this.file + 2] == null || board.grid[this.rank + 1][this.file + 2].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank + 1, this.file + 2));
			}
		}
		
		if(board.coordinateInside(this.rank - 1, this.file + 2)) {
			if(board.grid[this.rank - 1][this.file + 2] == null || board.grid[this.rank - 1][this.file + 2].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank - 1, this.file + 2));
			}
		}
		
		if(board.coordinateInside(this.rank + 1, this.file - 2)) {
			if(board.grid[this.rank + 1][this.file - 2] == null || board.grid[this.rank + 1][this.file - 2].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank + 1, this.file - 2));
			}
		}
		
		if(board.coordinateInside(this.rank - 1, this.file - 2)) {
			if(board.grid[this.rank - 1][this.file - 2] == null || board.grid[this.rank - 1][this.file - 2].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank - 1, this.file - 2));
			}
		}
		
		return possibleMoves;
	}

	/**
	 * Inherited from Piece, manipulates the board to move this piece and capture enemy pieces
	 */
	@Override
	public boolean executeMove(Move m, Board board) {
		if(this.allPossibleMoves(board).contains(m)) {
			board.grid[m.endRank][m.endFile] = this;
			board.grid[this.rank][this.file] = null;
			this.rank = m.endRank;
			this.file = m.endFile;
			return true;
		}
		return false;
	}
	/**
	 * returns the string representation of the piece to be printed to the console.
	 */
	public String toString() {
    	return this.isWhite ? "wN" : "bN";
    }

}
