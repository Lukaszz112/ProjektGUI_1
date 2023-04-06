package Menu;

import Menu.Exception.TrainStationExist;
import Menu.Interfaces.CorrectType;
import TrainJourney.StationData;
import TrainJourney.TrainStation;

import java.util.List;
import java.util.Scanner;

public class CreateTrainStation implements CorrectType {
    Scanner scan = new Scanner(System.in);

    public void createTestStation(List<TrainStation> trainStationList){
        StationData[] trainStationNames = StationData.values();

        for (StationData a :
                trainStationNames) {
            trainStationList.add(new TrainStation(String.valueOf(a)));
        }
    }
    public void create(List<TrainStation> trainStationList) throws TrainStationExist {
        System.out.println("Insert station name: ");
        String name = scan.next();

        String trainStationExist = trainStationList.stream()
                .map(TrainStation::getName)
                .filter(x -> x.equals(name))
                .findFirst()
                .orElse(null);

        if(name.equals(trainStationExist)){
            throw new TrainStationExist("Name is already used!");
        }

        trainStationList.add(new TrainStation(name));
        System.out.println("Train station created!");
    }
}
