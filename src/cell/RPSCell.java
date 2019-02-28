package cell;

public class RPSCell extends Cell{
    //0 = rock
    //1 = paper
    //2 = scissors

    private static final int THRESHOLD = 3;
    private int enemy;



    public RPSCell(int state){
        super(state);
        setEnemy();
    }

    @Override
    public void checkNeighborStatus(Cell[] neighbors){
        setEnemy();
        if(getNeighborsOfState(enemy,neighbors).size()>THRESHOLD){
            this.setNextState(enemy);
        }
    }

    private void setEnemy(){
        if(this.getState()==2){
            enemy=0;
        }
        else{
            enemy=this.getState()+1;
        }
    }
}
