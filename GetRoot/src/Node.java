import java.awt.*;
public class Node {
    public Point center;
    public int diameter;
    public int ID;
    public Node(Point center, int diameter, int ID) {
        this.center = center;
        this.diameter = diameter;
        this.ID = ID;
    }
    public void drawNode(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(center.x - diameter / 2, center.y - diameter / 2, diameter, diameter);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(ID), center.x, center.y);
    }
    public boolean isInside(Point point) {
        return (point.x - center.x) * (point.x - center.x) + (point.y - center.y) * (point.y - center.y) <= diameter * diameter / 4;
    }


    public int getID() {
        return ID;
    }

    public void setCenter(Point point) {
        center = point;
    }
public void setDiameter(int diameter) {
        this.diameter = diameter;
    }
    public boolean isInside(Point point, int diameter) {
        for(int i=0;i<diameter/2;i++)
            for(int j=0;j<diameter/2;j++)
            {
                Point p1=new Point(point.x+i,point.y+j);
                Point p2=new Point(point.x-i,point.y-j);
                if (isInside(p1) || isInside(p2))
                    return true;
            }
        return false;
    }
}
