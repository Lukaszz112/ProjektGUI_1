package TrainComposition.TrainCars.FreightCars.Basic;

import TrainComposition.TrainCars.Abstract.FreightCars;
import TrainComposition.TrainCars.Abstract.TrainCarFreightType;

public class LiquidGoodsCar extends FreightCars {
    private String liquidComposition;
    private int trainCarCapacity;

    public LiquidGoodsCar(
            String security,
            String sender,
            double netWeight,
            double grossWeight,
            String liquidComposition,
            int trainCarCapacity
    ) {
        super(
                security,
                sender,
                netWeight,
                grossWeight,
                TrainCarFreightType.LIQUID_GOODS_CAR
        );

        this.liquidComposition = liquidComposition;
        this.trainCarCapacity = trainCarCapacity;
    }
}
