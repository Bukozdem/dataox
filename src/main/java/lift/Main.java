package lift;

import lift.service.StartWorking;

public class Main {
    private static final StartWorking startWorking = new StartWorking();
    public static void main(String[] args) {
        startWorking.liftOff();
    }
}
