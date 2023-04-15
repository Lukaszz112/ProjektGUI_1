package TrainComposition.TrainCars.Abstract;

public abstract class PassengerCars extends TrainCar{
    private final int numOfSeats;

    public PassengerCars(
            int numOfSeats,
            String security,
            double netWeight,
            double grossWeight
    ) {
        super(
                netWeight,
                grossWeight
        );
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
