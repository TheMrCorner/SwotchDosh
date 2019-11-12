package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Sprite;

public class Ball extends GameObject{

    Ball(){
        _actv = true;
    }


    @Override
    public void update() {

    }

    @Override
    public void render() {

    }

    public void setActive(boolean b){
        _actv = b;
    }

    public boolean isActive(){
        return _actv;
    }

    // Active
    boolean _actv;


}
