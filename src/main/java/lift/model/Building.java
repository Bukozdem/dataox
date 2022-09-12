package lift.model;

import lombok.Data;
import java.util.List;

@Data
public class Building {
    private int floorQuantity;
    private int calls;
    private Lift lift;
    private List<Floor> floors;
}
