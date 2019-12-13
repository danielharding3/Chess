import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan Harding
 */
public class Bishop extends Piece {

    public Bishop(int rank, int file, boolean isWhite) {
        super(rank, file, isWhite);
    }

    /**
     * Inherited from Piece, returns a list of possible moves the piece can make with the current board layout.
     */
    @Override
    public List<Move> allPossibleMoves(Board board) {
        List<Move> moves = new ArrayList<Move>();

        //moving towards top right of board
        for (int i = 1; i < 8; i++) {
            if (board.coordinateInside(this.rank + i, this.file + i)) {
                if (board.grid[this.rank + i][this.file + i] != null) { //spot is occupied
                    if (board.grid[this.rank + i][this.file + i].isWhite != this.isWhite) {
                        Move m = new Move(this.rank + i, this.file + i);
                        moves.add(m);
                    }
                    break;
                } else {
                    Move m = new Move(this.rank + i, this.file + i);
                    moves.add(m);
                }
            }
        }

        //moving towards top left
        for (int i = 1; i < 8; i++) {
            if (board.coordinateInside(this.rank - i, this.file + i)) {
                if (board.grid[this.rank - i][this.file + i] != null) { //spot is occupied
                    if (board.grid[this.rank - i][this.file + i].isWhite != this.isWhite) {
                        Move m = new Move(this.rank - i, this.file + i);
                        moves.add(m);
                    }
                    break;
                } else {
                    Move m = new Move(this.rank - i, this.file + i);
                    moves.add(m);
                }
            }
        }

        //moving towards bottom left
        for (int i = 1; i < 8; i++) {
            if (board.coordinateInside(this.rank - i, this.file - i)) {
                if (board.grid[this.rank - i][this.file - i] != null) { //spot is occupied
                    if (board.grid[this.rank - i][this.file - i].isWhite != this.isWhite) {
                        Move m = new Move(this.rank - i, this.file - i);
                        moves.add(m);
                    }
                    break;
                } else {
                    Move m = new Move(this.rank - i, this.file - i);
                    moves.add(m);
                }
            }
        }

        //moving towards bottom right
        for (int i = 1; i < 8; i++) {
            if (board.coordinateInside(this.rank + i, this.file - i)) {
                if (board.grid[this.rank + i][this.file - i] != null) { //spot is occupied
                    if (board.grid[this.rank + i][this.file - i].isWhite != this.isWhite) {
                        Move m = new Move(this.rank + i, this.file - i);
                        moves.add(m);
                    }
                    break;
                } else {
                    Move m = new Move(this.rank + i, this.file - i);
                    moves.add(m);
                }
            }
        }

        return moves;

    }

    /**
     *Inherited from Piece, manipulates the board layout for the given input.
     */
    @Override
    public boolean executeMove(Move m, Board b) {
        if(this.allPossibleMoves(b).contains(m)) {
            b.grid[m.endRank][m.endFile] = this;
            b.grid[this.rank][this.file] = null;
            this.rank = m.endRank;
            this.file = m.endFile;
            return true;
        }
        return false;
    }

    /**
     *returns the representation of the piece needed to be printed to the console
     */
    public String toString() {
    	return this.isWhite ? "wB" : "bB";
    }
}
