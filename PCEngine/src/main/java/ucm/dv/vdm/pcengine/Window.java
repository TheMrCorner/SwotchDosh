package ucm.dv.vdm.pcengine;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame{

    public Window(int width, int height, String title){
        super(title);
        init(width, height);
    }

    public void init(int w, int h) {

        //Revisar si esto lo queremos así o que el graphics no dependa de la ventana
        setSize(w, h);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);

        createBufferStrategy(2);



    }

    public void setGraphics(BufferStrategy str){
        g = str.getDrawGraphics();
    }

    public java.awt.Graphics getJGraphics (){
        return g;
    }

    java.awt.image.BufferStrategy str;

    java.awt.Graphics g;

}
