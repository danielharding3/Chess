import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan Harding
 */
public class Queen extends Piece {

    public Queen(int rank, int file, boolean isWhite) {
        super(rank, file, isWhite);
    }

    /**
     *Inherited from Piece, returns a list of possible moves the piece can make with the current board layout.
     */
    @Override
    public List<Move> allPossibleMoves(Board board) {
        List<Move> moves = new ArrayList<Move>();

        //moves vertically up
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

        //moves towards top right of board
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

        //moves horizontally right
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

        //moves towards bottom right of board
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

        //moves vertically down
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

        //moves towards bottom left of board
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

        //moves horizontally left
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

        //moves towards top left of board
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

        return moves;
    }

    /**
     *Inherited from Piece, manipulates the board layout for the given input.
     */
    @Override
    public boolean executeMove(Move m, Board b) {
        if (this.allPossibleMoves(b).contains(m)) {
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
    	return this.isWhite ? "wQ" : "bQ";
    }
}
