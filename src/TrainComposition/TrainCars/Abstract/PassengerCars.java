package TrainComposition.TrainCars.Abstract;

public abstract class PassengerCars extends TrainCar{
    private final int numOfSeats;
    private final TrainCarPassengerType typeOfGoods;

    public PassengerCars(
            int numOfSeats,
            String security,
            double netWeight,
            double grossWeight,
            TrainCarPassengerType typeOfGoods
    ) {
        super(
                security,
                netWeight,
                grossWeight
        );
        this.typeOfGoods = typeOfGoods;
        this.numOfSeats = numOfSeats;
    }
    @Override
    public String toString() {
        return  "uid: " + getUid() +
                ", net weight: " + getNetWeight() +
                ", gross weight: " + getGrossWeight() +
                ", number of seats: " + numOfSeats;

    }
}
