package chess.ui;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import chess.ChessBoard;
import chess.ChessPiece;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BoardView {

	// Les cases ont un peu plus de 80 pixels
	public static final double squareSize = 80.3;

	// La bordure fait 80 pixels tout le tour
	public static final int borderSize = 80;

	// L'arrière plan est dans une fen�tre 800x800.
	public static final int sceneSize = 800;

	// La bordure fait 80 pixels tout le tour
	public static final int pieceDeltaX = 12;
	// La bordure fait 80 pixels tout le tour
	public static final int pieceDeltaY = 2;

	// Panneau d'interface représentant la planche de jeu
	private Pane boardPane;
	private ImageView boardView;
	public double startX;
	public double startY;

	public BoardView(double x, double y) {
		
		startX = x;
		startY = y;

		// Création de l'arrière-plan.
		Image boardImage;
		try {
			boardImage = new Image(new FileInputStream("images/board.jpg"));

		} catch (FileNotFoundException e) {

			return;
		}

		boardView = new ImageView(boardImage);

		boardView.setX(x);
		boardView.setY(y);

		boardView.setFitHeight(sceneSize);
		boardView.setFitWidth(sceneSize);

		boardView.setPreserveRatio(true);

		boardPane = new Pane(boardView);
	}
	
	public Pane getPane() {
		return boardPane;
	}

	public Point paneToGrid(double xPos, double yPos) {
		
		if (xPos < (borderSize + startX))
			xPos = borderSize + startX;
		if (xPos > startX + sceneSize - borderSize)
			xPos = startX + sceneSize - borderSize - (squareSize / 2);
		if (yPos < borderSize + startY)
			yPos = borderSize + startY;
		if (yPos > startY + sceneSize - borderSize)
			yPos = startY + sceneSize - borderSize - (squareSize / 2);
		int xGridPos = (int) ((xPos - (startX + borderSize)) / squareSize);
		int yGridPos = (int) ((yPos - (startY + borderSize)) / squareSize);
		return new Point(xGridPos, yGridPos);
	}

	public Point2D gridToPane(int x, int y) {
	
		if (x < 0 || x > 7 || y < 0 || y > 7)
			throw new IllegalArgumentException("Piece out of grid: (" + x + "," + y + ")");
	
		return new Point2D(startX + x * squareSize + borderSize + pieceDeltaX,
				startY + y * squareSize + borderSize + pieceDeltaY);
	
	}
	
	

}
