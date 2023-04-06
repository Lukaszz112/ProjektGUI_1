package Menu.CreateTrainCars;

import Menu.AppConfig;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainCars.Abstract.TrainCarTypes;
import TrainComposition.TrainCars.FreightCars.Basic.*;
import TrainComposition.TrainCars.FreightCars.Heavy.ExplosivesCar;
import TrainComposition.TrainCars.FreightCars.Heavy.LiquidToxicMaterialCar;
import TrainComposition.TrainCars.FreightCars.Heavy.ToxicMaterialCar;
import TrainComposition.TrainCars.PassengerCars.PassengerCar;
import TrainComposition.TrainCars.PassengerCars.RestaurantCar;

import java.util.List;
import java.util.Random;

public class CreateTrainCar {
    public void create(List<TrainCar> trainCarList, int userChoice){
        switch (userChoice) {
            case 1 -> new CreateFreightCar().create(trainCarList);
            case 2 -> new CreatePassengerCar().create(trainCarList);
        }
        System.out.println("Train car created!");
    }

    public void createTestTrainCars(List<TrainCar> trainCarList){
        Random rand = new Random();
        TrainCarTypes[] trainCarTypes = TrainCarTypes.values();

        for (int i = 1; i < AppConfig.quantityOfTrainCars; i++) {

            String trainCarType = trainCarTypes[rand.nextInt(trainCarTypes.length)].toString();

            switch (trainCarType){
                case "PASSENGER_CAR" -> trainCarList.add(new PassengerCar(
                        rand.nextInt(100)+50,
                        "SecurityCompany",
                        rand.nextInt(1000)+400,
                        rand.nextInt(2000)+1400,
                        rand.nextInt(10)+1,
                        rand.nextInt(10)+1
                ));
                case "RESTAURANT_CAR" -> trainCarList.add(new RestaurantCar(
                        rand.nextInt(100)+50,
                        "SecurityCompany",
                        rand.nextInt(1000)+400,
                        rand.nextInt(2000)+1400,
                        rand.nextInt(10)+1,
                        "ResponsibleCompany"
                ));
                case "BAGGAGE_MAIL_CAR" -> trainCarList.add(new BaggageMailCar(
                        "SecurityCompany",
                        "Sender" + rand.nextInt(10)+1,
                        rand.nextInt(1000)+400,
                        rand.nextInt(2000)+1400,
                        rand.nextInt(1000)+1,
                        rand.nextInt(10000)+1
                ));
                case "GASEOUS_MATERIALS_CAR" -> trainCarList.add(new GaseousMaterialsCar(
                        "SecurityCompany",
                        "Sender" + rand.nextInt(10)+1,
                        rand.nextInt(1000)+400,
                        rand.nextInt(2000)+1400,
                        "ToxicGas",
                        rand.nextInt(21)+1
                ));
                case "LIQUID_GOODS_CAR" -> trainCarList.add(new LiquidGoodsCar(
                        "SecurityCompany",
                        "Sender" + rand.nextInt(10)+1,
                        rand.nextInt(1000)+400,
                        rand.nextInt(2000)+1400,
                        "Milk and chocolate",
                        rand.nextInt(10000)+1
                ));
                case "MAIL_CAR" -> trainCarList.add(new MailCar(
                        "SecurityCompany",
                        "Sender" + rand.nextInt(10)+1,
                        rand.nextInt(1000)+400,
                        rand.nextInt(2000)+1400,
                        rand.nextInt(1000)+1,
                        "ResponsibleCompany"
                ));
                case "REFRIGERATED_CAR" -> trainCarList.add(new RefrigeratedCar(
                        "SecurityCompany",
                        "Sender" + rand.nextInt(10)+1,
                        rand.nextInt(1000)+400,
                        rand.nextInt(2000)+1400,
                        -rand.nextInt(40)+1,
                        rand.nextInt(10)+1
                ));
                case "EXPLOSIVES_CAR" -> trainCarList.add(new ExplosivesCar(
                        "SecurityCompany",
                        "Sender" + rand.nextInt(10)+1,
                        rand.nextInt(1000)+400,
                        rand.nextInt(2000)+1400,
                        "TNT",
                        rand.nextInt(100)+1
                ));
                case "LIQUID_TOXIC_MATERIALS_CAR" -> trainCarList.add(new LiquidToxicMaterialCar(
                        "SecurityCompany",
                        "Sender" + rand.nextInt(10)+1,
                        rand.nextInt(1000)+400,
                        rand.nextInt(2000)+1400,
                        "ToxicWater",
                        rand.nextInt(10000)+1
                ));
                case "TOXIC_MATERIALS_CAR" -> trainCarList.add(new ToxicMaterialCar(
                        "SecurityCompany",
                        "Sender" + rand.nextInt(10)+1,
                        rand.nextInt(1000)+400,
                        rand.nextInt(2000)+1400,
                        "ToxicGoo",
                        rand.nextInt(10000)+1
                ));
            }
        }
    }
}
