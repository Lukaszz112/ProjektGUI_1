package Menu;

import Menu.CreateTrainCars.CreateTrainCar;
import TrainComposition.Exceptions.IsAlreadyPluggedException;
import TrainComposition.Exceptions.TooHeavyToGoException;
import TrainComposition.Exceptions.TooManyCarsException;
import TrainComposition.Exceptions.TooManyElectricCarsException;
import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainComposition;
import TrainJourney.RouteGraph;
import TrainJourney.TrainStation;

import java.util.List;

public class TestObj {
    public void create(
            List<TrainStation> trainStationList,
            List<Locomotive> locomotiveList,
            List<TrainCar> trainCarList,
            List<TrainComposition> trainCompositionList
    ){
        new CreateTrainStation().createTestStation(trainStationList);
        new CreateLocomotive().createTestLocomotives(locomotiveList);
        new CreateTrainCar().createTestTrainCars(trainCarList);

        try{
            new CreateTrainComposition().createTestTrainComposition(
                    locomotiveList,
                    trainCarList,
                    trainCompositionList
            );
        }catch (
                TooManyElectricCarsException |
                TooHeavyToGoException |
                TooManyCarsException |
                IsAlreadyPluggedException e
        ){
            System.out.println(e.getMessage());
        }


        RouteGraph graph = new RouteGraph();
        graph.automaticAddStation(trainStationList);
        graph.automaticAddEdge(trainStationList);
    }
}
