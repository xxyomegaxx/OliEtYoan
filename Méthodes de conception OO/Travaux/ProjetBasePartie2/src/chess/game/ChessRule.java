package chess.game;

import java.awt.Point;

public class ChessRule {
	
	int type;
	int color;
	
	public ChessRule(int t,int c)
	{
		type=t;
		color = c;
	}
	public ChessRule(PieceMemento piece) {
		type = piece.getType();
		color = piece.getColor();
	}
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		int distanceX = newGridPos.x - gridPos.x;
		int distanceY = newGridPos.y - gridPos.y;
		switch (type) {

		case ChessUtils.TYPE_PAWN:

			if (color == ChessUtils.WHITE) {
				return (distanceX == 0 && distanceY == -1);
			} else if (color == ChessUtils.BLACK) {
				return (distanceX == 0 && distanceY == 1);
			}

		case ChessUtils.TYPE_BISHOP:

			return (Math.abs(distanceX) == Math.abs(distanceY));

		case ChessUtils.TYPE_KING:

			return (Math.abs(distanceX) <= 1 && Math.abs(distanceY) <= 1);

		case ChessUtils.TYPE_ROOK:

			return (distanceX != 0 && distanceY == 0) || (distanceX == 0 && distanceY != 0);

		case ChessUtils.TYPE_QUEEN:

			return (Math.abs(distanceX) == Math.abs(distanceY)) || (distanceX != 0 && distanceY == 0)
					|| (distanceX == 0 && distanceY != 0);

		case ChessUtils.TYPE_KNIGHT:

			return (Math.abs(distanceX) == 1 && Math.abs(distanceY) == 2
					|| Math.abs(distanceX) == 2 && Math.abs(distanceY) == 1);

		}

		return false;
	}
	
	

}
