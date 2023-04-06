package TrainJourney;

import java.util.*;

public class RouteGraph {
    private Map<TrainStation, List<Edge>> journeyGraph = new HashMap<>();

    public Map<TrainStation, List<Edge>> getJourneyGraph() {
        return journeyGraph;
    }

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
                            stationData.get(index+1),
                            distance
                    );
                }
                this.addEdge(
                        trainStation,
                        stationData.get(index) == trainStation ? stationData.get(index+2) : stationData.get(index),
                        distance
                );
            }
        }
    }

    private static class Edge {
        TrainStation destination;
        int distance;

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
