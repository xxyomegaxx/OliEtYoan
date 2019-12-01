package chess.game;

import java.awt.Point;
import java.io.Writer;
import java.util.Scanner;

import chess.rules.ChessRule;
import chess.ui.PieceView;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class ChessPiece {

	// Position de la pièce sur l'échiquier
	private int gridPosX;
	private int gridPosY;

	private int type;
	private int color;
	
	ChessRule rule;

	private PieceView view;

	// Pour créer des pièces à mettre sur les cases vides
	public ChessPiece(int x, int y, ChessBoard b) {
		rule = new InvalidRule();
		this.type = ChessUtils.TYPE_NONE;
		this.color = ChessUtils.COLORLESS;
		gridPosX = x;
		gridPosY = y;
		
		rule = new InvalidRule();

	}

	// Création d'une pièce normale. La position algébrique en notation d'échecs
	// lui donne sa position sur la grille.
	public ChessPiece(String name, String pos, ChessBoard b) {


		color = ChessUtils.getColor(name);
		type = ChessUtils.getType(name);
		view = new PieceView(type, color, b);

		setAlgebraicPos(pos);
		
		rule = ChessRule.createRulesForPiece(this);

	}
	
	ChessPiece(PieceMemento mem,ChessBoard b)
	{
		color=mem.getColor();
		type = mem.getType();
		gridPosX = mem.getPos().x;
		gridPosY = mem.getPos().y;
		view = new PieceView(type, color, b);
		
		rule= ChessRule.createRulesForPiece(this);
	}

	// Règles de mouvements
	public boolean verifyMove(Point gridPos, Point newGridPos) {

		return rule.veriyMove(gridPos,newGridPos);
	}

	public void setAlgebraicPos(String pos) {

		Point pos2d = ChessUtils.convertAlgebraicPosition(pos);

		gridPosX = pos2d.x;
		gridPosY = pos2d.y;
	}

	public int getType() {
		return type;
	}

	public int getColor() {
		return color;
	}

	public int getGridX() {
		return gridPosX;
	}

	public int getGridY() {
		return gridPosY;
	}

	public Point getGridPos() {
		return new Point(gridPosX, gridPosY);
	}

	public void setGridPos(Point pos) {
		gridPosX = pos.x;
		gridPosY = pos.y;
	}

	public Pane getUI() {
		return view.getPane();
	}

	public void moveUI(Point2D pos) {
		if (view != null)
			view.move(pos);
	}

	public boolean isNone() {

		return type == ChessUtils.TYPE_NONE;
	}

	@Override
	public boolean equals(Object other) {

		if (other instanceof ChessPiece) {
			ChessPiece otherPiece = (ChessPiece) other;

			return (color == otherPiece.color && type == otherPiece.type);
		}
		return false;

	}
	
	
	
	public void restoreMemento()
	{
		
	}

}
