/**
 * 
 * @author Connor Magee
 *
 */
public class Board {
	/**
	 * A grid to hold all chess pieces
	 */
	Piece[][] grid;
	
	/**
	 * Initializes the board with the correct starting positions of each piece
	 */
	public Board() {
		this.grid = new Piece[8][8];
		for(int i = 0; i < 8; i++) {
			this.grid[1][i] = new Pawn(1,i,true);
			this.grid[6][i] = new Pawn(6,i,false);
		}
		
		this.grid[0][0] = new Rook(0,0,true);
		this.grid[0][7] = new Rook(0,7,true);
		this.grid[7][0] = new Rook(7,0,false);
		this.grid[7][7] = new Rook(7,7,false);
		
		this.grid[0][1] = new Knight(0,1,true);
		this.grid[0][6] = new Knight(0,6,true);
		this.grid[7][1] = new Knight(7,1,false);
		this.grid[7][6] = new Knight(7,6,false);
		
		this.grid[0][2] = new Bishop(0,2,true);
		this.grid[0][5] = new Bishop(0,5,true);
		this.grid[7][2] = new Bishop(7,2,false);
		this.grid[7][5] = new Bishop(7,5,false);
		
		this.grid[0][3] = new Queen(0, 3, true);
		this.grid[0][4] = new King(0, 4, true);

		this.grid[7][3] = new Queen(7, 3, false);
		this.grid[7][4] = new King(7, 3, false);
	}
	
	/**
	 * 
	 * @param m: move being evaluated
	 * @return true if the move is within the confines of the chess board, false otherwise
	 */
	public boolean fitsInside(Move m) {
		return m.endFile < 8 && m.endFile >= 0 && m.endRank < 8 && m.endRank >= 0;
	}
	
	
	/**
	 * 
	 * @param row: row position of the coordinate in question
	 * @param col: col position of the coordinate in question
	 * @return whether the queried x,y position is within the confines
	 */
	public boolean coordinateInside(int row, int col) {
		return row < 8 && row >= 0 && col < 8 && col >= 0;
	}
	
	/**
	 * 
	 * @param k: king being evaluated
	 * @return true if king k is in checkmate, false otherwise
	 */
	public boolean checkmate(King k) {
		return k.isInCheck(this) && k.allPossibleMoves(this).isEmpty();
	}
	
	/**
	 * attempts to execute a move for a piece at position (pieceRank, pieceFile) 
	 * @param pieceRank: rank of piece to be moved
	 * @param pieceFile: File of piece to be moved
	 * @param m: Move to be made on the piece
	 * @return true if the piece can be successfully moved to the position from m, false if not possible
	 */
	//and returns true only if the move was successfully executed.
	public boolean executeMove(int pieceRank, int pieceFile, Move m) {
		
		if(this.grid[pieceRank][pieceFile] == null) return false;
		
		if(this.grid[pieceRank][pieceFile].allPossibleMoves(this).contains(m)) {
			this.grid[pieceRank][pieceFile].executeMove(m, this);
			return true;
		}
		return false;
	}

	/**
	 * @author Dan Harding
	 * @param rank: rank of piece on chessboard
	 * @param file: file of piece on chessboard
	 * @return true if there is a piece at this rank and file; false if no piece is located here
	 */
	public boolean isAPieceHere(int rank, int file) {
		if (this.grid[rank][file] == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @author Dan Harding
	 * Draws the board in the console, using "##" to represent dark squares and "  " to represent light squares
	 */
	public void drawBoard() {

		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {

				if (!this.isAPieceHere(i, j)) {
					String s;
					if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
						s = "##";
						System.out.print(s + " ");
					} else {
						s = "  ";
						System.out.print(s + " ");
					}

				} else {
					System.out.print(this.grid[i][j] + " ");
				}

				if (j == 7) {
					System.out.println();
				}
			}
		}
		System.out.println();
	}


}
