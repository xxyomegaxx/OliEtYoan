package chess.game;

import java.awt.Point;
import java.io.Writer;
import java.util.Scanner;

public class ChessMove {
	
	private Point dep;
	private Point arr;
	
	ChessMove(Point a,Point b)
	{
		dep = a;
		arr=b;
		
	}
	
	ChessMove(String move)
	{
		String start = move.substring(0,2);
		String end = move.substring(3,5);
		dep = ChessUtils.convertAlgebraicPosition(start);
		arr = ChessUtils.convertAlgebraicPosition(end);
	}
	
	public Point getDep()
	{
		return dep;
	}
	
	public Point getArr()
	{
		return arr;
	}
	
	public void saveToStream(Writer writer ) throws Exception {
		writer.append(ChessUtils.makeAlgebraicPosition(dep.x, dep.y));
		writer.append('-');
		writer.append(ChessUtils.makeAlgebraicPosition(arr.x, arr.y));
		writer.append('\n');	
	}
	
	public static ChessMove readFromStream(Scanner reader) {
		// LaboX: vider

		String moveDescription = reader.next();

		if (moveDescription.length() != 5) {
			throw new IllegalArgumentException("Badly formed Chess Piece description: " + moveDescription);
		}

		return new ChessMove(moveDescription);

	}

}
