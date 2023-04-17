package Menu;

import Menu.Exception.AlreadyOnJourney;
import Menu.Exception.IsAlreadyPlugged;
import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainCars.Abstract.TrainCar;
import TrainComposition.TrainComposition;

import java.util.List;

public class CreateDeletingObjects {
    ChooseManagement chooseManagement = new ChooseManagement();
    public void deleteTrainCar(
            List<TrainCar> trainCarList
    ){
        TrainCar trainCar = chooseManagement.getCorrectTrainCar(trainCarList);
        trainCarList.remove(trainCar);

    }

    public void deleteTrainComposition (
            List<TrainComposition> trainCompositionList
    ) throws
            IsAlreadyPlugged,
            AlreadyOnJourney
    {
        TrainComposition trainComposition = chooseManagement.getCorrectTrainComposition(trainCompositionList);
        if(trainComposition.getTrainCars().size() > 0){
            throw new IsAlreadyPlugged("Train composition includes train cars, remove first!");
        }

        if(trainComposition.getLocomotive().getFinalStation() != null){
            throw new AlreadyOnJourney("Train composition is already on journey. Stop first!");
        }

        trainCompositionList.remove(trainComposition);
    }

    public void deleteLocomotive(
            List<TrainComposition> trainCompositionList,
            List<Locomotive> locomotiveList
    ) throws
            IsAlreadyPlugged
    {
        Locomotive locomotive = chooseManagement.getCorrectLocomotive(locomotiveList);

        boolean isExist = trainCompositionList.stream()
                .map(TrainComposition::getLocomotive)
                .anyMatch(x -> x == locomotive);

        TrainComposition trainComposition = trainCompositionList.stream()
                .filter( x -> x.getLocomotive() == locomotive)
                .findFirst()
                .orElse(null);

        if(isExist){
            assert trainComposition != null;
            throw new IsAlreadyPlugged("This locomotive is used by train composition id: " + trainComposition.getUid());
        }

        locomotiveList.remove(locomotive);
    }

}
