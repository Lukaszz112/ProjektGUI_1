package Menu.TrainCompositionManagement;

import Menu.Exception.AlreadyOnJourney;
import Menu.Interfaces.CorrectType;
import TrainComposition.TrainComposition;
import TrainJourney.TrainStation;

import java.util.List;
import java.util.Scanner;

public class ManageTrainJourneyInit implements CorrectType {
        public void initialize(
                List<TrainComposition> trainCompositionList,
                List<TrainComposition> runningTrainCompositionList,
                List<Thread> threadList,
                List<TrainStation> trainStationList
        ){
        Scanner scan = new Scanner(System.in);

        System.out.println("=============================");
        System.out.println("= 1. Start journey          =");
        System.out.println("= 2. Stop journey           =");
        System.out.println("=============================");

        int userChoice;

        do{
            userChoice = getValue(scan, Integer.class);
            if(userChoice > 2 || userChoice < 1){
                System.out.println("Please type correct number!");
            }
        }while(userChoice > 2 || userChoice < 1);

            switch (userChoice){
                case  1 -> {
                    try{
                        new ManageTrainJourney().start(
                                trainCompositionList,
                                runningTrainCompositionList,
                                threadList,
                                trainStationList
                        );
                    }catch (AlreadyOnJourney e){
                        System.out.println(e.getMessage());
                    }
                }
                case 2 ->{
                    new ManageTrainJourney().stop(trainCompositionList);
                }
            }
    }
}
