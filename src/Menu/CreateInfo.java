package Menu;

import TrainComposition.TrainCars.Load;
import TrainComposition.TrainComposition;

import java.util.List;
import java.util.stream.Collectors;

public class CreateInfo {
    ChooseManagement chooseManagement = new ChooseManagement();

    public String trainComposition(TrainComposition trainComposition) {
        return trainComposition.getLocomotive().getFinalStation() == null ?
                "====================================================================================" +
                        "\n" + trainComposition.getUid() + ". Train Composition includes: " +
                        " \n Locomotive: " + trainComposition.getLocomotive() +
                        "\n Train Cars (uid): " +
                        (
                                trainComposition.getTrainCars().stream()
                                        .map(trainCar -> {
                                            String loadInfo = trainCar.getLoadList().stream()
                                                    .map(Load::toString)
                                                    .collect(Collectors.joining(", "));
                                            return trainCar.getUid() + (loadInfo.isEmpty() ? ": No load" : ": " + loadInfo);
                                        })
                                        .collect(Collectors.joining(", \n       "))
                        ) +
                        "\n State: " +
                        "\n     On parking" +
                        "====================================================================================" :
                "====================================================================================" +
                        "\n" + trainComposition.getUid() + ". Train Composition includes: " +
                        " \n Locomotive: " + trainComposition.getLocomotive()  +
                        "\n Train Cars (uid): " +
                        (
                                trainComposition.getTrainCars().stream()
                                        .map(trainCar -> {
                                            String loadInfo = trainCar.getLoadList().stream()
                                                    .map(Load::toString)
                                                    .collect(Collectors.joining(", "));
                                            return trainCar.getUid() + (loadInfo.isEmpty() ? ": No load" : ": " + loadInfo);
                                        })
                                        .collect(Collectors.joining(", \n     "))
                        ) +
                        "\n State: " +
                        "\n    Percent of the entire route: " + (int)trainComposition.getLocomotive().getJourneyPercent() + "%" +
                        "\n    Percent to the next station: " + (int)trainComposition.getLocomotive().getToStationPercent() + "%" +
                        "\n====================================================================================";
    }

    public void show(
            List<TrainComposition> trainCompositionList
    ){
        TrainComposition trainComposition = chooseManagement.getCorrectTrainComposition(
                trainCompositionList
        );

        System.out.println(trainComposition(trainComposition));
    }
}
