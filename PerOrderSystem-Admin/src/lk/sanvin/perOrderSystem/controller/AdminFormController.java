/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sanvin.perOrderSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
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
import lk.sanvin.perOrderSystem.dto.AdminDTO;
import lk.sanvin.perOrderSystem.dto.CookDTO;
import lk.sanvin.perOrderSystem.proxy.ProxyHandler;
import lk.sanvin.perOrderSystem.service.ServiceFactory;
import lk.sanvin.perOrderSystem.service.custom.AdminService;
import lk.sanvin.perOrderSystem.service.custom.CookService;
import lk.sanvin.perOrderSystem.util.Notifi;

/**
 * FXML Controller class
 *
 * @author LahiruPG
 */
public class AdminFormController implements Initializable {

    ObservableList<AdminDTO> List = FXCollections.observableArrayList();

    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXRadioButton rdbMale;
    @FXML
    private JFXRadioButton rdbFemale;
    @FXML
    private JFXTextField txtNic;
    @FXML
    private JFXTextField txtDob;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextArea txtAddress;
    @FXML
    private JFXTextField txtPassword;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSDelete;
    @FXML
    private JFXButton btnClear;
    @FXML
    private TableView<AdminDTO> tblCook;
    @FXML
    private TableColumn<AdminDTO, String> clmName;
    @FXML
    private TableColumn<AdminDTO, String> clmGender;
    @FXML
    private TableColumn<AdminDTO, String> clmNIC;
    @FXML
    private TableColumn<AdminDTO, String> clmDob;
    @FXML
    private TableColumn<AdminDTO, String> clmContactNo;
    @FXML
    private TableColumn<AdminDTO, String> clmPassword;
    @FXML
    private TableColumn<AdminDTO, String> clmAddress;
    @FXML
    private JFXTextField txtName1;

