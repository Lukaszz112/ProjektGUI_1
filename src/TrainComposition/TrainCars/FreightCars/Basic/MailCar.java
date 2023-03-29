package TrainComposition.TrainCars.FreightCars.Basic;

import TrainComposition.TrainCars.Interfaces.ElectricCars;
import TrainComposition.TrainCars.Abstract.FreightCars;
import TrainComposition.TrainCars.Abstract.TrainCarFreightType;

public class MailCar extends FreightCars implements ElectricCars {
    private int numOfShipments;
    private String responsibleCompany;

    public MailCar(
            String security,
            double netWeight,
            double grossWeight,
            int numOfShipments,
            String responsibleCompany
    ) {
        super(
                security,
                netWeight,
                grossWeight,
                TrainCarFreightType.MAIL_CAR
        );

        this.numOfShipments = numOfShipments;
        this.responsibleCompany = responsibleCompany;
    }
}
