package Menu;

import Menu.Exception.DoesntExist;
import Menu.Interfaces.CorrectType;
import TrainComposition.Exceptions.IsNotAlreadyPluggedException;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainComposition;

import java.util.List;
import java.util.Scanner;

public class ManageTrainCompositionInit implements CorrectType {
    Scanner scan = new Scanner(System.in);

    public void initialize(
            List<TrainComposition> trainCompositionList,
            List<TrainCar> trainCarList
    ) throws DoesntExist {
        if(trainCompositionList.size() < 1){
            throw new DoesntExist("Train composition doesn't exist! Create first!");
        }

        System.out.println("========================");
        System.out.println("= 1. Add train car     =");
        System.out.println("= 2. Remove train car  =");
        System.out.println("========================");
        System.out.println("Choose correct option: ");

        int userChoice;

        do{
            userChoice = getValue(scan, Integer.class);
            if(userChoice > 2 || userChoice < 1){
                System.out.println("Please type correct number!");
            }
        }while(userChoice > 2 || userChoice < 1);

        switch (userChoice) {
            case 1 -> new ManageTrainComposition().add(trainCompositionList, trainCarList);
            case 2 -> {
                try {
                    new ManageTrainComposition().remove(trainCompositionList, trainCarList);
                }catch(IsNotAlreadyPluggedException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
