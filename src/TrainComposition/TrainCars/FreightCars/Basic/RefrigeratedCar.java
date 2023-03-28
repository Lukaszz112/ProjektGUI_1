package TrainComposition.TrainCars.FreightCars.Basic;

import TrainComposition.TrainCars.Abstract.ElectricCars;
import TrainComposition.TrainCars.Abstract.FreightCars;
import TrainComposition.TrainCars.Abstract.TrainCarFreightType;

public class RefrigeratedCar extends FreightCars implements ElectricCars {
    private int minimumTemperature;
    private int basicTemperature;

    public RefrigeratedCar(
            String security,
            double netWeight,
            double grossWeight,
            int minimumTemperature,
            int basicTemperature
    ) {
        super(
                security,
                netWeight,
                grossWeight,
                TrainCarFreightType.REFRIGERATED_CAR
        );
        this.basicTemperature = basicTemperature;
        this.minimumTemperature = minimumTemperature;
    }

}
