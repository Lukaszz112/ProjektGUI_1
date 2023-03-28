package TrainComposition.TrainCars.Abstract;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class TrainCar {
    private final String security;
    private final double netWeight;
    private final double grossWeight;
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int uid;

    public double getGrossWeight() {
        return grossWeight;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public int getUid() {
        return uid;
    }

    public TrainCar(
            String security,
            double netWeight,
            double grossWeight
    ) {
        this.security = security;
        this.netWeight = netWeight;
        this.grossWeight = grossWeight;
        this.uid = count.incrementAndGet();
    }


}
