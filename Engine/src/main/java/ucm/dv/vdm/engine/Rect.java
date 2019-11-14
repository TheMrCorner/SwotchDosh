package ucm.dv.vdm.engine;

// TODO: COMENTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAR

public class Rect {

    // Pixel where it begins
    int _right = 0;
    int _left = 0;
    int _top = 0;
    int _bottom = 0;

    // Positions
    int _x = 0;
    int _y = 0;

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

    public void setPosition(int x, int y) {
        _x = x;
        _y = y;
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
    public int getX(){ return _x; }
    public int getY() { return _y; }

}
