package ucm.dv.vdm.engine;

public interface Game {

    /**
     *
     * @return  GraphicEngine's instance
     *
     */
    Graphics getGraphics();

    /**
     * Get input manager
     *
     * @return  Input manager
     *
     */
    Input getInput();

    void setLogic(Logic l);

    void HandleException(Exception e);

    int getWidth();

    int getHeight();

    /**
     * Gets the information about the playing scene, part, zone
     * @return Returns the canvas size
     */
    Rect getCanvas();


}
