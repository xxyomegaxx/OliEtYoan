package chess.rules;

import java.awt.Point;

public class WhitePawnBigStep extends ChessRule {

	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		int distanceY = newGridPos.y - gridPos.y;

		return (gridPos.y == 6 && distanceY == -2);

	}
}
