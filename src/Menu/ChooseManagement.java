package Menu;

import Menu.Interfaces.CorrectType;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainCars.Load;
import TrainComposition.TrainComposition;
import TrainJourney.TrainStation;

import java.util.List;
import java.util.Scanner;

public class ChooseManagement implements CorrectType {
    Scanner scan = new Scanner(System.in);
    public TrainComposition getCorrectTrainComposition(
            List<TrainComposition> trainCompositionList
    ){

        System.out.println("====================================");

        trainCompositionList.stream()
                .map(TrainComposition::toString)
                .forEach(System.out::println);

        System.out.println("====================================");
        System.out.println("Choose train composition (index): ");

        int userSelection;

        do{
            userSelection = getValue(scan, Integer.class);
        }while(userSelection < 1 || userSelection > trainCompositionList.size());

        int finalUserSelection1 = userSelection;

        return trainCompositionList.stream()
                .filter(x -> x.getUid() == finalUserSelection1)
                .findFirst()
                .orElse(null);
    }

    public Load getCorrectLoad(TrainCar chosenTrainCar){
        System.out.println("====================================");
        chosenTrainCar.getLoadList().stream()
                .mapToInt(i -> chosenTrainCar.getLoadList().indexOf(i))
                .mapToObj(
                        i -> String.format(
                                "%d: %s",
                                i + 1,
                                chosenTrainCar.getLoadList().get(i).toString()
                        ))
                .forEach(System.out::println);
        System.out.println("====================================");
        System.out.println("Which one do you want to remove?: ");

        int userSelection;

        do{
            userSelection = getValue(scan, Integer.class);
        }while(userSelection < 1 || userSelection > chosenTrainCar.getLoadList().size());

        int finalUserSelection = userSelection;

        return chosenTrainCar.getLoadList().get(finalUserSelection-1);
    }

    public TrainCar getCorrectTrainCar(List<TrainCar> trainCarList){
        System.out.println("====================================");

        trainCarList.stream()
                .mapToInt(trainCarList::indexOf)
                .mapToObj(i -> String.format("%d: %s", i + 1, trainCarList.get(i).toString()))
                .forEach(System.out::println);

        System.out.println("====================================");
        System.out.println("Select train car (index): ");

        int userSelection;

        do{
            userSelection = getValue(scan, Integer.class);
        }while(userSelection < 1 || userSelection > trainCarList.size());

        int finalUserSelection = userSelection;

        return trainCarList.get(finalUserSelection-1);
    }

    public TrainStation getCorrectTrainStation(List<TrainStation> trainStationList, String placeholder){

        System.out.println("====================================");

        trainStationList.stream()
                .mapToInt(trainStationList::indexOf)
                .mapToObj(i -> String.format("%d: %s", i + 1, trainStationList.get(i).toString()))
                .forEach(System.out::println);

        System.out.println("====================================");

        System.out.println(placeholder);
        int userChoice = getValue(scan, Integer.class);

        return trainStationList.get(userChoice-1);
    }
}
