package Menu.CreateTrainCars;

import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainCars.Abstract.TrainCarPassengerType;
import TrainComposition.TrainCars.PassengerCars.PassengerCar;
import TrainComposition.TrainCars.PassengerCars.RestaurantCar;

import java.util.List;
import java.util.Scanner;

public class CreatePassengerCar {
    private final TrainCarPassengerType[] trainCarPassengerTypes = TrainCarPassengerType.values();
    Scanner scan = new Scanner(System.in);
    public void create(List<TrainCar> trainCarList){
        System.out.println("Number of seats: ");
        int numOfSeats = scan.nextInt();

        System.out.println("Security company: ");
        String security = scan.next();

        System.out.println("Net weight: ");
        double netWeight = scan.nextDouble();

        System.out.println("Gross weight: ");
        double grossWeight = scan.nextDouble();

        System.out.println("==========================");
        for (int i = 0; i < trainCarPassengerTypes.length; i++) {
            System.out.println(i + 1 + ". " + trainCarPassengerTypes[i]);
        }
        System.out.println("==========================");

        System.out.println("Choose type of passenger cars (id): ");

        int temp;

        do {
            temp = scan.nextInt();
            if(temp < 0 || temp > trainCarPassengerTypes.length){
                System.out.println("Please select correct type!");
            }
        }while(temp < 0 || temp > trainCarPassengerTypes.length);

        switch (temp){
            case 1 -> {
                System.out.println("Number of compartment: ");
                int numOfCompartment = scan.nextInt();
                System.out.println("Number of Vip seats: ");
                int numOfVipSeats = scan.nextInt();
                trainCarList.add(
                        new PassengerCar(
                                numOfSeats,
                                security,
                                netWeight,
                                grossWeight,
                                numOfCompartment,
                                numOfVipSeats
                        )
                );
            }
            case 2 -> {
                System.out.println("Number of compartment: ");
                int numOfCrew = scan.nextInt();
                System.out.println("Number of Vip seats: ");
                String responsibleCompany = scan.next();
                trainCarList.add(
                        new RestaurantCar(
                                numOfSeats,
                                security,
                                netWeight,
                                grossWeight,
                                numOfCrew,
                                responsibleCompany
                        )
                );
            }
        }
    }
}
