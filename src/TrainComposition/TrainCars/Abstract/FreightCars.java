package TrainComposition.TrainCars.Abstract;

import TrainComposition.TrainCars.Exceptions.InvalidTypeOfGoods;
import TrainComposition.TrainCars.Exceptions.TooHeavyGoods;

import java.util.Objects;
public abstract class FreightCars extends TrainCar {
    private String sender;
    private TrainCarFreightType typeOfGoods;
    private double weightOfTheGoods = 0;

    public FreightCars(
            String security,
            String sender,
            double netWeight,
            double grossWeight,
            TrainCarFreightType typeOfGoods
    ) {
        super(
                security,
                netWeight,
                grossWeight
        );
        this.typeOfGoods = typeOfGoods;
        this.sender = sender;
    }

    public void addLoad(TrainCarFreightType typeOfGoods, double weightOfTheGoods, String sender){
        if(!Objects.equals(typeOfGoods, this.typeOfGoods)){
            throw new InvalidTypeOfGoods(
                    "This type of goods is not correct to current car! Choose another one."
            );
        }
        if(getNetWeight() + this.weightOfTheGoods + weightOfTheGoods > getGrossWeight()){
            throw new TooHeavyGoods(
                    "These goods are too heavy for current car! Choose another one or remove some goods"
            );
        }

        this.weightOfTheGoods += weightOfTheGoods;
        this.sender = sender;
        System.out.println("Added successfully! Current car weight: " +
                getNetWeight() +
                this.weightOfTheGoods
        );
    }

    @Override
    public String toString() {
        return  "uid: " + getUid() +
                ", sender: " + sender +
                ", net weight: " + getNetWeight() +
                ", gross weight: " + getGrossWeight();

    }
}
