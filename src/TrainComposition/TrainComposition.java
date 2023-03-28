package TrainComposition;

import TrainComposition.Exceptions.*;
import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainCars.Abstract.ElectricCars;
import TrainComposition.TrainCars.Abstract.TrainCar;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TrainComposition {

    private static final AtomicInteger count = new AtomicInteger(0);
    private int uid = count.incrementAndGet();

    public int getUid() {
        return uid;
    }
    private final Locomotive locomotive;

    public Locomotive getLocomotive() {
        return locomotive;
    }
    private List<TrainCar> trainCars;
    private int sumOfElectricTrainCars = 0;

    public TrainComposition(
            Locomotive locomotive
    ) {
        this.locomotive = locomotive;
    }



    public void add(TrainCar trainCar, Locomotive toLocomotive){

        int sumOfTrainCarsWeight = trainCars.stream()
                .map(x -> (int) x.getNetWeight())
                .reduce(0,Integer::sum);

        boolean isPlugged = trainCars.stream()
                .map(TrainCar::getUid)
                .anyMatch(tmp -> tmp.equals(trainCar.getUid()));

        if(sumOfElectricTrainCars + 1 > toLocomotive.getNumOfElectricTrainCars()){
            throw new TooManyElectricCarsException(
                    "There is too many electric cars in train composition, please remove one to plug another!"
            );
        }
        if(sumOfTrainCarsWeight + trainCar.getNetWeight() > toLocomotive.getTorsion()){
            throw new TooHeavyToGoException(
                    "The car is too heavy to plug! Try another one or change the locomotive!"
            );
        }
        if(trainCars.size() + 1 > toLocomotive.getNumOfTrainCars()){
            throw new TooManyCarsException(
                    "There is too many cars in train composition, please remove one to plug another!"
            );
        }
        if(isPlugged){
            throw new IsAlreadyPluggedException(
                    "The car is already plugged! Remove first to plug again."
            );
        }

            trainCars.add(trainCar);
            sumOfElectricTrainCars += trainCar instanceof ElectricCars ? 1 : 0;
            System.out.println("The Car of id: " + trainCar.getUid() + ", plugged successfully!");
    }

    public void remove(TrainCar trainCar){
        boolean isPlugged = trainCars.stream()
                .map(TrainCar::getUid)
                .anyMatch(tmp -> tmp.equals(trainCar.getUid()));

        if(!isPlugged){
            throw new IsNotAlreadyPluggedException(
                    "The car is not already plugged! Plug first to remove again."
            );
        }

        trainCars.remove(trainCar);
        System.out.println("The Car of id: " + trainCar.getUid() + ", unplugged successfully!");
    }


    @Override
    public String toString() {
        return "Train Composition includes: " +
                "Locomotive:" + locomotive +
                ", Train Cars (uid): " + trainCars.stream()
                                            .map(i -> String.valueOf(i.getUid()))
                                            .collect(Collectors.joining(", "));

    }
}
