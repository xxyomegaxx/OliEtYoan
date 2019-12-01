package chess.game;

import java.awt.Point;

import javax.xml.crypto.NoSuchMechanismException;

import chess.rules.ChessRule;

public class InvalidRule extends ChessRule {
	

	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		throw new NoSuchMechanismException();
	}
	

}