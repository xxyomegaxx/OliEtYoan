package chess.rules;

import java.awt.Point;

import chess.game.ChessBoard;

public class HasPieceRule extends ChessRule{
	
	ChessBoard board;
	
	public HasPieceRule(ChessBoard b)
	{
		board = b;
	}

	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		return !(board.getPiece(gridPos).isNone());
	}

}
