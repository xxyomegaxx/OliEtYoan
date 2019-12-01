package chess.rules;

import java.awt.Point;

import chess.game.ChessUtils;

public class BlackPawnBasicRule extends ChessRule{


	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		
		
		int distanceX = newGridPos.x - gridPos.x;
		int distanceY = newGridPos.y - gridPos.y;
		
		return (distanceX == 0 && distanceY == 1);
		
		
	}

}
