package gfx;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.image.BufferStrategy;

/*
PERF TESTS (2D) (rendering 1000 circles per frame):
    |AVG(FPS)|PAGE FLIPPING|BUFFERS|OPENGL FLAG|
    |56	     |true         |2	   |false      |
    |55	     |true         |3	   |false      |
    |50	     |true         |2	   |true       |
    |49	     |false        |3	   |true       |
    ============================================
Conclusion:
    enabling hardware acceleration caused a decrease in terms of overall performance
 */
public abstract class Display extends Canvas{
    static{
        //hardware acceleration is not enabled by default
        //jvm arg: -Dsun.java2d.opengl=true
        System.setProperty("sun.java2d.opengl", "false");
    }
    public JFrame frame;
    public BufferStrategy strategy;
    public final int BUFFERS = 3;
    private int framesDrawn = 0;

    public Display(int width, int height){
        this(width, height, "");
    }
    public Display(int width, int height, String title){
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.black);

        //prevent AWT redrawing the surface (clearing the canvas if subsequent frames are not drawn)
        setIgnoreRepaint(true);

        frame.add(this);
        frame.pack();
        frame.setVisible(true);

        //frame must be visible before buffering strategy can be created
        if(getBufferStrategy() == null){
            createBufferStrategy(BUFFERS);
        }
        strategy = getBufferStrategy();
        printCapabilities(strategy);
    }

    private static void printCapabilities(BufferStrategy strategy){
        BufferCapabilities capabilities = strategy.getCapabilities();
        System.out.println("isPageFlipping: " + capabilities.isPageFlipping());
        System.out.println("isFullScreenRequired: " + capabilities.isFullScreenRequired());
        System.out.println("isMultiBufferAvailable: " + capabilities.isMultiBufferAvailable());
    }

    public abstract void draw(Graphics2D gfx);
    public void renderFrame(){
        Graphics gfx = null;
        try{
            gfx = strategy.getDrawGraphics();
            Graphics2D gfx2D = (Graphics2D) gfx;
            gfx2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            gfx2D.clearRect(0,0, getWidth(), getHeight());
            draw(gfx2D);
            framesDrawn++;
        }finally{
            if(gfx != null){
                gfx.dispose();
            }
        }
        strategy.show();
    }

    public int getFrames(){
        return framesDrawn;
    }
}
