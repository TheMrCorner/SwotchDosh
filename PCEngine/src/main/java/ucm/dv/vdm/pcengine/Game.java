package ucm.dv.vdm.pcengine;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import ucm.dv.vdm.engine.Logic;

/**
 * Class that will control all the game: call the update of the logic to update positions and
 * simulate the game, call render to update graphics on the screen and update the time passed
 * between frames and also store information about the frames.
 */
public class Game implements ucm.dv.vdm.engine.Game, Runnable{

    /**
     * Constructor of the class.
     * Gets information about the graphics device (the screen) and saves it to be used later.
     * Creates the Window, Input and Graphics instances with their own initialisations.
     * Initializes the time values and gets them ready to be used.
     */
    public Game(){

        //Window generation
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        // Window values
        _width = gd.getDisplayMode().getWidth();
        _height = gd.getDisplayMode().getHeight();
        String name = "SwotchDosh";

        // Create the Window
        _win = new Window(_width, _height, name, this);

        // Create the Input instance
        _ip = new Input();

        // Create Graphics instance
        _g = new Graphics(_win);

        // Initialize some time values
        _lastFrameTime = System.nanoTime(); // System time in ms
        _info = _lastFrameTime; // Information about the fps (debug)
        _frames = 0; // Number of frames passed

    }

    /**
     * Calls the logic update and gives it the _elapsedTime for mathematical calculations.
     */
    void update(){ // Deberíamos pensar si esto es realmente útil tho y cambiarlo si eso
        _logic.update(_elapsedTime);
    }

    /**
     * Render function.
     * Clears the buffer to paint in it again.
     * Calls the render from the logic to paint the updated version of the screen.
     * If it fails, it will still paint the screen.  It has 2 loops because it will try to paint
     * the buffer and then show the buffer. Ideally, this won't fail and it will do only 1 iteration
     * per loop.
     */
    void render(){
        //_g.clear(0); // Clear the whole buffer

        // Ideally this will only do 1 iteration per loop
        do {
            do {
                try { // Try to paint in the Graphics
                    _logic.render();
                }
                finally { // If not, still dispose the Swing Graphics
                    _win.getJGraphics().dispose();
                }
            } while(_win.getBufferStrategy().contentsRestored());
            _win.getBufferStrategy().show(); // Show the buffer with the updated information (painted)
        } while(_win.getBufferStrategy().contentsLost());
    }

    /**
     * Saves the Logic in the private atribute to access it and call the render and update function.
     * @param l Logic of the game.
     */
    @Override
    public void setLogic(Logic l){
        _logic = l;
    }

    /**
     * Main loop of the game. Updates time and calls for update and render. Calculates the time pased
     * between frames and provides it to the update.
     */
    @Override
    public void run() {

        //Main Loop
        while(true){
            // Calculate time pased between frames and convert it to seconds (from miliseconds?)
            _currentTime = System.nanoTime();
            _nanoElapsedTime = _currentTime - _lastFrameTime;
            _lastFrameTime = _currentTime;
            _elapsedTime = (double) _nanoElapsedTime / 1.0E9;

            // Update all Logic
            update();

            // Inform about the fps (Debug only)
            if(_currentTime - _info > 1000000000l){
                long fps = _frames * 1000000000l / (_currentTime - _info);
                System.out.println("Info: " + fps + " fps");
                _frames = 0;
                _info = _currentTime;
            }
            ++_frames; // Update frames

            //Clear and update graphics
            render();
        }
        // TODO: Deberíamos pensar alguna manera de poner el loop que no sea un while(true) (?)

    }

    /**
     * Processes the Exception received and shows the information in the System.
     * @param e Exception thrown by anyone
     */
    @Override
    public void HandleException(Exception e){
        System.err.println(e); // Just print the exception. For debugging.
    }

    /**
     * Function to get the width of the screen (or the monitor).
     * @return Width of the screen
     */
    @Override
    public int getWidth(){
        return _width;
    }

    /**
     * Function to get the height of the screen (or the monitor).
     * @return Height of the screen
     */
    @Override
    public int getHeight(){
        return _height;
    }

    /**
     * Returns the Graphics in order to paint things and update the screen.
     * @return Graphics instance saved here.
     */
    @Override
    public Graphics getGraphics() {

        return _g;

    }

    /**
     * Gets the Input class in order to process input from the keyboard and the mouse.
     * @return Input instance saved here.
     */
    @Override
    public Input getInput() {
        return _ip;
    }

    //---------------------------------------------------------------
    //----------------------Pivate Atributes-------------------------
    //---------------------------------------------------------------


    // Instances needed to initialize and get everything working.
    Window _win;
    Graphics _g;
    Input _ip;
    Logic _logic;

    // Atributes for calculations and time.
    long _lastFrameTime;
    long _currentTime, _nanoElapsedTime;
    double _elapsedTime;
    int _width;
    int _height;
    int _frames;
    long _info;

}
