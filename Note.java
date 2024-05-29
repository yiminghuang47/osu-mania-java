
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Note {
    private int xLeft;
    private int yTop;
    private int lane;

    public Note(int x, int y,int lane) {
        xLeft = x;
        yTop = y;
        this.lane = lane;
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
        if(lane==0||lane==2) g2.setColor(Color.WHITE);
        else g2.setColor(new Color(61, 165, 255));
        g2.fill(new Rectangle(xLeft, yTop, Sizes.NOTE_WIDTH,Sizes.NOTE_HEIGHT));
        g2.setColor(Color.BLACK);
        g2.draw(new Rectangle(xLeft, yTop, Sizes.NOTE_WIDTH,Sizes.NOTE_HEIGHT));
        
        
    }
}
