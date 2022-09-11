package lift;

import lift.service.StartWorkingImpl;

public class Main {
    private static final StartWorkingImpl startWorking = new StartWorkingImpl();
    public static void main(String[] args) {
        startWorking.liftOff();
    }
}
