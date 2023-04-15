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
            List<TrainComposition> trainCompositionList,
            RouteGraph graph,
            List<RouteGraph.Edge> busyEdgeList,
            String monitor
    ){
        new CreateTrainStation().createTestStation(trainStationList);
        new CreateLocomotive().createTestLocomotives(locomotiveList,trainStationList);
        new CreateTrainCar().createTestTrainCars(trainCarList);

        try{
            new CreateTrainComposition().createTestTrainComposition(
                    locomotiveList,
                    trainCarList,
                    trainCompositionList,
                    graph,
                    busyEdgeList,
                    monitor
            );
        }catch (
                TooManyElectricCarsException |
                TooHeavyToGoException |
                TooManyCarsException |
                IsAlreadyPluggedException e
        ){
            System.out.println(e.getMessage());
        }

        // Wypisanie grafu
//        List<TrainStation> path = graph.findPath(trainStationList.get(5), trainStationList.get(82));
//        System.out.println(trainStationList.get(5) + "  " + trainStationList.get(82));
//
//        for (TrainStation station : graph.getJourneyGraph().keySet()) {
//            List<RouteGraph.Edge> neighbors = graph.getNeighbors(station);
//            System.out.print(station + ": ");
//            for (RouteGraph.Edge neighbor : neighbors) {
//                System.out.print(neighbor.toString() + " ");
//            }
//            System.out.println();
//        }
//
//        path.forEach(x -> System.out.print(x + " "));
//        System.out.println(path.get(0));
//
//
//        for (int i = 0; i < path.size()-1; i++) {
//            List<RouteGraph.Edge> a = graph.getNeighbors(path.get(i));
//            int finali = i;
//            a.stream().filter(x -> x.getDestination() == path.get(finali+1))
//                    .forEach(System.out::println);
//        }
//
//        System.out.println(path.get(path.size()-1));



    }
}
