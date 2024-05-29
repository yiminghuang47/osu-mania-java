
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Note {
    private int xLeft;
    private int yTop;

    public Note(int x, int y) {
        xLeft = x;
        yTop = y;
    }
    public void setX(int xLeft){
        this.xLeft = xLeft;
    }
    public void setY(int yTop){
        this.yTop = yTop;
    }
    public int getY(){
        return yTop;
    }
    public int getX(){
        return xLeft;
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fill(new Rectangle(xLeft, yTop, Sizes.NOTE_WIDTH,Sizes.NOTE_HEIGHT));
        g2.setColor(Color.BLACK);
        g2.draw(new Rectangle(xLeft, yTop, Sizes.NOTE_WIDTH,Sizes.NOTE_HEIGHT));
        
        
    }
}
