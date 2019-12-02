package chess.rules;

import java.awt.Point;

import chess.game.ChessBoard;
import chess.game.ChessPiece;
import chess.game.ChessUtils;

public abstract class ChessRule {
	
	public abstract boolean veriyMove(Point gridPos, Point newGridPos);
	
	public static ChessRule createRulesForPiece(ChessPiece piece,ChessBoard board)
	{
		int type = piece.getType();
		int color = piece.getColor();
		switch (type) {

		case ChessUtils.TYPE_PAWN:

			if (color == ChessUtils.WHITE) {
				AndChessRule temp1 = new AndChessRule(new EmptyDestinationRule(board),new WhitePawnBasicRule());
				AndChessRule temp2 = new AndChessRule(new EnemyDestinationRule(board),new WhitePawnCapture());				
				OrChessRule or = new OrChessRule(temp1,temp2);
				return new OrChessRule(or,new WhitePawnBigStep());
				
				
				
			} else if (color == ChessUtils.BLACK) {
				
				AndChessRule temp1 = new AndChessRule(new EmptyDestinationRule(board),new BlackPawnBasicRule());
				AndChessRule temp2 = new AndChessRule(new EnemyDestinationRule(board),new BlackPawnCapture());				
				OrChessRule or = new OrChessRule(temp1,temp2);
				return new OrChessRule(or,new BlackPawnBigStep());
			}

		case ChessUtils.TYPE_BISHOP:

			return new BishopBasicRule();

		case ChessUtils.TYPE_KING:

			return new KingBasicRule();

		case ChessUtils.TYPE_ROOK:

			return new RookBasicRule();

		case ChessUtils.TYPE_QUEEN:
			return or(new BishopBasicRule(),new RookBasicRule());

		case ChessUtils.TYPE_KNIGHT:

			return new KnightBasicRule();

		}
		
		
	    return null;
	}

	public static ChessRule and(ChessRule r1,ChessRule r2) {
		
		return new AndChessRule(r1,r2);
		}
	
		public static ChessRule or(ChessRule r1,ChessRule r2) {
		return new OrChessRule(r1,r2);
		}
	

}
