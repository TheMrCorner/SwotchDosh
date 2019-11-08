package ucm.dv.vdm.engine;

public class Rect {

    // Pixel where it begins
    public int _right = 0;
    public int _left = 0;
    public int _top = 0;
    public int _bottom = 0;

    // Constructor
    public Rect(int r, int l, int t, int b){
        _right = r;
        _left = l;
        _top = t;
        _bottom = b;
    }

    public int getWidth(){
        return _left - _right;
    }

    public int getHeight(){
        return _bottom - _top;
    }

}
