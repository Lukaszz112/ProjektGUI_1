package TrainComposition.TrainCars.FreightCars.Basic;

import TrainComposition.TrainCars.Interfaces.ElectricCars;
import TrainComposition.TrainCars.Abstract.FreightCars;
import TrainComposition.TrainCars.Abstract.TrainCarFreightType;

public class MailCar extends FreightCars implements ElectricCars {
    private int numOfShipments;
    private String responsibleCompany;

    public MailCar(
            String security,
            String sender,
            double netWeight,
            double grossWeight,
            int numOfShipments,
            String responsibleCompany
    ) {
        super(
                security,
                sender,
                netWeight,
                grossWeight,
                TrainCarFreightType.MAIL_CAR
        );

        this.numOfShipments = numOfShipments;
        this.responsibleCompany = responsibleCompany;
    }

    @Override
    public String toString() {
        return "Mail car: " + super.toString() +
                ", number of shipments: " + numOfShipments +
                ", responsible company: " + responsibleCompany;
    }
}
