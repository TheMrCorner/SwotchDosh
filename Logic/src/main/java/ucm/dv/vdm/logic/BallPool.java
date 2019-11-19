package ucm.dv.vdm.logic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Image;
import ucm.dv.vdm.engine.Sprite;

/**
 * Simple class to store the Ball pools.
 */
public class BallPool extends GameObject { // TODO: Illo comenta esta wea

    /**
     * Constructor of the BallPool. Initializes it's position, the counter, creates the BallPool in
     * order to instantiate them correctly. Sets available to false. Creates the first ball.
     * @param x X position on the screen.
     * @param y Y position on the screen.
     * @param sprite Sprite to put in the objects.
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

        // Set available to false
        _avbl = 0;

        AddNewBall();
    }

    /**
     * Creates a new ball and add it to the pool of objects. Set it to the generic Y position for all
     * balls. Activate it and set the RandomColor. Add it at the end of the queue.
     */
    public void AddNewBall(){
        // Create the sprite array
        Sprite[] sprt = new Sprite[2];

        // Add the created sprites for the balls to the Ball sprite
        sprt[0] = _sprite[0];
        sprt[1] = _sprite[10];

        // Create the new Ball
        Ball n = new Ball(_x, _y, sprt);

        // Activate the ball
        n.setActive(true);

        // Give it a random color depending on the ball bellow
        n.setColor(randomColor());

        // Add it to the last position of the queue
        _balls.add(n);
    }

    /**
     * Removes the lowest element of the queue.
     */
    public void destroy(){
        // Save that ball to a new variable
        Ball d = _balls.poll();

        // Reposition ball and deactivate it
        d.setActive(false);
        d.setPosY(_y);

        d.setColor(randomColor());

        // Add that element to the end of the queue
        _balls.add(d);

        // Number of balls available
        _avbl++;

        _cnt++;
    }

    /**
     * Updates all balls (that are active). Called every frame
     * @param t Time elapsed since last update
     */
    @Override
    public void update(double t) {
        _c += _balls.peek().getVel() * t;

        // If the last ball's position is greater than the maximum distance
        if(_c >= _d){
            System.out.println(_avbl);
            // Create a new ball
            // If there is no ball available
            if (_avbl == 0) {
                //Create a new ball
                AddNewBall();
                // Give it the velocity of all the balls
                _balls.getLast().set_vel(_balls.peek().getVel()); // TODO: Mirar si guardar la vel aquí en BallPool (Decisions)
            }
            // If there is a ball available
            else {
                // Searching the older ball destroyed
                Iterator b = _balls.descendingIterator();
                // See the number of balls available
                int i = _avbl;

                // Search
                do{
                    if(i == 1){ // The older ball available
                        // Activate that ball
                        ((Ball)b.next()).setActive(true);
                        // Reduce the number of balls available
                        _avbl--;
                    }
                    // Keep searching
                    else {
                        b.next();
                    }
                    i--;
                } while(i > 0);
            }

            // Reset interval
            _c = 0.0;
        }

        // Update all the balls that are active
        for(Ball b : _balls){ // Iterator
            if(b.isActive()){
                b.update(t);
            }

            // Increase velocity when 10 balls are destroyed
            if(_cnt == 10){
                b.faster();
            }
        }

        // Reset ball destroyed counter
        if(_cnt == 10) {
            _cnt = 0;
        }
    }

    @Override
    public void render(Graphics g) { // Call render for all balls (if they are active)
        for(Ball b : _balls){ // Iterator
            if(b.isActive()){
                b.render(g);
            }
        }
    }

    /**
     * Get a reference to the lower ball.
     * @return Lower ball
     */
    public Ball getLowerBall(){
        return _balls.peek();
    }

    /**
     * Get the number of balls registered in the pool.
     * @return Size of the pool (int).
     */
    public int getNBalls(){
        return _balls.size();
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
    int _avbl;

    // Distance
    int _d = 395;

    // Counter porque queremos llorar y es lo más rápido de facer
    double _c = 0.0;

    // Generic position (top of the screen)
    int _x, _y;

    // Balls get
    int _cnt;


}
