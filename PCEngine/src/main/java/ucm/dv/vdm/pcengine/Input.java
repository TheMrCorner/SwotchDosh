package ucm.dv.vdm.pcengine;

import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Input implements ucm.dv.vdm.engine.Input, MouseListener, KeyListener, MouseMotionListener {

    /**
     * Constructor of the Input System. Singleton
     */
    Input (Window w){
        // Create the TouchEventList
        _touchEvn = new ArrayList<TouchEvent>();
        w.addMouseListener(this);
        w.addKeyListener(this);
        w.addMouseMotionListener(this);
    }

    /**
     * FUnction to get access to the Input.
     * @return Instance of Input
     */
    public Input getInput(Window w){
        if(_inp == null){
            _inp = new Input(w);
        }

        return _inp;
    }

    /**
     * Returns an event of touching something in the window (mouse click etc.) Saves the list in a
     * temporary Variable and then clears it.
     * @return TouchEvent List
     */
    @Override
    public synchronized List<TouchEvent> getTouchEvent() {
        List<TouchEvent> tmp;

        synchronized (this) {
            tmp = new ArrayList<TouchEvent>(_touchEvn);
            _touchEvn.clear();
        }

        return tmp;
    }


    //-------------------------------------------------
    //------------------Mouse Events-------------------
    //-------------------------------------------------

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    /**
     * Gets called when a Mouse button is pressed down but not released.
     * @param mouseEvent MouseEvent received from the Window.
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        // Click Izquierdo pa probar
        if(mouseEvent.getButton() == MouseEvent.BUTTON1) {
            TouchEvent aux = new TouchEvent(mouseEvent.getX(), mouseEvent.getY(), TouchEvent.TouchType.PRESSED_DOWN, 0);
            _touchEvn.add(aux);
        }

        System.out.println("PULSADO EL RATON YAY");
    }

    /**
     * Gets called when a mouse button is released.
     * @param mouseEvent MouseEvent received from the Window.
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseEvent.BUTTON1) {
            TouchEvent aux = new TouchEvent(mouseEvent.getX(), mouseEvent.getY(), TouchEvent.TouchType.RELEASED, 0);
            _touchEvn.add(aux);
        }

        System.out.println("LIBERADO EL RATON YAY");
    }

    /**
     * Gets called when the mouse has entered the screen.
     * @param mouseEvent MouseEvent received from the Window
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    /**
     * Gets called when the mouse has exited the screen.
     * @param mouseEvent MouseEvent received from the Window.
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    /**
     * Gets called when mouse is moved with a button pressed.
     * @param mouseEvent MouseEvent received from the Window.
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * Gets called when the mouse is moved without a button pressed.
     * @param mouseEvent MouseEvent received from the screen.
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        TouchEvent aux = new TouchEvent(mouseEvent.getX(), mouseEvent.getY(), TouchEvent.TouchType.MOVED, 0);
        _touchEvn.add(aux);

        System.out.println("MOUSE MOVED");
    }


    //-------------------------------------------------
    //------------------Keyboard Events----------------
    //-------------------------------------------------

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    //-----------------Atributes---------------
    // Singleton
    Input _inp;

    // Event list (Or even Queue)
    List<TouchEvent> _touchEvn;
}
