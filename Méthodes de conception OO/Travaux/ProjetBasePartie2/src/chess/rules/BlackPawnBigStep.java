package chess.rules;

import java.awt.Point;

public class BlackPawnBigStep extends ChessRule{

	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		int distanceY = newGridPos.y - gridPos.y;
		
		return (gridPos.y == 1 && distanceY == 2);
	}

}
