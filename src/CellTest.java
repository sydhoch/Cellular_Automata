import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    private Cell c1;
    private Cell c2;

    @BeforeEach
    void setup(){
         c1 = new PercCell(0,1,1);
         c1.checkNeighborStatus();



         c2 = new GoLCell(0,1,1);
    }

    @Test
    public void updatePercCell() {
    }

    @Test
    public void updateGoLCell() {
    }

    @Test
    public void getRectangle() {
    }

    @Test
    public void setNextState() {
    }

    @Test
    public void getState() {
    }

    @Test
    public void equals() {
    }
}
