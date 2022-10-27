package input;

import collide.Circle;
import collide.Shapes;
import gfx.Display;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class MouseHandler implements MouseListener{
    public static final boolean DISPLAY_PRESS_LOCATION = false;
    public static final boolean DISPLAY_RELEASE_LOCATION = false;
    private final Display display;
    private final List<Circle> circleList;
    private boolean pickedUp = false;
    private Circle pickedCircle;

    public MouseHandler(Display display, List<Circle> circleList){
        this.display = display;
        this.circleList = circleList;
    }
    public void mouseClicked(MouseEvent click){}

    @Override
    public void mousePressed(MouseEvent press){
        if(DISPLAY_PRESS_LOCATION){
            System.out.println("Press[x:" + press.getX() + ", y:" + press.getY()+ "]");
        }

        if(!pickedUp){
            int x = press.getX();
            int y = press.getY();
            for (Circle c : circleList){
                double d = Shapes.distance(c.x, c.y, x, y);
                if(d <= c.r){
                    pickedUp = true;
                    pickedCircle = c;
                    return;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent release){
        if(DISPLAY_RELEASE_LOCATION){
            System.out.println("Release[x:" + release.getX() + ", y:" + release.getY()+ "]");
        }
        if(pickedUp){
            pickedCircle.x = release.getX();
            pickedCircle.y = release.getY();
            display.renderFrame();
            pickedUp = false;
        }
    }

    public void mouseEntered(MouseEvent e){e.consume();}

    public void mouseExited(MouseEvent e){e.consume();}
}
