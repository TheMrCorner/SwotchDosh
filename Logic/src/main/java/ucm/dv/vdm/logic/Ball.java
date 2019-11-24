package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Sprite;

/**
 * Ball object. Has all necessary methods to update the balls and render them.
 */
public class Ball extends GameObject{

    /**
     * Constructor of Balls. Receives the position the ball will have and the Sprite it will use to
     * draw himself.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param s Sprite[] to draw
     */
    Ball(int x, int y, Sprite[] s){
        super(x, y, s);

        setPosX(x - (_sprite[0].get_rect().getWidth()/2));

        _vel = 430; // Initializes to 430

        _actv = true;
    } // Ball

    /**
     * Return the actual velocity of Ball (for debugging)
     *
     * @return int Velocity
     */
    public int getVel(){
        return _vel;
    } // getVel

    /**
     * Update the Y position of the balls with time. Called once per frame.
     *
     * @param t Time elapsed since last frame.
     */
    @Override
    public void update(double t) {
        _y += _vel * t;
    } // update

    /**
     * Render the Ball in it's corresponding position and corresponding sprite.
     *
     * @param g Graphics instance to render Ball.
     */
    @Override
    public void render(Graphics g) {
        if(_c == Color.BLACK){
            _sprite[1].draw(g, (int)_x, (int)_y);
        } // if
        else{
            _sprite[0].draw(g, (int)_x, (int)_y);
        } // else
    } // render

    /**
     * Change active value.
     *
     * @param b New value for active. Boolean.
     */
    public void setActive(boolean b){
        _actv = b;
    } // setActive

    /**
     * Method that checks if Ball is Active.
     *
     * @return boolean _actv
     */
    public boolean isActive(){
        return _actv;
    } // isActive

    /**
     * Set velocity of the ball.
     *
     * @param vel int new velocity
     */
    public void set_vel(int vel){
        _vel = vel;
    } // set_vel

    /**
     * Augments the velocity of the ball (when 10 are destroyed)
     */
    public void faster(){
        _vel += 90;
    } // faster

    // Active
    boolean _actv;

    // Velocity
    int _vel;
}

