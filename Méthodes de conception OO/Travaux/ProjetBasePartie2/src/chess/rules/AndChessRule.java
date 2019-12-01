package chess.rules;

import java.awt.Point;

public class AndChessRule extends ChessRule{
	
	ChessRule rule1;
	ChessRule rule2;
	
	AndChessRule(ChessRule r1,ChessRule r2)
	{
		rule1 = r1;
		rule2 = r2;
	}

	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		return (rule1.veriyMove(gridPos,newGridPos) && rule2.veriyMove(gridPos,newGridPos));
	}

}
