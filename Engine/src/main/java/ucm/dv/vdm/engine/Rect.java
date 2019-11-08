package ucm.dv.vdm.engine;

public class Rect {

    // Pixel where it begins
    public int _right = 0;
    public int _left = 0;
    public int _top = 0;
    public int _bottom = 0;

    // MEH
    public int width = 0;
    public int height = 0;

    // Constructor
    public Rect(int r, int l, int t, int b){
        _right = r;
        _left = l;
        _top = t;
        _bottom = b;
    }

    public int getWidth(){
        width = _right - _left;
        return width;
    }

    public int getHeight(){
        height = _bottom - _top;
        return height;
    }

    public int getLeft(){
        return _left;
    }
    public int getRight(){
        return _right;
    }
    public int getTop(){
        return _top;
    }
    public int getBottom(){
        return _bottom;
    }

}
