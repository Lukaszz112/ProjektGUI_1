package Menu;

import Menu.Exception.DoesntExist;
import Menu.Exception.ThereIsNoSuchLocomotiveYet;
import Menu.Exception.ThisLocomotiveIsAlreadyUsed;
import Menu.Exception.TrainStationExist;
import Menu.Interfaces.CorrectType;
import Menu.TrainCompositionManagement.ManageTrainCompositionInit;
import TrainJourney.TrainStation;
import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainComposition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CreateMenuInit implements CorrectType {
    private final List<Locomotive> locomotiveList = new ArrayList<>();
    private final List<TrainComposition> trainCompositionList = new LinkedList<>();
    private final List<TrainCar> trainCarList = new ArrayList<>();
    private final List<TrainStation> trainStationList = new ArrayList<>();
    public void initialize(){
        Scanner scan = new Scanner(System.in);

        new TestObj().create(trainStationList,locomotiveList,trainCarList,trainCompositionList);

        int userSelection;

        do {
            System.out.println("What do you want to do?: ");

            userSelection = getValue(scan, Integer.class);

            switch (userSelection) {
                case 1:
                    locomotiveList.add(new CreateLocomotive().createLocomotive());
                    break;
                case 2:
                    new CreateTrainCarInit().initialize(trainCarList);
                    break;
                case 3:
                    try {
                        new CreateTrainComposition().initialize(trainCompositionList, locomotiveList);
                    }catch (ThisLocomotiveIsAlreadyUsed | ThereIsNoSuchLocomotiveYet | DoesntExist e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try{
                        new CreateTrainStation().create(trainStationList);
                    }catch (TrainStationExist e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try{
                        new ManageTrainCompositionInit().initialize(trainCompositionList, trainCarList);
                    }catch(DoesntExist e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    new ManageTrainCarLoadsInit().initialize(trainCompositionList);
                    break;
                case 7:
                    //Remove train Set from the route
                    break;
                case 8:
                    //Start the train journey
                    break;
                case 9:
                    //Management
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please insert correct option!");
                    break;
            }
        }while(true);
    }
}
