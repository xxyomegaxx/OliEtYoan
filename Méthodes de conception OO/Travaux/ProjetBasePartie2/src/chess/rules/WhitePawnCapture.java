package chess.rules;

import java.awt.Point;

public class WhitePawnCapture extends ChessRule{

	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		
		int distanceX = newGridPos.x - gridPos.x;
		int distanceY = newGridPos.y - gridPos.y;
		
		return(Math.abs(distanceX) == 1 && distanceY == -1);
	}
	
	

}
