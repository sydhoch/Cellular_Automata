package cell;

public class FireCell extends Cell{
    //0 = empty
    //1 = tree
    //2 = burning

    public FireCell(int state){
        super(state);
    }

    @Override
    public void checkNeighborStatus(Cell[] neighbors){
        //south = neighbors[1]
        //west = neighbors[3]
        //east = neighbors[4]
        //north = neighbors[6]
    }
}
