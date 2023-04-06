package TrainComposition.TrainCars.FreightCars.Basic;

import TrainComposition.TrainCars.Interfaces.ElectricCars;
import TrainComposition.TrainCars.Abstract.FreightCars;
import TrainComposition.TrainCars.Abstract.TrainCarFreightType;

public class RefrigeratedCar extends FreightCars implements ElectricCars {
    private int minimumTemperature;
    private int basicTemperature;

    public RefrigeratedCar(
            String security,
            String sender,
            double netWeight,
            double grossWeight,
            int minimumTemperature,
            int basicTemperature
    ) {
        super(
                security,
                sender,
                netWeight,
                grossWeight,
                TrainCarFreightType.REFRIGERATED_CAR
        );
        this.basicTemperature = basicTemperature;
        this.minimumTemperature = minimumTemperature;
    }

    @Override
    public String toString() {
        return "Refrigerated car: " + super.toString() +
                ", minimum temperature: " + minimumTemperature +
                ", basic temperature: " + basicTemperature;
    }
}
