package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBo;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo extends SuperBo {


    boolean saveCustomer(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean upadteItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean deleteItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    public  String getCurrentId() throws SQLException, ClassNotFoundException;
    public List<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
    public ItemDTO ItemSearchById(String id) throws SQLException, ClassNotFoundException;
}
