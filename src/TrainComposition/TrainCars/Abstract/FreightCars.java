package TrainComposition.TrainCars.Abstract;

public abstract class FreightCars extends TrainCar {
    private final String sender;

    public FreightCars(
            String security,
            String sender,
            double netWeight,
            double grossWeight,
            TrainCarFreightType baggageMailCar) {
        super(
                netWeight,
                grossWeight
        );
        this.sender = sender;
    }

    @Override
    public String toString() {
        return  "uid: " + getUid() +
                ", sender: " + sender +
                ", net weight: " + getNetWeight() +
                ", gross weight: " + getGrossWeight();

    }
}
