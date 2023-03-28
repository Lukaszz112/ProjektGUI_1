package TrainComposition.TrainCars.FreightCars.Heavy;

import TrainComposition.TrainCars.Abstract.TrainCarFreightType;

public class ExplosivesCar extends FreightHeavyCar{
    private String typeOfExplosives;
    private int amountOfExplosives;

    public ExplosivesCar(
            String security,
            double netWeight,
            double grossWeight,
            String typeOfExplosives,
            int amountOfExplosives
    ) {
        super(
                security,
                netWeight,
                grossWeight,
                TrainCarFreightType.EXPLOSIVES_CAR,
                "High risk of explosion",
                "ADR 1st class"
        );
        this.amountOfExplosives = amountOfExplosives;
        this.typeOfExplosives = typeOfExplosives;
    }
}
