package Menu;

import Menu.Exception.DoesntExist;
import Menu.Interfaces.CorrectType;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainCars.Exceptions.TooHeavyGoods;
import TrainComposition.TrainCars.Load;
import TrainComposition.TrainComposition;

import java.util.List;
import java.util.Scanner;
public class ManageTrainCarLoads implements CorrectType {
    Scanner scan = new Scanner(System.in);

    public void add(
                List< TrainComposition > trainCompositionList
    ) throws
        DoesntExist
        {

            System.out.println("====================================");

            trainCompositionList.stream()
                    .map(TrainComposition::toString)
                    .forEach(System.out::println);

            System.out.println("====================================");
            System.out.println("Choose train set (index): ");

            int userSelection1;

            do{
                userSelection1 = getValue(scan, Integer.class);
            }while(userSelection1 < 1 || userSelection1 > trainCompositionList.size());

            int finalUserSelection1 = userSelection1;

            TrainComposition chosenTrainComposition = trainCompositionList.stream()
                    .filter(x -> x.getUid() == finalUserSelection1)
                    .findFirst()
                    .orElse(null);

            assert chosenTrainComposition != null;
            List<TrainCar> trainCarList = chosenTrainComposition.getTrainCars();

            if(trainCarList.size() < 1){
                throw new DoesntExist("Train set doesn't include any train cars!");
            }

            System.out.println("====================================");

            trainCarList.stream()
                    .mapToInt(trainCarList::indexOf)
                    .mapToObj(i -> String.format("%d: %s", i + 1, trainCarList.get(i).toString()))
                    .forEach(System.out::println);

            System.out.println("====================================");
            System.out.println("Select train car (index): ");

            int userSelection2;

            do{
                userSelection2 = getValue(scan, Integer.class);
            }while(userSelection2 < 1 || userSelection2 > trainCarList.size());

            int finalUserSelection2 = userSelection2;

            TrainCar chosenTrainCar = trainCarList.get(finalUserSelection2-1);

            System.out.println("Insert name: ");
            String nameOfGoods = scan.next();

            System.out.println("Insert weight: ");
            double weightOfGoods = scan.nextDouble();
            try{
                chosenTrainCar.addLoad(
                        chosenTrainComposition.getLocomotive(),
                        nameOfGoods,
                        weightOfGoods
                );
                System.out.println("Success!");
            }catch (TooHeavyGoods e) {
                System.out.println(e.getMessage());
            }
    }

    public void remove(
            List< TrainComposition > trainCompositionList
    ) throws
            DoesntExist
    {

        System.out.println("====================================");

        trainCompositionList.stream()
                .map(TrainComposition::toString)
                .forEach(System.out::println);

        System.out.println("====================================");
        System.out.println("Choose train set (index): ");

        int userSelection1;

        do{
            userSelection1 = getValue(scan, Integer.class);
        }while(userSelection1 < 1 || userSelection1 > trainCompositionList.size());

        int finalUserSelection1 = userSelection1;

        TrainComposition chosenTrainComposition = trainCompositionList.stream()
                .filter(x -> x.getUid() == finalUserSelection1)
                .findFirst()
                .orElse(null);

        assert chosenTrainComposition != null;
        List<TrainCar> trainCarList = chosenTrainComposition.getTrainCars();

        if(trainCarList.size() < 1){
            throw new DoesntExist("Train set doesn't include any train cars!");
        }

        System.out.println("====================================");

        trainCarList.stream()
                .mapToInt(trainCarList::indexOf)
                .mapToObj(i -> String.format("%d: %s", i + 1, trainCarList.get(i).toString()))
                .forEach(System.out::println);

        System.out.println("====================================");
        System.out.println("Select train car (index): ");

        int userSelection2;

        do{
            userSelection2 = getValue(scan, Integer.class);
        }while(userSelection2 < 1 || userSelection2 > trainCarList.size());

        int finalUserSelection2 = userSelection2;

        TrainCar chosenTrainCar = trainCarList.get(finalUserSelection2-1);

        if(chosenTrainCar.getLoadList().size() < 1){
            throw new DoesntExist("There is no one load!");
        }

        System.out.println("===============================");
        chosenTrainCar.getLoadList().stream()
                        .mapToInt(i -> chosenTrainCar.getLoadList().indexOf(i))
                                .mapToObj(
                                        i -> String.format(
                                                "%d: %s",
                                                i + 1,
                                                chosenTrainCar.getLoadList().get(i).toString()
                                        ))
                                        .forEach(System.out::println);
        System.out.println("===============================");
        System.out.println("Which one do you want to remove?: ");

        int userSelection3;

        do{
            userSelection3 = getValue(scan, Integer.class);
        }while(userSelection3 < 1 || userSelection3 > chosenTrainCar.getLoadList().size());

        int finalUserSelection3 = userSelection3;

        Load chosenLoad = chosenTrainCar.getLoadList().get(finalUserSelection3-1);

        chosenTrainCar.removeLoad(chosenLoad);
        chosenTrainComposition.getLocomotive().setAvailableWeightOfLoads(
                chosenTrainComposition.getLocomotive().getAvailableWeightOfLoads() +
                chosenLoad.getWeight()
        );

        System.out.println("Success!");
}
}
