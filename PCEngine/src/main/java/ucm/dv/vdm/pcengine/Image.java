package ucm.dv.vdm.pcengine;

public class Image implements ucm.dv.vdm.engine.Image{

    /**
     * Constructor of Image. Stores an instance of a Swing Image.
     * @param img Instance of Swing Image.
     */
    public Image (java.awt.Image img){
        _image = img;
    }

    /**
     * Function that provides the width of the image.
     * @return Width of image (pixels)
     */
    @Override
    public int getWidth() {
        return _image.getWidth(null);
    }

    /**
     * Function that provides the height of the Image.
     * @return Height of Image (pixels)
     */
    @Override
    public int getHeight() {
        return _image.getHeight(null);
    }

    /**
     * Function that provides the instance of the Swing Image.
     * @return Instance of Swing Image.
     */
    public java.awt.Image getImage() {
        return _image;
    }

    /**
     * Instance of Swing Image.
     */
    private java.awt.Image _image;

}
