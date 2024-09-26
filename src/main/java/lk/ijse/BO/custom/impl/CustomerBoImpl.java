
package lk.ijse.BO.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.BO.custom.CustomerBo;
import lk.ijse.Entity.Customer;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    CustomerDao customerDao = (CustomerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
       return customerDao.save(new Customer(customer.getId(),customer.getName(),customer.getAddress(),customer.getTel()));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> accessoriesDTOArrayList= customerDao.getAll();

        List<CustomerDTO> customerDTOS=new ArrayList<>();

        for (Customer customer : accessoriesDTOArrayList) {
            customerDTOS.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getTel()
            ));
        }
        return customerDTOS;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return customerDao.getCurrentId();
    }

    @Override
    public boolean deleteCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDao.delete(new Customer(customer.getId(),customer.getName(),customer.getAddress(),customer.getTel()));
    }

    @Override
    public boolean upadteCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(customer.getId(),customer.getName(),customer.getAddress(),customer.getTel()));

    }

    @Override
    public CustomerDTO CusSearchById(String id) throws SQLException, ClassNotFoundException {
        Customer customer= customerDao.searchById(id);
        return new CustomerDTO( customer.getId(),customer.getName(),customer.getAddress(),customer.getTel());

    }


}
