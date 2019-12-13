import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dan Harding
 *
 */
public class Rook extends Piece {
    /**
     * Field that checks if piece has been moved
     */
    public boolean hasBeenMoved = false;

    public Rook(int rank, int file, boolean isWhite, boolean hasBeenMoved) {
        super(rank, file, isWhite);
        this.hasBeenMoved = hasBeenMoved;
    }
    
    public Rook(int rank, int file, boolean isWhite) {
    	super(rank, file, isWhite);
    	this.hasBeenMoved = false;
    }

    /**
     * Inherited from Piece, returns a list of possible moves the piece can make with the current board layout.
     */
    @Override
    public List<Move> allPossibleMoves(Board board) {
        List<Move> moves = new ArrayList<Move>();

        //moving vertically up
        for (int i = 1; i < 8; i++) {
            if (board.coordinateInside(this.rank, this.file + i)) {
                if (board.grid[this.rank][this.file + i] != null) {
                    if (board.grid[this.rank][this.file + i].isWhite != this.isWhite) {
                        Move move = new Move(this.rank, this.file + i);
                        moves.add(move);
                    }
                    break;
                } else {
                    Move move = new Move(this.rank, this.file + i);
                    moves.add(move);
                }
            }
        }

        //moving vertically down
        for (int i = 1; i < 8; i++) {
            if (board.coordinateInside(this.rank, this.file - i)) {
                if (board.grid[this.rank][this.file - i] != null) {
                    if (board.grid[this.rank][this.file - i].isWhite != this.isWhite) {
                        Move move = new Move(this.rank, this.file - i);
                        moves.add(move);
                    }
                    break;
                } else {
                    Move move = new Move(this.rank, this.file - i);
                    moves.add(move);
                }
            }
        }

        //moving horizontally left
        for (int i = 1; i < 8; i++) {
            if (board.coordinateInside(this.rank - i, this.file)) {
                if (board.grid[this.rank - i][this.file] != null) {
                    if (board.grid[this.rank - i][this.file].isWhite != this.isWhite) {
                        Move move = new Move(this.rank - i, this.file);
                        moves.add(move);
                    }
                    break;
                } else {
                    Move move = new Move(this.rank - i, this.file);
                    moves.add(move);
                }
            }
        }

        //moving horizontally right
        for (int i = 1; i < 8; i++) {
            if (board.coordinateInside(this.rank + i, this.file)) {
                if (board.grid[this.rank + i][this.file] != null) {
                    if (board.grid[this.rank + i][this.file].isWhite != this.isWhite) {
                        Move move = new Move(this.rank + i, this.file);
                        moves.add(move);
                    }
                    break;
                } else {
                    Move move = new Move(this.rank + i, this.file);
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    /**
     * Inherited from Piece, manipulates the board layout for the given input.
     */
    @Override
    public boolean executeMove(Move m, Board b) {
        if (this.allPossibleMoves(b).contains(m)) {
            this.hasBeenMoved = true;
            b.grid[m.endRank][m.endFile] = this;
            b.grid[this.rank][this.file] = null;
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
    	return this.isWhite ? "wR" : "bR";
    }

}
