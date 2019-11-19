package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Rect;
import ucm.dv.vdm.engine.Sprite;

public class Arrow {

   public Arrow(int x, int y, Sprite s){

       _sprite = s;

       _x = x;
       _y = y;

       _vel = 384;
       _basicVel = 384;

   }

    public void update (double t, int h){

       _y += _vel*t;

       if (_y >= h){
           _y = - (_sprite.get_rect().getHeight());
       }

    }

    public void render (Graphics g){

        Rect arrowDest = new Rect(_sprite.get_rect().getWidth(),
                0,0, _sprite.get_rect().getHeight());

        arrowDest.setPosition(_x, _y);

        _sprite.draw(g, arrowDest, 0.6f);
    }

    public void resetVel () {
       _vel = _basicVel;
    }

    public void faster () {
        _vel += 90;
    }

    public int getX(){
        return _x;
    }

    public int getY(){
        return _y;
    }

    public void setX(int x){
        _x = x;
    }

    public void setY(int y){
        _y = y;
    }

    //Gets the sprite of the arrow
    Sprite getSprite(){
        return _sprite;
    }

    //Sets a new sprite to the arrow
    void setSprite(Sprite s){
        _sprite = s;
    }

    Sprite _sprite;
    int _x;
    int _y;

    int _vel;
    int _basicVel;

}
