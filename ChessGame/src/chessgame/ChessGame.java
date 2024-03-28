
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
    public static LinkedList<Piece> ps=new LinkedList<>();
    public static Piece selectedPiece = null;
    public static void main(String[] args) throws IOException {
        BufferedImage all=ImageIO.read(new File("C:\\chess.png"));
        Image imgs[]=new Image[12];
       int ind=0;
        for(int y=0;y<400;y+=200){
        for(int x=0;x<1200;x+=200){
            imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
       ind++;
        }    
        }
        
                
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);
        JPanel pn=new JPanel(){
            @Override
            public void paint(Graphics g) {
            boolean white=true;
                for(int y= 0;y<8;y++){
            for(int x= 0;x<8;x++){
                if(white){
                    g.setColor(new Color(235,235, 208));
                }else{
                    g.setColor(new Color(119, 148, 85));
                    
                }
                g.fillRect(x*64, y*64, 64, 64);
           white=!white;
            }
            white=!white;
            }
                for(Piece p: ps){
                    int ind=0;
                    if(p.name.equalsIgnoreCase("king")){
                        ind=0;
                    }
                    if(p.name.equalsIgnoreCase("queen")){
                        ind=1;
                    }
                    if(p.name.equalsIgnoreCase("bishop")){
                        ind=2;
                    }
                    if(p.name.equalsIgnoreCase("knight")){
                        ind=3;
                    }
                    if(p.name.equalsIgnoreCase("rook")){
                        ind=4;
                    }
                    if(p.name.equalsIgnoreCase("pawn")){
                        ind=5;
                    }
                    if(!p.isWhite){
                        ind+=6;
                    }
                    g.drawImage(imgs[ind], p.x, p.y, this);
                }
            }
            
        };
        frame.add(pn);
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e){
                if(selectedPiece != null) {
                    selectedPiece.x = e.getX()-32;
                    selectedPiece.y = e.getY()-32;
                    frame.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        frame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                selectedPiece = getPiece(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedPiece.move(e.getX()/64, e.getY()/64);
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                
            }


            
        });
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

        Piece blackrookPiece=new Piece(0, 0, false, "rook", ps);
        Piece blackknightPiece=new Piece(1, 0, false, "knight", ps);
        Piece blackbishopPiece=new Piece(2, 0, false, "bishop", ps);
        Piece blackqueenPiece=new Piece(3, 0, false, "queen", ps);
        Piece blackkingPiece=new Piece(4, 0, false, "king", ps);
        Piece blackbishopPiece2=new Piece(5, 0, false, "bishop", ps);
        Piece blackknightPIece2=new Piece(6, 0, false, "knight", ps);
        Piece blackrookPiece2=new Piece(7, 0, false, "rook", ps);
        Piece blackpawnPiece1=new Piece(1, 1, false, "pawn", ps);
        Piece blackpawnPiece2=new Piece(2, 1, false, "pawn", ps);
        Piece blackpawnPiece3=new Piece(3, 1, false, "pawn", ps);
        Piece blackpawnPiece4=new Piece(4, 1, false, "pawn", ps);
        Piece blackpawnPiece5=new Piece(5, 1, false, "pawn", ps);
        Piece blackpawnPiece6=new Piece(6, 1, false, "pawn", ps);
        Piece blackpawnPiece7=new Piece(7, 1, false, "pawn", ps);
        Piece blackpawnPiece8=new Piece(0, 1, false, "pawn", ps);
        
        Piece whiterookPiece=new Piece(0, 7, true, "rook", ps);
        Piece whiteknightPiece=new Piece(1, 7, true, "knight", ps);
        Piece whitebishopPiece=new Piece(2, 7, true, "bishop", ps);
        Piece whitequeenPiece=new Piece(3, 7, true, "queen", ps);
        Piece whitekingPiece=new Piece(4, 7, true, "king", ps);
        Piece whitebishopPiece2=new Piece(5, 7, true, "bishop", ps);
        Piece whiteknightPiece2=new Piece(6, 7, true, "knight", ps);
        Piece whiterookPiece2=new Piece(7, 7, true, "rook", ps);
        Piece whitepawnPiece1=new Piece(1, 6, true, "pawn", ps);
        Piece whitepawnPiece2=new Piece(2, 6, true, "pawn", ps);
        Piece whitepawnPiece3=new Piece(3, 6, true, "pawn", ps);
        Piece whitepawnPiece4=new Piece(4, 6, true, "pawn", ps);
        Piece whitepawnPiece5=new Piece(5, 6, true, "pawn", ps);
        Piece whitepawnPiece6=new Piece(6, 6, true, "pawn", ps);
        Piece whitepawnPiece7=new Piece(7, 6, true, "pawn", ps);
        Piece whitepawnPiece8=new Piece(0, 6, true, "pawn", ps);
    }
    public static Piece getPiece(int x, int y) {
        int xp = x/64;
        int yp = y/64;
        for (Piece p: ps){
            if (p.xp == xp && p.yp == yp) {
                return p;
            }
        }
        return null;
    }
}
