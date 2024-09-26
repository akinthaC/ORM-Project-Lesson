package lk.ijse.dao.custom.impl;

import lk.ijse.Entity.Customer;
import lk.ijse.Entity.Item;
import lk.ijse.confit.FactoryConfiguration;
import lk.ijse.dao.custom.ItemDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item DTO) throws SQLException, ClassNotFoundException {
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

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Object item = session.createQuery("SELECT id FROM Item ORDER BY id DESC LIMIT 1").uniqueResult();
        String id ="";
        if (item!=null) {
            String itemId = item.toString();

            String[] split = itemId.split("[iI]+");

            int idNum = Integer.parseInt(split[1]);

            id= "I" + String.format("%03d", ++idNum);
        }else {
            return "I001";
        }

        transaction.commit();

        session.close();
        return id;
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Item> items = session.createQuery("from Item").list();
        transaction.commit();
        session.close();
        return items ;
    }

    @Override
    public boolean update(Item DTO) throws SQLException, ClassNotFoundException {
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
    public boolean delete(Item DTO) throws SQLException, ClassNotFoundException {
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
    public Item searchById(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<Item> query = session.createQuery("FROM Item WHERE id = :id", Item.class);
        query.setParameter("id", id);
        Item item = query.uniqueResult();

        transaction.commit();
        session.close();

        return item;
    }


}
