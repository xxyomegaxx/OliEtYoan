package chess.game;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import chess.ui.BoardView;
import javafx.geometry.Point2D;
import javafx.scene.Node;

public class ChessBoard {

	// Grille de jeu 8x8 cases. Contient des références aux pièces présentes sur
	// la grille.
	// Lorsqu'une case est vide, elle contient une pièce spéciale
	// (type=ChessPiece.NONE, color=ChessPiece.COLORLESS).
	private ChessPiece[][] grid;

	private BoardView view;
	
	private ArrayList<ChessMove> ancientMoves = new ArrayList<ChessMove>();
	
	private static BoardMemento initialState;

	public ChessBoard(int x, int y) {

		view = new BoardView(x, y);

		// Initialise la grille avec des pièces vides.
		grid = new ChessPiece[8][8];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new ChessPiece(i, j, this);
			}
		}

	}
	
	
	public ChessBoard(BoardMemento mem)
	{
		this(200,100);
		for(int i=0;i<mem.getSize();i++)
		{
			putPiece(new ChessPiece(mem.getPiece(i)));
		}
	}

	// Place une pièce vide dans la case
	public void clearSquare(int x, int y) {
		grid[x][y] = new ChessPiece(x, y, this);
	}

	// Place une pièce sur le planche de jeu.
	public void putPiece(ChessPiece piece) {

		Point2D pos = view.gridToPane(piece.getGridX(), piece.getGridY());
		piece.moveUI(pos);
		piece.getUI().relocate(pos.getX(), pos.getY());
		view.getPane().getChildren().add(piece.getUI());
		grid[piece.getGridX()][piece.getGridY()] = piece;
	}

	// Vérifie si la case contient une pièce vide
	public boolean isEmpty(Point pos) {
		return (grid[pos.x][pos.y].getType() == ChessUtils.TYPE_NONE);
	}

	// Vérifie si une coordonnée est valide sur la planche de jeu
	public boolean isValid(Point pos) {
		return (pos.x >= 0 && pos.x <= 7 && pos.y >= 0 && pos.y <= 7);
	}

	// Vérifie si deux pièces sont de la même couleur
	public boolean isSameColor(Point pos1, Point pos2) {
		return grid[pos1.x][pos1.y].getColor() == grid[pos2.x][pos2.y].getColor();
	}

	// Déplace une pièce. Appelé par l'interface graphique lorsqu'un déplacement est
	// détecté.
	public boolean move(Point2D pixelPos, Point2D newPixelPos) {

		Point gridPos = view.paneToGrid(pixelPos);

		ChessPiece toMove = grid[gridPos.x][gridPos.y];
		
		ChessMove mov = new ChessMove(view.paneToGrid(pixelPos), view.paneToGrid(newPixelPos));

		boolean result = move(mov);

		toMove.moveUI(view.gridToPane(toMove.getGridPos()));

		return result;
	}

	// Déplace une pièce sur la grille. Vérifie les règles de déplacement.
	public boolean move(ChessMove mov) {

		ChessPiece toMove = getPiece(mov.getDep());

		if (toMove.isNone()) {
			return false;
		}

		if (!isValid(mov.getArr())) {
			return false;
		}

		if (isSameColor(mov.getDep(),mov.getArr())) {
			return false;
		}

		if (!toMove.verifyMove(mov.getDep(),mov.getArr())) {
			return false;
		}

		if (!isEmpty(mov.getArr())) {

			// Capture!
			removePiece(mov.getArr());
		}
		assignSquare(mov.getArr(), toMove);
		clearSquare(mov.getDep());
		ancientMoves.add(mov);

		return true;
	}

	// Lecture et exécution d'une suite de mouvements
	public void loadMovesFromFile(File file) throws Exception {
		// Non implanté!!
	}

	// Lecture d'un ChessBoard à partir d'un fichier. Utilisé par les tests.
	public static ChessBoard readFromFile(String fileName) throws Exception {
		return readFromFile(new File(fileName), 0, 0);
	}
	
	// Lecture d'un ChessBoard à partir d'un fichier
	public static ChessBoard readFromFile(File file, int x, int y) throws Exception {
		
		Scanner reader = new Scanner(new FileReader(file));
		ChessBoard retour = BoardMemento.readFromStream(reader, x, y);
		initialState = new BoardMemento(retour);
		return retour;
		
	}

	//Sauvegarde dans un fichier.
	public void saveToFile(File file) throws Exception {

		FileWriter writer = new FileWriter(file);
		BoardMemento mem = new BoardMemento(this);
		mem.saveToStream(writer);


	}

	@Override
	public boolean equals(Object other) {

		if (other instanceof ChessBoard) {
			ChessBoard otherBoard = (ChessBoard) other;

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (!grid[i][j].equals(otherBoard.grid[i][j])) {
						return false;
					}
				}
			}
			return true;
		}

		return false;

	}

	public void removePiece(Point pos) {

		view.getPane().getChildren().remove(grid[pos.x][pos.y].getUI());
		clearSquare(pos);
	}

	public void clearSquare(Point pos) {
		grid[pos.x][pos.y] = new ChessPiece(pos.x, pos.y, this);
	}

	public void assignSquare(Point pos, ChessPiece piece) {
		piece.setGridPos(pos);
		grid[pos.x][pos.y] = piece;
	}

	public ChessPiece getPiece(Point pos) {
		return grid[pos.x][pos.y];
	}

	public Node getUI() {

		return view.getPane();
	}
	
	public int getLength()
	{
		return grid.length;
	}
	
	public BoardMemento createMemento()
	{
		return new BoardMemento(this);
	}
	
	public void saveScript(File file) throws Exception
	{
		FileWriter writer = new FileWriter(file);
		BoardMemento mem = new BoardMemento(this);
		mem.saveToStream(writer);
		for(int i=0;i<ancientMoves.size();i++)
		{
			ancientMoves.get(i).saveToStream(writer);
		}
		
	}
	public void loadScript(File file) throws Exception
	{
		Scanner reader = new Scanner(new FileReader(file));
		ChessBoard board = new ChessBoard(200, 100);

		while (true) {
			ChessPiece piece;
			try {
				piece = PieceMemento.readFromStream(reader, board);
			} catch (Exception e) {
				break;
			}
			board.putPiece(piece);
		}
		for(int i = 0;i<ancientMoves.size();i++)
		{
			move(ancientMoves.get(i));
		}
	}

}
