package chessgame;

import java.util.LinkedList;

public class Piece {
    int xp;
    int yp;
    int x;
    int y;
    boolean isWhite;
    LinkedList<Piece> pieces;
    String name;

    public Piece(int xp, int yp, boolean isWhite, String name, LinkedList<Piece> pieces) {
        this.xp = xp;
        this.yp = yp;
        this.x = xp * 64;
        this.y = yp * 64;
        this.isWhite = isWhite;
        this.name = name;
        this.pieces = pieces;
        pieces.add(this);
    }

    public void move(int xp, int yp) {
        Piece pieceAtDestination = ChessGame.getPieceAtLocation(xp * 64, yp * 64);
        if (pieceAtDestination != null) {
            if (pieceAtDestination.isWhite != isWhite) {
                pieceAtDestination.kill();
            } else {
                resetPosition();
                return;
            }
        }
        this.xp = xp;
        this.yp = yp;
        this.x = xp * 64;
        this.y = yp * 64;
    }

    public void kill() {
        pieces.remove(this);
    }

    private void resetPosition() {
        this.x = this.xp * 64;
        this.y = this.yp * 64;
    }
}
