package chess.ui;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import chess.ChessBoard;
import chess.ChessPiece;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PieceView {
	// Utilisé pour générer les noms de fichiers contenant les images des pièces.
	private static final String names[] = { "pawn", "knight", "bishop", "rook", "queen", "king" };
	private static final String prefixes[] = { "w", "b" };

	// Taille d'une pièce dans l'interface
	private static double pieceSize = 75.0;

	// Référence à la planche de jeu. Utilisée pour déplacer la pièce.
	private ChessBoard board;
	// Panneau d'interface contenant l'image de la pièce
	private Pane piecePane;

	public PieceView(ChessBoard b) {
		board = b;
	}

	public PieceView(String name, ChessBoard b, int color, int type) {
		board = b;

		Image pieceImage;
		try {
			pieceImage = new Image(new FileInputStream("images/" + prefixes[color] + names[type] + ".png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		ImageView pieceView = new ImageView(pieceImage);

		pieceView.setX(0);
		pieceView.setY(0);
		pieceView.setFitHeight(pieceSize);
		pieceView.setFitWidth(pieceSize);

		pieceView.setPreserveRatio(true);
		piecePane = new Pane(pieceView);
		enableDragging(piecePane);
		
	}
	public void relocateNode(Point p)
	{
		Point2D paneP = new Point2D(0,0);
		paneP = board.gridToPane(p);
		piecePane.relocate(paneP.getX(), paneP.getY());
	}

	public Pane getPane() {
		return piecePane;
	}

	// Gestionnaire d'événements pour le déplacement des pièces
	private void enableDragging(Node node) {
		final ObjectProperty<Point2D> mouseAnchor = new SimpleObjectProperty<>();
		final ObjectProperty<Point2D> firstPos = new SimpleObjectProperty<>();

		// Lorsque la pièce est saisie, on préserve la position de départ
		node.setOnMousePressed(event -> {
			Point2D start = new Point2D(event.getSceneX(), event.getSceneY());
			mouseAnchor.set(start);
			firstPos.set(start);
			
		});

		// À chaque événement de déplacement, on déplace la pièce et on met à
		// jour la position de départ
		node.setOnMouseDragged(event -> {
			double deltaX = event.getSceneX() - mouseAnchor.get().getX();
			double deltaY = event.getSceneY() - mouseAnchor.get().getY();
			node.relocate(node.getLayoutX() + deltaX, node.getLayoutY() + deltaY);
			node.toFront();
			mouseAnchor.set(new Point2D(event.getSceneX(), event.getSceneY()));

		});

		// Lorsqu'on relâche la pièce, le mouvement correspondant est appliqué
		// au jeu d'échecs si possible.
		// L'image de la pièce est également centrée sur la case la plus proche.
		node.setOnMouseReleased(event -> {
			firstPos.set(board.gridToPane(board.paneToGrid(firstPos.get())));
			Point2D newPos = new Point2D(event.getSceneX(), event.getSceneY());
			
			if((board.move(firstPos.get(), newPos))==false)
			{
				relocateNode(board.paneToGrid(firstPos.get()));
			}
						
		});
	}
}


