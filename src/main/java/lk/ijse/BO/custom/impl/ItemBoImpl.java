package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.ItemBo;
import lk.ijse.Entity.Customer;
import lk.ijse.Entity.Item;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.custom.ItemDao;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {

    ItemDao itemDao = (ItemDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    @Override
    public boolean saveCustomer(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDao.save(new Item(itemDTO.getId(),itemDTO.getName(),itemDTO.getQty(),itemDTO.getUnitPrice()));
    }

    @Override
    public boolean upadteItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDao.update(new Item(itemDTO.getId(),itemDTO.getName(),itemDTO.getQty(),itemDTO.getUnitPrice()));
    }

    @Override
    public boolean deleteItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDao.delete(new Item(itemDTO.getId(),itemDTO.getName(),itemDTO.getQty(),itemDTO.getUnitPrice()));
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return itemDao.getCurrentId();
    }

    @Override
    public List<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        List<Item> items= itemDao.getAll();

        List<ItemDTO> itemDTOS=new ArrayList<>();

        for (Item item : items) {
            itemDTOS.add(new ItemDTO(
                    item.getId(),
                    item.getName(),
                    item.getQty(),
                    item.getUnitPrice()
            ));
        }
        return itemDTOS;
    }

    @Override
    public ItemDTO ItemSearchById(String id) throws SQLException, ClassNotFoundException {
        Item itemDTO= itemDao.searchById(id);
        return new ItemDTO(itemDTO.getId(),itemDTO.getName(),itemDTO.getQty(),itemDTO.getUnitPrice());

    }


}
