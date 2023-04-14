package TrainJourney;

import TrainComposition.TrainComposition;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class TrainStation {
    private String name;

    private Queue<TrainComposition> queue;
    private int uid;
    private static final AtomicInteger count = new AtomicInteger(0);

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public TrainStation(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
