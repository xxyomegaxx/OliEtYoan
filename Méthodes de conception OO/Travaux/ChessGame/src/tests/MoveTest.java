package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.junit.jupiter.api.Test;

import chess.ChessBoard;
import chess.ChessGame;

class MoveTest {

	
	@Test
	public void testBasicCollision() throws Exception {
	ChessGame game=new ChessGame(100,200);
	game.loadBoard(new File("boards/normalStart"));
	ChessBoard result= ChessBoard.readFromFile(new File("boards/normalStart"));
	//Move tower over a pawn of the same color
	game.movePiece("a1-a2");
	assertTrue(game.compareBoard(result));
	}

}
