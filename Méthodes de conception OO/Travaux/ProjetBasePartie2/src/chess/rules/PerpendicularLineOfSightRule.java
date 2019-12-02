package chess.rules;

import java.awt.Point;

import chess.game.ChessBoard;

public class PerpendicularLineOfSightRule extends ChessRule{
	
	ChessBoard board;
	
	public PerpendicularLineOfSightRule(ChessBoard b)
	{
		board = b;
	}

	@Override
	public boolean veriyMove(Point gridPos, Point newGridPos) {
		Point val;
		if(gridPos.x == newGridPos.x)
		{
			if(gridPos.y > newGridPos.y)
			{
				for(int i=newGridPos.y+1;i<gridPos.y;i++)
				{
					val = new Point(gridPos.x,i);
					if (!(board.isEmpty(val)))return false;				
				}
			}
			else
			{
				for(int i=gridPos.y+1;i<newGridPos.y;i++)
				{
					val = new Point(gridPos.x,i);
					if (!(board.isEmpty(val)))return false;		
				}
			}
		}
		else if(gridPos.y == newGridPos.y)
		{
			if(gridPos.x > newGridPos.x)
			{
				for(int i=newGridPos.x+1;i<gridPos.x;i++)
				{
					val = new Point(i,gridPos.y);
					if (!(board.isEmpty(val)))return false;				
				}
			}
			else
			{
				for(int i=gridPos.x+1;i<newGridPos.x;i++)
				{
					val = new Point(i,gridPos.y);
					if (!(board.isEmpty(val)))return false;		
				}
			}
		}
		
		return true;
	}

}
