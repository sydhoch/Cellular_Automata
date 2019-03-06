package Enums;

public enum SimType {
    FIRE(3),
    GOL(2),
    PERC(3),
    PP(3),
    RPS(3),
    SEG(3);

    private final int myNumStates;

    SimType(int numState){
        myNumStates = numState;
    }

    public int getNumStates(){
        return myNumStates;
    }
}

