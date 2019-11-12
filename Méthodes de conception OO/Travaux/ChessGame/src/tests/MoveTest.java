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
	
	@Test
	public void testPawnLegalMoves() throws Exception
	{
		ChessGame initial = new ChessGame(100,200);
	
		initial.loadBoard(new File("boards/tests/bpawnBasic"));
		initial.movePiece("f5-f4");
		ChessBoard result= ChessBoard.readFromFile(new File("boards/tests/bpawnBasic1"));
		assert(initial.compareBoard(result));
		
		initial.loadBoard(new File("boards/tests/wpawnBasic"));
		initial.movePiece("f5-f6");
		result= ChessBoard.readFromFile(new File("boards/tests/wpawnBasic1"));
		assert(initial.compareBoard(result));		
	}
	
	@Test
	public void testRookLegalMoves() throws Exception
	{
		ChessGame initial = new ChessGame(100,200);
		File initialPos = new File("boards/tests/rookBasic");
	
		initial.loadBoard(initialPos);
		initial.movePiece("e4-e3");
		ChessBoard result= ChessBoard.readFromFile(new File("boards/tests/rookBasic1"));
		assert(initial.compareBoard(result));
		
		initial.loadBoard(initialPos);
		initial.movePiece("e4-a4");
		result= ChessBoard.readFromFile(new File("boards/tests/rookBasic2"));
		assert(initial.compareBoard(result));	
		
		initial.loadBoard(initialPos);
		initial.movePiece("e4-g4");
		result= ChessBoard.readFromFile(new File("boards/tests/rookBasic3"));
		assert(initial.compareBoard(result));
		
		initial.loadBoard(initialPos);
		initial.movePiece("e4-e8");
		result= ChessBoard.readFromFile(new File("boards/tests/rookBasic4"));
		assert(initial.compareBoard(result));
	}
	
	@Test
	public void testKnightLegalMoves() throws Exception
	{
		ChessGame initial = new ChessGame(100,200);
		File initialPos = new File("boards/tests/knightBasic");
	
		initial.loadBoard(initialPos);
		initial.movePiece("f5-g7");
		ChessBoard result= ChessBoard.readFromFile(new File("boards/tests/knightBasic1"));
		assert(initial.compareBoard(result));
		
		initial.loadBoard(initialPos);
		initial.movePiece("f5-h4");
		result= ChessBoard.readFromFile(new File("boards/tests/knightBasic2"));
		assert(initial.compareBoard(result));	
		
		initial.loadBoard(initialPos);
		initial.movePiece("f5-e3");
		result= ChessBoard.readFromFile(new File("boards/tests/knightBasic3"));
		assert(initial.compareBoard(result));
		
		initial.loadBoard(initialPos);
		initial.movePiece("f5-d6");
		result= ChessBoard.readFromFile(new File("boards/tests/knightBasic4"));
		assert(initial.compareBoard(result));
		
	}
	
	@Test
	public void testBishoipLegalMoves() throws Exception
	{
		ChessGame initial = new ChessGame(100,200);
		File initialPos = new File("boards/tests/bishopBasic");
	
		initial.loadBoard(initialPos);
		initial.movePiece("e4-d3");
		ChessBoard result= ChessBoard.readFromFile(new File("boards/tests/bishopBasic1"));
		assert(initial.compareBoard(result));
		
		initial.loadBoard(initialPos);
		initial.movePiece("e4-g2");
		result= ChessBoard.readFromFile(new File("boards/tests/bishopBasic2"));
		assert(initial.compareBoard(result));	
		
		initial.loadBoard(initialPos);
		initial.movePiece("e4-a8");
		result= ChessBoard.readFromFile(new File("boards/tests/bishopBasic3"));
		assert(initial.compareBoard(result));
		
		initial.loadBoard(initialPos);
		initial.movePiece("e4-h7");
		result= ChessBoard.readFromFile(new File("boards/tests/bishopBasic4"));
		assert(initial.compareBoard(result));
		
	}
	
	@Test
	public void testQueenLegalMoves() throws Exception
	{
		ChessGame initial = new ChessGame(100,200);
		File initialPos = new File("boards/tests/queenBasic");
	
		initial.loadBoard(initialPos);
		initial.movePiece("e4-a8");
		ChessBoard result= ChessBoard.readFromFile(new File("boards/tests/queenBasic1"));
		assert(initial.compareBoard(result));
		
		initial.loadBoard(initialPos);
		initial.movePiece("e4-a4");
		result= ChessBoard.readFromFile(new File("boards/tests/queenBasic2"));
		assert(initial.compareBoard(result));	
		
		initial.loadBoard(initialPos);
		initial.movePiece("e4-e3");
		result= ChessBoard.readFromFile(new File("boards/tests/queenBasic3"));
		assert(initial.compareBoard(result));
		
		initial.loadBoard(initialPos);
		initial.movePiece("e4-f3");
		result= ChessBoard.readFromFile(new File("boards/tests/queenBasic4"));
		assert(initial.compareBoard(result));
		
	}
	
	@Test
	public void testKingLegalMoves() throws Exception
	{
		ChessGame initial = new ChessGame(100,200);
		File initialPos = new File("boards/tests/kingBasic");
	
		initial.loadBoard(initialPos);
		initial.movePiece("e4-f5");
		ChessBoard result= ChessBoard.readFromFile(new File("boards/tests/kingBasic1"));
		assert(initial.compareBoard(result));
		
		initial.loadBoard(initialPos);
		initial.movePiece("e4-d4");
		result= ChessBoard.readFromFile(new File("boards/tests/kingBasic2"));
		assert(initial.compareBoard(result));	
		
		initial.loadBoard(initialPos);
		initial.movePiece("e4-d3");
		result= ChessBoard.readFromFile(new File("boards/tests/kingBasic3"));
		assert(initial.compareBoard(result));
		
		initial.loadBoard(initialPos);
		initial.movePiece("e4-e5");
		result= ChessBoard.readFromFile(new File("boards/tests/kingBasic4"));
		assert(initial.compareBoard(result));
		
	}

}
