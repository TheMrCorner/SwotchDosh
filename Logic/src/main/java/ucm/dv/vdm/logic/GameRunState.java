package ucm.dv.vdm.logic;

import java.util.ArrayDeque;
import java.util.List;

import ucm.dv.vdm.engine.Game;
import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Input;
import ucm.dv.vdm.engine.Sprite;

public class GameRunState extends GameState {
    /**
     * Constructor of GameState. Receives an instance of Logic to call it when needed. Receives
     * the player's score when needed (coming from GamRun to GameOver to show punctuation).
     *
     * @param l     Logic instance.
     * @param score Player's actual score.
     */
    public GameRunState(Logic l, int score) {
        super(l, score);
    }

    /**
     * Initializes the GameState with all the sprites needed, provided by the ResourceManager
     *
     * @param r ResourceManager instance.
     */
    @Override
    public void initState(ResourceManager r){
        // Change background color
        _l.changeBackgroundColor();

        // Init _go
        _go = new GameObject[3]; // Player, BallPool, Text for punctuation

        // Init each GO
        _go[0] = new Player(_l.getCanvasSize().getWidth()/2, 1200, r.getGameObject("Player"));
        _go[0].setColor(GameObject.Color.BLACK);
        _go[1] = new BallPool(_l.getCanvasSize().getWidth()/2, -200, r.getGameObject("Balls"));

        Sprite[] font = Sprite.spriteMaker(r.getText("Font"), 15, 7);

        int first = _l.getCanvasSize().getWidth() - font[0].get_rect().getWidth();

        _go[2] = new Points (first, 90, font, this, 3);

        _sParticle = Sprite.spriteMaker(r.getGameObject("Balls"), 10, 2);
        // Create the pool
        _particleSystems = new ArrayDeque<ParticlesSystem>();
    }

    /**
     * Updates all GameObjects in this State with the time passed since the las update.
     *
     * @param t Time elapsed since the last frame.
     */
    @Override
    public void update (double t){
        // Update
        for(int i = 0; i < _go.length; i++){
            _go[i].update(t);
        }

        for(ParticlesSystem ps : _particleSystems){
            ps.update(t);
            if (ps.getList().isEmpty()){
                _particleSystems.pop();
            }
        }

        // Check colision
        colisions();
    }

    /**
     * Renders all GameObjects in their specific locations. Receives an instance of Graphics
     * to call the drawing methods.
     *
     * @param g Instance of Graphics
     */
    @Override
    public void render(Graphics g){
        for(int i = 0; i < _go.length; i++){
            _go[i].render(g);
        }

        for(ParticlesSystem ps : _particleSystems){
            ps.render(g);
        }

    }

    /**
     * Process the Input received from the Logic. If mouse is clicked, change Player color.
     * @param g Game instance to get the Input.
     */
    @Override
    public void processInput(Game g){
        List<Input.TouchEvent> e = g.getInput().getTouchEvent(); // Get the list of TouchEvents

        int ptr = e.size() - 1; // Pointer to roam the list

        while(!e.isEmpty() && ptr >= -1){ // While list is not empty

            Input.TouchEvent te = e.get(ptr); // Get the TouchEvent at the pointer position

            switch(te.getType()){ // Process the type of the TouchEvent
                case CLICKED:
                        if(_go[0].getColor() == GameObject.Color.BLACK) {
                            _go[0].setColor(GameObject.Color.WHITE);
                        }
                        else{
                            _go[0].setColor(GameObject.Color.BLACK);
                        }
                    break;
                default:
                    //Anything else, do nothing.
                    break;
            }

            e.remove(ptr); // Remove that TouchEvent from the list
            ptr--;
        }
    }

    /**
     * Checks if the player colision with the lowest ball.
     */
    void colisions(){

        // Get the lower ball and check if it collides with the Player
        if(_go[0].getPosY() <= ((BallPool)_go[1]).getLowerBall().getPosY() + (_go[1]._sprite[0].get_rect().getHeight())){

            ParticlesSystem ps;
            int particlesX = (int)((BallPool)_go[1]).getLowerBall().getPosX();
            int particlesY = (int)((BallPool)_go[1]).getLowerBall().getPosY() + (((BallPool)_go[1])._sprite[0].get_rect().getHeight()/2);

            // If both (player and ball) have the same color...
            if(((BallPool)_go[1]).getLowerBall().getColor() == _go[0].getColor()){
                // Add Points
                _pts++;


                ps = new ParticlesSystem(particlesX, particlesY, _sParticle, ((BallPool)_go[1]).getLowerBall().getColor());

                // Destroy ball
                ((BallPool)_go[1]).destroy();
            }
            // If not, GameOver
            else{
                ps = new ParticlesSystem(particlesX, particlesY, _sParticle, ((BallPool)_go[1]).getLowerBall().getColor());
                // TODO: mirar lo de incrementar la velocidad de las flechas
                _l.resetArrowsVel();
                _l.changeState(new GameOverState(_l, _pts));
            }

            _particleSystems.add(ps);

        }
    }

    Sprite[] _sParticle;

    // Pool of particles system
    ArrayDeque<ParticlesSystem> _particleSystems;
}
