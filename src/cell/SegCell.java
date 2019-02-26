package cell;

public class SegCell extends Cell{
    //0 = empty
    //1 = jewish
    //2 = christian

    private static final int THRESHOLD = 30;
    private double satisfaction;

    public SegCell(int state){
        super(state);
    }

    @Override
    public void checkNeighborStatus(Cell[] neighbors){
        if(this.getState()==0){
            setSatisfaction(100);
        }
        setSatisfaction(4);
    }

    private void setSatisfaction(int blah){

    }
}
