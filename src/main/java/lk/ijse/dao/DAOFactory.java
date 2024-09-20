package lk.ijse.dao;


import lk.ijse.dao.custom.impl.CustomerDaoImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory==null) ?  new DAOFactory(): daoFactory;
    }

    public enum DAOType{
       CUSTOMER
    }
    public SuperDao getDAO(DAOType daoType){
        switch (daoType) {
            case CUSTOMER:

                return new CustomerDaoImpl();
            default:
                return null;

        }

    }
}
