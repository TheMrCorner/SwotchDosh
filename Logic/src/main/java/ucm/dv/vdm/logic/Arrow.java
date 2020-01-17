package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Rect;
import ucm.dv.vdm.engine.Sprite;

/**
 * Class that manages the Arrows that appear on the background.
 */
public class Arrow {

    /**
     * Constructor of the Arrow. Receives it's position and the Sprite it will use to draw.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param s Sprite instance to draw
     */
    public Arrow(int x, int y, Sprite s){
       _sprite = s;

       _x = x;
       _y = y;

       _originalY = y;

       _vel = 384;
       _basicVel = 384;
    } // Arrow

    /**
     * Update Arrows position with the velocity and the time elapsed since last frame. Called once
     * per frame.
     *
     * @param t Time elapsed since last frame.
     */
    public void update (double t){

       // Update position
       _y += _vel * t;

       // If Y position is greater than 0, reposition it
       if(_y >= 0){
           _y = _originalY;
       } // if
    } // update

    /**
     * Render arrows with the information updated.
     *
     * @param g Graphics instance to draw arrows.
     */
    public void render (Graphics g){
        // Destination rectangle in which arrows will be drawn.
        Rect arrowDest = new Rect(_sprite.get_rect().getWidth(),
                0,0, _sprite.get_rect().getHeight());

        arrowDest.setPosition((int)_x, (int)_y);

        // draw arrows
        _sprite.draw(g, arrowDest, 0.5f);
    } // render

    /**
     * Set velocity to the basicVelocity
     */
    public void resetVel () {
       _vel = _basicVel;
    } // resetVel

    /**
     * Adds more velocity to the Arrows.
     */
    public void faster () {
        _vel += 90;
    } // faster

    /**
     * Get X position of the Arrows.
     *
     * @return X
     */
    public double getX(){
        return _x;
    }

    /**
     * Get Y position of the Arrows.
     *
     * @return Y
     */
    public double getY(){
        return _y;
    } // getY

    /**
     * Set X position of the Arrows.
     */
    public void setX(int x){
        _x = x;
    } // setX

    /**
     * Set Y position of the Arrows.
     */
    public void setY(int y){
        _y = y;
    } // setY

    /**
     * Gets the Sprite this Arrow has stored and uses to paint.
     *
     * @return _sprite
     */
    Sprite getSprite(){
        return _sprite;
    } // getSprite

    /**
     * Sets a new Sprite to the Arrows.
     *
     * @param s new Sprite
     */
    void setSprite(Sprite s){
        _sprite = s;
    } // setSprite

    // Private atributes.
    Sprite _sprite;
    double _x;
    double _y;

    // original Y position of the Arrows.
    int _originalY;

    int _vel;
    int _basicVel;

}
