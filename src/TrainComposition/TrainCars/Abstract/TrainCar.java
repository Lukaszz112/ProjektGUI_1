package TrainComposition.TrainCars.Abstract;

import TrainComposition.Locomotive.Locomotive;
import TrainComposition.TrainCars.Exceptions.TooHeavyGoods;
import TrainComposition.TrainCars.Load;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class TrainCar {
    private final double netWeight;
    private final double grossWeight;
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int uid;

    private final List<Load> loadList = new ArrayList<>();

    public List<Load> getLoadList() {
        return loadList;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public int getUid() {
        return uid;
    }

    public TrainCar(
            String security,
            double netWeight,
            double grossWeight
    ) {
        this.netWeight = netWeight;
        this.grossWeight = grossWeight;
        this.uid = count.incrementAndGet();
    }

    public void addLoad(
            Locomotive locomotive,
            String nameOfGoods,
            double weightOfTheGoods
    ) throws
            TooHeavyGoods
    {

        double weightOfAllStuff = loadList.stream()
                .map(Load::getWeight)
                .reduce(Double::sum)
                .orElse((double)0);

        if(weightOfTheGoods > locomotive.getAvailableWeightOfLoads()){
            throw new TooHeavyGoods(
                    "This locomotive is to weak to pull such a load!"
            );
        }
        if(getNetWeight() + weightOfAllStuff + weightOfTheGoods > getGrossWeight()){
            throw new TooHeavyGoods(
                    "These goods are too heavy for current car! Choose another one or remove some goods"
            );
        }

        loadList.add(new Load(nameOfGoods,weightOfTheGoods));
    }

    public void removeLoad(
            Load load
    ){
        loadList.remove(load);
    }


}
