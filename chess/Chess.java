import java.util.Scanner;

/**
 * @author Dan Harding
 */
public class Chess {

	/**
	 *
	 * @param input: String that represents the player's input
	 * @return file of the piece the player is trying to move
	 */
	public static int getCurrentFile(String input) {
		int crntFile;
		char ch = input.charAt(0);
		if (ch == 'a') crntFile = 0;
		else if (ch == 'b') crntFile = 1;
		else if (ch == 'c') crntFile = 2;
		else if (ch == 'd') crntFile = 3;
		else if (ch == 'e') crntFile = 4;
		else if (ch == 'f') crntFile = 5;
		else if (ch == 'g') crntFile = 6;
		else crntFile = 7;

		return crntFile;
	}

	/**
	 *
	 * @param input: String that represents the player's input
	 * @return rank of the piece the player is trying to move
	 */
	public static int getCurrentRank(String input) {
		int crntRank;
		char ch = input.charAt(1);
		if (ch == '1') crntRank = 0;
		else if (ch == '2') crntRank = 1;
		else if (ch == '3') crntRank = 2;
		else if (ch == '4') crntRank = 3;
		else if (ch == '5') crntRank = 4;
		else if (ch == '6') crntRank = 5;
		else if (ch == '7') crntRank = 6;
		else crntRank = 7;

		return crntRank;
	}

	/**
	 *
	 * @param input: String that represents the player's input
	 * @return file of the place on the board the player is trying to move another piece to
	 */

	public static int getDestinationFile(String input) {
		int endFile;
		char ch = input.charAt(3);
		if (ch == 'a') endFile = 0;
		else if (ch == 'b') endFile = 1;
		else if (ch == 'c') endFile = 2;
		else if (ch == 'd') endFile = 3;
		else if (ch == 'e') endFile = 4;
		else if (ch == 'f') endFile = 5;
		else if (ch == 'g') endFile = 6;
		else endFile = 7;

		return endFile;
	}

	/**
	 *
	 * @param input: String that represents the player's input
	 * @return rank of the place on the board the player is trying to move another piece to
	 */
	public static int getDestinationRank(String input) {
		int endRank;
		char ch = input.charAt(4);
		if (ch == '1') endRank = 0;
		else if (ch == '2') endRank = 1;
		else if (ch == '3') endRank = 2;
		else if (ch == '4') endRank = 3;
		else if (ch == '5') endRank = 4;
		else if (ch == '6') endRank = 5;
		else if (ch == '7') endRank = 6;
		else endRank = 7;

		return endRank;
	}

	/**
	 *
	 * @param input: String that represents the player's input
	 * @return String that consists of the promotion piece type if player can promote, or null if not
	 */
	public static String piecePromote(String input) {
		if (input.length() == 7) {
			char ch = input.charAt(6);
			if (ch == 'N') return "N";
			else if (ch == 'B') return "B";
			else if (ch == 'R') return "R";
			else return "Q";
		} else {
			return "null";
		}

	}

