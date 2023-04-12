package Menu;

import Menu.Exception.DoesntExist;
import Menu.Interfaces.CorrectType;
import TrainComposition.TrainComposition;

import java.util.List;
import java.util.Scanner;

public class ManageTrainCarLoadsInit implements CorrectType {
    public void initialize(List<TrainComposition> trainCompositionList){
        Scanner scan = new Scanner(System.in);

        System.out.println("=============================");
        System.out.println("= 1. Add load               =");
        System.out.println("= 2. Remove load            =");
        System.out.println("=============================");

        int userChoice;

        do{
            userChoice = getValue(scan, Integer.class);
            if(userChoice > 2 || userChoice < 1){
                System.out.println("Please type correct number!");
            }
        }while(userChoice > 2 || userChoice < 1);

        switch (userChoice) {
            case 1 -> {
                try{
                    new ManageTrainCarLoads().add(trainCompositionList);
                }catch (DoesntExist e){
                    System.out.println(e.getMessage());
                }
            }
            case 2 -> {
                try{
                    new ManageTrainCarLoads().remove(trainCompositionList);
                }catch (DoesntExist e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
