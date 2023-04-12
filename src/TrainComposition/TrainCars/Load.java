package TrainComposition.TrainCars;

public class Load {
    private final String name;
    private final double weight;

    public double getWeight() {
        return weight;
    }

    public Load(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return  "Load: " + name +
                ", weight: " + weight;
    }
}
