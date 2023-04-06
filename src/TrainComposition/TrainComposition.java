package TrainComposition;

import TrainComposition.Exceptions.*;
import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainCars.Interfaces.ElectricCars;
import TrainComposition.TrainCars.Abstract.TrainCar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TrainComposition {

    private static final AtomicInteger count = new AtomicInteger(0);
    private final int uid = count.incrementAndGet();

    public int getUid() {
        return uid;
    }
    private final Locomotive locomotive;

    public Locomotive getLocomotive() {
        return locomotive;
    }
    private final List<TrainCar> trainCars = new ArrayList<>();

    public List<TrainCar> getTrainCars() {
        return trainCars;
    }

    private int sumOfElectricTrainCars = 0;

    public TrainComposition(
            Locomotive locomotive
    ) {
        this.locomotive = locomotive;
    }

    public void add(TrainCar trainCar) throws
            TooManyElectricCarsException,
            TooHeavyToGoException,
            TooManyCarsException,
            IsAlreadyPluggedException

    {

        int sumOfTrainCarsWeight = trainCars.stream()
                .map(x -> (int) x.getNetWeight())
                .reduce(0,Integer::sum);

        if(trainCar instanceof ElectricCars){
            if(sumOfElectricTrainCars + 1 > locomotive.getNumOfElectricTrainCars())
                throw new TooManyElectricCarsException(
                        "There is too many electric cars in train composition, please remove one to plug another!"
                );
        }

        if(sumOfTrainCarsWeight + trainCar.getNetWeight() > this.locomotive.getTorsion()){
            throw new TooHeavyToGoException(
                    "The car is too heavy to plug! Try another one or change the locomotive!"
            );
        }
        if(trainCars.size() + 1 > this.locomotive.getNumOfTrainCars()){
            throw new TooManyCarsException(
                    "There is too many cars in train composition, please remove one to plug another!"
            );
        }

            trainCars.add(trainCar);
            sumOfElectricTrainCars += trainCar instanceof ElectricCars ? 1 : 0;
            System.out.println("The Car of id: " + trainCar.getUid() + ", plugged successfully!");
    }

    public void remove(TrainCar trainCar) throws IsNotAlreadyPluggedException {
        trainCars.remove(trainCar);
        System.out.println("The Car of id: " + trainCar.getUid() + ", unplugged successfully!");
    }


    @Override
    public String toString() {
        return uid + ". Train Composition includes: " +
                "Locomotive: " + locomotive +
                ", Train Cars (uid): " +
                (
                                trainCars.stream()
                                            .map(i -> String.valueOf(i.getUid()))
                                            .collect(Collectors.joining(", "))
                );


    }
}
