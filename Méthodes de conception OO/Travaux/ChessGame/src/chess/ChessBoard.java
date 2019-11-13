package chess;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import chess.ui.BoardView;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

//Représente la planche de jeu avec les pièces.


public class ChessBoard {
	
	public BoardView view;

	// Grille de jeu 8x8 cases. Contient des références aux piéces présentes sur
	// la grille.
	// Lorsqu'une case est vide, elle contient une pièce spéciale
	// (type=ChessPiece.NONE, color=ChessPiece.COLORLESS).
	private ChessPiece[][] grid;

	public ChessBoard(double x, double y) {
		
		view = new BoardView(x,y);
		
		// Initialise la grille avec des pièces vides.
		grid = new ChessPiece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				grid[i][j] = new ChessPiece(i, j, this);
			}
		}

	}

	// Place une pièce vide dans la case
	public void clearSquare(int x, int y) {
		grid[x][y] = new ChessPiece(x, y, this);
	}

	// Place une pièce sur le planche de jeu.
	public void putPiece(ChessPiece piece) {

		Point2D pos = view.gridToPane(piece.getGridX(), piece.getGridY());
		piece.getUI().relocate(pos.getX(), pos.getY());
		getUI().getChildren().add(piece.getUI());
		grid[piece.getGridX()][piece.getGridY()] = piece;
	}

	public Pane getUI() {
		return view.getPane();
	}

	//Les cases vides contiennent une pièce spéciale
	public boolean isEmpty(Point pos) {
		return (grid[pos.x][pos.y].getType() == ChessUtils.TYPE_NONE);
	}

	//Vérifie si une coordonnée dans la grille est valide
	public boolean isValid(Point pos) {
		return (pos.x >= 0 && pos.x <= 7 && pos.y >= 0 && pos.y <= 7);
	}

	//Vérifie si les pièces à deux positions dans la grille sont de la même couleur.
	public boolean isSameColor(Point pos1, Point pos2) {
		return grid[pos1.x][pos1.y].getColor() == grid[pos2.x][pos2.y].getColor();
	}
	
	//Effectue un mouvement à partir de la notation algébrique des cases ("e2-b5" par exemple)
	public void algebraicMove(String move){
		if(move.length()!=5){
			throw new IllegalArgumentException("Badly formed move");
		}
		String start = move.substring(0,2);
		String end = move.substring(3,5);
		move(ChessUtils.convertAlgebraicPosition(start),ChessUtils.convertAlgebraicPosition(end));
	}
	
	public boolean move(Point2D pos, Point2D newPos) {
		
		Point newGridPos = view.paneToGrid(newPos.getX(), newPos.getY());
		Point gridPos = view.paneToGrid(pos.getX(), pos.getY());
		
		return move(gridPos,newGridPos);
		
	
	}
	
	//Effectue un mouvement sur l'échiqier. Quelques règles de base sont implantées ici.
	public boolean move(Point gridPos, Point newGridPos) {
		ChessPiece toMove = getPiece(gridPos);
		if (!toMove.verifyMove(gridPos, newGridPos)) {
			return false;
			}
		//Vérifie si les coordonnées sont valides
	    else if (!isValid(newGridPos))
			return false;

		//Si la case destination est vide, on peut faire le mouvement
		else if (isEmpty(newGridPos)) {
			assignSquare(newGridPos,toMove);
			clearSquare(gridPos);			
			return true;
		}

		//Si elle est occuppé par une pièce de couleur différente, alors c'est une capture
		else if (!isSameColor(gridPos, newGridPos)) {			
			removePiece(newGridPos);
			assignSquare(newGridPos,toMove);
			clearSquare(gridPos);
			return true;
		}

		return false;
	}
	
	private void assignSquare(Point point ,ChessPiece piece)
	{
		grid[point.x][point.y]= piece;
		grid[point.x][point.y].setGridPos(point);
	}
	
	private void clearSquare(Point point)
	{
		grid[point.x][point.y] = new ChessPiece(point.x, point.y, this);
	}
	
	private void removePiece(Point point)
	{
		getUI().getChildren().remove(grid[point.x][point.y].getUI());
	}
	
	ChessPiece getPiece(Point point)
	{
		return grid[point.x][point.y];
	}
	

	//Fonctions de lecture et de sauvegarde d'échiquier dans des fichiers. À implanter.
	
	public static ChessBoard readFromFile(File file) throws Exception {
		return readFromFile(file, 200, 100);
	}

	public static ChessBoard readFromFile(File file, int x, int y) throws Exception {
		ChessBoard board = new ChessBoard(x,y);
		Scanner sc = new Scanner(file);
		String desc;
		while(sc.hasNext())
		{
			desc = sc.nextLine();
			board.putPiece(ChessPiece.readFromStream(desc, board));
		}
		//throw new Exception("Pas implanté");
		return board;
	}
	
	
	public void saveToFile(File file) throws Exception {

		FileWriter writer = new FileWriter(file);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(!isEmpty(grid[i][j].getGridPos()))
				{
					writer.write(grid[i][j].saveToStream()+"\n");
				}					
				
			}
		}
		writer.close();
		
	}
	
	public Point paneToGrid(Point2D p) {
		return view.paneToGrid(p.getX(), p.getY());

	}

	public Point2D gridToPane(Point p) {
		return view.gridToPane(p.x, p.y);
	
	}
	
	@Override
	public boolean equals(Object o)
	{
		for(int i = 0;i<8;i++)
		{
			for(int j =0;j<8;j++)
			{
				if(grid[i][j].equals(((ChessBoard) o).grid[i][j]))
				{
					
				}
				else return false;
			}
				
		}
		return true;
		
	}

}
