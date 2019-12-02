package chess.rules;

import java.awt.Point;

import chess.game.ChessBoard;

public class NoFriendlyFireRule extends ChessRule{
	
	ChessBoard board;
	
	public NoFriendlyFireRule(ChessBoard b)
	{
		board = b;
	}

	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		boolean retour = !(board.isSameColor(gridPos,newGridPos));
		return retour;
	}

}
