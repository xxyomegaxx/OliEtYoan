package chess.rules;

import java.awt.Point;

import chess.game.ChessBoard;

public class DiagonalLineOfSightRule extends ChessRule {

	ChessBoard board;

	public DiagonalLineOfSightRule(ChessBoard b) {
		board = b;
	}

	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {

		int distanceX = newGridPos.x - gridPos.x;
		int distanceY = newGridPos.y - gridPos.y;

		Point val;
		if (distanceX > 0) {
			if (distanceY > 0) {
				for (int i = 1; i < distanceX; i++) {
					val = new Point(gridPos.x + i, gridPos.y + i);
					if (!(board.isEmpty(val)))
						return false;
				}
			} else if (distanceY < 0) {
				for (int i = 1; i < distanceX; i++) {
					val = new Point(gridPos.x + i, gridPos.y - i);
					if (!(board.isEmpty(val)))
						return false;
				}
			}
		} else if (distanceX < 0) {
			if (distanceY > 0) {
				for (int i = 1; i < -distanceX; i++) {
					val = new Point(gridPos.x - i, gridPos.y + i);
					if (!(board.isEmpty(val)))
						return false;
				}
			} else if (distanceY < 0) {
				for (int i = 1; i < -distanceX; i++) {
					val = new Point(gridPos.x - i, gridPos.y - i);
					if (!(board.isEmpty(val)))
						return false;
				}
			}
		}

		return true;
	}

}
