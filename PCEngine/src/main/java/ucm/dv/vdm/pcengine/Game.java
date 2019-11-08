package ucm.dv.vdm.pcengine;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import ucm.dv.vdm.engine.Logic;

public class Game implements ucm.dv.vdm.engine.Game, Runnable{

    Window _win;
    Graphics _g;
    Input _ip;
    Logic _logic;

    long _lastFrameTime;
    long _currentTime, _nanoElapsedTime;
    double _elapsedTime;

    public Game(){

        //Window generation
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        String name = "SwotchDosh";

        _win = new Window(width, height, name);
        _win.setVisible(true);

        _ip = new Input();
        _g = new Graphics(_win);

        _lastFrameTime = System.nanoTime();


    }

    @Override
    public Graphics getGraphics() {

        return _g;

    }

    @Override
    public Input getInput() {

        return _ip;

    }

    void update(){

        _currentTime = System.nanoTime();
        _nanoElapsedTime = _currentTime - _lastFrameTime;
        _elapsedTime = (double) _nanoElapsedTime / 1E09;
        _lastFrameTime = _currentTime;

        _logic.update(_elapsedTime);
    }

    @Override
    public void setLogic(Logic l){
        _logic = l;
    }

    @Override
    public void run() {

        //Principal Loop
        while(true){

            update();

            //Inicio del frame, render de la lógica y final del frame dentro de un do while (finaliza el frame)

            //Clear and update graphics
            //_g.clear(0);
            _logic.render();

        }

    }

    @Override
    public void HandleException(Exception e){
        System.err.println(e);
    }

}
