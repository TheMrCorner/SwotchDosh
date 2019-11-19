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

    public int getVel(){
        return _vel;
    }


    @Override
    public void update(double t) {
        _y += _vel * t;
    }

    @Override
    public void render(Graphics g) {
        if(_c == Color.BLACK){
            _sprite[1].draw(g, (int)_x, (int)_y);
        }
        else{
            _sprite[0].draw(g, (int)_x, (int)_y);
        }
    }

    public void setActive(boolean b){
        _actv = b;
    }

    public boolean isActive(){
        return _actv;
    }

    public void set_vel(int vel){
        _vel = vel;
    }

    public void faster(){
        _vel += 90;
    }

    // Active
    boolean _actv;

    // Velocity
    int _vel;


}

