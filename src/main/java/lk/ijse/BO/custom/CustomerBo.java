package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBo {
    public  boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;
    public  List<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public  String getCurrentId() throws SQLException, ClassNotFoundException;

    public  boolean deleteCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;

    boolean upadteCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;

    public CustomerDTO CusSearchById(String id) throws SQLException, ClassNotFoundException;

    /*public  boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;

    public  CustomerDTO CusSearchById(String id) throws SQLException, ClassNotFoundException;



    public  List<String> getIds() throws SQLException, ClassNotFoundException;
    public  List<String> getStatus() ;*/

}
