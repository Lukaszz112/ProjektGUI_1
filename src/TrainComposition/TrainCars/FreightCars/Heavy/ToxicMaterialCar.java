package TrainComposition.TrainCars.FreightCars.Heavy;

import TrainComposition.TrainCars.Abstract.TrainCarFreightType;

public class ToxicMaterialCar extends FreightHeavyCar{
    private final String typeOfToxicMaterial;
    private final int capacity;

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

    @Override
    public String toString() {
        return "Toxic material car: " + super.toString() +
                ", type of toxic material: " + typeOfToxicMaterial +
                ", capacity: " + capacity;
    }
}
