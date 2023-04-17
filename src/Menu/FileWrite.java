package Menu;

import TrainComposition.TrainComposition;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileWrite implements Runnable{
    private final List<TrainComposition> trainCompositionList;
    public FileWrite(List<TrainComposition> trainCompositionList){
        this.trainCompositionList = trainCompositionList;
    }
    @Override
    public void run() {
        CreateInfo info = new CreateInfo();
        while(true){
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("AppState.txt")
                );
                String a = trainCompositionList.stream()
                        .filter(x -> x.getLocomotive().getFinalStation() != null)
                        .sorted(Comparator.comparing(x ->x.getLocomotive().getJourneyPercent()))
                        .map(info::trainCompositionInfo)
                        .collect(Collectors.joining("\n"));
                String b = trainCompositionList.stream()
                        .filter(x -> x.getLocomotive().getFinalStation() == null)
                        .sorted(Comparator.comparing(x ->x.getLocomotive().getJourneyPercent()))
                        .map(info::trainCompositionInfo)
                        .collect(Collectors.joining("\n"));
                writer.write("Pociągi w podróży: \n" + a + "\n \n Pociągi na parkingu: \n" + b);
                writer.close();
                Thread.sleep(5000);
            }catch(IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }
}
