package lk.ijse.BO;


import lk.ijse.BO.custom.impl.CustomerBoImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory(){
        return (boFactory == null) ? new BOFactory() : boFactory;
    }

    public enum BOType{
        CUSTOMER
    }
    public SuperBo GetBo(BOType boType){
        switch (boType) {
            case CUSTOMER:
                return new CustomerBoImpl();

            default:
                return null;

        }

    }
}
