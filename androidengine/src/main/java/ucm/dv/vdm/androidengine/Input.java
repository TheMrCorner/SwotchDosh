package ucm.dv.vdm.androidengine;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to get the Input events from Android and manage them with the usage we want to give them.
 * Implements the Input interface created in engine package and OnTouchListener from Android to
 * receive events and process them to return our own TouchEvent events.
 */
public class Input implements ucm.dv.vdm.engine.Input, View.OnTouchListener {

    /**
     * Constructor. Saves an instance of Graphics to reposition the Event coordinates to the logic
     * canvas so that Logic can process them correctly. Initializes the event list, in which all the
     * events will be stored and returned when asked.
     *
     * @param g Graphics instance.
     */
    Input (Graphics g){
        // Init touch event list
        _touchEvn = new ArrayList<TouchEvent>();

        _g = g;
    } // Input

    /**
     * Returns the TouchEvent list with all the events.
     *
     * @return _touchEvn list.
     */
    @Override
    public List<TouchEvent> getTouchEvent() {
        // Create a temporal list to save the internal list
        List<TouchEvent> tmp;

        synchronized (this) {
            // Save internal list in temporal list
            tmp = new ArrayList<TouchEvent>(_touchEvn);
            // Clear internal list for memory efficiency
            _touchEvn.clear();
        } // synchronized

        // Return the temporal list
        return tmp;
    } // getTouchEvent

    /**
     * OnTouch method. Called when the screen is touched. Gets the android Event and processes it to
     * create our own TouchEvent and save it to the TouchEven list. Repositions the coordinates of
     * the event and gives it a TouchType value.
     *
     * @param view View instance
     * @param motionEvent MotionEvent received from the View
     * @return true if touched, false if not (Android things)
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        // TouchEvent to be created from the MotionEvent
        TouchEvent _te;

        // Check if screen has been touched
        boolean touched = false;

        // Positions for the TouchEvent
        int x, y;

        switch(motionEvent.getAction()){ // Process MotionEvent
            case MotionEvent.ACTION_DOWN:
                // Screen touched
                // Check if it is in canvas
                if(_g.isInCanvas((int)motionEvent.getX(), (int)motionEvent.getY())){
                    // If it is in canvas, reposition coordinates to place them in canvas.
                    x = _g.reverseRepositionX((int)motionEvent.getX() - _g.getCanvas().getX());
                    y = _g.reverseRepositionY((int)motionEvent.getY() - _g.getCanvas().getY());
                }
                else{
                    // If not, don't reposition, is not very important because it won't be processed
                    x = (int)motionEvent.getX();
                    y = (int)motionEvent.getY();
                }
                // Create a new TouchEvent with all the parameters needed
                _te = new TouchEvent(x, y, TouchEvent.TouchType.CLICKED,
                        motionEvent.getActionIndex());

                // Add event to the list
                synchronized (this){
                    // Synchronize all threads with the same list
                    _touchEvn.add(_te);
                } // synchronized

                touched = true;
                break;

            case MotionEvent.ACTION_UP:
                // Finger stopped touching the screen
                // Check if it is in canvas
                if(_g.isInCanvas((int)motionEvent.getX(), (int)motionEvent.getY())){
                    // If it is in canvas, reposition coordinates to place them in canvas.
                    x = _g.reverseRepositionX((int)motionEvent.getX() - _g.getCanvas().getX());
                    y = _g.reverseRepositionY((int)motionEvent.getY() - _g.getCanvas().getY());
                }
                else{
                    // If not, don't reposition, is not very important because it won't be processed
                    x = (int)motionEvent.getX();
                    y = (int)motionEvent.getY();
                }
                // Create a new TouchEvent with all the parameters needed
                _te = new TouchEvent(x, y, TouchEvent.TouchType.RELEASED, motionEvent.getActionIndex());

                // Add that event to the list
                synchronized (this){
                    // Synchronize all threads with same list
                    _touchEvn.add(_te);
                } // synchronized
                touched = true;
                break;
        } // switch

        return touched;
    } // onTouch

    // Event list (Or even Queue)
    List<TouchEvent> _touchEvn;


    // Instance of Graphics for checking position
    Graphics _g;
}
