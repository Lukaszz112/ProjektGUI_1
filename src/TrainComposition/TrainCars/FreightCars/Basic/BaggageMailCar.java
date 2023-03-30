package TrainComposition.TrainCars.FreightCars.Basic;

import TrainComposition.TrainCars.Abstract.FreightCars;
import TrainComposition.TrainCars.Abstract.TrainCarFreightType;

public class BaggageMailCar extends FreightCars {
    private int amountOfLuggage;
    private int numOfEnvelopes;

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
}
