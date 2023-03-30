package Menu;

import Menu.Interfaces.CorrectType;
import TrainComposition.Exceptions.*;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainComposition;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ManageTrainComposition implements CorrectType {
    Scanner scan = new Scanner(System.in);
    public void add(List<TrainComposition> trainCompositionList, List<TrainCar> trainCarList){
        System.out.println("====================================");

        trainCompositionList.stream()
                        .map(TrainComposition::toString)
                        .forEach(System.out::println);

        System.out.println("====================================");
        System.out.println("Where do you want to add train car (index): ");

        int userSelection1;

        do{
            userSelection1 = getValue(scan, Integer.class);
        }while(userSelection1 < 1 || userSelection1 > trainCompositionList.size());

        int finalUserSelection1 = userSelection1;

        TrainComposition chosenTrainComposition = trainCompositionList.stream()
                .filter(x -> x.getUid() == finalUserSelection1)
                .findFirst()
                .orElse(null);

        System.out.println("====================================");

        trainCarList.stream()
                .map(TrainCar::toString)
                .forEach(System.out::println);

        System.out.println("====================================");
        System.out.println("Select train car (uid): ");

        int userSelection2;

        do{
            userSelection2 = getValue(scan, Integer.class);
        }while(userSelection2 < 1 || userSelection2 > trainCarList.size());

        int finalUserSelection2 = userSelection2;

        TrainCar chosenTrainCar = trainCarList.stream()
                .filter(x -> x.getUid() == finalUserSelection2)
                .findFirst()
                .orElse(null);

        try{
            assert chosenTrainComposition != null;
            chosenTrainComposition.add(chosenTrainCar);
        }catch (
                TooManyElectricCarsException |
                TooHeavyToGoException |
                TooManyCarsException |
                IsAlreadyPluggedException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void remove(List<TrainComposition> trainCompositionList){
        System.out.println("====================================");

        trainCompositionList.stream()
                .map(TrainComposition::toString)
                .forEach(System.out::println);

        System.out.println("====================================");
        System.out.println("Choose train composition (index): ");

        int userSelection1;

        do{
            userSelection1 = getValue(scan, Integer.class);
        }while(userSelection1 < 1 || userSelection1 > trainCompositionList.size());

        int finalUserSelection1 = userSelection1;

        TrainComposition chosenTrainComposition = trainCompositionList.stream()
                .filter(x -> x.getUid() == finalUserSelection1)
                .findFirst()
                .orElse(null);

        System.out.println("====================================");

        trainCompositionList.stream()
                        .map(TrainComposition::getTrainCars)
                        .forEach(System.out::println);

        System.out.println("====================================");
        System.out.println("Select train car (uid): ");

        int userSelection2;

        List<TrainCar> trainCars = trainCompositionList.stream()
                .flatMap(x -> x.getTrainCars().stream())
                .toList();

        do{
            userSelection2 = getValue(scan, Integer.class);
        }while(userSelection2 < 1 || userSelection2 > trainCars.size());

        int finalUserSelection2 = userSelection2;

        TrainCar chosenTrainCar = trainCars.stream()
                .filter(x -> x.getUid() == finalUserSelection2)
                .findFirst()
                .orElse(null);

        try{
            assert chosenTrainComposition != null;
            chosenTrainComposition.remove(chosenTrainCar);
        }catch (IsNotAlreadyPluggedException e) {
            System.out.println(e.getMessage());
        }
    }
}
