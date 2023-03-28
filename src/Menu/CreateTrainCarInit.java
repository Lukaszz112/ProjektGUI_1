package Menu;

import Menu.CreateTrainCars.CreateTrainCar;
import TrainComposition.TrainCars.Abstract.TrainCar;

import java.util.List;
import java.util.Scanner;

public class CreateTrainCarInit {
    Scanner scan = new Scanner(System.in);

    public void initialize(List<TrainCar> trainCarList){
        System.out.println("===================");
        System.out.println("1. Freight Car     ");
        System.out.println("2. Passenger Car   ");
        System.out.println("===================");
        System.out.println("Which type of train car do you want to create (id): ");

        int userChoice;
        do{
            userChoice = scan.nextInt();
            if(userChoice > 2 || userChoice < 1){
                System.out.println("Please type correct number!");
            }
        }while(userChoice > 2 || userChoice < 1);

        new CreateTrainCar().create(trainCarList, userChoice);
    }

}
