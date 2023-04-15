package TrainJourney;

import TrainComposition.TrainComposition;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TrainStation{
    private String name;
    private final List<TrainComposition> queue = new LinkedList<>();
    public List<TrainComposition> getQueue() {
        return queue;
    }
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
        this.uid = count.incrementAndGet();
    }

    @Override
    public String toString() {
        return name;
    }
}
