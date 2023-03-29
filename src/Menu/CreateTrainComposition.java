package Menu;

import Menu.Exception.ThereIsNoSuchLocomotiveYet;
import Menu.Exception.ThisLocomotiveIsAlreadyUsed;
import Menu.Interfaces.CorrectType;
import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainComposition;

import java.util.List;
import java.util.Scanner;

public class CreateTrainComposition implements CorrectType {
    Scanner scan = new Scanner(System.in);
    public void initialize(
            List<TrainComposition> trainCompositionList,
            List<Locomotive> locomotiveList
    ) throws
            ThereIsNoSuchLocomotiveYet,
            ThisLocomotiveIsAlreadyUsed
    {

        locomotiveList.stream().map(Locomotive::toString).forEach(System.out::println);

        System.out.println("Which locomotive do you want to use? (uid): ");
        int locomotiveUid = getValue(scan, Integer.class);

        Locomotive locomotiveToAdd = locomotiveList
                .stream()
                .filter( i -> i.getUid() == locomotiveUid)
                .findFirst()
                .orElse(null);

        Locomotive locomotiveExist = trainCompositionList.stream()
                .map(TrainComposition::getLocomotive)
                .filter(i -> i == locomotiveToAdd)
                .findFirst()
                .orElse(null);

        if(locomotiveToAdd == null){
            throw new ThereIsNoSuchLocomotiveYet(
                    "There is no such locomotive here! Try to create one, or choose another"
            );
        }

        if(locomotiveExist != null){
            throw new ThisLocomotiveIsAlreadyUsed(
                    "This locomotive is already used! Try another"
            );
        }

        trainCompositionList.add(new TrainComposition(locomotiveToAdd));
    }
}
