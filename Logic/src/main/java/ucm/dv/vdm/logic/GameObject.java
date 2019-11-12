package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Sprite;

public class GameObject {

    Sprite _sprite;

    //First point position
    int _x;
    int _y;

    //Getters
    public Sprite getSprite() {
        return _sprite;
    }

    public int getPosX() { return _x; }
    public int getPosY() {
        return _y;
    }

    //Setters
    public void setSprite(Sprite s) {_sprite = s;}

    public void setPosX (int x) {_x = x;}
    public void setPosY (int y) {_y = y;}

    void update(){};
    void render(){

    }

}
