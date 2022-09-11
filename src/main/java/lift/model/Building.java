package lift.model;

import lombok.Data;
import java.util.LinkedList;

@Data
public class Building {
    private int floorQuantity;
    private int calls;
    private Lift lift;
    private LinkedList<Floor> floors;
}
