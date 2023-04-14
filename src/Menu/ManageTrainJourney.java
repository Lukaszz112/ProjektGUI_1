package Menu;

import TrainComposition.TrainComposition;
import TrainJourney.RouteGraph;
import TrainJourney.TrainStation;

import java.util.List;

public class ManageTrainJourney {
    ChooseManagement chooseManagement = new ChooseManagement();
    public void start(
            List<TrainComposition> trainCompositionList,
            List<TrainComposition> runningTrainCompositionList,
            List<Thread> threadList,
            List<TrainStation> trainStationList
    ) {
        TrainComposition trainComposition = chooseManagement.getCorrectTrainComposition(trainCompositionList);
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
    }

    public void stop(){

    }
}
