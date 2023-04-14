package Menu;

import Menu.Interfaces.CorrectType;
import TrainComposition.Locomotive.Locomotive;
import TrainJourney.StationData;
import TrainJourney.TrainStation;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CreateLocomotive implements CorrectType{
    Scanner scan = new Scanner(System.in);

    public Locomotive createLocomotive(
            List<TrainStation> trainStationList
    ){
        System.out.println("Enter locomotive name: ");
        String name = scan.next();
        Random rand = new Random();

        System.out.println("Enter maximum quantity of train cars: ");
        int numOfTrainCars = getValue(scan, Integer.class);

        System.out.println("Enter maximum quantity of electric cars: ");
        int numOfElectricTrainCars = getValue(scan, Integer.class);

        System.out.println("Enter maximum weight of train cars: ");
        double torsion = getValue(scan, Double.class);

        TrainStation homeStation = new ChooseManagement().getCorrectTrainStation(
                trainStationList,
                "Choose station: "
        );

        return new Locomotive(
                name,
                homeStation,
                numOfTrainCars,
                numOfElectricTrainCars,
                torsion
        );
    }

    public void createTestLocomotives(
            List<Locomotive> locomotiveList,
            List<TrainStation> trainStationList
    ){
        Random rand = new Random();
        for (int i = 1; i < AppConfig.quantityOfLocomotives; i++) {
            locomotiveList.add(
                    new Locomotive(
                            "Locomotive " + i,
                            trainStationList.get(rand.nextInt(99)),
                            rand.nextInt(25)+10,
                            rand.nextInt(AppConfig.quantityOfElectricCars)+15,
                            rand.nextInt(10000)+14000)
            );
        }
    }
}
