package Menu;

import Menu.Exception.DoesntExist;
import Menu.Interfaces.CorrectType;
import TrainComposition.Exceptions.*;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainComposition;

import java.util.List;
import java.util.Scanner;

public class ManageTrainComposition implements CorrectType {
    Scanner scan = new Scanner(System.in);
    public void add(
            List<TrainComposition> trainCompositionList,
            List<TrainCar> trainCarList
    ) throws
            DoesntExist
    {

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

        if(trainCarList.size() < 1){
            throw new DoesntExist("Train car doesn't exist! Create first!");
        }

        System.out.println("====================================");

        trainCarList.stream()
                .mapToInt(trainCarList::indexOf)
                .mapToObj(i -> String.format("%d: %s", i + 1, trainCarList.get(i).toString()))
                .forEach(System.out::println);

        System.out.println("====================================");
        System.out.println("Select train car (index): ");

        int userSelection2;

        do{
            userSelection2 = getValue(scan, Integer.class);
        }while(userSelection2 < 1 || userSelection2 > trainCarList.size());

        int finalUserSelection2 = userSelection2;

        TrainCar chosenTrainCar = trainCarList.get(finalUserSelection2-1);

        try{
            assert chosenTrainComposition != null;
            chosenTrainComposition.add(chosenTrainCar);
            trainCarList.remove(chosenTrainCar);
            System.out.println("The Train Car plugged successfully!");
        }catch (
                TooManyElectricCarsException |
                TooHeavyToGoException |
                TooManyCarsException |
                IsAlreadyPluggedException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void remove(
            List<TrainComposition> trainCompositionList,
            List<TrainCar> trainCarList
    ) throws
            IsNotAlreadyPluggedException
    {
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

        List<TrainCar> localTrainCarList = trainCompositionList.stream()
                .flatMap(x -> x.getTrainCars().stream())
                .toList();

        if(!(localTrainCarList.size() > 0)){
            throw new IsNotAlreadyPluggedException(
                    "Train set does not include any train cars! Please plug first!"
            );
        }

        System.out.println("====================================");

        localTrainCarList.stream()
                .mapToInt(localTrainCarList::indexOf)
                .mapToObj(i -> String.format("%d: %s", i + 1, localTrainCarList.get(i).toString()))
                .forEach(System.out::println);

        System.out.println("====================================");
        System.out.println("Select train car (index): ");

        int userSelection2;

        do{
            userSelection2 = getValue(scan, Integer.class);
        }while(userSelection2 < 1 || userSelection2 > localTrainCarList.size());

        int finalUserSelection2 = userSelection2;

        TrainCar chosenTrainCar = localTrainCarList.get(finalUserSelection2 - 1);

        try{
            assert chosenTrainComposition != null;
            chosenTrainComposition.remove(chosenTrainCar);
            trainCarList.add(chosenTrainCar);
            System.out.println("The Train Car unplugged successfully!");
        }catch (IsNotAlreadyPluggedException e) {
            System.out.println(e.getMessage());
        }
    }
}
