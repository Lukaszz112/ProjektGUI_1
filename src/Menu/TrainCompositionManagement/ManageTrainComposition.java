package Menu.TrainCompositionManagement;

import Menu.ChooseManagement;
import Menu.Exception.DoesntExist;
import Menu.Interfaces.CorrectType;
import TrainComposition.Exceptions.*;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainComposition;

import java.util.List;
public class ManageTrainComposition implements CorrectType {
    ChooseManagement chooseManagement = new ChooseManagement();
    public void add(
            List<TrainComposition> trainCompositionList,
            List<TrainCar> trainCarList
    ) throws
            DoesntExist
    {

        TrainComposition chosenTrainComposition = chooseManagement.getCorrectTrainComposition(trainCompositionList);

        if(trainCarList.size() < 1){
            throw new DoesntExist("Train car doesn't exist! Create first!");
        }

        TrainCar chosenTrainCar = chooseManagement.getCorrectTrainCar(trainCarList);

        try{
            assert chosenTrainComposition != null;
            chosenTrainComposition.add(chosenTrainCar);
            trainCarList.remove(chosenTrainCar);
            System.out.println("The Train Car plugged successfully!");
        }catch (
                TooManyElectricCarsException |
                TooHeavyToGoException |
                TooManyCarsException |
                IsAlreadyPluggedException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void remove(
            List<TrainComposition> trainCompositionList,
            List<TrainCar> trainCarList
    ) throws
            IsNotAlreadyPluggedException
    {
        TrainComposition chosenTrainComposition = chooseManagement.getCorrectTrainComposition(trainCompositionList);

        List<TrainCar> localTrainCarList = trainCompositionList.stream()
                .flatMap(x -> x.getTrainCars().stream())
                .toList();

        if(!(localTrainCarList.size() > 0)){
            throw new IsNotAlreadyPluggedException(
                    "Train set does not include any train cars! Please plug first!"
            );
        }

        TrainCar chosenTrainCar = chooseManagement.getCorrectTrainCar(localTrainCarList);

        try{
            assert chosenTrainComposition != null;
            chosenTrainComposition.remove(chosenTrainCar);
            trainCarList.add(chosenTrainCar);
            System.out.println("The Train Car unplugged successfully!");
        }catch (IsNotAlreadyPluggedException e) {
            System.out.println(e.getMessage());
        }
    }
}
