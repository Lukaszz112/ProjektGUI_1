package TrainComposition.Locomotive;

import TrainJourney.StationData;
import TrainJourney.TrainStation;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Locomotive{
    private final int numOfTrainCars;
    private final int numOfElectricTrainCars;
    private final double torsion;
    private double availableWeightOfLoads;
    private final String name;
    private final TrainStation homeStation;
    private TrainStation startingStation;
    private TrainStation finalStation;
    private double speed;

    private double journeyPercent = 0;
    private double toStationPercent = 0;

    public void setJourneyPercent(double journeyPercent) {
        this.journeyPercent = journeyPercent;
    }

    public void setToStationPercent(double toStationPercent) {
        this.toStationPercent = toStationPercent;
    }

    private final int uid;
    private static final AtomicInteger count = new AtomicInteger(0);

    public void setStartingStation(TrainStation startingStation) {
        this.startingStation = startingStation;
    }

    public void setFinalStation(TrainStation finalStation) {
        this.finalStation = finalStation;
    }
    public double getJourneyPercent() {
        return journeyPercent;
    }

    public double getToStationPercent() {
        return toStationPercent;
    }

    public TrainStation getStartingStation() {
        return startingStation;
    }

    public TrainStation getFinalStation() {
        return finalStation;
    }

    public int getUid() {
        return uid;
    }
    public int getNumOfTrainCars() {
        return numOfTrainCars;
    }

    public int getNumOfElectricTrainCars() {
        return numOfElectricTrainCars;
    }

    public double getTorsion() {
        return torsion;
    }
    public double getAvailableWeightOfLoads() {
        return availableWeightOfLoads;
    }
    public void setAvailableWeightOfLoads(double availableWeightOfLoads) {
        this.availableWeightOfLoads = availableWeightOfLoads;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Locomotive(
            String name,
            TrainStation homeStation,
            int numOfTrainCars,
            int numOfElectricTrainCars,
            double torsion
    ) {
        Random rand = new Random();
        this.name = name;
        this.homeStation = homeStation;
        this.numOfElectricTrainCars = numOfElectricTrainCars;
        this.numOfTrainCars = numOfTrainCars;
        this.torsion = torsion;
        this.availableWeightOfLoads = torsion;
        this.uid = count.incrementAndGet();
        this.speed = rand.nextDouble(90)+60;
    }



    @Override
    public String toString() {
        return  "Uid: " + uid +
                ", maximum quantity of train cars: " + numOfTrainCars +
                ", maximum quantity of electric train cars: " + numOfElectricTrainCars +
                ", maximum weight of train cars: " + torsion +
                ", name: " + name +
                ", home station: " + homeStation +
                ", start station: " + startingStation +
                ", final station: " + finalStation;
    }


}
