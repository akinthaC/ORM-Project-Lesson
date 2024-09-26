package lk.ijse.BO;


import lk.ijse.BO.custom.impl.CustomerBoImpl;
import lk.ijse.BO.custom.impl.ItemBoImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory(){
        return (boFactory == null) ? new BOFactory() : boFactory;
    }

    public enum BOType{
        CUSTOMER,Item
    }
    public SuperBo GetBo(BOType boType){
        switch (boType) {
            case CUSTOMER:
                return new CustomerBoImpl();

            case Item:
                return new ItemBoImpl();

            default:
                return null;

        }

    }
}
