package chess.game;

import java.awt.Point;
import java.io.Writer;
import java.util.Scanner;

import chess.rules.ChessRule;

public class PieceMemento {
	private int type; 
	private int color;
	private Point pos;
	PieceMemento(ChessPiece p)
	{
		type = p.getType();
		color = p.getColor();
		pos = p.getGridPos();
	}
	
	public void saveToStream(Writer writer ) throws Exception {

		
		writer.append(ChessUtils.makeAlgebraicPosition(pos.x, pos.y));
		writer.append('-');
		writer.append(ChessUtils.makePieceName(color, type));
		writer.append('\n');

	}
	
	public static ChessPiece readFromStream(Scanner reader, ChessBoard b) {
		// LaboX: vider

		String pieceDescription = reader.next();

		if (pieceDescription.length() != 5) {
			throw new IllegalArgumentException("Badly formed Chess Piece description: " + pieceDescription);
		}

		return new ChessPiece(pieceDescription.substring(3, 5), pieceDescription.substring(0, 2), b);

	}
	
	public int getColor()
	{
		return color;
	}
	public int getType()
	{
		return type;
	}
	public Point getPos()
	{
		return pos;
	}
	

	

}
