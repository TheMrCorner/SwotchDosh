package ucm.dv.vdm.androidengine;

import android.graphics.Canvas;

import ucm.dv.vdm.engine.AbstractGraphics;
import ucm.dv.vdm.engine.Image;
import ucm.dv.vdm.engine.Rect;

public class  Graphics extends AbstractGraphics {

    @Override
    public void setCanvasSize(Rect c, Rect dim) {

    }

    @Override
    public Rect getCanvas() {
        return null;
    }

    @Override
    public void setReferenceCanvas(Rect c) {

    }

    @Override
    public Image newImage(String name) {
        return null;
    }

    @Override
    public void clear(int color) {

    }

    @Override
    public void drawImage(Image image, int x, int y) {

    }

    @Override
    public void drawImage(Image image, Rect source, int x, int y) {

    }

    @Override
    public void drawImage(Image image, Rect source, Rect dest) {

    }

    @Override
    public void drawImage(Image image, Rect source, Rect dest, float alpha) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public Rect dimensions(Rect src, Rect dim) {
        return null;
    }

    @Override
    public void setCanvasPos(int x, int y) {

    }

    @Override
    public boolean isInCanvas(int x, int y) {
        return false;
    }

    @Override
    public int repositionX(int x) {
        return 0;
    }

    @Override
    public int repositionY(int y) {
        return 0;
    }

    @Override
    public int reverseRepositionX(int x) {
        return 0;
    }

    @Override
    public int reverseRepositionY(int y) {
        return 0;
    }

}
