package Menu;

import Menu.Exception.DoesntExist;
import Menu.Exception.ThereIsNoSuchLocomotiveYet;
import Menu.Exception.ThisLocomotiveIsAlreadyUsed;
import Menu.Interfaces.CorrectType;
import TrainComposition.Exceptions.IsAlreadyPluggedException;
import TrainComposition.Exceptions.TooHeavyToGoException;
import TrainComposition.Exceptions.TooManyCarsException;
import TrainComposition.Exceptions.TooManyElectricCarsException;
import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainComposition;
import TrainJourney.RouteGraph;

import java.util.List;
import java.util.Random;

public class CreateTrainComposition implements CorrectType {
    public void initialize(
            List<TrainComposition> trainCompositionList,
            List<Locomotive> locomotiveList,
            RouteGraph graph,
            List<RouteGraph.Edge> busyEdgeList,
            String monitor
    ) throws
            ThereIsNoSuchLocomotiveYet,
            ThisLocomotiveIsAlreadyUsed,
            DoesntExist
    {
        Locomotive locomotiveToAdd = new ChooseManagement().getCorrectLocomotive(locomotiveList);

        Locomotive locomotiveExist = trainCompositionList.stream()
                .map(TrainComposition::getLocomotive)
                .filter(i -> i == locomotiveToAdd)
                .findFirst()
                .orElse(null);

        if(locomotiveToAdd == null){
            throw new ThereIsNoSuchLocomotiveYet(
                    "There is no such locomotive here! Try to create one, or choose another"
            );
        }

        if(locomotiveExist != null){
            throw new ThisLocomotiveIsAlreadyUsed(
                    "This locomotive is already used! Try another"
            );
        }

        trainCompositionList.add(new TrainComposition(locomotiveToAdd,graph,busyEdgeList,monitor));
        System.out.println("Train composition created!");
    }

    public void createTestTrainComposition(
            List<Locomotive> locomotiveList,
            List<TrainCar> trainCarList,
            List<TrainComposition> trainCompositionList,
            RouteGraph graph,
            List<RouteGraph.Edge> busyEdgeList,
            String monitor
    ) throws
            TooManyElectricCarsException,
            TooHeavyToGoException,
            TooManyCarsException,
            IsAlreadyPluggedException
    {
        Random rand = new Random();

        for (int i = 0; i < AppConfig.quantityOfTrainSets; i++) {

            int index;
            Locomotive locomotive;
            boolean isLocomotiveAlreadyUsed;

            do {
                index = rand.nextInt(AppConfig.quantityOfLocomotives-1);
                locomotive = locomotiveList.get(index);

                isLocomotiveAlreadyUsed = false;
                for (TrainComposition trainComposition : trainCompositionList) {
                    if (trainComposition.getLocomotive() == locomotive) {
                        isLocomotiveAlreadyUsed = true;
                        break;
                    }
                }
            } while (isLocomotiveAlreadyUsed);

            TrainComposition trainComposition = new TrainComposition(locomotive,graph,busyEdgeList, monitor);
            trainCompositionList.add(trainComposition);

            for (int j = 0; j < rand.nextInt(5)+5; j++) {
                TrainCar trainCar = trainCarList.get(rand.nextInt(AppConfig.quantityOfTrainCars-1));
                trainComposition.add(trainCar);
            }
        }

    }
}
