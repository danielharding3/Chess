import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Connor Magee
 *
 */
public class Pawn extends Piece {
	
	/**
	 * Boolean variables that control aspects related to pawn movement
	 */
	public boolean twoSpaceAvailable;
	public boolean vulnerableToEnpassant;
	
	public Pawn(int rank, int file, boolean isWhite) {
		super(rank, file, isWhite);
		this.twoSpaceAvailable = true;
	}
	
	public Pawn(int rank, int file, boolean isWhite, boolean vulnerableToEnpassant) {
		super(rank, file, isWhite);
		this.twoSpaceAvailable = false;
		this.vulnerableToEnpassant = vulnerableToEnpassant;
		
	}
	
	
	/**
	 * A method that returns a list of possible moves for the given piece for the board it is contained within.
	 * @param A Board object
	 * @return A list of possible move objects the piece can make based on the board configuration
	 */
	@Override
	public List<Move> allPossibleMoves(Board board) {
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		
		if((this.rank + 1 < 8 && this.isWhite) || (this.rank - 1 >= 0 && !this.isWhite)) {
			Move oneSpace = this.isWhite ? new Move(this.rank + 1, this.file) : new Move(this.rank - 1, this.file);
			if((this.isWhite && board.grid[this.rank + 1][this.file] == null) || (!this.isWhite && board.grid[this.rank - 1][this.file] == null)) {
				possibleMoves.add(oneSpace);
			}
			
			if(board.coordinateInside(this.rank + 1, this.file + 1) && this.isWhite) {
				if(board.grid[this.rank + 1][this.file + 1] != null && !board.grid[this.rank + 1][this.file + 1].isWhite) {
					possibleMoves.add(new Move(this.rank + 1, this.file + 1));
				}
				
				if(board.grid[this.rank][this.file + 1] instanceof Pawn) {
					Pawn target = (Pawn)board.grid[this.rank][this.file + 1];
					if(!board.grid[this.rank][this.file + 1].isWhite && target.vulnerableToEnpassant && board.grid[this.rank + 1][this.file + 1] == null) {
						possibleMoves.add(new Move(this.rank + 1, this.file + 1));
					}
				}
			}
			
			if(board.coordinateInside(this.rank + 1, this.file - 1) && this.isWhite) {
				if(board.grid[this.rank + 1][this.file - 1] != null && !board.grid[this.rank + 1][this.file - 1].isWhite) {
					possibleMoves.add(new Move(this.rank + 1, this.file - 1));
				}
				
				if(board.grid[this.rank][this.file - 1] instanceof Pawn) {
					Pawn target = (Pawn)board.grid[this.rank][this.file - 1];
					if(!target.isWhite && target.vulnerableToEnpassant && board.grid[this.rank + 1][this.file - 1] == null) {
						possibleMoves.add(new Move(this.rank + 1, this.file - 1));
					}
				}
			}
			
			if(board.coordinateInside(this.rank - 1, this.file + 1) && !this.isWhite) {
				if(board.grid[this.rank - 1][this.file + 1] != null && board.grid[this.rank - 1][this.file + 1].isWhite) {
					possibleMoves.add(new Move(this.rank - 1, this.file + 1));
				}
				
				if(board.grid[this.rank][this.file + 1] instanceof Pawn) {
					Pawn target = (Pawn)board.grid[this.rank][this.file + 1];
					if(target.isWhite && target.vulnerableToEnpassant && board.grid[this.rank - 1][this.file + 1] == null) {
						possibleMoves.add(new Move(this.rank - 1, this.file + 1));
					}
				}
			}
			
			if(board.coordinateInside(this.rank - 1, this.file - 1) && !this.isWhite) {
				if(board.grid[this.rank - 1][this.file - 1] != null && board.grid[this.rank - 1][this.file - 1].isWhite) {
					possibleMoves.add(new Move(this.rank - 1, this.file - 1));
				}
				
				if(board.grid[this.rank][this.file - 1] instanceof Pawn) {
					Pawn target = (Pawn)board.grid[this.rank][this.file - 1];
					if(board.grid[this.rank][this.file - 1].isWhite && target.vulnerableToEnpassant && board.grid[this.rank - 1][this.file - 1] == null) {
						possibleMoves.add(new Move(this.rank - 1, this.file - 1));
					}
				}
			}
		}
		
		if(twoSpaceAvailable && (this.rank + 2 < 8 || this.rank - 2 >= 0)) {
			Move twoSpace = this.isWhite ? new Move(this.rank + 2, this.file) : new Move(this.rank - 2, this.file);
			
			if((this.isWhite && board.grid[this.rank + 2][this.file] == null) || (!this.isWhite && board.grid[this.rank - 2][this.file] == null)) {
				possibleMoves.add(twoSpace);
			}
		}
		
		return possibleMoves;
	}
	
