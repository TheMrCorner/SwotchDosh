package ucm.dv.vdm.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import ucm.dv.vdm.engine.Logic;
import ucm.dv.vdm.engine.Rect;

public class Game implements ucm.dv.vdm.engine.Game, Runnable{

    public Game(Context cont){
        DisplayMetrics display = new DisplayMetrics();

        _mSurface = new SurfaceView(cont);

        _holder = _mSurface.getHolder();

        _aMan = cont.getAssets();

        _g = new Graphics(_mSurface, _aMan);

        _ip = new Input();

        // Initialize some time values
        _lastFrameTime = System.nanoTime(); // System time in ms
        _info = _lastFrameTime; // Information about the fps (debug)
        _frames = 0; // Number of frames passed
    }

    @Override
    public Graphics getGraphics() {
        return _g;
    }

    @Override
    public Input getInput() {
        return _ip;
    }

    @Override
    public void setLogic(Logic l) {
        _logic = l;

        _g.setReferenceCanvas(_logic.getCanvasSize());

        resize();
    }

    @Override
    public void HandleException(Exception e) {

    }

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
                }catch(InterruptedException ie){

                } // catch()
            } // while()
        } // if
    } // onPause

    @Override
    public int getWidth() {
        return _mSurface.getWidth();
    }

    @Override
    public int getHeight() {
        return _mSurface.getHeight();
    }

    protected void update(){
        _logic.update(_elapsedTime);
    }

    protected void render(){
        Canvas c = _holder.lockCanvas();
        _logic.render();
        _holder.unlockCanvasAndPost(c);
    }

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

        _g.setCanvasPos(((_mSurface.getWidth()/2) - (_g.getCanvas().getWidth() / 2)), ((_mSurface.getHeight()/2) - (_g.getCanvas().getHeight() / 2)));
    }

    /**
     * It should not be called directly.
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
            while(!_holder.getSurface().isValid());
            render();

        }// while running
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
    int _width;
    int _height;
    int _frames;
    long _info;

}
