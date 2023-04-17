package TrainComposition.TrainCars.Abstract;

public abstract class FreightCars extends TrainCar {
    private final String sender;
    TrainCarFreightType typeOfGoods;

    public FreightCars(
            String security,
            String sender,
            double netWeight,
            double grossWeight,
            TrainCarFreightType typeOfGoods
    ) {
        super(
                netWeight,
                grossWeight
        );
        this.sender = sender;
        this.typeOfGoods = typeOfGoods;
    }

    @Override
    public String toString() {
        return  "uid: " + getUid() +
                ", sender: " + sender +
                ", net weight: " + getNetWeight() +
                ", gross weight: " + getGrossWeight();

    }
}