    private String gender = "";
    private int id;
    AdminService service;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            service =  (AdminService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
        } catch (Exception ex) {
            Logger.getLogger(AdminFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initCol();
       loadTable();
       tblGetItem();
    }

    @FXML
    private void rdbMaleAction(ActionEvent event) {
        rdbFemale.setSelected(false);
        gender = "Male";
    }

    @FXML
    private void rdbFemaleAction(ActionEvent event) {
        rdbMale.setSelected(false);
        gender = "Female";
    }

    @FXML
    private void txtNicKeyReleasdAction(KeyEvent event) {
        if (txtNic.getText().matches("[0-9]{9}V")) {
            txtNic.setStyle("-fx-text-fill: black;");
            txtDob.requestFocus();
        } else {
            txtNic.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void txtDobKeyReleasedAction(KeyEvent event) {
        if (txtDob.getText().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
            txtDob.setStyle("-fx-text-fill: black;");
            txtContact.requestFocus();
        } else {
            txtDob.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void txtContactKeyReleasedAction(KeyEvent event) {
        if (txtContact.getText().matches("^[0-9]{3}-[0-9]{7}$")) {
            txtContact.setStyle("-fx-text-fill: black;");
            txtAddress.requestFocus();
        } else {
            txtContact.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void btnAddAction(ActionEvent event) {
        if (txtName.getText().matches("^([A-Z a-z0-9.]){1,95}")) {
            if (rdbFemale.isSelected() || rdbMale.isSelected()) {
                if (txtAddress.getText().matches("^([A-Z a-z0-9.,\\n]){1,95}.$")) {
                    if (txtContact.getText().matches("^[0-9]{3}-[0-9]{7}$")) {
                        if (txtDob.getText().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
                            if (txtNic.getText().matches("^([A-Z a-z0-9.]){1,95}")) {
                                try {
                                    AdminDTO dto = new AdminDTO();
                                    dto.setName(txtName.getText());
                                    dto.setGender(gender);
                                    dto.setNic(txtNic.getText());
                                    dto.setDob(txtDob.getText());
                                    dto.setContact(txtContact.getText());
                                    dto.setAddress(txtAddress.getText());
                                    dto.setPassword(txtPassword.getText());
                                    service.add(dto);
                                    txtAddress.setUnFocusColor(Color.rgb(77, 77, 77));
                                    txtName.setUnFocusColor(Color.rgb(77, 77, 77));
                                    clearTextFields();
                                    loadTable();
                                    new Notifi("Added Sucess", "Sucessfully added new Admin");
                                } catch (Exception ex) {
                                    Logger.getLogger(TPOFormController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                Notifi n1 = new Notifi("Invalid Input", "Check NIC", "asd");
                            }
                        } else {
                            Notifi n1 = new Notifi("Invalid Input", "Check DOB", "asd");
                        }
                    } else {
                        Notifi n1 = new Notifi("Invalid Input", "Check Contact", "asd");
                    }
                } else {
                    txtAddress.setUnFocusColor(Color.RED);
                    Notifi n1 = new Notifi("Invalid Input", "Check Address", "asd");
                }
            } else {
                Notifi n1 = new Notifi("Invalid Input", "Select Gender", "asd");
            }
        } else {
            System.out.println("unfource color");
            txtName.setUnFocusColor(Color.RED);
            Notifi n1 = new Notifi("Invalid Input", "Check Name", "asd");
        }
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) {
        if (txtName.getText().matches("^([A-Z a-z0-9.]){1,95}")) {
            if (rdbFemale.isSelected() || rdbMale.isSelected()) {
                if (txtAddress.getText().matches("^([A-Z a-z0-9.,\\n]){1,95}.$")) {
                    if (txtContact.getText().matches("^[0-9]{3}-[0-9]{7}$")) {
                        if (txtDob.getText().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
                            if (txtNic.getText().matches("^([A-Z a-z0-9.]){1,95}")) {
                                try {
                                    AdminDTO dto = new AdminDTO();
                                    dto.setId(id);
                                    dto.setName(txtName.getText());
                                    dto.setGender(gender);
                                    dto.setNic(txtNic.getText());
                                    dto.setDob(txtDob.getText());
                                    dto.setContact(txtContact.getText());
                                    dto.setAddress(txtAddress.getText());
                                    dto.setPassword(txtPassword.getText());
                                    service.update(dto);
                                    txtAddress.setUnFocusColor(Color.rgb(77, 77, 77));
                                    txtName.setUnFocusColor(Color.rgb(77, 77, 77));
                                    clearTextFields();
                                    loadTable();
                                    new Notifi("Update Sucess", "Sucessfully update a Admin");
                                } catch (Exception ex) {
                                    Logger.getLogger(TPOFormController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                Notifi n1 = new Notifi("Invalid Input", "Check NIC", "asd");
                            }
                        } else {
                            Notifi n1 = new Notifi("Invalid Input", "Check DOB", "asd");
                        }
                    } else {
                        Notifi n1 = new Notifi("Invalid Input", "Check Contact", "asd");
                    }
                } else {
                    txtAddress.setUnFocusColor(Color.RED);
                    Notifi n1 = new Notifi("Invalid Input", "Check Address", "asd");
                }
            } else {
                Notifi n1 = new Notifi("Invalid Input", "Select Gender", "asd");
            }
        } else {
            System.out.println("unfource color");
            txtName.setUnFocusColor(Color.RED);
            Notifi n1 = new Notifi("Invalid Input", "Check Name", "asd");
        }
    }

    @FXML
    private void btnSDeleteAction(ActionEvent event) {
 try {
            AdminDTO dto = new AdminDTO();
            dto.setId(id);
            dto.setName(txtName.getText());
            dto.setGender(gender);
            dto.setNic(txtNic.getText());
            dto.setDob(txtDob.getText());
            dto.setContact(txtContact.getText());
            dto.setAddress(txtAddress.getText());
            service.remove(dto);
            loadTable();
            new Notifi("Delete Sucess", "Sucessfully delete a Admin");
        } catch (Exception ex) {
            Logger.getLogger(TPOFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnClearAction(ActionEvent event) {
        clearTextFields();
    }

    private void clearTextFields() {
        txtName.clear();
        rdbFemale.setSelected(false);
        rdbMale.setSelected(false);
        txtAddress.clear();
        txtContact.clear();
        txtDob.clear();
        txtNic.clear();
        txtPassword.clear();
    }

    private void initCol() {
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmContactNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        clmNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        clmPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    private void loadTable() {
        try {
            List.clear();
            List<AdminDTO> all = service.getAll();
            for (AdminDTO tpo : all) {
                List.add(tpo);
            }
            tblCook.getItems().setAll(List);
        } catch (Exception ex) {
            Logger.getLogger(TPOFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void tblGetItem() {
        tblCook.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String gen = "";

                id = tblCook.getSelectionModel().getSelectedItem().getId();
                txtName.setText(tblCook.getSelectionModel().getSelectedItem().getName());
                gen = tblCook.getSelectionModel().getSelectedItem().getGender();
                txtAddress.setText(tblCook.getSelectionModel().getSelectedItem().getAddress());
                txtContact.setText(tblCook.getSelectionModel().getSelectedItem().getContact());
                txtDob.setText(tblCook.getSelectionModel().getSelectedItem().getDob());
                txtNic.setText(tblCook.getSelectionModel().getSelectedItem().getNic());
                txtPassword.setText(tblCook.getSelectionModel().getSelectedItem().getPassword());

                if (gen.matches("Male")) {
                    rdbMale.setSelected(true);
                    rdbFemale.setSelected(false);
                    gender = "Male";
                } else {

                    rdbMale.setSelected(false);
                    rdbFemale.setSelected(true);
                    gender = "Female";
                }

                //rdbMale.setSelected(true);
            }
        });
    }
}
