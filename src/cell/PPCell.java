package cell;

public class PPCell extends Cell {
    private static final int lifeDeath = 3; //number at which new fish is born & how long shark can live without food
    private int energy;
    private int survivalNum;
    public PPCell(int state){
        super(state);
        energy = lifeDeath;
        survivalNum = 0;
    }

    @Override
    public void checkNeighborStatus(Cell[] neighbors){
        //south = neighbors[1]
        //west = neighbors[3]
        //east = neighbors[4]
        //north = neighbors[6]
        Cell[] neighbors2 = new Cell[4];
        neighbors2[0] = neighbors[1];
        neighbors2[1] = neighbors[3];
        neighbors2[2] = neighbors[4];
        neighbors2[3] = neighbors[6];

        if(this.getState()==1){

        }
    }
}
