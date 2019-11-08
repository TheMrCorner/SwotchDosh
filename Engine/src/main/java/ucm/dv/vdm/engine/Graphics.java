package ucm.dv.vdm.engine;

public interface Graphics {

    void setCanvasSize(int x, int y);

    /**
     * Creates new Image from resource container
     *
     * @return  new Image
     *
     */
    public Image newImage(String name);

    /**
     * Clears screen, fills it with color
     *
     * @return  void
     *
     */
    public void clear(int color);

    /**
     * Gets an image and shows it in the screen
     *
     * @return  void
     *
     */
    void drawImage(Image image, int x, int y);
    void drawImage(Image image, Rect source, int x, int y);
    void drawImage(Image image, Rect source, Rect dest);
    void drawImage(Image image, Rect source, Rect dest, int alpha);

    // Get window information
    /**
     * Get Window width
     *
     * @return  int window width
     *
     */
    public int getWidth();

    /**
     * Get Window Height
     *
     * @return  int window height
     *
     */
    public int getHeight();

}
