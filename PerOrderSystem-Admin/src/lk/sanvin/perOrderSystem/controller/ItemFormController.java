/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import lk.sanvin.perOrderSystem.dto.ItemDTO;
import lk.sanvin.perOrderSystem.proxy.ProxyHandler;
import lk.sanvin.perOrderSystem.service.ServiceFactory;
import lk.sanvin.perOrderSystem.service.custom.ItemService;
import lk.sanvin.perOrderSystem.util.Notifi;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class ItemFormController implements Initializable {

    ObservableList<ItemDTO> List = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSDelete;
    @FXML
    private JFXButton btnClear;
    @FXML
    private TableView<ItemDTO> tblItem;
    @FXML
    private TableColumn<ItemDTO, String> clmName;
    @FXML
    private TableColumn<ItemDTO, Double> clmUnitPrice;
    @FXML
    private JFXTextField txtName1;
    private int id;
    ItemService service;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            service = (ItemService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ITM);
        } catch (Exception ex) {
            Logger.getLogger(CookFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initCol();
        loadTable();
        tblGetItem();
    }

    @FXML
    private void btnAddAction(ActionEvent event) {
        if (!txtName.getText().isEmpty()) {
            if (txtUnitPrice.getText().matches("^([0-9.]){1,95}")) {
                try {
                    ItemDTO dto = new ItemDTO();
                    dto.setId(id);
                    dto.setDiscription(txtName.getText());
                    dto.setAmount(Double.parseDouble(txtUnitPrice.getText()));
                    service.add(dto);
                    txtUnitPrice.setUnFocusColor(Color.rgb(77, 77, 77));
                    txtName.setUnFocusColor(Color.rgb(77, 77, 77));
                    clearTextFields();
                    loadTable();
                    new Notifi("Update Sucess", "Sucessfully update a Item");
                } catch (Exception ex) {
                    Logger.getLogger(TPOFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Notifi n1 = new Notifi("Invalid Input", "Check Unit Price", "asd");
            }
        } else {
            Notifi n1 = new Notifi("Invalid Input", "Check Discription", "asd");
        }
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) {
        if (!txtName.getText().isEmpty()) {
            if (txtUnitPrice.getText().matches("^([0-9.]){1,95}")) {
                try {
                    ItemDTO dto = new ItemDTO();
                    dto.setId(id);
                    dto.setDiscription(txtName.getText());
                    dto.setAmount(Double.parseDouble(txtUnitPrice.getText()));
                    service.update(dto);
                    txtUnitPrice.setUnFocusColor(Color.rgb(77, 77, 77));
                    txtName.setUnFocusColor(Color.rgb(77, 77, 77));
                    clearTextFields();
                    loadTable();
                    new Notifi("Update Sucess", "Sucessfully update a student");
                } catch (Exception ex) {
                    Logger.getLogger(TPOFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Notifi n1 = new Notifi("Invalid Input", "Check Unit Price", "asd");
            }
        } else {
            Notifi n1 = new Notifi("Invalid Input", "Check Discription", "asd");
        }
    }

    @FXML
    private void btnSDeleteAction(ActionEvent event) {
        try {
            ItemDTO dto = new ItemDTO();
            dto.setId(id);
            dto.setDiscription(txtName.getText());
            dto.setAmount(Double.parseDouble(txtUnitPrice.getText()));
            service.remove(dto);
            loadTable();
            new Notifi("Delete Sucess", "Sucessfully delete a Student");
        } catch (Exception ex) {
            Logger.getLogger(TPOFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnClearAction(ActionEvent event) {
        clearTextFields();
    }

    private void initCol() {
        clmName.setCellValueFactory(new PropertyValueFactory<>("discription"));
        clmUnitPrice.setCellValueFactory(new PropertyValueFactory<>("amount"));

    }

    private void loadTable() {
        try {
            List.clear();
            List<ItemDTO> all = service.getAll();
            for (ItemDTO dto : all) {
                List.add(dto);
            }
            tblItem.getItems().setAll(List);
        } catch (Exception ex) {
            Logger.getLogger(TPOFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void tblGetItem() {
        tblItem.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    id = tblItem.getSelectionModel().getSelectedItem().getId();
                    txtName.setText(tblItem.getSelectionModel().getSelectedItem().getDiscription());
                    txtUnitPrice.setText(tblItem.getSelectionModel().getSelectedItem().getAmount() + "");
                } catch (Exception e) {
                }

            }
        });
    }

    @FXML
    private void txtAmountKeyReleasdAction(KeyEvent event) {

    }

    private void clearTextFields() {
        txtName.clear();
        txtUnitPrice.clear();

    }
}
