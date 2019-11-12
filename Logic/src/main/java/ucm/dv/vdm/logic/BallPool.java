package ucm.dv.vdm.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple class to store the Ball pools. Efficiency.
 */
public class BallPool extends GameObject {

    public BallPool(){
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
    boolean _avbl;


}
