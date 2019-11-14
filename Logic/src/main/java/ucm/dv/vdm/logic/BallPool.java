package ucm.dv.vdm.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple class to store the Ball pools. Efficiency.
 */
public class BallPool extends GameObject {

    public BallPool(int x, int y){
        // Set the generic position for the Ball generation
        _x = x;
        _y = y;

        // Create the pool
        _balls = new ArrayList<Ball>();
    }

    public void AddNewBall(){
        Ball n = new Ball();

        _balls.add(n);
    }

    @Override
    public void update() { // Call update for all balls (if they are active)
        if(!_avbl){
            AddNewBall();
        }
        else {
            _balls.get(_temp).setActive(true);
            _balls.get(_temp).setPosX(_x);
            _balls.get(_temp).setPosY(_y);
        }

        for (int i = 0; i < _balls.size(); i++){
            if(_balls.get(i).isActive()){
                _balls.get(i).update();
            }
        }
    }

    @Override
    public void render() { // Call render for all balls (if they are active)
        for (int i = 0; i < _balls.size(); i++){
            if(_balls.get(i).isActive()){
                _balls.get(i).render();
            }
        }
    }

    // Pool
    List<Ball> _balls;

    // A ball is Available
    int _temp; // Saves the ball that is inactive (for efficiency)
    boolean _avbl;

    // Generic position (top of the screen)
    int _x, _y;


}
