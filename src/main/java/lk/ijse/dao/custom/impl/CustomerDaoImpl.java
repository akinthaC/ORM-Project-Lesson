package lk.ijse.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.Entity.Customer;
import lk.ijse.confit.FactoryConfiguration;
import lk.ijse.dao.SuperDao;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dto.CustomerDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(Customer DTO) throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(DTO);

        transaction.commit();
        session.close();
        System.out.println(transaction.getStatus());
        if (transaction.getStatus().toString().equals("COMMITTED")){
            return true;
        }
        return false;


    }

    public boolean update(Customer DTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(DTO);

        transaction.commit();
        session.close();
        System.out.println(transaction.getStatus());
        if (transaction.getStatus().toString().equals("COMMITTED")){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Customer DTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(DTO);

        transaction.commit();
        session.close();
        System.out.println(transaction.getStatus());
        if (transaction.getStatus().toString().equals("COMMITTED")){
            return true;
        }
        return false;
    }

    @Override
    public Customer searchById(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<Customer> query = session.createQuery("FROM Customer WHERE id = :id", Customer.class);
        query.setParameter("id", id);
        Customer customer = query.uniqueResult();

        transaction.commit();
        session.close();

        return customer;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Customer> customers = session.createQuery("from Customer").list();
        transaction.commit();
        session.close();
        return customers;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Object customer = session.createQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1").uniqueResult();
        String id ="";
        if (customer!=null) {
            String customerId = customer.toString();

            String[] split = customerId.split("[cC]+");

            int idNum = Integer.parseInt(split[1]);

            id= "C" + String.format("%03d", ++idNum);
        }else {
            return "C001";
        }

        transaction.commit();

        session.close();
        return id;
    }

    /*@Override
    public boolean update(Customer DTO) throws SQLException, ClassNotFoundException {
        return false;
    }



    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }



    @Override
    public Customer searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }*/
}
