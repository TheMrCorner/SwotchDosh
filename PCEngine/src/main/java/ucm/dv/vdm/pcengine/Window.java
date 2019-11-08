package ucm.dv.vdm.pcengine;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame{

    public Window(int width, int height, String title, Game game){
        super(title);
        _game = game;
        init(width, height);
    }

    public void init(int w, int h) {

        //Revisar si esto lo queremos así o que el graphics no dependa de la ventana
        setSize(w, h);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);

        int trys = 100;
        while(trys-- > 0) {
            try {
                createBufferStrategy(1);
                break;
            }
            catch(Exception e) {
                _game.HandleException(e);
            }
        } // while asking for the buffeStrategy creation

        setVisible(true);

        // Obtenemos el Buffer Strategy que se supone acaba de crearse.
        str = getBufferStrategy();
        setGraphics(str);

    }

    public void setGraphics(BufferStrategy str){
        g = str.getDrawGraphics();
    }

    public java.awt.Graphics getJGraphics (){
        return g;
    }

    java.awt.image.BufferStrategy str;

    java.awt.Graphics g;

    Game _game;


}
