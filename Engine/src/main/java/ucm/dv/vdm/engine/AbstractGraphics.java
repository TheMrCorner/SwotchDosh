package ucm.dv.vdm.engine;

public abstract class AbstractGraphics implements Graphics{ // TODO: Comentar chiquets

    public Rect getCanvas() {
        return _can;
    }

    public void setReferenceCanvas(Rect c) {
        _refCan = c;
    }

    /**
     * Set a size for the canvas to place objects in the menus and UI
     * @param c Size of canvas
     */
    @Override
    public void setCanvasSize(Rect c, Rect dim) { // TODO: Recomentar
        Rect temp; // Temporal rectangle for calculations

        int width = c.getWidth(); //
        int height = c.getHeight();

        if(width > dim.getWidth()){
            width = dim.getWidth();

            height = (width * c.getHeight()) / c.getWidth();
        }

        if(height > dim.getHeight()){
            height = dim.getHeight();

            width = (height * c.getWidth()) / c.getHeight();
        }

        temp = new Rect (width, 0, 0, height);

        _can = temp;
    }

    // TODO: COmentar de aquí para abajo.
    @Override
    public void setCanvasPos(int x, int y) {
        _can.setPosition(x, y);
    }


    /**
     * Change the size of a Rectangle using another rectangle as a reference. Maintains the aspect
     * ratio of the Rectangle (proportions) and returns the new rectangle.
     * @param src Rectangle to be resized
     * @param dim Rectangle to use as a reference.
     * @return Resized src rectangle maintaining the aspect ratio of it.
     */
    public Rect dimensions(Rect src, Rect dim){
        Rect temp; // Temporal rectangle for calculations

        int width = src.getWidth(); // Save the src width
        int height = src.getHeight(); // Save the src height

        // If the src width is higher than the reference width
        if(width > dim.getWidth()){
            // Set the new width but resized proportionally
            width = repositionX(width);
            // Change height keeping proportions
            height = (width * src.getHeight()) / src.getWidth();
        }

        // If the src height (or the changed height) is bigger than the reference one
        if(height > dim.getHeight()){
            // Set the new height but resized proportionally
            height = repositionY(height);
            // Change width proportionally
            width = (height * src.getWidth()) / src.getHeight();
        }

        // Save the changes to the new Rectangle
        temp = new Rect (width, 0, 0, height);

        // Set the original position in canvas of the source Rectangle
        temp.setPosition(src.getX(), src.getY());

        // Return result
        return temp;
    }

    public boolean isInCanvas(int x, int y) {
        if( ((x >= _can.getX()) && (x < (_can.getX() + _can.getWidth())))
                && ((y >= _can.getY()) && (y < (_can.getY() + _can.getHeight())))){
            return true;
        }
        else {
            return false;
        }
    }

    public int repositionX(int x) {
        return (x * _can.getWidth()) / _refCan.getWidth();
    }

    public int repositionY(int y) {
        return (y * _can.getHeight()) / _refCan.getHeight();
    }

    public int reverseRepositionX(int x) {
        return (x * _refCan.getWidth()) / _can.getWidth();
    }

    public int reverseRepositionY(int y) {
        return (y * _refCan.getHeight()) / _can.getHeight();
    }

    // Canvas
    public Rect _can;

    public Rect _refCan;

    // TODO: Meter aquí toda la parte de escalado y hacer el Graphics de PC que herede de este.

}
