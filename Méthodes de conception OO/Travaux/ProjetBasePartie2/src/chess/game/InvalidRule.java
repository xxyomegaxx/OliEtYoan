package chess.game;

import java.awt.Point;

import javax.xml.crypto.NoSuchMechanismException;

public class InvalidRule extends ChessRule {
	
	public InvalidRule(PieceMemento piece) {
		super(piece);
	}

	public InvalidRule() {
		super(ChessUtils.TYPE_NONE,ChessUtils.COLORLESS);
	}

	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		throw new NoSuchMechanismException();
	}
	

}