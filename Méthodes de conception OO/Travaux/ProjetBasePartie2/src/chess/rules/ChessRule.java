package chess.rules;

import java.awt.Point;

import chess.game.ChessBoard;
import chess.game.ChessPiece;
import chess.game.ChessUtils;

public abstract class ChessRule {
	
	public abstract boolean veriyMove(Point gridPos, Point newGridPos);
	
	public static ChessRule createBoardRules(ChessBoard board)
	{
		AndChessRule temp = new AndChessRule(new NoFriendlyFireRule(board),new HasPieceRule(board));
		return new AndChessRule(temp,new ValidDestinationRule(board));
	}
	
	public static ChessRule createRulesForPiece(ChessPiece piece,ChessBoard board)
	{
		int type = piece.getType();
		int color = piece.getColor();
		switch (type) {

		case ChessUtils.TYPE_PAWN:

			if (color == ChessUtils.WHITE) {
				AndChessRule temp1 = new AndChessRule(new EmptyDestinationRule(board),new WhitePawnBasicRule());
				AndChessRule temp2 = new AndChessRule(new EnemyDestinationRule(board),new WhitePawnCapture());				
				OrChessRule or = or(temp1,temp2);
				OrChessRule or2 = or(or,new WhitePawnBigStep());
				return and(or2,new PerpendicularLineOfSightRule(board));
				
				
			} else if (color == ChessUtils.BLACK) {
				
				AndChessRule temp1 = new AndChessRule(new EmptyDestinationRule(board),new BlackPawnBasicRule());
				AndChessRule temp2 = new AndChessRule(new EnemyDestinationRule(board),new BlackPawnCapture());				
				OrChessRule or = or(temp1,temp2);
				OrChessRule or2 = or(or,new BlackPawnBigStep());
				return and(or2,new PerpendicularLineOfSightRule(board));		}

		case ChessUtils.TYPE_BISHOP:

			return and(new BishopBasicRule(),new DiagonalLineOfSightRule(board));

		case ChessUtils.TYPE_KING:

			return new KingBasicRule();

		case ChessUtils.TYPE_ROOK:

			return and(new RookBasicRule(),new PerpendicularLineOfSightRule(board));

		case ChessUtils.TYPE_QUEEN:
			OrChessRule or = or(new BishopBasicRule(),new RookBasicRule());
			AndChessRule and = and(or,new PerpendicularLineOfSightRule(board));
			return and(and,new DiagonalLineOfSightRule(board));

		case ChessUtils.TYPE_KNIGHT:

			return new KnightBasicRule();

		}
		
		
	    return null;
	}

	public static AndChessRule and(ChessRule r1,ChessRule r2) {
		
		return new AndChessRule(r1,r2);
		}
	
		public static OrChessRule or(ChessRule r1,ChessRule r2) {
		return new OrChessRule(r1,r2);
		}
	

}
