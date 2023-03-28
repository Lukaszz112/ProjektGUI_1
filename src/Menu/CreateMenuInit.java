package Menu;

import Menu.Exception.ThereIsNoSuchLocomotiveYet;
import Menu.Exception.ThisLocomotiveIsAlreadyUsed;
import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainComposition;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateMenuInit {
    private List<Locomotive> locomotiveList = new ArrayList<>();
    private List<TrainComposition> trainCompositionList = new ArrayList<>();
    private List<TrainCar> trainCarList = new ArrayList<>();

    public void initialize(){
        Scanner scan = new Scanner(System.in);

        int userSelection;
        do {
            System.out.println("What do you want to do?: ");
            userSelection = scan.nextInt();

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
                    }catch (ThisLocomotiveIsAlreadyUsed | ThereIsNoSuchLocomotiveYet e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    //Create train station
                    break;
                case 5:
                    // Manage train car goods
                    break;
                case 6:
                    trainCompositionList.stream().map(TrainComposition::toString).forEach(System.out::println);
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

            }
        }while(userSelection > 0 && userSelection < 10);
    }
}
