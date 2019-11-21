package chess.game;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardMemento {
	ArrayList<PieceMemento> list;
	
	BoardMemento(ChessBoard b)
	{
		list = new ArrayList<PieceMemento>();
		for (int i = 0; i < b.getLength(); i++) {
			for (int j = 0; j < b.getLength(); j++) {
				ChessPiece toWrite = b.getPiece(new Point(i,j));
				if (!toWrite.isNone()) {
					list.add(new PieceMemento(toWrite));
				}
			}
		}
	}
	
	//Sauvegarde dans un fichier.
		public void saveToStream(FileWriter writer) throws Exception {

			for (int j = 0; j < list.size(); j++) {
			    list.get(j).saveToStream(writer);
			}
			//Séparateur. Nécessaire pour la lecture de scripts.
			writer.write("</BOARD>\n");
			writer.close();
		}
		
		public static ChessBoard readFromStream(Scanner reader, int x, int y) throws Exception {

			ChessBoard board = new ChessBoard(x, y);

			while (true) {
				ChessPiece piece;
				try {
					piece = PieceMemento.readFromStream(reader, board);
				} catch (Exception e) {
					break;
				}
				board.putPiece(piece);
			}
			reader.close();
			return board;
		}
		
		public PieceMemento getPiece(int i)
		{
			return list.get(i);
		}
		public int getSize()
		{
			return list.size();
		}


}
