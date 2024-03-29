package dsw.rumap.app.gui.swing.state;

import dsw.rumap.app.gui.swing.state.stateimpl.*;

public class StateManager {

    private State currentState;
    private AddRelationState addRelationState;
    private AddTermState addTermState;
    private DeleteElementState deleteElementState;
    private MoveElementState moveElementState;
    private SelectElementState selectElementState;

    public StateManager(){
        initialiseStates();
    }

    private void initialiseStates(){
        addRelationState = new AddRelationState();
        addTermState = new AddTermState();
        deleteElementState = new DeleteElementState();
        moveElementState = new MoveElementState();
        selectElementState = new SelectElementState();
        currentState = addTermState;
    }

    public void setAddRelationState() {

        this.currentState = addRelationState;
    }

    public void setAddTermState() {
        this.currentState = addTermState;
    }

    public void setDeleteElementState() {
        this.currentState = deleteElementState;
    }

    public void setMoveElementState() {
        this.currentState = moveElementState;
    }

    public void setSelectElementState() {
        this.currentState = selectElementState;
    }

    public State getCurrentState(){
        return currentState;
    }
}
