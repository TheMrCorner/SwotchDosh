package ucm.dv.vdm.logic;

import ucm.dv.vdm.engine.Graphics;
import ucm.dv.vdm.engine.Sprite;

public class Text extends GameObject{

    /**
     * Initializes Text.
     *
     * @param x
     * @param y
     * @param s
     */
    public Text(int x, int y, Sprite[] s) {
        super(x, y, s);

        _active = true;

        _character = 52;
    }

    public void setCharacter (char c){

        switch (c){
            case 'a':
            case 'A':
                _character = 0;
                break;

            case 'b':
            case 'B':
                _character = 1;
                break;

            case 'c':
            case 'C':
                _character = 2;
                break;

            case 'd':
            case 'D':
                _character = 3;
                break;

            case 'e':
            case 'E':
                _character = 4;
                break;

            case 'f':
            case 'F':
                _character = 5;
                break;

            case 'g':
            case 'G':
                _character = 6;
                break;

            case 'h':
            case 'H':
                _character = 7;
                break;

            case 'i':
            case 'I':
                _character = 8;
                break;

            case 'j':
            case 'J':
                _character = 9;
                break;

            case 'k':
            case 'K':
                _character = 10;
                break;

            case 'l':
            case 'L':
                _character = 11;
                break;

            case 'm':
            case 'M':
                _character = 12;
                break;

            case 'n':
            case 'N':
                _character = 13;
                break;

            case 'o':
            case 'O':
                _character = 14;
                break;

            case 'p':
            case 'P':
                _character = 15;
                break;

            case 'q':
            case 'Q':
                _character = 16;
                break;

            case 'r':
            case 'R':
                _character = 17;
                break;

            case 's':
            case 'S':
                _character = 18;
                break;

            case 't':
            case 'T':
                _character = 19;
                break;
                //-20-
            case 'u':
            case 'U':
                _character = 20;
                break;

            case 'v':
            case 'V':
                _character = 21;
                break;

            case 'w':
            case 'W':
                _character = 22;
                break;

            case 'x':
            case 'X':
                _character = 23;
                break;

            case 'y':
            case 'Y':
                _character = 24;
                break;

            case 'z':
            case 'Z':
                _character = 25;
                break;

        }

    }

    public void setNumber (int n){

        switch (n) {
            case 0:
                _character = 52;
                break;

            case 1:
                _character = 53; //27
                break;

            case 2:
                _character = 54;
                break;

            case 3:
                _character = 55;
                break;

            case 4:
                _character = 56;
                break;

            case 5:
                _character = 57;
                break;

            case 6:
                _character = 58;
                break;

            case 7:
                _character = 59;
                break;

            case 8:
                _character = 60;
                break;

            case 9:
                _character = 61;
                break;
        }

    }

    @Override
    public void render(Graphics g) {
        if (_active) {
            _sprite[_character].draw(g, (int) _x, (int) _y);
        }
    }

    public void setActive(boolean a){ _active = a;}
    public boolean getActive(){ return _active;}

    int _character; //Iterator to a specific letter or number

    boolean _active;
}
