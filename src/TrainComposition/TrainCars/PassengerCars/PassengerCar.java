package TrainComposition.TrainCars.PassengerCars;

import TrainComposition.TrainCars.Abstract.ElectricCars;
import TrainComposition.TrainCars.Abstract.PassengerCars;
import TrainComposition.TrainCars.Abstract.TrainCarPassengerType;

public class PassengerCar extends PassengerCars implements ElectricCars {
    private int numOfCompartment;
    private int numOfVipSeats;

    public PassengerCar(
            int numOfSeats,
            String security,
            double netWeight,
            double grossWeight,
            int numOfCompartment,
            int numOfVipSeats
    ) {
        super(
                numOfSeats,
                security,
                netWeight,
                grossWeight,
                TrainCarPassengerType.PASSENGER_CAR
        );

        this.numOfCompartment = numOfCompartment;
        this.numOfVipSeats = numOfVipSeats;
    }

}
