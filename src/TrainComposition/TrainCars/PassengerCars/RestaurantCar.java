package TrainComposition.TrainCars.PassengerCars;

import TrainComposition.TrainCars.Abstract.ElectricCars;
import TrainComposition.TrainCars.Abstract.PassengerCars;
import TrainComposition.TrainCars.Abstract.TrainCarPassengerType;

public class RestaurantCar extends PassengerCars implements ElectricCars {
    private int numOfCrew;
    private String responsibleCompany;

    public RestaurantCar(
            int numOfSeats,
            String security,
            double netWeight,
            double grossWeight,
            int numOfCrew,
            String responsibleCompany
    ) {
        super(
                numOfSeats,
                security,
                netWeight,
                grossWeight,
                TrainCarPassengerType.RESTAURANT_CAR
        );

        this.numOfCrew = numOfCrew;
        this.responsibleCompany = responsibleCompany;
    }

}
