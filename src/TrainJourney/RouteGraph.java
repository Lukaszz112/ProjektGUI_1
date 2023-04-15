package TrainJourney;

import TrainComposition.TrainComposition;

import java.util.*;

public class RouteGraph {
    private final Map<TrainStation, List<Edge>> journeyGraph = new HashMap<>();

    public List<Edge> getNeighbors(TrainStation vertex) {
        return journeyGraph.get(vertex);
    }
    public void addVertex(TrainStation vertex) {
        journeyGraph.put(vertex, new ArrayList<>());
    }

    public Map<TrainStation, List<Edge>> getJourneyGraph() {
        return journeyGraph;
    }

    //Przy pisaniu findPath wspomagałem się informacjami ze strony: https://favtutor.com/blogs/depth-first-search-java
    public List<TrainStation> findPath(TrainStation startStation, TrainStation finalStation) {
        Stack<TrainStation> stack = new Stack<>();
        Set<TrainStation> visited = new HashSet<>();
        Map<TrainStation, TrainStation> parentMap = new HashMap<>();

        stack.push(startStation);
        visited.add(startStation);

        while (!stack.isEmpty()) {
            TrainStation current = stack.pop();

            if (current.equals(finalStation)) {
                List<TrainStation> path = new ArrayList<>();
                TrainStation node = finalStation;
                while (node != null) {
                    path.add(0, node);
                    node = parentMap.get(node);
                }
                return path;
            }

            for (Edge edge : getNeighbors(current)) {
                TrainStation neighbor = edge.getDestination();
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        return null;
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
            int distance = rand.nextInt(5)+1;
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

    public class Edge {

        //globalna lista do przechowywania stanu edga
        TrainStation destination;
        int distance;

        public TrainStation getDestination() {
            return destination;
        }

        public int getDistance() {
            return distance;
        }

        public Edge(TrainStation destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }

        @Override
        public String toString(){
            return " -> " + destination.toString() + " "  + distance;
        }
    }
}
