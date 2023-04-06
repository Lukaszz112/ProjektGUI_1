package TrainComposition.TrainCars.FreightCars.Heavy;

import TrainComposition.TrainCars.Abstract.TrainCarFreightType;

public class LiquidToxicMaterialCar extends FreightHeavyCar {
    private String typeOfLiquidToxicMaterial;
    private int capacity;

    public LiquidToxicMaterialCar(
            String security,
            String sender,
            double netWeight,
            double grossWeight,
            String typeOfLiquidToxicMaterial,
            int capacity
    ) {
        super(
                security,
                sender,
                netWeight,
                grossWeight,
                TrainCarFreightType.LIQUID_TOXIC_MATERIALS_CAR,
                "High risk of contamination",
                "ADR 2nd class"
        );

        this.capacity = capacity;
        this.typeOfLiquidToxicMaterial = typeOfLiquidToxicMaterial;
    }

    @Override
    public String toString() {
        return "LiquidToxicMaterialCar: " + super.toString() +
                ", type of liquid toxic material: " + typeOfLiquidToxicMaterial +
                ", capacity: " + capacity;
    }
}
