package ucm.dv.vdm.androidengine;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import ucm.dv.vdm.engine.Logic;
import ucm.dv.vdm.engine.Rect;

/**
 * Game class. Runs the main loop of the app, calling the necesary methods to update the position
 * of different things and render them on the screen. Also checks the time elapsed between frames
 * and manages all the rendering process (new canvas, wait for Surface to be ready, update holder,
 * etc.)
 */
public class Game implements ucm.dv.vdm.engine.Game, Runnable{

    /**
     * Constructor. Creates a new Game instance and initializes all necessary atributes for it to
     * work. Creates a new SurfaceView with the Activity received and creates an AssetManager.
     * Create the Graphics instance to paint everything correctly on the screen. Create the Input
     * instance to get access to the Input received from the View. Initialize the time values
     * and frame values for debugging and getting information about the app working process.
     * Prepare the SurfaceView to see it Fullscreen and without the upper banner with the app name.
     * Set the content view and the OnTouchListener.
     *
     * @param act Android Activity
     * @param cont Android Context
     */
    public Game(Activity act, Context cont){
        // Create Surface
        _mSurface = new SurfaceView(act);

        // Save the assets to load them later
        _aMan = cont.getAssets();

        // Init Graphics with all values needed
        _g = new Graphics(_mSurface, _aMan);

        // Create the Input
        _ip = new Input(_g);

        // Initialize some time values
        _lastFrameTime = System.nanoTime(); // System time in ms
        _info = _lastFrameTime; // Information about the fps (debug)
        _frames = 0; // Number of frames passed

        // Set surface in fullscreen and not showing the upper banner
        _mSurface.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                        // Inmersion flags and navigation
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide Banner with the name
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        // Set Input as OnTouchListener
        _mSurface.setOnTouchListener(_ip);

        // Set ContentView
        act.setContentView(_mSurface);
    } // Game()

    /**
     * Save the instance of Logic to call the different methods needed to make the game work. Set
     * the Graphics reference canvas to scale images.
     *
     * @param l Logic instance.
     */
    @Override
    public void setLogic(Logic l) {
        _logic = l;

        _g.setReferenceCanvas(_logic.getCanvasSize());

        // Make a first resize to scale the canvas to fit the screen
        resize();

        // Initialize logic
        _logic.initLogic();
    } // setLogic()

    /**
     * Handles an exception (just print out what exception is and its info)
     *
     * @param e Exception
     */
    @Override
    public void HandleException(Exception e) {
        System.err.println(e);
    } // HandleException

    /**
     * Method called when activeRendering needs to be
     * activated again. The game comes back to life.
     */
    public void onResume(){
        if(!_running){ // Check if not running
            // reset running
            _running = true;

            // Initialize thread
            _renderThread = new Thread(this);
            _renderThread.start();
        } // if
    } // onResume()

    /**
     * Method call when active rendering must be stopped.
     * Can take a little to come back because it waits till
     * actual frame is generated.
     *
     * It is made this way to block the UI thread temporarily.
     * This can avoid situations like Android calling resume()
     * before last frame has been generated.
     */
    public void onPause(){
        if(_running){ // Check if it is running...
            _running = false;
            while(true){
                try{
                    _renderThread.join();
                    _renderThread = null;
                    break;
                } // try
                catch(InterruptedException ie){

                } // catch()
            } // while()
        } // if
    } // onPause

    /**
     * Update method. Is called once per frame and updates the logic with the elapsedTime value.
     */
    protected void update(){
        _logic.update(_elapsedTime);
    } // update()

    /**
     * Renders all new information.
     * Initialize the canvas and lock it to paint in it. Starts the frame and calls for Logic's
     * render. Then Unlock canvas and show it on the screen.
     */
    protected void render(){
        // Get holder canvas
        Canvas c = _holder.lockCanvas();

        // Start the frame
        _g.startFrame( c);

        _logic.render();

        // Show the new information
        _holder.unlockCanvasAndPost(c);
    }

    /**
     * Resize canvas to fit the screen. Only called when needed.
     */
    public void resize(){
        Rect temp;
        Rect temp2;

        // RESIZE
        // Get window size (as a rectangle)
        temp2 = new Rect(_mSurface.getWidth(), 0, 0, _mSurface.getHeight());

        // Get Logic's canvas
        temp = _logic.getCanvasSize();

        // Resize the Logic's canvas with that reference
        _g.setCanvasSize(temp, temp2);

        _g.setCanvasPos(((_mSurface.getWidth()/2) - (_g.getCanvas().getWidth() / 2)),
                ((_mSurface.getHeight()/2) - (_g.getCanvas().getHeight() / 2)));
    }

    /**
     * Main loop of the app. Should not be called directly. Updates information about the time
     * elapsed since last frame. Calls for logic update and then renders all. Waits for surface
     * to be ready to draw in it.
     */
    @Override
    public void run() {
        if(_renderThread != Thread.currentThread()){
            // Check if run() is called directly
            throw new RuntimeException("run() should not be called directly, ditch digger");
        }

        while(_running && _mSurface.getWidth() == 0);

        while(_running){
            // Update
            // Calculate time passed between frames and convert it to seconds
            _currentTime = System.nanoTime();
            _nanoElapsedTime = _currentTime - _lastFrameTime;
            _lastFrameTime = _currentTime;
            _elapsedTime = (double) _nanoElapsedTime / 1.0E9;

            //processInput();
            resize();

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

            // Render
            // Update holder.
            _holder = _mSurface.getHolder();

            // Wait till Surface is ready
            while(!_holder.getSurface().isValid());

            render();

        }// while running
    } // run()

    /**
     * Returns the instance of Graphics when needed to draw or making calculations.
     *
     * @return Graphics instance saved here.
     */
    @Override
    public Graphics getGraphics() {
        return _g;
    }

    /**
     * Return Input Instance when needed for processing Input and etc.
     *
     * @return Input instance saved here.
     */
    @Override
    public Input getInput() {
        return _ip;
    }

    /**
     * Return the SurfaceWidth if needed for calculations
     *
     * @return _mSurface Width
     */
    @Override
    public int getWidth() {
        return _mSurface.getWidth();
    }

    /**
     * Return the SurfaceWidth if needed for calculations
     *
     * @return _mSurface Height
     */
    @Override
    public int getHeight() {
        return _mSurface.getHeight();
    }


    //---------------------------------------------------------------
    //----------------------Pivate Atributes-------------------------
    //---------------------------------------------------------------

    // Instances needed to initialize and get everything working.
    SurfaceView _mSurface;
    SurfaceHolder _holder;
    AssetManager _aMan;

    Thread _renderThread;

    volatile boolean _running = false;

    Graphics _g;
    Input _ip;
    Logic _logic;

    // Atributes for calculations and time.
    long _lastFrameTime;
    long _currentTime, _nanoElapsedTime;
    double _elapsedTime;
    int _frames;
    long _info;

}
