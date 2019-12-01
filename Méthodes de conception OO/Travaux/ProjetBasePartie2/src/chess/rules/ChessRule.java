package chess.rules;

import java.awt.Point;

import chess.game.ChessPiece;
import chess.game.ChessUtils;
import chess.game.PieceMemento;

public abstract class ChessRule {
	
	public abstract boolean veriyMove(Point gridPos, Point newGridPos);
	
	public static ChessRule createRulesForPiece(ChessPiece piece)
	{
		int type = piece.getType();
		int color = piece.getColor();
		switch (type) {

		case ChessUtils.TYPE_PAWN:

			if (color == ChessUtils.WHITE) {
				return new WhitePawnBasicRule();
			} else if (color == ChessUtils.BLACK) {
				return new BlackPawnBasicRule();
			}

		case ChessUtils.TYPE_BISHOP:

			return new BishopBasicRule();

		case ChessUtils.TYPE_KING:

			return new KingBasicRule();

		case ChessUtils.TYPE_ROOK:

			return new RookBasicRule();

		case ChessUtils.TYPE_QUEEN:

			return new QueenBasicRule();

		case ChessUtils.TYPE_KNIGHT:

			return new KnightBasicRule();

		}
	    return null;
	}

	
	

}
