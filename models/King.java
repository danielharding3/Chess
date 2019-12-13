import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Connor Magee
 *
 */
public class King extends Piece {
	/**
	 * Field that tracks whether the piece has been moved
	 */
	public boolean hasBeenMoved;
	
	public King(int rank, int file, boolean isWhite) {
		super(rank, file, isWhite);
		this.hasBeenMoved = false;
	}
	/**
	 * A method that checks if a move if executed, moves the king into check
	 * @param m: the move that is being evaluated
	 * @param b: the enclosing board
	 * @return whether move m moves the king into check
	 */
	public boolean movesIntoCheck(Move m, Board b) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(b.grid[i][j] == null || b.grid[i][j].equals(this)) continue;
				if(b.grid[i][j].allPossibleMoves(b).contains(m) && b.grid[i][j].isWhite != this.isWhite) return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param b: the enclosing board
	 * @return whether the king is in check
	 */
	public boolean isInCheck(Board b) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(b.grid[i][j] == null || b.grid[i][j].equals(this)) continue;
				if(b.grid[i][j].allPossibleMoves(b).contains(new Move(this.rank, this.file)) && b.grid[i][j].isWhite != this.isWhite) return true;
			}
		}
		return false;
	}

	/**
	 * Inherited from Piece, returns a list of possible moves the piece can make with the current board layout.
	 * For kings, this method strips away moves the king could possibly make but move it into check.
	 */
	@Override
	public List<Move> allPossibleMoves(Board board) {
		List<Move> possibleMoves = new ArrayList<Move>();
		
		if(board.coordinateInside(this.rank + 1, this.file + 1)) {
			if(board.grid[this.rank + 1][this.file + 1] == null || board.grid[this.rank + 1][this.file + 1].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank + 1, this.file + 1));
			}
		}
		
		if(board.coordinateInside(this.rank + 1, this.file)) {
			if(board.grid[this.rank + 1][this.file] == null || board.grid[this.rank + 1][this.file].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank + 1, this.file));
			}
		}
		
		if(board.coordinateInside(this.rank + 1, this.file - 1)) {
			if(board.grid[this.rank + 1][this.file - 1] == null || board.grid[this.rank + 1][this.file - 1].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank + 1, this.file - 1));
			}
		}
		
		if(board.coordinateInside(this.rank, this.file + 1)) {
			if(board.grid[this.rank][this.file + 1] == null || board.grid[this.rank][this.file + 1].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank, this.file + 1));
			}
		}
		
		if(board.coordinateInside(this.rank, this.file - 1)) {
			if(board.grid[this.rank][this.file - 1] == null || board.grid[this.rank][this.file - 1].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank, this.file - 1));
			}
		}
		
		if(board.coordinateInside(this.rank - 1, this.file + 1)) {
			if(board.grid[this.rank - 1][this.file + 1] == null || board.grid[this.rank - 1][this.file + 1].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank - 1, this.file + 1));
			}
		}
		
		if(board.coordinateInside(this.rank - 1, this.file)) {
			if(board.grid[this.rank - 1][this.file] == null || board.grid[this.rank - 1][this.file].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank - 1, this.file));
			}
		}
		
		if(board.coordinateInside(this.rank - 1, this.file - 1)) {
			if(board.grid[this.rank - 1][this.file - 1] == null || board.grid[this.rank - 1][this.file - 1].isWhite != this.isWhite) {
				possibleMoves.add(new Move(this.rank - 1, this.file - 1));
			}
		}
		
		if(board.coordinateInside(this.rank, this.file - 4)) {
			if(this.hasBeenMoved == false && board.grid[this.rank][this.file - 4] instanceof Rook) {
				Rook target = (Rook)board.grid[this.rank][this.file - 4];
				if(target.hasBeenMoved == false && target.isWhite == this.isWhite) {
					possibleMoves.add(new Move(this.rank, this.file - 4));
				}
			}
		}
		
		if(board.coordinateInside(this.rank, this.file + 3)) {
			if(this.hasBeenMoved == false && board.grid[this.rank][this.file + 3] instanceof Rook) {
				Rook target = (Rook)board.grid[this.rank][this.file + 3];
				if(target.hasBeenMoved == false && target.isWhite == this.isWhite) {
					possibleMoves.add(new Move(this.rank, this.file + 3));
				}
			}
		}
		
		possibleMoves.removeIf(m -> this.movesIntoCheck(m, board));
		
		return possibleMoves;
	}
	/**
	 * 
	 * @param b: the enclosing board
	 * @return true if the king cannot make any more moves and is in check, false otherwise
	 */
	public boolean isCheckmate(Board b) {
		return this.isInCheck(b) && this.allPossibleMoves(b).isEmpty();
	}

	/**
	 * Inherited from Piece, manipulates the board layout for the given input. Castling is handled here.
	 */
	@Override
	public boolean executeMove(Move m, Board board) {
		if(this.allPossibleMoves(board).contains(m)) {
			this.hasBeenMoved = true;
			
			if(board.grid[m.endRank][m.endFile] instanceof Rook) {
				Rook target = (Rook)board.grid[m.endRank][m.endFile];
				if(target.hasBeenMoved == false && target.isWhite == this.isWhite) {
					board.grid[this.rank][this.file] = target;
				}
			}else {
				board.grid[this.rank][this.file] = null;
			}
			
			board.grid[m.endRank][m.endFile] = this;
			this.rank = m.endRank;
			this.file = m.endFile;
			return true;
		}
		return false;
	}
	
	/**
	 * returns the representation of the piece needed to be printed to the console
	 */
	public String toString() {
    	return this.isWhite ? "wK" : "bK";
    }

}
