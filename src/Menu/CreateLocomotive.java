package Menu;

import Menu.Interfaces.CorrectType;
import TrainComposition.Locomotive.Locomotive;

import java.util.List;
import java.util.Scanner;

public class CreateLocomotive implements CorrectType {
    Scanner scan = new Scanner(System.in);

    public Locomotive createLocomotive(){
        System.out.println("Enter locomotive name: ");
        String name = scan.next();

        System.out.println("Enter name of home station: ");
        String homeStation = scan.next();

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
        for (int i = 1; i < 6; i++) {
            locomotiveList.add(new Locomotive(String.valueOf(i),String.valueOf(i),i,i,i));
        }
    }
}
