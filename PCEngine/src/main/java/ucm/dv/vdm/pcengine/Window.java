package ucm.dv.vdm.pcengine;

import javax.swing.JFrame;

public class Window extends JFrame{

    /**
     * Window constructor. Sets the values for JFrame, saves Game instance in the atributes and
     * initialize in the init() function with the width and height values.
     *
     * @param width Width of the window
     * @param height Height of the window
     * @param title Title that the window will have
     * @param game An instance of Game to save it in the atribute
     */
    public Window(int width, int height, String title, Game game){
        super(title);
        _game = game;
        init(width, height);
    }

    /**
     * Initialization function for the window. Sets the size the window will have. States
     * EXITO_ON_CLOSE as default close operation and sets fullscreen from the beginning.
     * It is configured to use active painting.
     * Then creates the StrategyBuffer to be used while painting. Sets that BufferStrategy to
     * the Graphics.
     *
     * @param w width of the window
     * @param h height of the window
     */
    public void init(int w, int h) {

        // Set the size of the window and configure it
        setSize(w, h);
        //setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation
        //setExtendedState(JFrame.MAXIMIZED_BOTH); // Set window FullScreen
        //setUndecorated(true);
        setIgnoreRepaint(true); // Active Painting

        // Set window Visible
        setVisible(true);

        // Create BufferStrategy
        int tries = 100; // Try to create the buffer strategy 100 times
        while (tries-- > 0) {
            try { // Ideally it will make this only once
                createBufferStrategy(2);
                break;
            } catch (Exception e) { // Handle Exception if it fails
                _game.HandleException(e);
            }
        }

        // Just for debugging
        if (tries == 0) {
            // if buffer strategy is not created
            System.err.println("BufferStrategy not created");
            // End init (maybe return an error or something?)
            return;
        }

        // Save that buffer strategy (if created)
        synchronized (this) {
            str = getBufferStrategy();

            setGraphics(); // Set the Graphics with the BufferStrategy
        }
    }

    /**
     * Sets the Graphics from the BufferStrategy in order to paint correctly.
     */
    public synchronized void setGraphics(){
        // Get the BufferStrategy created before and set the graphics
        synchronized (this) {
            g = str.getDrawGraphics();
        }
    }

    /**
     * Get the Graphics from Java and return it. Used to draw images and etc.
     * @return Return the Swing library Graphics.
     */
    public java.awt.Graphics getJGraphics (){
        return g;
    }

    //---------------------------------------------------------------
    //----------------------Pivate Atributes-------------------------
    //---------------------------------------------------------------

    /**
     * BufferStrategy created in the init() function.
     */
    java.awt.image.BufferStrategy str;

    /**
     * Graphics from Swing.
     */
    java.awt.Graphics g;

    /**
     * Game instance saved here to be referenced.
     */
    Game _game;


}
