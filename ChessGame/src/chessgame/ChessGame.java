package chessgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessGame {
    public static LinkedList<Piece> pieces = new LinkedList<>();
    public static Piece selectedPiece = null;

    public static void main(String[] args) throws IOException {
        BufferedImage all = ImageIO.read(new File("C:\\chess.png"));
        Image[] imgs = new Image[12];
        int ind = 0;
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }

        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);
        JPanel panel = createBoard(imgs);
        frame.add(panel);
        addMouseListeners(frame, panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        initializePieces();
    }

    public static JPanel createBoard(Image[] imgs) {
        return new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        g.setColor(white ? new Color(235, 235, 208) : new Color(119, 148, 85));
                        g.fillRect(x * 64, y * 64, 64, 64);
                        white = !white;
                    }
                    white = !white;
                }
                for (Piece p : pieces) {
                    int ind = p.isWhite ? 0 : 6;
                    switch (p.name.toLowerCase()) {
                        case "king":
                            ind += 0;
                            break;
                        case "queen":
                            ind += 1;
                            break;
                        case "bishop":
                            ind += 2;
                            break;
                        case "knight":
                            ind += 3;
                            break;
                        case "rook":
                            ind += 4;
                            break;
                        case "pawn":
                            ind += 5;
                            break;
                    }
                    g.drawImage(imgs[ind], p.x, p.y, this);
                }
            }
        };
    }

    public static void addMouseListeners(JFrame frame, JPanel panel) {
        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedPiece != null) {
                    selectedPiece.x = e.getX() - 32;
                    selectedPiece.y = e.getY() - 32;
                    frame.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                selectedPiece = getPieceAtLocation(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedPiece != null) {
                    selectedPiece.move(e.getX() / 64, e.getY() / 64);
                    frame.repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public static void initializePieces() {
        initializePiecesForColor(0, false);
        initializePiecesForColor(7, true);
    }

    public static void initializePiecesForColor(int row, boolean isWhite) {
        new Piece(0, row, isWhite, "rook", pieces);
        new Piece(1, row, isWhite, "knight", pieces);
        new Piece(2, row, isWhite, "bishop", pieces);
        new Piece(3, row, isWhite, "queen", pieces);
        new Piece(4, row, isWhite, "king", pieces);
        new Piece(5, row, isWhite, "bishop", pieces);
        new Piece(6, row, isWhite, "knight", pieces);
        new Piece(7, row, isWhite, "rook", pieces);
        for (int i = 0; i < 8; i++) {
            new Piece(i, row + (isWhite ? -1 : 1), isWhite, "pawn", pieces);
        }
    }

    public static Piece getPieceAtLocation(int x, int y) {
        int row = x / 64;
        int col = y / 64;
        for (Piece piece : pieces) {
            if (piece.xp == row && piece.yp == col) {
                return piece;
            }
        }
        return null;
    }
}
