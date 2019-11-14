package ucm.dv.vdm.engine;

public interface Graphics {

    void setCanvasSize(Rect c);

    /**
     * Creates new Image from resource container
     *
     * @return  new Image
     *
     */
    Image newImage(String name);

    /**
     * Clears screen, fills it with color
     *
     * @return  void
     *
     */
    void clear(int color);

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
    int getWidth();

    /**
     * Get Window Height
     *
     * @return  int window height
     *
     */
    int getHeight();

    /**
     * Scalates an image counting on the Window Height and Width from the original dimensions.
     *
     * @param src Original size of the image.
     * @return The new Sizes of he image.
     */
    Rect dimensions(Rect src);

    void setCanvasPos(int x, int y);

}
