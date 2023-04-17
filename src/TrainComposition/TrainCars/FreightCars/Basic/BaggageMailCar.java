package TrainComposition.TrainCars.FreightCars.Basic;

import TrainComposition.TrainCars.Abstract.FreightCars;
import TrainComposition.TrainCars.Abstract.TrainCarFreightType;

public class BaggageMailCar extends FreightCars {
    private final int amountOfLuggage;
    private final int numOfEnvelopes;

    public BaggageMailCar(
            String security,
            String sender,
            double netWeight,
            double grossWeight,
            int amountOfLuggage,
            int numOfEnvelopes
    ) {
        super(
                security,
                sender,
                netWeight,
                grossWeight,
                TrainCarFreightType.BAGGAGE_MAIL_CAR
        );

        this.amountOfLuggage = amountOfLuggage;
        this.numOfEnvelopes = numOfEnvelopes;
    }

    @Override
    public String toString() {
        return "Baggage mail car: " + super.toString() +
                ", amount of luggage: " + amountOfLuggage +
                ", number of envelopes: " + numOfEnvelopes;
    }
}
