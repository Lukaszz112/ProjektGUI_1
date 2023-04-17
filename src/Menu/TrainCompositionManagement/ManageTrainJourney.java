package Menu.TrainCompositionManagement;

import Menu.ChooseManagement;
import Menu.Exception.AlreadyOnJourney;
import Menu.Interfaces.CorrectType;
import TrainComposition.TrainComposition;
import TrainJourney.TrainStation;

import java.util.List;
import java.util.Scanner;

public class ManageTrainJourney implements CorrectType {
    ChooseManagement chooseManagement = new ChooseManagement();
    public void start(
            List<TrainComposition> trainCompositionList,
            List<TrainComposition> runningTrainCompositionList,
            List<Thread> threadList,
            List<TrainStation> trainStationList
    ) throws AlreadyOnJourney {
        TrainComposition trainComposition = chooseManagement.getCorrectTrainComposition(trainCompositionList);

        if(trainComposition.getLocomotive().getFinalStation() != null){
            throw new AlreadyOnJourney("Train composition is already on journey!");
        }

        TrainStation startStation = chooseManagement.getCorrectTrainStation(
                trainStationList,
                "Choose start station: "
        );
        TrainStation destinationStation = chooseManagement.getCorrectTrainStation(
                trainStationList,
                "Choose destination station: "
        );

        trainComposition.getLocomotive().setStartingStation(startStation);
        trainComposition.getLocomotive().setFinalStation(destinationStation);

        Thread thread = new Thread(trainComposition);
        thread.start();

        threadList.add(thread);

        runningTrainCompositionList.add(trainComposition);
    }

    public void stop(
            List<TrainComposition> runningTrainCompositionList
    ){
        Scanner scan = new Scanner(System.in);

        System.out.println("=====================================");

        runningTrainCompositionList.stream()
                .mapToInt(runningTrainCompositionList::indexOf)
                .mapToObj(i -> String.format("%d: %s", i + 1, runningTrainCompositionList.get(i).toString()))
                .forEach(System.out::println);

        System.out.println("=====================================");

        System.out.println("Choose train composition: ");

        int userChoice;
        do{
           userChoice = getValue(scan, Integer.class);
        }while(userChoice > runningTrainCompositionList.size() || userChoice < 0);

        TrainComposition chosenTrainComposition = runningTrainCompositionList.get(userChoice - 1);
        chosenTrainComposition.setEndingJourney(true);
        System.out.println("The train will end at the next station");
    }
}
