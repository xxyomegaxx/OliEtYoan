package chess.rules;

import java.awt.Point;

import chess.game.ChessBoard;

public class EmptyDestinationRule extends ChessRule{
	
	ChessBoard board;
	public EmptyDestinationRule(ChessBoard b)
	{
		board  = b;
	}
	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		return board.isEmpty(newGridPos);
	}
	

}
