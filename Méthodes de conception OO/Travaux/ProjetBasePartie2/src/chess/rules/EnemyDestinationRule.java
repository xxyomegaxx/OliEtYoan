package chess.rules;

import java.awt.Point;

import chess.game.ChessBoard;

public class EnemyDestinationRule extends ChessRule{
	ChessBoard board;
	public EnemyDestinationRule(ChessBoard b)
	{
		board  = b;
	}
	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		if(board.isEmpty(newGridPos)) return false;
		return !(board.isSameColor(gridPos, newGridPos));
	}

}
