package Menu;

import TrainComposition.Locomotive.Locomotive;

import java.util.Scanner;

public class CreateLocomotive {
    private String name;
    private String homeStation;
    private int numOfTrainCars;
    private int numOfElectricTrainCars;
    private double torsion;

    Scanner scan = new Scanner(System.in);

    public Locomotive createLocomotive(){
        System.out.println("Enter locomotive name: ");
        name = scan.next();
        System.out.println("Enter name of home station: ");
        homeStation = scan.next();
        System.out.println("Enter maximum quantity of train cars: ");
        numOfTrainCars = scan.nextInt();
        System.out.println("Enter maximum quantity of electric cars: ");
        numOfElectricTrainCars = scan.nextInt();
        System.out.println("Enter maximum weight of train cars: ");
        torsion = scan.nextDouble();

        return new Locomotive(name, homeStation, numOfTrainCars, numOfElectricTrainCars, torsion);
    }
}
