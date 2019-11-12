package chess;

import java.io.File;
import java.util.function.BooleanSupplier;

public class ChessGame {
	
	//Planche de jeu (incluant les pi�ces)
	private ChessBoard board;
	private int boardPosX;
	private int boardPosY;

	
	public ChessGame(int x,int y)
	{
		board = null;
		boardPosX = x;
		boardPosY = y;
	}

	public ChessBoard getBoard() {
		return board;
	}

	public void loadBoard(File file) throws Exception {
		board = ChessBoard.readFromFile(file,boardPosX,boardPosY);

		
	}

	public void saveBoard(File file) throws Exception {
		board.saveToFile(file);
	}

	public void movePiece(String s) {
		String oldPos = s.substring(0,2);
		String newPos = s.substring(3,5);
		board.move(ChessUtils.convertAlgebraicPosition(oldPos), ChessUtils.convertAlgebraicPosition(newPos));
		
	}

	public boolean compareBoard(ChessBoard result) {
		return board.equals(result);
	}

	

}
