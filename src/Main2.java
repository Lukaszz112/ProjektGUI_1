import TrainComposition.Exceptions.IsAlreadyPluggedException;
import TrainComposition.Exceptions.TooHeavyToGoException;
import TrainComposition.Exceptions.TooManyCarsException;
import TrainComposition.Exceptions.TooManyElectricCarsException;
import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainCars.Abstract.PassengerCars;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainCars.PassengerCars.PassengerCar;
import TrainComposition.TrainComposition;
import TrainJourney.RouteGraph;
import TrainJourney.TrainStation;

import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        String monitor = "";

        TrainStation trainStation1 = new TrainStation("Krakow");
        TrainStation trainStation2 = new TrainStation("Warszawa");

        List<RouteGraph.Edge> busyEdges = new ArrayList<>();

        Locomotive locomotive1 = new Locomotive(
                "Nazwa",
                trainStation1,
                15,
                2,
                1600
        );

        Locomotive locomotive2 = new Locomotive(
                "Nazwa1",
                trainStation1,
                15,
                2,
                1600
        );

        Locomotive locomotive3 = new Locomotive(
                "Nazwa3",
                trainStation1,
                15,
                2,
                1600
        );

        TrainCar trainCar = new PassengerCar(
                15,
                "juventus",
                1500,
                2700,
                14,
                2
        );
        TrainCar trainCar1 = new PassengerCar(
                15,
                "juventus",
                1500,
                2700,
                14,
                2
        );
        TrainCar trainCar2 = new PassengerCar(
                15,
                "juventus",
                1500,
                2700,
                14,
                2
        );

        RouteGraph graph = new RouteGraph();
        graph.addVertex(trainStation1);
        graph.addVertex(trainStation2);

        graph.addEdge(trainStation1,trainStation2,2);
        graph.addEdge(trainStation2,trainStation1,2);

        locomotive1.setStartingStation(trainStation1);
        locomotive2.setStartingStation(trainStation1);
        locomotive3.setStartingStation(trainStation1);

        locomotive1.setFinalStation(trainStation2);
        locomotive2.setFinalStation(trainStation2);
        locomotive3.setFinalStation(trainStation2);

        locomotive1.setSpeed(150);
        locomotive2.setSpeed(160);
        locomotive3.setSpeed(190);

        TrainComposition trainComposition1 = new TrainComposition(locomotive1,graph,busyEdges,monitor);
        TrainComposition trainComposition2 = new TrainComposition(locomotive2,graph,busyEdges,monitor);
        TrainComposition trainComposition3 = new TrainComposition(locomotive3,graph,busyEdges,monitor);

        try {
            trainComposition1.add(trainCar);
            trainComposition2.add(trainCar1);
            trainComposition3.add(trainCar2);
        } catch (TooManyElectricCarsException | TooHeavyToGoException | TooManyCarsException |
                 IsAlreadyPluggedException e) {
            throw new RuntimeException(e);
        }

        Thread t1 = new Thread(trainComposition1);
        Thread t2 = new Thread(trainComposition2);
        Thread t3 = new Thread(trainComposition3);

        t1.start();
        t2.start();
        t3.start();
    }
}
