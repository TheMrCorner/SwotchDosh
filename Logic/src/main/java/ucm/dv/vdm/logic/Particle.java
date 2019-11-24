package ucm.dv.vdm.logic;

import java.util.Random;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Rect;
import ucm.dv.vdm.engine.Sprite;

public class Particle extends GameObject {

    public enum Direction {
        LEFT,
        RIGHT
    }

    /**
     * Initializes Particle. Sets the position and the sprite of this gameObject.
     * Also sets their value to the velocity and the active state
     *
     *@param x X position on the screen.
     *@param y Y position on the screen.
     *@param s Sprite to put in the objects.
     */
    public Particle(int x, int y, Sprite[] s) {
        super(x, y, s);

        _velY = 0;
        _velX = 0;

        _actv = true;

        _alpha = 1.0f;

        rnd = new Random();

        //Sets a random size to the destiny Rect
        randomSize();

        randomDirection();

    }

    /**
     * Sets a new random direction to the particle (left or right)
     *
     */
    void randomDirection(){
        int r = rnd.nextInt(2);
        if(r == 0) {
            d = Direction.LEFT;
        }
        else if (r == 1){
            d = Direction.RIGHT;
        }
    }

    /**
     * Sets a new random size to the particle
     *
     */
    void randomSize(){

        int size = rnd.nextInt(5);
        int right = 0;
        int bottom = 0;

        switch(size){

            case 0:
                right = _sprite[0].get_rect().getWidth()/2;
                bottom = _sprite[0].get_rect().getWidth()/2;
                break;
            case 1:
                right = _sprite[0].get_rect().getWidth()/3;
                bottom = _sprite[0].get_rect().getWidth()/3;
                break;
            case 2:
                right = _sprite[0].get_rect().getWidth()/4;
                bottom = _sprite[0].get_rect().getWidth()/4;
                break;
            case 3:
                right = _sprite[0].get_rect().getWidth()/5;
                bottom = _sprite[0].get_rect().getWidth()/5;
                break;
            case 4:
                right = _sprite[0].get_rect().getWidth()/6;
                bottom = _sprite[0].get_rect().getWidth()/6;
                break;

        }

        _dest = new Rect((int)_x + right, (int)_x, (int)_y, (int)_y + bottom);
        _dest.setPosition((int)_x, (int)_y);

    }

    /**
     * Setter of a new velocity value of y
     *
     *@param vel new velocity
     */
    public void set_velY(int vel){
        _velY = vel;
    }

    /**
     * Setter of a new velocity value of x
     *
     *@param vel new velocity
     */
    public void set_velX(int vel){
        _velX = vel;
    }

    /**
     * Update the x and y position with each velocity
     *
     *@param t time of each frame
     */
    @Override
    public void update(double t) {

        _alpha -= 0.01;

        _y += _velY * t;

        //Change x position with velX
        if(d == Direction.LEFT) {
            _x -= _velX * t;
        }
        else if (d == Direction.RIGHT){
            _x += _velX * t;
        }

        if (_alpha <= 0 ){
            _actv = false;
        }
    }

    /**
     * Render the particle with its color, alpha and size
     *
     *@param g instance to Graphics
     */
    @Override
    public void render(Graphics g) {
        if(_c == Color.BLACK){
            _sprite[1].draw(g, _dest, _alpha);
        }
        else{
            _sprite[0].draw(g, _dest, _alpha);
        }
    }

    /**
     * Actives or desactives the particle
     *
     *@param b new boolean to set
     */
    public void setActive(boolean b){
        _actv = b;
    }


    /**
     * Ask if the particle is active or not
     *
     * @return _actv value
     */
    public boolean isActive(){
        return _actv;
    }

    // Active
    boolean _actv;

    // Velocities
    int _velX;
    int _velY;

    //Alpha
    float _alpha;

    //Destiny Rect
    Rect _dest;

    //Random to use in direction and size
    private Random rnd;

    //Direction of the particle
    Direction d;
}
