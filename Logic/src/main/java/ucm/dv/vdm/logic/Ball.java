package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Sprite;

public class Ball extends GameObject{

    Ball(int x, int y, Sprite[] s){
        super(x, y, s);

        setPosX(x - (_sprite[0].get_rect().getWidth()/2));

        _vel = 430; // Initializes to 430

        _actv = true;
    }


    @Override
    public void update(double t) {
        _y += _vel * t;
    }

    @Override
    public void render(Graphics g) {
        _sprite[0].draw(g, (int)_x, (int)_y);
    }

    public void setActive(boolean b){
        _actv = b;
    }

    public boolean isActive(){
        return _actv;
    }

    public void faster(){
        _vel += 90;
    }

    // Active
    boolean _actv;

    // Velocity
    int _vel;


}
