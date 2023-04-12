package TrainJourney;

import java.util.*;

public class RouteGraph {
    private final Map<TrainStation, List<Edge>> journeyGraph = new HashMap<>();

    public List<Edge> getNeighbors(TrainStation vertex) {
        return journeyGraph.get(vertex);
    }
    public void addVertex(TrainStation vertex) {
        journeyGraph.put(vertex, new ArrayList<>());
    }

    public void addEdge(TrainStation stationA, TrainStation stationB, int distance) {
        List<Edge> startStationNeighbors = journeyGraph.get(stationA);
        List<Edge> destinationStationNeighbors = journeyGraph.get(stationB);
        startStationNeighbors.add(new Edge(stationB, distance));
        destinationStationNeighbors.add(new Edge(stationA, distance));
    }

    public void automaticAddStation(List<TrainStation> stationData){
        for (TrainStation station :
                stationData) {
            this.addVertex(station);
        }
    }
    
    public void automaticAddEdge(List<TrainStation> stationData){
        Random rand = new Random();

        for (TrainStation trainStation :
                stationData) {
            int index = rand.nextInt(100);
            int distance = rand.nextInt(200)+1;
            for (int i = 0; i < 2; i++) {
                if(i==0){
                    this.addEdge(
                            trainStation,
                            stationData.get(
                                    stationData.indexOf(trainStation)+1 < 100 ?
                                            stationData.indexOf(trainStation)+1 :
                                            0
                            ),
                            distance
                    );
                    this.addEdge(
                            trainStation,
                            this.getNeighbors(trainStation)
                                    .stream()
                                    .map(Edge::getDestination)
                                    .filter( x -> x == trainStation)
                                    .findFirst()
                                    .orElse(null) == null ?
                            stationData.get(index) :
                            null,
                            distance
                    );
                }
            }
        }
    }

    private static class Edge {
        TrainStation destination;
        int distance;

        public TrainStation getDestination() {
            return destination;
        }

        public Edge(TrainStation destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }

        @Override
        public String toString(){
            return destination.toString() + " -> " + distance;
        }
    }
}
