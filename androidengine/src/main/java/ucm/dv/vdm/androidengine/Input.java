package ucm.dv.vdm.androidengine;

import android.text.method.Touch;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Input implements ucm.dv.vdm.engine.Input, View.OnTouchListener {

    Input (Graphics g){
        _touchEvn = new ArrayList<TouchEvent>();

        _g = g;
    }

    @Override
    public List<TouchEvent> getTouchEvent() {
        List<TouchEvent> tmp;

        synchronized (this) {
            tmp = new ArrayList<TouchEvent>(_touchEvn);
            _touchEvn.clear();
        }

        return tmp;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TouchEvent _te;

        boolean touched = false;

        int x, y;

        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(_g.isInCanvas((int)motionEvent.getX(), (int)motionEvent.getY())){
                    x = _g.reverseRepositionX((int)motionEvent.getX() - _g.getCanvas().getX());
                    y = _g.reverseRepositionY((int)motionEvent.getY() - _g.getCanvas().getY());
                }
                else{
                    x = (int)motionEvent.getX();
                    y = (int)motionEvent.getY();
                }
                _te = new TouchEvent(x, y, TouchEvent.TouchType.CLICKED, motionEvent.getActionIndex());

                synchronized (this){
                    _touchEvn.add(_te);
                }
                touched = true;
                break;
            case MotionEvent.ACTION_UP:
                if(_g.isInCanvas((int)motionEvent.getX(), (int)motionEvent.getY())){
                    x = _g.reverseRepositionX((int)motionEvent.getX() - _g.getCanvas().getX());
                    y = _g.reverseRepositionY((int)motionEvent.getY() - _g.getCanvas().getY());
                }
                else{
                    x = (int)motionEvent.getX();
                    y = (int)motionEvent.getY();
                }
                _te = new TouchEvent(x, y, TouchEvent.TouchType.RELEASED, motionEvent.getActionIndex());

                synchronized (this){
                    _touchEvn.add(_te);
                }
                touched = true;
                break;
        }

        return touched;
    }

    // Singleton
    Input _inp;

    // Event list (Or even Queue)
    List<TouchEvent> _touchEvn;


    // Instance of Graphics for checking position
    Graphics _g;
}
