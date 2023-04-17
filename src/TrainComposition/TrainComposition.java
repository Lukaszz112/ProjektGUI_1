package TrainComposition;

import Menu.Interfaces.CorrectType;
import TrainComposition.Exceptions.*;
import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainCars.Interfaces.ElectricCars;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainJourney.RouteGraph;
import TrainJourney.TrainStation;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainComposition implements Runnable, CorrectType {

    private static final AtomicInteger count = new AtomicInteger(0);
    private final int uid = count.incrementAndGet();

    public int getUid() {
        return uid;
    }
    private final Locomotive locomotive;
    private final RouteGraph graph;

    public Locomotive getLocomotive() {
        return locomotive;
    }
    private final List<TrainCar> trainCars = new ArrayList<>();

    public List<TrainCar> getTrainCars() {
        return trainCars;
    }

    private final List<RouteGraph.Edge> busyEdgeList;
    private final List<TrainComposition> runningTrainCompositionList;
    private final String monitor;
    private boolean readyToGo = true;
    public void setReadyToGo(boolean readyToGo) {
        this.readyToGo = readyToGo;
    }

    private boolean isEndingJourney = false;

    public void setEndingJourney(boolean endingJourney) {
        isEndingJourney = endingJourney;
    }

    private int sumOfElectricTrainCars = 0;

    Random rand = new Random();
    public TrainComposition(
            Locomotive locomotive,
            RouteGraph graph,
            List<RouteGraph.Edge> busyEdgeList,
            String monitor,
            List<TrainComposition> runningTrainCompositionList
    ) {
        this.locomotive = locomotive;
        this.graph = graph;
        this.busyEdgeList = busyEdgeList;
        this.monitor = monitor;
        this.runningTrainCompositionList = runningTrainCompositionList;
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
        return "Uid: " + uid +
                ". TrainComposition: " +
                ", locomotive: " + locomotive +
                ", train cars: " + trainCars.stream()
                                        .sorted(Comparator.comparing(x -> x.getWeightOfAllStuff() + x.getNetWeight()))
                                        .mapToInt(TrainCar::getUid)
                                        .mapToObj(String::valueOf)
                                        .collect(Collectors.joining(", ")) +

                ", sumOfElectricTrainCars=" + sumOfElectricTrainCars;
    }



    @Override
    public void run() {
        while(true){
            TrainStation startStation = getLocomotive().getStartingStation();
            TrainStation finalStation = getLocomotive().getFinalStation();
            Locomotive locomotive = getLocomotive();

            List<TrainStation> path = graph.findPath(startStation,finalStation);

            double routeDistance = IntStream.range(0, path.size() - 1)
                    .mapToObj(i -> graph.getNeighbors(path.get(i))
                            .stream()
                            .filter(x -> x.getDestination() == path.get(i + 1))
                            .findFirst()
                            .orElse(null))
                    .filter(Objects::nonNull)
                    .mapToInt(RouteGraph.Edge::getDistance)
                    .sum();

            double routeTraveled = 0;

            for (int i = 0; i < path.size()-1; i++) {
                List<RouteGraph.Edge> neighbors = graph.getNeighbors(path.get(i));

                int finalI = i;

                RouteGraph.Edge nextStation = neighbors.stream()
                        .filter(x -> x.getDestination() == path.get(finalI+1))
                        .findFirst()
                        .orElse(null);

                assert nextStation != null;
                List<RouteGraph.Edge> reverseNeighbors = graph.getNeighbors(nextStation.getDestination());
                RouteGraph.Edge reverseEdge = reverseNeighbors.stream()
                        .filter(x -> x.getDestination() == path.get(finalI))
                        .findFirst()
                        .orElse(null);

                synchronized (monitor) {
                    while (true) {
                        RouteGraph.Edge usingEdge = busyEdgeList.stream()
                                .filter(x -> x == reverseEdge || x == nextStation)
                                .findAny()
                                .orElse(null);

                        if (usingEdge == null) {
                            break;
                        }

                        try {
                            System.out.println(
                                    "Train: " + this.getUid() +
                                    " is waiting in queue at station: " + path.get(i).getName()
                            );

                            path.get(i).getQueue().add(this);
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    readyToGo = true;
                    busyEdgeList.add(reverseEdge);
                    busyEdgeList.add(nextStation);
                }

                double distance = nextStation.getDistance();
                double distanceToNextStation = nextStation.getDistance() * 1000; //change type km -> m
                double traveledToStation = 0;

                do{
                    locomotive.setSpeed(
                            rand.nextBoolean() ?
                                    locomotive.getSpeed() * 1.03 :
                                    locomotive.getSpeed() * 0.97
                    );

                    if(locomotive.getSpeed() > 200){
                        try {
                            throw new RailroadHazard(this.toString());
                        } catch (RailroadHazard e) {
                            System.out.println("The train has exceeded the speed limit: \n" + e);
                        }
                        locomotive.setSpeed(150);
                    }

                    double traveled = locomotive.getSpeed()/3.6 * 3;
                    distanceToNextStation -= traveled;

                    double traveledToStation1 = distanceToNextStation < 0 ?
                            traveled + distanceToNextStation :
                            traveled;

                    traveledToStation += traveledToStation1;

                    routeTraveled += traveledToStation1;

                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    locomotive.setJourneyPercent(((routeTraveled/1000)/routeDistance)*100);
                    locomotive.setToStationPercent(((traveledToStation/1000)/distance)*100);

//                    Wyswietlanie drogi (przyklad prewencji kolizji)
//                    System.out.println(uid + ": " + (int)locomotive.getToStationPercent() + "% " + nextStation);

                }while(distanceToNextStation > 0);

                synchronized (monitor) {
                    busyEdgeList.remove(reverseEdge);
                    busyEdgeList.remove(nextStation);

                    if (path.get(i).getQueue().size() > 0) {
                        TrainComposition nextTrain = path.get(i).getQueue().get(0);

                        nextTrain.setReadyToGo(true);
                        path.get(i).getQueue().remove(0);

                        monitor.notifyAll();
                    }

                    if(isEndingJourney){
                        this.getLocomotive().setStartingStation(null);
                        this.getLocomotive().setFinalStation(null);

                        Thread.currentThread().interrupt();
                        runningTrainCompositionList.remove(this);

                        System.out.println("Train : " + this.getUid() + " ended its run!");
                        break;
                    }
                }

                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            if(isEndingJourney) break;

            try{
                Thread.sleep(30000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            locomotive.setStartingStation(finalStation);
            locomotive.setFinalStation(startStation);
        }
    }
}
