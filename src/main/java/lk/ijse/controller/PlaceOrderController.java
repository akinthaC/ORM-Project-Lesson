package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PlaceOrderController {

    @FXML
    private TableColumn<?, ?> ColQty;

    @FXML
    private JFXComboBox<?> ComCusContact;

    @FXML
    private TableColumn<?, ?> UnitPrice;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private JFXComboBox<?> comItemName;

    @FXML
    private Label lblAvalibleQty;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private TableView<?> tblcustomer;

    @FXML
    private TextField txtQty;

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnPLaceOrderOnAction(ActionEvent event) {

    }

}
