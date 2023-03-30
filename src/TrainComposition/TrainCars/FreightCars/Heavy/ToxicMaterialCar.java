package TrainComposition.TrainCars.FreightCars.Heavy;

import TrainComposition.TrainCars.Abstract.TrainCarFreightType;

public class ToxicMaterialCar extends FreightHeavyCar{
    private String typeOfToxicMaterial;
    private int capacity;

    public ToxicMaterialCar(
            String security,
            String sender,
            double netWeight,
            double grossWeight,
            String typeOfToxicMaterial,
            int capacity
    ) {
        super(
                security,
                sender,
                netWeight,
                grossWeight,
                TrainCarFreightType.TOXIC_MATERIALS_CAR,
                "High risk of contamination",
                "ADR 2nd class"
        );

        this.capacity = capacity;
        this.typeOfToxicMaterial = typeOfToxicMaterial;
    }
}
