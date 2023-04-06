package TrainComposition.TrainCars.FreightCars.Heavy;

import TrainComposition.TrainCars.Abstract.FreightCars;
import TrainComposition.TrainCars.Abstract.TrainCarFreightType;

public abstract class FreightHeavyCar extends FreightCars {
    private String warnings;
    private String requiredLicenses;

    public FreightHeavyCar(
            String security,
            String sender,
            double netWeight,
            double grossWeight,
            TrainCarFreightType typeOfGoods,
            String warnings,
            String requiredLicenses
    ) {
        super(
                security,
                sender,
                netWeight,
                grossWeight,
                typeOfGoods
        );
        this.warnings = warnings;
        this.requiredLicenses = requiredLicenses;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", warnings: " + warnings +
                ", requiredLicenses: " + requiredLicenses;
    }
}
