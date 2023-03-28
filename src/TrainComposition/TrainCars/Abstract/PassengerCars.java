package TrainComposition.TrainCars.Abstract;

import TrainComposition.Exceptions.TooHeavyToGoException;
import TrainComposition.TrainCars.Exceptions.TooManyPeopleException;

public abstract class PassengerCars extends TrainCar{
    private int numOfSeats;
    private int numOfPeople = 0;
    private double peopleWeight = 0;
    private TrainCarPassengerType typeOfGoods;

    public PassengerCars(
            int numOfSeats,
            String security,
            double netWeight,
            double grossWeight,
            TrainCarPassengerType typeOfGoods
    ) {
        super(
                security,
                netWeight,
                grossWeight
        );
        this.typeOfGoods = typeOfGoods;
        this.numOfSeats = numOfSeats;
    }

    public void addPerson(double weight){
        if(this.numOfPeople + 1 > this.numOfSeats){
            throw new TooManyPeopleException(
                    "There is too many people inside! Try to another one."
            );
        }else if(getNetWeight() + peopleWeight + weight > getGrossWeight()){
            throw new TooHeavyToGoException(
                    "Weight limit reached! Try to another one."
            );
        }
    }

    @Override
    public String toString() {
        return "Passenger car includes: " +
                "\n number of seats: " + numOfSeats +
                "\n number of people: " + numOfPeople +
                "\n people weight: " + peopleWeight +
                "\n type of goods: '" + typeOfGoods;
    }
}