	/**
	 *
	 * @param whiteTurn: true if it is white player's turn; false if it is black player's turn
	 * @return opposite of input, allowing the changing of turns
	 */
	public static boolean changeTurns(boolean whiteTurn) {
		if (whiteTurn) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Input reader as well as turn-based system are implemented in this main method.
	 * The game is played here, and the winner is determined based on different factors.
	 */
	public static void main(String[] args) {
		// TODO put board and input logic here
/**
 * boolean fields to allow for different conditionals to activate, depending on the event
 */
		boolean gameIsStillOn = true;
		boolean whiteTurn = true;
		boolean drawHasBeenProposed = false;
		boolean whiteInCheck = false;
		boolean blackInCheck = false;

		String promotion = "null";

		Board board = new Board();
		Scanner sc = new Scanner(System.in);
		String input;

		//while game is still going on
		while (gameIsStillOn) {
			board.drawBoard();


			//white's turn
			if (whiteTurn) {
				System.out.println("White's move: ");
			} else {
				//black's turn
				System.out.println("Black's move: ");
			}

			input = sc.nextLine();

			//checks if player resigned
			if (input.equals("resign")) {
				if (whiteTurn) {
					System.out.println("Black wins");
					gameIsStillOn = false;
					return;
				} else {
					System.out.println("White wins");
					gameIsStillOn = false;
					return;
				}
			}

			//checks whether a player has proposed a draw
			if (drawHasBeenProposed) {
				if (input.equals("draw")) {
					System.out.println("draw");
					gameIsStillOn = false;
					return;
				} else {
					//draw has been denied
					drawHasBeenProposed = false;
				}
			}

			//when a player proposes a draw
			if (input.length() >= 10 && input.charAt(6) == 'd') {
				drawHasBeenProposed = true;
			}

			//this is for pawn promotion
			if (!piecePromote(input).equals("null")) {
				promotion = piecePromote(input);
			}

			//it is white's turn
			if (whiteTurn) {
				int crntRank = getCurrentRank(input);
				int crntFile = getCurrentFile(input);
				int endRank = getDestinationRank(input);
				int endFile = getDestinationFile(input);

				//this move is valid; checks if current piece is white and if their desired move is valid
				if (board.grid[crntRank][crntFile] != null && board.grid[crntRank][crntFile].isWhite) {
					boolean moveOccurred = board.grid[crntRank][crntFile].executeMove(new Move(endRank, endFile), board);
					if (!moveOccurred) {
						if (whiteInCheck) {
							System.out.println("Checkmate");
							System.out.println("Black wins");
							gameIsStillOn = false;
							return;
						}
						System.out.println("Illegal move, try again");
						System.out.println();
						continue;
					} else {

						//move was successful, now checking if pawn needs promotion
						if (!promotion.equals("null")) {
							if (board.grid[endRank][endFile] instanceof Pawn) {
								Pawn whitePawn = (Pawn) board.grid[endRank][endFile];
								whitePawn.promote(promotion, board);
							}
						}
						System.out.println();
					}
				} else {
					System.out.println("Illegal move, try again");
					System.out.println();
					continue;
				}

				//this finds the bK and places it in the variable blackKing, checking if it is in check
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (board.grid[i][j] instanceof King && !board.grid[i][j].isWhite) {
							King blackKing = (King)board.grid[i][j];
							if (blackKing.isInCheck(board)) {
								blackInCheck = true;
								System.out.println("Check");
								System.out.println();
							}
						}
					}
				}


				//it is black's turn
			} else {
				int crntRank = getCurrentRank(input);
				int crntFile = getCurrentFile(input);
				int endRank = getDestinationRank(input);
				int endFile = getDestinationFile(input);

				if (board.grid[crntRank][crntFile] != null && !board.grid[crntRank][crntFile].isWhite) {
					boolean moveOccurred = board.grid[crntRank][crntFile].executeMove(new Move(endRank, endFile), board);
					if (!moveOccurred) {
						if (blackInCheck) {
							System.out.println("Checkmate");
							System.out.println("White wins");
							gameIsStillOn = false;
							return;
						}
						System.out.println("Illegal move, try again");
						System.out.println();
						continue;
					} else {

						//move was executed, checking if pawn needs promotion
						if (!promotion.equals("null")) {
							if (board.grid[endRank][endFile] instanceof Pawn) {
								Pawn whitePawn = (Pawn) board.grid[endRank][endFile];
								whitePawn.promote(promotion, board);
							}
						}
						System.out.println();
					}
				} else {
					System.out.println("Illegal move, try again");
					System.out.println();
					continue;
				}
			}

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (board.grid[i][j] instanceof King && board.grid[i][j].isWhite) {
						King whiteKing = (King)board.grid[i][j];
						if (whiteKing.isInCheck(board)) {
							whiteInCheck = true;
							System.out.println("Check");
							System.out.println();
						}
					}
				}
			}


				whiteTurn = changeTurns(whiteTurn);
		}


	}

}
