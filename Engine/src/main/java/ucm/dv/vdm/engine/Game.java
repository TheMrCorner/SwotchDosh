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
}
