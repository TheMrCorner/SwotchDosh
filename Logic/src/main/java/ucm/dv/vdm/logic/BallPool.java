package ucm.dv.vdm.logic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Image;
import ucm.dv.vdm.engine.Sprite;

/**
 * Simple class to store the Ball pools. Efficiency.
 */
public class BallPool extends GameObject { // TODO: Illo comenta esta wea

    /**
     * Constructor of the Ball Pool. Initializes a new BallPool.
     * @param x
     * @param y
     * @param sprite
     */
    public BallPool(int x, int y, Image sprite){
        super(x, y, Sprite.spriteMaker(sprite, 10, 2));

        // Set the generic position for the Ball generation
        _x = x;
        _y = y;

        // Init counter
        _cnt = 0;

        // Create the pool
        _balls = new ArrayDeque<Ball>();

        _avbl = false;

        AddNewBall();
    }

    public void AddNewBall(){
        Sprite[] sprt = new Sprite[2];

        sprt[0] = _sprite[0]; // Esto puede cambiar
        sprt[1] = _sprite[10];

        Ball n = new Ball(_x, _y, sprt);

        n.setActive(true);

        n.setColor(randomColor());

        _balls.add(n); // Adds at the end of the queue

        System.out.println("A ver, fiera, hemos creado un peloto to tocho");
    }

    /**
     * Removes the first element of the queue.
     */
    public void destroy(){
        // Save that ball to a new variable
        Ball d = _balls.poll();

        // Reposition ball and deactivate it
        d.setActive(false);
        d.setPosY(_y);

        d.setColor(randomColor());

        _balls.add(d); // Add that element to the end of the queue

        _avbl = true; // Set available to true

        _cnt++;
    }

    @Override
    public void update(double t) { // Call update for all balls (if they are active)
        _c += _balls.peek().getVel() * t;

        if(_c >= _d){
            if (!_avbl) {
                AddNewBall();
                _balls.getLast().set_vel(_balls.peek().getVel());
            }
            else {
                _balls.getLast().setActive(true);
                _avbl = false;
            }
            _c = 0.0;
        }

        for(Ball b : _balls){ // Iterator
            if(b.isActive()){
                b.update(t);
            }

            if(_cnt == 10){
                b.faster();
            }
        }

        if(_cnt == 10) {
            _cnt = 0;
        }
    }

    public Ball colision(){
        if(_balls.peek().isActive()){
            return _balls.peek();
        }
        else {
            return null;
        }
    }

    public int getNBalls(){
        return _balls.size();
    }

    @Override
    public void render(Graphics g) { // Call render for all balls (if they are active)
        for(Ball b : _balls){ // Iterator
            if(b.isActive()){
                b.render(g);
            }
        }
    }

    Color randomColor (){
        double r = (Math.random() *((1 - 0) + 1)) + 0;

        if(_balls.isEmpty()){
            if((int)r == 1){
                return Color.WHITE;
            }
            else{
                return Color.BLACK;
            }
        }
        else{
            Color last = _balls.getLast().getColor();
            if(r <= 0.7){
                return last;
            }
            else{
                if(last == Color.WHITE){
                    return Color.BLACK;
                }
                else {
                    return Color.WHITE;
                }
            }
        }
    }

    // Pool
    ArrayDeque<Ball> _balls;

    // A ball is Available
    boolean _avbl;

    // Distance
    int _d = 395;

    // Counter porque queremos llorar y es lo más rápido de facer
    double _c = 0.0;

    // Generic position (top of the screen)
    int _x, _y;

    // Balls get
    int _cnt;


}
