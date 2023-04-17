package Menu;

import Menu.CreateTrainCars.CreateTrainCar;
import Menu.Exception.AlreadyOnJourney;
import Menu.Exception.IsAlreadyPlugged;
import Menu.Interfaces.CorrectType;
import TrainComposition.Exceptions.TooHeavyGoods;
import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainComposition;
import TrainJourney.TrainStation;

import java.util.List;
import java.util.Scanner;

public class CreateDeletingObjectsInit implements CorrectType {
    Scanner scan = new Scanner(System.in);
    public void initialize(
            List<TrainCar> trainCarList,
            List<TrainComposition> trainCompositionList,
            List<Locomotive> locomotiveList
    ){
        System.out.println("=================================");
        System.out.println("1. Delete Train car              ");
        System.out.println("2. Delete Locomotive             ");
        System.out.println("3. Delete Train composition      ");
        System.out.println("=================================");
        System.out.println("Choose option: ");

        int userChoice;
        do{
            userChoice = getValue(scan, Integer.class);
            if(userChoice > 2 || userChoice < 1){
                System.out.println("Please type correct number!");
            }
        }while(userChoice > 3 || userChoice < 1);

        switch (userChoice){
            case 1 -> {
                try {
                    new CreateDeletingObjects().deleteTrainCar(trainCarList);
                } catch (TooHeavyGoods e) {
                    System.out.println(e.getMessage());
                }
            }
            case 2 -> {
                try {
                    new CreateDeletingObjects().deleteLocomotive(trainCompositionList,locomotiveList);
                } catch (IsAlreadyPlugged e) {
                    System.out.println(e.getMessage());
                }
            }
            case 3 -> {
                try {
                    new CreateDeletingObjects().deleteTrainComposition(trainCompositionList);
                } catch (
                        IsAlreadyPlugged |
                        AlreadyOnJourney e
                ) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
