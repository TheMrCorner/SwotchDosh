package ucm.dv.vdm.logic;

import java.util.ArrayDeque;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Sprite;

public class ParticlesSystem extends GameObject {

    /**
     * Constructor of the particles system. Sets the position and the sprite to the game object
     *
     * @param x X position on the screen.
     * @param y Y position on the screen.
     * @param s Sprite to put in the objects.
     */
    public ParticlesSystem(int x, int y, Sprite[] s, Color c) {
        super(x, y, s);

        // Set the generic position for the Particle generation
        _x = x;
        _y = y;

        _c = c;

        // Create the pool
        _particles = new ArrayDeque<Particle>();

        double numParticles = (Math.random() * 20 + 10);

        for (int i = 0; i < numParticles; i++) {
            AddNewParticle();
        }
    }

    /**
     * Creates a new paricle and add it to the pool of objects. Set it to the generic Y position for all
     * paricles. Add it at the end of the queue.
     */
    public void AddNewParticle(){
        // Create the sprite array
        Sprite[] sprt = new Sprite[2];

        // Add the created sprites for the balls to the Ball sprite
        sprt[0] = _sprite[0];
        sprt[1] = _sprite[10];

        // Create the new Ball
        Particle n = new Particle(_x, _y, sprt);

        // Give the color of the ball
        n.setColor(_c);

        // Add it to the last position of the queue
        _particles.add(n);
    }

    /**
     * Update each particle
     *
     *@param t time of each frame
     */
    @Override
    public void update(double t){
        // Update all the balls that are active
        for(Particle p : _particles){ // Iterator
            if(p.isActive()){
                p.update(t);
            }
            else{
                _particles.pop();
            }
        }
    }

    /**
     * Render each particle
     *
     *@param g instance of Graphics
     */
    @Override
    public void render(Graphics g) { // Call render for all balls (if they are active)
        for(Particle p : _particles){ // Iterator
            if(p.isActive()){
                p.render(g);
            }
        }
    }

    public ArrayDeque<Particle> getList(){
        return _particles;
    }

    // Pool
    ArrayDeque<Particle> _particles;

    // Generic position (position of the destroy ball)
    int _x, _y;

}
