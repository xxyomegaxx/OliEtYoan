package chess.game;

import java.io.File;

import javafx.scene.Node;

public class ChessGame {

	private int posX;
	private int posY;

	private ChessBoard board;

	public ChessGame() {
		posX = 0;
		posY = 0;
	}

	public ChessGame(int x, int y) {
		posX = x;
		posY = y;
	}
	
	
	public void movePiece(String move){
		if(move.length()!=5){
			throw new IllegalArgumentException("Badly formed move");
		}		
		ChessMove chessMove = new ChessMove(move);
		board.move(chessMove);
	}
	

	public ChessBoard getBoard() {
		return board;
	}

	public Node getUI() {
		return board.getUI();
	}

	// Pour tests
	public void loadBoard(String fileName) throws Exception {

		loadBoardFromFile(new File(fileName));
	}

	public void saveBoardToFile(File file) throws Exception {
		board.saveToFile(file);
	}

	public void loadBoardFromFile(File file) throws Exception {

		ChessBoard newBoard = ChessBoard.readFromFile(file, posX, posY);

		board = newBoard;
	}
	
	public void loadScript(String fileName) throws Exception {
		loadScriptFromFile(new File(fileName));

	}

	public void loadScriptFromFile(File file) throws Exception {
		board = new ChessBoard(posX, posY);
		board.loadMovesFromFile(file);

	}

	public boolean compareBoard(ChessBoard other) {
		
		return board.equals(other);
	}

	public void saveScript(File file) throws Exception {
		board.saveScript(file);
		
	}

}
