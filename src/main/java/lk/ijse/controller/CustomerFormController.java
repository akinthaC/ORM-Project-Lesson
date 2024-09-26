package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.CustomerBo;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.tdm.CustomerTm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TextField txtCusAddress;

    @FXML
    private TextField txtCusId;

    @FXML
    private TextField txtCusName;

    @FXML
    private TextField txtCusTel;
    @FXML
    private TableView<CustomerTm> tblcustomer;

    CustomerBo customerBo = (CustomerBo) BOFactory.getBoFactory().GetBo(BOFactory.BOType.CUSTOMER);

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
        getCurrentOrderId();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));

    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id=txtCusId.getText();
        String name = txtCusName.getText();
        String address = txtCusAddress.getText();
        String tel = txtCusTel.getText();

        CustomerDTO customer = new CustomerDTO(id, name, address, tel);


        try {
            boolean isSaved = customerBo.saveCustomer(customer);
            System.out.println(isSaved);
            if (isSaved) {
                System.out.println(isSaved);
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saved!!!.").show();
                setCellValueFactory();
                loadAllCustomers();
                clearFields();
                getCurrentOrderId();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void searchBy(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtCusId.getText();

        CustomerDTO customer = customerBo.CusSearchById(id);
        if (customer != null) {
            txtCusId.setText(customer.getId());
            txtCusName.setText(customer.getName());
            txtCusAddress.setText(customer.getAddress());
            txtCusTel.setText(customer.getTel());


        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (txtCusId.getText() == null) {
            new Alert(Alert.AlertType.ERROR, "Id column is empty Please enter a valid ID!").show();
            return;
        }
        String id=txtCusId.getText();
        String name = txtCusName.getText();
        String address = txtCusAddress.getText();
        String tel = txtCusTel.getText();

        CustomerDTO customer = new CustomerDTO(id, name, address, tel);

        boolean isDelete = customerBo.deleteCustomer((customer));

        if (isDelete) {
            new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted!!!.").show();
            setCellValueFactory();
            loadAllCustomers();
            clearFields();
            getCurrentOrderId();
        }

    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (txtCusId.getText() == null || txtCusName.getText() ==null) {
            new Alert(Alert.AlertType.ERROR, "Id column is empty Please enter a valid ID!").show();
            return;
        }
        String id=txtCusId.getText();
        String name = txtCusName.getText();
        String address = txtCusAddress.getText();
        String tel = txtCusTel.getText();

        CustomerDTO customer = new CustomerDTO(id, name, address, tel);

        boolean isUpdated = customerBo.upadteCustomer((customer));

        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated!!!.").show();
            setCellValueFactory();
            loadAllCustomers();
            clearFields();
            getCurrentOrderId();
        }else {
            new Alert(Alert.AlertType.ERROR, "Customer not updated!!!").show();
        }

    }

    private void clearFields() {
        txtCusId.setText("");
        txtCusName.setText("");
        txtCusAddress.setText("");
        txtCusTel.setText("");
    }

    private void getCurrentOrderId() {
        try {
            String currentId = customerBo.getCurrentId();


            txtCusId.setText(currentId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDTO> customerList = customerBo.getAllCustomers();
            for (CustomerDTO customer : customerList) {
                CustomerTm tm = new CustomerTm(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getTel()
                );

                obList.add(tm);
            }

            tblcustomer.setItems(obList);
            setCellValueFactory();
            getCurrentOrderId();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
