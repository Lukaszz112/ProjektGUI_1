package Menu.CreateTrainCars;

import TrainComposition.TrainCars.Abstract.TrainCar;

import java.util.List;
public class CreateTrainCar {
    public void create(List<TrainCar> trainCarList, int userChoice){
        switch (userChoice) {
            case 1 -> new CreateFreightCar().create(trainCarList);
            case 2 -> new CreatePassengerCar().create(trainCarList);
        }
        System.out.println("Train car created!");
    }
}
