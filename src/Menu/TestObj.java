package Menu;

import Menu.CreateTrainCars.CreateTrainCar;
import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainJourney.RouteGraph;
import TrainJourney.TrainStation;

import java.util.List;

public class TestObj {
    public void create(
            List<TrainStation> trainStationList,
            List<Locomotive> locomotiveList,
            List<TrainCar> trainCarList
    ){
        new CreateTrainStation().createTestStation(trainStationList);
        new CreateLocomotive().createTestLocomotives(locomotiveList);
        new CreateTrainCar().createTestTrainCars(trainCarList);

        RouteGraph graph = new RouteGraph();
        graph.automaticAddStation(trainStationList);
        graph.automaticAddEdge(trainStationList);
    }
}
