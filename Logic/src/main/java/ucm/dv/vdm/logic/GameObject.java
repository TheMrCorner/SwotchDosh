package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Sprite;

public class GameObject {

    Sprite _sprite;

    //First point position
    int _x;
    int _y;

    public Sprite getSprite() {
        return _sprite;
    }


    public int getPosX() {
        return _x;
    }


    public int getPosY() {
        return _y;
    }

    void update(){};
    void render(){

    }

}
