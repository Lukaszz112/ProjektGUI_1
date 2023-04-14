package Menu;

import Menu.Exception.DoesntExist;
import Menu.Exception.ThereIsNoSuchLocomotiveYet;
import Menu.Exception.ThisLocomotiveIsAlreadyUsed;
import Menu.Exception.TrainStationExist;
import Menu.Interfaces.CorrectType;
import Menu.TrainCompositionManagement.ManageTrainCarLoadsInit;
import Menu.TrainCompositionManagement.ManageTrainCompositionInit;
import TrainJourney.RouteGraph;
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
    private final List<Thread> threadList = new ArrayList<>();
    private final List<TrainComposition> runningTrainCompositionList = new ArrayList<>();
    private final List<TrainComposition> trainCompositionList = new ArrayList<>();
    private final List<TrainCar> trainCarList = new ArrayList<>();
    private final List<TrainStation> trainStationList = new ArrayList<>();
    private final RouteGraph graph = new RouteGraph();

    public void initialize(){
        Scanner scan = new Scanner(System.in);

        new TestObj().create(trainStationList,locomotiveList,trainCarList,trainCompositionList,graph);
        graph.automaticAddStation(trainStationList);
        graph.automaticAddEdge(trainStationList);

        int userSelection;

        do {
            System.out.println("What do you want to do?: ");
            userSelection = getValue(scan, Integer.class);

            switch (userSelection) {
                case 1:
                    locomotiveList.add(new CreateLocomotive().createLocomotive(trainStationList));
                    break;
                case 2:
                    new CreateTrainCarInit().initialize(trainCarList);
                    break;
                case 3:
                    try {
                        new CreateTrainComposition().initialize(
                                trainCompositionList,
                                locomotiveList,
                                graph
                        );
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
                    new ManageTrainJourneyInit().initialize(
                            trainCompositionList,
                            runningTrainCompositionList,
                            threadList,
                            trainStationList
                    );
                    break;
                case 8:
                    break;
                case 9:
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
