package Menu.CreateTrainCars;

import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainCars.Abstract.TrainCarFreightType;
import TrainComposition.TrainCars.FreightCars.Basic.*;
import TrainComposition.TrainCars.FreightCars.Heavy.ExplosivesCar;
import TrainComposition.TrainCars.FreightCars.Heavy.LiquidToxicMaterialCar;
import TrainComposition.TrainCars.FreightCars.Heavy.ToxicMaterialCar;

import java.util.List;
import java.util.Scanner;

public class CreateFreightCar {
    private final TrainCarFreightType[] trainCarFreightTypes = TrainCarFreightType.values();
    Scanner scan = new Scanner(System.in);
    public void create(List<TrainCar> trainCarList){
        System.out.println("Security company: ");
        String security = scan.next();

        System.out.println("Net weight: ");
        double netWeight = scan.nextDouble();

        System.out.println("Gross weight: ");
        double grossWeight = scan.nextDouble();

        System.out.println("==========================");
        for (int i = 0; i < trainCarFreightTypes.length; i++) {
            System.out.println(i + 1 + ". " + trainCarFreightTypes[i]);
        }
        System.out.println("==========================");

        System.out.println("Choose type of freight cars (id): ");

        int temp;

        do {
            temp = scan.nextInt();
            if(temp < 0 || temp > trainCarFreightTypes.length){
                System.out.println("Please select correct type!");
            }
        }while(temp < 0 || temp > trainCarFreightTypes.length);

        switch (temp) {
            case 1 -> {
                System.out.println("Amount of Luggage: ");
                int amountOfLuggage = scan.nextInt();
                System.out.println("Number of Envelopes: ");
                int numOfEnvelopes = scan.nextInt();
                trainCarList.add(
                        new BaggageMailCar(
                                security,
                                netWeight,
                                grossWeight,
                                amountOfLuggage,
                                numOfEnvelopes
                        )
                );
            }
            case 2 -> {
                System.out.println("Type of gas: ");
                String typeOfGas = scan.next();
                System.out.println("Resublimation temperature: ");
                int resublimationTemperature = scan.nextInt();
                trainCarList.add(
                        new GaseousMaterialsCar(
                                security,
                                netWeight,
                                grossWeight,
                                typeOfGas,
                                resublimationTemperature
                        )
                );
            }
            case 3 -> {
                System.out.println("Train car capacity: ");
                int trainCarCapacity = scan.nextInt();
                System.out.println("Liquid composition: ");
                String liquidComposition = scan.next();
                trainCarList.add(
                        new LiquidGoodsCar(
                                security,
                                netWeight,
                                grossWeight,
                                liquidComposition,
                                trainCarCapacity
                        )
                );
            }
            case 4 -> {
                System.out.println("Number of shipments: ");
                int numOfShipments = scan.nextInt();
                System.out.println("Responsible company: ");
                String responsibleCompany = scan.next();
                trainCarList.add(
                        new MailCar(
                                security,
                                netWeight,
                                grossWeight,
                                numOfShipments,
                                responsibleCompany
                        )
                );
            }
            case 5 -> {
                System.out.println("Minimum temperature: ");
                int minimumTemperature = scan.nextInt();
                System.out.println("Basic temperature: ");
                int basicTemperature = scan.nextInt();
                trainCarList.add(
                        new RefrigeratedCar(
                                security,
                                netWeight,
                                grossWeight,
                                minimumTemperature,
                                basicTemperature
                        )
                );
            }
            case 6 -> {
                System.out.println("Type of explosives: ");
                String typeOfExplosives = scan.next();
                System.out.println("Amount of explosives: ");
                int amountOfExplosives = scan.nextInt();
                trainCarList.add(
                        new ExplosivesCar(
                                security,
                                netWeight,
                                grossWeight,
                                typeOfExplosives,
                                amountOfExplosives
                        )
                );
            }
            case 7 -> {
                System.out.println("Type of liquid toxic material: ");
                String typeOfLiquidToxicMaterial = scan.next();
                System.out.println("Train car capacity: ");
                int capacity = scan.nextInt();
                trainCarList.add(
                        new LiquidToxicMaterialCar(
                                security,
                                netWeight,
                                grossWeight,
                                typeOfLiquidToxicMaterial,
                                capacity
                        )
                );
            }
            case 8 -> {
                System.out.println("Type of toxic material: ");
                String typeOfToxicMaterial = scan.next();
                System.out.println("Train car capacity: ");
                int classicToxicCapacity = scan.nextInt();
                trainCarList.add(
                        new ToxicMaterialCar(
                                security,
                                netWeight,
                                grossWeight,
                                typeOfToxicMaterial,
                                classicToxicCapacity
                        )
                );
            }
        }
    }
}
