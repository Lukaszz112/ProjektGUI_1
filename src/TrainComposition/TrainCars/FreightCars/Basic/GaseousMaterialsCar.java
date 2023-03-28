package TrainComposition.TrainCars.FreightCars.Basic;

import TrainComposition.TrainCars.Abstract.FreightCars;
import TrainComposition.TrainCars.Abstract.TrainCarFreightType;

public class GaseousMaterialsCar extends FreightCars {
    private String typeOfGas;
    private int resublimationTemperature;

    public GaseousMaterialsCar(
            String security,
            double netWeight,
            double grossWeight,
            String typeOfGas,
            int resublimationTemperature
    ) {
        super(
                security,
                netWeight,
                grossWeight,
                TrainCarFreightType.GASEOUS_MATERIALS_CAR
        );

        this.resublimationTemperature = resublimationTemperature;
        this.typeOfGas = typeOfGas;
    }
}
