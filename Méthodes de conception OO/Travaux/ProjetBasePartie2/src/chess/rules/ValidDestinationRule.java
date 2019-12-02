package chess.rules;

import java.awt.Point;

import chess.game.ChessBoard;

public class ValidDestinationRule extends ChessRule{
	
	ChessBoard board;
	
	public ValidDestinationRule(ChessBoard b)
	{
		board = b;
	}

	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		return board.isValid(newGridPos);
	}

}
