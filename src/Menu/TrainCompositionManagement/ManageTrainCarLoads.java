package Menu.TrainCompositionManagement;

import Menu.ChooseManagement;
import Menu.Exception.AlreadyOnJourney;
import Menu.Exception.DoesntExist;
import Menu.Interfaces.CorrectType;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.Exceptions.TooHeavyGoods;
import TrainComposition.TrainCars.Load;
import TrainComposition.TrainComposition;

import java.util.List;
import java.util.Scanner;
public class ManageTrainCarLoads implements CorrectType {
    Scanner scan = new Scanner(System.in);
    ChooseManagement chooseManagement = new ChooseManagement();

    public void add(
                List<TrainComposition> trainCompositionList
    ) throws
            DoesntExist, AlreadyOnJourney {

            TrainComposition chosenTrainComposition = chooseManagement
                    .getCorrectTrainComposition(
                            trainCompositionList
                    );

            if(chosenTrainComposition.getLocomotive().getFinalStation() != null){
                throw new AlreadyOnJourney("Train is already on journey!");
            }

            List<TrainCar> trainCarList = chosenTrainComposition.getTrainCars();

            if(trainCarList.size() < 1){
                throw new DoesntExist("Train set doesn't include any train cars!");
            }

            TrainCar chosenTrainCar = chooseManagement.getCorrectTrainCar(trainCarList);

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
            DoesntExist, AlreadyOnJourney {
        TrainComposition chosenTrainComposition = chooseManagement
                .getCorrectTrainComposition(
                        trainCompositionList
                );

        if(chosenTrainComposition.getLocomotive().getFinalStation() != null){
            throw new AlreadyOnJourney("Train is already on journey!");
        }

        List<TrainCar> trainCarList = chosenTrainComposition.getTrainCars();

        if(trainCarList.size() < 1){
            throw new DoesntExist("Train set doesn't include any train cars!");
        }


        TrainCar chosenTrainCar = chooseManagement.getCorrectTrainCar(trainCarList);

        if(chosenTrainCar.getLoadList().size() < 1){
            throw new DoesntExist("There is no one load!");
        }

        Load chosenLoad = chooseManagement.getCorrectLoad(chosenTrainCar);

        chosenTrainCar.removeLoad(chosenLoad);
        chosenTrainComposition.getLocomotive().setAvailableWeightOfLoads(
                chosenTrainComposition.getLocomotive().getAvailableWeightOfLoads() +
                chosenLoad.getWeight()
        );

        System.out.println("Success!");
}
}
