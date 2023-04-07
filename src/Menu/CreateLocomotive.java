package Menu;

import Menu.Interfaces.CorrectType;
import TrainComposition.Locomotive.Locomotive;
import TrainJourney.StationData;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CreateLocomotive implements CorrectType{
    Scanner scan = new Scanner(System.in);

    public Locomotive createLocomotive(){
        System.out.println("Enter locomotive name: ");
        String name = scan.next();

        for (int i = 0; i < StationData.values().length; i++) {
            System.out.println(i+1 + ". " + StationData.values()[i]);
        }

        System.out.println("Choose station: ");
        int userChoice;
        do{
            userChoice = getValue(scan, Integer.class);
        }while(userChoice >= StationData.values().length || userChoice < 0);

        StationData homeStation = StationData.values()[userChoice-1];

        System.out.println("Enter maximum quantity of train cars: ");
        int numOfTrainCars = getValue(scan, Integer.class);

        System.out.println("Enter maximum quantity of electric cars: ");
        int numOfElectricTrainCars = getValue(scan, Integer.class);

        System.out.println("Enter maximum weight of train cars: ");
        double torsion = getValue(scan, Double.class);

        System.out.println("Locomotive created!");

        return new Locomotive(name, homeStation, numOfTrainCars, numOfElectricTrainCars, torsion);
    }

    public void createTestLocomotives(List<Locomotive> locomotiveList){
        Random rand = new Random();
        for (int i = 1; i < AppConfig.quantityOfLocomotives; i++) {
            locomotiveList.add(
                    new Locomotive(
                            "Locomotive " + i,
                            StationData.values()[i],
                            rand.nextInt(25)+10,
                            rand.nextInt(10)+5,
                            rand.nextInt(10000)+14000)
            );
        }
    }
}
