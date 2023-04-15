package Menu;

import TrainComposition.TrainComposition;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FileWrite implements Runnable{
    private List<TrainComposition> trainCompositionList;
    public FileWrite(List<TrainComposition> trainCompositionList){
        this.trainCompositionList = trainCompositionList;
    }
    @Override
    public void run() {
        CreateInfo info = new CreateInfo();
        while(true){
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("trainStateInfo.txt")
                );
                String a = trainCompositionList.stream()
                                .map(info::trainCompositionInfo)
                                .collect(Collectors.joining("\n"));
                writer.write(a);
                writer.close();
                Thread.sleep(5000);
            }catch(IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }
}
