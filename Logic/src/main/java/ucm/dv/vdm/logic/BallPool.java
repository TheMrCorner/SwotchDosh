package ucm.dv.vdm.logic;

import java.util.ArrayList;
import java.util.List;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Image;
import ucm.dv.vdm.engine.Sprite;

/**
 * Simple class to store the Ball pools. Efficiency.
 */
public class BallPool extends GameObject { // TODO: Illo comenta esta wea

    public BallPool(int x, int y, Image sprite){
        super(x, y, Sprite.spriteMaker(sprite, 10, 2));

        // Set the generic position for the Ball generation
        _x = x;
        _y = y;

        // Init counter
        _cnt = 0;

        // Create the pool
        _balls = new ArrayList<Ball>();

        _temp = 0;

        _avbl = false;
    }

    public void AddNewBall(){
        Sprite[] sprt = new Sprite[1];

        sprt[0] = _sprite[0]; // Esto puede cambiar

        Ball n = new Ball(_x, _y, sprt);

        n.setActive(true);

        _balls.add(n);
    }

    @Override
    public void update(double t) { // Call update for all balls (if they are active)
        _time += t;

        if(_balls.isEmpty() || (!_avbl && _time >= 1.0)){
            AddNewBall();
            _time = 0.0;
        }
        else {
            _balls.get(_temp).setActive(true);
            _balls.get(_temp).setPosX(_x);
            _balls.get(_temp).setPosY(_y);
        }

        for (int i = 0; i < _balls.size(); i++){
            if(_balls.get(i).isActive()){
                _balls.get(i).update(t);
            }

            if(_cnt == 10){
                _balls.get(i).faster();
                _cnt = 0;
            }
        }
    }

    @Override
    public void render(Graphics g) { // Call render for all balls (if they are active)
        for (int i = 0; i < _balls.size(); i++){
            if(_balls.get(i).isActive()){
                _balls.get(i).render(g);
            }
        }
    }

    // Pool
    List<Ball> _balls;

    // A ball is Available
    int _temp; // Saves the ball that is inactive (for efficiency)
    boolean _avbl;

    // Time since last gen
    double _time;

    // Generic position (top of the screen)
    int _x, _y;

    // Balls get
    int _cnt;


}
