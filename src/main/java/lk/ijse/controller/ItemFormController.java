package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.CustomerBo;
import lk.ijse.BO.custom.ItemBo;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.tdm.CustomerTm;
import lk.ijse.tdm.ItemTm;

import java.sql.SQLException;
import java.util.List;

public class ItemFormController {

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXComboBox<?> txtType;

    @FXML
    private TextField txtUnitPrice;

    ItemBo itemBo = (ItemBo) BOFactory.getBoFactory().GetBo(BOFactory.BOType.Item);

    public void initialize() {
        setCellValueFactory();
        loadAllItem();
        getCurrentItemId();
    }

    private void getCurrentItemId() {
        try {
            String currentId = itemBo.getCurrentId();


            txtItemId.setText(currentId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void searchBy(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtItemId.getText();

        ItemDTO item = itemBo.ItemSearchById(id);
        if (item != null) {
            txtItemId.setText(item.getId());
            txtItemName.setText(item.getName());
            txtQty.setText(item.getQty());
            txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));


        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }

    private void loadAllItem() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            List<ItemDTO> customerList = itemBo.getAllItems();
            for (ItemDTO item : customerList) {
                ItemTm tm = new ItemTm(
                        item.getId(),
                        item.getName(),
                        item.getQty(),
                        item.getUnitPrice()
                );

                obList.add(tm);
            }

            tblItem.setItems(obList);
            setCellValueFactory();
            getCurrentItemId();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (txtItemId.getText() == null) {
            new Alert(Alert.AlertType.ERROR, "Id column is empty Please enter a valid ID!").show();
            return;
        }
        String itemId = txtItemId.getText();
        String itemName = txtItemName.getText();
        String qty = txtQty.getText();
        Double unitPrice = Double.parseDouble(txtUnitPrice.getText());

        ItemDTO itemDTO = new ItemDTO(itemId, itemName, qty, unitPrice);

        boolean isDelete = itemBo.deleteItem((itemDTO));

        if (isDelete) {
            new Alert(Alert.AlertType.CONFIRMATION, "Item deleted!!!.").show();
            setCellValueFactory();
            loadAllItem();
            getCurrentItemId();
            clearFields();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();
        String itemName = txtItemName.getText();
        String qty = txtQty.getText();
        Double unitPrice = Double.parseDouble(txtUnitPrice.getText());

        ItemDTO itemDTO = new ItemDTO(itemId, itemName, qty, unitPrice);

        try {
            boolean isSaved = itemBo.saveCustomer(itemDTO);
            System.out.println(isSaved);
            if (isSaved) {
                System.out.println(isSaved);
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!!!.").show();

                setCellValueFactory();
                loadAllItem();
                getCurrentItemId();
                clearFields();

            }
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void clearFields() {
        txtItemId.setText("");
        txtItemName.setText("");
        txtQty.setText("");
        txtUnitPrice.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (txtItemId.getText() == null || txtItemName.getText() ==null) {
            new Alert(Alert.AlertType.ERROR, "Id column is empty Please enter a valid ID!").show();
            return;
        }
        String itemId = txtItemId.getText();
        String itemName = txtItemName.getText();
        String qty = txtQty.getText();
        Double unitPrice = Double.parseDouble(txtUnitPrice.getText());

        ItemDTO itemDTO = new ItemDTO(itemId, itemName, qty, unitPrice);

        boolean isUpdated = itemBo.upadteItem((itemDTO));

        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Item Updated!!!.").show();
            setCellValueFactory();
            loadAllItem();
            clearFields();
            getCurrentItemId();
        }else {
            new Alert(Alert.AlertType.ERROR, "Item not updated!!!").show();
        }
    }

}