	/**
	 * Attempts to move this piece to the end location specified by the given move object
	 * @param The enclosing board
	 * @param The move containing the desired end position for the piece
	 * @return Whether the move was valid for this piece
	 */
	@Override
	public boolean executeMove(Move m, Board b) {
		if(!b.fitsInside(m)) {
			return false;
		}
		
		if(this.allPossibleMoves(b).contains(m)) {
			if(m.endRank == this.rank + 2 || m.endRank == this.rank - 2) {
				this.twoSpaceAvailable = false;
				
				if(b.coordinateInside(m.endRank, m.endFile + 1)) {
					this.vulnerableToEnpassant = b.grid[m.endRank][m.endFile + 1] instanceof Pawn ? true : false;
				}
				
				if(b.coordinateInside(m.endRank, m.endFile - 1)) {
					this.vulnerableToEnpassant = b.grid[m.endRank][m.endFile - 1] instanceof Pawn ? true : false;
				}
			}
			
			b.grid[m.endRank][m.endFile] = this;
			b.grid[this.rank][this.file] = null;
			
			if((m.endRank == this.rank + 1 && m.endFile == this.file + 1) || (m.endRank == this.rank - 1 && m.endFile == this.file + 1)) {
				if(b.grid[this.rank][this.file + 1] instanceof Pawn) {
					Pawn target = (Pawn)b.grid[this.rank][this.file + 1];
					if(target.isWhite != this.isWhite && target.vulnerableToEnpassant) {
						b.grid[this.rank][this.file + 1] = null;
					}
				}
			}
			
			if((m.endRank == this.rank + 1 && m.endFile == this.file - 1) || (m.endRank == this.rank - 1 && m.endFile == this.file - 1)) {
				if(b.grid[this.rank][this.file - 1] instanceof Pawn) {
					Pawn target = (Pawn)b.grid[this.rank][this.file - 1];
					if(target.isWhite != this.isWhite && target.vulnerableToEnpassant) {
						b.grid[this.rank][this.file - 1] = null;
					}
				}
			}
			
			this.rank = m.endRank;
			this.file = m.endFile;
			return true;	
		}
		else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param pieceType: The desired type of piece the player wants to promote this pawn to 
	 * @param b: The enclosing board for this pawn
	 * @return Whether the pawn is in a position to be legally promoted
	 */
	//returns true if the piece is successfully promoted and false if the pawn cannot be promoted at the currennt position
	public boolean promote(String pieceType, Board b) {
		if((this.rank == 7 && this.isWhite == true) || (this.rank == 0 && this.isWhite == false)) {
			
			switch(pieceType) {
				case "R":
					b.grid[this.rank][this.file] = new Rook(this.rank, this.file, this.isWhite, true);
					break;
				case "B":
					b.grid[this.rank][this.file] = new Bishop(this.rank, this.file, this.isWhite);
					break;
				case "N":
					b.grid[this.rank][this.file] = new Knight(this.rank, this.file, this.isWhite);
					break;
				default:
					b.grid[this.rank][this.file] = new Queen(this.rank, this.file, this.isWhite);
					break;
			}
			
			return true;
		}
		return false;
	}
	
	/**
	 * @return returns the format the piece should be written when printed to the console
	 */
	public String toString() {
    	return this.isWhite ? "wp" : "bp";
    }

}
