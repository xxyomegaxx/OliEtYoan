package chess.rules;

import java.awt.Point;

import chess.game.ChessUtils;

public class KnightBasicRule extends ChessRule{


	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		int distanceX = newGridPos.x - gridPos.x;
		int distanceY = newGridPos.y - gridPos.y;
		
		return (Math.abs(distanceX) == 1 && Math.abs(distanceY) == 2
				|| Math.abs(distanceX) == 2 && Math.abs(distanceY) == 1);
		
	}

}
