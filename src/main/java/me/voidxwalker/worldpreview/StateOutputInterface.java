package me.voidxwalker.worldpreview;

import xyz.tildejustin.stateoutput.State;
import xyz.tildejustin.stateoutput.StateOutputHelper;

public class StateOutputInterface {
    public static void outputPreviewing() {
        StateOutputHelper.outputState(State.PREVIEW);
    }
}
