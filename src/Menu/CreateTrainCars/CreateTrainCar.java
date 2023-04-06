package Menu.CreateTrainCars;

import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainCars.Abstract.PassengerCars;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainCars.Abstract.TrainCarPassengerType;
import TrainComposition.TrainCars.FreightCars.Basic.GaseousMaterialsCar;
import TrainComposition.TrainCars.PassengerCars.PassengerCar;

import java.util.List;
public class CreateTrainCar {
    public void create(List<TrainCar> trainCarList, int userChoice){
        switch (userChoice) {
            case 1 -> new CreateFreightCar().create(trainCarList);
            case 2 -> new CreatePassengerCar().create(trainCarList);
        }
        System.out.println("Train car created!");
    }

    public void createTestTraincars(List<TrainCar> trainCarList){
        for (int i = 1; i < 3; i++) {
            trainCarList.add(new PassengerCar(i,String.valueOf(i), i, i, i, i));
            trainCarList.add(new GaseousMaterialsCar(String.valueOf(i),String.valueOf(i), i, i, String.valueOf(i), i));
        }
    }
}
