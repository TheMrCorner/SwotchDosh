package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Sprite;

public class GameObject {

    public enum Color {
        WHITE,
        BLACK
    }

    /**
     * Initializes GameObject. (Create _sprite,
     */
    public GameObject(int x, int y, Sprite[] s){
        setPosX(x);
        setPosY(y);

        setSprite(s);

        _c = null;
    }

    // Getters
    public Sprite getSprite(int i) {
        return _sprite[i];
    }

    public double getPosX() { return _x; }
    public double getPosY() {
        return _y;
    }

    // Setters
    public void setSprite(Sprite[] s) {_sprite = s;}

    void setPosX (int x) {_x = x;}
    void setPosY (int y) {_y = y;}

    void setColor(Color c){
        _c = c;
    }

    public Color getColor(){
        return _c;
    }

    public void update(double t){}

    public void render(Graphics g){}

    //---------------------------------------------
    //----------------Atributes--------------------
    //---------------------------------------------
    Sprite[] _sprite;

    // Color of the sprite
    Color _c;

    // First point position
    double _x;
    double _y;

}
