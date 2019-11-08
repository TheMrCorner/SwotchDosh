package ucm.dv.vdm.engine;

import java.util.List;

public interface Input {

    // Represents screen touch inf.
    // Represents keyboard and mouse inf.
    class TouchEvent{

    }

    List<TouchEvent> getTouchEvent();

}
