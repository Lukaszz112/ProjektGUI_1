package TrainComposition;

import Menu.Interfaces.CorrectType;
import TrainComposition.Exceptions.*;
import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainCars.Interfaces.ElectricCars;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainJourney.RouteGraph;
import TrainJourney.TrainStation;
import com.sun.source.doctree.StartElementTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TrainComposition implements Runnable, CorrectType {

    private static final AtomicInteger count = new AtomicInteger(0);
    private final int uid = count.incrementAndGet();

    public int getUid() {
        return uid;
    }
    private final Locomotive locomotive;
    private RouteGraph graph;

    public Locomotive getLocomotive() {
        return locomotive;
    }
    private final List<TrainCar> trainCars = new ArrayList<>();

    public List<TrainCar> getTrainCars() {
        return trainCars;
    }

    private int sumOfElectricTrainCars = 0;

    Random rand = new Random();
    public TrainComposition(
            Locomotive locomotive,
            RouteGraph graph
    ) {
        this.locomotive = locomotive;
        this.graph = graph;
    }

    public void add(TrainCar trainCar) throws
            TooManyElectricCarsException,
            TooHeavyToGoException,
            TooManyCarsException,
            IsAlreadyPluggedException

    {

        int sumOfTrainCarsWeight = trainCars.stream()
                .map(x -> (int) x.getNetWeight())
                .reduce(0,Integer::sum);

        if(trainCar instanceof ElectricCars){
            if(sumOfElectricTrainCars + 1 > locomotive.getNumOfElectricTrainCars())
                throw new TooManyElectricCarsException(
                        "There is too many electric cars in train composition, please remove one to plug another!"
                );
        }

        if(sumOfTrainCarsWeight + trainCar.getNetWeight() > this.locomotive.getTorsion()){
            throw new TooHeavyToGoException(
                    "The car is too heavy to plug! Try another one or change the locomotive!"
            );
        }
        if(trainCars.size() + 1 > this.locomotive.getNumOfTrainCars()){
            throw new TooManyCarsException(
                    "There is too many cars in train composition, please remove one to plug another!"
            );
        }

            trainCars.add(trainCar);
            locomotive.setAvailableWeightOfLoads(locomotive.getAvailableWeightOfLoads() - trainCar.getNetWeight());
            sumOfElectricTrainCars += trainCar instanceof ElectricCars ? 1 : 0;
    }

    public void remove(TrainCar trainCar) throws IsNotAlreadyPluggedException {
        trainCars.remove(trainCar);
    }

    @Override
    public String toString() {
        return uid + ". Train Composition includes: " +
                "Locomotive: " + locomotive +
                ", Train Cars (uid): " +
                (
                                trainCars.stream()
                                            .map(i -> String.valueOf(i.getUid()))
                                            .collect(Collectors.joining(", "))
                );
    }

    @Override
    public void run() {
        while(true){
            TrainStation startStation = getLocomotive().getStartingStation();
            TrainStation finalStation = getLocomotive().getFinalStation();
            Locomotive locomotive = getLocomotive();

            assert startStation != null;
            assert finalStation != null;
            assert locomotive != null;

            List<TrainStation> path = graph.findPath(startStation,finalStation);

            for (int i = 0; i < path.size()-1; i++) {
                List<RouteGraph.Edge> a = graph.getNeighbors(path.get(i));

                int finalI = i;

                RouteGraph.Edge tempEdge = a.stream()
                        .filter(x -> x.getDestination() == path.get(finalI+1))
                        .findFirst()
                        .orElse(null);

                int distance = tempEdge.getDistance() * 1000; //change type km -> m
                int routeTraveled = 0;

                path.forEach(x -> System.out.println(x + " "));
                do{
                    locomotive.setSpeed(
                            rand.nextBoolean() ?
                                    locomotive.getSpeed() * 1.03 :
                                    locomotive.getSpeed() * 0.97
                    );

                    distance -= routeTraveled;
                    routeTraveled += distance / (locomotive.getSpeed()/3.6);
                    //predkosc przyspieszona na potrzeby symulacji

                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

//                   zamiast do while
//                    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
//                    scheduler.scheduleAtFixedRate(() -> {
//                        // ...
//                    }, 0, 1, TimeUnit.SECONDS);

                }while(distance >= 0);

                System.out.println("You have arrived to station: " + path.get(i+1));
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            System.out.println("You have arrived to final station: " + path.get(path.size()-1));

            try{
                Thread.sleep(30000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            locomotive.setStartingStation(finalStation);
            locomotive.setFinalStation(startStation);
        }


        //while true
        //sprawdzenie czy trasa jest zajeta, globalna lista krawedzi
        //zapis i odczyt z listy / sekcja krytyczna

    }
}
