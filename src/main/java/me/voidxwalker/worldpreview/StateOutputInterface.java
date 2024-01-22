package me.voidxwalker.worldpreview;

import dev.tildejustin.stateoutput.*;

public class StateOutputInterface {
    public static void outputPreviewing() {
        // change the state to previewing, without changing the static progress
        StateOutputHelper.outputState(State.PREVIEW);
    }
}
